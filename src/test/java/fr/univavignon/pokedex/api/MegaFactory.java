package fr.univavignon.pokedex.api;

import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class MegaFactory {
	@Mock
	private static IPokemonMetadataProvider metadataProvider;
	
	@Mock
	private static IPokemonFactory pokemonFactory;
	
	@Mock
	private static IPokedexFactory pokedexFactory;
	
	@Mock
	private static IPokemonTrainerFactory trainerFactory;
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule(); 
	
	
	public static IPokemonMetadataProvider getIPokemonMetadataProvider() {
		return metadataProvider;
	}

	public static IPokemonFactory getIPokemonFactory() {
		return pokemonFactory;
	}

	public static IPokedexFactory getIPokedexFactory() {
		return pokedexFactory;
	}

	public static IPokemonTrainerFactory getIPokemonTrainerFactory() {
		return trainerFactory;
	}
}
