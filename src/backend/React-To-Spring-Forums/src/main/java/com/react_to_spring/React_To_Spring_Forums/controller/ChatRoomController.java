package com.react_to_spring.React_To_Spring_Forums.controller;

import com.react_to_spring.React_To_Spring_Forums.dto.request.chatroom.ChatRoomCreationRequest;
import com.react_to_spring.React_To_Spring_Forums.dto.response.ApiResponse;
import com.react_to_spring.React_To_Spring_Forums.dto.response.ChatRoomResponse;
import com.react_to_spring.React_To_Spring_Forums.dto.response.PageResponse;
import com.react_to_spring.React_To_Spring_Forums.service.chatroom.ChatRoomService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat-rooms")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatRoomController {

    ChatRoomService chatRoomService;

    @PostMapping
    public ApiResponse<ChatRoomResponse> createChatRoom(@RequestBody ChatRoomCreationRequest request) {
        return ApiResponse.<ChatRoomResponse>builder()
                .data(chatRoomService.createChatRoom(request))
                .build();
    }

    @GetMapping("/chat-room-name/{chatRoomName}")
    public ApiResponse<ChatRoomResponse> getChatRoomByName(@PathVariable String chatRoomName) {
        return ApiResponse.<ChatRoomResponse>builder()
                .data(chatRoomService.getChatRoomByName(chatRoomName))
                .build();
    }

    @GetMapping
    public ApiResponse<ChatRoomResponse> getDirectChatRoom(@RequestParam("senderId") String senderId,
                                                           @RequestParam("recipientId") String recipientId) {
        return ApiResponse.<ChatRoomResponse>builder()
                .data(chatRoomService.getDirectChatRoom(senderId, recipientId))
                .build();
    }

    @GetMapping("/my-chat-rooms")
    public ApiResponse<PageResponse<ChatRoomResponse>> getMyChatRooms(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                                      @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                                                                      @RequestParam(value = "chatroomName", required = false, defaultValue = "") String chatroomName) {
        return ApiResponse.<PageResponse<ChatRoomResponse>>builder()
                .data(chatRoomService.getMyChatRooms(page, size, chatroomName))
                .build();
    }

    @DeleteMapping("/{chatId}")
    public ApiResponse<Void> deleteChatRoom(@PathVariable String chatId) {
        chatRoomService.deleteChatRoom(chatId);
        return ApiResponse.<Void>builder()
                .message("Chat room deleted successfully")
                .build();
    }
}
