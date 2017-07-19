/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.moozisms.api.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author anonymousghost
 */
public class Moozisms implements MoozismsApiClient{
    private String ApiKey;
    private String ApiSecret;
    private String To;
    private String From;
    private String Text;
    private String DataType;
    
    private final String Moozisms_URL = "http://api.moozisms.com";
    
    
    public boolean testConnection(){
        HttpURLConnection connection = null;
        try {
            URL testgoogle = new URL("http://www.google.com");
            connection = (HttpURLConnection) testgoogle.openConnection();
            connection.setRequestMethod("GET");
             
            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK){
                return false;
            }else{
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally{
            connection.disconnect();
        }
    }
    
    
    public boolean send(String api_key, String api_secret, String to, String from, String text){
        boolean result = false;
        
        try {
            

            this.ApiKey = api_key;
            
            this.ApiSecret = api_secret;
            
            this.To = to;
            
            this.From = from;
            
            this.Text = text;
            
            this.DataType = "json";
            
            
            String urlParameters = "api_key="+this.ApiKey+
                    "&api_secret="+this.ApiSecret+
                    "&to="+this.To+
                    "&from="+this.From+
                    "&text="+this.Text+
                    "&datatype="+this.DataType;
            
            System.out.println("Parametre : "+urlParameters+"\n");

            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
            
            int postDataLength = postData.length;
            
            URL restServiceURL = new URL(this.Moozisms_URL);

            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
            
            httpConnection.setDoOutput(true);
            
            httpConnection.setInstanceFollowRedirects(false);
            
            httpConnection.setRequestMethod("POST");
            
            httpConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            
            httpConnection.setRequestProperty("charset", "utf-8");
            
            httpConnection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            
            httpConnection.setUseCaches(false);
            
            try (DataOutputStream wr = new DataOutputStream(httpConnection.getOutputStream())) {
                wr.write(postData);
            }
            
            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader((httpConnection.getInputStream())));
            
            String buffLine;
            String output = "";
            
            while ((buffLine = responseBuffer.readLine()) != null) {
                //System.out.println(buffLine);
                output += buffLine;
            }
            
            String responseMessage = httpConnection.getResponseMessage();
            
            int responseCode = httpConnection.getResponseCode();
            
            if(responseCode == HttpURLConnection.HTTP_OK){
                
                if(!output.contains("\"report\":\"delivred\"")){
                    result = false;
                }else{
                    result = true;
                }
                
            }

            System.out.println("Response code : " + responseCode+"\n");
            
            System.out.println("Response message :" + responseMessage+"\n");

            System.out.println("Response from server : "+output+"\n");
            
            httpConnection.disconnect();
            
            return result;

        } catch (MalformedURLException ex) {

            ex.printStackTrace();
            
            return false;

        } catch (IOException e) {

            e.printStackTrace();
            
            return false;

        }
    }

    /**
     * 
     * @param api_key Your api_key generate from Moozisms plateform, example : vLaR6iXDoLrvSPzy
     * @param api_secret Your api_secret generate from Moozisms plateform, example : fbb2c6a0-5533-11e7-806e-cfdc8z11ccao
     * @param to Phonenumber of the recipient with code without 00 or +, example : 22890147895(00228 or +228 and 90147895, phonenumber from TOGO),13483158714(001 or +1 and 3483158714, phonenumber from USA)
     * @param from Sender ID less than 11 characters, example : Administration
     * @param text Your messages without the special characters
     * @return true if message is sent and false if message is not sent
     */
    @Override
    public boolean sendSimple(String api_key, String api_secret, String to, String from, String text){
        
        if(this.testConnection()){
            return this.send(api_key, api_secret, to, from, text);
        }else{
            return false;
        }
    }
    
    
    /**
      * 
      * @param api_key Your api_key generate from Moozisms plateform, example : vLaR6iXDoLrvSPzy
      * @param api_secret Your api_secret generate from Moozisms plateform, example : fbb2c6a0-5533-11e7-806e-cfdc8z11ccao
      * @param toList List of Phonenumber with code without 00 or +, example : 22890147895(00228 or +228 and 90147895, phonenumber from TOGO),13483158714(001 or +1 and 3483158714, phonenumber from USA)
      * @param from Sender ID less than 11 characters, example : Administration
      * @param text Your messages without the special characters
      * @return a Map wich contains phonenumber with acknowledgement of recipient or null if messages are not sent
      */
    @Override
    public Map<String,Boolean> sendGroup(String api_key, String api_secret, List<String> toList, String from, String text){
        
        if(this.testConnection()){
            Map<String,Boolean> result = new HashMap<>();
            
            for(String to : toList){
                boolean report = this.send(api_key, api_secret, to, from, text);
                result.put(to, report);
            }
            
            return result;
        }else{
            return null;
        }
    }
    
    
    
}
