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
public class NetworkNotSupportedException extends RuntimeException {

    /**
     * Creates a new instance of <code>NetworkNotSupportedException</code>
     * without detail message.
     */
    public NetworkNotSupportedException() {
    }

    /**
     * Constructs an instance of <code>NetworkNotSupportedException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public NetworkNotSupportedException(String msg) {
        super(msg);
    }
}
