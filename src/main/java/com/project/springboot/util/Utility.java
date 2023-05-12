package com.project.springboot.util;

import com.project.springboot.dto.request.ReqToken;
import com.project.springboot.entity.User;
import com.project.springboot.entity.UserToken;
import com.project.springboot.enums.EnumAvailableStatus;
import com.project.springboot.exception.ExceptionConstants;
import com.project.springboot.exception.MyException;
import com.project.springboot.repository.UserRepository;
import com.project.springboot.repository.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Utility {

    private final UserRepository userRepository;

    private final UserTokenRepository userTokenRepository;

    public UserToken checkToken(ReqToken reqToken) {
        Long userId = reqToken.getUserId();
        String token = reqToken.getToken();
        if (userId == null || token == null) {
            throw new MyException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
        }
        User user = userRepository.findUserByIdAndActive(userId, EnumAvailableStatus.ACTIVE.value);
        if (user == null) {
            throw new MyException(ExceptionConstants.USER_NOT_FOUND, "User not found");
        }
        UserToken userToken = userTokenRepository.findUserTokenByUserAndTokenAndActive(user, token, EnumAvailableStatus.ACTIVE.value);
        if (userToken == null) {
            throw new MyException(ExceptionConstants.INVALID_TOKEN, "Invalid token");
        }
        return userToken;
    }
}