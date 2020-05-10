package eu.ase.angedasincronizareonline.network;

import java.util.Date;

public class MeetingInfo {
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    private String description;
    private Date date;
    private String hour;

    public MeetingInfo(String description, Date date, String hour) {
        this.description = description;
        this.date = date;
        this.hour = hour;
    }

    @Override
    public String toString() {
        return "MeetingInfo{" +
                "description='" + description + '\'' +
                ", date=" + date +
                ", hour='" + hour + '\'' +
                '}';
    }
}
