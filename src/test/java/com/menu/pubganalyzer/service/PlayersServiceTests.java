package com.menu.pubganalyzer.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class PlayersServiceTests {
    private PlayersService playersService;
    public PlayersServiceTests() {
        this.playersService = new PlayersService();
    }
    @Test
    @DisplayName("get Account ID")
    public void getAccountId() {
        //given
        Map<String, String> mock = new HashMap<String, String>();
        mock.put("playerName", "WackyJacky101");
        mock.put("accountId", "account.c0e530e9b7244b358def282782f893af");
        String[] playerNames = {"WackyJacky101", "Patriot_Engineer"};
        String[] accountIds = {"account.c0e530e9b7244b358def282782f893af", "account.b5b9f342e8824021853f3d34308589d5"};
        //when
        String accountId = this.playersService.getAccountId(playerNames);
        System.out.println(accountId);
        //then
//        assertThat(accountId).isEqualTo(accountIds.toString());
    }

    @Test
    @DisplayName("get Match IDs")
    public void getMatches() {
        //given
//        String[] playerNames = {"Patriot_Engineer", "ss_barl"};
        String[] playerNames = {"Patriot_Engineer"};
        //when
        String matchesId = this.playersService.getMatches(playerNames);
        //then
        System.out.println(matchesId);
    }
}
