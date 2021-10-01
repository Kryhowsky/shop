package com.kryhowsky.shop.repository;

import com.kryhowsky.shop.model.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { // warstwa do komunikacji z warstwą danych
}
