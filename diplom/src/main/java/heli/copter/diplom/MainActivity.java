package heli.copter.diplom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView loginField;
    private TextView passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.buttonLog);
        loginField = (TextView)findViewById(R.id.TextLogin);
        passwordField = (TextView)findViewById(R.id.TextPassword);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = loginField.getText().toString();
                String password = passwordField.getText().toString();
                if(login.equals("admin") && password.equals("123")){
                    main_menu("Ситников\nАлександр\nРоль: Администратор");
                }else if(login.equals("operator") && password.equals("321")){
                    main_menu("Альтернативных\nАлексей\nРоль: Сотрудник класса D");
                }else{
                    Toast.makeText(getApplicationContext(),"Неверный логин или пароль",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void main_menu(String FIO) {
        Intent intent = new Intent(this, SecondActivity.class);
        if(!FIO.equals("")){
            intent.putExtra("FIO", FIO);
        }
        startActivity(intent);
    }

}