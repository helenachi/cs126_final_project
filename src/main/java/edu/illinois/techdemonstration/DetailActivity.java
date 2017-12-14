package edu.illinois.techdemonstration;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import TVMaze.Image;
import TVMaze.Show;

/**
 * Created by Helena Chi on 12/1/2017.
 */

public class  DetailActivity extends AppCompatActivity{

    private ImageView image;
    private TextView name;
    private TextView genre;
    private TextView rating;
    private TextView summary;
    private int selectedNavItem;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        //below is from https://stackoverflow.com/questions/2504064/android-vertical-layout-only
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Intent intent = getIntent();
        Show show = intent.getParcelableExtra(TVMazeAdapter.TVSHOW);


        image = (ImageView) findViewById(R.id.show_imageView);
        name = (TextView) findViewById(R.id.title_year_textView);
        genre = (TextView) findViewById(R.id.genre_textView);
        String[] genres = show.getGenres();
        String genreList = TVMazeAdapter.getGenreList(genres);
        genre.setText(genreList);
        rating = (TextView) findViewById(R.id.rating_textView);
        summary = (TextView) findViewById(R.id.summary_textView);
        final BottomNavigationView bottomNav = (BottomNavigationView) findViewById(R.id.navigation);


        String titleText = show.getName() + " (" + show.getPremiered().substring(0,4) + ")";
        name.setText(titleText);
        rating.setText("rating: " + String.valueOf(show.getRating().getAverage()) + " / 10");
        summary.setText(show.getSummary());
        summary.setMovementMethod(new ScrollingMovementMethod());
        final String imgURL = show.getImage().getMedium();
        if (imgURL != null) {
            Picasso.with(image.getContext()).load(imgURL).into(image);
        }


        //handle nav item click
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectedNavItem = item.getItemId();
                switch (selectedNavItem) {
                    case R.id.navigation_playlist:
                        //setTitle("Playlist");
                        Intent playlist = new Intent(getBaseContext(),PlaylistActivity.class);
                        startActivity(playlist);
                        break;
                    case R.id.navigation_search:
                        Intent search = new Intent(getBaseContext(),MainActivity.class);
                        startActivity(search);
                        break;
                }
                return true;
            }
        });

    }

}
