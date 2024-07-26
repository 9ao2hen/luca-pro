package cn.mervyn.luca.module.ai.controller.admin.chat.vo.conversation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - AI 聊天对话创建【我的】 Request VO")
@Data
public class AiChatConversationCreateMyReqVO {

    @Schema(description = "聊天角色编号", example = "666")
    private Long roleId;

}
