package TVMaze;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Helena Chi on 11/15/2017.
 */

public class Episode implements Parcelable {
    private String href;

    public String getHref() {
        return href;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.href);
    }

    public Episode() {
    }

    protected Episode(Parcel in) {
        this.href = in.readString();
    }

    public static final Parcelable.Creator<Episode> CREATOR = new Parcelable.Creator<Episode>() {
        @Override
        public Episode createFromParcel(Parcel source) {
            return new Episode(source);
        }

        @Override
        public Episode[] newArray(int size) {
            return new Episode[size];
        }
    };
}
