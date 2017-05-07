package fr.univavignon.pokedex.api.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class PokemonService implements IPokemonService {

	private static String SERVICE_URL = "https://poke-metadata.herokuapp.com/ivcal.php?";
	public static String ERROR_KEY = "error";
	
	private String callService(String params) throws IOException {
		String resp = "";
		
		//Create connection
	    URL url = new URL(SERVICE_URL);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    conn.setRequestProperty("Content-Length", Integer.toString(params.getBytes().length));
	    conn.setUseCaches(false);
	    conn.setDoOutput(true);
	    
	    //conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	    //conn.setRequestProperty("Content-Language", "en-US");  

	    //Send request
	    DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
	    wr.writeBytes(params);
	    wr.close();

	    //Get Response  
	    InputStream is = conn.getInputStream();
	    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	    StringBuffer response = new StringBuffer(); // or StringBuffer if Java version 5+
	    String line;
	    while ((line = rd.readLine()) != null) {
	      response.append(line);
	      response.append('\r');
	    }
	    rd.close();
		
		resp = response.toString();
		return resp;
	}
	
	private Map<String, String> parseResponse(String json) {
		Map<String, String> res = new HashMap<>();
		
		// Gestion de reponse inconnue
		if (! json.matches("^\\{.*\\}$")) {
			res.put(ERROR_KEY, "Reponse inconnue");
			return res;
		}
		
		// Parsing du string

		// {"id":1,"name":"Bulbasaur","stamina":90,"attack":118,"defense":118}
		// ﻿{"atkIV":3,"defIV":15,"staIV":7,"level":25,"perfection":56,"poke":{"id":1,"name":"Bulbasaur","stamina":90,"attack":118,"defense":118}}
		// ﻿{"error":"Aucun pok\u00e9mon trouv\u00e9 avec ces donn\u00e9es"}
		json = json.substring(1, json.length()-1);
		
		// "id":1,"name":"Bulbasaur","stamina":90,"attack":118,"defense":118
		// ﻿"atkIV":3,"defIV":15,"staIV":7,"level":25,"perfection":56,"poke":{"id":1,"name":"Bulbasaur","stamina":90,"attack":118,"defense":118}
		// ﻿"error":"Aucun pok\u00e9mon trouv\u00e9 avec ces donn\u00e9es"
		String[] attribs = json.split(",");
		String[] attParts;
		
		for (String att : attribs) {
			attParts = att.split(":");							// att = "name":"Bulbasaur"
			
			String key = attParts[0];							// key = "name"
			key = key.substring(1, key.length() - 1);			// key = name
			
			String value = attParts[1];
			if (value.matches("^\".*\"$")) {					// value = "Bulbasaur"
				value = value.substring(1, value.length()-1);	// value = Bulbasaur
			}
			
			res.put(key, value);
		}
		
		return res;
	} 
	
	@Override
	public Map<String, Object> getPokemonMetadata(int index) {
		try {
			Map<String, Object> res = new HashMap<>();
			
			// Construction des parametres
			String params = "method=getMetadata";
			params += "&name=" + index;
			
			// Appel au service et recuperation de la reponse
			String jsonResp = callService(params);
			
			// Traitement de la reponse
			Map<String, String> respValues = parseResponse(jsonResp);
			
			// Gestion des erreurs
			if (respValues.containsKey(ERROR_KEY)) {
				// System.out.println("Erreur : " + respValues.get("error"));
				res.put(ERROR_KEY, respValues.get(ERROR_KEY));
				return null;
			}
			
			// Construction du map de retour
			// On imagine que tous les attributs attendus existent
			res.put("id", Integer.parseInt(respValues.get("id")));
			res.put("name", respValues.get("name"));
			res.put("stamina", Integer.parseInt(respValues.get("stamina")));
			res.put("attack", Integer.parseInt(respValues.get("attack")));
			res.put("defense", Integer.parseInt(respValues.get("defense")));
			
			return res;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Map<String, Object> getPokemonIVs(int index, int cp, int hp, int dust) {
		try {
			Map<String, Object> res = new HashMap<>();
			
			// Construction des parametres
			String params = "method=getIVs";
			params += "&name=" + index;
			params += "&cp=" + cp;
			params += "&hp=" + hp;
			params += "&dust=" + dust;
			
			// Appel au service et recuperation de la reponse
			String jsonResp = callService(params);
			
			// Traitement de la reponse
			Map<String, String> respValues = parseResponse(jsonResp);
			
			// Gestion des erreurs
			if (respValues.containsKey(ERROR_KEY)) {
				//System.out.println("Erreur : " + respValues.get("error"));
				res.put(ERROR_KEY, respValues.get(ERROR_KEY));
				return null;
			}
			
			// Construction du map de retour
			// On imagine que tous les attributs attendus existent
			res.put("atkIV", Integer.parseInt(respValues.get("atkIV")));
			res.put("defIV", Integer.parseInt(respValues.get("defIV")));
			res.put("staIV", Integer.parseInt(respValues.get("staIV")));
			res.put("level", Integer.parseInt(respValues.get("level")));
			res.put("perfection", Double.parseDouble(respValues.get("perfection")));
			
			return res;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
