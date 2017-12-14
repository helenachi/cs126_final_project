package TVMaze;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Helena Chi on 11/15/2017.
 */

public class Network implements Parcelable {
    private int id;
    private String name;
    private Country country;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeParcelable(this.country, flags);
    }

    public Network() {
    }

    protected Network(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.country = in.readParcelable(Country.class.getClassLoader());
    }

    public static final Parcelable.Creator<Network> CREATOR = new Parcelable.Creator<Network>() {
        @Override
        public Network createFromParcel(Parcel source) {
            return new Network(source);
        }

        @Override
        public Network[] newArray(int size) {
            return new Network[size];
        }
    };
}
