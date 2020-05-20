package eu.ase.angedasincronizareonline.utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Event implements Parcelable {
    private String nameEvent;
    private Date data;
    private String hour;
    private int importance;


    public Event(String nameEvent, Date data, String hour, int importance) {
        this.nameEvent = nameEvent;
        this.data = data;
        this.importance = importance;
        this.hour=hour;
    }


    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel source) {
            return new Event(source);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameEvent);
        dest.writeString(hour);
        dest.writeLong(this.data.getTime());
        dest.writeInt(importance);
    }
    private Event(Parcel in) {
        this.nameEvent = in.readString();
        this.hour = in.readString();

        this.importance = in.readInt();
        this.data = new Date(in.readLong());

    }

    @Override
    public String toString() {
        return "Event{" +
                "nameEvent='" + nameEvent + '\'' +
                ", data=" + data +
                ", hour='" + hour + '\'' +
                ", importance=" + importance +
                '}';
    }
}
