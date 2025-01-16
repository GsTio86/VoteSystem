package me.gt.votesystem.service;

import me.gt.votesystem.dto.VoteLogDto;
import me.gt.votesystem.model.VoteLog;

import java.util.List;

public interface VoteLogsService {

    /**
     * 建立新的投票紀錄
     * @param voteLogDto 投票紀錄
     */
    void createVoteLog(VoteLogDto voteLogDto);

    /**
     * 建立新的投票紀錄
     * @param user 投票人
     * @param itemId 投票項目編號
     */
    void createVoteLog(String user, int itemId);

    /**
     * 取得所有投票紀錄
     * @return List<VoteLog>
     */
    List<VoteLog> getAllVoteLogs();
}
