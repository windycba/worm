package com.wei.worm.util;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class HibernateDao<T>{
    @Autowired
    private SessionFactory sessionFactory;
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Query createQuery(String hql, Object... parameters) {
        Query q = getSession().createQuery(hql);
        if (parameters != null) {
            for (int i = 0; i < parameters.length; ++i) {
                q.setParameter(i, parameters[i]);
            }
        }
        return q;
    }

    public Query createQuery(String queryString, Map<String, ?> parameters) {
        Query query = getSession().createQuery(queryString);
        if (parameters != null) {
            query.setProperties(parameters);
        }
        return query;
    }

    protected long countHqlResult(String hql, Map<String, ?> parameters) {
        String countHql = generateCountHql(hql);
        return ((Number) findUnique(countHql, parameters)).longValue();
    }
    protected long countHqlResult(String hql, Object... parameters) {
        String countHql = generateCountHql(hql);
        return ((Number) findUnique(countHql, parameters)).longValue();
    }

    @SuppressWarnings("unchecked")
    public <X> X findUnique(String hql, Map<String, ?> parameters) {
        return (X) createQuery(hql, parameters).uniqueResult();
    }
    @SuppressWarnings("unchecked")
    public <X> X findUnique(String hql, Object... parameters) {
        return (X) createQuery(hql, parameters).uniqueResult();
    }
    private String generateCountHql(String hql) {
        hql = "from " + StringUtils.substringAfter(hql, "from");
        hql = StringUtils.substringBefore(hql, "order by");
        String countHql = "select count(*) " + hql;
        return countHql;
    }
    protected Query setPageParameterToQuery(Query q, Page<T> page) {
        q.setFirstResult(page.getFrom());
        q.setMaxResults(page.getPageSize());
        return q;
    }

    @SuppressWarnings("unchecked")
    public Page<T> getPage(int pageSize, int pageNo, String hql,
                           Map<String, ?> parameters) {
        Page<T> page = new Page<T>(pageSize, pageNo);
        Query q = createQuery(hql, parameters);
        long totalCount = countHqlResult(hql, parameters);
        page.setTotal((int) totalCount);
        page.setTotalPage((int) totalCount/pageSize+1);
        setPageParameterToQuery(q, page);
        page.setRows(q.list());
        return page;
    }

    @SuppressWarnings("unchecked")
    public Page<T> getPage(int pageSize, int pageNo, String hql,
                           Object... parameters) {

        Query q = createQuery(hql, parameters);//创建查询
        long totalCount = countHqlResult(hql, parameters);//统计总数
        Page<T> page = new Page<T>(pageSize, pageNo);
        page.setTotal((int) totalCount);
        page.setTotalPage((int) totalCount/pageSize+1);
        setPageParameterToQuery(q, page);
        page.setRows(q.list());
        return page;
    }
}
