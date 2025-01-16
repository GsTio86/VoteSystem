package me.gt.votesystem.service;

import me.gt.votesystem.model.VoteItem;

import java.util.List;

public interface VoteItemsService {

    /**
     * 建立新的投票項目
     * @param itemName 投票項目名稱
     */
    void createVoteItem(String itemName);

    /**
     * 獲取所有投票項目
     * @return List<VoteItemDto>
     */
    List<VoteItem> getAllVoteItems();

    /**
     * 獲取特定投票項目
     * @param itemId 投票項目編號
     * @return VoteItem
     */
    VoteItem getVoteItemById(int itemId);

    /**
     * 更新投票項目
     * @param itemId 投票項目編號
     * @param itemName 新的投票項目名稱
     * @return 更新的筆數
     */
    int updateVoteItem(int itemId, String itemName);

    /**
     * 刪除特定投票項目
     * @param itemId 投票項目編號
     * @return 刪除的筆數
     */
    DeleteVoteInfo deleteVoteItemById(int itemId);

    /**
     * 紀錄刪除投票資訊
     * @param itemId 投票項目編號
     * @param deleteCount 刪除的投票數
     * @param deleteLogCount 刪除的投票紀錄數
     */
    record DeleteVoteInfo(int itemId, int deleteCount, int deleteLogCount) {}

}
