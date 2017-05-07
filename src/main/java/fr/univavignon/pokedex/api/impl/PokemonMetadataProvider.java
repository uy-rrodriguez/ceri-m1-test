package fr.univavignon.pokedex.api.impl;

import java.util.Map;

import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.PokemonMetadata;
import fr.univavignon.pokedex.api.util.IPokemonService;
import fr.univavignon.pokedex.api.util.PokemonService;

public class PokemonMetadataProvider implements IPokemonMetadataProvider {

	@Override
	public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
		// L'id du Pokemon est 1 de plus que son index dans la Pokedex
		int id = index +1;
		
		PokemonMetadata pokeMeta;
		
		// Connection au service web
		IPokemonService service = new PokemonService();
		Map<String, Object> data = service.getPokemonMetadata(id);
		
		if (data.containsKey(PokemonService.ERROR_KEY)) {
			System.out.println("Erreur : " + data.get(PokemonService.ERROR_KEY));
			pokeMeta = new PokemonMetadata(id, "", 0, 0, 0);
		}
		else {
			pokeMeta = new PokemonMetadata(id,
					(String) data.get("name"),
					(int) data.get("attack"),
					(int) data.get("defense"),
					(int) data.get("stamina"));
		}
		
		return pokeMeta;
	}

}
