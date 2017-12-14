package TVShowManager;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Helena Chi on 12/10/2017.
 */

public class User implements Parcelable{
    private String username; //??? do i need this
    private String password; //??? do i need this
    private List<Playlist> userPlaylists;

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public List<Playlist> getUserPlaylists() {
        return userPlaylists;
    }

    public User() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeTypedList(this.userPlaylists);
    }

    protected User(Parcel in) {
        this.username = in.readString();
        this.password = in.readString();
        this.userPlaylists = in.createTypedArrayList(Playlist.CREATOR);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
