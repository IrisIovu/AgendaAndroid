package eu.ase.angedasincronizareonline;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import eu.ase.angedasincronizareonline.database.model.User;
import eu.ase.angedasincronizareonline.database.service.UserService;
import eu.ase.angedasincronizareonline.utils.CustomSharedPreferences;

public class LoginActivity extends AppCompatActivity {

    public static final String CURRENT_USER = "currentUser";

    private EditText et_name;
    private EditText et_parola;
    private Button btn_login;
    private TextView tv_inregistrare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        long id = CustomSharedPreferences.getIdFromPreferences(getApplicationContext(),
                RegisterActivity.SHARED_PREF_NAME);
        if (id != -1) {
            getUserByIdFromDb(id);
        }
        setContentView(R.layout.activity_login);
        initComponents();
    }

    private boolean validate() {
        if (et_name == null ||
                et_name.getText().toString().trim().isEmpty()) {
            Toast.makeText(
                    getApplicationContext(),
                    getString(R.string.login_error_empty_username),
                    Toast.LENGTH_LONG).show();

            return false;
        }

        if (et_parola == null ||
                et_parola.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.login_empty_password),
                    Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean checkCredentials(User userFound) {
        if (!et_name.getText().toString().equals(userFound.getName())) {
            Toast.makeText(getApplicationContext(),
                    R.string.login_nume_invalid,
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if (!et_parola.getText().toString().equals(userFound.getParola())) {
            Toast.makeText(getApplicationContext(),
                    R.string.login_parola_invalida,
                    Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void initComponents() {
        et_name = findViewById(R.id.login_name);
        et_parola = findViewById(R.id.login_parola);
        btn_login = findViewById(R.id.btn_login);
        tv_inregistrare = findViewById(R.id.login_tv_register);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    getUserByUsernameFromDb(et_name.getText().toString());
                }
            }

        });

        tv_inregistrare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private void getUserByUsernameFromDb(String username) {
        new UserService.GetOneByUsername(getApplication()) {
            @Override
            protected void onPostExecute(User result) {
                if (result != null) {
                    if (checkCredentials(result)) {
                        CustomSharedPreferences.setIdToPreferences(
                                getApplicationContext(),
                                RegisterActivity.SHARED_PREF_NAME,
                                result.getId());
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra(CURRENT_USER, result);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), R.string.User_negasit, Toast.LENGTH_LONG).show();
                }
            }
        }.execute(username);
    }

    @SuppressLint("StaticFieldLeak")
    private void getUserByIdFromDb(long id) {
        new UserService.GetOneById(getApplication()) {
            @Override
            protected void onPostExecute(User result) {
                if (result != null) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra(CURRENT_USER, result);
                    startActivity(intent);
                }
            }
        }.execute(id);
    }
}
