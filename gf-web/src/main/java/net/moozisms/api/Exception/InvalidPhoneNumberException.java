/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.moozisms.api.Exception;

/**
 *
 * @author anonymousghost
 */
public class InvalidPhoneNumberException extends RuntimeException {

    /**
     * Creates a new instance of <code>InvalidPhoneNumberException</code>
     * without detail message.
     */
    public InvalidPhoneNumberException() {
    }

    /**
     * Constructs an instance of <code>InvalidPhoneNumberException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidPhoneNumberException(String msg) {
        super(msg);
    }
}
