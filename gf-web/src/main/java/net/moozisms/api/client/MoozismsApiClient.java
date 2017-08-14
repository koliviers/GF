/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.moozisms.api.client;

import java.util.List;
import java.util.Map;
import net.moozisms.api.Exception.InsufficientSmsException;
import net.moozisms.api.Exception.InternalErrorException;
import net.moozisms.api.Exception.InvalidAccessKeyException;
import net.moozisms.api.Exception.InvalidPhoneNumberException;
import net.moozisms.api.Exception.InvalidSenderIdException;
import net.moozisms.api.Exception.MessageNotSentException;
import net.moozisms.api.Exception.NetworkNotSupportedException;


/**
 *
 * @author anonymousghost
 */
public interface MoozismsApiClient {
        
    
    
     
     
     /**
      * 
      * @param api_key Your api_key generate from Moozisms plateform, example : vLaR6iXDoLrvSPzy
      * @param api_secret Your api_secret generate from Moozisms plateform, example : fbb2c6a0-5533-11e7-806e-cfdc8z11ccao
      * @param to Phonenumber of the recipient with code without 00 or +, example : 22890147895(00228 or +228 and 90147895, phonenumber from TOGO),13483158714(001 or +1 and 3483158714, phonenumber from USA)
      * @param from Sender ID less than 11 characters, example : Administration
      * @param text Your messages without the special characters
      * @return true if message is sent and false if message is not sent
      * @throws net.moozisms.api.Exception.InternalErrorException
     * @throws net.moozisms.api.Exception.MessageNotSentException
     * @throws net.moozisms.api.Exception.NetworkNotSupportedException
     * @throws net.moozisms.api.Exception.InsufficientSmsException
     * @throws net.moozisms.api.Exception.InvalidAccessKeyException
     * @throws net.moozisms.api.Exception.InvalidPhoneNumberException
     * @throws net.moozisms.api.Exception.InvalidSenderIdException 
      */
     public boolean sendSimple(String api_key,String api_secret,String to,String from,String text) throws InternalErrorException, MessageNotSentException, NetworkNotSupportedException, InsufficientSmsException, InvalidAccessKeyException, InvalidPhoneNumberException, InvalidSenderIdException ;
     
     
     /**
      * 
      * @param api_key Your api_key generate from Moozisms plateform, example : vLaR6iXDoLrvSPzy
      * @param api_secret Your api_secret generate from Moozisms plateform, example : fbb2c6a0-5533-11e7-806e-cfdc8z11ccao
      * @param toList List of Phonenumber with code without 00 or +, example : 22890147895(00228 or +228 and 90147895, phonenumber from TOGO),13483158714(001 or +1 and 3483158714, phonenumber from USA)
      * @param from Sender ID less than 11 characters, example : Administration
      * @param text Your messages without the special characters
      * @return a Map wich contains phonenumber with acknowledgement of recipient or null if messages are not sent
      * @throws net.moozisms.api.Exception.InternalErrorException
     * @throws net.moozisms.api.Exception.MessageNotSentException
     * @throws net.moozisms.api.Exception.NetworkNotSupportedException
     * @throws net.moozisms.api.Exception.InsufficientSmsException
     * @throws net.moozisms.api.Exception.InvalidAccessKeyException
     * @throws net.moozisms.api.Exception.InvalidPhoneNumberException
     * @throws net.moozisms.api.Exception.InvalidSenderIdException
      */
     public Map<String,Boolean> sendGroup(String api_key, String api_secret, List<String> toList, String from, String text) throws InternalErrorException, MessageNotSentException, NetworkNotSupportedException, InsufficientSmsException, InvalidAccessKeyException, InvalidPhoneNumberException, InvalidSenderIdException ;
}
