package TVMaze;

import android.os.Parcel;
import android.os.Parcelable;

import org.jsoup.Jsoup;

/**
 * Created by Helena Chi on 11/15/2017.
 */

public class Show implements Parcelable {
    public String label;

    private int id;
    private String url;
    private String name;
    private String type;
    private String language;
    private String[] genres;
    private String status;
    private int runtime;
    private String premiered;
    private String officialSite;
    private Schedule schedule;
    private Rating rating;
    private int weight;
    private Network network;
    private Network webChannel;
    private Image image;
    private String summary;

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLanguage() {
        return language;
    }

    public String[] getGenres() {
        return genres;
    }

    public String getStatus() {
        return status;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getPremiered() {
        return premiered;
    }

    public String getOfficialSite() {
        return officialSite;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public Rating getRating() {
        return rating;
    }

    public int getWeight() {
        return weight;
    }

    public Network getNetwork() {
        return network;
    }

    public Network getWebChannel() {
        return webChannel;
    }

    public Image getImage() {
        return image;
    }

    public String getSummary() {
        return Jsoup.parse(this.summary).text();
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setTempShow() {
        this.name = "Empty List Show Item";
        this.genres = new String[1];
        genres[0] = "N/A";
        this.rating.setAverage(0.0);
        this.summary = "Add some shows to your playlist!";
        this.runtime = 0;
        this.label = "favorites";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.label);
        dest.writeInt(this.id);
        dest.writeString(this.url);
        dest.writeString(this.name);
        dest.writeString(this.type);
        dest.writeString(this.language);
        dest.writeStringArray(this.genres);
        dest.writeString(this.status);
        dest.writeInt(this.runtime);
        dest.writeString(this.premiered);
        dest.writeString(this.officialSite);
        dest.writeParcelable(this.schedule, flags);
        dest.writeParcelable(this.rating, flags);
        dest.writeInt(this.weight);
        dest.writeParcelable(this.network, flags);
        dest.writeParcelable(this.webChannel, flags);
        dest.writeParcelable(this.image, flags);
        dest.writeString(this.summary);
    }

    public Show() {
    }

    protected Show(Parcel in) {
        this.label = in.readString();
        this.id = in.readInt();
        this.url = in.readString();
        this.name = in.readString();
        this.type = in.readString();
        this.language = in.readString();
        this.genres = in.createStringArray();
        this.status = in.readString();
        this.runtime = in.readInt();
        this.premiered = in.readString();
        this.officialSite = in.readString();
        this.schedule = in.readParcelable(Schedule.class.getClassLoader());
        this.rating = in.readParcelable(Rating.class.getClassLoader());
        this.weight = in.readInt();
        this.network = in.readParcelable(Network.class.getClassLoader());
        this.webChannel = in.readParcelable(Network.class.getClassLoader());
        this.image = in.readParcelable(Image.class.getClassLoader());
        this.summary = in.readString();
    }

    public static final Parcelable.Creator<Show> CREATOR = new Parcelable.Creator<Show>() {
        @Override
        public Show createFromParcel(Parcel source) {
            return new Show(source);
        }

        @Override
        public Show[] newArray(int size) {
            return new Show[size];
        }
    };
}
