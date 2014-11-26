/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.clothoapi.clotho3javaapi;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author prashantvaidyanathan
 */
public class ClothoAPI {

    //private static WebSocket.Connection serverConnection;

    public static void main(String[] args) {
        
        
        ClothoConnection conn = new ClothoConnection("wss://localhost:8443/websocket");
        Clotho clothoObject = new Clotho(conn);
        
        String jsonQuery = "{\"channel\":\"queryOne\",\"data\":{\"name\":\"newPart\"},\"requestId\":3}";
        
        Map map = new HashMap();
        map.put("name","newPart");
        
        Object ret = clothoObject.query(map);
        
        System.out.println("The result is " + ret);
        
        conn.closeConnection();
        
        //<editor-fold desc="Old code. Back up">
        /*
        Clotho conn = new Clotho();

        String clothoURI = "wss://localhost:8443/websocket";
        WebSocketClientFactory factory = new WebSocketClientFactory();
        ClothoWebSocket clothoSocket = new ClothoWebSocket();
        try {
            factory.start();
            WebSocketClient wsClient = factory.newWebSocketClient();
            URI uri = new URI(clothoURI);
            Future fut = wsClient.open(uri, clothoSocket);
            serverConnection = (WebSocket.Connection) fut.get(10, TimeUnit.SECONDS);
            String jsonString = "{\"channel\":\"create\",\"data\":{\"name\":\"newPart\",\"sequence\":\"ATGCGTAGA\"},\"requestId\":2}";
            
            String jsonQuery = "{\"channel\":\"queryOne\",\"data\":{\"name\":\"newPart\"},\"requestId\":3}";

            clothoSocket.addMessageListener(conn);
            Object ret = conn.query(serverConnection, jsonQuery);
            System.out.println("This is it!" + ret);

        } catch (Exception ex) {
            Logger.getLogger(Clotho.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                //factory.stop();
            } catch (Exception ex) {
                Logger.getLogger(Clotho.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        */
        //</editor-fold >
        
    }
}
