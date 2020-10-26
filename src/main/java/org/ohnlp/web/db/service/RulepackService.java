package org.ohnlp.web.db.service;

import java.util.Date;
import java.util.List;

import org.ohnlp.web.db.entity.Project;
import org.ohnlp.web.db.entity.Rulepack;
import org.ohnlp.web.db.entity.User;
import org.ohnlp.web.db.repo.RulepackRepository;
import org.springframework.stereotype.Service;

@Service
public class RulepackService {
    private RulepackRepository rulepackRepository;

    public RulepackService(RulepackRepository rulepackRepository) {
        this.rulepackRepository = rulepackRepository;
    }

    public List<Rulepack> getRulepackListByUser(User user) {
        List<Rulepack> rulepacks = this.rulepackRepository.findByUserId(user.getId());
        return rulepacks;
    }

    public Rulepack getRulepack(int id) {
        Rulepack rulepack = this.rulepackRepository.findById(id);
        return rulepack;
    }

    public Rulepack setRulepack(int id, String title, String data) {
        Rulepack rulepack = this.rulepackRepository.findById(id);

        // set those new attrs
        rulepack.setTitle(title);
        rulepack.setData(data);
        rulepack.setDateUpdated(new Date());

        // save into db
        this.rulepackRepository.save(rulepack);

        return rulepack;
    }

    public Rulepack createRulepack(User user, Project project, String title, String data) {
        Rulepack rulepack = new Rulepack();

        // set the attributes
        rulepack.setUser(user);
        rulepack.setProject(project);
        rulepack.setTitle(title);
        rulepack.setData(data);
        rulepack.setDateCreated(new Date());
        rulepack.setDateUpdated(new Date());

        // save into db
        this.rulepackRepository.save(rulepack);

        return rulepack;
    }

    public int deleteRulepack(User user, int id) {
        Rulepack rulepack = this.rulepackRepository.findById(id);

        if (rulepack.getUser() == user) {
            this.rulepackRepository.delete(rulepack);
            return 0;
        } else {
            return -1;
        }
    }
}
