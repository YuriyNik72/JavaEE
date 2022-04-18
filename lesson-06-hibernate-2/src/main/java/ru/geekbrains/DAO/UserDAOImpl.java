package ru.geekbrains.DAO;


import org.hibernate.Session;
import ru.geekbrains.model.User;
import ru.geekbrains.utils.SessionFactoryUtils;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    private SessionFactoryUtils sessionFactoryUtils;

    public UserDAOImpl(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }



    @Override
    public User findById(Long id) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.getTransaction().commit();
            return user;
        }
    }
    @Override
    public List<User> findAll () {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            List<User> user = session.createQuery("select u from User u").getResultList();
            session.getTransaction().commit();
            return user;
        }
    }

    @Override
    public User saveOrUpdate (User user) {
        try (Session session = sessionFactoryUtils.getSession()){
            session.beginTransaction();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
        }
        return user;
    }

    @Override
    public void deleteById (Long id){
        try (Session session = sessionFactoryUtils.getSession()){
            session.beginTransaction();
            session.createQuery("delete from User u where u.id = :id")
                    .setParameter("id",id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

}
