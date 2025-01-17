package me.gt.votesystem.service;

import me.gt.votesystem.dto.VoteLogDto;
import me.gt.votesystem.model.VoteLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
class VoteLogsServiceTest {

    @Autowired
    private VoteLogsService voteLogsService;

    @Test
    void addVoteLog() {
        VoteLogDto voteLogDto = VoteLogDto
                .builder()
                .user("test")
                .itemIds(Set.of(1, 2,3,4,5))
                .build();
        voteLogsService.createVoteLog(voteLogDto);
    }

    @Test
    void getVoteLogs() {
        for (VoteLog log : voteLogsService.getAllVoteLogs()) {
            System.out.println(log);
        }
    }

}
