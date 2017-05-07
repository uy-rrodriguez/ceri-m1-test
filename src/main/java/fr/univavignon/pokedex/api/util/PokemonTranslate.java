package fr.univavignon.pokedex.api.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PokemonTranslate {
	private static PokemonTranslate instance;
	
	// Traductions des noms de l'anglais au français
	private static Properties frenchNames = new Properties();
		
	private PokemonTranslate() {
		InputStream input = null;
		InputStreamReader reader = null;

		try {
			String filePath = "src/main/java/fr/univavignon/pokedex/api/util/pokemon_names_fr.properties";
			//input = getClass().getResourceAsStream();
			input = new FileInputStream(filePath);
			reader = new InputStreamReader(input, "UTF-8");
			frenchNames.load(reader);
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static PokemonTranslate getInstance() {
		if (instance == null)
			instance = new PokemonTranslate();
		return instance;
	}
	
	public String getFrenchName(String englishName) {
		return frenchNames.getProperty(englishName);
	}
}
