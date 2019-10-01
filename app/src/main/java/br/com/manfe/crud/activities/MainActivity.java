package br.com.manfe.crud.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
    UserRecyclerViewAdapter userAdapter;
    FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserRecycler = findViewById(R.id.recycler_users);
        mFab = findViewById(R.id.fab_new_user);

        User u1 = new User("teste12@teste.com", "1235");

        userAdapter = new UserRecyclerViewAdapter(new ArrayList<>());
        mUserRecycler.setAdapter(userAdapter);

        new Thread(() -> {
            AppRoomDatabase.getDatabase(getBaseContext()).userDAO().insert(u1);
            List<User> users = AppRoomDatabase.getDatabase(getBaseContext()).userDAO().getAllUsers();

            runOnUiThread(() -> {
                userAdapter.setmUsers(users);
                userAdapter.notifyDataSetChanged();
            });
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();

        new Thread(() -> {
            List<User> users = AppRoomDatabase.getDatabase(getBaseContext()).userDAO().getAllUsers();

            // COMO A THREAD NÃO PODE MODIFICAR A VIEW, É UTILIZADO A PRÓPRIA THREAD DA UI
            // QUE É ESSA ABAIXO, DESSA FORMA CHAMAMOS A THREAD DA UI DENTRO DA THREAD QUE
            // EXECUTOU O PROCESSO DE CONEXÃO COM O BANCO.
            runOnUiThread(() -> {
                userAdapter.setmUsers(users);
                userAdapter.notifyDataSetChanged();
            });

        }).start();

    }
}
