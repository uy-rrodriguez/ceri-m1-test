package fr.univavignon.pokedex.api.impl;

import org.junit.Before;

import fr.univavignon.pokedex.api.IPokemonTrainerFactoryTest;

public class PokemonTrainerFactoryTest extends IPokemonTrainerFactoryTest {

	@Before
	public void setUp() throws Exception {
		trainerFactory = new PokemonTrainerFactory();
		pokedexFactory = new PokedexFactory();
		metadataProvider = new PokemonMetadataProvider();
		pokemonFactory = new PokemonFactory();
		pokedex = null;
	}
}
