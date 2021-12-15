package com.example.stocktrackerapp.service;

import com.example.stocktrackerapp.model.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserServices {

    List<UserInfo> findAllUser();

    UserInfo saverUser(UserInfo userInfo);

    UserInfo authenticate(String email, String password);
}
