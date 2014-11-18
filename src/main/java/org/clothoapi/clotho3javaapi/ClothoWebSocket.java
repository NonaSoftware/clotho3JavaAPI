/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.clothoapi.clotho3javaapi;



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
    public void onMessage(String arg0) {
        System.out.println("Message says "+arg0);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onOpen(Connection cnctn) {
        System.out.println("Connection has been established!\nConnection id : "+cnctn);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onClose(int i, String string) {
        System.out.println("Connection closed.\nConnection Id : "+i+"\nConnection Message : "+string);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
