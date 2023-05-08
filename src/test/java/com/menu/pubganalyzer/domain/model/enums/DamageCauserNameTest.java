package com.menu.pubganalyzer.domain.model.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DamageCauserNameTest {

    @Test
    void of() {
        String label1 = "BP_Dirtbike_C";
        String label2 = "Weapvz61Skorpion_C";
        String label3 = "AIPawn_Base_Female_C";
        DamageCauserName damageCauserName1 = DamageCauserName.of(label1);
        DamageCauserName damageCauserName2 = DamageCauserName.of(label2);
        DamageCauserName damageCauserName3 = DamageCauserName.of(label3);
        assertEquals(DamageCauserName.BP_DIRTBIKE_C, damageCauserName1);
        assertEquals(DamageCauserName.WEAPVZ61SKORPION_C, damageCauserName2);
        assertEquals(DamageCauserName.AIPAWN_BASE_FEMALE_C, damageCauserName3);
    }

    @Test
    void of_not_found() {
        String label = "wrong_label";

        assertEquals(DamageCauserName.NOT_FOUND, DamageCauserName.of(label));
    }

    @Test
    void of_20000_times() {
        int enumCnt = DamageCauserName.values().length;
        String label;
        for (int i = 0; i < 20000; i++) {
            int index = (int) (Math.random() * enumCnt);
            DamageCauserName expect = DamageCauserName.values()[index];
            label = expect.getLabel();
            DamageCauserName damageCauserName = DamageCauserName.of(label);
            assertEquals(expect, damageCauserName);
        }
    }
}