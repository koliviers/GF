/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.moozisms.api.client;

import java.util.List;
import java.util.Map;


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
      */
     public boolean sendSimple(String api_key,String api_secret,String to,String from,String text);
     
     
     /**
      * 
      * @param api_key Your api_key generate from Moozisms plateform, example : vLaR6iXDoLrvSPzy
      * @param api_secret Your api_secret generate from Moozisms plateform, example : fbb2c6a0-5533-11e7-806e-cfdc8z11ccao
      * @param toList List of Phonenumber with code without 00 or +, example : 22890147895(00228 or +228 and 90147895, phonenumber from TOGO),13483158714(001 or +1 and 3483158714, phonenumber from USA)
      * @param from Sender ID less than 11 characters, example : Administration
      * @param text Your messages without the special characters
      * @return a Map wich contains phonenumber with acknowledgement of recipient or null if messages are not sent
      */
     public Map<String,Boolean> sendGroup(String api_key, String api_secret, List<String> toList, String from, String text);
}
