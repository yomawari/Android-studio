package heli.copter.diplom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import heli.copter.diplom.danger_zone.ApiApap26Client;
import heli.copter.diplom.danger_zone.RequestObject;

public class InputInv extends AppCompatActivity {

    private TextView number;
    private Button create;

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
        setContentView(R.layout.createi);
        setupMenu();

        number = (TextView) findViewById(R.id.editTextNumber);
        create = (Button) findViewById(R.id.buttonCreateInv);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numberr = number.getText().toString();
                ApiApap26Client client = ApiApap26Client.getClient();
                RequestObject requestObject = new RequestObject();
                requestObject.data.TypeID = Integer.parseInt(numberr);
                new Thread(new Runnable() {
                    public void run() {
                        client.newInventory(2);
                    }
                }).start();
                Toast.makeText(getApplicationContext(),"Данные успешно внесены",Toast.LENGTH_LONG).show();
            }
        });
    }
}