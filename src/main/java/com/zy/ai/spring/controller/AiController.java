package com.zy.ai.spring.controller;

import com.zy.ai.spring.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangyang
 * @since 2026/10/30 14:51
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/ai")
public class AiController {

    private final AiService aiService;

    @GetMapping("/chat")
    public String chat(@RequestParam String text) {
        return this.aiService.doChat(text);
    }
}
