package fr.univavignon.pokedex.api.impl;

import java.util.Map;

import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.util.IPokemonService;
import fr.univavignon.pokedex.api.util.PokemonService;

public class PokemonFactory implements IPokemonFactory {

	@Override
	public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
		Pokemon poke;
		
		// Connection au service web
		IPokemonService service = new PokemonService();
		Map<String, Object> data = service.getPokemonMetadata(index);
		Map<String, Object> ivs = service.getPokemonIVs(index, cp, hp, dust);
		
		if (data.containsKey(PokemonService.ERROR_KEY)) {
			System.out.println("Erreur : " + data.get(PokemonService.ERROR_KEY));
			poke = new Pokemon(index, "", 0, 0, 0, cp, hp, dust, candy, 0);
		}
		
		else if (ivs.containsKey(PokemonService.ERROR_KEY)) {
			System.out.println("Erreur : " + ivs.get(PokemonService.ERROR_KEY));
			poke = new Pokemon(index, "", 0, 0, 0, cp, hp, dust, candy, 0);
			/*
			poke = new Pokemon(index,
					(String) data.get("name"),
					(int) data.get("attack"),
					(int) data.get("defense"),
					(int) data.get("stamina"),
					cp, hp, dust, candy, 0);
			*/
		}
		
		else {
			poke = new Pokemon(index,
					(String) data.get("name"),
					(int) data.get("attack"),
					(int) data.get("defense"),
					(int) data.get("stamina"),
					cp, hp, dust, candy,
					(double) ivs.get("perfection"));
		}
		
		return poke;
	}

}
