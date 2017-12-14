package edu.illinois.techdemonstration;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import TVMaze.Show;
import TVShowManager.Playlist;

/**
 * Created by Helena Chi on 12/8/2017.
 */

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {
    public static final String BASE_URL = "http://api.tvmaze.com/shows";
    public List<Show> shows = new ArrayList<>();
    public TVMazeAdapter tvMazeAdapter;
    public TVMazeAsyncTask tvMazeAsyncTask;

    private List<Playlist> playlists;
    private Show emptyListShow;
    private List<Show> favoriteShows = new ArrayList<>();
    private Playlist favorites = new Playlist("favorites", favoriteShows);

    public final static String PLAYLIST = "Playlist";




    public PlaylistAdapter(List<Playlist> playlists, Context context) {
        populateEmptyPlaylist(context);
        this.playlists = playlists;
        if (playlists == null) {
            playlists = new ArrayList<>();
            List<Show> favoriteShows = new ArrayList<>();
            Show emptyListShow = new Show();
            if (emptyListShow == null || emptyListShow.getName() == null) {
                emptyListShow = tvMazeAsyncTask.getEmptyListShow();
            }
            favoriteShows.add(emptyListShow);
            playlists.add(new Playlist("Favorites", favoriteShows));
        }
    }

    public Playlist getPlaylistByName(String showLabel) {
        for (Playlist p : this.playlists) {
            if (p.getPlaylistName().equals(showLabel)) {
                return p;
            }
        }
        return null;
    }

    public void addPlaylist(Playlist playlist) {
        this.playlists.add(playlist);
    }

    public void addShowList(List<Playlist> otherPlaylists) {
        this.playlists.addAll(otherPlaylists);
    }


    public void populateEmptyPlaylist(Context context) {
        this.tvMazeAdapter = new TVMazeAdapter(shows);
        this.tvMazeAsyncTask = new TVMazeAsyncTask(context, tvMazeAdapter, null);
        this.tvMazeAsyncTask.execute(BASE_URL);
        this.emptyListShow = tvMazeAsyncTask.getEmptyListShow();
        this.favoriteShows.add(emptyListShow);
        this.playlists = new ArrayList<>();
        this.playlists.add(favorites);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        View playlistItem;

        populateEmptyPlaylist(context);

        playlistItem = LayoutInflater.from(context).inflate(R.layout.playlist_item, parent, false); //viewtype
        //R.layout.playlist_activity viewtype
        ViewHolder holder = new ViewHolder(playlistItem);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(position >= playlists.size())
            return;
        final Playlist playlist = playlists.get(position);

        holder.playlistNameTV.setText(playlist.getPlaylistName());

        int showCount = playlist.getPlaylistSize();
        String showCountString = "(" + Integer.toString(showCount) + ")";
        holder.showCountTV.setText(showCountString);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), PlaylistDetailActivity.class);
                intent.putExtra(PLAYLIST, playlist);
                view.getContext().startActivity(intent);
            }

        });
    }

    @Override
    public int getItemCount() {
        return favoriteShows.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.playlist_activity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public View itemView;
        public TextView playlistNameTV;
        public TextView showCountTV;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.playlistNameTV = (TextView) itemView.findViewById(R.id.playlist_name_textView);
            this.showCountTV = (TextView) itemView.findViewById(R.id.show_count_textView);
        }
    }
}
