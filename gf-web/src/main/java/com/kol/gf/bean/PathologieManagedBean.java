/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.entities.Cim10;
import com.kol.gf.entities.Pathologie;
import com.kol.gf.service.PathologieServiceBeanLocal;
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
public class PathologieManagedBean implements Serializable {

    private Pathologie pathologie;

    private Pathologie pathologieExcell;

    private List<Pathologie> pathologieListe;

    private InputStream inptStrm;

    @EJB
    private PathologieServiceBeanLocal pathologieService;

    public PathologieManagedBean() {
        pathologie = new Pathologie();

        pathologieExcell = new Pathologie();

        pathologieListe = new ArrayList<>();
    }

    public void gestionPathologie() {
        UserTransaction tx = TransactionManager.getUserTransaction();

        try {
            if (pathologie.getNomPathologie().trim().isEmpty()) {
                Mtm.mikiMessageWarnSaisir("le nom de la pathologie");
            } else {
                if (pathologie.getId() == null) {
                    tx.begin();
                    pathologieService.saveOne(pathologie);
                    tx.commit();
                } else {
                    tx.begin();
                    pathologieService.updateOne(pathologie);
                    tx.commit();
                }

                Mtm.mikiMessageInfo();
                pathologie = new Pathologie();
            }
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
            Mtm.mikiMessageError();
        }
    }

    public void renvoiePathologie(Pathologie patho) {
        pathologie = patho;
    }

    public void annulerPathologie() {
        pathologie = new Pathologie();
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

                            if (pathologieService.getBy("nomPathologie", values.get(0)).isEmpty()) {

                                pathologieExcell.setNomPathologie(values.get(0));

                                tx.begin();
                                pathologieService.saveOne(pathologieExcell);
                                tx.commit();

                                pathologieExcell = new Pathologie();

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

    public Pathologie getPathologie() {
        return pathologie;
    }

    public void setPathologie(Pathologie pathologie) {
        this.pathologie = pathologie;
    }

    public List<Pathologie> getPathologieListe() {
        return pathologieService.getAll("nomPathologie", true);
    }

    public void setPathologieListe(List<Pathologie> pathologieListe) {
        this.pathologieListe = pathologieListe;
    }

    public PathologieServiceBeanLocal getPathologieService() {
        return pathologieService;
    }

    public void setPathologieService(PathologieServiceBeanLocal pathologieService) {
        this.pathologieService = pathologieService;
    }

    public InputStream getInptStrm() {
        return inptStrm;
    }

    public void setInptStrm(InputStream inptStrm) {
        this.inptStrm = inptStrm;
    }

    public Pathologie getPathologieExcell() {
        return pathologieExcell;
    }

    public void setPathologieExcell(Pathologie pathologieExcell) {
        this.pathologieExcell = pathologieExcell;
    }

}
