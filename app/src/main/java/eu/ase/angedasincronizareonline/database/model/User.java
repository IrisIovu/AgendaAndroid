package eu.ase.angedasincronizareonline.database.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "parola")
    private String parola;

    public User(long id, String name,String parola) {
        this.id = id;
        this.name = name;
        this.parola = parola;
    }
    @Ignore
    public User(String username, String password) {
        this.name = username;
        this.parola = password;
    }

    private User(Parcel in) {
        this.name = in.readString();
        this.parola=in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(parola);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", parola='" + parola + '\'' +
                '}';
    }

    public long getId(){
        return id;
    }
    public void setId(long id) {
        this.id=id;
    }
}
