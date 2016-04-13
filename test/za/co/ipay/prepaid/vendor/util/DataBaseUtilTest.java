package za.co.ipay.prepaid.vendor.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Ignore;
import org.junit.Test;
import za.co.ipay.prepaid.vendor.builder.PayTypeBuilder;
import za.co.ipay.prepaid.vendor.domain.ElecTransaction;
import za.co.ipay.prepaid.vendor.domain.Meter;
import za.co.ipay.prepaid.vendor.domain.PayType;

import java.sql.Types;
import java.util.List;

/**
 * Created by F4742443 on 2016/03/10.
 */
public class DataBaseUtilTest {

    @Test
    @Ignore
    public void insertPayTypeDetailsIntoDB() {
        SessionFactory sessionFactory = DataBaseUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<PayType> list = session.createQuery("from pay_type").list();
        if(list.size() > 0)
            System.out.println(list.get(9).getDescription());
        session.getTransaction().commit();
        session.close();
    }

    @Test
    @Ignore
    public void testLastTransNumber() {
        Session session = DataBaseUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<ElecTransaction> list = session.createQuery("select max (tr.transactionNumber) from elec_trans tr ").list();
        session.getTransaction().commit();
        if(list.size() > 0 ){
            System.out.println(list.size());
            System.out.println(list.get(0));;
        }
    }

    @Test
    @Ignore
    public void testMeterByName() {
        Session session = DataBaseUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String number = "7776662221";
        List<Meter> list = session.createQuery("select m from meter m where m.meterNumber like :mNumber")
                .setParameter("mNumber", number).list();
        session.getTransaction().commit();
        System.out.println(list.size());
    }
}
