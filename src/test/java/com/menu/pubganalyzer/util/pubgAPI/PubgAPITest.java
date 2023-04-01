package com.menu.pubganalyzer.util.pubgAPI;

import com.menu.pubganalyzer.model.Match;
import com.menu.pubganalyzer.model.Participant;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;

class PubgAPITest {
    private final PubgAPI pubgAPI = new DefaultPubgAPI();

    @Test
    void match() {
        Match m = pubgAPI.match("8a805d51-1333-4efa-a563-22940b4c67de");
        Set<Participant> participants = m.getParticipants();
        participants.forEach(System.out::println);
    }

    @Test
    void matchParallel() {
        List<String> matches = List.of("59283a04-7f08-4195-9567-fde208229f31",
                            "8a805d51-1333-4efa-a563-22940b4c67de",
                            "411c147d-b0c7-424d-95e4-c3a147a9d406",
                            "e71cbdac-38ad-48d1-b55d-8802d9528eaa",
                            "4634bdaf-0f80-4b44-af22-b8fe7b73e82a",
                            "d8de83b9-3469-4b6c-977d-b8479b8fd363",
                            "458565b4-222d-432b-aaae-5692d985effc",
                            "288d4531-8c25-4014-8e75-001ff2e8b5dd",
                            "f83878ff-1051-427f-853a-8198e25e3547",
                            "1a6f1403-7bef-4a31-8bd2-fc3dc9789771",
                            "46f41653-1ec0-4dc5-9e5e-8d3fe5ffe095",
                            "811a11ad-7956-404b-a6cb-f7b1f172d17a",
                            "1af3d857-5ab3-4395-b889-a577481cda55",
                            "1e6ae3c4-8f39-45ff-bb19-b509743e6b87",
                            "3553ff0f-0b6e-4e7e-8e87-ccc463bbd75c",
                            "47d72d3e-11b6-4889-8c39-b908f8964a78",
                            "642a189f-85af-4c2a-b02d-c271f2714dff",
                            "0bd60861-fcd9-4818-98a2-5e57a70d78d4",
                            "fa71a4fb-cb53-4286-8cfa-11ec4ca8f2c0",
                            "ed780bdc-010b-4add-b2e8-e80159304f1f",
                            "2dd23197-ab7c-4fc2-8808-391641d27dc2");

        System.out.println("get many matches start...");
        long start = System.currentTimeMillis();
        matches.stream()
                .parallel()
                .forEach(pubgAPI::match);
        System.out.println((System.currentTimeMillis() - start) + "ms");
    }
}