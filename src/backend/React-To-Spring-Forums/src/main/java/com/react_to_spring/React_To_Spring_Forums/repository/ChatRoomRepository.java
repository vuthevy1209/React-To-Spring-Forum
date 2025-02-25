package com.react_to_spring.React_To_Spring_Forums.repository;

import com.react_to_spring.React_To_Spring_Forums.entity.ChatRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    Optional<ChatRoom> findByChatId(String chatId);

    Optional<ChatRoom> findByChatRoomName(String chatRoomName);

    Optional<ChatRoom> findByParticipantIds(List<String> participantIds);

    Page<ChatRoom> findChatRoomByParticipantIdsContaining(List<String> participantIds, Pageable pageable);

    Page<ChatRoom> findChatRoomByParticipantIdsContainingAndChatRoomNameContaining(List<String> participantIds,
                                                                                   String chatRoomName,
                                                                                   Pageable pageable);

    Optional<ChatRoom> findChatRoomByParticipantIds(List<String> participantIds);

    void deleteByChatId(String chatId);
}
