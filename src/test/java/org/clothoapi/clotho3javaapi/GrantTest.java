/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.clothoapi.clotho3javaapi;

import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author prash
 */
public class GrantTest {
    
    @Test
    public void grantTest(){
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
        
        //assertEquals(createUserResult.get("id").toString(),loginResult.get("id").toString());
        
        Map personObjectMap = new HashMap();
        personObjectMap.put("firstname", "Prashant");
        personObjectMap.put("lastname", "Vaidyanathan");
        personObjectMap.put("id", loginResult.get("id").toString());
        Object setResult = clothoObject.set(personObjectMap);
        
        //assertEquals(setResult.toString(),createUserResult.get("id").toString(),loginResult.get("id").toString());
        
        List<String> add = new ArrayList<String>();
        List<String> remove = new ArrayList<String>();
        
        add.add("public");
        
        Map grantMap = new HashMap();
        grantMap.put("id", createUserResult.get("id").toString());
        grantMap.put("user", "none");
        grantMap.put("add", add);
        grantMap.put("remove", remove);
        
        Map grantResult = new HashMap();
        grantResult = (Map)(clothoObject.grant(grantMap));
         
        conn.closeConnection();
    }
    
}
