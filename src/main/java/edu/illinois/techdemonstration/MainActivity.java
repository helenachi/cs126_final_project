package edu.illinois.techdemonstration;

import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import TVMaze.Show;



public class MainActivity extends AppCompatActivity {

    private MainActivity main;
    public static final String BASE_URL = "http://api.tvmaze.com/shows";
    public List<Show> shows = new ArrayList<>();
    public TVMazeAdapter tvMazeAdapter;
    public TVMazeAsyncTask tvMazeAsyncTask;
    public int selectedNavItem;

//    public TVMazeAsyncTask getTvMazeAsyncTask() {
//        return this.tvMazeAsyncTask;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        main = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //below is from https://stackoverflow.com/questions/2504064/android-vertical-layout-only
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tvMazeAdapter = new TVMazeAdapter(shows);

        final RecyclerView showsList = (RecyclerView) findViewById(R.id.show_list);
        showsList.setAdapter(tvMazeAdapter);
        showsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        tvMazeAsyncTask = new TVMazeAsyncTask(this, tvMazeAdapter, null);
        tvMazeAsyncTask.execute(BASE_URL);

        final BottomNavigationView bottomNav = (BottomNavigationView) findViewById(R.id.navigation);

        //handle nav item click
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectedNavItem = item.getItemId();
                switch (selectedNavItem) {
                    case R.id.navigation_playlist:
                        //setTitle("Playlist");
                        Intent playlist = new Intent(getBaseContext(), PlaylistActivity.class);
                        startActivity(playlist);
                        break;
                    case R.id.navigation_search:
//                        Intent search = new Intent(getBaseContext(), DetailActivity.class);
//                        startActivity(search);
                        RecyclerView showList = (RecyclerView) findViewById(R.id.show_list);
                        showList.smoothScrollToPosition(0);
                        break;
                }
                return true;
            }
        });

    }

}
