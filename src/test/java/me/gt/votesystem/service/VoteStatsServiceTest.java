package me.gt.votesystem.service;

import me.gt.votesystem.model.VoteStats;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VoteStatsServiceTest {

    @Autowired
    private VoteStatsService voteStatsService;

    @Test
    void getVoteStats() {
        for (VoteStats stats : voteStatsService.getVoteStats()) {
            System.out.println(stats);
        }
    }

    @Test
    void getVoteStatsById() {
        int voteCount = voteStatsService.getVoteStatsById(3);
        System.out.println(voteCount);
    }

}
