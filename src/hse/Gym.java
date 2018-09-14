package hse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by dinahas on 29.10.2017.
 * work scheduel of gym
 */
public class Gym extends Info {

    public Gym(){

    }

    public Gym(String time, String location, String day){
        this.setTime(time);
        this.setLocation(location);
        this.setDay(day);
    }

    public Gym needInfo(String dayNum) throws ParseException {

        String time = null, location = null;

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(dayNum));
        int num = calendar.get(Calendar.DAY_OF_WEEK);

        switch (num){
            case 2:
                time  = "10:00 - 14:00";
                location = "Gym: section 3, 127 room";
                break;
            case 3:
                time = "18:00 - 22:00";
                location =  "Gym: section 3, 127 room";
                break;
            case 4:
                time = "18:00 - 22:00";
                location = "Gym: section 3, 127 room";
                break;
            case 5:
                time = "10:00 - 14:00";
                location = "Gym: section 3, 127 room";
                break;
            case 6:
                time = "18:00 - 22:00";
                location = "Gym: section 3, 127 room";
                break;
            case 7:
                time = "10:00 - 14:00";
                location = "Gym: section 3, 127 room";
                break;
            case 1:
                time = "day off";
                location = "Gym: section 3, 127 room";
                break;
            default:
                break;
        }

        Gym gym = new Gym(time, location, dayNum);
        return gym;
    }

}
