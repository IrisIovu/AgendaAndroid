package eu.ase.angedasincronizareonline.network;

import java.util.List;

public class HttpResponse {
    private List<Item> Room1;
    private List<Item> Room2;
    private List<Item> Room3;
    private List<Item> Room4;

    public HttpResponse(List<Item> room1, List<Item> room2, List<Item> room3, List<Item> room4) {
        Room1 = room1;
        Room2 = room2;
        Room3 = room3;
        Room4 = room4;
    }

    public List<Item> getRoom1() {
        return Room1;
    }

    public void setRoom1(List<Item> room1) {
        Room1 = room1;
    }

    public List<Item> getRoom2() {
        return Room2;
    }

    public void setRoom2(List<Item> room2) {
        Room2 = room2;
    }

    public List<Item> getRoom3() {
        return Room3;
    }

    public void setRoom3(List<Item> room3) {
        Room3 = room3;
    }

    public List<Item> getRoom4() {
        return Room4;
    }

    public void setRoom4(List<Item> room4) {
        Room4 = room4;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "Room1=" + Room1 +
                ", Room2=" + Room2 +
                ", Room3=" + Room3 +
                ", Room4=" + Room4 +
                '}';
    }
}
