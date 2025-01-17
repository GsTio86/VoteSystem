package me.gt.votesystem.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VoteItem {

    @Schema(name = "item_id", description = "投票項目編號")
    private Integer itemId;

    @Schema(name = "item_name", description = "投票項目名稱")
    private String itemName;

    @Schema(name = "vote_count", description = "投票數")
    private Integer voteCount;
}
