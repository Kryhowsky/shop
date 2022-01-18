package com.kryhowsky.shop.service.impl;

import com.kryhowsky.shop.model.dao.User;
import com.kryhowsky.shop.repository.RoleRepository;
import com.kryhowsky.shop.repository.UserRepository;
import com.kryhowsky.shop.security.SecurityUtils;
import com.kryhowsky.shop.service.MailService;
import com.kryhowsky.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final MailService mailService;
    
    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        roleRepository.findByName("ROLE_USER").ifPresent(role -> user.setRoles(Collections.singleton(role)));
        user.setActivationToken(UUID.randomUUID().toString());
        var result = userRepository.save(user);
        Map<String, Object> variables = new HashMap<>();
        variables.put("link", "http://shop-env.eba-bmtuybzm.eu-central-1.elasticbeanstalk.com/api/users/activate?token=" + user.getActivationToken());
        mailService.sendEmail(variables, "greetingsMail", user.getEmail());
        return result;
    }

    @Override
    @Transactional
    public User update(User user, Long id) {

        var userDb = getUserById(id);
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

    @Override
    @Transactional
    public void activateUser(String activationToken) {
        var user = userRepository.findByActivationToken(activationToken).orElseThrow(EntityNotFoundException::new);
        user.setActivationToken(null);
    }
}
