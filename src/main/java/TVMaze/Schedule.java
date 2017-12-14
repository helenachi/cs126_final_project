package TVMaze;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Helena Chi on 11/15/2017.
 */

public class Schedule implements Parcelable {
    private String time;
    private String[] days;

    public String getTime() {
        return time;
    }

    public String[] getDays() {
        return days;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.time);
        dest.writeStringArray(this.days);
    }

    public Schedule() {
    }

    protected Schedule(Parcel in) {
        this.time = in.readString();
        this.days = in.createStringArray();
    }

    public static final Parcelable.Creator<Schedule> CREATOR = new Parcelable.Creator<Schedule>() {
        @Override
        public Schedule createFromParcel(Parcel source) {
            return new Schedule(source);
        }

        @Override
        public Schedule[] newArray(int size) {
            return new Schedule[size];
        }
    };
}
