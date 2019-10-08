package br.com.manfe.crud.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.manfe.crud.R;
import br.com.manfe.crud.entities.User;
import br.com.manfe.crud.utils.AppRoomDatabase;

public class NewUserActivity extends AppCompatActivity {

    EditText mEditEmail;
    EditText mEditPassword;
    Button mBtnSalvar;
    Button mBtnCancelar;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);


        mEditEmail = findViewById(R.id.editTextNewUserEmail);
        mEditPassword = findViewById(R.id.editTextNewUserPassword);
        mBtnSalvar = findViewById(R.id.btnNewUserSalvar);
        mBtnCancelar = findViewById(R.id.btnNewUserCancelar);

        // MODO JAVA antigo para setar uma nova classe anônima para o botão
        mBtnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEditEmail.getText().toString();
                String password = mEditPassword.getText().toString();

                User user = new User(email, password);


                new AsyncTask<Void, Void, Boolean>() {

                    @Override
                    protected Boolean doInBackground(Void... voids) {
                        AppRoomDatabase.getDatabase(getBaseContext()).userDAO().insert(user);
                        return true;
                    }

                    @Override
                    protected void onPostExecute(Boolean aBoolean) {
                        super.onPostExecute(aBoolean);
                        finish();
                    }
                }.execute();

                finish();
            }
        });

        // MODO NOVO E MAIS LIMPO, PORÉM AMBOS FUNCIONAM.
        mBtnCancelar.setOnClickListener((view) -> {
            finish();
        });

    }
}
