package me.gt.votesystem.service;

import me.gt.votesystem.model.VoteItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VoteItemsServiceTest {

    @Autowired
    private VoteItemsService voteItemsService;

    @Test
    void addVoteItem() {
        voteItemsService.createVoteItem("測試投票項目");
    }

    @Test
    void getVoteItems() {
        for (VoteItem item : voteItemsService.getAllVoteItems()) {
            System.out.println(item);
        }
    }

    @Test
    void getVoteItem() {
        VoteItem voteItem = voteItemsService.getVoteItemById(1);
        if (voteItem != null) {
            System.out.println(voteItem);
        } else {
            System.out.println("找不到投票項目");
        }
    }

    @Test
    void updateVoteItem() {
        int updateCount = voteItemsService.updateVoteItem(1, "超級電腦");
        if (updateCount > 0) {
            System.out.println("更新成功");
        } else {
            System.out.println("更新失敗");
        }
    }

    @Test
    void deleteVoteItem() {
        VoteItemsService.DeleteVoteInfo deleteVoteInfo = voteItemsService.deleteVoteItemById(2);
        if (deleteVoteInfo.deleteCount() > 0) {
            System.out.println("刪除成功");
            if (deleteVoteInfo.deleteLogCount() > 0) {
                System.out.println("刪除了" + deleteVoteInfo.deleteLogCount() + "筆投票紀錄");
            } else {
                System.out.println("沒有投票紀錄");
            }
        } else {
            System.out.println("刪除失敗");
        }
    }


}
