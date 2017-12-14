package TVMaze;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Helena Chi on 11/15/2017.
 */

public class Image implements Parcelable {
    private String medium;
    private String original;
    private String summary;
    private int updated;

    @SerializedName("links")
    private String _links;


    public String getMedium() {
        return medium;
    }

    public String getOriginal() {
        return original;
    }

    public String getSummary() {
        return summary;
    }

    public int getUpdated() {
        return updated;
    }

    public String get_links() {
        return _links;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.medium);
        dest.writeString(this.original);
        dest.writeString(this.summary);
        dest.writeInt(this.updated);
        dest.writeString(this._links);
    }

    public Image() {
    }

    protected Image(Parcel in) {
        this.medium = in.readString();
        this.original = in.readString();
        this.summary = in.readString();
        this.updated = in.readInt();
        this._links = in.readString();
    }

    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}
