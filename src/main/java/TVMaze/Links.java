package TVMaze;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Helena Chi on 11/15/2017.
 */

public class Links implements Parcelable {
    private Episode self;

    @SerializedName("previousEpisode")
    private Episode previosepisode;

    @SerializedName("nextEpisode")
    private Episode nextepisode;


    public Episode getSelf() {
        return self;
    }

    public Episode getPreviosepisode() {
        return previosepisode;
    }

    public Episode getNextepisode() {
        return nextepisode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.self, flags);
        dest.writeParcelable(this.previosepisode, flags);
        dest.writeParcelable(this.nextepisode, flags);
    }

    public Links() {
    }

    protected Links(Parcel in) {
        this.self = in.readParcelable(Episode.class.getClassLoader());
        this.previosepisode = in.readParcelable(Episode.class.getClassLoader());
        this.nextepisode = in.readParcelable(Episode.class.getClassLoader());
    }

    public static final Parcelable.Creator<Links> CREATOR = new Parcelable.Creator<Links>() {
        @Override
        public Links createFromParcel(Parcel source) {
            return new Links(source);
        }

        @Override
        public Links[] newArray(int size) {
            return new Links[size];
        }
    };
}
