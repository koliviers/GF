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
public class InvalidSenderIdException extends RuntimeException {

    /**
     * Creates a new instance of <code>InvalidSenderIdException</code> without
     * detail message.
     */
    public InvalidSenderIdException() {
    }

    /**
     * Constructs an instance of <code>InvalidSenderIdException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidSenderIdException(String msg) {
        super(msg);
    }
    
    
    
}
