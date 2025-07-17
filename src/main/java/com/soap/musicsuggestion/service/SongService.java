package com.soap.musicsuggestion.service;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.lajpat.guides.music.GetSongRequest;
import com.lajpat.guides.music.GetSongResponse;
import com.soap.musicsuggestion.repo.BollywoodSongRepository;

@Endpoint
public class SongService {

    private static final String NAMESPACE_URI = "http://lajpat.com/guides/music";

    private final BollywoodSongRepository bollywoodSongRepository;

    public SongService(BollywoodSongRepository bollywoodSongRepository) {
        this.bollywoodSongRepository = bollywoodSongRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSongRequest")
    @ResponsePayload
    public GetSongResponse getSongs(@RequestPayload GetSongRequest request) {
        GetSongResponse response = new GetSongResponse();
        response.getSong().addAll(bollywoodSongRepository.findTop10ByMood(request.getMood()));
        return response;
    }
}