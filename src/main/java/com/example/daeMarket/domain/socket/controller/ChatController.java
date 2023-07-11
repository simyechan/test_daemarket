package com.example.daeMarket.domain.socket.controller;

import com.example.daeMarket.domain.socket.MessageType;
import com.example.daeMarket.domain.socket.dto.ChatMessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.ArrayList;

@Controller
@Slf4j
public class ChatController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    ArrayList<String> users = new ArrayList<>();

    @EventListener
    public void handleWebSocketConnecListener(SessionConnectEvent event) {
        log.info("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("uesrname");

        if(username != null) {
            log.info("User Disconnected : " + username);

            users.remove(username);
            System.out.println(users);

            ChatMessageDTO chat = new ChatMessageDTO(MessageType.LEAVE, null, username);
            messagingTemplate.convertAndSend("/roomname/public", chat);
        }
    }

    @MessageMapping("/sendMessage")
    @SendTo("/roomname/public")
    public ChatMessageDTO sendMessage(@Payload ChatMessageDTO chat) {
        return chat;
    }

    @MessageMapping("/addUser")
    @SendTo("/roomname/public")
    public ChatMessageDTO addUser(@Payload ChatMessageDTO chat, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chat.getSender());
        users.add(chat.getSender());
        return chat;
    }
}

