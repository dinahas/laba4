package hse;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import hse.BedLinen;
import hse.Gym;
import hse.PassporOffice;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by dinahas on 12.12.2017.
 * calendar with tasks
 */
public class Struct {

    public Info[] infos = new Info[100];
    private int sum = 0;

    public Struct(Info inf) {

        infos[0] = inf;
    }

    public Struct() {

    }

    public Struct(Info[] other) {

        int i;

        for (i = 0; i < sum; i++)

            infos[i] = other[i];

    }
	
	// inserting data in the calendar
    public void insertInfo(Info info) {
        int i;

        if(searchInfo(info.day, info.location)){
            switch (info.getClass().getSimpleName()) {
                case "PassporOffice":
                    infos[sum]=new PassporOffice(info.getTime(),
                            info.getLocation(),
                            info.getDay());
                    break;
                case "Gym":
                    infos[sum]=new Gym(info.getTime(),
                            info.getLocation(),
                            info.getDay());
                    break;
                case "BedLinen":
                    infos[sum]=new BedLinen(info.getTime(),
                            info.getLocation(),
                            info.getDay());
                    break;
                case "Doctor":
                    infos[sum]=new Doctor(info.getTime(),
                            info.getLocation(),
                            info.getDay());
                    break;
            }
            sum++;
        }
    }

	//deleting data from the calendar
    public void deleteInfo(String location, String day) {

        int i;

        boolean flag = false;

        if(!isEmpty() && !searchInfo(day, location)){
            for (i = 0; i < sum; i++) {

                if (infos[i].day == day && infos[i].location == location) {

                    flag = true;
                    break;
                }
                int j;
                if (flag) {

                    for (j = i; j < sum - 1; j++)
                        infos[j] = infos[j + 1];
                    sum--;
                }
            }
        }
    }

    public String showInfo(){

        return showAll();
    }

	// writing data from the calendar to json file
    public void writeJson(String filename) {

        int i;

        JSONArray array = new JSONArray();

        if(!isEmpty()){
            for (i = 0; i < sum; i++) {

                JSONObject object = new JSONObject();
                object.put("day", infos[i].day);
                object.put("location", infos[i].location);
                object.put("time", infos[i].time);
                array.add(i, object);
            }

            try (FileWriter writer = new FileWriter(filename)) {
                writer.write(array.toJSONString());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

	// reading data fron the json file
    public void readJson(String filename) {

        StringBuilder jsonStrBuilder = new StringBuilder();
        Scanner inputScanner;
        try{
            inputScanner = new Scanner(new File(filename));
            while(inputScanner.hasNext())
                jsonStrBuilder.append(inputScanner.next());
            inputScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JSONParser jsonParser = new JSONParser();

        try{

            int i;
            JSONArray array = (JSONArray) jsonParser
                    .parse(jsonStrBuilder.toString());
            int all = array.size(), j;
            for(j = 0; j < all; j++){

                JSONObject object = (JSONObject) array.get(j);
                String day = (String) object.get("day");
                String location = (String) object.get("location");
                String time  = (String) object.get("time");
                Doctor doctor = new Doctor(time, location, day);
                insert(doctor);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

	// writing data from array 
    private String showAll(){

        String str = "", location, time, day;

        for(int i = 0; i < sum; i++){
            location = infos[i].getLocation();
            day = infos[i].getDay();
            time = infos[i].getTime();
            str = str + day + " " + location + " " + time + " \n";
        }
        String finalStr = str;
        return str;
    }


    private boolean searchInfo(String day, String location){

        int i;

        boolean flag = true;

        for(i = 0; i < sum; i++){
            if(infos[i].getDay() == day &&
                    infos[i].getLocation() == location){
                flag = false;
                break;
            }
        }
        return flag;
    }

    private int setDate(String day) throws java.text.ParseException {

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(day));
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        return  weekDay;

    }

	// returns todays day of week
    private int dayOfWeek() {

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return day;
    }

	// checks if json file/calendar is empty
    public boolean isEmpty(){
        if(sum == 0)
            return true;
        else
            return false;
    }
}