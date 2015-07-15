/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.clothoapi.clotho3javaapi;

import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author prash
 */
public class SecurityTest {
    
    @Test
    public void createAndLoginTest(){
        ClothoConnection conn = new ClothoConnection(TestArgs.clothoLocalAddress);
        Clotho clothoObject = new Clotho(conn);
        
        
        String username = "test" + System.currentTimeMillis();  ;
        String password = "testPassword";
        
        Map createUserMap = new HashMap();
        createUserMap.put("username", username);
        createUserMap.put("password", password);
        
        Map createUserResult = new HashMap();
        createUserResult = (Map)(clothoObject.createUser(createUserMap));
        
        Map loginUserMap = new HashMap();
        loginUserMap.put("username", username);
        loginUserMap.put("credentials", password);
        
        Map loginResult = new HashMap();
        loginResult = (Map)(clothoObject.login(loginUserMap));
        
        assertEquals(createUserResult.get("id").toString(),loginResult.get("id").toString());
        
        
        Map personObjectMap = new HashMap();
        personObjectMap.put("firstname", "Prashant");
        personObjectMap.put("lastname", "Vaidyanathan");
        personObjectMap.put("id", loginResult.get("id").toString());
        Object setResult = clothoObject.set(personObjectMap);
        
        assertEquals(setResult.toString(),createUserResult.get("id").toString(),loginResult.get("id").toString());
        
        conn.closeConnection();
    }
    
    @Test
    public void remoteCreate(){
        ClothoConnection conn = new ClothoConnection(TestArgs.clothoLocalAddress);
        Clotho clothoObject = new Clotho(conn);
        
        String username = "test" + System.currentTimeMillis();  ;
        String password = "testPassword";
        
        Map createUserMap = new HashMap();
        createUserMap.put("username", username);
        createUserMap.put("password", password);
        
        Map createUserResult = new HashMap();
        createUserResult = (Map)(clothoObject.createUser(createUserMap));
        
        Map loginUserMap = new HashMap();
        loginUserMap.put("username", username);
        loginUserMap.put("credentials", password);
        
        Map loginResult = new HashMap();
        loginResult = (Map)(clothoObject.login(loginUserMap));
        
        String objectName =  "TestSeq" + System.currentTimeMillis();
        String objectId = "" + System.currentTimeMillis();
        
        Map newObject = new HashMap();
        newObject.put("seq", "ATGC");
        newObject.put("name", objectName);
        newObject.put("id", objectId);
        Object createResult = clothoObject.create(newObject);
        
        Map getResult = new HashMap();
        getResult = (Map)(clothoObject.get(objectId));
        
        assertEquals(objectName,getResult.get("name"));
        
        conn.closeConnection();
    }
    
}
