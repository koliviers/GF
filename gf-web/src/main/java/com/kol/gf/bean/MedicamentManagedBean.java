/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.entities.Medicament;
import com.kol.gf.entities.Pathologie;
import com.kol.gf.service.MedicamentSessionBeanLocal;
import com.miki.webapp.core.Transaction.TransactionManager;
import com.miki.webapp.core.Utils.Mtm;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author anonymousghost
 */
@ManagedBean
@ViewScoped
public class MedicamentManagedBean implements Serializable {

    private Medicament medicament;

    private Medicament medicamentExcell;

    private List<Medicament> medicamentListe;

    private InputStream inptStrm;

    @EJB
    private MedicamentSessionBeanLocal medicamentServices;

    public MedicamentManagedBean() {
        medicament = new Medicament();

        medicamentExcell = new Medicament();

        medicamentListe = new ArrayList<>();
    }

    public void gestionMedicament() {
        UserTransaction tx = TransactionManager.getUserTransaction();

        try {
            if (medicament.getLabel().trim().isEmpty()) {
                Mtm.mikiMessageWarnSaisir("le nom du medicament");
            } else {
                if (medicament.getId() == null) {
                    tx.begin();
                    medicamentServices.saveOne(medicament);
                    tx.commit();
                } else {
                    tx.begin();
                    medicamentServices.updateOne(medicament);
                    tx.commit();
                }

                Mtm.mikiMessageInfo();
                medicament = new Medicament();
            }
        } catch (Exception ex) {
            try {
                tx.rollback();
            } catch (IllegalStateException ex1) {
                Logger.getLogger(ClasseTherapeutiqueManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SecurityException ex1) {
                Logger.getLogger(ClasseTherapeutiqueManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SystemException ex1) {
                Logger.getLogger(ClasseTherapeutiqueManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Mtm.mikiMessageError();
        }
    }

    public void renvoieMedicament(Medicament medoc) {
        medicament = medoc;
    }

    public void annulerMedicament() {
        medicament = new Medicament();
    }

    public void handleFileUpload(FileUploadEvent event) {
        try {
            inptStrm = event.getFile().getInputstream();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importer() throws IOException {
        UserTransaction tx = TransactionManager.getUserTransaction();

        if (inptStrm != null) {
            ArrayList<String> values = new ArrayList<String>();
            try {
                InputStream input = inptStrm;
                XSSFWorkbook wb = new XSSFWorkbook(input);
                XSSFSheet sheet = wb.getSheetAt(0);
                XSSFRow v = sheet.getRow(0);
                Iterator rows = sheet.rowIterator();
                while (rows.hasNext()) {
                    values.clear();
                    XSSFRow row = (XSSFRow) rows.next();
                    //if (row.getRowNum() > 0) {
                    Iterator cells = row.cellIterator();

                    while (cells.hasNext()) {
                        XSSFCell cell = (XSSFCell) cells.next();

                        if (XSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
                            values.add(Integer.toString((int) cell.getNumericCellValue()));
                        } else if (XSSFCell.CELL_TYPE_STRING == cell.getCellType()) {
                            values.add(cell.getStringCellValue());
                        }
                    }

                    //Les données se retrouve dans un tableau de String
                    //NB: chaque valeur du tableau values correspond aux données d'une ligne du fichier excel
                    if (!values.isEmpty()) {

                        if (!values.get(0).trim().isEmpty()) {

                            if (medicamentServices.getBy("label", values.get(0)).isEmpty()) {
                                medicamentExcell.setLabel(values.get(0));

                                tx.begin();
                                medicamentServices.saveOne(medicamentExcell);
                                tx.commit();

                                medicamentExcell = new Medicament();
                            }

                        } else {
                            int nbrLigne2 = row.getRowNum() + 1;
                            Mtm.mikiMessageWarn("Syntaxe de la ligne " + nbrLigne2 + " est incorrect !");
                        }

                    } else {
                        Mtm.mikiMessageWarn("Le contenu du fichier est vide !, veuillez choisir un fichier non vide svp !");
                        values.clear();
                    }
                }

                Mtm.mikiMessageInfo();

                inptStrm = null;
            } catch (Exception ex) {
                try {
                    tx.rollback();
                } catch (IllegalStateException ex1) {
                    Logger.getLogger(PathologieManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SecurityException ex1) {
                    Logger.getLogger(PathologieManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SystemException ex1) {
                    Logger.getLogger(PathologieManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                }

                ex.printStackTrace();

                Mtm.mikiMessageError();
            }
        } else {
            Mtm.mikiMessageWarn("Veuillez choisir le fichier a importer svp !");
        }

    }

    public void annulerImporter() {
        inptStrm = null;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }

    public List<Medicament> getMedicamentListe() {
        
        try {
            return medicamentServices.getAll("label",true);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }

    public void setMedicamentListe(List<Medicament> medicamentListe) {
        this.medicamentListe = medicamentListe;
    }

    public MedicamentSessionBeanLocal getMedicamentServices() {
        return medicamentServices;
    }

    public void setMedicamentServices(MedicamentSessionBeanLocal medicamentServices) {
        this.medicamentServices = medicamentServices;
    }

    public Medicament getMedicamentExcell() {
        return medicamentExcell;
    }

    public void setMedicamentExcell(Medicament medicamentExcell) {
        this.medicamentExcell = medicamentExcell;
    }

    public InputStream getInptStrm() {
        return inptStrm;
    }

    public void setInptStrm(InputStream inptStrm) {
        this.inptStrm = inptStrm;
    }

}
