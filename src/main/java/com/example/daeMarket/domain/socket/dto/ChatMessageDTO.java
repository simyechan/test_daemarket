package com.example.daeMarket.domain.socket.dto;

import com.example.daeMarket.domain.socket.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDTO {
    private MessageType type;
    private String content;
    private String sender;
}
