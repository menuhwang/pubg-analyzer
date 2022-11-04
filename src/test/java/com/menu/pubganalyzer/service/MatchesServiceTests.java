package com.menu.pubganalyzer.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MatchesServiceTests {
    @Autowired
    private MatchesService matchesService;
    private String matchId = "cf6671da-0e97-43d1-8da5-40a01eaa2606";
    private String[] matchIds = {
            "4fe52750-6894-4da4-8a95-75f09adf1603",
            "10235ff1-b42a-488d-8fdc-fed537a50a48",
            "800280c7-55a8-4d72-9778-5fefda7d4b65",
            "eaa616f2-f6d9-48ae-958c-64e77443aa86",
            "96507b4c-4827-44fb-a52d-e69e4e6f59a2",
            "58a15460-ba57-49a4-9f86-ea6fc648fe89",
            "e4b965ef-347b-46b8-a9a5-32e806e2a23a",
            "49a2bd0a-2a69-4c25-a327-984605669683",
            "cf6671da-0e97-43d1-8da5-40a01eaa2606",
            "95c33f91-e840-4724-84d0-3aa7acb242bc",
            "9af69681-3f4f-4c7c-9085-436093ab7902",
            "61930901-681f-476f-9339-a93036149491",
            "250300cb-5090-4a5f-94d6-92ca1af1659f",
            "be93b787-ebaf-4ed5-afb1-91a27a545b05",
            "b906a6b7-9d49-48a2-8c55-84328c879757"
    };

    @Test
    public void getInformation() {
        System.out.println(this.matchesService.getMatch(this.matchId).getInformation());
    }
    @Test
    public void getInformationMany() {
        // 다운로드가 필요한 매치 15번 읽기 [7초 186ms]
        // 이미 다운로드한 매치 15번 읽기 [458ms]
        for ( int a = 0; a < 2; a++) {
            for (int i = 0; i < matchIds.length; i++) {
                System.out.println(this.matchesService.getMatch(this.matchIds[i]).getInformation());
            }
        }
    }
}
