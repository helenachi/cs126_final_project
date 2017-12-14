package edu.illinois.techdemonstration;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import TVMaze.Show;
import TVShowManager.Playlist;

/**
 * Created by Helena Chi on 11/29/2017.
 */

public class PlaylistActivity extends AppCompatActivity {
    private List<Playlist> playlists;
    public PlaylistAdapter playlistAdapter;
    public int selectedNavItem;
    public FirebaseDatabase mDatabase;

    private Button addButton;
    private Button removeButton;
    private RecyclerView playlistsRV;
    private EditText newPlaylistName;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.playlist_activity);
        playlistAdapter = new PlaylistAdapter(playlists, getApplicationContext());
        //below is from https://stackoverflow.com/questions/2504064/android-vertical-layout-only
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mDatabase = FirebaseDatabase.getInstance();

        newPlaylistName = (EditText) findViewById(R.id.new_playlist_name_tv);
        addButton = (Button) findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = newPlaylistName.getText().toString();
                DatabaseReference mRef = mDatabase.getReference("playlists").push();
                mRef.setValue(new Playlist(name, new ArrayList<Show>()));
                mRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });
            }
        });
        removeButton = (Button) findViewById(R.id.remove_button);

        playlists = new ArrayList<>();
        playlists.add(new Playlist());

        playlistsRV = (RecyclerView) findViewById(R.id.playlist_recyclerView);

        playlistsRV.setAdapter(playlistAdapter);
        playlistsRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        final BottomNavigationView bottomNav = (BottomNavigationView) findViewById(R.id.navigation);
        //handle nav item click
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectedNavItem = item.getItemId();
                switch (selectedNavItem) {
                    case R.id.navigation_playlist:
                        setTitle("Playlist");
                        Intent playlist = new Intent(getBaseContext(),PlaylistActivity.class);
                        startActivity(playlist);
                        break;
                    case R.id.navigation_search:
                        setTitle("Search");
                        Intent search = new Intent(getBaseContext(),DetailActivity.class);
                        startActivity(search);
                        break;
                }
                return true;
            }
        });
    }

    public void updatePlaylistsView()
    {

    }
}
