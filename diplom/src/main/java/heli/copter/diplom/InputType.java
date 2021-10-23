package heli.copter.diplom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import heli.copter.diplom.danger_zone.ApiApap26Client;

public class InputType extends AppCompatActivity {

    private TextView name;
    private TextView id;
    private TextView prim;
    private Button createtype;

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
        setContentView(R.layout.createtype);
        setupMenu();

        createtype = (Button)findViewById(R.id.Createtype);
        id = (TextView) findViewById(R.id.editTextID);
        prim = (TextView) findViewById(R.id.editTextPrim);
        name = (TextView) findViewById(R.id.editTextName);

        createtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiApap26Client client = ApiApap26Client.getClient();
                new Thread(new Runnable() {
                    public void run() {
                        String namee = name.getText().toString();
                        int idd = Integer.parseInt(id.getText().toString());
                        String primm = prim.getText().toString();
                        client.newType(namee,idd);
                    }
                }).start();
                Toast.makeText(getApplicationContext(),"Данные успешно внесены",Toast.LENGTH_LONG).show();
            }
        });

    }
}