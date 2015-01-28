/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.clothoapi.clotho3javaapi;

import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author prash
 */
public class PhoenixClassesTest {
    
    
    public static void main(String[] args) {
        
        System.out.println("Start Test for Phoenix Modules\n\n");
        //testqueryFeature();
        //testquerySpecificName("cI.ref");
        //testquerySpecificName("T-Sapphire.ref");
        testPhoenixParts();
        testPhoenixquery();
        System.out.println("\n\nEnd Test for Phoenix Modules");
    }
    
    public static void testPhoenixParts()
    {
        
        ClothoConnection conn = new ClothoConnection("wss://localhost:8443/websocket");
        Clotho clothoObject = new Clotho(conn);
        Map map = new HashMap();
        map.put("name","partTest123.ph");
        map.put("id", "testPart123");
        JSONArray emArray = new JSONArray();
        JSONObject o1 = new JSONObject();
        JSONObject o2 = new JSONObject();
        JSONObject o3 = new JSONObject();
        
        o1.put("1",0.90);
        o2.put("2",30.90);
        o3.put("3",4.50);
        
        emArray.add(o1);
        emArray.add(o2);
        emArray.add(o3);
        
        map.put("em", emArray);
        
        JSONObject exObj = new JSONObject();
        exObj.put("1", 0.1);
        exObj.put("2", 1.1);
        exObj.put("3", 2.1);
        exObj.put("4", 3.1);
        
        map.put("ex", exObj);
        
        clothoObject.set(map);
        
        conn.closeConnection();
        
    }
    public static void testPhoenixquery()
    {
        ClothoConnection conn = new ClothoConnection("wss://localhost:8443/websocket");
        Clotho clothoObject = new Clotho(conn);
        Map map = new HashMap();
        map.put("id", "testPart123");
        Object query = clothoObject.queryOne(map);
        System.out.println(((JSONObject)query).get("em"));
        System.out.println(((JSONObject)query).get("ex"));
        
        conn.closeConnection();
    }
    
    public static void testquerySpecificName(String namefield)
    {
        ClothoConnection conn = new ClothoConnection("wss://localhost:8443/websocket");
        Clotho clothoObject = new Clotho(conn);
        
        //HashSet<Feature> features = new HashSet<>();
        
        Map map = new HashMap();
        map.put("name", namefield);
        Object query = clothoObject.query(map);
        JSONArray array = (JSONArray) query;
        for(int i=0;i<array.size();i++)
        {
            JSONObject jsonFeature = array.getJSONObject(i);
            System.out.println("object id of object "+i+": "+ jsonFeature.get("id"));
            if(jsonFeature.containsKey("em_spectrum"))
                System.out.println("em Spectrum ::"+ jsonFeature.get("em_spectrum"));
            
            
        }
        //System.out.println("Result for Feature query is : "+array);
        conn.closeConnection();
    }
    
    public static void testqueryFeature()
    {
        ClothoConnection conn = new ClothoConnection("wss://localhost:8443/websocket");
        Clotho clothoObject = new Clotho(conn);
        
        //HashSet<Feature> features = new HashSet<>();
        
        Map map = new HashMap();
        map.put("schema", "org.clothocad.model.Feature");
        Object query = clothoObject.query(map);
        JSONArray array = (JSONArray) query;
        //System.out.println("Result for Feature query is : "+array);
        conn.closeConnection();
    }
    
}
