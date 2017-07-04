/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.bean;

import com.kol.gf.entities.Pathologie;
import javax.ejb.Stateless;

/**
 *
 * @author kol
 */
@Stateless
public class PathologieDaoBean extends GenericDaoBean<Pathologie, Long> implements PathologieDaoBeanLocal{
    
    public PathologieDaoBean(){
        super(Pathologie.class);
    }
    public PathologieDaoBean(Class<Pathologie> entiClass){
        this.entityClass=entiClass;
    }
}
