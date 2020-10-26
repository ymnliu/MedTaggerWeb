package org.ohnlp.web.db.service;

import org.ohnlp.web.db.entity.Project;
import org.ohnlp.web.db.entity.User;
import org.ohnlp.web.db.repo.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    private ProjectRepository projectRepository;
    
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(User user, String title) {
        Project project = new Project();
        
        project.setTitle(title);
        project.setUser(user);

        this.projectRepository.save(project);

        return project;
    }

    public Project getProjectByTitle(User user, String title) {
        Project project = this.projectRepository.findByTitle(title);

        return project;
    }
}
