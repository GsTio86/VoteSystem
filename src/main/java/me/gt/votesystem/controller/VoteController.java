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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static me.gt.votesystem.utils.StringUtils.escapeHtml;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class VoteController {

    @Autowired
    private VoteItemsService voteItemsService;

    private static final String DATA_REGEX = "^(?!\\s+$)[\\u4e00-\\u9fa5a-zA-Z0-9. ]+$";

    @Operation(summary = "新增投票項目")
    @PostMapping("/vote/items/add")
    public ResponseEntity<String> addVoteItem(@RequestParam String itemName) {
        if (!itemName.matches(DATA_REGEX)) {
            return ResponseEntity.badRequest().body("新增投票項目失敗，請檢查資料格式");
        }
        voteItemsService.createVoteItem(itemName);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "獲取所有投票項目")
    @GetMapping("/vote/items")
    public List<VoteItem> getVoteItems() {
        List<VoteItem> items = voteItemsService.getAllVoteItems();
        items.forEach(item -> {
            item.setItemName(escapeHtml(item.getItemName()));
        });
        return items;
    }

    @Operation(summary = "獲取特定的投票項目")
    @GetMapping("/vote/items/{itemId}")
    public VoteItem getVoteItem(@PathVariable int itemId) {
        VoteItem item = voteItemsService.getVoteItemById(itemId);
        if (item != null) {
            item.setItemName(escapeHtml(item.getItemName()));
            return item;
        }
        return item;
    }

    @Operation(summary = "更新投票項目")
    @PutMapping("/vote/items/{itemId}")
    public int updateVoteItem(@PathVariable int itemId, @RequestParam String itemName) {
        if (!itemName.matches(DATA_REGEX)) {
            return -1;
        }
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
    public ResponseEntity<String> addVoteLog(@RequestBody VoteLogDto voteLogDto) {
        if (!voteLogDto.getUser().matches(DATA_REGEX)) {
            return ResponseEntity.badRequest().body("新增投票紀錄失敗，請檢查資料格式");
        }
        voteLogsService.createVoteLog(voteLogDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "獲取所有投票紀錄")
    @GetMapping("/vote/logs")
    public List<VoteLog> getVoteLogs() {
        List<VoteLog> logs = voteLogsService.getAllVoteLogs();
        logs.forEach(log -> {
            log.setUser(escapeHtml(log.getUser()));
        });
        return logs;
    }

    //--------------------------------

    @Autowired
    private VoteStatsService voteStatsService;

    @Operation(summary = "獲取投票統計")
    @GetMapping("/vote/stats")
    public List<VoteStats> getVoteStats() {
        List<VoteStats> stats = voteStatsService.getVoteStats();
        stats.forEach(stat -> {
            stat.setItemName(escapeHtml(stat.getItemName()));
        });
        return stats;
    }
}
