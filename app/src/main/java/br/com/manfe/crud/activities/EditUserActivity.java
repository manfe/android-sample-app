package br.com.manfe.crud.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.manfe.crud.R;
import br.com.manfe.crud.entities.User;
import br.com.manfe.crud.utils.AppRoomDatabase;

public class EditUserActivity extends AppCompatActivity {

    EditText mEditEmail;
    EditText mEditPassword;
    Button mBtnSalvar;
    Button mBtnCancelar;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        mEditEmail = findViewById(R.id.editTextEmail);
        mEditPassword = findViewById(R.id.editTextPassword);
        mBtnSalvar = findViewById(R.id.btnSalvar);
        mBtnCancelar = findViewById(R.id.btnCancelar);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        new Thread(() -> {
            user = AppRoomDatabase.getDatabase(getBaseContext()).userDAO().getUser(email);
            mEditEmail.setText(user.getEmail());
            mEditPassword.setText(user.getPassword());
        }).start();


        mBtnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEditEmail.getText().toString();
                String password = mEditPassword.getText().toString();

                user.setEmail(email);
                user.setPassword(password);

                new AsyncTask<Void, Void, Boolean>() {

                    @Override
                    protected Boolean doInBackground(Void... voids) {
                        AppRoomDatabase.getDatabase(getBaseContext()).userDAO().updateUser(user);
                        return true;
                    }

                    @Override
                    protected void onPostExecute(Boolean aBoolean) {
                        super.onPostExecute(aBoolean);
                        finish();
                    }
                }.execute();
            }
        });





    }
}
