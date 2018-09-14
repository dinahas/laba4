package hse;

import javax.print.Doc;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by dinahas on 29.10.2017.
 * work scheduel of doctor
 */
public class Doctor extends Info {

    public Doctor(){

    }

    public Doctor(String time, String location, String day){

        this.setTime(time);
        this.setLocation(location);
        this.setDay(day);
    }

    public Doctor needInfo(String dayNum) throws ParseException {

        String time = null, location = null;

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(dayNum));
        int num = calendar.get(Calendar.DAY_OF_WEEK);

        switch (num){
            case 2:
                time = "15:00 - 19:00";
                location  = "Doctor: section 1, 123 room";
                break;
            case 3:
                time = "15:00 - 19:00";
                location  = "Doctor: section 1, 123 room";
                break;
            case 4:
                time = "15:00 - 19:00";
                location  = "Doctor: section 1, 123 room";
                break;
            case 5:
                time = "15:00 - 19:00";
                location  = "Doctor: section 1, 123 room";
                break;
            case 6:
                time = "15:00 - 19:00";
                location  = "Doctor: section 1, 123 room";
                break;
            case 7:
                time = "day off";
                location  = "Doctor: section 1, 123 room";
                break;
            case 1:
                time = "day off";
                location  = "Doctor: section 1, 123 room";
                break;
            default:
                break;
        }
        Doctor doctor = new Doctor(time, location, dayNum);
        return doctor;
    }
}
