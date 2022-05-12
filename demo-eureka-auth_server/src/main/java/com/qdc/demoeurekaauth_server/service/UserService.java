package com.qdc.demoeurekaauth_server.service;

import com.qdc.demoeurekaauth_server.pojo.User;
import org.springframework.stereotype.Service;


public interface UserService {
    public User getUser(String username);
}
