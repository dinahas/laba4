package hse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by dinahas on 29.10.2017.
 * work scheduel of bed linen exchange
 */
public class BedLinen extends Info {

    public BedLinen(String time, String location, String day) {
        this.setTime(time);
        this.setLocation(location);
        this.setDay(day);
    }

    public BedLinen(){

    }

    public BedLinen needInfo(String dayNum) throws ParseException {

        String time = null, location = null;

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(dayNum));
        int num = calendar.get(Calendar.DAY_OF_WEEK);

        switch (num){
            case 2:
                time = "14:00 - 21:45";
                location = "Bed linen: section 3, 130 room";
                break;
            case 3:
                time = "9:30 - 13:30 14:00 - 17:45";
                location = "Bed linen: section 3, 130 room";
                break;
            case 4:
                time = "day off";
                location = "Bed linen: section 3, 130 room";
                break;
            case 5:
                time = "9:30 - 13:30 14:00 - 17:45";
                location = "Bed linen: section 3, 130 room";
                break;
            case 6:
                time = "9:30 - 13:30 14:00 - 17:45";
                location = "Bed linen: section 3, 130 room";
                break;
            case 7:
                time = "day off";
                location = "Bed linen: section 3, 130 room";
                break;
            case 1:
                time = "day off";
                location = "Bed linen: section 3, 130 room";;
                break;
            default:
                break;
        }
        BedLinen bedLinen = new BedLinen(time, location, dayNum);
        return bedLinen;

    }

}
