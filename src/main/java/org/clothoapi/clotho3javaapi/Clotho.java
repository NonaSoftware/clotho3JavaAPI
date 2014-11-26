/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.clothoapi.clotho3javaapi;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.eclipse.jetty.websocket.WebSocket;

/**
 *
 * @author prashantvaidyanathan
 */

@Slf4j
public class Clotho implements MessageListener
{
    
    public static Object receivedObject;
    private static boolean received;
    
    public static String requestId;
    public static Channel channel;
    
    
    private static WebSocket.Connection connection;
  
    public Clotho(ClothoConnection clothoConn)
    {
        System.out.println("New Connection established");
        connection = clothoConn.getServerConnection();
        clothoConn.clothoSocket.addMessageListener(this);
    }
    
    public static Object query(String queryString)
    {
        channel = Channel.queryOne;
        received = false;
        
        try {
            connection.sendMessage(queryString);
            while(!received)
            {
                System.out.println("Waiting");
            }
            received = false;
            return receivedObject;
            
        } catch (IOException ex) {
            Logger.getLogger(Clotho.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    @Override
    public void messageRecieved(OnMessageEvent event) 
    {
        String message = event.getMessage();
        System.out.println("This is a message :" + message);
        //System.out.println("Got a result : " + event.getMessage());
        //System.out.println("Got a result : "+message);
        JSONObject messageObject = JSONObject.fromObject(message);
        try
        {
            if(messageObject.get("channel").equals("say"))
            {
                //System.out.println("Data : \n"+messageObject.get("data"));
                JSONObject dataObject = JSONObject.fromObject(messageObject.get("data"));
                if(dataObject.get("text") == null)
                {
                    System.out.println("No results found!");
                    received = true;
                    receivedObject = messageObject.get("data");
                }
                else
                {
                    System.out.println(dataObject.get("text"));
                }
            }
            if(messageObject.get("channel").equals("queryOne"))
            {
                receivedObject = messageObject.get("data");
                received = true;
            }
            System.out.println("Message Channel : " + messageObject.get("channel"));
        }
        catch(Exception e)
        {
            System.out.println("Error accessing one of the object values");
        }
    }
    
}
