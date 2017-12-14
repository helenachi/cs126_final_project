package TVShowManager;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import TVMaze.Show;

/**
 * Created by Helena Chi on 11/29/2017.
 */

public class Playlist implements Parcelable {
    private String playlistName;
    private List<Show> playlistShows;


    public String getPlaylistName() {
        return this.playlistName;
    }

    public List<Show> getPlaylistShows() {
        return this.playlistShows;
    }

    public int getPlaylistSize() {
        return this.playlistShows.size();
    }

    public void add (List<Show> shows) {
        this.playlistShows.addAll(shows);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.playlistName);
        dest.writeList(this.playlistShows);
    }

    public Playlist(String playlistName, List<Show> playlistShows) {
        this.playlistName = playlistName;
        this.playlistShows = playlistShows;
    }

    public Playlist () {
    }

    protected Playlist(Parcel in) {
        this.playlistName = in.readString();
        this.playlistShows = new ArrayList<Show>();
        in.readList(this.playlistShows, Show.class.getClassLoader());
    }

    public static final Parcelable.Creator<Playlist> CREATOR = new Parcelable.Creator<Playlist>() {
        @Override
        public Playlist createFromParcel(Parcel source) {
            return new Playlist(source);
        }

        @Override
        public Playlist[] newArray(int size) {
            return new Playlist[size];
        }
    };
}
