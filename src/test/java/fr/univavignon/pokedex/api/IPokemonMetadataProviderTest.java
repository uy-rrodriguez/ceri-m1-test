package fr.univavignon.pokedex.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.*;

/**
 * An IPokemonMetadataProvider aims to provide PokemonMetadata
 * for a given pokemon index.
 */
public class IPokemonMetadataProviderTest {

	@Mock
	private static IPokemonMetadataProvider metadataProvider;
	
	@Rule
	private MockitoRule mockitoRule = MockitoJUnit.rule();
	
	/**
	 * Configuration du mock pour IPokemonMetadataProvider.
	 * 
	 * @param mock
	 * @throws PokedexException 
	 * @throws Exception
	 */
	protected static void configureIPokemonMetadataProvider(IPokemonMetadataProvider mock) throws PokedexException {
		when(mock.getPokemonMetadata(0)).thenAnswer(a -> {
			return new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
		});
	}
	
	
	@Before
	private void setUp() throws Exception {
		configureIPokemonMetadataProvider(metadataProvider);
	}
	
	
	/**
	 * Retrieves and returns the metadata for the pokemon
	 * denoted by the given <tt>index</tt>.
	 * 
	 * @param index Index of the pokemon to retrieve metadata for.
	 * @return Metadata of the pokemon.
	 * @throws PokedexException If the given <tt>index</tt> is not valid.
	 */
	@Test
	public void testGetPokemonMetadata() {
		try {
			int index = 0;
			
			PokemonMetadata metadata = metadataProvider.getPokemonMetadata(index);
			
			Assert.assertEquals("Bulbizarre", metadata.getName());
			Assert.assertEquals(0, metadata.getIndex());
			Assert.assertEquals(126, metadata.getAttack());
			Assert.assertEquals(126, metadata.getDefense());
			Assert.assertEquals(90, metadata.getStamina());
		}
		catch (PokedexException pokex) {
			pokex.printStackTrace();
		}
	}
	
}
