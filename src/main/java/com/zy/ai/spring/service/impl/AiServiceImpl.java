package com.zy.ai.spring.service.impl;

import com.zy.ai.spring.model.vo.AiChatVo;
import com.zy.ai.spring.service.AiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;

/**
 * AiServiceImpl
 *
 * @author zhangyang
 * @since 2026/10/30 15:03
 */
@Slf4j
@Service
public class AiServiceImpl implements AiService {

    private final ChatClient chatClient;

    public AiServiceImpl(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @Override
    public String doChat(String text) {
        log.info("request -> text={}", text);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        ChatResponse chatResponse = this.chatClient.prompt().user(text).call().chatResponse();
        Assert.notNull(chatResponse, "AI响应超时");
        Assert.notNull(chatResponse.getResult(), "AI无响应");
        String content = chatResponse.getResult().getOutput().getText();
        Usage usage = chatResponse.getMetadata().getUsage();

        AiChatVo aiChatVo = new AiChatVo();
        aiChatVo.setInputToken(usage.getPromptTokens());
        aiChatVo.setOutputToken(usage.getCompletionTokens());
        aiChatVo.setTotalToken(usage.getTotalTokens());
        aiChatVo.setContent(content);

        stopWatch.stop();

        log.info("response -> aiChatVo={},duration={}ms", aiChatVo, stopWatch.getTotalTimeMillis());
        return content;
    }
}
