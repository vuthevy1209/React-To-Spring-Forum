package com.react_to_spring.React_To_Spring_Forums.service.chatroom;

import com.react_to_spring.React_To_Spring_Forums.dto.request.chatroom.ChatRoomCreationRequest;
import com.react_to_spring.React_To_Spring_Forums.dto.response.ChatRoomResponse;
import com.react_to_spring.React_To_Spring_Forums.dto.response.PageResponse;

public interface ChatRoomService {

    ChatRoomResponse createChatRoom(ChatRoomCreationRequest request);

    ChatRoomResponse getDirectChatRoom(String senderId, String recipientId);

    ChatRoomResponse getChatRoomByName(String chatRoomName);

    PageResponse<ChatRoomResponse> getMyChatRooms(int page, int size, String chatroomName);

    void deleteChatRoom(String chatId);
}
