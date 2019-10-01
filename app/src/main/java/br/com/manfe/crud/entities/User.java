package br.com.manfe.crud.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "user_email")
    @NonNull
    private String email;

    @ColumnInfo(name = "user_password")
    @NonNull
    private String password;

    public User(int uid, @NonNull String email, @NonNull String password) {
        this.uid = uid;
        this.email = email;
        this.password = password;
    }

    @Ignore
    public User(@NonNull String email, @NonNull String password) {
        this.email = email;
        this.password = password;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }
}
