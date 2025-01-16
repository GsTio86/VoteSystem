package me.gt.votesystem.service;

import me.gt.votesystem.model.VoteStats;

import java.util.List;

public interface VoteStatsService {

    /**
     * 獲取投票統計資料
     * @return List<VoteStats>
     */
    List<VoteStats> getVoteStats();

    /**
     * 獲取指定投票項目編號的投票數
     * @param itemId 投票項目編號
     * @return int
     */
    int getVoteStatsById(int itemId);

}
