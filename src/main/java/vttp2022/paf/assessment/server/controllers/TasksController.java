package vttp2022.paf.assessment.server.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vttp2022.paf.assessment.server.models.Task;
import vttp2022.paf.assessment.server.services.TodoService;

// TODO: fill in this class according to the assessment tasks

@Controller
@RequestMapping
public class TasksController {

    @Autowired
    private TodoService tdSvc;

    @PostMapping(path="/task", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView saveToDo(@RequestBody MultiValueMap<String, String> payload) throws Exception{

        List<Task> list = new ArrayList<>();
        Task task = new Task();

        int i = 0;
        while(true){
            String description = payload.getFirst("description-%d".formatted(i));
            if((description == null) || description.trim().length() == 0){
                break;
            }
            Integer priority = Integer.parseInt(payload.getFirst("priority-%d".formatted(i)));

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date dueDate = format.parse(payload.getFirst("dueDate-%d".formatted(i)));
                task.setDueDate(dueDate);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            task.setDescription(description);
            task.setPriority(priority);
            list.add(task);
            i++;
        }
        
        ModelAndView mav = new ModelAndView();

        try {
            tdSvc.upsertTask(list, payload.getFirst("username"));
            mav.setStatus(HttpStatus.OK);
            mav.setViewName("result");
            mav.addObject("taskCount", list.size());
            mav.addObject("username", payload.getFirst("username"));
            return mav;
        } catch (Exception e) {
            e.printStackTrace();   
            mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            mav.setViewName("error");
            return mav;
        }
    }
}
