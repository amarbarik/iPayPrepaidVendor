package za.co.ipay.prepaid.vendor.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by F4742443 on 2016/04/10.
 */
public class Util {

    private static final SimpleDateFormat IPAY_DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");

    public static String formatToiPay(Date date) {
        if (date != null) {
            return IPAY_DATE_TIME_FORMAT.format(date);
        }
        return null;
    }

    public static Date parseDate(String date) {
        if (date == null || date.isEmpty()) {
            return null;
        }
        try {
            return IPAY_DATE_TIME_FORMAT.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

}
