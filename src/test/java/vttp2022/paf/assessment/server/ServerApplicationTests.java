package vttp2022.paf.assessment.server;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vttp2022.paf.assessment.server.models.Task;
import vttp2022.paf.assessment.server.models.User;
import vttp2022.paf.assessment.server.services.TodoService;
import vttp2022.paf.assessment.server.services.UserService;

// TODO: fill in this class according to the assessment tasks

@SpringBootTest
class ServerApplicationTests {

	@Autowired
	private TodoService tdSvc;

	@Autowired
	private UserService uSvc;

	private List<Task> list = new ArrayList<>(); 
	private String mollyId;

	public ServerApplicationTests(){

		Task task = new Task();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		task.setDescription("test");
		task.setPriority(1);
		try {
			task.setDueDate(format.parse("2022-03-31"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		list.add(task);
	}

	@Test
	void upsertShouldReturnUserId() throws Exception{
		String username = "fred";
		String userid = tdSvc.upsertTask(list, username);
		assertEquals("1b80114c", userid, "expected %s. got %s.".formatted("1b80114c", userid));
	}

	@Test
	void ShouldCreateNewUser() throws Exception{
		String username ="molly";
		mollyId = tdSvc.upsertTask(list, username);
		Optional<User> finduser = uSvc.findUserByUserId(mollyId);
		User user = new User();
		String finduserId = null;
		if(finduser.isPresent()){
			user= finduser.get();
			finduserId = user.getUserId(); 
		}
		assertEquals(mollyId, finduserId, "expected %s. got %s.".formatted(mollyId, finduserId));
	}

	@AfterEach
	void teardown(){
		uSvc.delete(mollyId);
	}

}
