package com.kryhowsky.shop.service;

import com.kryhowsky.shop.model.dao.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User save(User user);
    User update(User user, Long id);
    void delete(Long id);
    Page<User> getPage(Pageable pageable);
    User getUserById(Long id);
    User getCurrentUser();
}
