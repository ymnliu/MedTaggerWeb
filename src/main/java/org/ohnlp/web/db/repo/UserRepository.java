package org.ohnlp.web.db.repo;

import org.ohnlp.web.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(int id);

    User findByUsername(String username);
}