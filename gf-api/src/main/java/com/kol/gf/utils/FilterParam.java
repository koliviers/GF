/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.utils;

import java.io.Serializable;
import javax.persistence.Query;

/**
 *
 * @author komilo
 */
public class FilterParam implements Serializable {
    
    public final static String DEFAULT_PREFIX = "e";
    
    private final String field;
    private final Object value;

    private FilterParam(String field, Object value) {
        this.field = field;
        this.value = value;
    }
    
    public static FilterParam from(String field, Object value) {
        return new FilterParam(field, value);
    }
    
    public boolean isValid() {
        return this.field != null && (! this.field.isEmpty());
    }
    
    public boolean isNotValid() {
        return this.field == null || this.field.isEmpty();
    }
    
    public String queryChunk() {
        return this.queryChunk(DEFAULT_PREFIX);
    }
    
    public String queryChunk(String prefix) {
        if (this.isNotValid()) {
            return "";
        }
        return prefix + "." + this.field + " = :" + this.getFieldQueryParameter();
    }
    
    public void setQueryParam(Query query) {
        if (this.isValid()) {
            query.setParameter(this.getFieldQueryParameter(), this.value);
        }
    }
    
    private String getFieldQueryParameter() {
        return this.field == null ? "" : this.field.replace('.', '_');
    }

    @Override
    public String toString() {
        return "FilterParam{" + "field=" + field + ", value=" + value + '}';
    }
}
