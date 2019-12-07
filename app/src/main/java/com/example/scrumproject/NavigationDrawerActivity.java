package com.example.scrumproject;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.collection.ArraySet;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.scrumproject.Database.UserRight;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Set;


public class NavigationDrawerActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private AppBarConfiguration appBarConfig;
    private NavController navController;
    private Toolbar toolbar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    TextView nav_user, nav_email;
    ImageView image;

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

            String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference("UserRight").child(userID);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    UserRight userRight = new UserRight();
                    userRight = dataSnapshot.getValue(UserRight.class);
                    if (userRight.isAdmin()) {
                        NavigationView view = findViewById(R.id.nav_view);
                        Menu menuNav = view.getMenu();

                        MenuItem home = menuNav.findItem(R.id.nav_home);
                        home.setVisible(true);
                        MenuItem create_group = menuNav.findItem(R.id.nav_create_group);
                        create_group.setVisible(true);
                        MenuItem create_question = menuNav.findItem(R.id.nav_create_question);
                        create_question.setVisible(true);
                        MenuItem view_question = menuNav.findItem(R.id.nav_view_question);
                        view_question.setVisible(true);
                        MenuItem join_group = menuNav.findItem(R.id.nav_join_group);
                        join_group.setVisible(false);
                        MenuItem join_question = menuNav.findItem(R.id.nav_join_question);
                        join_question.setVisible(false);
                    } else {
                        NavigationView view = findViewById(R.id.nav_view);
                        Menu menuNav = view.getMenu();

                        MenuItem home = menuNav.findItem(R.id.nav_home);
                        home.setVisible(false);
                        MenuItem create_group = menuNav.findItem(R.id.nav_create_group);
                        create_group.setVisible(false);
                        MenuItem create_question = menuNav.findItem(R.id.nav_create_question);
                        create_question.setVisible(false);
                        MenuItem view_question = menuNav.findItem(R.id.nav_view_question);
                        view_question.setVisible(false);
                        MenuItem join_group = menuNav.findItem(R.id.nav_join_group);
                        join_group.setVisible(true);
                        MenuItem join_question = menuNav.findItem(R.id.nav_join_question);
                        join_question.setVisible(true);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerview = navigationView.getHeaderView(0);

        nav_user = headerview.findViewById(R.id.header_username);
        nav_user.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        nav_email = headerview.findViewById(R.id.header_email);
        nav_email.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        image = headerview.findViewById(R.id.imageView);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

    }

    public void logout()
    {
        mAuth.getInstance().signOut();
        startActivity(new Intent(NavigationDrawerActivity.this, MainActivity.class));
        finish();
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
