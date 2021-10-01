package com.kryhowsky.shop.service.impl;

import com.kryhowsky.shop.model.dao.User;
import com.kryhowsky.shop.repository.UserRepository;
import com.kryhowsky.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User save(User user) { //  jeśli id jest ustawione to robi select i jeśli taki obiekt jest w bazie to go aktualizuje
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
    public void delete(Long id) { // zwraca wyjątek jeśli nie ma takiego obiektu w bazie
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> getPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserById(Long id) { // zwraca EntityNotFoundException
        return userRepository.getById(id);
    }
}
