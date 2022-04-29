package vttp2022.paf.assessment.server.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.util.MultiValueMap;

// Do not change this class

public class User {

	private String userId;
	private String username;
	private String name;

	public User() { }

	public String getUserId() { return this.userId; }
	public void setUserId(String userId) { this.userId = userId; }

	public String getUsername() { return this.username; }
	public void setUsername(String username) { this.username = username; }

	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }

	@Override
	public String toString() {
		return "user_id: %s, username: %s, name: %s"
				.formatted(userId, username, name);
	}

    public static User create(SqlRowSet result) {
		User user = new User();
		user.setUserId(result.getString("user_id"));
		user.setUsername(result.getString("username"));
		user.setName(result.getString("name"));
        return user;
    }

	public User create(MultiValueMap<String, String> payload) {
		User user = new User();
		user.setUserId(payload.getFirst("user_id"));
		user.setUsername(payload.getFirst("username"));
		user.setName(payload.getFirst("name"));
        return user;
	}
}
