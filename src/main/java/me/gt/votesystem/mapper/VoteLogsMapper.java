package me.gt.votesystem.mapper;

import me.gt.votesystem.model.VoteLog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VoteLogsMapper {

    @Insert("CALL createVoteLog(#{user}, #{itemId})")
    void createVoteLog(String user, int itemId);

    @Select("CALL getAllVoteLogs()")
    @Results(id = "voteLog", value = {
            @Result(property = "user", column = "user"),
            @Result(property = "itemId", column = "item_id")
    })
    List<VoteLog> getAllVoteLogs();

    @Select("CALL deleteVoteLogsById(#{itemId})")
    int deleteVoteLogsById(int itemId);

}
