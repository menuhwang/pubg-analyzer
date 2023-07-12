package com.menu.pubganalyzer.domain.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DamageCauserName {
    NOT_FOUND("Not_Found", "Not Found", ""),
    EMPTY("", "", ""),
    AIPAWN_BASE_FEMALE_C("AIPawn_Base_Female_C", "AI Player", ""),
    AIPAWN_BASE_MALE_C("AIPawn_Base_Male_C", "AI Player", ""),
    AIRBOAT_V2_C("AirBoat_V2_C", "Airboat", ""),
    AQUARAIL_A_01_C("AquaRail_A_01_C", "Aquarail", "교통사고[Aquarail]"),
    AQUARAIL_A_02_C("AquaRail_A_02_C", "Aquarail", "교통사고[Aquarail]"),
    AQUARAIL_A_03_C("AquaRail_A_03_C", "Aquarail", "교통사고[Aquarail]"),
    BP_ATV_C("BP_ATV_C", "Quad", "교통사고[Quad]"),
    BP_BEARV2_C("BP_BearV2_C", "Bear", "곰"),
    BP_BRDM_C("BP_BRDM_C", "BRDM-2", "교통사고[BRDM]"),
    BP_BICYCLE_C("BP_Bicycle_C", "Mountain Bike", "교통사고[Mountain Bike]"),
    BP_COUPERB_C("BP_CoupeRB_C", "Coupe RB", "교통사고[Coupe RB]"),
    BP_DO_CIRCLE_TRAIN_MERGED_C("BP_DO_Circle_Train_Merged_C", "Train", "교통사고[Train]"),
    BP_DO_LINE_TRAIN_DINO_MERGED_C("BP_DO_Line_Train_Dino_Merged_C", "Train", "교통사고[Train]"),
    BP_DO_LINE_TRAIN_MERGED_C("BP_DO_Line_Train_Merged_C", "Train", "교통사고[Train]"),
    BP_DIRTBIKE_C("BP_Dirtbike_C", "Dirt Bike", "교통사고[Dirt Bike]"),
    BP_DRONEPACKAGE_PROJECTILE_C("BP_DronePackage_Projectile_C", "Drone", "드론 낙하물"),
    BP_ERAGEL_CARGOSHIP01_C("BP_Eragel_CargoShip01_C", "Ferry Damage", "교통사고[Ferry]"),
    BP_FAKELOOTPROJ_AMMOBOX_C("BP_FakeLootProj_AmmoBox_C", "Loot Truck", "교통사고[Loot Truck]"),
    BP_FAKELOOTPROJ_MILITARYCRATE_C("BP_FakeLootProj_MilitaryCrate_C", "Loot Truck", "교통사고[Loot Truck]"),
    BP_FIREEFFECTCONTROLLER_C("BP_FireEffectController_C", "Molotov Fire", "화염병"),
    BP_FIREEFFECTCONTROLLER_JERRYCAN_C("BP_FireEffectController_JerryCan_C", "Jerrycan Fire", "연료통"),
    BP_FOOD_TRUCK_C("BP_Food_Truck_C", "Food Truck", "교통사고[Food Truck]"),
    BP_HELICOPTER_C("BP_Helicopter_C", "Pillar Scout Helicopter", "교통사고[Pillar Scout Helicopter]"),
    BP_INCENDIARYDEBUFF_C("BP_IncendiaryDebuff_C", "Burn", ""),
    BP_JERRYCANFIREDEBUFF_C("BP_JerryCanFireDebuff_C", "Burn", "연료통"),
    BP_JERRYCAN_FUELPUDDLE_C("BP_JerryCan_FuelPuddle_C", "Burn", "연료통"),
    BP_KILLTRUCK_C("BP_KillTruck_C", "Kill Truck", ""),
    BP_LOOTTRUCK_C("BP_LootTruck_C", "Loot Truck", ""),
    BP_M_RONY_A_01_C("BP_M_Rony_A_01_C", "Rony", "교통사고[Rony]"),
    BP_M_RONY_A_02_C("BP_M_Rony_A_02_C", "Rony", "교통사고[Rony]"),
    BP_M_RONY_A_03_C("BP_M_Rony_A_03_C", "Rony", "교통사고[Rony]"),
    BP_MIRADO_A_02_C("BP_Mirado_A_02_C", "Mirado", "교통사고[Mirado]"),
    BP_MIRADO_A_03_ESPORTS_C("BP_Mirado_A_03_Esports_C", "Mirado", "교통사고[Mirado]"),
    BP_MIRADO_OPEN_03_C("BP_Mirado_Open_03_C", "Mirado (open top)", "교통사고[Mirado]"),
    BP_MIRADO_OPEN_04_C("BP_Mirado_Open_04_C", "Mirado (open top)", "교통사고[Mirado]"),
    BP_MIRADO_OPEN_05_C("BP_Mirado_Open_05_C", "Mirado (open top)", "교통사고[Mirado]"),
    BP_MOLOTOVFIREDEBUFF_C("BP_MolotovFireDebuff_C", "Molotov Fire Damage", "화염병"),
    BP_MOTORBIKE_04_C("BP_Motorbike_04_C", "Motorcycle", "교통사고[Motorcycle]"),
    BP_MOTORBIKE_04_DESERT_C("BP_Motorbike_04_Desert_C", "Motorcycle", "교통사고[Motorcycle]"),
    BP_MOTORBIKE_04_SIDECAR_C("BP_Motorbike_04_SideCar_C", "Motorcycle (w/ Sidecar)", "교통사고[Motorcycle]"),
    BP_MOTORBIKE_04_SIDECAR_DESERT_C("BP_Motorbike_04_SideCar_Desert_C", "Motorcycle (w/ Sidecar)", "교통사고[Motorcycle]"),
    BP_MOTORBIKE_SOLITARIO_C("BP_Motorbike_Solitario_C", "Motorcycle", "교통사고[Motorcycle]"),
    BP_MOTORGLIDER_C("BP_Motorglider_C", "Motor Glider", "교통사고[Motor Glider]"),
    BP_MOTORGLIDER_GREEN_C("BP_Motorglider_Green_C", "Motor Glider", "교통사고[Motor Glider]"),
    BP_NIVA_01_C("BP_Niva_01_C", "Zima", "교통사고[Zima]"),
    BP_NIVA_02_C("BP_Niva_02_C", "Zima", "교통사고[Zima]"),
    BP_NIVA_03_C("BP_Niva_03_C", "Zima", "교통사고[Zima]"),
    BP_NIVA_04_C("BP_Niva_04_C", "Zima", "교통사고[Zima]"),
    BP_NIVA_05_C("BP_Niva_05_C", "Zima", "교통사고[Zima]"),
    BP_NIVA_06_C("BP_Niva_06_C", "Zima", "교통사고[Zima]"),
    BP_NIVA_07_C("BP_Niva_07_C", "Zima", "교통사고[Zima]"),
    BP_NIVA_ESPORTS_C("BP_Niva_Esports_C", "Zima", "교통사고[Zima]"),
    BP_PICKUPTRUCK_A_01_C("BP_PickupTruck_A_01_C", "Pickup Truck (closed top)", "교통사고[Pickup Truck]"),
    BP_PICKUPTRUCK_A_02_C("BP_PickupTruck_A_02_C", "Pickup Truck (closed top)", "교통사고[Pickup Truck]"),
    BP_PICKUPTRUCK_A_03_C("BP_PickupTruck_A_03_C", "Pickup Truck (closed top)", "교통사고[Pickup Truck]"),
    BP_PICKUPTRUCK_A_04_C("BP_PickupTruck_A_04_C", "Pickup Truck (closed top)", "교통사고[Pickup Truck]"),
    BP_PICKUPTRUCK_A_05_C("BP_PickupTruck_A_05_C", "Pickup Truck (closed top)", "교통사고[Pickup Truck]"),
    BP_PICKUPTRUCK_A_ESPORTS_C("BP_PickupTruck_A_esports_C", "Pickup Truck (closed top)", "교통사고[Pickup Truck]"),
    BP_PICKUPTRUCK_B_01_C("BP_PickupTruck_B_01_C", "Pickup Truck (open top)", "교통사고[Pickup Truck]"),
    BP_PICKUPTRUCK_B_02_C("BP_PickupTruck_B_02_C", "Pickup Truck (open top)", "교통사고[Pickup Truck]"),
    BP_PICKUPTRUCK_B_03_C("BP_PickupTruck_B_03_C", "Pickup Truck (open top)", "교통사고[Pickup Truck]"),
    BP_PICKUPTRUCK_B_04_C("BP_PickupTruck_B_04_C", "Pickup Truck (open top)", "교통사고[Pickup Truck]"),
    BP_PICKUPTRUCK_B_05_C("BP_PickupTruck_B_05_C", "Pickup Truck (open top)", "교통사고[Pickup Truck]"),
    BP_PILLAR_CAR_C("BP_Pillar_Car_C", "Pillar Security Car", "교통사고[Pillar Security Car]"),
    BP_PONYCOUPE_C("BP_PonyCoupe_C", "Pony Coupe", "교통사고[Pony Coupe]"),
    BP_PORTER_C("BP_Porter_C", "Porter", "교통사고[Porter]"),
    BP_SCOOTER_01_A_C("BP_Scooter_01_A_C", "Scooter", "교통사고[Scooter]"),
    BP_SCOOTER_02_A_C("BP_Scooter_02_A_C", "Scooter", "교통사고[Scooter]"),
    BP_SCOOTER_03_A_C("BP_Scooter_03_A_C", "Scooter", "교통사고[Scooter]"),
    BP_SCOOTER_04_A_C("BP_Scooter_04_A_C", "Scooter", "교통사고[Scooter]"),
    BP_SNOWBIKE_01_C("BP_Snowbike_01_C", "Snowbike", "교통사고[Snowbike]"),
    BP_SNOWBIKE_02_C("BP_Snowbike_02_C", "Snowbike", "교통사고[Snowbike]"),
    BP_SNOWMOBILE_01_C("BP_Snowmobile_01_C", "Snowmobile", "교통사고[Snowmobile]"),
    BP_SNOWMOBILE_02_C("BP_Snowmobile_02_C", "Snowmobile", "교통사고[Snowmobile]"),
    BP_SNOWMOBILE_03_C("BP_Snowmobile_03_C", "Snowmobile", "교통사고[Snowmobile]"),
    BP_SPIKETRAP_C("BP_Spiketrap_C", "Spike Trap", "스파이크 트랩"),
    BP_TSLGASPUMP_C("BP_TslGasPump_C", "Gas Pump", "주유소"),
    BP_TUKTUKTUK_A_01_C("BP_TukTukTuk_A_01_C", "Tukshai", "교통사고[툭툭이]"),
    BP_TUKTUKTUK_A_02_C("BP_TukTukTuk_A_02_C", "Tukshai", "교통사고[툭툭이]"),
    BP_TUKTUKTUK_A_03_C("BP_TukTukTuk_A_03_C", "Tukshai", "교통사고[툭툭이]"),
    BP_VAN_A_01_C("BP_Van_A_01_C", "Van", "교통사고[Van]"),
    BP_VAN_A_02_C("BP_Van_A_02_C", "Van", "교통사고[Van]"),
    BP_VAN_A_03_C("BP_Van_A_03_C", "Van", "교통사고[Van]"),
    BATTLEROYALEMODECONTROLLER_CHIMERA_C("BattleRoyaleModeController_Chimera_C", "Bluezone", "블루존"),
    BATTLEROYALEMODECONTROLLER_DEF_C("BattleRoyaleModeController_Def_C", "Bluezone", "블루존"),
    BATTLEROYALEMODECONTROLLER_DESERT_C("BattleRoyaleModeController_Desert_C", "Bluezone", "블루존"),
    BATTLEROYALEMODECONTROLLER_DIHOROTOK_C("BattleRoyaleModeController_DihorOtok_C", "Bluezone", "블루존"),
    BATTLEROYALEMODECONTROLLER_HEAVEN_C("BattleRoyaleModeController_Heaven_C", "Bluezone", "블루존"),
    BATTLEROYALEMODECONTROLLER_KIKI_C("BattleRoyaleModeController_Kiki_C", "Bluezone", "블루존"),
    BATTLEROYALEMODECONTROLLER_SAVAGE_C("BattleRoyaleModeController_Savage_C", "Bluezone", "블루존"),
    BATTLEROYALEMODECONTROLLER_SUMMERLAND_C("BattleRoyaleModeController_Summerland_C", "Bluezone", "블루존"),
    BATTLEROYALEMODECONTROLLER_TIGER_C("BattleRoyaleModeController_Tiger_C", "Bluezone", "블루존"),
    BLACKZONECONTROLLER_DEF_C("BlackZoneController_Def_C", "Blackzone", "블랙존"),
    BLIZZARDBUFF_BP_C("BlizzardBuff_BP_C", "Blizzard", "블리자드"),
    BLUEZONEBOMB_EFFECTACTOR_C("Bluezonebomb_EffectActor_C", "Bluezone Grenade", "블루존 수류탄"),
    BOAT_PG117_C("Boat_PG117_C", "PG-117", "교통사고[Boat]"),
    BUFF_DECREASEBREATHINAPNEA_C("Buff_DecreaseBreathInApnea_C", "Drowning", "산소부족"),
    BUGGY_A_01_C("Buggy_A_01_C", "Buggy", "교통사고[Buggy]"),
    BUGGY_A_02_C("Buggy_A_02_C", "Buggy", "교통사고[Buggy]"),
    BUGGY_A_03_C("Buggy_A_03_C", "Buggy", "교통사고[Buggy]"),
    BUGGY_A_04_C("Buggy_A_04_C", "Buggy", "교통사고[Buggy]"),
    BUGGY_A_05_C("Buggy_A_05_C", "Buggy", "교통사고[Buggy]"),
    BUGGY_A_06_C("Buggy_A_06_C", "Buggy", "교통사고[Buggy]"),
    CAREPACKAGE_CONTAINER_C("Carepackage_Container_C", "Care Package", "보급"),
    DACIA_A_01_V2_C("Dacia_A_01_v2_C", "Dacia", "교통사고[Dacia]"),
    DACIA_A_01_V2_SNOW_C("Dacia_A_01_v2_snow_C", "Dacia", "교통사고[Dacia]"),
    DACIA_A_02_V2_C("Dacia_A_02_v2_C", "Dacia", "교통사고[Dacia]"),
    DACIA_A_03_V2_C("Dacia_A_03_v2_C", "Dacia", "교통사고[Dacia]"),
    DACIA_A_03_V2_ESPORTS_C("Dacia_A_03_v2_Esports_C", "Dacia", "교통사고[Dacia]"),
    DACIA_A_04_V2_C("Dacia_A_04_v2_C", "Dacia", "교통사고[Dacia]"),
    DROPPEDITEMGROUP("DroppedItemGroup", "Object Fragments", ""),
    EMERGENCYAIRCRAFT_TIGER_C("EmergencyAircraft_Tiger_C", "Emergency Aircraft", ""),
    JERRYCAN("Jerrycan", "Jerrycan", "연료통"),
    JERRYCANFIRE("JerrycanFire", "Jerrycan Fire", "연료통"),
    LAVA("Lava", "Lava", "용암"),
    MORTAR_PROJECTILE_C("Mortar_Projectile_C", "Mortar Projectile", "박격포"),
    NONE("None", "None", ""),
    PG117_A_01_C("PG117_A_01_C", "PG-117", "교통사고[Boat]"),
    PANZERFAUST100M_PROJECTILE_C("PanzerFaust100M_Projectile_C", "Panzerfaust Projectile", "판처"),
    PLAYERFEMALE_A_C("PlayerFemale_A_C", "Player", "주먹"),
    PLAYERMALE_A_C("PlayerMale_A_C", "Player", "주먹"),
    PROJC4_C("ProjC4_C", "C4", "C4"),
    PROJGRENADE_C("ProjGrenade_C", "Frag Grenade", "수류탄"),
    PROJINCENDIARY_C("ProjIncendiary_C", "Incendiary Projectile", ""), // 헤이븐 트럭?
    PROJMOLOTOV_C("ProjMolotov_C", "Molotov Cocktail", "화염병"),
    PROJMOLOTOV_DAMAGEFIELD_DIRECT_C("ProjMolotov_DamageField_Direct_C", "Molotov Cocktail Fire Field", "화염병"),
    PROJSTICKYGRENADE_C("ProjStickyGrenade_C", "Sticky Bomb", "점착 폭탄"),
    RACINGDESTRUCTIBLEPROPANETANKACTOR_C("RacingDestructiblePropaneTankActor_C", "Propane Tank", ""),
    RACINGMODECONTORLLER_DESERT_C("RacingModeContorller_Desert_C", "Bluezone", "블루존"),
    REDZONEBOMB_C("RedZoneBomb_C", "Redzone", "레드존"),
    REDZONEBOMBINGFIELD("RedZoneBombingField", "Redzone", "레드존"),
    REDZONEBOMBINGFIELD_DEF_C("RedZoneBombingField_Def_C", "Redzone", "레드존"),
    TSLDESTRUCTIBLESURFACEMANAGER("TslDestructibleSurfaceManager", "Destructible Surface", ""),
    TSLPAINCAUSINGVOLUME("TslPainCausingVolume", "Lava", "용암"),
    UAZ_A_01_C("Uaz_A_01_C", "UAZ (open top)", "교통사고[UAZ]"),
    UAZ_ARMORED_C("Uaz_Armored_C", "UAZ (armored)", "교통사고[UAZ]"),
    UAZ_B_01_C("Uaz_B_01_C", "UAZ (soft top)", "교통사고[UAZ]"),
    UAZ_B_01_ESPORTS_C("Uaz_B_01_esports_C", "UAZ (soft top)", "교통사고[UAZ]"),
    UAZ_C_01_C("Uaz_C_01_C", "UAZ (hard top)", "교통사고[UAZ]"),
    ULTAIPAWN_BASE_FEMALE_C("UltAIPawn_Base_Female_C", "Player", "주먹"),
    ULTAIPAWN_BASE_MALE_C("UltAIPawn_Base_Male_C", "Player", "주먹"),
    WEAPACE32_C("WeapACE32_C", "ACE32", "ACE32"),
    WEAPAK47_C("WeapAK47_C", "AKM", "AKM"),
    WEAPAUG_C("WeapAUG_C", "AUG A3", "AUG"),
    WEAPAWM_C("WeapAWM_C", "AWM", "AWM"),
    WEAPBERRETA686_C("WeapBerreta686_C", "S686", "S686"),
    WEAPBERYLM762_C("WeapBerylM762_C", "Beryl", "Beryl"),
    WEAPBIZONPP19_C("WeapBizonPP19_C", "Bizon", "Bizon"),
    WEAPCOWBARPROJECTILE_C("WeapCowbarProjectile_C", "Crowbar Projectile", "석궁"),
    WEAPCOWBAR_C("WeapCowbar_C", "Crowbar", "석궁"),
    WEAPCROSSBOW_1_C("WeapCrossbow_1_C", "Crossbow", "석궁"),
    WEAPDP12_C("WeapDP12_C", "DBS", "DBS"),
    WEAPDP28_C("WeapDP28_C", "DP-28", "DP-28"),
    WEAPDESERTEAGLE_C("WeapDesertEagle_C", "Deagle", "Deagle"),
    WEAPDUNCANSHK416_C("WeapDuncansHK416_C", "M416", "M416"),
    WeapFamasG2_C("WeapFamasG2_C", "Famas", "Famas"),
    WEAPFNFAL_C("WeapFNFal_C", "SLR", "SLR"),
    WEAPG18_C("WeapG18_C", "P18C", "P18C"),
    WEAPG36C_C("WeapG36C_C", "G36C", "G36C"),
    WEAPGROZA_C("WeapGroza_C", "Groza", "Groza"),
    WEAPHK416_C("WeapHK416_C", "M416", "M416"),
    WEAPJULIESKAR98K_C("WeapJuliesKar98k_C", "Kar98k", "Kar98k"),
    WEAPK2_C("WeapK2_C", "K2", "K2"),
    WEAPKAR98K_C("WeapKar98k_C", "Kar98k", "Kar98k"),
    WEAPL6_C("WeapL6_C", "Lynx AMR", "링스"),
    WEAPLUNCHMEATSAK47_C("WeapLunchmeatsAK47_C", "AKM", "AKM"),
    WEAPM16A4_C("WeapM16A4_C", "M16A4", "M16A4"),
    WEAPM1911_C("WeapM1911_C", "P1911", "P1911"),
    WEAPM249_C("WeapM249_C", "M249", "M249"),
    WEAPM24_C("WeapM24_C", "M24", "M24"),
    WEAPM9_C("WeapM9_C", "P92", "P92"),
    WEAPMG3_C("WeapMG3_C", "MG3", "MG3"),
    WEAPMP5K_C("WeapMP5K_C", "MP5K", "MP5K"),
    WEAPMP9_C("WeapMP9_C", "MP9", "MP9"),
    WEAPMACHETEPROJECTILE_C("WeapMacheteProjectile_C", "Machete Projectile", "마체테"),
    WEAPMACHETE_C("WeapMachete_C", "Machete", "마체테"),
    WEAPMADSQBU88_C("WeapMadsQBU88_C", "QBU88", "QBU88"),
    WEAPMINI14_C("WeapMini14_C", "Mini 14", "Mini14"),
    WEAPMK12_C("WeapMk12_C", "Mk12", "Mk12"),
    WEAPMK14_C("WeapMk14_C", "Mk14 EBR", "Mk14"),
    WEAPMK47MUTANT_C("WeapMk47Mutant_C", "Mk47 Mutant", "Mutant"),
    WEAPMOSINNAGANT_C("WeapMosinNagant_C", "Mosin-Nagant", "모신나강"),
    WEAPNAGANTM1895_C("WeapNagantM1895_C", "R1895", "R1895"),
    WEAPORIGINS12_C("WeapOriginS12_C", "O12", "O12"),
    WEAPP90_C("WeapP90_C", "P90", "P90"),
    WEAPPANPROJECTILE_C("WeapPanProjectile_C", "Pan Projectile", "프라이팬"),
    WEAPPAN_C("WeapPan_C", "Pan", "프라이팬"),
    WEAPPANZERFAUST100M1_C("WeapPanzerFaust100M1_C", "Panzerfaust", "판처"),
    WEAPQBU88_C("WeapQBU88_C", "QBU88", "QBU88"),
    WEAPQBZ95_C("WeapQBZ95_C", "QBZ95", "QBZ95"),
    WEAPRHINO_C("WeapRhino_C", "R45", "R45"),
    WEAPSCAR_L_C("WeapSCAR-L_C", "SCAR-L", "SCAR-L"),
    WEAPSKS_C("WeapSKS_C", "SKS", "SKS"),
    WEAPSAIGA12_C("WeapSaiga12_C", "S12K", "S12K"),
    WEAPSAWNOFF_C("WeapSawnoff_C", "Sawed-off", "소드오프"),
    WEAPSICKLEPROJECTILE_C("WeapSickleProjectile_C", "Sickle Projectile", "낫"),
    WEAPSICKLE_C("WeapSickle_C", "Sickle", "낫"),
    WEAPTHOMPSON_C("WeapThompson_C", "Tommy Gun", "Tommy Gun"),
    WEAPTURRET_KILLTRUCK_MAIN_C("WeapTurret_KillTruck_Main_C", "Kill Truck Turret", "터렛"),
    WEAPUMP_C("WeapUMP_C", "UMP9", "UMP9"),
    WEAPUZI_C("WeapUZI_C", "Micro Uzi", "Uzi"),
    WEAPVSS_C("WeapVSS_C", "VSS", "VSS"),
    WEAPVECTOR_C("WeapVector_C", "Vector", "Vector"),
    WEAPWIN94_C("WeapWin94_C", "Win94", "Win94"),
    WEAPWINCHESTER_C("WeapWinchester_C", "S1897", "S1897"),
    WEAPVZ61SKORPION_C("Weapvz61Skorpion_C", "Skorpion", "Skorpion"),
    ;

    private static final DamageCauserName[] VALUES = values();
    private final String label;
    private final String eng;
    private final String kor;

    DamageCauserName(String label, String eng, String kor) {
        this.label = label;
        this.eng = eng;
        this.kor = kor;
    }

    public String getLabel() {
        return label;
    }

    public String getKor() {
        return kor;
    }

    public static DamageCauserName of(String label) {
        for (DamageCauserName damageCauserName : VALUES) {
            if (damageCauserName.label.equals(label)) return damageCauserName;
        }
        log.warn("DamageCauserName not found [{}]", label);
        return NOT_FOUND;
    }
}
