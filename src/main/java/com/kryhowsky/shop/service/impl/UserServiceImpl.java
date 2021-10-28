package com.kryhowsky.shop.service.impl;

import com.kryhowsky.shop.model.dao.User;
import com.kryhowsky.shop.repository.RoleRepository;
import com.kryhowsky.shop.repository.UserRepository;
import com.kryhowsky.shop.security.SecurityUtils;
import com.kryhowsky.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        roleRepository.findByName("ROLE_USER").ifPresent(role -> user.setRoles(Collections.singleton(role)));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(User user, Long id) {

        User userDb = getUserById(id);
        userDb.setEmail(user.getEmail());
        userDb.setFirstName(user.getFirstName());
        userDb.setLastName(user.getLastName());

        return userDb;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> getPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public User getCurrentUser() {
        return userRepository.findByEmail(SecurityUtils.getCurrentEmailUser()).orElseThrow(EntityNotFoundException::new);
    }
}
