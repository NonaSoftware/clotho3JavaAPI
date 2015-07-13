/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.clothoapi.clotho3javaapi;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

/**
 *
 * @author prash
 */
public class SecurityTest {
    
    @Test
    public void loginTest(){
        ClothoConnection conn = new ClothoConnection("wss://localhost:8443/websocket");
        Clotho clothoObject = new Clotho(conn);
        
        String username = "vprashant@live.com";
        String password = "1234";
        
        Map newUser = new HashMap();
        newUser.put("username", username);
        newUser.put("password", password);
        Object result1 = clothoObject.createUser(newUser);
        
        Map loginMap = new HashMap();
        loginMap.put("username", username);
        loginMap.put("credentials", password);
        Object result2 = clothoObject.login(loginMap);
        System.out.println("Result :: "+ result2.toString());
        
        
        Map personObject = new HashMap();
        personObject.put("firstname", "Prashant");
        personObject.put("username", "vprashant1");
        personObject.put("id", ((Map)result2).get("id").toString());
        
        
        System.out.println("Result 1 ID:: "+ ((Map)result1).get("id").toString());
        System.out.println("Result 2 ID:: "+ ((Map)result2).get("id").toString());
        
        clothoObject.set(personObject);
        
        conn.closeConnection();
        
         
    }
    
    
    //@Test
    public void remoteConnectionTest(){
        ClothoConnection conn = new ClothoConnection("wss://54.68.8.207:8443/websocket");
        Clotho clothoObject = new Clotho(conn);
        
        Map createUserMap = new HashMap();
        createUserMap.put("username", "vprashant1");
        createUserMap.put("password", "1234");
        
        Map result = new HashMap();
        result = (Map)(clothoObject.createUser(createUserMap));
        
        Map personObject = new HashMap();
        personObject.put("firstname", "Prashant");
        personObject.put("lastname", "Vaidyanathan");
        personObject.put("id", result.get("id").toString());
        clothoObject.set(personObject);
        
        conn.closeConnection();
    }
    
    //@Test
    public void remoteCreate(){
        ClothoConnection conn = new ClothoConnection("wss://54.68.8.207:8443/websocket");
        Clotho clothoObject = new Clotho(conn);
        
        
        Map createUserMap = new HashMap();
        createUserMap.put("username", "vprashant1");
        createUserMap.put("credentials", "1234");
        
        clothoObject.login(createUserMap);
        
        Map newObject = new HashMap();
        newObject.put("seq", "ATGC");
        newObject.put("name", "TestSeq");
        
        clothoObject.create(newObject);
        
        conn.closeConnection();
    }
    
    
    
    //@Test
    public void remoteOperations(){
        ClothoConnection conn = new ClothoConnection("wss://54.68.8.207:8443/websocket");
        Clotho clothoObject = new Clotho(conn);
        
        
        Map createUserMap = new HashMap();
        createUserMap.put("username", "vprashant1");
        createUserMap.put("credentials", "1234");
        
        clothoObject.login(createUserMap);
        
        Map newObject = new HashMap();
        newObject.put("schema", "org.clothocad.model.Person");
        newObject.put("name", "vprashant1");
        
        Object result = clothoObject.query(newObject);
        System.out.println("Result ::" + result.toString());
        
        Object getResult = clothoObject.get("55a2d402e4b0d88bfc624d7e");
        System.out.println("Get Result :: "+ getResult.toString());
        
        Map modifyPerson = new HashMap();
        modifyPerson.put("firstname", "Prashant");
        modifyPerson.put("lastname", "Vaidyanathan");
        modifyPerson.put("id", "55a2d402e4b0d88bfc624d7e");
        clothoObject.set(modifyPerson);
        
        conn.closeConnection();
    }
    
    //@Test
    public void createUserTest(){
        ClothoConnection conn = new ClothoConnection("wss://localhost:8443/websocket");
        Clotho clothoObject = new Clotho(conn);
        
        Map createUserMap = new HashMap();
        createUserMap.put("username", "vprashant1");
        createUserMap.put("password", "1234");
        
        Map result = new HashMap();
        result = (Map)(clothoObject.createUser(createUserMap));
        System.out.println("User Clotho Id" + result.get("id"));
        
        Map personObject2 = new HashMap();
        personObject2 = (Map)clothoObject.get(result.get("id").toString());
        System.out.println("Person :: "+ personObject2.toString());
        
        conn.closeConnection();
    }
    
}
