/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.imp.service;

import com.kol.gf.dao.bean.GenericDAOBeanLocal;
import com.kol.gf.service.GenericServiceBeanLocal;
import com.kol.gf.utils.FilterParams;
import com.kol.gf.utils.SortParams;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author kol
 */
public abstract class GenericServiceBean<E extends Serializable, ID> implements GenericServiceBeanLocal<E, ID> {

    protected abstract GenericDAOBeanLocal<E, ID> getDAO();

    public GenericServiceBean() {

    }

    @Override
    public void addOne(E e) {

        this.getDAO().addOne(e);
    }

    @Override
    public void addAll(Collection<E> es) {

        for (E e : es) {
            this.getDAO().addOne(e);
        }
    }

    @Override
    public E updateOne(E e) {

        return this.getDAO().updateOne(e);

    }

    @Override
    public Collection<E> updateAll(Collection<E> es) {
        Collection<E> c = new ArrayList<>();
        for (E e : es) {
            this.getDAO().updateOne(e);
            c.add(e);
        }
        return c;

    }

    @Override
    public void deleteOne(E e) {

        this.getDAO().deleteOne(e);

    }

    @Override
    public void deleteAll(Collection<E> es) {
        for (E e : es) {
            this.getDAO().deleteOne(e);
        }

    }

    @Override
    public void deleteOne(ID id) {

        E e = this.getDAO().getOne(id);
        this.getDAO().deleteOne(e);
    }

    @Override
    public void deleteOne(Collection<ID> ids) {
        for (ID id : ids) {
            this.getDAO().deleteOne(id);
        }
    }

    @Override
    public void deleteAll() {
        this.getDAO().deleteAll();

    }

    @Override
    public E getOne(ID id) {

        return this.getDAO().getOne(id);
    }

    @Override
    public List<E> getAll() {

        return this.getDAO().getAll();
    }

    @Override
    public List<E> getAll(String sortProperty, boolean sortAsc) {
        return this.getDAO().getAll(SortParams.from(sortProperty, sortAsc));
    }

    @Override
    public List<E> getAll(SortParams sortParams) {
        return this.getDAO().getAll(sortParams);

    }

    @Override
    public List<E> getAll(SortParams sortParams, FilterParams filterParams) {

        return this.getDAO().getAll(sortParams, filterParams);

    }

    @Override
    public List<E> getAll(int first, int size, String sortProperty, boolean sortAsc) {

        return this.getDAO().getAll(first, size, SortParams.from(sortProperty, sortAsc));
    }

    @Override
    public List<E> getAll(int first, int size, SortParams sortParams) {
        return this.getDAO().getAll(first, size, sortParams);
    }

    @Override
    public List<E> getAll(int first, int size, SortParams sortParams, FilterParams filterParams) {

        return this.getDAO().getAll(first, size, sortParams, filterParams);

    }

    @Override
    public Long count() {
        return this.getDAO().count();
    }

    @Override
    public boolean exists(E e) {

        return this.getDAO().getOne(this.getId(e)) != null;
    }

    @Override
    public boolean exists(ID id) {

        return this.getDAO().getOne(id) != null;
    }

    @Override
    public <E> List<E> getBy(String sortProperty, E sortValue) {
         return this.getDAO().getBy(sortProperty, sortValue);
    }
    
    

}
