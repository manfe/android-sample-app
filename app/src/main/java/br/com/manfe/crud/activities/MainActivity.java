package br.com.manfe.crud.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.com.manfe.crud.R;
import br.com.manfe.crud.adapters.UserRecyclerViewAdapter;
import br.com.manfe.crud.entities.User;
import br.com.manfe.crud.utils.AppRoomDatabase;

public class MainActivity extends AppCompatActivity {

    RecyclerView mUserRecycler;
    FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserRecycler = findViewById(R.id.recycler_users);
        mFab = findViewById(R.id.fab_new_user);

        User u1 = new User("teste5@teste.com", "1235");
        User u2 = new User("teste6@teste.com", "1235");

        AppRoomDatabase.getDatabase(getBaseContext()).userDAO().insert(u1);
        AppRoomDatabase.getDatabase(getBaseContext()).userDAO().insert(u2);

        List<User> users = AppRoomDatabase.getDatabase(getBaseContext()).userDAO().getAllUsers();

        UserRecyclerViewAdapter userAdapter = new UserRecyclerViewAdapter(users);

        mUserRecycler.setAdapter(userAdapter);

    }
}
