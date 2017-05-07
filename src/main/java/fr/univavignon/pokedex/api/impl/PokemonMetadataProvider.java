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
		PokemonMetadata pokeMeta;
		
		// Connection au service web
		IPokemonService service = new PokemonService();
		Map<String, Object> data = service.getPokemonMetadata(index);
		
		if (data.containsKey(PokemonService.ERROR_KEY)) {
			System.out.println("Erreur : " + data.get(PokemonService.ERROR_KEY));
			pokeMeta = new PokemonMetadata(index, "", 0, 0, 0);
		}
		else {
			pokeMeta = new PokemonMetadata(index,
					(String) data.get("name"),
					(int) data.get("attack"),
					(int) data.get("defense"),
					(int) data.get("stamina"));
		}
		
		return pokeMeta;
	}

}
