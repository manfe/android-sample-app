package br.com.manfe.crud.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import br.com.manfe.crud.entities.User;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Query("DELETE FROM user_table")
    void deleteAll();

    @Query("DELETE FROM user_table WHERE email = :email")
    void deleteUser(String email);

    @Query("SELECT * FROM user_table ORDER BY email ASC")
    List<User> getAllUsers();

    @Query("SELECT * FROM user_table where uid = :email")
    User getUser(String email);
}
