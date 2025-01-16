package me.gt.votesystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import me.gt.votesystem.dto.VoteLogDto;
import me.gt.votesystem.model.VoteItem;
import me.gt.votesystem.model.VoteLog;
import me.gt.votesystem.model.VoteStats;
import me.gt.votesystem.service.VoteItemsService;
import me.gt.votesystem.service.VoteLogsService;
import me.gt.votesystem.service.VoteStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class VoteController {

    @Autowired
    private VoteItemsService voteItemsService;

    @Operation(summary = "新增投票項目")
    @PostMapping("/vote/items/add")
    public void addVoteItem(@RequestParam String itemName) {
        voteItemsService.createVoteItem(itemName);
    }

    @Operation(summary = "獲取所有投票項目")
    @GetMapping("/vote/items")
    public List<VoteItem> getVoteItems() {
        return voteItemsService.getAllVoteItems();
    }

    @Operation(summary = "獲取特定的投票項目")
    @GetMapping("/vote/items/{itemId}")
    public VoteItem getVoteItem(@PathVariable int itemId) {
        return voteItemsService.getVoteItemById(itemId);
    }

    @Operation(summary = "更新投票項目")
    @PutMapping("/vote/items/{itemId}")
    public int updateVoteItem(@PathVariable int itemId, @RequestParam String itemName) {
        return voteItemsService.updateVoteItem(itemId, itemName);
    }

    @Operation(summary = "刪除投票項目")
    @DeleteMapping("/vote/items/{itemId}")
    public VoteItemsService.DeleteVoteInfo deleteVoteItem(@PathVariable int itemId) {
        return voteItemsService.deleteVoteItemById(itemId);
    }

    //--------------------------------

    @Autowired
    private VoteLogsService voteLogsService;

    @Operation(summary = "新增投票紀錄")
    @PostMapping("/vote/")
    public void addVoteLog(@RequestBody VoteLogDto voteLogDto) {
        voteLogsService.createVoteLog(voteLogDto);
    }

    @Operation(summary = "獲取所有投票紀錄")
    @GetMapping("/vote/logs")
    public List<VoteLog> getVoteLogs() {
        return voteLogsService.getAllVoteLogs();
    }

    //--------------------------------

    @Autowired
    private VoteStatsService voteStatsService;

    @Operation(summary = "獲取投票統計")
    @GetMapping("/vote/stats")
    public List<VoteStats> getVoteStats() {
        return voteStatsService.getVoteStats();
    }
}
