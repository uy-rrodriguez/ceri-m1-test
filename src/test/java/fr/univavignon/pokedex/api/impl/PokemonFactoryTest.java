package fr.univavignon.pokedex.api.impl;

import org.junit.Before;

import fr.univavignon.pokedex.api.IPokemonFactoryTest;

public class PokemonFactoryTest extends IPokemonFactoryTest {

	@Before
	public void setUp() {
		pokemonFactory = new PokemonFactory();
	}
}
