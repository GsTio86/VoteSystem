package me.gt.votesystem.service.impl;

import me.gt.votesystem.mapper.VoteItemsMapper;
import me.gt.votesystem.mapper.VoteLogsMapper;
import me.gt.votesystem.model.VoteItem;
import me.gt.votesystem.service.VoteItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VoteItemsServiceImpl implements VoteItemsService {

    @Autowired
    private VoteItemsMapper voteItemsMapper;

    @Autowired
    private VoteLogsMapper voteLogsMapper;

    @Override
    public void createVoteItem(String itemName) {
        voteItemsMapper.createVoteItems(itemName);
    }

    @Override
    public List<VoteItem> getAllVoteItems() {
        return voteItemsMapper.getAllVoteItems();
    }

    @Override
    public VoteItem getVoteItemById(int itemId) {
        return voteItemsMapper.getVoteItemById(itemId);
    }

    @Override
    public int updateVoteItem(int itemId, String itemName) {
        return voteItemsMapper.updateVoteItem(itemId, itemName);
    }

    @Override
    @Transactional
    public DeleteVoteInfo deleteVoteItemById(int itemId) {
        int deleteCount = voteItemsMapper.deleteVoteItemById(itemId);
        int deleteLogCount = voteLogsMapper.deleteVoteLogsById(itemId);
        return new DeleteVoteInfo(itemId, deleteCount, deleteLogCount);
    }
}
