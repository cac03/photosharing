package org.catshake.photosharing.domain;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

@Repository
@AllArgsConstructor
public class JdbcPostRepository implements PostRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Post savePost(Post post) {
        Assert.notNull(post, "post == null");
        Assert.isTrue(post.getId() == null, "id must be null");


        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(
                    "insert into posts(posted_at, posted_by_user_id, filename, description) values (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setTimestamp(1, Timestamp.from(post.getDate()));
            preparedStatement.setLong(2, post.getUserId());
            preparedStatement.setString(3, post.getFilename());
            preparedStatement.setString(4, post.getDescription());
            return preparedStatement;
        }, generatedKeyHolder);
        post.setId(generatedKeyHolder.getKey().longValue());
        return post;
    }

    @Override
    public List<Post> findAll() {
        List<Post> posts = jdbcTemplate.query("SELECT * FROM posts ORDER BY posted_at DESC",
                (resultSet, i) -> {

                    Post post = new Post();
                    post.setId(resultSet.getLong("id"));
                    post.setDate(resultSet.getTimestamp("posted_at").toInstant());
                    post.setUserId(resultSet.getLong("posted_by_user_id"));
                    post.setFilename(resultSet.getString("filename"));
                    post.setDescription(resultSet.getString("description"));

                    return post;
                });
        return posts;
    }
}
