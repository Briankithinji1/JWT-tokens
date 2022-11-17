package com.example.JWTtokens.service;

import com.example.JWTtokens.domain.AppUser;
import com.example.JWTtokens.domain.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppUserService {

    AppUser saveUser(AppUser appUser);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    AppUser getUser(String username);
    List<AppUser>getUsers();
}
