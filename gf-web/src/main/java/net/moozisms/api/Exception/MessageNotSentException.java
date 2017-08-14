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
public class MessageNotSentException extends RuntimeException {

    /**
     * Creates a new instance of <code>MessageNotSentException</code> without
     * detail message.
     */
    public MessageNotSentException() {
    }

    /**
     * Constructs an instance of <code>MessageNotSentException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public MessageNotSentException(String msg) {
        super(msg);
    }
}
