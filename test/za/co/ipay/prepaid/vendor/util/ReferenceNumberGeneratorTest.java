package za.co.ipay.prepaid.vendor.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by F4742443 on 2016/03/10.
 */
public class ReferenceNumberGeneratorTest {

    @Test
    public void TestNextReferenceNumberWithValidCount(){
        int count = 123;
        String referenceNumber = ReferenceNumberGenerator.nextReferenceNumber(count);
        System.out.println(referenceNumber);
        Assert.assertNotNull(referenceNumber);
    }

    @Test
    public void TestNextReferenceNumberWithInvalidCount(){
        int count = 100000;
        String referenceNumber = ReferenceNumberGenerator.nextReferenceNumber(count);
        Assert.assertNull(referenceNumber);

        count = 0;
        referenceNumber = ReferenceNumberGenerator.nextReferenceNumber(count);
        Assert.assertNull(referenceNumber);
    }

    private Date getDate(int day, int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.YEAR, year);
        return cal.getTime();
    }

}
