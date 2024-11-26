package com.react_to_spring.React_To_Spring_Forums.controller;

import com.react_to_spring.React_To_Spring_Forums.dto.request.verifycode.SendVerificationRequest;
import com.react_to_spring.React_To_Spring_Forums.dto.response.ApiResponse;
import com.react_to_spring.React_To_Spring_Forums.entity.User;
import com.react_to_spring.React_To_Spring_Forums.exception.ErrorCode;
import com.react_to_spring.React_To_Spring_Forums.service.verifycode.VerifyCodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/verify")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Tag(name = "Verify Code Controller", description = "APIs for verification code management")
public class VerifyCodeController {

    VerifyCodeService verifyCodeService;


    @GetMapping()
    @Operation(summary = "Verify link",
            description = "Verify link when user clicks on the link in the email")
    public ApiResponse<Object> verifyLink(@RequestParam("userId") String userId, @RequestParam("verificationCode") String verficationCode) {
        if(verifyCodeService.verify(userId, verficationCode)){
            return ApiResponse.builder()
                    .message("Verification successful")
                    .build();
        }

        // if expired (invalid will throw exception)
        return ApiResponse.builder()
                .code(ErrorCode.VERIFY_CODE_EXPIRED.getCode())
                .message(ErrorCode.VERIFY_CODE_EXPIRED.getMessage())
                .build();

    }

    @PostMapping("/send-code")
    @Operation(summary = "Send verification code",
            description = "Send verification code to user's email")
    public ApiResponse<Object> sendVerifyCode(@RequestBody SendVerificationRequest sendVerificationRequest) {
        verifyCodeService.sendVerifyCode(sendVerificationRequest);
        return ApiResponse.builder()
                .message("Verification code sent")
                .build();
    }

    @PostMapping("/send-link")
    @Operation(summary = "Send verification link",
            description = "Send verification link to user's email")
    public ApiResponse<Object> sendVerifyLink(@RequestBody SendVerificationRequest sendVerificationRequest) {
        verifyCodeService.sendVerifyLink(sendVerificationRequest);
        return ApiResponse.builder()
                .message("Verification code resent")
                .build();
    }


}
