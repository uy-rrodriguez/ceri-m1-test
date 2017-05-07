package fr.univavignon.pokedex.api.impl;

import org.junit.Before;

import fr.univavignon.pokedex.api.IPokedexTest;

public class PokedexTest extends IPokedexTest {

	@Before
	public void setUp() throws Exception {
		pokedexFactory = new PokedexFactory();
		metadataProvider = new PokemonMetadataProvider();
		pokemonFactory = new PokemonFactory();
		pokedex = null;
	}
}
