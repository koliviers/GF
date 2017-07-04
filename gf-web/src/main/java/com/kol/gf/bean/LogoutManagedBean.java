/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.bean;



import com.miki.webapp.shiro.EntityRealm;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.omnifaces.util.Faces;
//import org.omnifaces.util.Faces;

/**
 *
 * @author Mikel
 */

@ManagedBean
@RequestScoped
public class LogoutManagedBean implements Serializable{

    public void deconnexionUser() throws IOException {
//        Mtm.logMikiLog4j(LogoutManagedBean.class.getName(), Level.INFO, "Deconnexion");
        EntityRealm.getSubject().logout();
        Faces.invalidateSession();
        Faces.redirect("login.xhtml");
    }
    
}
