package za.co.ipay.prepaid.vendor.util;

import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by F4742443 on 2016/03/10.
 */
public class ReferenceNumberGenerator implements Serializable {

    private static final SimpleDateFormat MINUTE_OF_THE_DAY = new SimpleDateFormat("mmss");

    public static String nextReferenceNumber(int count){
        if(count <= 0) {
            return null;
        } else if(count > 99999) {
            count = 1;
        }
        String countStr = Integer.toString(count + 1);
        Calendar c = createCalendar();
        String dayOfYear = Integer.toString(c.get(Calendar.DAY_OF_YEAR));
        String minute = MINUTE_OF_THE_DAY.format(c.getTime());

        String newRefNumber = StringUtils.leftPad(dayOfYear, 3, '0') + minute + StringUtils.leftPad(countStr, 5, '0');
        System.out.println("New Ref Number > " + newRefNumber);
        return newRefNumber;
    }

    /**
     * Create a calendar with the first day of the week monday.
     */
    public static GregorianCalendar createCalendar() {
        GregorianCalendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        return c;
    }
}
