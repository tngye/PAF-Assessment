package vttp2022.paf.assessment.server;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import vttp2022.paf.assessment.server.controllers.TasksController;
import vttp2022.paf.assessment.server.models.User;
import vttp2022.paf.assessment.server.services.UserService;

@SpringBootTest
public class TestTasksController {

    @Autowired
    private TasksController tCtrl;

    @Autowired
	private UserService uSvc;


    @Test
    void ShouldReturn200(){
        MultiValueMap<String, String> form =  new LinkedMultiValueMap<>();
		form.add("username", "test");
		form.add("dueDate-0", "2022-05-01");
		form.add("description-0", "testing123");
		form.add("priority-0", "3");

        try {
            tCtrl.saveToDo(form);
            assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void teardown(){
        Optional<User> opt = uSvc.findUserByUsername("test");

        if(opt.isPresent()){
            User test = new User();
            test = opt.get();
            uSvc.delete(test.getUserId());
        }



    }


    
}
