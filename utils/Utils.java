package utils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TreeMap;

public class Utils {
    public static String dayOfWeek(){
        TreeMap<String,String> engRus = new TreeMap<>();
        engRus.put("Monday", "Понедельник");
        engRus.put("Tuesday", "Вторник");
        engRus.put("Wednesday", "Среда");
        engRus.put("Thursday", "Четверг");
        engRus.put("Friday", "Пятница");
        engRus.put("Saturday", "Суббота");
        engRus.put("Sunday", "Воскресенье");

        Date d = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE", Locale.US);
        String str = dateFormat.format(d);
        return engRus.get(str);
    }
}
