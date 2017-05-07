package fr.univavignon.pokedex.api.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class PokemonService implements IPokemonService {

	private static final String SERVICE_URL = "https://poke-metadata.herokuapp.com/ivcal.php?";
	//private static final String SERVICE_URL = "http://localhost/poke/ivcal.php?";
	public static final String ERROR_KEY = "error";
	
	private String callService(String params) throws IOException {
		String resp = "";
		
		//Create connection
	    URL url = new URL(SERVICE_URL + params);
	    URLConnection conn = url.openConnection();
	    conn.setUseCaches(false);
	    conn.setDoOutput(true);
	    
	    //Get Response  
	    InputStream is = conn.getInputStream();
	    InputStreamReader r = new InputStreamReader(is, "UTF-8");
	    BufferedReader buff = new BufferedReader(r);
	    
	    resp = buff.readLine();
		
	    return resp;
	}
	
	private Map<String, String> parseResponse(String json) {
		Map<String, String> res = new HashMap<>();
		
		// Gestion de reponse inconnue
		//json = "{\"id\":1,\"name\":\"Bulbasaur\",\"stamina\":90,\"attack\":118,\"defense\":118}";
		
		if (! json.matches("^\\{.*\\}$")) {
			res.put(ERROR_KEY, "Reponse inconnue : \"" + json + "\"");
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
		Map<String, Object> res = new HashMap<>();
		
		try {
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
				return res;
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
			res.put(ERROR_KEY, e.getMessage());
			return res;
		}
	}

	@Override
	public Map<String, Object> getPokemonIVs(int index, int cp, int hp, int dust) {
		Map<String, Object> res = new HashMap<>();
		
		try {
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
				return res;
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
			res.put(ERROR_KEY, e.getMessage());
			return res;
		}
	}

}
