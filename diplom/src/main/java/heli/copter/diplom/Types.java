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

public class Types extends AppCompatActivity {

    String parse = "";
    String dparse = "";
    private Button type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.types);
        setupMenu();

        type = (Button)findViewById(R.id.createtype);
        type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateeType();
            }
        });

       // Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl(Api.BASE_URL)
                //.addConverterFactory(GsonConverterFactory.create())
               // .build();

        //Api api = retrofit.create(Api.class);

        //Call<List<Hero>> call = api.getHeroes();

        //call.enqueue(new Callback<List<Hero>>() {
            //@Override
            //public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                //List<Hero> heroes = response.body();

                //for(Hero h: heroes){
                   // Log.d("name",h.getName());
                   // Log.d("realname",h.getRealname());
                   // Log.d("imageurl",h.getImageurl());
               // }
           // }

           // @Override
           // public void onFailure(Call<List<Hero>> call, Throwable t) {
              //  Toast.makeText(getApplicationContext(), t.getMessage(),Toast.LENGTH_SHORT).show();
           // }
       // });

        new Thread(new Runnable() {
            public void run() {
                ApiApap26Client client = ApiApap26Client.getClient();
                String result = client.getTypes(0,100);
                try {
                    JSONObject jObject = new JSONObject(result);
                    int aJsonInteger = jObject.getInt("status");
                    if (aJsonInteger == 200) {
                        JSONArray jArray = jObject.getJSONArray("data");
                        for (int i=0; i < jArray.length(); i++)
                        {
                            try {
                                JSONObject oneObject = jArray.getJSONObject(i);
                                parse = "\n" + "ID: " + oneObject.get("id") + "\n"+
                                        "Имя: " + oneObject.get("name") + "\n"+
                                        "Номер: " + oneObject.get("onesNumber") + "\n" +
                                        "ID Категории: " + oneObject.get("categoryID") + "\n"+
                                        "Категории: " + oneObject.get("category") + "\n"+
                                        "Примечание: " + oneObject.get("desk_text") + "\n" + "----------";

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

    private void openCreateeType() {
        Intent intent = new Intent(this,InputType.class);
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