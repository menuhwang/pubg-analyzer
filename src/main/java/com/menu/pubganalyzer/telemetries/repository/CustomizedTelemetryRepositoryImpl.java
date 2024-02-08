package com.menu.pubganalyzer.telemetries.repository;

import com.menu.pubganalyzer.telemetries.model.TelemetryEntity;
import com.menu.pubganalyzer.util.pubg.response.telemetry.events.LogPlayerAttack;
import com.menu.pubganalyzer.util.pubg.response.telemetry.events.LogPlayerTakeDamage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CustomizedTelemetryRepositoryImpl implements CustomizedTelemetryRepository {
    private final MongoTemplate mongoTemplate;

    @Override
    public List<LogPlayerAttack> findPlayerAttackByHittableWeapon(String matchId, String attackerName) {
        /*
        * {
            'matchId': 'c768dd59-0413-4fe9-b1ec-94c422c161cf',
            'telemetry.type': 'LogPlayerAttack',
            'telemetry.attacker.name': 'Patriot_Engineer',
            'telemetry.weapon.subCategory': {$in: ['Main', 'Handgun']}
        }*/
        MatchOperation match = TypedAggregation.match(
                    Criteria.where("matchId").is(matchId)
                            .and("telemetry.type").is("LogPlayerAttack")
                            .and("telemetry.attacker.name").is(attackerName)
                            .and("telemetry.weapon.subCategory").in("Main", "Handgun")
                            .and("telemetry.weapon.itemId").nin("Item_Weapon_M79_C", "Item_Weapon_StunGun_C", "Item_Weapon_FlareGun_C")
        );
        SortOperation sort = TypedAggregation.sort(Sort.Direction.DESC, "telemetry.timestamp");

        List<TelemetryEntity> telemetryEntities = mongoTemplate.aggregate(
                TypedAggregation.newAggregation(TelemetryEntity.class, match, sort),
                TelemetryEntity.class
        ).getMappedResults();

        return telemetryEntities.stream()
                .map(TelemetryEntity::getTelemetry)
                .map(telemetryResponse -> (LogPlayerAttack) telemetryResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<LogPlayerTakeDamage> findPlayerTakeDamageGunByAttacker(String matchId, String attackerName) {
        /*
        * {
            'matchId': 'c768dd59-0413-4fe9-b1ec-94c422c161cf',
            'telemetry.type': 'LogPlayerTakeDamage',
            'telemetry.attacker.name': 'Patriot_Engineer',
            'telemetry.victim.name': {$ne: 'Patriot_Engineer'},
        }*/
        List<TelemetryEntity> telemetryEntities = mongoTemplate.find(
                Query.query(
                        Criteria.where("matchId").is(matchId)
                                .and("telemetry.type").is("LogPlayerTakeDamage")
                                .and("telemetry.attacker.name").is(attackerName)
                                .and("telemetry.victim.name").ne(attackerName)
                                .and("telemetry.damageTypeCategory").is("Damage_Gun")
                ),
                TelemetryEntity.class
        );

        return telemetryEntities.stream()
                .map(TelemetryEntity::getTelemetry)
                .map(telemetryResponse -> (LogPlayerTakeDamage) telemetryResponse)
                .collect(Collectors.toList());
    }
}
