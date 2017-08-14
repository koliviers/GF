/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miki.webapp.databasetools;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Mikel
 */
public class FichierUtils {

    public static final String CONTAINS = "contains";
    public static final String EQUALS = "equals";

    /**
     * Cette methode retourne la liste des disque local
     *
     * @return
     */
    public static List<String> listerDisqueLocal() {
        File[] lecteurs = File.listRoots();
        FileSystemView fsv = FileSystemView.getFileSystemView();
        List<String> diskLocal = new ArrayList<>();

        for (File disk : lecteurs) {
            String DriveType = fsv.getSystemTypeDescription(disk);
            if (DriveType.toLowerCase().contains("disque local")) {
                diskLocal.add(disk.getPath());
            }
        }
        return diskLocal;
    }

    /**
     * cette methode retourne la liste des disque amovible
     *
     * @return
     */
    public static List<String> listerDisqueAmovible() {
        File[] lecteurs = File.listRoots();
        FileSystemView fsv = FileSystemView.getFileSystemView();
        List<String> diskLocal = new ArrayList<>();

        for (File disk : lecteurs) {
            String DriveType = fsv.getSystemTypeDescription(disk);
            if (DriveType.toLowerCase().contains("disque amovible")) {
                diskLocal.add(disk.getPath());
            }
        }
        return diskLocal;
    }

    /**
     * cette methode retourne la liste des lecteur de cd
     *
     * @return
     */
    public static List<String> listerLecteurCD() {
        File[] lecteurs = File.listRoots();
        FileSystemView fsv = FileSystemView.getFileSystemView();
        List<String> diskLocal = new ArrayList<>();

        for (File disk : lecteurs) {
            String DriveType = fsv.getSystemTypeDescription(disk);
            if (DriveType.toLowerCase().contains("lecteur de cd")) {
                diskLocal.add(disk.getPath());
            }
        }
        return diskLocal;
    }

    /**
     * Cette methode nous permet de rechercher un fichier dans un repertoire et
     * de nous renvoyer son lien s'il le trouve
     *
     * @param repertoire le lien du repertoire
     * @param fichierRecherche le nom ou l'extension ou un mot que contient le
     * nom du fichier
     * @param option option de la recherche Ex: FichierUtils.CONTAINS
     * @return une liste de string contenant le lien du fichier recherche
     */
    public static List<String> listeRepertoire(final Path repertoire, String fichierRecherche, String option) {
        List<String> resultat = new ArrayList<>();
        if (option.equals("contains") || option.equals("equals")) {
            if (Files.exists(repertoire) && Files.isDirectory(repertoire)) {
                try {
                    Files.walkFileTree(repertoire, new SimpleFileVisitor<Path>() {

                        // Peut être utilisé pour effectuer des actions sur le répertoire et les sous-répertoires en début de visite.
                        @Override
                        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                            return FileVisitResult.CONTINUE;

                        }

                        // Peut être utilisé pour effectuer des actions sur les fichiers dans chaque répertoire au cours de la visite.
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                            switch (option) {
                                case "contains":
                                    if (file.toFile().getName().toLowerCase().contains(fichierRecherche.toLowerCase())) {
                                        resultat.add(file.toFile().getPath());
                                    }
                                    break;
                                case "equals":
                                    if (file.toFile().getName().toLowerCase().equals(fichierRecherche.toLowerCase())) {
                                        resultat.add(file.toFile().getPath());
                                    }
                                    break;
                            }

                            return FileVisitResult.CONTINUE;
                        }

                        // Peut être utilisé pour effectuer des actions sur le répertoire et les sous-répertoires en fin de visite.
                        @Override
                        public FileVisitResult visitFileFailed(Path dir, IOException exc) throws IOException {
                            return FileVisitResult.SKIP_SUBTREE;
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(repertoire + " : Erreur de lecture");
                }
            }
        } else {
            System.out.println("Option non valide");
        }

        return resultat;
    }

    /**
     * Cette methode nous permet de rechercher un fichier sur chaque disque
     * local se trouvant sur la machine, dans un dossier et de renvoyer son lien
     * s'il le trouve
     *
     * @param dossier le lien du dossier (hormis les caractères des disques
     * local) ou il doit rechercher le fichier Ex: si je dois faire une
     * recherche dans "c:\Gespharma\test" alors dossier = "Gespharma\\test"
     * @param fichierRecherche le nom du fichier
     * @param option option de la recherche Ex: FichierUtils.CONTAINS
     * @return une liste de string contenant le(s) liens du fichier recherche
     */
    public static List<String> rechercherFichierParDisqueLocal(String dossier, String fichierRecherche, String option) {
        List<String> disque = listerDisqueLocal();
        try {
            for (String d : disque) {
                String direct = d + "\\" + dossier;

                File dir = new File(direct);
                if (dir.exists() && dir.isDirectory()) {
                    return listeRepertoire(Paths.get(direct), fichierRecherche, option);
                } else {
                    return null;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Cette methode nous permet de rechercher un fichier dans un dossier et de
     * renvoyer son lien s'il le trouve
     *
     * @param dossier le lien du dossier Ex: "c:\\Gespharma\\test"
     * @param fichierRecherche le nom du fichier
     * @param option option de la recherche Ex: FichierUtils.CONTAINS
     * @return une liste de string contenant le(s) liens du fichier recherche
     */
    public static List<String> rechercherFichier(String dossier, String fichierRecherche, String option) {
        try {
            File dir = new File(dossier);
            if (dir.exists() && dir.isDirectory()) {
                return listeRepertoire(Paths.get(dossier), fichierRecherche, option);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Cette methode verifie si un dossier existe ou pas
     *
     * @param nomDossier le nom du dossier a verifier
     * @return
     */
    public static boolean testExistenceDossier(String nomDossier) {
        File dossier = new File(nomDossier);

        if (dossier.exists() && dossier.isDirectory()) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 
     * @param lien lien du dossier à transformer en dossier caché
     * @return 
     */
    public static String transformationLinuxDossierCache(String lien) {
        try {

            if (lien.contains("/")) {

                if (lien.startsWith("/")) {

                   
                    String[] tableauSplit = lien.split("/");
                    List<String> resultatTableau = new ArrayList<>();
                    List<String> resultatTableau2 = new ArrayList<>();
                    List<String> resultatTableau3 = new ArrayList<>();
                    String resultat="";

                    for (int i = 0; i < tableauSplit.length; i++) {
                            resultatTableau.add(tableauSplit[i]);  
                    }
                                          
                    
                    for(int i2= resultatTableau.size()-1; i2>=0; i2--){
                        resultatTableau2.add(resultatTableau.get(i2));
                    }
                    
                    
                    for(int i3 = resultatTableau2.size()-1; i3>=0; i3--){
                        
                        if(i3 == 0){
                            resultatTableau3.add("/."+resultatTableau2.get(i3));
                        }else if(i3 == resultatTableau2.size()-1){
                            resultatTableau3.add("");
                        }else{
                            resultatTableau3.add("/"+resultatTableau2.get(i3));
                        }
                    }                    
                    
                    for(String rslt : resultatTableau3){
                        resultat = resultat + rslt;
                    }
                    
                    return resultat;
                    

                } else {

                    System.out.println("Mauvais lien = " + lien);
                    return null;

                }

            } else {
                System.out.println("Mauvais lien = " + lien);
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Cette methode nous permet de creer et de cacher un dossier
     *
     * @param lienDossier lien du dossier à creer
     * @return boolean
     * @throws IOException
     * @throws InterruptedException
     */
    public static boolean creationDossierCache(String lienDossier) throws IOException, InterruptedException {
        boolean exist = false;
        boolean resultat = false;
        File dossier;

        try {

            if (System.getProperty("os.name").equals("Linux")) {
                
                exist = testExistenceDossier(transformationLinuxDossierCache(lienDossier));

                if (exist == false) {
                    String lienLinux = transformationLinuxDossierCache(lienDossier);
                    dossier = new File(lienLinux);
                    resultat = dossier.mkdirs();                  
                } else {
                    System.out.println("Le dossier existe deja !");
                    resultat = true;
                }

            } else {
                
                exist = testExistenceDossier(lienDossier);

                if (exist == false) {
                    dossier = new File(lienDossier);
                    dossier.mkdirs();

                    Process pr = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "attrib", "+s", "+h", lienDossier});
                    int code = pr.waitFor();
                    if (code == 0) {
                        System.out.println("Gestion de dossier effectuée avec succès");
                        resultat = true;
                    } else {
                        System.out.println("Echec de l'operation gestion de dossier !");
                        resultat = false;
                    }
                } else {
                    System.out.println("Le dossier existe deja !");
                    resultat = true;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            resultat = false;
        }

        return resultat;
    }

}
