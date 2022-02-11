package com.geektech.apple1group39;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.geektech.apple1group39.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,R.id.profileFragment)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        navController.navigate(R.id.onBoardFragment);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            List<Integer> fragments = new ArrayList<>();
            fragments.add(R.id.navigation_home);
            fragments.add(R.id.navigation_dashboard);
            fragments.add(R.id.navigation_notifications);
            fragments.add(R.id.profileFragment);
            if (fragments.contains(destination.getId())){
                binding.navView.setVisibility(View.VISIBLE);
            }else {
                binding.navView.setVisibility(View.GONE);
            }
            if (destination.getId()==R.id.onBoardFragment){
                getSupportActionBar().hide();
            }else{
                getSupportActionBar().show();
            }
        });


    }

}