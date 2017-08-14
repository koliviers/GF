/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miki.webapp.databasetools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mikel
 */
public class MysqlUtils {

    /**
     * Cette methode nous permet de faire une sauvegarde de base de donnee Mysql
     * grace aux donnees passes en parametre
     *
     * @param lienDossier le lien du dossier de la sauvegarde ( si elle n'existe
     * pas , elle va etre cree )
     * @param host nom de l'hote
     * @param nomBaseDeDonnee le nom de la base de donnee
     * @param utilisateur l'utilisateur
     * @param motDePasse mot de passe
     * @param lienRepertoireMysqlDump lien du repertoire mysqldump
     * @return boolean
     * @throws IOException
     * @throws InterruptedException
     */
    public static boolean backupBaseDeDonneeMysql(String lienDossier, String host, String nomBaseDeDonnee, String utilisateur, String motDePasse, String lienRepertoireMysqlDump) throws IOException, InterruptedException {
        boolean creation = FichierUtils.creationDossierCache(lienDossier);
        boolean result = false;

        try {

            if (System.getProperty("os.name").equals("Linux")) {

                if (creation) {

                    List<String> cmds = new ArrayList<String>();
                    cmds.add("mysqldump");
                    cmds.add("--default-character-set=utf8");
                    cmds.add("-h" + host);
                    cmds.add("-u" + utilisateur);
                    cmds.add("-p" + motDePasse);
                    cmds.add("-c");
                    cmds.add("-B");
                    cmds.add(nomBaseDeDonnee);
                    cmds.add("-r");
                    cmds.add(FichierUtils.transformationLinuxDossierCache(lienDossier) + "/Backup" + nomBaseDeDonnee.toUpperCase() + "" + System.currentTimeMillis() + ".sql");
                    ProcessBuilder processB = new ProcessBuilder();
                    Process pro = processB.command(cmds).start();
                    AfficheurFlux fluxSortie = new AfficheurFlux(pro.getInputStream());
                    AfficheurFlux fluxErreur = new AfficheurFlux(pro.getErrorStream());
                    new Thread(fluxSortie).start();
                    new Thread(fluxErreur).start();
                    int code = pro.waitFor();

                    if (code == 0) {
                        System.out.println("Sauvegarde reussie de la base de donnee :" + nomBaseDeDonnee);
                        result = true;
                    } else {
                        System.out.println("Echec lors de la sauvegarde de la base de donnee :" + nomBaseDeDonnee);
                        result = false;
                    }

                } else {
                    System.out.println("Impossible de faire la sauvegarde car le dossier de sauvegarde ne peut etre cree");
                    result = false;
                }

            } else {

                if (creation) {
                    File repertoire = new File(lienRepertoireMysqlDump);
                    if (!repertoire.exists()) {
                        System.out.println("Le repertoire de mysqldump n'existe pas !");
                        result = false;
                    } else {
                        List<String> cmds = new ArrayList<String>();
                        cmds.add(lienRepertoireMysqlDump);
                        cmds.add("--default-character-set=utf8");
                        cmds.add("-h" + host);
                        cmds.add("-u" + utilisateur);
                        cmds.add("-p" + motDePasse);
                        cmds.add("-c");
                        cmds.add("-B");
                        cmds.add(nomBaseDeDonnee);
                        cmds.add("-r");
                        cmds.add(lienDossier + "\\Backup" + nomBaseDeDonnee.toUpperCase() + "" + System.currentTimeMillis() + ".sql");
                        ProcessBuilder processB = new ProcessBuilder();
                        Process pro = processB.command(cmds).start();
                        AfficheurFlux fluxSortie = new AfficheurFlux(pro.getInputStream());
                        AfficheurFlux fluxErreur = new AfficheurFlux(pro.getErrorStream());
                        new Thread(fluxSortie).start();
                        new Thread(fluxErreur).start();
                        int code = pro.waitFor();

                        if (code == 0) {
                            System.out.println("Sauvegarde reussie de la base de donnee :" + nomBaseDeDonnee);
                            result = true;
                        } else {
                            System.out.println("Echec lors de la sauvegarde de la base de donnee :" + nomBaseDeDonnee);
                            result = false;
                        }
                    }

                } else {
                    System.out.println("Impossible de faire la sauvegarde car le dossier de sauvegarde ne peut etre cree");
                    result = false;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }

        return result;
    }

    /**
     * Cette methode nous permet de faire une restauration de base de donnee
     * Mysql grace aux donnees passes en parametre
     *
     * @param nomBaseDeDonnee le nom de la base de donnee
     * @param host le nom de l'hote
     * @param port port
     * @param utilisateur l'utilisateur
     * @param motDePasse le mot de passe
     * @param lien le lien du fichier backup
     * @param lienRepertoireMysql le lien du mysql
     * @return boolean
     * @throws IOException
     * @throws InterruptedException
     */
    public static boolean restoreBaseDeDonneeMysql(String nomBaseDeDonnee, String host, String port, String utilisateur, String motDePasse, String lien, String lienRepertoireMysql) throws IOException, InterruptedException {
        boolean result = false;

        try {

            if (System.getProperty("os.name").equals("Linux")) {

                String commande = "mysql" + " --host=" + host + " --port=" + port + " --user=" + utilisateur + " --password=" + motDePasse + " < " + lien;
                Process pro = Runtime.getRuntime().exec(new String[]{commande});
                AfficheurFlux fluxSortie = new AfficheurFlux(pro.getInputStream());
                AfficheurFlux fluxErreur = new AfficheurFlux(pro.getErrorStream());
                new Thread(fluxSortie).start();
                new Thread(fluxErreur).start();
                int code = pro.waitFor();

                if (code == 0) {
                    System.out.println("Restauration reussie de la base de donnee :" + nomBaseDeDonnee);
                    result = true;
                } else {
                    System.out.println("Echec lors de la restauration de la base de donnee :" + nomBaseDeDonnee);
                    result = false;
                }

            } else {

                File repertoire = new File(lienRepertoireMysql);
                if (!repertoire.exists()) {
                    System.out.println("Le repertoire de mysql n'existe pas !");
                    result = false;
                } else {
                    String commande = lienRepertoireMysql + " --host=" + host + " --port=" + port + " --user=" + utilisateur + " --password=" + motDePasse + " < " + lien;
                    Process pro = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", commande});
                    AfficheurFlux fluxSortie = new AfficheurFlux(pro.getInputStream());
                    AfficheurFlux fluxErreur = new AfficheurFlux(pro.getErrorStream());
                    new Thread(fluxSortie).start();
                    new Thread(fluxErreur).start();
                    int code = pro.waitFor();

                    if (code == 0) {
                        System.out.println("Restauration reussie de la base de donnee :" + nomBaseDeDonnee);
                        result = true;
                    } else {
                        System.out.println("Echec lors de la restauration de la base de donnee :" + nomBaseDeDonnee);
                        result = false;
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }

        return result;
    }
}
