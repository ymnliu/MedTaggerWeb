package org.ohnlp.web.db.service;

import java.sql.Date;
import org.ohnlp.web.db.entity.User;
import org.ohnlp.web.db.repo.UserRepository;
import org.springframework.stereotype.Service;

/**
 * UserService
 */
@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> listAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(String username) {
        User u = new User(username);
        this.userRepository.save(u);
        return u;
    }

    public User getUserByUsername(String username) {
        User u = this.userRepository.findByUsername(username);
        return u;
    }

    public User getOrCreateUser(String username) {
        User u = this.userRepository.findByUsername(username);
        if (u == null) {
            u = this.createUser(username);
        }
        return u;
    }
}