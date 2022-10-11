package com.techelevator.controller;

import com.techelevator.controller.exceptions.BlogNotFoundException;
import com.techelevator.controller.exceptions.BreweryNotFoundException;
import com.techelevator.controller.exceptions.NotAuthorizedException;
import com.techelevator.model.Breweries;
import com.techelevator.model.BreweryBlog;
import com.techelevator.service.BreweryBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class BreweryBlogController {

    @Autowired
    private BreweryBlogService blogService;

    public BreweryBlogController(BreweryBlogService blogService) {
        this.blogService = blogService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/addBlog")
    public boolean addBlog(@Valid @RequestBody BreweryBlog blog) {
        return blogService.createBlog(blog);
    }


    @PermitAll
    @RequestMapping(value = "/breweryBlogs", method = RequestMethod.GET)
    public List<BreweryBlog> getAllBlogs() {
        return blogService.returnAllBlogs();
    }

    @PermitAll
    @RequestMapping(value = "/breweryBlog/{blogId}", method = RequestMethod.GET)
    public BreweryBlog getBlogsById(@PathVariable long blogId) throws BlogNotFoundException {
        return blogService.returnBlogById(blogId);
    }


    @PreAuthorize("hasRole('ROLE_BREWER')")
    @RequestMapping(path = "/blog/{blogId}", method = RequestMethod.PUT)
    public boolean updateBlog(@PathVariable long blogId, @Valid @RequestBody BreweryBlog blogToChange, Principal principal) throws NotAuthorizedException {
        return blogService.checkBlogUpdate(blogId, blogToChange, principal);
    }

    @PreAuthorize("hasRole('ROLE_BREWER')")
    @RequestMapping(path = "/blog/{blogId}", method = RequestMethod.DELETE)
    public boolean deleteABlog(@PathVariable Long blogId, Principal principal) throws NotAuthorizedException {
        if (blogService.returnBlogById(blogId) != null) {
            return blogService.checkBlogDelete(blogId, principal);
        }
        return false;
    }


}
