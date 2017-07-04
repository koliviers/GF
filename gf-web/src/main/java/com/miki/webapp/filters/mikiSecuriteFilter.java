/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miki.webapp.filters;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Mikel
 */
public class mikiSecuriteFilter implements Filter {

    private static final String PAGE_ACCUEIL = "dashboard.xhtml";
    private static final String PAGE_ERROR = "access.xhtml";

    private FilterConfig filterConfig = null;

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        System.out.println("Initialisation du filtre :" + this.filterConfig.getFilterName());
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        HttpServletRequest request2 = (HttpServletRequest) request;
        String page = request2.getRequestURI().substring(request2.getContextPath().length() + 1);
        try {

//            switch (page) {
//                case "Administration.xhtml":
//                    if (EntityRealm.getSubject().isPermitted(constante.ROLE_CONSULTER_LIVRAISON_CLE)) {
//                        request2.getRequestDispatcher("Administration.xhtml").forward(request, response);
//                    } else {
//                        request2.getRequestDispatcher(PAGE_ERROR).forward(request, response);
//                    }   break;
//                
//                default:
//                    chain.doFilter(request, response);
//            }
           
        } catch (Exception e) {
            //erreur dans le filtre
            System.out.println("Erreur dans le filtre FiltreJournalisation");
        }
    }

    public void destroy() {
        System.out.println("Destruction du filtre : " + this.filterConfig.getFilterName());
    }

}
