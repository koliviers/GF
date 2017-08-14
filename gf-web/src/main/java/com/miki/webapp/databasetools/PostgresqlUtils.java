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
public class PostgresqlUtils {

    /**
     * Cette methode nous permet de faire une sauvegarde de base de donnee
     * postgresql grace aux donnees passes en parametre 
     *
     * @param lienDossier le lien du dossier de la sauvegarde ( si elle n'existe pas , elle va etre crée )
     * @param nomBaseDeDonnee le nom de la base de donnee
     * @param utilisateur l'utilisateur
     * @param hote l'hote qui heberge la base de donnee Ex: localhost
     * @param port le port Ex: 5432
     * @param lienRepertoirePG_DUMP le lien du repertoire du pg_dump Ex:
     * C:\\Program Files\\PostgreSQL\\9.3\\bin\\pg_dump.exe
     * @return boolean
     * @throws IOException
     * @throws InterruptedException
     */
    public static boolean backupBaseDeDonneePostgresql(String lienDossier, String nomBaseDeDonnee, String utilisateur, String hote, String port, String lienRepertoirePG_DUMP) throws IOException, InterruptedException {
        boolean creation = FichierUtils.creationDossierCache(lienDossier);
        boolean result = false;
        if (creation) {
            File repertoire = new File(lienRepertoirePG_DUMP);
            if (!repertoire.exists()) {
                System.out.println("Le repertoire de pg_dump n'existe pas !");
                result = false;
            } else {
                List<String> cmds = new ArrayList<String>();
                cmds.add(lienRepertoirePG_DUMP);
                cmds.add("-h");
                cmds.add(hote);
                cmds.add("-p");
                cmds.add(port);
                cmds.add("-U");
                cmds.add(utilisateur);
                cmds.add("-w");
                cmds.add("-F");
                cmds.add("c");
                cmds.add("-f");
                cmds.add(lienDossier + "\\Backup" + nomBaseDeDonnee.toUpperCase() + "" + System.currentTimeMillis() + ".backup");
                cmds.add(nomBaseDeDonnee);
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
        }else{
            System.out.println("Impossible de faire la sauvegarde car le dossier de sauvegarde ne peut être crée");
            result = false;
        }

        return result;
    }

    /**
     * Cette methode nous permet de faire une restauration de base de donnee
     * postgresql grace aux donnees passes en parametre
     *
     * @param nomBaseDeDonnee le nom de la base de donnee
     * @param utilisateur l'utilisateur
     * @param hote l'hote qui heberge la base de donnee Ex: localhost
     * @param port le port Ex: 5432
     * @param lienRepertoirePG_RESTORE le lien du repertoire du pg_restore Ex:
     * C:\\Program Files\\PostgreSQL\\9.3\\bin\\pg_restore.exe
     * @param lien le lien du fichier backup
     * @return boolean
     * @throws IOException
     * @throws InterruptedException
     */
    public static boolean restoreBaseDeDonneePostgresql(String nomBaseDeDonnee, String utilisateur, String hote, String port, String lienRepertoirePG_RESTORE, String lien) throws IOException, InterruptedException {
        boolean result = false;

        File repertoire = new File(lienRepertoirePG_RESTORE);
        if (!repertoire.exists()) {
            System.out.println("Repertoire de pg_restore n'existe pas !");
            result = false;
        } else {
            List<String> cmds = new ArrayList<String>();
            cmds.add(lienRepertoirePG_RESTORE);
            cmds.add("--host");
            cmds.add(hote);
            cmds.add("--port");
            cmds.add(port);
            cmds.add("--username");
            cmds.add(utilisateur);
            cmds.add("--dbname");
            cmds.add(nomBaseDeDonnee);
            cmds.add(lien);
            ProcessBuilder processB = new ProcessBuilder();
            Process pro = processB.command(cmds).start();
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

        return result;
    }
}
