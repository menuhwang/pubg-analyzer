package com.menu.pubganalyzer.lib.Telemetry;

import com.menu.pubganalyzer.utile.FileHandler;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LogPlayerTakeDamageListTests {
    private String matchId = "7d205f42-3a04-4687-b894-60a9725361d8";
    private String path = "D:\\java\\wdtmk\\src\\main\\java\\com\\menu\\wdtmk\\data\\telemetry" + "\\" + matchId;
    private LogPlayerTakeDamageList logPlayerTakeDamageList;

    public LogPlayerTakeDamageListTests() {
        try {
            String raw = new FileHandler(path).readToString("telemetry.json");
            this.logPlayerTakeDamageList = new TelemetryParser(raw).getLogPlayerTakeDamageList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void get() {
        String attacker = "LSWW1";
        String victim = "incheonnoir";
        LogPlayerTakeDamageList getData = this.logPlayerTakeDamageList.get(attacker, victim);
        System.out.println("log : " + getData.size() + " Line(s)");
        for (int i = 0; i < getData.size(); i++) {
            LogPlayerTakeDamage log = getData.get(i);
            System.out.println(log);
        }
        System.out.println("Total Damage : " + getData.getTotalDamage());
    }
    @Test
    public void getByVictim() {
        String victim = "Kimsan910";
        LogPlayerTakeDamageList getData = this.logPlayerTakeDamageList.getByVictim(victim);
        System.out.println("log : " + getData.size() + " Line(s)");
        for (int i = 0; i < getData.size(); i++) {
            LogPlayerTakeDamage log = getData.get(i);
            System.out.println(log);
        }
    }
    @Test
    public void getByAttacker() {
//        String attacker = "Patriot_Engineer";
        String attacker = "LSWW1";
        LogPlayerTakeDamageList getData = this.logPlayerTakeDamageList.getByAttacker(attacker);
        System.out.println("log : " + getData.size() + " Line(s)");
        for (int i = 0; i < getData.size(); i++) {
            LogPlayerTakeDamage log = getData.get(i);
            System.out.println(log);
        }
    }
}
