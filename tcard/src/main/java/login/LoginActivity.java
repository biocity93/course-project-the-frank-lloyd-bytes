package login;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import dataBase.DBHelper;

public class LoginActivity extends AppCompatActivity{
    EditText username, password;
    Button register, login;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        username = findViewById(R.id.userNameInput);
        password = findViewById(R.id.passwordInput);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        DB = new DBHelper(this);

        register.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
        });
        login.setOnClickListener(view -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();
            if (user.equals("")||pass.equals("")){
                Toast.makeText(LoginActivity.this, "Please enter your username and password", Toast.LENGTH_SHORT).show();
            }
            else {
                Boolean checkUserPass = DB.checkusernamepassword(user, pass);
                if (checkUserPass) {
                    Intent intent2 = new Intent(getApplicationContext(), DashBoardActivity.class);
                    startActivity(intent2);
                }
                else{
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
