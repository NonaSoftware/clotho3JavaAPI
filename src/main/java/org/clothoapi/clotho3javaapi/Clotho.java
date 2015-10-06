/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.clothoapi.clotho3javaapi;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.eclipse.jetty.websocket.WebSocket;
import org.json.simple.JSONValue;

/**
 *
 * @author prashantvaidyanathan
 */

@Slf4j
public class Clotho implements MessageListener
{
    
    public Object receivedObject;
    private boolean received;
    private boolean successfulResult;
    
    public String requestId;
    public Channel channel;
    
    
    private WebSocket.Connection connection;
  
    public Clotho(ClothoConnection clothoConn)
    {
        System.out.println("New Connection established");
        connection = clothoConn.getServerConnection();
        clothoConn.clothoSocket.addMessageListener(this);
    }
    
    private static String getRequestId()
    {
        String reqId = "";
        reqId += System.currentTimeMillis();
        //System.out.println("Generated Req ID :" + reqId);
        return reqId;
    }
    
    public Object createUser(String username,String password){
        Map createUserMap = new HashMap();
        createUserMap.put("username", username);
        createUserMap.put("password", password);
        return createUser(createUserMap);
    }
    
    public Object createUser(Map map) 
    {
        JSONObject resultObject = null;
        getRequestId();
        channel = Channel.createUser;
        received = false;
        successfulResult = false;
        try {
            StringWriter mapStringWriter = new StringWriter();
            JSONValue.writeJSONString(map, mapStringWriter);
            String mapText = mapStringWriter.toString();
            //System.out.println(jsonText);
            requestId = getRequestId();
            Map createUserMap = new HashMap();
            createUserMap.put("channel", channel.toString());
            createUserMap.put("data", map);
            createUserMap.put("requestId", requestId);

            StringWriter queryStringWriter = new StringWriter();
            JSONValue.writeJSONString(createUserMap, queryStringWriter);
            String queryString = queryStringWriter.toString();
            long startTime = System.currentTimeMillis();
            long elapsedTime = 0;
            connection.sendMessage(queryString);
            while((!received) && (elapsedTime < Args.maxTimeOut))
            {
                System.out.print("");
                elapsedTime = (System.currentTimeMillis() - startTime)/1000;
            }
            if(elapsedTime >= 10)
            {
                System.out.println("System time out. Please check your Clotho Connection");
            }
            received = false;
            
            if(successfulResult)
            {
                resultObject = JSONObject.fromObject(receivedObject);
            }
            return resultObject;
            
            
        } catch (IOException ex) {
            Logger.getLogger(Clotho.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Object login(String username,String password){
        Map loginMap = new HashMap();
        loginMap.put("username", username);
        loginMap.put("credentials", password);
        return login(loginMap);
    }
    
    
    public Object grant(Map map) 
    {
        JSONObject resultObject = null;
        getRequestId();
        channel = Channel.grant;
        received = false;
        successfulResult = false;
        try {
            StringWriter mapStringWriter = new StringWriter();
            JSONValue.writeJSONString(map, mapStringWriter);
            String mapText = mapStringWriter.toString();
            //System.out.println(jsonText);
            requestId = getRequestId();
            Map loginMap = new HashMap();
            loginMap.put("channel", channel.toString());
            loginMap.put("data", map);
            loginMap.put("requestId", requestId);

            StringWriter queryStringWriter = new StringWriter();
            JSONValue.writeJSONString(loginMap, queryStringWriter);
            String queryString = queryStringWriter.toString();
            long startTime = System.currentTimeMillis();
            long elapsedTime = 0;
            connection.sendMessage(queryString);
            while((!received) && (elapsedTime <Args.maxTimeOut))
            {
                System.out.print("");
                elapsedTime = (System.currentTimeMillis() - startTime)/1000;
            }
            if(elapsedTime >= 10)
            {
                System.out.println("System time out. Please check your Clotho Connection");
            }
            received = false;
            
            if(successfulResult)
            {
                resultObject = JSONObject.fromObject(receivedObject);
            }
            return resultObject;
            
            
        } catch (IOException ex) {
            Logger.getLogger(Clotho.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
    public Object login(Map map) 
    {
        JSONObject resultObject = null;
        getRequestId();
        channel = Channel.login;
        received = false;
        successfulResult = false;
        try {
            StringWriter mapStringWriter = new StringWriter();
            JSONValue.writeJSONString(map, mapStringWriter);
            String mapText = mapStringWriter.toString();
            //System.out.println(jsonText);
            requestId = getRequestId();
            Map loginMap = new HashMap();
            loginMap.put("channel", channel.toString());
            loginMap.put("data", map);
            loginMap.put("requestId", requestId);

            StringWriter queryStringWriter = new StringWriter();
            JSONValue.writeJSONString(loginMap, queryStringWriter);
            String queryString = queryStringWriter.toString();
            long startTime = System.currentTimeMillis();
            long elapsedTime = 0;
            connection.sendMessage(queryString);
            while((!received) && (elapsedTime <Args.maxTimeOut))
            {
                System.out.print("");
                elapsedTime = (System.currentTimeMillis() - startTime)/1000;
            }
            if(elapsedTime >= 10)
            {
                System.out.println("System time out. Please check your Clotho Connection");
            }
            received = false;
            
            if(successfulResult)
            {
                resultObject = JSONObject.fromObject(receivedObject);
            }
            return resultObject;
            
            
        } catch (IOException ex) {
            Logger.getLogger(Clotho.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Object logout() 
    {
        JSONObject resultObject = null;
        getRequestId();
        channel = Channel.logout;
        received = false;
        successfulResult = false;
        try {
            
            requestId = getRequestId();
            Map loginMap = new HashMap();
            loginMap.put("channel", channel.toString());
            loginMap.put("data", "");
            loginMap.put("requestId", requestId);

            StringWriter queryStringWriter = new StringWriter();
            JSONValue.writeJSONString(loginMap, queryStringWriter);
            String queryString = queryStringWriter.toString();
            long startTime = System.currentTimeMillis();
            long elapsedTime = 0;
            connection.sendMessage(queryString);
            while((!received) && (elapsedTime <Args.maxTimeOut))
            {
                System.out.print("");
                elapsedTime = (System.currentTimeMillis() - startTime)/1000;
            }
            if(elapsedTime >= 10)
            {
                System.out.println("System time out. Please check your Clotho Connection");
            }
            received = false;
            
            if(successfulResult)
            {
                resultObject = JSONObject.fromObject(receivedObject);
            }
            return resultObject;
            
            
        } catch (IOException ex) {
            Logger.getLogger(Clotho.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Object queryOne(Map map) 
    {
        JSONObject resultObject = null;
        getRequestId();
        channel = Channel.queryOne;
        received = false;
        successfulResult = false;
        try {
            StringWriter mapStringWriter = new StringWriter();
            JSONValue.writeJSONString(map, mapStringWriter);
            String mapText = mapStringWriter.toString();
            //System.out.println(jsonText);
            requestId = getRequestId();
            Map queryMap = new HashMap();
            queryMap.put("channel", channel.toString());
            queryMap.put("data", map);
            queryMap.put("requestId", requestId);

            StringWriter queryStringWriter = new StringWriter();
            JSONValue.writeJSONString(queryMap, queryStringWriter);
            String queryString = queryStringWriter.toString();
            long startTime = System.currentTimeMillis();
            long elapsedTime = 0;
            connection.sendMessage(queryString);
            while((!received) && (elapsedTime <Args.maxTimeOut))
            {
                System.out.print("");
                elapsedTime = (System.currentTimeMillis() - startTime)/1000;
            }
            if(elapsedTime >= 10)
            {
                System.out.println("System time out. Please check your Clotho Connection");
            }
            received = false;
            
            if(successfulResult)
            {
                resultObject = JSONObject.fromObject(receivedObject);
            }
            return resultObject;
            
            
        } catch (IOException ex) {
            Logger.getLogger(Clotho.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Object query(Map map) 
    {
        JSONArray resultObject = null;
        getRequestId();
        channel = Channel.query;
        received = false;
        successfulResult = false;
        try {
            StringWriter mapStringWriter = new StringWriter();
            JSONValue.writeJSONString(map, mapStringWriter);
            String mapText = mapStringWriter.toString();
            //System.out.println(jsonText);
            requestId = getRequestId();
            Map queryMap = new HashMap();
            queryMap.put("channel", channel.toString());
            queryMap.put("data", map);
            queryMap.put("requestId", requestId);

            StringWriter queryStringWriter = new StringWriter();
            JSONValue.writeJSONString(queryMap, queryStringWriter);
            String queryString = queryStringWriter.toString();
            long startTime = System.currentTimeMillis();
            long elapsedTime = 0;
            connection.sendMessage(queryString);
            while((!received) && (elapsedTime <Args.maxTimeOut))
            {
                System.out.print("");
                elapsedTime = (System.currentTimeMillis() - startTime)/1000;
            }
            if(elapsedTime >= 10)
            {
                System.out.println("System time out. Please check your Clotho Connection");
            }
            received = false;
            
            if(successfulResult)
            {
                resultObject = JSONArray.fromObject(receivedObject);
            }
            return resultObject;
            
            
        } catch (IOException ex) {
            Logger.getLogger(Clotho.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Object submit(String getString) 
    {
        JSONObject resultObject = null;
        getRequestId();
        channel = Channel.submit;
        received = false;
        successfulResult = false;
        try {
            /*StringWriter mapStringWriter = new StringWriter();
            JSONValue.writeJSONString(map, mapStringWriter);
            String mapText = mapStringWriter.toString();*/
            //System.out.println(jsonText);
            requestId = getRequestId();
            Map queryMap = new HashMap();
            queryMap.put("channel", channel.toString());
            queryMap.put("data", getString);
            queryMap.put("requestId", requestId);

            StringWriter queryStringWriter = new StringWriter();
            JSONValue.writeJSONString(queryMap, queryStringWriter);
            String queryString = queryStringWriter.toString();
            long startTime = System.currentTimeMillis();
            long elapsedTime = 0;
            connection.sendMessage(queryString);
            while((!received) && (elapsedTime <Args.maxTimeOut))
            {
                System.out.print("");
                elapsedTime = (System.currentTimeMillis() - startTime)/1000;
            }
            if(elapsedTime >= 10)
            {
                System.out.println("System time out. Please check your Clotho Connection");
            }
            received = false;
            
            if(successfulResult)
            {
                resultObject = JSONObject.fromObject(receivedObject);
            }
            return resultObject;
            
            
        } catch (IOException ex) {
            Logger.getLogger(Clotho.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Object get(String getString) 
    {
        JSONObject resultObject = null;
        getRequestId();
        channel = Channel.get;
        received = false;
        successfulResult = false;
        try {
            /*StringWriter mapStringWriter = new StringWriter();
            JSONValue.writeJSONString(map, mapStringWriter);
            String mapText = mapStringWriter.toString();*/
            //System.out.println(jsonText);
            requestId = getRequestId();
            Map queryMap = new HashMap();
            queryMap.put("channel", channel.toString());
            queryMap.put("data", getString);
            queryMap.put("requestId", requestId);

            StringWriter queryStringWriter = new StringWriter();
            JSONValue.writeJSONString(queryMap, queryStringWriter);
            String queryString = queryStringWriter.toString();
            long startTime = System.currentTimeMillis();
            long elapsedTime = 0;
            connection.sendMessage(queryString);
            while((!received) && (elapsedTime <Args.maxTimeOut))
            {
                System.out.print("");
                elapsedTime = (System.currentTimeMillis() - startTime)/1000;
            }
            if(elapsedTime >= 10)
            {
                System.out.println("System time out. Please check your Clotho Connection");
            }
            received = false;
            
            if(successfulResult)
            {
                resultObject = JSONObject.fromObject(receivedObject);
            }
            return resultObject;
            
            
        } catch (IOException ex) {
            Logger.getLogger(Clotho.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Object getAll(List<String> getStringList) 
    {
        JSONArray resultObject = null;
        getRequestId();
        channel = Channel.getAll;
        received = false;
        successfulResult = false;
        try {
            /*StringWriter mapStringWriter = new StringWriter();
            JSONValue.writeJSONString(map, mapStringWriter);
            String mapText = mapStringWriter.toString();*/
            //System.out.println(jsonText);
            requestId = getRequestId();
            Map queryMap = new HashMap();
            queryMap.put("channel", channel.toString());
            queryMap.put("data", getStringList);
            queryMap.put("requestId", requestId);

            StringWriter queryStringWriter = new StringWriter();
            JSONValue.writeJSONString(queryMap, queryStringWriter);
            String queryString = queryStringWriter.toString();
            long startTime = System.currentTimeMillis();
            long elapsedTime = 0;
            connection.sendMessage(queryString);
            while((!received) && (elapsedTime <Args.maxTimeOut))
            {
                System.out.print("");
                elapsedTime = (System.currentTimeMillis() - startTime)/1000;
            }
            if(elapsedTime >= 10)
            {
                System.out.println("System time out. Please check your Clotho Connection");
            }
            received = false;
            
            if(successfulResult)
            {
                resultObject = JSONArray.fromObject(receivedObject);
            }
            return resultObject;
            
            
        } catch (IOException ex) {
            Logger.getLogger(Clotho.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Object create(Map map) 
    {
        Object resultObject = null;
        getRequestId();
        channel = Channel.create;
        received = false;
        successfulResult = false;
        try {
            StringWriter mapStringWriter = new StringWriter();
            JSONValue.writeJSONString(map, mapStringWriter);
            String mapText = mapStringWriter.toString();
            //System.out.println(jsonText);
            requestId = getRequestId();
            Map queryMap = new HashMap();
            queryMap.put("channel", channel.toString());
            queryMap.put("data", map);
            queryMap.put("requestId", requestId);

            StringWriter queryStringWriter = new StringWriter();
            JSONValue.writeJSONString(queryMap, queryStringWriter);
            String queryString = queryStringWriter.toString();
            long startTime = System.currentTimeMillis();
            long elapsedTime = 0;
            connection.sendMessage(queryString);
            while((!received) && (elapsedTime <Args.maxTimeOut))
            {
                System.out.print("");
                elapsedTime = (System.currentTimeMillis() - startTime)/1000;
            }
            if(elapsedTime >= 10)
            {
                System.out.println("System time out. Please check your Clotho Connection");
            }
            received = false;
            
            if(successfulResult)
            {
                resultObject = receivedObject;
            }
            return resultObject;
            
            
        } catch (IOException ex) {
            Logger.getLogger(Clotho.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Object createAll(List<Map> map) 
    {
        Object resultObject = null;
        getRequestId();
        channel = Channel.createAll;
        received = false;
        successfulResult = false;
        try {
            StringWriter mapStringWriter = new StringWriter();
            JSONValue.writeJSONString(map, mapStringWriter);
            String mapText = mapStringWriter.toString();
            //System.out.println(jsonText);
            requestId = getRequestId();
            Map queryMap = new HashMap();
            queryMap.put("channel", channel.toString());
            queryMap.put("data", map);
            queryMap.put("requestId", requestId);

            StringWriter queryStringWriter = new StringWriter();
            JSONValue.writeJSONString(queryMap, queryStringWriter);
            String queryString = queryStringWriter.toString();
            long startTime = System.currentTimeMillis();
            long elapsedTime = 0;
            connection.sendMessage(queryString);
            while((!received) && (elapsedTime <Args.maxTimeOut))
            {
                System.out.print("");
                elapsedTime = (System.currentTimeMillis() - startTime)/1000;
            }
            if(elapsedTime >= 10)
            {
                System.out.println("System time out. Please check your Clotho Connection");
            }
            received = false;
            
            if(successfulResult)
            {
                resultObject = receivedObject;
            }
            return resultObject;
            
            
        } catch (IOException ex) {
            Logger.getLogger(Clotho.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Object set(Map map) 
    {
        Object resultObject = null;
        getRequestId();
        channel = Channel.set;
        received = false;
        successfulResult = false;
        try {
            StringWriter mapStringWriter = new StringWriter();
            JSONValue.writeJSONString(map, mapStringWriter);
            String mapText = mapStringWriter.toString();
            //System.out.println(jsonText);
            requestId = getRequestId();
            Map queryMap = new HashMap();
            queryMap.put("channel", channel.toString());
            queryMap.put("data", map);
            queryMap.put("requestId", requestId);

            StringWriter queryStringWriter = new StringWriter();
            JSONValue.writeJSONString(queryMap, queryStringWriter);
            String queryString = queryStringWriter.toString();
            long startTime = System.currentTimeMillis();
            long elapsedTime = 0;
            connection.sendMessage(queryString);
            while((!received) && (elapsedTime <Args.maxTimeOut))
            {
                System.out.print("");
                elapsedTime = (System.currentTimeMillis() - startTime)/1000;
            }
            if(elapsedTime >= 10)
            {
                System.out.println("System time out. Please check your Clotho Connection");
            }
            received = false;
            
            if(successfulResult)
            {
                resultObject = receivedObject;
            }
            return resultObject;
            
            
        } catch (IOException ex) {
            Logger.getLogger(Clotho.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Object setAll(List<Map> map) 
    {
        Object resultObject = null;
        getRequestId();
        channel = Channel.setAll;
        received = false;
        successfulResult = false;
        try {
            StringWriter mapStringWriter = new StringWriter();
            JSONValue.writeJSONString(map, mapStringWriter);
            String mapText = mapStringWriter.toString();
            //System.out.println(jsonText);
            requestId = getRequestId();
            Map queryMap = new HashMap();
            queryMap.put("channel", channel.toString());
            queryMap.put("data", map);
            queryMap.put("requestId", requestId);

            StringWriter queryStringWriter = new StringWriter();
            JSONValue.writeJSONString(queryMap, queryStringWriter);
            String queryString = queryStringWriter.toString();
            long startTime = System.currentTimeMillis();
            long elapsedTime = 0;
            connection.sendMessage(queryString);
            while((!received) && (elapsedTime <Args.maxTimeOut))
            {
                System.out.print("");
                elapsedTime = (System.currentTimeMillis() - startTime)/1000;
            }
            if(elapsedTime >= 10)
            {
                System.out.println("System time out. Please check your Clotho Connection");
            }
            received = false;
            
            if(successfulResult)
            {
                resultObject = receivedObject;
            }
            return resultObject;
            
            
        } catch (IOException ex) {
            Logger.getLogger(Clotho.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    @Override
    public void messageRecieved(OnMessageEvent event) 
    {
        String message = event.getMessage();
        String nullString = null;
        //System.out.println("This is a message :" + message);
        JSONObject messageObject = JSONObject.fromObject(message);
        try
        {
            if (this.requestId.equals(messageObject.get("requestId").toString())) 
            {
                if (messageObject.get("channel").equals("say")) 
                {

                    JSONObject dataObject = JSONObject.fromObject(messageObject.get("data"));
                    if (dataObject.get("text").equals(nullString)) 
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
                if (messageObject.get("channel").equals(this.channel.toString())) 
                {
                    successfulResult = true;
                    receivedObject = messageObject.get("data");
                    received = true;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Error accessing one of the object values");
        }
    }
    
}
