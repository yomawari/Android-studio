package heli.copter.diplom;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import heli.copter.diplom.danger_zone.ApiApap26Client;

public class InputCat extends AppCompatActivity {

    private Button createcat;
    private TextView category;

    private void setupMenu() {
        FragmentManager fm = getSupportFragmentManager();
        MenuFragmentList mMenuFragment = (MenuFragmentList) fm.findFragmentById(R.id.id_container_menu);
        if (mMenuFragment == null) {
            mMenuFragment = new MenuFragmentList(this);
            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment).commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createc);
        setupMenu();

        category = (TextView) findViewById(R.id.editTextCat);
        createcat = (Button) findViewById(R.id.RdfWf);

        createcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiApap26Client client = ApiApap26Client.getClient();
                new Thread(new Runnable() {
                    public void run() {
                        client.newCategory(category.getText().toString());
                    }
                }).start();
                Toast.makeText(getApplicationContext(),"Данные успешно внесены",Toast.LENGTH_LONG).show();
            }
        });
    }
}