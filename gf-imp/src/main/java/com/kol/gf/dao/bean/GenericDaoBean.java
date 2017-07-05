/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.bean;

import com.kol.gf.utils.FilterParams;
import com.kol.gf.utils.SortParams;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kol
 */
public abstract class GenericDaoBean<E extends Serializable, ID> implements GenericDAOBeanLocal<E, ID> {

    @PersistenceContext(unitName = "gfPU")
    protected EntityManager em;
    protected Class<E> entityClass;

    public GenericDaoBean() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().
                getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[0];

    }

    public GenericDaoBean(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void addOne(E e) {
        this.em.persist(e);

    }

    @Override
    public E updateOne(E e) {

        return this.em.merge(e);
    }

    @Override
    public void deleteOne(E e) {

        this.em.remove(this.em.merge(e));
    }

    @Override
    public void deleteOne(ID id) {

        this.em.remove(this.getOne(id));
    }

    @Override
    public void deleteAll() {

        String jpql = "DELETE FROM " + this.entityClass.getSimpleName();
        Query query = this.em.createQuery(jpql);
        query.executeUpdate();
    }

    @Override
    public E getOne(ID id) {
        return this.em.find(this.entityClass, id);
    }

    @Override
    public List<E> getAll() {

        String jpql = "SELECT e FROM " + this.entityClass.getSimpleName() + " e";
        Query query = this.em.createQuery(jpql);
        return query.getResultList();
    }

    @Override
    public List<E> getAll(SortParams sortParams) {

        String jpql = "SELECT e FROM " + this.entityClass.getSimpleName() + " e "
                + sortParams.queryChunk();
        Query query = this.em.createQuery(jpql);
        return query.getResultList();
    }

    @Override
    public List<E> getAll(SortParams sortParams, FilterParams filterParams) {

        String jpql = "SELECT e FROM " + this.entityClass.getSimpleName() + " e "
                + filterParams.queryChunkWithWhere()
                + sortParams.queryChunk();
        Query query = this.em.createQuery(jpql);
        filterParams.setQueryParams(query);
        return query.getResultList();

    }

    @Override
    public List<E> getAll(int first, int size, SortParams sortParams) {

        String jpql = "SELECT e FROM " + this.entityClass.getSimpleName() + " e "
                + sortParams.queryChunk();
        Query query = this.em.createQuery(jpql);
        query.setFirstResult(first);
        query.setMaxResults(size);
        return query.getResultList();

    }

    @Override
    public List<E> getAll(int first, int size, SortParams sortParams, FilterParams filterParams) {

        String jpql = "SELECT e FROM " + this.entityClass.getSimpleName() + " e "
                + filterParams.queryChunkWithWhere()
                + sortParams.queryChunk();
        Query query = this.em.createQuery(jpql);
        filterParams.setQueryParams(query);
        query.setFirstResult(first);
        query.setMaxResults(size);
        return query.getResultList();
    }

    @Override
    public Long count() {

        String jpql = "SELECT COUNT(e) FROM " + this.entityClass.getSimpleName() + " e";
        Query query = this.em.createQuery(jpql);
        return (Long) query.getSingleResult();
    }

    @Override
    public boolean exists(ID id) {

        return this.getOne(id) != null;

    }
    
     @Override
    public <E> List<E> getBy(String sortProperty, E sortValue) {
        try {
            String jpql = "SELECT t FROM " + entityClass.getSimpleName()
                    + " t WHERE t." + sortProperty + " =:d "
                    + " ORDER BY t." + sortProperty + " ASC";
            Query query = em.createQuery(jpql);
            query.setParameter("d", sortValue);
            return query.getResultList();
        } catch (NoResultException exception) {
            exception.printStackTrace();
            return null;
        }
    }

}
