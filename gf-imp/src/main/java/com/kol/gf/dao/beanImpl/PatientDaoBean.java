/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.dao.beanImpl;

import com.kol.gf.dao.bean.PatientDaoBeanLocal;
import com.kol.gf.entities.Deces;
import com.kol.gf.entities.Patient;
import com.miki.webapp.core.DaoImpl.BaseDaoBeanImpl;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author kol
 */
@Stateless
public class PatientDaoBean extends BaseDaoBeanImpl<Patient, Long> implements PatientDaoBeanLocal {

    public PatientDaoBean() {

        super(Patient.class);
    }

    @Override
    public List<Patient> patientNonDecedes() {
        try {
            Query query = this.em.createQuery("SELECT t FROM Deces t");
            Query query2 = this.em.createQuery("SELECT p FROM Patient p");

            List<Patient> patientTampon = query2.getResultList();

            List<Deces> decesTampon = query.getResultList();

            if (!decesTampon.isEmpty()) {
                for (Deces dc : decesTampon) {
                    patientTampon.remove(dc.getPatient());
                }

                return patientTampon.stream()
                        .sorted(Comparator.comparing(Patient::getNomComplet))
                        .collect(Collectors.toList());
            } else {
                if (patientTampon.isEmpty()) {
                    return patientTampon;
                } else {
                    return patientTampon.stream()
                            .sorted(Comparator.comparing(Patient::getNomComplet))
                            .collect(Collectors.toList());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
