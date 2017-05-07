package fr.univavignon.pokedex.api.impl;

import org.junit.Before;

import fr.univavignon.pokedex.api.IPokemonMetadataProviderTest;

public class PokemonMetadataProviderTest extends IPokemonMetadataProviderTest {

	@Before
	public void setUp() throws Exception {
		metadataProvider = new PokemonMetadataProvider();
	}
	
}
