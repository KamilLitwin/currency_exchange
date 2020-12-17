package pl.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.model.Currency;
import pl.model.util.HibernateUtil;

import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

    public Currency getById(long id) {
        return null;
    }

    public void delete(long id) {

        Currency currency = getById(id);
        if (currency != null) {

            Transaction transaction = null;
            try {
                Session session = HibernateUtil.getSessionFactory().openSession();
                transaction = session.beginTransaction();
                session.delete(currency);
                transaction.commit();
            } catch (IllegalStateException | RollbackException e) {
                System.err.println("Błąd usuwania Currency");
                e.printStackTrace();

                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
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

        public static Currency getByDateAndByFromAndTo(String baseCurrency, String exchangeCurrency) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                CriteriaBuilder cb = session.getCriteriaBuilder();
                CriteriaQuery<Currency> query = cb.createQuery(Currency.class);
                Root<Currency> table = query.from(Currency.class);

                Predicate[] predicates = new Predicate[2];
                predicates[0] = cb.equal(table.get("baseCurrency"), baseCurrency);
                predicates[1] = cb.equal(table.get("currency"), exchangeCurrency);

                query.select(table).where(predicates);

                Currency currency = session.createQuery(query).getSingleResult();

                return currency;
            } catch (PersistenceException e) {
                System.err.println("Błąd zapisu encji Currency");
                e.printStackTrace();
            }

            return null;
        }
    public static void create(Currency currency) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(currency);
            transaction.commit();
        } catch (IllegalStateException | RollbackException e) {
            System.err.println("Błąd zapisu encji Currency");
            e.printStackTrace();
        }
    }
}
