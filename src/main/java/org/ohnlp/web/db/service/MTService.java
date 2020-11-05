package org.ohnlp.web.db.service;

import org.ohnlp.web.db.entity.Project;
import org.ohnlp.web.db.entity.User;
import org.ohnlp.web.db.repo.ProjectRepository;
import org.ohnlp.web.db.repo.RulepackRepository;
import org.ohnlp.web.db.repo.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class MTService {

    private UserService userService;
    private ProjectService projectService;
    private RulepackService rulepackService;

    public MTService(
        UserService userService,
        ProjectService projectService,
        RulepackService rulepackService) {
        this.userService = userService;
        this.projectService = projectService;
        this.rulepackService = rulepackService;
    }

    public void createUserAndRelated(String username) {
        User user = this.userService.getOrCreateUser(username);
        Project project = this.projectService.getOrCreateProject(user, user.getUsername());
    }
    
}
