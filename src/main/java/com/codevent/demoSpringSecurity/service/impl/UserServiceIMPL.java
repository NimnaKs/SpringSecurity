package com.codevent.demoSpringSecurity.service.impl;

import com.codevent.demoSpringSecurity.dao.UserDao;
import com.codevent.demoSpringSecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceIMPL implements UserService {

    final private UserDao userDao;
    @Override
    public UserDetailsService userDetailsService() {
        return username ->
                userDao.findByEmail(username).
                orElseThrow(()->new UsernameNotFoundException("User Not Found"));
    }
}
