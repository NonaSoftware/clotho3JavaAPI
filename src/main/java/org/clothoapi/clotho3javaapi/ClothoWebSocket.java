/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.clothoapi.clotho3javaapi;



import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
//import lombok.extern.slf4j.Slf4j;
/**
 *
 * @author prashantvaidyanathan
 */

//@Slf4j
public class ClothoWebSocket implements org.eclipse.jetty.websocket.WebSocket.OnTextMessage
{
    private Session session;
    
    

    @Override
    public void onMessage(String message) {
        System.out.println("Message says "+message);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onOpen(Connection connection) {
        System.out.println("Connection has been established!\nConnection id : "+connection);
        try {
            String helloString = "{\"channel\":\"say\",\"data\":\"Hello Clotho!\",\"requestId\":1}";
            
            connection.sendMessage(helloString);
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (IOException ex) {
            Logger.getLogger(ClothoWebSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onClose(int i, String string) {
        System.out.println("Connection closed.\nConnection Id : "+i+"\nClosing Message : "+string);
    }
    
}
