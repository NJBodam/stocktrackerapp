package com.example.stocktrackerapp.service.serviceimplementation;

import com.example.stocktrackerapp.model.UserInfo;
import com.example.stocktrackerapp.repository.UserRepository;
import com.example.stocktrackerapp.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserServices {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserInfo> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public UserInfo saverUser(UserInfo userInfo) {
        if (userRepository.findByEmail(userInfo.getEmail()).isPresent()) {
            System.out.println("This mail is in use");
            return null;
        }
        return userRepository.save(userInfo);
    }

    @Override
    public UserInfo authenticate(String email, String password){
        return userRepository.findByEmailAndPassword(email, password).orElse(null);
    }

}
