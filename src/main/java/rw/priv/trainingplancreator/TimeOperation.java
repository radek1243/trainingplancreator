package rw.priv.trainingplancreator;

import java.util.Calendar;

/**
 * Created by Radek on 10.03.2017.
 */

public class TimeOperation {

    public static String getDayText(int day){
        switch(day){
            case Calendar.MONDAY:{
                return "Poniedziałek";
            }
            case Calendar.TUESDAY:{
                return "Wtorek";
            }
            case Calendar.WEDNESDAY:{
                return "Środa";
            }
            case Calendar.THURSDAY:{
                return "Czwartek";
            }
            case Calendar.FRIDAY:{
                return "Piątek";
            }
            case Calendar.SATURDAY:{
                return "Sobota";
            }
            case Calendar.SUNDAY:{
                return "Niedziela";
            }
            default:{
                return null;
            }
        }
    }

}
