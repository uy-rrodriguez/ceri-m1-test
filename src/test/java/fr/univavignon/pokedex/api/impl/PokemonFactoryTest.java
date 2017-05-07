package fr.univavignon.pokedex.api.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.univavignon.pokedex.api.IPokemonFactoryTest;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;

public class PokemonFactoryTest extends IPokemonFactoryTest {

	@Before
	public void setUp() {
		pokemonFactory = new PokemonFactory();
	}
	
	/**
	 * Tests du traitement d'erreurs qui n'etait pas pris en compte dans les mocks.
	 * 
	 */
	@Test
	public void testCreatePokemonErrors() {
		Pokemon poke;
		
		int index = 0;
		int cp = 613;
		int hp = 64;
		int dust = 4000;
		int candy = 4;
		
		// Test pokemon inexistant
		try {
			index = -1;
			poke = pokemonFactory.createPokemon(index, cp, hp, dust, candy);
			Assert.assertEquals("Bulbizarre", poke.getName()); // Ce code ne devrait pas etre pas execute
		}
		catch (PokedexException pokex) {
			Assert.assertTrue(pokex.getMessage().contains("Could not find pokemon"));
		}
		
		// Test data incorrecte
		try {
			index = 0;
			dust = 150000;
			poke = pokemonFactory.createPokemon(index, cp, hp, dust, candy);
			Assert.assertEquals("Bulbizarre", poke.getName()); // Ce code ne devrait pas etre pas execute
		}
		catch (PokedexException pokex) {
			Assert.assertTrue(pokex.getMessage().contains("Aucun pokémon trouvé avec ces données"));
		}
	}	
}
