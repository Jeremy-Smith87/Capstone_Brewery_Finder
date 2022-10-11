package com.techelevator.service;

import com.techelevator.controller.exceptions.BlogNotFoundException;
import com.techelevator.controller.exceptions.NotAuthorizedException;
import com.techelevator.dao.BreweriesDao;
import com.techelevator.dao.BreweryBlogDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Breweries;
import com.techelevator.model.BreweryBlog;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Service
public class BreweryBlogService {

    @Autowired
    BreweryBlogDao blogDao;

    @Autowired
    UserDao userDao;

    @Autowired
    BreweriesDao breweriesDao;

    public boolean createBlog(BreweryBlog blog) {
        boolean result = false;
        blog.setCreateDate(LocalDate.now());

        try {
            blogDao.createBlog(blog);
            result = true;
        } catch (BlogNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public List<BreweryBlog> returnAllBlogs() {
        return blogDao.getAllBlogs();
    }

    public BreweryBlog returnBlogById(long id) {
        return blogDao.getBlogsById(id);
    }

    public boolean checkBlogUpdate(long blogId, BreweryBlog blogToChange, Principal principal) {
        blogToChange.setCreateDate(LocalDate.now());
        String username = principal.getName();
        long currentId = userDao.findIdByUsername(username);
        User user = userDao.getUserById(currentId);
        Breweries brewer = breweriesDao.getBreweryByUserId(currentId);
        if (blogToChange.getBreweryId() == brewer.getBreweryId()) {
            return blogDao.updateBlog(blogId, blogToChange);
        } else return false;
    }

    public boolean checkBlogDelete(long blogId, Principal principal) {
        String username = principal.getName();
        long currentId = userDao.findIdByUsername(username);
        User user = userDao.getUserById(currentId);
        BreweryBlog blogToDelete = returnBlogById(blogId);
        Breweries brewer = breweriesDao.getBreweryByUserId(currentId);

        if (blogToDelete.getBreweryId() == brewer.getBreweryId() || user.getRole().equals("ROLE_ADMIN")) {
            return blogDao.deleteBlog(blogId);
        } else return false;
    }

}
