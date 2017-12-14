package TVMaze;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Helena Chi on 11/15/2017.
 */

public class Country implements Parcelable {
    private String name;
    private String code;
    private String timezone;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getTimezone() {
        return timezone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.code);
        dest.writeString(this.timezone);
    }

    public Country() {
    }

    protected Country(Parcel in) {
        this.name = in.readString();
        this.code = in.readString();
        this.timezone = in.readString();
    }

    public static final Parcelable.Creator<Country> CREATOR = new Parcelable.Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel source) {
            return new Country(source);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };
}
