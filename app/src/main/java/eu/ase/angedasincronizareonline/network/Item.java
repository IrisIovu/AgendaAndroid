package eu.ase.angedasincronizareonline.network;

public class Item {

    private String SubjectOfTheMeeting;
    private String extraInfo;
    private MeetingInfo meetingInfo;

    public String getSubjectOfTheMeeting() {
        return SubjectOfTheMeeting;
    }

    public void setSubjectOfTheMeeting(String subjectOfTheMeeting) {
        SubjectOfTheMeeting = subjectOfTheMeeting;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public MeetingInfo getMeetingInfo() {
        return meetingInfo;
    }

    public void setMeetingInfo(MeetingInfo meetingInfo) {
        this.meetingInfo = meetingInfo;
    }

    @Override
    public String toString() {
        return "Item{" +
                "SubjectOfTheMeeting='" + SubjectOfTheMeeting + '\'' +
                ", extraInfo='" + extraInfo + '\'' +
                ", meetingInfo=" + meetingInfo +
                '}';
    }


    public Item(String SubjectOfTheMeeting, String extraInfo, MeetingInfo meetingInfo) {
        this.SubjectOfTheMeeting = SubjectOfTheMeeting;
        this.extraInfo = extraInfo;
        this.meetingInfo = meetingInfo;
    }


}
