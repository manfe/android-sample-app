package br.com.manfe.crud.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.manfe.crud.entities.User;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Query("DELETE FROM user_table")
    void deleteAll();

    @Query("DELETE FROM user_table WHERE uid = :uid")
    void deleteUser(int uid);

    @Query("SELECT * FROM user_table ORDER BY uid ASC")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM user_table where user_email = :email")
    User getUser(String email);

    @Update
    void updateUser(User user);

    @Query("UPDATE user_table set user_email = :email ")
    void updateEmail(String email);
}
