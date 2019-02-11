import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    private SessionFactory sessionFactory;

    public Item save(Item item) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = createSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();

            session.save(item);

            transaction.commit();
            System.out.println("save is done");
        } catch (HibernateException e) {
            System.err.println("save is failed");
            System.err.println(e.getMessage());

            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return item;
    }

    public Item update(Item item) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = createSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();

            session.update(item);

            transaction.commit();
            System.out.println("update is done");
        } catch (HibernateException e) {
            System.err.println("update is failed");
            System.err.println(e.getMessage());

            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return item;
    }


    public void delete(Item item) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = createSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();

            session.delete(item);

            transaction.commit();
            System.out.println("delete is done");
        } catch (HibernateException e) {
            System.err.println("delete is failed");
            System.err.println(e.getMessage());

            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    public List<Item> findById(long id) {
        List<Item> foundObjects = new ArrayList<>();
        Session session = null;
        Transaction transaction = null;
        try {
            session = createSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();

            String hql = "from Item where id = " + id;
            Query query = session.createQuery(hql);

            foundObjects.addAll(query.list());

            transaction.commit();
            System.out.println("Search by id is done.");
        } catch (HibernateException e) {
            System.err.println("Search by id is failed.");
            System.err.println(e.getMessage());

            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return foundObjects;
    }

    private SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }

}
