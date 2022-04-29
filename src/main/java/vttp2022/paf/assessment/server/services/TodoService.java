package vttp2022.paf.assessment.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp2022.paf.assessment.server.models.Task;
import vttp2022.paf.assessment.server.models.User;
import vttp2022.paf.assessment.server.repositories.TaskRepository;

// TODO: fill in this class according to the assessment tasks

@Service
public class TodoService {

    @Autowired
    private TaskRepository tRepo;

    @Autowired
    private UserService uSvc;

    @Transactional(rollbackFor = InsertFailException.class)
    public String upsertTask(List<Task> task, String username) throws Exception {

        Optional<User> opt = uSvc.findUserByUsername(username);
        String userId = null;
        boolean added = false;

        if (opt.isEmpty()) {
            User user = new User();
            user.setUsername(username);
            String name = username.substring(0, 1).toUpperCase() + username.substring(1);
            user.setName(name);
            userId = uSvc.insertUser(user);
        } else {
            User user = opt.get();
            userId = user.getUserId();
        }

        System.out.println(">>>>task.get(i): " + task.get(0));

        try {
            if (userId == null) {
                throw new InsertFailException("Insert fail");
            }
            for (int i = 0; i < task.size(); i++) {
                added = tRepo.insertTask(task.get(i), userId);
                if (added == false) {
                    throw new InsertFailException("Insert fail");
                    
                }
            }
        } catch (Exception ex) {
            throw ex;
        }

        System.out.println(">>>>userId: " + userId);

        return userId;

    }
}
