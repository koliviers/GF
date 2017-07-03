/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;

/**
 *
 * @author komilo
 */
public class FilterParams implements Serializable {

    private List<FilterParam> params;

    private FilterParams() {
        this.params = new ArrayList<>();
    }

    private FilterParams(Collection<FilterParam> params) {
        this();
        this.params.addAll(params);
    }

    private FilterParams(FilterParam... params) {
        this();
        this.params.addAll(Arrays.asList(params));
    }

    private FilterParams(Map<String, Object> map) {
        this();
        for (String key : map.keySet()) {
            this.params.add(FilterParam.from(key, map.get(key)));
        }
    }

    public static FilterParams from(Collection<FilterParam> filterParams) {
        return new FilterParams(filterParams);
    }

    public static FilterParams from(FilterParam... filterParams) {
        return new FilterParams(filterParams);
    }

    public static FilterParams from(String field, Object value) {
        return new FilterParams(FilterParam.from(field, value));
    }

    public static FilterParams from(Map<String, Object> map) {
        return new FilterParams(map);
    }

    public String queryChunk() {
        return this.queryChunk(FilterParam.DEFAULT_PREFIX);
    }
    
    public String queryChunk(String prefix) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (FilterParam param : this.params) {
            if (param.isValid()) {
                if (i >= 1) {
                    builder.append(" AND ");
                }
                builder.append(param.queryChunk(prefix));
                i++;
            }
        }
        return builder.toString();
    }
    
    public String queryChunkWithWhere() {
        return this.queryChunkWithWhere(FilterParam.DEFAULT_PREFIX);
    }
    
    public String queryChunkWithWhere(String prefix) {
        String chunk = this.queryChunk(prefix);
        return chunk.isEmpty() ? chunk : " WHERE " + chunk;
    }
    
    public void setQueryParams(Query query) {
        for (FilterParam param : this.params) {
            param.setQueryParam(query);
        }
    }
}
