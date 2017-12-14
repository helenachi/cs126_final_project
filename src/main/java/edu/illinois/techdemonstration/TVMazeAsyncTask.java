package edu.illinois.techdemonstration;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import TVMaze.Show;

/**
 * Created by Helena Chi on 11/15/2017.
 */

public class TVMazeAsyncTask extends AsyncTask<String, Integer, Show[]> {
    private Context context;
    private TVMazeAdapter tvMazeAdapter;
    private String label;
    public Show emptyListShow = null;
    public Show[] allShows;

    public TVMazeAsyncTask(Context context, TVMazeAdapter tvMazeAdapter, String label) {
        this.context = context;
        this.tvMazeAdapter = tvMazeAdapter;
        this.label = label;
    }

    public Show getEmptyListShow() {
        return this.emptyListShow;
    }

    @Override
    protected Show[] doInBackground(String... strings) {
        try{
            URL url = new URL(strings[0]);

            URLConnection connection = url.openConnection();
            connection.connect();

            InputStream inStream = connection.getInputStream();
            InputStreamReader inStreamReader = new InputStreamReader(inStream, Charset.forName("UTF-8"));

            Gson gson = new Gson();
            allShows = gson.fromJson(inStreamReader, Show[].class);

            emptyListShow = allShows[0];
            emptyListShow.setTempShow();


            return tvMazeAdapter.filterShows(allShows, label);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Show[] shows) {
        Log.d("Tag", "Number of Movies: " + shows.length);
        if (null == shows || shows.length <= 0) {
            return;
        }

        tvMazeAdapter.addShowList(Arrays.asList(shows));
        tvMazeAdapter.notifyDataSetChanged();


    }
}
