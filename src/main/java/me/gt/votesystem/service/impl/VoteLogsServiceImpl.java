package me.gt.votesystem.service.impl;

import me.gt.votesystem.dto.VoteLogDto;
import me.gt.votesystem.mapper.VoteLogsMapper;
import me.gt.votesystem.model.VoteLog;
import me.gt.votesystem.service.VoteLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VoteLogsServiceImpl implements VoteLogsService {

    @Autowired
    private VoteLogsMapper voteLogsMapper;

    @Override
    @Transactional
    public void createVoteLog(VoteLogDto voteLogDto) {
        for (int itemId : voteLogDto.getItemIds()) {
            voteLogsMapper.createVoteLog(voteLogDto.getUser(), itemId);
        }
    }

    @Override
    public void createVoteLog(String user, int itemId) {
        voteLogsMapper.createVoteLog(user, itemId);
    }

    @Override
    public List<VoteLog> getAllVoteLogs() {
        return voteLogsMapper.getAllVoteLogs();
    }
}
