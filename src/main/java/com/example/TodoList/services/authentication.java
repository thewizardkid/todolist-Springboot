package com.example.TodoList.services;

import org.springframework.stereotype.Service;

@Service
public class authentication {
    public boolean authenticate(String name, String password){
        boolean isValidUsername = name.equalsIgnoreCase("niggahoe");
        boolean isValidPassword = password.equalsIgnoreCase("iseedeadpeople");

        return isValidUsername && isValidPassword;
    }
}
