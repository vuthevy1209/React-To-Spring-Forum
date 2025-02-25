package com.react_to_spring.React_To_Spring_Forums.service.react;

import com.react_to_spring.React_To_Spring_Forums.dto.response.ReactResponse;
import com.react_to_spring.React_To_Spring_Forums.entity.React;
import com.react_to_spring.React_To_Spring_Forums.exception.AppException;
import com.react_to_spring.React_To_Spring_Forums.exception.ErrorCode;
import com.react_to_spring.React_To_Spring_Forums.mapper.ReactMapper;
import com.react_to_spring.React_To_Spring_Forums.repository.PostRepository;
import com.react_to_spring.React_To_Spring_Forums.repository.ReactRepository;
import com.react_to_spring.React_To_Spring_Forums.service.notification.NotificationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReactServiceImpl implements ReactService {
    PostRepository postRepository;
    ReactMapper reactMapper;
    ReactRepository reactRepository;

    NotificationService notificationService;

    @Override
    public ReactResponse createReact(String postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        if(!postRepository.existsById(postId)) {
            throw new AppException(ErrorCode.POST_NOT_FOUND);
        }
        if(reactRepository.existsByUserIdAndPostId(userId, postId)) {
            throw new AppException(ErrorCode.REACT_ALREADY_EXISTS);
        }

        LocalDateTime currentTime = LocalDateTime.now();
        React react = React.builder()
                .postId(postId)
                .userId(userId)
                .createdDate(currentTime).build();

        react = reactRepository.save(react);

        String postOwnerId = postRepository.findById(postId).get().getUserId();

        notificationService.sendReactToPostCreationNotification(userId,postOwnerId,react.getId());
        return reactMapper.toReactResponse(react);
    }

    @Override
    public void deleteReactByPostId(String postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        boolean exists = reactRepository.existsByUserIdAndPostId(userId, postId);
        if(!exists) {
            throw new AppException(ErrorCode.REACT_NOT_FOUND);
        }

        reactRepository.deleteByPostIdAndUserId(postId, userId);
    }

    @Override
    public ReactResponse getReactByPostId(String postId) {
        return null;
    }
}
