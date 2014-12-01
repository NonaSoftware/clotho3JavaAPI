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

/**
 *
 * @author prashantvaidyanathan
 */
public class ClothoAPI {

    
    public ClothoAPI(){
    }
    //private static WebSocket.Connection serverConnection;

    
    public static void main(String[] args) {
        
        
        ClothoConnection conn = new ClothoConnection("wss://localhost:8443/websocket");
        Clotho clothoObject = new Clotho(conn);
        
        Map map = new HashMap();
        map.put("name","part1");
        
        Object ret = clothoObject.queryOne(map);
        System.out.println("The result is : " + ((JSONObject)ret).get("sequence"));
        
        Map map2 = new HashMap();
        map2.put("schema","org.clothocad.testapi");
        
        Object ret2 = clothoObject.query(map2);
        System.out.println("The result is : " + ((JSONArray)ret2).get(0));
        
        Map map3 = new HashMap();
        map3.put("name","createdPart0");
        map3.put("sequence","atataagcgcaaa");
        map3.put("schema","org.clothocad.testapi");
        
        Object ret3 = clothoObject.create(map3);
        System.out.println("Create result : " + ret3);
        
        List<Map> createMap = new ArrayList<Map>();
        Map map41 = new HashMap();
        map41.put("name","createdPart1");
        map41.put("sequence","atataagcgcaaa");
        map41.put("schema","org.clothocad.testapi");
        
        Map map42 = new HashMap();
        map42.put("name","createdPart2");
        map42.put("sequence","atataagcgcaaa");
        map42.put("schema","org.clothocad.testapi");
        
        Map map43 = new HashMap();
        map43.put("name","createdPart3");
        map43.put("sequence","atataagcgcaaa");
        map43.put("schema","org.clothocad.testapi");
        
        createMap.add(map41);
        createMap.add(map42);
        createMap.add(map43);
        
        Object ret4 = clothoObject.createAll(createMap);
        System.out.println("CreateAll result : " + ret4);
        
        Object ret5 = clothoObject.get("5478f610e4b0d2e779178b5b");
        System.out.println("Get result : " + ret5);
        
        
        List<String> getAllList = new ArrayList<String>();
        getAllList.add("54790621e4b0d2e779178b67");
        getAllList.add("54790621e4b0d2e779178b60");
        
        Object ret6 = clothoObject.getAll(getAllList);
        System.out.println("Get result : " + ret6);
        conn.closeConnection();
        
        
    }
    
    
    
}
