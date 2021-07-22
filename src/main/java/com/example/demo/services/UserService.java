package com.example.demo.services;


import com.example.demo.dto.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    List<UserDetails> userDetailsList = new ArrayList<>();


    public List<UserDetails> getAll() {
        return userDetailsList;
    }

    public void add(UserDetails userDetails) {
        userDetailsList.add(userDetails);
    }
}
