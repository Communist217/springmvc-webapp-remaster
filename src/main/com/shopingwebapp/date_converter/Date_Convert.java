package main.com.shopingwebapp.date_converter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Date_Convert {
    //Convert a string to date with the format underneath.
    public static Timestamp String_to_Date(String dateinstring) {
        Timestamp timestamp = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date date = sdf.parse(dateinstring);
            timestamp = new Timestamp(date.getTime());

        } catch (Exception e) {
            //Nothing
        }
        return timestamp;
    }

    //Calculating day gap between 2 distinct days
    public static long Day_Gap(String before, String after) {
        long gap = 0;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d1 = format.parse(before);
            Date d2 = format.parse(after);
            System.out.println(d2);
            System.out.println(d1);
            long dif = d2.getTime() - d1.getTime();
            gap += dif / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gap;
    }
}