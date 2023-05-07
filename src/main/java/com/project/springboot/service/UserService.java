package com.project.springboot.service;

import com.project.springboot.dto.request.ReqLogin;
import com.project.springboot.dto.request.ReqToken;
import com.project.springboot.dto.response.RespUser;
import com.project.springboot.dto.response.Response;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    Response<RespUser> login(ReqLogin reqLogin);

    Response logout(ReqToken reqToken);
}
