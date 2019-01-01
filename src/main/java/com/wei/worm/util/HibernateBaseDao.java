package com.wei.worm.util;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class HibernateBaseDao extends HibernateDaoSupport {
    @Autowired
    public void setMySessionFactory(SessionFactory mySessionFactory){
        super.setSessionFactory(mySessionFactory);
    }
}
