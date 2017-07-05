/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miki.webapp.journal.utils;

import java.net.URL;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.xml.DOMConfigurator;

/**
 *
 * @author Mikel
 */
public class JournalUtils {
    
     /**
     * Enregistre dans le journal grace a log4j
     * @param loggerName le nom de la classe concernée
     * @param priority priorité
     * @param message le message qui suit le log
     */
    public void logMikiLog4j(String loggerName, Priority priority, String message) {
        try {
            Logger loggerMiki = Logger.getLogger(loggerName);
            URL u = getClass().getClassLoader().getResource("log4j.xml");            
            DOMConfigurator.configure(u);
            loggerMiki.log(priority, message);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la journalisation, veuillez verifier les configurations svp !");
        }

    }
}
