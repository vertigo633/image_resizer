package com.vertigo633.api.dao;

import com.vertigo633.api.entities.ImageOriginal;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Vertigo633 on 28.06.2015.
 */
@Repository
@Transactional
public class ImageOriginalDAOImpl implements ImageOriginalDAO {

    @Autowired
    SessionFactory sessionFactory;

    public ImageOriginal getImageOriginalById(Integer image_id) {
        Criteria c = sessionFactory.getCurrentSession().createCriteria(ImageOriginal.class).
                add(Restrictions.eq("id", image_id));
        return (ImageOriginal) c.uniqueResult();
    }

    public void save(ImageOriginal imageOriginal) {
        Session session = sessionFactory.getCurrentSession();
        session.save(imageOriginal);
        System.out.println("Image saved");
    }
}
