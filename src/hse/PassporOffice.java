package hse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by dinahas on 29.10.2017.
 * work scheduel of passport office
 */
public class PassporOffice extends Info {

    public PassporOffice(){

    }

    public PassporOffice(String time, String location, String day){

        this.setTime(time);
        this.setLocation(location);
        this.setDay(day);
    }

    @Override
    PassporOffice needInfo(String dayNum) throws ParseException {

        String time = null, location = null;

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(dayNum));
        int num = calendar.get(Calendar.DAY_OF_WEEK);

        switch (num){
            case 2:
                time  = "9:00 - 18:00";
                location = "Passport Office: section 3, 123 room";
                break;
            case 3:
                time  = "9:00 - 18:00";
                location = "Passport Office: section 3, 123 room";
                break;
            case 4:
                time  = "9:00 - 18:00";
                location = "Passport Office: section 3, 123 room";
                break;
            case 5:
                time  = "9:00 - 18:00";
                location = "Passport Office: section 3, 123 room";
                break;
            case 6:
                time  = "9:00 - 18:00";
                location = "Passport Office: section 3, 123 room";
                break;
            case 7:
                time  = "day off";
                location = "Passport Office: section 3, 123 room";
                break;
            case 1:
                time  = "day off";
                location = "Passport Office: section 3, 123 room";
                break;
            default:
                break;
        }

        PassporOffice office = new PassporOffice(time, location, dayNum);
        return  office;
    }
}
