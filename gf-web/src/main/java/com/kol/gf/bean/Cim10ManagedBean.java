/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.entities.Cim10;
import com.kol.gf.service.Cim10SessionBeanLocal;
import com.miki.webapp.core.Transaction.TransactionManager;
import com.miki.webapp.core.Utils.Mtm;
import java.io.File;
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
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletContext;
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
public class Cim10ManagedBean implements Serializable {

    private Cim10 cim10;

    private Cim10 cim10Excell;

    private List<Cim10> cim10Liste;

    private InputStream inptStrm;

    @EJB
    private Cim10SessionBeanLocal cimServices;

    public Cim10ManagedBean() {
        cim10 = new Cim10();

        cim10Excell = new Cim10();

        cim10Liste = new ArrayList<>();

    }

    public void gesitonCim10() {
        UserTransaction tx = TransactionManager.getUserTransaction();

        try {
            if (cim10.getLabel().trim().isEmpty()) {
                Mtm.mikiMessageWarnSaisir("le nom de la pathologie");
            } else {
                if (cim10.getId() == null) {
                    tx.begin();
                    cimServices.saveOne(cim10);
                    tx.commit();
                } else {
                    tx.begin();
                    cimServices.updateOne(cim10);
                    tx.commit();
                }

                Mtm.mikiMessageInfo();
                cim10 = new Cim10();
            }
        } catch (Exception ex) {
            try {
                tx.rollback();
            } catch (IllegalStateException ex1) {
                Logger.getLogger(Cim10ManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SecurityException ex1) {
                Logger.getLogger(Cim10ManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SystemException ex1) {
                Logger.getLogger(Cim10ManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Mtm.mikiMessageError();
        }
    }

    public void renvoieCim(Cim10 cm) {
        cim10 = cm;
    }

    public void annulerCim() {
        cim10 = new Cim10();
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

                            if (cimServices.getBy("label", values.get(0)).isEmpty()) {
                                cim10Excell.setLabel(values.get(0));

                                tx.begin();
                                cimServices.saveOne(cim10Excell);
                                tx.commit();

                                cim10Excell = new Cim10();
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
                    Logger.getLogger(Cim10ManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SecurityException ex1) {
                    Logger.getLogger(Cim10ManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SystemException ex1) {
                    Logger.getLogger(Cim10ManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
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

    public Cim10 getCim10() {
        return cim10;
    }

    public void setCim10(Cim10 cim10) {
        this.cim10 = cim10;
    }

    public List<Cim10> getCim10Liste() {
        return cimServices.getAll("label", true);
    }

    public void setCim10Liste(List<Cim10> cim10Liste) {
        this.cim10Liste = cim10Liste;
    }

    public Cim10SessionBeanLocal getCimServices() {
        return cimServices;
    }

    public void setCimServices(Cim10SessionBeanLocal cimServices) {
        this.cimServices = cimServices;
    }

    public InputStream getInptStrm() {
        return inptStrm;
    }

    public void setInptStrm(InputStream inptStrm) {
        this.inptStrm = inptStrm;
    }

    public Cim10 getCim10Excell() {
        return cim10Excell;
    }

    public void setCim10Excell(Cim10 cim10Excell) {
        this.cim10Excell = cim10Excell;
    }

}
