package me.gt.votesystem.mapper;

import me.gt.votesystem.model.VoteItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VoteItemsMapper {

    @Insert("CALL createVoteItem(#{itemName})")
    void createVoteItems(String itemName);

    @Select("CALL getVoteItemById(#{itemId})")
    @Results(id = "voteItem", value = {
            @Result(property = "itemId", column = "item_id"),
            @Result(property = "itemName", column = "item_name"),
            @Result(property = "voteCount", column = "vote_count")
    })
    VoteItem getVoteItemById(int itemId);

    @Select("CALL getAllVoteItems()")
    @ResultMap("voteItem")
    List<VoteItem> getAllVoteItems();

    @Select("CALL updateVoteItem(#{itemId}, #{itemName})")
    int updateVoteItem(int itemId, String itemName);

    @Select("CALL deleteVoteItemById(#{itemId})")
    int deleteVoteItemById(int itemId);

}
