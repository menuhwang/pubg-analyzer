package com.menu.pubganalyzer.telemetries.dto.response.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DamageCauserName {
    NOT_FOUND("Not Found", "Not Found"),
    EMPTY("", ""),
    AIPAWN_BASE_FEMALE_C("AI Player", ""),
    AIPAWN_BASE_MALE_C("AI Player", ""),
    AIRBOAT_V2_C("Airboat", ""),
    AQUARAIL_A_01_C("Aquarail", "교통사고[Aquarail]"),
    AQUARAIL_A_02_C("Aquarail", "교통사고[Aquarail]"),
    AQUARAIL_A_03_C("Aquarail", "교통사고[Aquarail]"),
    BP_ATV_C("Quad", "교통사고[Quad]"),
    BP_BEARV2_C("Bear", "곰"),
    BP_BRDM_C("BRDM-2", "교통사고[BRDM]"),
    BP_BICYCLE_C("Mountain Bike", "교통사고[Mountain Bike]"),
    BP_BLANC_C("Blanc", "교통사고[Blanc]"),
    BP_COUPERB_C("Coupe RB", "교통사고[Coupe RB]"),
    BP_DO_CIRCLE_TRAIN_MERGED_C("Train", "교통사고[Train]"),
    BP_DO_LINE_TRAIN_DINO_MERGED_C("Train", "교통사고[Train]"),
    BP_DO_LINE_TRAIN_MERGED_C("Train", "교통사고[Train]"),
    BP_DIRTBIKE_C("Dirt Bike", "교통사고[Dirt Bike]"),
    BP_DRONEPACKAGE_PROJECTILE_C("Drone", "드론 낙하물"),
    BP_ERAGEL_CARGOSHIP01_C("Ferry Damage", "교통사고[Ferry]"),
    BP_FAKELOOTPROJ_AMMOBOX_C("Loot Truck", "교통사고[Loot Truck]"),
    BP_FAKELOOTPROJ_MILITARYCRATE_C("Loot Truck", "교통사고[Loot Truck]"),
    BP_FIREEFFECTCONTROLLER_C("Molotov Fire", "화염병"),
    BP_FIREEFFECTCONTROLLER_JERRYCAN_C("Jerrycan Fire", "연료통"),
    BP_FOOD_TRUCK_C("Food Truck", "교통사고[Food Truck]"),
    BP_HELICOPTER_C("Pillar Scout Helicopter", "교통사고[Pillar Scout Helicopter]"),
    BP_INCENDIARYDEBUFF_C("Burn", ""),
    BP_JERRYCANFIREDEBUFF_C("Burn", "연료통"),
    BP_JERRYCAN_FUELPUDDLE_C("Burn", "연료통"),
    BP_KILLTRUCK_C("Kill Truck", ""),
    BP_LOOTTRUCK_C("Loot Truck", ""),
    BP_M_RONY_A_01_C("Rony", "교통사고[Rony]"),
    BP_M_RONY_A_02_C("Rony", "교통사고[Rony]"),
    BP_M_RONY_A_03_C("Rony", "교통사고[Rony]"),
    BP_MIRADO_A_02_C("Mirado", "교통사고[Mirado]"),
    BP_MIRADO_A_03_ESPORTS_C("Mirado", "교통사고[Mirado]"),
    BP_MIRADO_OPEN_03_C("Mirado (open top)", "교통사고[Mirado]"),
    BP_MIRADO_OPEN_04_C("Mirado (open top)", "교통사고[Mirado]"),
    BP_MIRADO_OPEN_05_C("Mirado (open top)", "교통사고[Mirado]"),
    BP_MOLOTOVFIREDEBUFF_C("Molotov Fire Damage", "화염병"),
    BP_MOTORBIKE_04_C("Motorcycle", "교통사고[Motorcycle]"),
    BP_MOTORBIKE_04_DESERT_C("Motorcycle", "교통사고[Motorcycle]"),
    BP_MOTORBIKE_04_SIDECAR_C("Motorcycle (w/ Sidecar)", "교통사고[Motorcycle]"),
    BP_MOTORBIKE_04_SIDECAR_DESERT_C("Motorcycle (w/ Sidecar)", "교통사고[Motorcycle]"),
    BP_MOTORBIKE_SOLITARIO_C("Motorcycle", "교통사고[Motorcycle]"),
    BP_MOTORGLIDER_C("Motor Glider", "교통사고[Motor Glider]"),
    BP_MOTORGLIDER_GREEN_C("Motor Glider", "교통사고[Motor Glider]"),
    BP_NIVA_01_C("Zima", "교통사고[Zima]"),
    BP_NIVA_02_C("Zima", "교통사고[Zima]"),
    BP_NIVA_03_C("Zima", "교통사고[Zima]"),
    BP_NIVA_04_C("Zima", "교통사고[Zima]"),
    BP_NIVA_05_C("Zima", "교통사고[Zima]"),
    BP_NIVA_06_C("Zima", "교통사고[Zima]"),
    BP_NIVA_07_C("Zima", "교통사고[Zima]"),
    BP_NIVA_ESPORTS_C("Zima", "교통사고[Zima]"),
    BP_PICKUPTRUCK_A_01_C("Pickup Truck (closed top)", "교통사고[Pickup Truck]"),
    BP_PICKUPTRUCK_A_02_C("Pickup Truck (closed top)", "교통사고[Pickup Truck]"),
    BP_PICKUPTRUCK_A_03_C("Pickup Truck (closed top)", "교통사고[Pickup Truck]"),
    BP_PICKUPTRUCK_A_04_C("Pickup Truck (closed top)", "교통사고[Pickup Truck]"),
    BP_PICKUPTRUCK_A_05_C("Pickup Truck (closed top)", "교통사고[Pickup Truck]"),
    BP_PICKUPTRUCK_A_ESPORTS_C("Pickup Truck (closed top)", "교통사고[Pickup Truck]"),
    BP_PICKUPTRUCK_B_01_C("Pickup Truck (open top)", "교통사고[Pickup Truck]"),
    BP_PICKUPTRUCK_B_02_C("Pickup Truck (open top)", "교통사고[Pickup Truck]"),
    BP_PICKUPTRUCK_B_03_C("Pickup Truck (open top)", "교통사고[Pickup Truck]"),
    BP_PICKUPTRUCK_B_04_C("Pickup Truck (open top)", "교통사고[Pickup Truck]"),
    BP_PICKUPTRUCK_B_05_C("Pickup Truck (open top)", "교통사고[Pickup Truck]"),
    BP_PICOBUS_C("Pico bus", "교통사고[피코 버스]"),
    BP_PILLAR_CAR_C("Pillar Security Car", "교통사고[Pillar Security Car]"),
    BP_PONYCOUPE_C("Pony Coupe", "교통사고[Pony Coupe]"),
    BP_PORTER_C("Porter", "교통사고[Porter]"),
    BP_SCOOTER_01_A_C("Scooter", "교통사고[Scooter]"),
    BP_SCOOTER_02_A_C("Scooter", "교통사고[Scooter]"),
    BP_SCOOTER_03_A_C("Scooter", "교통사고[Scooter]"),
    BP_SCOOTER_04_A_C("Scooter", "교통사고[Scooter]"),
    BP_SNOWBIKE_01_C("Snowbike", "교통사고[Snowbike]"),
    BP_SNOWBIKE_02_C("Snowbike", "교통사고[Snowbike]"),
    BP_SNOWMOBILE_01_C("Snowmobile", "교통사고[Snowmobile]"),
    BP_SNOWMOBILE_02_C("Snowmobile", "교통사고[Snowmobile]"),
    BP_SNOWMOBILE_03_C("Snowmobile", "교통사고[Snowmobile]"),
    BP_SPIKETRAP_C("Spike Trap", "스파이크 트랩"),
    BP_STRUCTDROP_C("Struct Drop", "긴급 엄폐 투하물"),
    BP_TSLGASPUMP_C("Gas Pump", "주유소"),
    BP_TUKTUKTUK_A_01_C("Tukshai", "교통사고[툭툭이]"),
    BP_TUKTUKTUK_A_02_C("Tukshai", "교통사고[툭툭이]"),
    BP_TUKTUKTUK_A_03_C("Tukshai", "교통사고[툭툭이]"),
    BP_VAN_A_01_C("Van", "교통사고[Van]"),
    BP_VAN_A_02_C("Van", "교통사고[Van]"),
    BP_VAN_A_03_C("Van", "교통사고[Van]"),
    BATTLEROYALEMODECONTROLLER_CHIMERA_C("Bluezone", "블루존"),
    BATTLEROYALEMODECONTROLLER_DEF_C("Bluezone", "블루존"),
    BATTLEROYALEMODECONTROLLER_DESERT_C("Bluezone", "블루존"),
    BATTLEROYALEMODECONTROLLER_DIHOROTOK_C("Bluezone", "블루존"),
    BATTLEROYALEMODECONTROLLER_HEAVEN_C("Bluezone", "블루존"),
    BATTLEROYALEMODECONTROLLER_KIKI_C("Bluezone", "블루존"),
    BATTLEROYALEMODECONTROLLER_SAVAGE_C("Bluezone", "블루존"),
    BATTLEROYALEMODECONTROLLER_SUMMERLAND_C("Bluezone", "블루존"),
    BATTLEROYALEMODECONTROLLER_TIGER_C("Bluezone", "블루존"),
    BLACKZONECONTROLLER_DEF_C("Blackzone", "블랙존"),
    BLIZZARDBUFF_BP_C("Blizzard", "블리자드"),
    BLUEZONEBOMB_EFFECTACTOR_C("Bluezone Grenade", "블루존 수류탄"),
    BOAT_PG117_C("PG-117", "교통사고[Boat]"),
    BUFF_DECREASEBREATHINAPNEA_C("Drowning", "산소부족"),
    BUGGY_A_01_C("Buggy", "교통사고[Buggy]"),
    BUGGY_A_02_C("Buggy", "교통사고[Buggy]"),
    BUGGY_A_03_C("Buggy", "교통사고[Buggy]"),
    BUGGY_A_04_C("Buggy", "교통사고[Buggy]"),
    BUGGY_A_05_C("Buggy", "교통사고[Buggy]"),
    BUGGY_A_06_C("Buggy", "교통사고[Buggy]"),
    CAREPACKAGE_CONTAINER_C("Care Package", "보급"),
    DACIA_A_01_V2_C("Dacia", "교통사고[Dacia]"),
    DACIA_A_01_V2_SNOW_C("Dacia", "교통사고[Dacia]"),
    DACIA_A_02_V2_C("Dacia", "교통사고[Dacia]"),
    DACIA_A_03_V2_C("Dacia", "교통사고[Dacia]"),
    DACIA_A_03_V2_ESPORTS_C("Dacia", "교통사고[Dacia]"),
    DACIA_A_04_V2_C("Dacia", "교통사고[Dacia]"),
    DROPPEDITEMGROUP("Object Fragments", ""),
    EMERGENCYAIRCRAFT_TIGER_C("Emergency Aircraft", ""),
    JERRYCAN("Jerrycan", "연료통"),
    JERRYCANFIRE("Jerrycan Fire", "연료통"),
    LAVA("Lava", "용암"),
    MORTAR_PROJECTILE_C("Mortar Projectile", "박격포"),
    NONE("None", ""),
    PG117_A_01_C("PG-117", "교통사고[Boat]"),
    PANZERFAUST100M_PROJECTILE_C("Panzerfaust Projectile", "판처"),
    PLAYERFEMALE_A_C("Player", "주먹"),
    PLAYERMALE_A_C("Player", "주먹"),
    PROJC4_C("C4", "C4"),
    PROJGRENADE_C("Frag Grenade", "수류탄"),
    PROJINCENDIARY_C("Incendiary Projectile", ""), // 헤이븐 트럭?
    PROJMOLOTOV_C("Molotov Cocktail", "화염병"),
    PROJMOLOTOV_DAMAGEFIELD_DIRECT_C("Molotov Cocktail Fire Field", "화염병"),
    PROJSTICKYGRENADE_C("Sticky Bomb", "점착 폭탄"),
    RACINGDESTRUCTIBLEPROPANETANKACTOR_C("Propane Tank", ""),
    RACINGMODECONTORLLER_DESERT_C("Bluezone", "블루존"),
    REDZONEBOMB_C("Redzone", "레드존"),
    REDZONEBOMBINGFIELD("Redzone", "레드존"),
    REDZONEBOMBINGFIELD_DEF_C("Redzone", "레드존"),
    TSLDESTRUCTIBLESURFACEMANAGER("Destructible Surface", ""),
    TSLPAINCAUSINGVOLUME("Lava", "용암"),
    UAZ_A_01_C("UAZ (open top)", "교통사고[UAZ]"),
    UAZ_ARMORED_C("UAZ (armored)", "교통사고[UAZ]"),
    UAZ_B_01_C("UAZ (soft top)", "교통사고[UAZ]"),
    UAZ_B_01_ESPORTS_C("UAZ (soft top)", "교통사고[UAZ]"),
    UAZ_C_01_C("UAZ (hard top)", "교통사고[UAZ]"),
    UAZ_PILLAR_C("UAZ (Pillar)", "교통사고[필라 UAZ]"),
    ULTAIPAWN_BASE_FEMALE_C("Player", "주먹"),
    ULTAIPAWN_BASE_MALE_C("Player", "주먹"),
    WEAPACE32_C("ACE32", "ACE32"),
    WEAPAK47_C("AKM", "AKM"),
    WEAPAUG_C("AUG A3", "AUG"),
    WEAPAWM_C("AWM", "AWM"),
    WEAPBERRETA686_C("S686", "S686"),
    WEAPBERYLM762_C("Beryl", "Beryl"),
    WEAPBIZONPP19_C("Bizon", "Bizon"),
    WEAPCOWBARPROJECTILE_C("Crowbar Projectile", "석궁"),
    WEAPCOWBAR_C("Crowbar", "석궁"),
    WEAPCROSSBOW_1_C("Crossbow", "석궁"),
    WEAPDP12_C("DBS", "DBS"),
    WEAPDP28_C("DP-28", "DP-28"),
    WEAPDESERTEAGLE_C("Deagle", "Deagle"),
    WEAPDUNCANSHK416_C("M416", "M416"),
    WEAPFAMASG2_C("Famas", "Famas"),
    WEAPFNFAL_C("SLR", "SLR"),
    WEAPG18_C("P18C", "P18C"),
    WEAPG36C_C("G36C", "G36C"),
    WEAPGROZA_C("Groza", "Groza"),
    WEAPHK416_C("M416", "M416"),
    WEAPJULIESKAR98K_C("Kar98k", "Kar98k"),
    WEAPK2_C("K2", "K2"),
    WEAPKAR98K_C("Kar98k", "Kar98k"),
    WEAPL6_C("Lynx AMR", "링스"),
    WEAPLUNCHMEATSAK47_C("AKM", "AKM"),
    WEAPM16A4_C("M16A4", "M16A4"),
    WEAPM1911_C("P1911", "P1911"),
    WEAPM249_C("M249", "M249"),
    WEAPM24_C("M24", "M24"),
    WEAPM9_C("P92", "P92"),
    WEAPMG3_C("MG3", "MG3"),
    WEAPMP5K_C("MP5K", "MP5K"),
    WEAPMP9_C("MP9", "MP9"),
    WEAPMACHETEPROJECTILE_C("Machete Projectile", "마체테"),
    WEAPMACHETE_C("Machete", "마체테"),
    WEAPMADSQBU88_C("QBU88", "QBU88"),
    WEAPMINI14_C("Mini 14", "Mini14"),
    WEAPMK12_C("Mk12", "Mk12"),
    WEAPMK14_C("Mk14 EBR", "Mk14"),
    WEAPMK47MUTANT_C("Mk47 Mutant", "Mutant"),
    WEAPMOSINNAGANT_C("Mosin-Nagant", "모신나강"),
    WEAPNAGANTM1895_C("R1895", "R1895"),
    WEAPORIGINS12_C("O12", "O12"),
    WEAPP90_C("P90", "P90"),
    WEAPPANPROJECTILE_C("Pan Projectile", "프라이팬"),
    WEAPPAN_C("Pan", "프라이팬"),
    WEAPPANZERFAUST100M1_C("Panzerfaust", "판처"),
    WEAPPICKAXE_C("Pick axe", "곡괭이"),
    WEAPQBU88_C("QBU88", "QBU88"),
    WEAPQBZ95_C("QBZ95", "QBZ95"),
    WEAPRHINO_C("R45", "R45"),
    WEAPSCAR_L_C("SCAR-L", "SCAR-L"),
    WEAPSKS_C("SKS", "SKS"),
    WEAPSAIGA12_C("S12K", "S12K"),
    WEAPSAWNOFF_C("Sawed-off", "소드오프"),
    WEAPSICKLEPROJECTILE_C("Sickle Projectile", "낫"),
    WEAPSICKLE_C("Sickle", "낫"),
    WEAPTHOMPSON_C("Tommy Gun", "Tommy Gun"),
    WEAPTURRET_KILLTRUCK_MAIN_C("Kill Truck Turret", "터렛"),
    WEAPUMP_C("UMP9", "UMP9"),
    WEAPUZI_C("Micro Uzi", "Uzi"),
    WEAPVSS_C("VSS", "VSS"),
    WEAPVECTOR_C("Vector", "Vector"),
    WEAPWIN94_C("Win94", "Win94"),
    WEAPWINCHESTER_C("S1897", "S1897"),
    WEAPVZ61SKORPION_C("Skorpion", "Skorpion"),
    WEAPDRAGUNOV_C("Dragunov", "드라구노프"),
    WEAPJS9_C("JS9", "JS9"),
    ;

    private final String eng;
    private final String kor;

    DamageCauserName(String eng, String kor) {
        this.eng = eng;
        this.kor = kor;
    }

    public static DamageCauserName of(String name) {
        if (name.isBlank()) return null;
        name = name.toUpperCase().replaceAll("-", "_");
        for (DamageCauserName damageCauserName : values()) {
            if (damageCauserName.name().equals(name)) return damageCauserName;
        }
        log.error("Enum DamageCauserName not found [{}]", name);
        return NOT_FOUND;
    }
}
