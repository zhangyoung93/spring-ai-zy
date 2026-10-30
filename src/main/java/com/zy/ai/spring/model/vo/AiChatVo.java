package com.zy.ai.spring.model.vo;

import lombok.Data;

/**
 * AiChatVo
 *
 * @author zhangyang
 * @since 2026/10/30 15:16
 */
@Data
public class AiChatVo {

    private String content;

    private Integer inputToken;

    private Integer outputToken;

    private Integer totalToken;

}
