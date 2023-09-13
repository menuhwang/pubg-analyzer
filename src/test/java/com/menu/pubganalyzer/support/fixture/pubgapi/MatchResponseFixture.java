package com.menu.pubganalyzer.support.fixture.pubgapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.menu.pubganalyzer.util.pubgAPI.response.MatchResponse;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

public class MatchResponseFixture {
    public static final String MATCH_ID = "f4b8690c-d165-4a0e-8292-db2bd894006e";
    public static final String MATCH_SHARD;
    public static final String MATCH_ASSET_URL;
    public static final MatchResponse MATCH_RESPONSE;

    static {
        try {
            ClassPathResource classpath = new ClassPathResource("matches" + File.separator + "response");
            File responseDir = classpath.getFile();

            MATCH_RESPONSE = read(new File(responseDir, MATCH_ID + ".json"));
            MATCH_SHARD = MATCH_RESPONSE.getAttributes().getShardId();
            MATCH_ASSET_URL = MATCH_RESPONSE.getIncluded().stream()
                    .filter(element -> element.getType().equals("asset"))
                    .findFirst().orElseThrow().getAttributes().getURL();

        } catch (IOException e) {
            System.err.println(e);
            throw new RuntimeException();
        }
    }

    private static MatchResponse read(File file) {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        MatchResponse result = null;
        try {
            result = objectMapper.readValue(file, MatchResponse.class);
        } catch (IOException e) {
            throw new RuntimeException();
        }

        return result;
    }
}
