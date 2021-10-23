package heli.copter.diplom;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

/**
 * Created by mxn on 2016/12/13.
 * MenuListFragment
 */

public class MenuFragmentList extends Fragment {

    private AppCompatActivity object;
    private ImageView ivMenuUserProfilePhoto;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container,
                false);
        NavigationView vNavigation = view.findViewById(R.id.vNavigation);
        vNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Toast.makeText(getActivity(),menuItem.getTitle(),Toast.LENGTH_SHORT).show();

                switch(menuItem.getTitle().toString()) {
                    case "Объекты":
                        Intent intent = new Intent(object, Obj.class);
                        startActivity(intent);
                        break;
                    case "Типы":
                        Intent intent2 = new Intent(object, Types.class);
                        startActivity(intent2);
                        break;
                    case "Инвентаризации":
                        Intent intent3 = new Intent(object, Inventory.class);
                        startActivity(intent3);
                        break;
                    case "Категории":
                        Intent intent4 = new Intent(object, Categories.class);
                        startActivity(intent4);
                        break;
                    case "Главная страница":
                        Intent intent5 = new Intent(object, SecondActivity.class);
                        startActivity(intent5);
                        break;
                    case "Выход":
                        Intent intent6 = new Intent(object, MainActivity.class);
                        startActivity(intent6);
                        break;
                }


                return false;
            }
        }) ;
        return  view ;
    }

    public MenuFragmentList(AppCompatActivity qwe) {
        this.object = qwe;
    }

}
