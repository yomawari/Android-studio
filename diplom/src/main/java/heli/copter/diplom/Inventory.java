package heli.copter.diplom;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import heli.copter.diplom.danger_zone.ApiApap26Client;

public class Inventory extends AppCompatActivity {

    String parse = "";
    String dparse = "";

    private Button CreateInventory;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory);

        CreateInventory = (Button) findViewById(R.id.CreateInventory);
        CreateInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateeInventory();
            }
        });
        setupMenu();

        new Thread(new Runnable() {
            public void run() {
                ApiApap26Client client = ApiApap26Client.getClient();
                String result = client.getInventories(0, 100);
                try {
                    JSONObject jObject = new JSONObject(result);
                    int aJsonInteger = jObject.getInt("status");
                    if (aJsonInteger == 200) {
                        JSONArray jArray = jObject.getJSONArray("data");
                        for (int i=0; i < jArray.length(); i++)
                        {
                            try {
                                JSONObject oneObject = jArray.getJSONObject(i);
                                parse = "\n" + "ID: " + oneObject.get("id") + "\n" +
                                        "Тип: " + oneObject.get("dateTime") + "\n" +
                                        "Место: " + oneObject.getJSONObject("place").get("address") + "\n" + "----------";

                                dparse = dparse + parse;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        TextView textView1 = findViewById(R.id.textView1);
                                        textView1.setText(dparse);
                                    }
                                });
                            }
                            catch (JSONException e) {
                                // обработка исключений на случай не нахождения конкретного ключа
                            }

                        }
                    }
                    else {
                        Log.e("TAGGGG", "onCreate: " + result);
                    }

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("TAGGGG", "onCreate: " + result);
            }
        }).start();
    }

    private void openCreateeInventory() {
        Intent intent = new Intent(this,InputInv.class);
        startActivity(intent);
    }

    private void setupMenu() {
        FragmentManager fm = getSupportFragmentManager();
        MenuFragmentList mMenuFragment = (MenuFragmentList) fm.findFragmentById(R.id.id_container_menu);
        if (mMenuFragment == null) {
            mMenuFragment = new MenuFragmentList(this);
            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment).commit();
        }
    }
}