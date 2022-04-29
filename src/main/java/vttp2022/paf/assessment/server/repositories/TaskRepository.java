package vttp2022.paf.assessment.server.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.server.models.Task;

import static vttp2022.paf.assessment.server.repositories.Queries.*;

// TODO: fill in this class according to the assessment tasks

@Repository
public class TaskRepository {

    @Autowired
    private JdbcTemplate template;
    
    public boolean insertTask(Task task, String userId){
        int added = template.update(SQL_INSERT_TASK, task.getDescription(), task.getPriority(), task.getDueDate(), userId);
        System.out.println(">>>>added: " + added);
        return added >0;
    }
}
