/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.clothoapi.clotho3javaapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author prashantvaidyanathan
 */
public class ClothoTest {
    
    public ClothoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void queryOne()
    {
        ClothoConnection conn = new ClothoConnection("wss://localhost:8443/websocket");
        Clotho clothoObject = new Clotho(conn);
        
        Map createPart = new HashMap();
        createPart.put("name","createdPart");
        createPart.put("sequence","atataagcgcaaa");
        createPart.put("schema","org.clothocad.testapi");
        createPart.put("id", "testAPIquery");
        
        clothoObject.create(createPart);
        
        Map map = new HashMap();
        map.put("id","testAPIquery");
        
        Object ret = clothoObject.queryOne(map);
        assertEquals(((JSONObject)ret).get("name").toString(),"createdPart");
        assertEquals(((JSONObject)ret).get("id").toString(),"testAPIquery");
        assertEquals(((JSONObject)ret).get("schema").toString(),"org.clothocad.testapi");
        assertEquals(((JSONObject)ret).get("sequence").toString(),"atataagcgcaaa");
        conn.closeConnection();
    }
    
    
    
    @Test
    public void query() //Tests Both Create All & Query
    {
        ClothoConnection conn = new ClothoConnection("wss://localhost:8443/websocket");
        Clotho clothoObject = new Clotho(conn);
        
        
        List<Map> createAllMap = new ArrayList<Map>();
        
        Map createPart1 = new HashMap();
        createPart1.put("name","createdPartQuery");
        createPart1.put("sequence","atataagcgcaaa");
        createPart1.put("schema","org.clothocad.testapi");
        createPart1.put("id", "testAPIquery1");
        
        Map createPart2 = new HashMap();
        createPart2.put("name","createdPartQuery");
        createPart2.put("sequence","atataagcgcaaa");
        createPart2.put("schema","org.clothocad.testapi");
        createPart2.put("id", "testAPIquery2");
        
        createAllMap.add(createPart1);
        createAllMap.add(createPart2);
        
        clothoObject.createAll(createAllMap);
        
        Map map = new HashMap();
        map.put("name","createdPartQuery");
        
        Object ret = clothoObject.query(map);
        assertEquals(((JSONObject)((JSONArray)ret).get(0)).get("id").toString(),"testAPIquery1");
        assertEquals(((JSONObject)((JSONArray)ret).get(1)).get("id").toString(),"testAPIquery2");
        assertEquals(((JSONObject)((JSONArray)ret).get(0)).get("name").toString(),"createdPartQuery");
        assertEquals(((JSONObject)((JSONArray)ret).get(1)).get("name").toString(),"createdPartQuery");
        
       
        conn.closeConnection();
    }
    
    @Test
    public void get()
    {
        ClothoConnection conn = new ClothoConnection("wss://localhost:8443/websocket");
        Clotho clothoObject = new Clotho(conn);
        
        Map createPart = new HashMap();
        createPart.put("name","createdPartget");
        createPart.put("sequence","atataagcgcaaa");
        createPart.put("schema","org.clothocad.testapi");
        createPart.put("id", "testAPIget");
        
        clothoObject.create(createPart);
        
        Object ret = clothoObject.get("testAPIget");
        assertEquals(((JSONObject)ret).get("name").toString(),"createdPartget");
        assertEquals(((JSONObject)ret).get("id").toString(),"testAPIget");
        assertEquals(((JSONObject)ret).get("schema").toString(),"org.clothocad.testapi");
        assertEquals(((JSONObject)ret).get("sequence").toString(),"atataagcgcaaa");
        conn.closeConnection();
    }
    
    @Test
    public void getAll()
    {
        ClothoConnection conn = new ClothoConnection("wss://localhost:8443/websocket");
        Clotho clothoObject = new Clotho(conn);
        
        Map createPart1 = new HashMap();
        createPart1.put("name","createdPartget1");
        createPart1.put("sequence","atataagcgcaaa");
        createPart1.put("schema","org.clothocad.testapi");
        createPart1.put("id", "testAPIget1");
        
        Map createPart2 = new HashMap();
        createPart2.put("name","createdPartget2");
        createPart2.put("sequence","atataagcgcaaa");
        createPart2.put("schema","org.clothocad.testapi");
        createPart2.put("id", "testAPIget2");
        
        
        clothoObject.create(createPart1);
        clothoObject.create(createPart2);
        
        List<String> objIds = new ArrayList<String>();
        objIds.add("testAPIget1");
        objIds.add("testAPIget2");
        
        Object ret = clothoObject.getAll(objIds);
        assertEquals(((JSONObject)((JSONArray)ret).get(0)).get("id").toString(),"testAPIget1");
        assertEquals(((JSONObject)((JSONArray)ret).get(1)).get("id").toString(),"testAPIget2");
        assertEquals(((JSONObject)((JSONArray)ret).get(0)).get("name").toString(),"createdPartget1");
        assertEquals(((JSONObject)((JSONArray)ret).get(1)).get("name").toString(),"createdPartget2");
        
       
        conn.closeConnection();
    }
    
    
    @Test
    public void multipleConnectionTest()
    {
        ClothoConnection conn1 = new ClothoConnection("wss://localhost:8443/websocket");
        Clotho clothoObject1 = new Clotho(conn1);
        ClothoConnection conn2 = new ClothoConnection("wss://localhost:8443/websocket");
        Clotho clothoObject2 = new Clotho(conn2);
        
        
        Map createPart1 = new HashMap();
        createPart1.put("name","createdPartconn1");
        createPart1.put("sequence","atataagcgcaaa");
        createPart1.put("schema","org.clothocad.testapi");
        createPart1.put("id", "testAPImultConn");
        
        clothoObject1.create(createPart1);
        
        
        Map map = new HashMap();
        map.put("id","testAPImultConn");
        
        Object ret = clothoObject2.queryOne(map);
        assertEquals(((JSONObject)ret).get("name").toString(),"createdPartconn1");
        assertEquals(((JSONObject)ret).get("id").toString(),"testAPImultConn");
        assertEquals(((JSONObject)ret).get("schema").toString(),"org.clothocad.testapi");
        assertEquals(((JSONObject)ret).get("sequence").toString(),"atataagcgcaaa");
        
        conn1.closeConnection();
        conn2.closeConnection();
    }
    
    
    
}
