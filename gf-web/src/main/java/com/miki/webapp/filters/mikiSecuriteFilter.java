/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miki.webapp.filters;

import com.kol.gf.dao.bean.IntervenantDaoBeanLocal;
import com.kol.gf.entities.Intervenant;
import com.miki.webapp.miki.securite.Dao.IUtilisateurDAO;
import com.miki.webapp.miki.securite.entities.Utilisateur;
import com.miki.webapp.shiro.EntityRealm;
import com.miki.webapp.shiro.utils.constante;
import javax.naming.InitialContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Mikel
 */
public class mikiSecuriteFilter implements Filter {

    private static final String PAGE_ACCUEIL = "dashboard.xhtml";
    private static final String PAGE_ERROR = "/access.xhtml";

    private FilterConfig filterConfig = null;

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        System.out.println("Initialisation du filtre :" + this.filterConfig.getFilterName());
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        HttpServletRequest request2 = (HttpServletRequest) request;
        String page = request2.getRequestURI().substring(request2.getContextPath().length() + 1);

        try {

            switch (page) {
                case "gf/administration/gestion.xhtml":
                    if (EntityRealm.getSubject().isPermitted(constante.ROLE_GESTION_SECURITE_CLE)) {
                        request2.getRequestDispatcher("gestion.xhtml").forward(request, response);
                    } else {
                        request2.getRequestDispatcher(PAGE_ERROR).forward(request, response);
                    }
                    break;

                case "gf/patient/gestionPat.xhtml":
                    if (EntityRealm.getSubject().isPermitted(constante.ROLE_CONSULTER_PATIENT_CLE)) {
                        request2.getRequestDispatcher("gestionPat.xhtml").forward(request, response);
                    } else {
                        request2.getRequestDispatcher(PAGE_ERROR).forward(request, response);
                    }
                    break;

                case "gf/consultation/gestionConsu.xhtml":
                    if (EntityRealm.getSubject().isPermitted(constante.ROLE_CONSULTER_CONSULTATION_CLE)) {
                        request2.getRequestDispatcher("gestionConsu.xhtml").forward(request, response);
                    } else {
                        request2.getRequestDispatcher(PAGE_ERROR).forward(request, response);
                    }
                    break;

                case "gf/intervenant/gestionInterv.xhtml":
                    if (EntityRealm.getSubject().isPermitted(constante.ROLE_CONSULTER_INTERVENANT_CLE)) {
                        request2.getRequestDispatcher("gestionInterv.xhtml").forward(request, response);
                    } else {
                        request2.getRequestDispatcher(PAGE_ERROR).forward(request, response);
                    }
                    break;

                case "gf/patient/deces.xhtml":
                    if (EntityRealm.getSubject().isPermitted(constante.ROLE_CONSULTER_PATIENT_CLE)) {
                        request2.getRequestDispatcher("deces.xhtml").forward(request, response);
                    } else {
                        request2.getRequestDispatcher(PAGE_ERROR).forward(request, response);
                    }
                    break;
                    
                case "gf/suivi/gestionSuivi.xhtml":
                    if (EntityRealm.getSubject().isPermitted(constante.ROLE_CONSULTER_CONSULTATION_CLE)) {
                        request2.getRequestDispatcher("gestionSuivi.xhtml").forward(request, response);
                    } else {
                        request2.getRequestDispatcher(PAGE_ERROR).forward(request, response);
                    }
                    break;

                case "dashboard.xhtml":
                    InitialContext context = new InitialContext();

                    IUtilisateurDAO userDAO;
                    IntervenantDaoBeanLocal intervenantDAO;

                    userDAO = (IUtilisateurDAO) context.lookup("java:global/gf-web/UtilisateurDAO");
                    intervenantDAO = (IntervenantDaoBeanLocal) context.lookup("java:global/gf-web/IntervenantDaoBean");
                    

                    Utilisateur utilisateurTampon = userDAO.getOneBy("login", EntityRealm.getUser().getLogin());
                    Intervenant intervenantTampon = intervenantDAO.getOneBy("utilisateur", utilisateurTampon);
                    
                    
                    
                    if(intervenantTampon == null){
                         request2.getRequestDispatcher("AccueilSecrétaire.xhtml").forward(request, response);
                    }else{
                         request2.getRequestDispatcher("AccueilIntervenant.xhtml").forward(request, response);
                    }
                    

                default:
                    chain.doFilter(request, response);
            }

        } catch (Exception e) {
            //erreur dans le filtre
            System.out.println("Erreur dans le filtre FiltreJournalisation");
        }
    }

    public void destroy() {
        System.out.println("Destruction du filtre : " + this.filterConfig.getFilterName());
    }

}
