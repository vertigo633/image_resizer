package com.vertigo633.api.dao;

import com.vertigo633.api.entities.Size;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Vertigo633 on 30.06.2015.
 */
@Repository
@Transactional
public class SizeDAOImpl implements SizeDAO {
    @Autowired
    SessionFactory sessionFactory;

    public Size getSizeByName(String size_name) {

        Criteria c = sessionFactory.getCurrentSession().createCriteria(Size.class).
                add(Restrictions.eq("name", size_name));
        return (Size) c.uniqueResult();

    }

    public void save(Size size) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(size);
    }
}
