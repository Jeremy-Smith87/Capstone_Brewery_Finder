package com.techelevator.dao;

import com.techelevator.model.User;

import java.util.List;

public interface UserDao {


    List<User> findAll();

    User getUserById(Long userId);

    User findByUsername(String username);

    long findIdByUsername(String username);

    boolean create(String username, String password, String role, String email);

    boolean updateUserRole(long userId, User userToUpdate);
}
