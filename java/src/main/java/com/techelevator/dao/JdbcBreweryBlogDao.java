package com.techelevator.dao;

import com.techelevator.controller.exceptions.BlogNotFoundException;
import com.techelevator.model.BreweryBlog;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBreweryBlogDao implements BreweryBlogDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcBreweryBlogDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //needs review
    @Override
    public boolean createBlog(BreweryBlog newBreweryBlog) {
        String sql = "INSERT INTO breweryBlog(blog_id, brewery_id, title, body, create_date) VALUES (DEFAULT, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, newBreweryBlog.getBlogId(), newBreweryBlog.getBreweryId(), newBreweryBlog.getTitle(), newBreweryBlog.getBody(), newBreweryBlog.getCreateDate()) == 1;
    }

    @Override
    public List<BreweryBlog> getAllBlogs() {
        List<BreweryBlog> blogs = new ArrayList<>();
        String sql = "SELECT * FROM breweryBlog";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            BreweryBlog blog = mapRowToBreweryBlog(results);
            blogs.add(blog);
        }
        return blogs;
    }


    @Override
    public BreweryBlog getBlogsById(long id) {
        BreweryBlog blog = null;
        String sql = "SELECT * FROM brewery_blog WHERE blog_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            blog = mapRowToBreweryBlog(results);
        } else {
            throw new BlogNotFoundException();
        }
        return blog;
    }

    @Override
    public boolean updateBlog(long blogId, BreweryBlog blogToChange) {
        String sql = "UPDATE brewery_blog SET title = ?, body = ?, create_date = ? WHERE blog_id = ?";
        return jdbcTemplate.update(sql, blogToChange.getTitle(), blogToChange.getBody(), blogToChange.getCreateDate(), blogId) == 1;
    }

    @Override
    public boolean deleteBlog(long id) {
        String sql = "DELETE FROM brewery_blog WHERE blog_id = ?";
        return jdbcTemplate.update(sql, id) == 1;
    }

    private BreweryBlog mapRowToBreweryBlog(SqlRowSet rs) {
        BreweryBlog blog = new BreweryBlog();
        blog.setBlogId(rs.getLong("blog_id"));
        blog.setBreweryId(rs.getLong("brewery_id"));
        blog.setTitle(rs.getString("title"));
        blog.setBody(rs.getString("body"));
        if (rs.getDate("create_date") != null) {
            blog.setCreateDate(rs.getDate("create_date").toLocalDate());
        }
        return blog;
    }


}
