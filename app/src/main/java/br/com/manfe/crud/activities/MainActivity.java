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

public class MainActivity extends AppCompatActivity {

    RecyclerView mUserRecycler;
    FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserRecycler = findViewById(R.id.recycler_users);
        mFab = findViewById(R.id.fab_new_user);

        System.out.println("Testy" + mUserRecycler);


        User u1 = new User("teste@teste.com", "1235");
        User u2 = new User("teste@teste.com", "1235");

        List<User> users = new ArrayList<User>();
        users.add(u1);
        users.add(u2);

        UserRecyclerViewAdapter userAdapter = new UserRecyclerViewAdapter(users);

        mUserRecycler.setAdapter(userAdapter);

    }
}
