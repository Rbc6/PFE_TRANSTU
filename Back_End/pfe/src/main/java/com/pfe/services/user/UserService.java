package com.pfe.services.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


public interface UserService {
    UserDetailsService userDetailsService();
}
