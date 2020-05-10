package eu.ase.angedasincronizareonline.utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import eu.ase.angedasincronizareonline.AddMeetingActivity;

public class Meeting implements Parcelable {
    private String description;
    private String hour;
    private Integer numberOfPart;
    private Date date;
    private String location;

    public static final Creator<Meeting> CREATOR = new Creator<Meeting>() {
        @Override
        public Meeting createFromParcel(Parcel in) {
            return new Meeting(in);
        }

        @Override
        public Meeting[] newArray(int size) {
            return new Meeting[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Integer getNumberOfPart() {
        return numberOfPart;
    }

    public void setNumberOfPart(Integer numberOfPart) {
        this.numberOfPart = numberOfPart;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }



    public Meeting(String description, String hour, Integer numberOfPart, Date date, String location) {
        this.description = description;
        this.hour = hour;
        this.numberOfPart = numberOfPart;
        this.date = date;
        this.location = location;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeString(hour);
        dest.writeInt(numberOfPart);

        String dateStr = this.date != null
                ? new SimpleDateFormat(
                AddMeetingActivity.DATE_FORMAT,
                Locale.US).format(this.date)
                : null;
        dest.writeString(dateStr);
        dest.writeString(location);
    }

    private Meeting(Parcel in) {
        this.description = in.readString();
        this.hour = in.readString();

        this.numberOfPart = in.readInt();
        try {
            this.date = new SimpleDateFormat(
                    AddMeetingActivity.DATE_FORMAT,
                    Locale.US).parse(in.readString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.location = in.readString();
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "description='" + description + '\'' +
                ", hour='" + hour + '\'' +
                ", numberOfPart=" + numberOfPart +
                ", date=" + date +
                ", location='" + location + '\'' +
                '}';
    }
}
