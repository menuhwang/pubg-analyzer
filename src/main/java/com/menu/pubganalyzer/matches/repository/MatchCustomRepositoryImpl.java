package com.menu.pubganalyzer.matches.repository;

import com.menu.pubganalyzer.matches.dto.request.MatchFindCondition;
import com.menu.pubganalyzer.matches.model.Match;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class MatchCustomRepositoryImpl implements MatchCustomRepository {
    private final MongoTemplate mongoTemplate;

    @Override
    public Page<Match> findByPlayerName(String playerName, Pageable pageable, MatchFindCondition matchFindCondition) {
        Query query = new Query().with(pageable);

        query.addCriteria(Criteria.where("rosters.participants.name").is(playerName));

        if (matchFindCondition.hasMatchTypeCondition()) {
            query.addCriteria(Criteria.where("matchType").in(matchFindCondition.matchTypes()));
        }

        List<Match> matches = mongoTemplate.find(query, Match.class);

        return PageableExecutionUtils.getPage(
                matches,
                pageable,
                () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Match.class)
        );
    }
}
