package com.react_to_spring.React_To_Spring_Forums.controller;

import com.react_to_spring.React_To_Spring_Forums.dto.request.addfriend.ResponseAddFriendRequest;
import com.react_to_spring.React_To_Spring_Forums.dto.response.AddFriendRequestResponse;
import com.react_to_spring.React_To_Spring_Forums.dto.response.ApiResponse;
import com.react_to_spring.React_To_Spring_Forums.dto.response.PageResponse;
import com.react_to_spring.React_To_Spring_Forums.service.addfriendrequest.AddFriendRequestService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/requests")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddFriendController {

    AddFriendRequestService addFriendRequestService;

    @NonFinal
    @Value("${app.controller.add-friend.response.response-add-friend-success}")
    String responseAddFriendSuccessMessage;

    @NonFinal
    @Value("${app.controller.add-friend.response.add-friend-success}")
    String addFriendSuccessMessage;

    @NonFinal
    @Value("${app.controller.add-friend.response.unfriend-success}")
    String unfriendSuccessMessage;

    @PostMapping("/add-friend")
    @Operation(summary = "Send add friend request",
            description = "Send add friend request to another user")
    public ApiResponse<Void> sendAddFriendRequest(@RequestParam("friendId") String friendId) {
        addFriendRequestService.sendAddFriendRequest(friendId);
        return ApiResponse.<Void>builder().message(addFriendSuccessMessage).build();
    }

    @PostMapping("/add-friend-response")
    @Operation(summary = "Response add friend request",
            description = "Response to add friend request from another user")
    public ApiResponse<Void> updateAddFriendRequest(@RequestBody ResponseAddFriendRequest request) {
        addFriendRequestService.responseAddFriendRequest(request);
        return ApiResponse.<Void>builder()
                .message(responseAddFriendSuccessMessage)
                .build();
    }

    @GetMapping("/all-add-friend")
    @Operation(summary = "Get all add friend requests by user ID",
            description = "Get all add friend requests by user ID")
    public ApiResponse<PageResponse<AddFriendRequestResponse>> getAllAddFriendRequestsByUserId(
            @RequestParam("userId") String userId,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        PageResponse<AddFriendRequestResponse> responses =
                addFriendRequestService.getAllAddFriendRequestsByUserId(userId, page, size);

        return ApiResponse.<PageResponse<AddFriendRequestResponse>>builder()
                .data(responses)
                .build();
    }

    @PatchMapping("/unfriend")
    @Operation(summary = "Unfriend",
            description = "Unfriend another user")
    public ApiResponse<Void> unfriend(@RequestParam("friendId") String friendId) {
        addFriendRequestService.unfriend(friendId);
        return ApiResponse.<Void>builder().message(unfriendSuccessMessage).build();
    }

}
