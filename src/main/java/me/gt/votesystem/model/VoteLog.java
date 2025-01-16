package me.gt.votesystem.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VoteLog {

    @Schema(name = "user", description = "投票人")
    private String user;

    @Schema(name = "item_id", description = "投票項目編號")
    private Integer itemId;
}
