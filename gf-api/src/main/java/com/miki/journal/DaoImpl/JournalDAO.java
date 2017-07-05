/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miki.journal.DaoImpl;


import com.miki.journal.Dao.IJournalDAO;
import com.miki.journal.entities.Journal;
import com.miki.webapp.core.DaoImpl.BaseDaoBeanImpl;
import javax.ejb.Stateless;

/**
 *
 * @author Mikel
 */
@Stateless
public class JournalDAO extends BaseDaoBeanImpl<Journal, Long> implements IJournalDAO {

    public JournalDAO() {
        super(Journal.class);
    }
    
}
