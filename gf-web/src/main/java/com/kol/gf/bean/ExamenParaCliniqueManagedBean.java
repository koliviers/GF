/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;

import com.kol.gf.entities.Categorie;
import com.kol.gf.entities.ExamenParaclinique;
import com.kol.gf.entities.Medicament;
import com.kol.gf.entities.Services;
import com.kol.gf.service.CategorieSessionBeanLocal;
import com.kol.gf.service.ExamenParacliniqueSessionBeanLocal;
import com.kol.gf.service.ServiceServiceBeanLocal;
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
public class ExamenParaCliniqueManagedBean implements Serializable {

    private ExamenParaclinique examenParaclinique;
    private ExamenParaclinique examenParacliniqueExcell;
    private Categorie categorie;

    private List<ExamenParaclinique> examenParacliniqueListe;
    private List<Services> serviceListe;
    private List<Categorie> categorieListe;
    private List<Categorie> categorieListeModal;

    private InputStream inptStrm;

    @EJB
    private ExamenParacliniqueSessionBeanLocal examenParacliniqueServices;

    @EJB
    private ServiceServiceBeanLocal servicesServices;

    @EJB
    private CategorieSessionBeanLocal categorieServices;

    public ExamenParaCliniqueManagedBean() {
        examenParaclinique = new ExamenParaclinique();
        examenParacliniqueExcell = new ExamenParaclinique();

        examenParacliniqueListe = new ArrayList<>();

        categorieListe = new ArrayList<>();

        categorieListeModal = new ArrayList<>();

        categorie = new Categorie();
    }

    public void gestionExamenParaclinique() {
        UserTransaction tx = TransactionManager.getUserTransaction();

        try {
            if (examenParaclinique.getLabel().trim().isEmpty()) {
                Mtm.mikiMessageWarnSaisir("le nom de l'examen paraclinique");
            } else if (examenParaclinique.getCategorie() == null) {
                Mtm.mikiMessageWarnSelectionner("la catégorie de l'examen");
            } else if (examenParaclinique.getService() == null) {
                Mtm.mikiMessageWarnSelectionner("le service où s'éffectuera l'examen");
            } else {
                if (examenParaclinique.getId() == null) {
                    tx.begin();
                    examenParacliniqueServices.saveOne(examenParaclinique);
                    tx.commit();
                } else {
                    tx.begin();
                    examenParacliniqueServices.updateOne(examenParaclinique);
                    tx.commit();
                }

                Mtm.mikiMessageInfo();
                examenParaclinique = new ExamenParaclinique();
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

    public void renvoieExamenParaclinique(ExamenParaclinique classT) {
        examenParaclinique = classT;
    }

    public void annulerExamenParaclinique() {
        examenParaclinique = new ExamenParaclinique();
    }

    public void gestionCategorie() {
        UserTransaction tx = TransactionManager.getUserTransaction();

        try {
            if (categorie.getLabel().trim().isEmpty()) {
                Mtm.mikiMessageWarnSaisir("le nom de la categorie");
            } else {
                if (categorie.getId() == null) {
                    tx.begin();
                    categorieServices.saveOne(categorie);
                    tx.commit();
                } else {
                    tx.begin();
                    categorieServices.updateOne(categorie);
                    tx.commit();
                }

                Mtm.mikiMessageInfo();
                categorie = new Categorie();
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

    public void renvoieCategorie(Categorie categ) {
        categorie = categ;
    }

    public void annulerCategorie() {
        categorie = new Categorie();
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

                            if (examenParacliniqueServices.getBy("label", values.get(0)).isEmpty()) {
                                examenParacliniqueExcell.setLabel(values.get(0));
                                examenParacliniqueExcell.setCategorie(categorieServices.getOneBy("label", values.get(1)));
                                examenParacliniqueExcell.setService(servicesServices.getOneBy("nomService", values.get(2)));

                                tx.begin();
                                examenParacliniqueServices.saveOne(examenParacliniqueExcell);
                                tx.commit();

                                examenParacliniqueExcell = new ExamenParaclinique();
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

    public ExamenParaclinique getExamenParaclinique() {
        return examenParaclinique;
    }

    public void setExamenParaclinique(ExamenParaclinique examenParaclinique) {
        this.examenParaclinique = examenParaclinique;
    }

    public List<ExamenParaclinique> getExamenParacliniqueListe() {
        return examenParacliniqueServices.getAll("label", true);
    }

    public void setExamenParacliniqueListe(List<ExamenParaclinique> examenParacliniqueListe) {
        this.examenParacliniqueListe = examenParacliniqueListe;
    }

    public ExamenParacliniqueSessionBeanLocal getExamenParacliniqueServices() {
        return examenParacliniqueServices;
    }

    public void setExamenParacliniqueServices(ExamenParacliniqueSessionBeanLocal examenParacliniqueServices) {
        this.examenParacliniqueServices = examenParacliniqueServices;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public List<Services> getServiceListe() {
        return servicesServices.getAll("nomService", true);
    }

    public void setServiceListe(List<Services> serviceListe) {
        this.serviceListe = serviceListe;
    }

    public List<Categorie> getCategorieListe() {
        return categorieServices.getAll("label", true);
    }

    public void setCategorieListe(List<Categorie> categorieListe) {
        this.categorieListe = categorieListe;
    }

    public List<Categorie> getCategorieListeModal() {
        return categorieServices.getAll("label", true);
    }

    public void setCategorieListeModal(List<Categorie> categorieListeModal) {
        this.categorieListeModal = categorieListeModal;
    }

    public ServiceServiceBeanLocal getServicesServices() {
        return servicesServices;
    }

    public void setServicesServices(ServiceServiceBeanLocal servicesServices) {
        this.servicesServices = servicesServices;
    }

    public CategorieSessionBeanLocal getCategorieServices() {
        return categorieServices;
    }

    public void setCategorieServices(CategorieSessionBeanLocal categorieServices) {
        this.categorieServices = categorieServices;
    }

    public ExamenParaclinique getExamenParacliniqueExcell() {
        return examenParacliniqueExcell;
    }

    public void setExamenParacliniqueExcell(ExamenParaclinique examenParacliniqueExcell) {
        this.examenParacliniqueExcell = examenParacliniqueExcell;
    }

    public InputStream getInptStrm() {
        return inptStrm;
    }

    public void setInptStrm(InputStream inptStrm) {
        this.inptStrm = inptStrm;
    }

}
