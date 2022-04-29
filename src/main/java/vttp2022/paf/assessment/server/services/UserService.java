package vttp2022.paf.assessment.server.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.paf.assessment.server.models.User;
import vttp2022.paf.assessment.server.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository uRepo;
    
    public Optional<User> findUserByUserId(String userId){
        Optional<User> opt = uRepo.findUserByUserId(userId);
        if(opt.isEmpty()){
            return Optional.empty();
        }
        return opt;
    }

    public Optional<User> findUserByUsername(String username){
        Optional<User> opt = uRepo.findUserByUsername(username);
        if(opt.isEmpty()){
            return Optional.empty();
        }
        return opt;
    }


    public String insertUser(User user){
        String userId = UUID.randomUUID().toString().substring(0, 8);
        user.setUserId(userId);

        if(uRepo.insertUser(user)){
            return userId;
        }
        return userId;
    }

    public boolean delete(String userId) {
        boolean deleted = uRepo.delete(userId);
        return deleted;
    }
}
