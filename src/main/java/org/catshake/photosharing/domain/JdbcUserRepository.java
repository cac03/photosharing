package org.catshake.photosharing.domain;

import lombok.AllArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class JdbcUserRepository implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public User saveUser(User user) {
        Assert.notNull(user, "user == null");
        Assert.isTrue(user.getId() == null, "id must be null");

        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(
                    "insert into users(username, password_hash) values (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPasswordHash());
            return preparedStatement;
        }, generatedKeyHolder);
        user.setId(generatedKeyHolder.getKey().longValue());
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE username = ?",
                ps -> ps.setString(1, username), (rs, rowNum) -> getUser(rs));
        User user = DataAccessUtils.singleResult(users);

        return Optional.ofNullable(user);
    }

    private User getUser(ResultSet rs) throws SQLException {
        String usernameFromResultSet = rs.getString("username");
        String passwordHash = rs.getString("password_hash");
        long id = rs.getLong("id");
        return new User(id, usernameFromResultSet, passwordHash);
    }
}
