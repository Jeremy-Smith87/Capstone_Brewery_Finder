package com.techelevator.dao;

import com.techelevator.model.BreweryBlog;

import java.util.List;

public interface BreweryBlogDao {

    boolean createBlog(BreweryBlog newBreweryBlog);

    List<BreweryBlog> getAllBlogs();

    BreweryBlog getBlogsById(long id);

    boolean updateBlog(long id, BreweryBlog blogToChange);

    boolean deleteBlog(long id);


}
