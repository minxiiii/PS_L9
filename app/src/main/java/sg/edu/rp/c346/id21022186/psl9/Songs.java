package sg.edu.rp.c346.id21022186.psl9;

import java.io.Serializable;

public class Songs implements Serializable {
    private int id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public Songs (String title, String singers,int year,int stars ) {
        this.title = title;
        this.singers=singers;
        this.year = year;
        this.stars=stars;
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSingers() {
        return singers;
    }

    public int getYear() {
        return year;
    }

    public int getStars() {
        return stars;
    }
}
