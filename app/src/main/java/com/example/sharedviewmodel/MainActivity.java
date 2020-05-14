package com.example.sharedviewmodel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private SharedViewModel sharedViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController);
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
    }

    @Override
    public boolean onSupportNavigateUp() {
        navController.navigateUp();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add_item) {
//            Toast.makeText(this, "Add Item", Toast.LENGTH_SHORT).show();
            addMovie();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addMovie() {
        Movie movie = new Movie("Avenger's", new Random().nextDouble());
        sharedViewModel.addMovie(movie);
    }
}
