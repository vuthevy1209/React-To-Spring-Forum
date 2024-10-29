package com.react_to_spring.React_To_Spring_Forums.dto.request.comment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@Builder
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class CommentCreationRequest {
    @JsonProperty("user_id")
    String userId;

    @JsonProperty("post_id")
    String postId;

    @JsonProperty("content")
    String content;

    @JsonProperty("image_url")
    String imageUrl;
}
