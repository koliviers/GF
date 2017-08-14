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
public class InternalErrorException extends RuntimeException {

    /**
     * Creates a new instance of <code>InternalErrorException</code> without
     * detail message.
     */
    public InternalErrorException() {
    }

    /**
     * Constructs an instance of <code>InternalErrorException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InternalErrorException(String msg) {
        super(msg);
    }
}
