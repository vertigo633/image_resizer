package com.vertigo633.api.dao;

import com.vertigo633.api.entities.ImageOriginal;
import com.vertigo633.api.entities.ImageResized;
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
public class ImageResizedDAOImpl implements ImageResizedDAO {
    @Autowired
    SessionFactory sessionFactory;

    public ImageResized get(ImageOriginal imageOriginal, String size_name) {

        Criteria c = sessionFactory.getCurrentSession().createCriteria(ImageResized.class).
                add(Restrictions.eq("imageOriginal.id", imageOriginal.getId())).add(Restrictions.eq("size.name", size_name));
        return (ImageResized) c.uniqueResult();
    }

    public void save(ImageResized imageResized) {
        Session session = sessionFactory.getCurrentSession();
        session.save(imageResized);
    }
}
