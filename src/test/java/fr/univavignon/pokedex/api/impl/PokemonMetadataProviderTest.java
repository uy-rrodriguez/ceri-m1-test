package fr.univavignon.pokedex.api.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.univavignon.pokedex.api.IPokemonMetadataProviderTest;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.PokemonMetadata;

public class PokemonMetadataProviderTest extends IPokemonMetadataProviderTest {

	@Before
	public void setUp() throws Exception {
		metadataProvider = new PokemonMetadataProvider();
	}
	
	/**
	 * Tests du traitement d'erreurs qui n'etait pas pris en compte dans les mocks.
	 * 
	 */
	@Test
	public void testGetPokemonMetadataErrors() {
		PokemonMetadata metadata;
		
		try {
			int index = -1;
			metadata = metadataProvider.getPokemonMetadata(index);
			Assert.assertEquals("Bulbizarre", metadata.getName()); // Ce code ne devrait pas etre pas execute
		}
		catch (PokedexException pokex) {
			Assert.assertTrue(pokex.getMessage().contains("Could not find pokemon"));
		}
		
		try {
			int index = 152;
			metadata = metadataProvider.getPokemonMetadata(index);
			Assert.assertEquals("", metadata.getName()); // Ce code ne devrait pas etre pas execute
		}
		catch (PokedexException pokex) {
			Assert.assertTrue(pokex.getMessage().contains("Could not find pokemon"));
		}
	}
}
