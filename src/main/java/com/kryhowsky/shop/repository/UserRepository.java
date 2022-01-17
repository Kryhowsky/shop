package com.kryhowsky.shop.repository;

import com.kryhowsky.shop.model.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, RevisionRepository<User, Long, Integer> {

    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndActivationTokenIsNull(String email);
    Optional<User> findByActivationToken(String activationToken);
}
