package br.com.manfe.crud.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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

        userAdapter = new UserRecyclerViewAdapter(new ArrayList<>());
        mUserRecycler.setAdapter(userAdapter);

        // ADICIONADO LIVEDATA (OBSERVER) NOS DADOS, ASSIM QUANDO HOUVER QUALQUER
        // ALTERAÇÃO VAI ATUALIZAR O ADAPTER, QUE IRÁ ATUALIZAR A ACTIVITY
        // NOTA IMPORTANTE: QUANDO ADICIONADO O MÉTODO OBSERVE NÃO É NECESSÁRIO
        // ESTAR DENTRO DE UMA THREAD, TAMBÉM NÃO SENDO NECESSÁRIO O USO DO onResume()
        AppRoomDatabase.getDatabase(getBaseContext()).userDAO().getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                userAdapter.setmUsers(users);
                Snackbar snackbar = Snackbar.make(findViewById(R.id.mainActivityConstraintLayout), "Lista atualizada.", Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });

        mFab.setOnClickListener((view) -> {
            Intent intent = new Intent(this, NewUserActivity.class);
            startActivity(intent);
        });





    }
}
