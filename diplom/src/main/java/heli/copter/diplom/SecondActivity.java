package heli.copter.diplom;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class SecondActivity extends AppCompatActivity {
    public static String FIO = "АААААа СТОП\n ОШИБКА 00000000000\n" +
            "ну это же очевидно! пришло время переустанавливать шЫндовс!";
    private TextView MyName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        this.MyName = (TextView)findViewById(R.id.MyName);
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        if(extras != null && extras.containsKey("FIO")){
            FIO = i.getStringExtra("FIO");
        }
        MyName.setText(FIO);
        setupMenu();
    }
    public SecondActivity(){

    }
    private void setupMenu() {
        FragmentManager fm = getSupportFragmentManager();
        MenuFragmentList mMenuFragment = (MenuFragmentList) fm.findFragmentById(R.id.id_container_menu);
        if (mMenuFragment == null) {
            mMenuFragment = new MenuFragmentList(this);
            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment).commit();
        }
        TextView textName = (TextView)findViewById(R.id.textView11);
    }

}