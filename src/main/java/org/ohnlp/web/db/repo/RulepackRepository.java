package org.ohnlp.web.db.repo;

import java.util.List;

import org.ohnlp.web.db.entity.Rulepack;
import org.springframework.data.jpa.repository.JpaRepository;

// CRUD refers Create, Read, Update, Delete
public interface RulepackRepository extends JpaRepository<Rulepack, Integer> {
    Rulepack findById(int id);

    List<Rulepack> findByUserId(int id);
}