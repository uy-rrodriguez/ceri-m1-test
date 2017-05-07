package fr.univavignon.pokedex.api.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonMetadata;

public class Pokedex implements IPokedex {

	private List<Pokemon> pokemons;
	private IPokemonMetadataProvider metadataProvider;
	private IPokemonFactory pokemonFactory;
	
	public Pokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
		pokemons = new ArrayList<>();
		this.metadataProvider = metadataProvider;
		this.pokemonFactory = pokemonFactory;
	}
	
	@Override
	public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
		return metadataProvider.getPokemonMetadata(index);
	}

	@Override
	public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
		return pokemonFactory.createPokemon(index, cp, hp, dust, candy);
		
		/*
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
		*/
	}

	@Override
	public int size() {
		return this.pokemons.size();
	}

	@Override
	public int addPokemon(Pokemon pokemon) {
		pokemons.add(pokemon.getIndex() - 1, pokemon);
		return pokemon.getIndex();
	}

	@Override
	public Pokemon getPokemon(int id) throws PokedexException {
		//int index = (id - 1);
		
		// L'id du Pokemon dans cet application (d'apres les PDF du cours)
		// est 1 de moins que son vrai ID, du coup sa correspond
		// exactement avec l'index dans la liste
		int index = id;
		
		if (pokemons.size() <= index || pokemons.get(index) == null)
			return null;
		return pokemons.get(index);
	}

	@Override
	public List<Pokemon> getPokemons() {
		return pokemons;
	}

	@Override
	public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
		List<Pokemon> copy = new ArrayList<>();
		copy.addAll(pokemons);
		copy.sort(order);
		return copy;
	}

}
