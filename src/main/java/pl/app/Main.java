package pl.app;

import org.hibernate.Session;
import pl.dao.Dao;
import pl.model.Currency;
import pl.util.HibernateUtil;

public class Main {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Dao dao = new Dao();
        Currency find = dao.findById(Currency.class,1);

        System.out.println(find.toString());
        session.close();

    }
}
