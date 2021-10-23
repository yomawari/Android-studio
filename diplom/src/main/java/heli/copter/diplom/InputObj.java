package heli.copter.diplom;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import heli.copter.diplom.danger_zone.ApiApap26Client;
import heli.copter.diplom.danger_zone.RequestObject;

public class InputObj extends AppCompatActivity {

    private TextView andNumber;
    private TextView epc;
    private TextView type;
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
        setContentView(R.layout.createo);
        setupMenu();
        andNumber = (TextView) findViewById(R.id.editTextNNum);
        epc = (TextView) findViewById(R.id.editTextEPC);
        type = (TextView) findViewById(R.id.editTextTypeObj);
        create = (Button) findViewById(R.id.buttonCreateObj);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String andNumberr = andNumber.getText().toString();
                String epcc = epc.getText().toString();
                String typee = type.getText().toString();
                ApiApap26Client client = ApiApap26Client.getClient();
                RequestObject requestObject = new RequestObject();
                requestObject.data.InvNumber = andNumberr;
                requestObject.data.TypeID = Integer.parseInt(typee);
                requestObject.data.epc = epcc;
                new Thread(new Runnable() {
                    public void run() {
                        client.newObject(requestObject);
                    }
                }).start();
                Toast.makeText(getApplicationContext(),"Данные успешно внесены",Toast.LENGTH_LONG).show();
            }
        });


    }
}