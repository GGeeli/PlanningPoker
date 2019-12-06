package com.example.scrumproject;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArraySet;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import java.util.Set;


public class NavigationDrawerActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private AppBarConfiguration appBarConfig;
    private NavController navController;
    private Toolbar toolbar;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_nav_drawer);
            toolbar =findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            drawerLayout = findViewById(R.id.drawer_layout);

            final Set<Integer> topLevelDestinations = new ArraySet<>();
            topLevelDestinations.add(R.id.nav_home);
            topLevelDestinations.add(R.id.nav_create_group);
            topLevelDestinations.add(R.id.nav_create_question);
            topLevelDestinations.add(R.id.nav_view_question);
            topLevelDestinations.add(R.id.nav_join_group);
            topLevelDestinations.add(R.id.nav_join_question);

            appBarConfig = new AppBarConfiguration.Builder(topLevelDestinations)
                    .setDrawerLayout(drawerLayout)
                    .build();

            navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig);
            NavigationUI.setupWithNavController(this.<NavigationView>findViewById(R.id.nav_view), navController);

        }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfig) || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
