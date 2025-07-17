package com.soap.musicsuggestion.repo;




import jakarta.annotation.PostConstruct;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.lajpat.guides.music.Mood;
import com.lajpat.guides.music.Song;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class BollywoodSongRepository {

//     private static final List<Song> songList = new ArrayList<>();

//     @PostConstruct
//     public void initData() {
//         // 🎵 HAPPY Songs
//         Song s1 = new Song();
//         s1.setTitle("Gallan Goodiyan");
//         s1.setArtist("Yashita Sharma");
//         s1.setMood(Mood.HAPPY);
//         s1.setRating(4.5f);
//         songList.add(s1);

//         Song s2 = new Song();
//         s2.setTitle("Badtameez Dil");
//         s2.setArtist("Benny Dayal");
//         s2.setMood(Mood.HAPPY);
//         s2.setRating(4.6f);
//         songList.add(s2);

//         // 🎵 SAD Songs
//         Song s3 = new Song();
//         s3.setTitle("Tum Hi Ho");
//         s3.setArtist("Arijit Singh");
//         s3.setMood(Mood.SAD);
//         s3.setRating(4.8f);
//         songList.add(s3);

//         Song s4 = new Song();
//         s4.setTitle("Channa Mereya");
//         s4.setArtist("Arijit Singh");
//         s4.setMood(Mood.SAD);
//         s4.setRating(4.7f);
//         songList.add(s4);

//         // 🎵 RELAXING Songs
//         Song s5 = new Song();
//         s5.setTitle("Raabta");
//         s5.setArtist("Arijit Singh");
//         s5.setMood(Mood.RELAXING);
//         s5.setRating(4.6f);
//         songList.add(s5);

//         Song s6 = new Song();
//         s6.setTitle("Phir Le Aaya Dil");
//         s6.setArtist("Rekha Bhardwaj");
//         s6.setMood(Mood.RELAXING);
//         s6.setRating(4.5f);
//         songList.add(s6);

//         // 🎵 ENERGETIC Songs
//         Song s7 = new Song();
//         s7.setTitle("Zinda");
//         s7.setArtist("Siddharth Mahadevan");
//         s7.setMood(Mood.ENERGETIC);
//         s7.setRating(4.7f);
//         songList.add(s7);

//         Song s8 = new Song();
//         s8.setTitle("Malhari");
//         s8.setArtist("Vishal Dadlani");
//         s8.setMood(Mood.ENERGETIC);
//         s8.setRating(4.6f);
//         songList.add(s8);
//     }

//     public List<Song> findTop10ByMood(Mood mood) {
//         Assert.notNull(mood, "Mood must not be null");
//         if (mood == null) {
//     throw new IllegalArgumentException("Please provide a valid mood (HAPPY, SAD, RELAXING, ENERGETIC).");
// }
//         return songList.stream()
//                 .filter(song -> song.getMood().equals(mood))
//                 .sorted((s1, s2) -> Float.compare(s2.getRating(), s1.getRating()))
//                 .limit(10)
//                 .toList();
//     }


private static final List<Song> songList = new ArrayList<>();

@PostConstruct
public void initData() {
    try {
        ClassPathResource resource = new ClassPathResource("songs.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
        String line;
        boolean isFirstLine = true;

        while ((line = reader.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }

            // Split commas outside quotes
            String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

            if (fields.length >= 6) {
                Song song = new Song();
                song.setTitle(fields[1].trim());

                // Clean artist field (remove surrounding quotes if any)
                String artist = fields[2].trim().replaceAll("^\"|\"$", "");
                song.setArtist(artist);

                song.setMood(parseMood(fields[3].trim())); // Mood parsing

                // Skip fields[4] — movie name

                // Parse rating
                String ratingStr = fields[5].trim().replace("/10", "");
                try {
                    song.setRating(Float.parseFloat(ratingStr));
                } catch (NumberFormatException e) {
                    song.setRating(0.0f);
                }

                System.out.println("Loaded: " + song.getTitle());
                songList.add(song);
            }
        }
    } catch (Exception e) {
        throw new RuntimeException("Failed to load songs from CSV", e);
    }
}
public List<Song> findTop10ByMood(Mood mood) {
    Assert.notNull(mood, "Mood must not be null");

    return songList.stream()
            .filter(song -> song.getMood() == mood)
            .sorted((s1, s2) -> Float.compare(s2.getRating(), s1.getRating()))
            .limit(10)
            .toList();
}

private Mood parseMood(String moodStr) {
    moodStr = moodStr.toLowerCase();

    if (moodStr.contains("sad")) return Mood.SAD;
    if (moodStr.contains("romantic")) return Mood.RELAXING;
    if (moodStr.contains("dance") || moodStr.contains("energetic")) return Mood.ENERGETIC;
    if (moodStr.contains("happy")) return Mood.HAPPY;

    return Mood.HAPPY; // fallback
}





}


