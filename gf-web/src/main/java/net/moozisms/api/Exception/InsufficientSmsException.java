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
public class InsufficientSmsException extends RuntimeException {

    /**
     * Creates a new instance of <code>InsufficientSmsException</code> without
     * detail message.
     */
    public InsufficientSmsException() {
    }

    /**
     * Constructs an instance of <code>InsufficientSmsException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InsufficientSmsException(String msg) {
        super(msg);
    }
}
