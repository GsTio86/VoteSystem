package me.gt.votesystem.service.impl;

import me.gt.votesystem.mapper.VoteStatsMapper;
import me.gt.votesystem.model.VoteStats;
import me.gt.votesystem.service.VoteStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteStatsServiceImpl implements VoteStatsService {

    @Autowired
    private VoteStatsMapper voteStatsMapper;

    @Override
    public List<VoteStats> getVoteStats() {
        return voteStatsMapper.getVoteStats();
    }

    @Override
    public int getVoteStatsById(int itemId) {
        return voteStatsMapper.getVoteStatsById(itemId);
    }
}
