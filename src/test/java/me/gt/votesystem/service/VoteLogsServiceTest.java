package me.gt.votesystem.service;

import me.gt.votesystem.dto.VoteLogDto;
import me.gt.votesystem.model.VoteLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VoteLogsServiceTest {

    @Autowired
    private VoteLogsService voteLogsService;

    @Test
    void addVoteLog() {
        VoteLogDto voteLogDto = VoteLogDto
                .builder()
                .user("test")
                .itemIds(new int[]{1, 3,5,7,8})
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
