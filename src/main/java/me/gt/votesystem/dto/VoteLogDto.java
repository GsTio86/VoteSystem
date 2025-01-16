package me.gt.votesystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteLogDto {

    @Schema(description = "投票人")
    private String user;

    @Schema(description = "投票項目編號 (多選)")
    private int[] itemIds;

}
