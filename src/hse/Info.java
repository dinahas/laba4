package hse;

import java.text.ParseException;
import java.util.Calendar;

/**
 * Created by dinahas on 29.10.2017.
 */
public abstract class Info {

    protected String time;
    protected String location;
    protected String day;

    public void Info(){

        this.day = "";
        this.time = "";
        this.location = "";
    }

    public String getTime() {

        return time;
    }

    public String getLocation() {

        return location;
    }

    public String getDay(){

        return day;
    }

    public void setTime(String time) {

        this.time = time;
    }

    public void setLocation(String location) {

        this.location = location;
    }

    public void setDay(String day){

        this.day = day;
    }

    public int dayOfWeek() {

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return day;

    }

    abstract Info needInfo(String dayNum) throws ParseException;
}