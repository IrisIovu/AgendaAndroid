package eu.ase.angedasincronizareonline.network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class JsonParser {


    public static HttpResponse parseJson(String json) {
        if (json == null) {
            return null;
        }
        try {
            JSONObject jsonObject = new JSONObject(json);
            List<Item> room1 = getItemListFromJson(jsonObject
                    .getJSONArray("Room1"));
            List<Item> room2 = getItemListFromJson(jsonObject
                    .getJSONArray("Room2"));
            List<Item> room3 = getItemListFromJson(jsonObject
                    .getJSONArray("Room3"));
            List<Item> room4 = getItemListFromJson(jsonObject
                    .getJSONArray("Room4"));
            return new HttpResponse(room1, room2, room3, room4);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<Item> getItemListFromJson(JSONArray array) throws JSONException {
        if (array == null) {
            return null;
        }
        List<Item> results = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            Item item = getItemFromJson(array
                    .getJSONObject(i));
            if (item != null) {
                results.add(item);
            }
        }
        return results;
    }

    private static Item getItemFromJson(JSONObject object) throws JSONException {
        if (object == null) {
            return null;
        }
        String SubjectOfTheMeeting = object.getString("SubjectOfTheMeeting");
        String extraInfo = object.getString("extraInfo");
        MeetingInfo meetingInfo = getMeetingInfoFromJson(object
                .getJSONObject("meetingInfo"));
        return new Item(SubjectOfTheMeeting, extraInfo, meetingInfo);
    }

    private static MeetingInfo getMeetingInfoFromJson(JSONObject object) throws JSONException {
        if (object == null) {
            return null;
        }

        String description = object.getString("description");
        Date date = null;
        try {
            date = new SimpleDateFormat("DD-MM-YYYY",
                    Locale.US).parse(object
                    .getString("date"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String hour = object
                .getString("hour");
        return new MeetingInfo(description, date, hour);
    }
}
