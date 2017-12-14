package TVMaze;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Helena Chi on 11/15/2017.
 */

public class Rating implements Parcelable {
    private double average;

    public double getAverage() {
        return average;
    }
    public void setAverage(double newAverage) {
        this.average = newAverage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.average);
    }

    public Rating() {
    }

    protected Rating(Parcel in) {
        this.average = in.readDouble();
    }

    public static final Parcelable.Creator<Rating> CREATOR = new Parcelable.Creator<Rating>() {
        @Override
        public Rating createFromParcel(Parcel source) {
            return new Rating(source);
        }

        @Override
        public Rating[] newArray(int size) {
            return new Rating[size];
        }
    };
}
