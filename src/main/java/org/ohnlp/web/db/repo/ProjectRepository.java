package org.ohnlp.web.db.repo;

import org.ohnlp.web.db.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

// CRUD refers Create, Read, Update, Delete
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Project findById(int id);
}