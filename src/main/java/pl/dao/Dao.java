package pl.dao;

import org.hibernate.Session;
import pl.model.Currency;
import pl.model.util.HibernateUtil;

public class Dao {
    private Session session;

    public Currency findById(Class<Currency> currencyClass, int id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Currency currency = session.find(Currency.class, id);
        session.close();
        return currency;
    }

    public void insert (Currency currency) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.persist(currency);
        session.flush();
        session.close();
    }

    public void delete (int id) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //session.delete(new Currency(id));
        session.delete("Currency", id);
        session.flush();
        session.close();
    }

    public void update (Currency currency) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        if (session.find(Currency.class, currency.getId()) != null) {
            session.merge(currency);
        } else {
            System.out.println("Currency does not exist");
        }
        session.flush();
        session.close();
    }
}
