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
    moodStr = moodStr.trim().toLowerCase();

    if (moodStr.contains("dancemotivationalpatriotic")) return Mood.DANCE_MOTIVATIONAL_PATRIOTIC;
    if (moodStr.contains("romanticsadsensual")) return Mood.ROMANTIC_SAD_SENSUAL;
    if (moodStr.contains("motivationalpatriotic")) return Mood.MOTIVATIONAL_PATRIOTIC;
    if (moodStr.contains("romanticsensual")) return Mood.ROMANTIC_SENSUAL;
    if (moodStr.contains("dancepatriotic")) return Mood.DANCE_PATRIOTIC;
    if (moodStr.contains("patrioticsad")) return Mood.PATRIOTIC_SAD;
    if (moodStr.contains("romanticsad")) return Mood.ROMANTIC_SAD;
    if (moodStr.contains("devotionalsad")) return Mood.DEVOTIONAL_SAD;
    if (moodStr.contains("danceromantic")) return Mood.DANCE_ROMANTIC;
    if (moodStr.contains("danceromance")) return Mood.DANCE_ROMANCE;
    if (moodStr.contains("dancesensual")) return Mood.DANCE_SENSUAL;
    if (moodStr.contains("dancesad")) return Mood.DANCE_SAD;
    if (moodStr.contains("motivational")) return Mood.MOTIVATIONAL;
    if (moodStr.contains("patriotic")) return Mood.PATRIOTIC;
    if (moodStr.contains("sensual")) return Mood.SENSUAL;
    if (moodStr.contains("romance")) return Mood.ROMANCE;
    if (moodStr.contains("romantic")) return Mood.ROMANTIC;
    if (moodStr.contains("devotional")) return Mood.DEVOTIONAL;
    if (moodStr.contains("dance")) return Mood.DANCE;
    if (moodStr.contains("sad")) return Mood.SAD;
    if (moodStr.contains("relaxing")) return Mood.RELAXING;
    if (moodStr.contains("energetic")) return Mood.ENERGETIC;
    if (moodStr.contains("happy")) return Mood.HAPPY;

    return Mood.HAPPY; // fallback
}





}


