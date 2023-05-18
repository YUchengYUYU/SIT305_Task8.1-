package com.application.week8media_class2.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    User getUser(String username, String password);

    @Insert
    void insertUser(User user);
}

