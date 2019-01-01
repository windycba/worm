package com.wei.worm.dao;

import com.alibaba.fastjson.JSONObject;
import com.wei.entity.DoubleColorBall;
import com.wei.util.HibernateDao;
import com.wei.util.Page;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration
        ({"/springConfig.xml","/springMvc.xml"}) //加载配置文件
@Repository
public class DoubleColorBallDao extends HibernateDao{
    @Autowired
    private SessionFactory sessionFactory;

    public void save(DoubleColorBall doubleColorBall){
        Session session=sessionFactory.getCurrentSession();

        session.saveOrUpdate(doubleColorBall);
    }

    public Page<DoubleColorBall> query(int pageSize,int pageNo, JSONObject param) {
        StringBuffer hql=new StringBuffer("from DoubleColorBall u where  1=1");
        Map<String,Object> map=new HashMap<String,Object>();
        if(!"".equals(param.get("monthNum"))&&param.get("monthNum")!=null){
            map.put("monthNum",param.get("monthNum"));
            hql.append(" and monthNum=:monthNum");
        }
      return   this.getPage(pageSize,pageNo,hql.toString(),map);



    }

    public void delete(List<Integer> param) {
        String hql="delete DoubleColorBall  where id in :ids";
        Session session=sessionFactory.getCurrentSession();
        Query query=session.createQuery(hql);
        query.setParameterList("ids",param);

        query.executeUpdate();
    }
@Test
@Transactional
public void test(){
        this.query(1,1,null);
    }
}
