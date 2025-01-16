package me.gt.votesystem.mapper;

import me.gt.votesystem.model.VoteStats;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VoteStatsMapper {

    @Select("CALL getVoteStats()")
    @Results(id = "voteStats", value = {
            @Result(property = "itemId", column = "投票項目編號"),
            @Result(property = "itemName", column = "投票項目名稱"),
            @Result(property = "voteCount", column = "目前投票票數"),
    })
    List<VoteStats> getVoteStats();

    @Select("CALL getVoteStatsById(#{itemId})")
    int getVoteStatsById(int itemId);

}
