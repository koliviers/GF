/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kol.gf.utils;

/**
 *
 * @author komilo
 */
public class CoreConstants {
    
    // Permissions
    public static final String PERM_ALL = "*";
    public static final String PERM_SECURITY_ALL = "security:*";
    public static final String PERM_SECURITY_USER_ALL = "security:user:*";
    public static final String PERM_SECURITY_USER_ADD = "security:user:add";
    public static final String PERM_SECURITY_USER_EDIT = "security:user:edit";
    public static final String PERM_SECURITY_USER_VIEW = "security:user:view";
    public static final String PERM_SECURITY_USER_LIST = "security:user:list";
    public static final String PERM_SECURITY_USER_DELETE = "security:user:delete";
    public static final String PERM_SECURITY_ROLE_ALL = "security:role:*";
    public static final String PERM_SECURITY_ROLE_CHANGE_ANY_PASSWORD = "security:role:changeanypassword";
    public static final String PERM_SECURITY_ROLE_CHANGE_OWN_PASSWORD = "security:role:changeownpassword";
    public static final String PERM_SECURITY_ROLE_ADD = "security:role:add";
    public static final String PERM_SECURITY_ROLE_EDIT = "security:role:edit";
    public static final String PERM_SECURITY_ROLE_VIEW = "security:role:view";
    public static final String PERM_SECURITY_ROLE_LIST = "security:role:list";
    public static final String PERM_SECURITY_ROLE_DELETE = "security:role:delete";
    public static final String PERM_LOG_ALL = "log:*";
    public static final String PERM_LOG_VIEW = "log:view";
    public static final String PERM_LOG_LIST = "log:list";
    
    // Catégories de logs
    public static final String LOG_CORE = "core";
    public static final String LOG_SECURITY = "security";
    public static final String LOG_SCHEDULER = "scheduler";
}
