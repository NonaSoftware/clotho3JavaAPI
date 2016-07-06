/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.clothoapi.clotho3javaapi;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
//import lombok.extern.slf4j.Slf4j;
/**
 *
 * @author prashantvaidyanathan
 */

//@Slf4j
@WebSocket
public class ClothoWebSocket
{
   
    private String _messageData;
    private List _listeners = new ArrayList();
    
    @SuppressWarnings("unused")
    public Session session;
    
    @OnWebSocketMessage
    public void onMessage(String message) {
        
        _messageData = message;
        fireMessageEvent();
    }
    
    public synchronized void addMessageListener(MessageListener l)
    {
        _listeners.add(l);
    }
    
    public synchronized void removeMessageListener(MessageListener l)
    {
        _listeners.remove(l);
    }
    
    
    private synchronized void fireMessageEvent()
    {
        
        OnMessageEvent event = new OnMessageEvent(this,_messageData);
        //addMessageListener(new Message)
        Iterator listeners = _listeners.iterator();
        //messageRecieved(event);
        //System.out.println(_listeners.size());
        while(listeners.hasNext()){
            //System.out.println("Reached here!!");
            ((MessageListener) listeners.next()).messageRecieved(event);
        }
    }
    
    @OnWebSocketConnect
    public void onOpen(Session session) {
        this.session = session;
        System.out.println("Connection has been established!");
        /*try {
            String helloString = "{\"channel\":\"say\",\"data\":\"Establish Connection!\",\"requestId\":1}";
            connection.sendMessage(helloString);
        
        } catch (IOException ex) {
            Logger.getLogger(ClothoWebSocket.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason)
    {
        System.out.println("Connection closed.\nStatus Code: " + statusCode + "\nClosing Message : "+reason);
    }
}
