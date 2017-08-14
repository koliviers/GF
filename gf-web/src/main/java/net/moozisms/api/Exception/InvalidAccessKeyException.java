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
public class InvalidAccessKeyException extends RuntimeException {

    /**
     * Creates a new instance of <code>InvalidAccessKeyException</code> without
     * detail message.
     */
    public InvalidAccessKeyException() {
    }

    /**
     * Constructs an instance of <code>InvalidAccessKeyException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidAccessKeyException(String msg) {
        super(msg);
    }
}
