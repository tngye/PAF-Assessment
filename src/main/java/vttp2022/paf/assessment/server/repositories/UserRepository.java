package vttp2022.paf.assessment.server.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.server.models.User;

import static vttp2022.paf.assessment.server.repositories.Queries.*;

// TODO: fill in this class according to the assessment tasks

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate template;

    public Optional<User> findUserByUserId(String userId) {

        final SqlRowSet result = template.queryForRowSet(SQL_SELECT_USER_BY_ID, userId);
        
        if (!result.next()){
            return Optional.empty();
        }
        return Optional.of(User.create(result));
    }

    public boolean insertUser(User user) {
        int count = template.update(SQL_INSERT_USER, user.getUserId(), user.getUsername(), user.getName());
        return count == 1;
    }

    public Optional<User> findUserByUsername(String username) {
        final SqlRowSet result = template.queryForRowSet(SQL_SELECT_USER_BY_USERNAME, username);
        
        if (!result.next()){
            return Optional.empty();
        }
        return Optional.of(User.create(result));
    }

    public boolean delete(String userId) {
        System.out.println(">>>molly: " + userId);
        int added = template.update(SQL_DELETE_BY_USERID, userId);
        return added > 0;
    }
}
