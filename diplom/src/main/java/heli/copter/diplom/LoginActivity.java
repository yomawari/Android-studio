package heli.copter.diplom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

        private EditText textLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textLogin = findViewById(R.id.TextLogin);

        findViewById(R.id.buttonLog).setOnClickListener(this);
    }

    private void userLogin(){
        String login = textLogin.getText().toString().trim();
        }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonLog:
                userLogin();
                break;

        }
    }
}