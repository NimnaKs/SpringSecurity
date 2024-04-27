package com.codevent.demoSpringSecurity.dao;

import com.codevent.demoSpringSecurity.dto.UserDTO;
import com.codevent.demoSpringSecurity.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<UserEntity,String> {
    Optional<UserEntity> finByEmail(String email);
}
