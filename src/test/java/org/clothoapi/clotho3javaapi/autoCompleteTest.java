/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.clothoapi.clotho3javaapi;

import java.util.HashMap;
import java.util.Map;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author prash
 */
public class autoCompleteTest {
    
    private static String username1;
    private static String username2;
    private static String password;
    
    @BeforeClass
    public static void setUpClass() {
        username1 = "testUser1" + System.currentTimeMillis();
        username2 = "testUser2" + System.currentTimeMillis();
        password = "testPassword";
        
    }
    
    
    
    @Test
    public void autocomplete(){
       ClothoConnection conn = new ClothoConnection(TestArgs.clothoLocalAddress);
       Clotho clothoObject = new Clotho(conn);
       Object ret = clothoObject.autocomplete("Fa");
       System.out.println("Autocomplete Result :: " + ret.toString());
       
       conn.closeConnection();
    }
    
    
    //@Test
    public void startsWith(){
       ClothoConnection conn = new ClothoConnection(TestArgs.clothoLocalAddress);
       Clotho clothoObject = new Clotho(conn);
       Map map = new HashMap();
       map.put("query", "org.");
       map.put("key", "schema");
       Object ret = clothoObject.startsWith(map);
       System.out.println("Result :: " + ret.toString());
       
       conn.closeConnection();
    }
    
}
