package com.mq310.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Dao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Integer save(Object o) {
        return (Integer) sessionFactory.getCurrentSession().save(o);
    }

    public void delete(final Object object) {
        sessionFactory.getCurrentSession().delete(object);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(final Class<T> type, final Integer id) {
        T t = (T) sessionFactory.getCurrentSession().get(type, id);
        return t;
    }

    public <T> T getDeatached(final Class<T> type, final Integer id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        T t = (T) session.get(type, id);
        session.getTransaction().commit();
        session.close();
        return t;
    }

    @SuppressWarnings({"unchecked"})
    public <T> List<T> execute(final Class<T> type, String hql) {
        List<T> list = sessionFactory.getCurrentSession().createQuery(hql).list();
        return list;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(final Class<T> type, String hql) {
        T t = ((T) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult());
        return t;
    }

    @SuppressWarnings("unchecked")
    public <T> T merge(final T o) {
        return (T) sessionFactory.getCurrentSession().merge(o);
    }

    public <T> void saveOrUpdate(final T o) {
        sessionFactory.getCurrentSession().saveOrUpdate(o);
    }
    
    public <T> void update(final T o) {
        sessionFactory.getCurrentSession().update(o);
    }
    
    public <T> void persist(final T o){
        sessionFactory.getCurrentSession().persist(o);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getAll(final Class<T> type) {
        final Criteria crit = sessionFactory.getCurrentSession().createCriteria(type);
        List<T> list = crit.list();
        return list;
    }

}
