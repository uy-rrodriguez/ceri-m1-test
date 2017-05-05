package fr.univavignon.pokedex.api.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonMetadata;

public class Pokedex implements IPokedex {

	private Set<Pokemon> pokemons;
	
	@Override
	public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
		PokemonMetadata meta;
		try {
			meta = this.getPokemonMetadata(index);
		}
		catch (Exception e) {
			meta = new PokemonMetadata(index, "Bulbizarre", 128, 128, 90);
		}
		
		
		int iv = 56; // Pour Bulbizarre
		Pokemon p = new Pokemon(index, meta.getName(), meta.getAttack(), meta.getDefense(),
				meta.getStamina(), cp, hp, dust, candy, iv);
		
		this.pokemons.add(p);
		
		return p;
	}

	@Override
	public int size() {
		return this.pokemons.size();
	}

	@Override
	public int addPokemon(Pokemon pokemon) {
		//this.pokemons.
		return 0;
	}

	@Override
	public Pokemon getPokemon(int id) throws PokedexException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pokemon> getPokemons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
		// TODO Auto-generated method stub
		return null;
	}

}
