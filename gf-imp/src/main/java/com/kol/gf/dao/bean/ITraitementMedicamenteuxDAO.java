/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.bean;

import com.kol.gf.entities.TraitementMedicamenteux;
import com.kol.gf.entities.TraitementMedicamenteuxId;
import com.miki.webapp.core.Dao.BaseDaoBean;
import java.io.Serializable;
import javax.ejb.Local;

/**
 *
 * @author anonymousghost
 */
@Local
public interface ITraitementMedicamenteuxDAO extends BaseDaoBean<TraitementMedicamenteux, TraitementMedicamenteuxId>{
    
    public void supprimerTraitementMedicamenteux(TraitementMedicamenteuxId id);
    
}
