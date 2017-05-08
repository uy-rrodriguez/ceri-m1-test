package fr.univavignon.pokedex.api;

import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class IPokedexTest  {
	
	private static Pokemon pokemonMock = new Pokemon(0, "Bulbizarre", 118, 118, 90, 613, 64, 4000, 4, 56);
	
	
	@Mock
	protected IPokedexFactory pokedexFactory;
	
	@Mock
	protected IPokemonMetadataProvider metadataProvider;
	
	@Mock
	protected IPokemonFactory pokemonFactory;
	
	@Mock
	protected IPokedex pokedex;
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	/**
	 * Configuration du mock pour IPokedex.
	 * 
	 * @param mock
	 * @param pokemon
	 * @param order
	 * @throws PokedexException
	 */
	protected static void configureIPokedex(IPokedex mock) throws PokedexException {
		when(mock.size()).thenAnswer(a -> 1);
		when(mock.addPokemon(null)).thenAnswer(a -> 0);
		when(mock.getPokemon(0)).thenAnswer(a -> pokemonMock);
		when(mock.getPokemons()).thenAnswer(a -> Arrays.asList(new Pokemon[] {pokemonMock} ));
		when(mock.getPokemons(null)).thenAnswer(a -> Arrays.asList(new Pokemon[] {pokemonMock}  ));
		when(mock.getPokemons(PokemonComparators.INDEX)).thenAnswer(a -> Arrays.asList(new Pokemon[] {pokemonMock}));
	}
	
	/**
	 * Configuration du mock pour IPokedexFactory.
	 * 
	 * @param mock
	 * @param pokedex
	 * @param metadataProvider
	 * @param pokemonFactory
	 * @throws PokedexException
	 */
	protected static void configureIPokedexFactory(IPokedexFactory mock,
													IPokedex pokedex,
													IPokemonMetadataProvider metadataProvider,
													IPokemonFactory pokemonFactory) throws PokedexException {
		
		// Mock IPokemonMetadataProvider
		IPokemonMetadataProviderTest.configureIPokemonMetadataProvider(metadataProvider);
		
		// Mock IPokemonFactory
		IPokemonFactoryTest.configureIPokemonFactory(pokemonFactory);

		// Mock IPokedex
		configureIPokedex(pokedex);

		// Mock IPokedexFactory
		when(mock.createPokedex(metadataProvider, pokemonFactory)).thenAnswer(a -> pokedex);
	}
	
	
	@Before
	public void setUp() throws Exception {
		configureIPokedexFactory(pokedexFactory, pokedex, metadataProvider, pokemonFactory);
	}
	
	/**
	 * Creates a new pokedex instance using the given 
	 * <tt>metadataProvider</tt> and <tt>pokemonFactory</tt>. 
	 * 
	 * @param metadataProvider Metadata provider the created pokedex will use.
	 * @param pokemonFactory Pokemon factory the created pokedex will use.
	 * @return Created pokedex instance.
	 */
	
	@Test
	public void testCreatePokedex() {
		try {
            int index = 0;
            int cp = 613;
            int hp = 64;
            int dust = 4000;
            int candy = 4;
            
			IPokedex localPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
			
			// Methodes de Pokemon
            Pokemon pokemon = localPokedex.createPokemon(index, cp, hp, dust, candy);
			Assert.assertEquals(0, localPokedex.addPokemon(pokemon));
			Assert.assertEquals(1, localPokedex.size());
			Assert.assertEquals("Bulbizarre", localPokedex.getPokemon(index).getName());
			Assert.assertEquals(0, localPokedex.getPokemon(index).getIndex());
			Assert.assertEquals(118, localPokedex.getPokemon(index).getAttack());
			Assert.assertEquals(118, localPokedex.getPokemon(index).getDefense());
			Assert.assertEquals(90, localPokedex.getPokemon(index).getStamina());
            Assert.assertEquals(cp, localPokedex.getPokemon(index).getCp());
			Assert.assertEquals(hp, localPokedex.getPokemon(index).getHp());
			Assert.assertEquals(dust, localPokedex.getPokemon(index).getDust());
			Assert.assertEquals(candy, localPokedex.getPokemon(index).getCandy());
            Assert.assertEquals(56, localPokedex.getPokemon(index).getIv(), 0);
            
            // Methodes de PokemonMetadtada
            PokemonMetadata metadata = localPokedex.getPokemonMetadata(index);
			Assert.assertEquals("Bulbizarre", metadata.getName());
			Assert.assertEquals(0, metadata.getIndex());
			Assert.assertEquals(118, metadata.getAttack());
			Assert.assertEquals(118, metadata.getDefense());
			Assert.assertEquals(90, metadata.getStamina());
			
            // Methodes de Pokedex
			Assert.assertEquals("Bulbizarre", localPokedex.getPokemons().get(0).getName());
			Assert.assertEquals("Bulbizarre", localPokedex.getPokemons(null).get(0).getName());
			
			Assert.assertEquals("Bulbizarre", localPokedex.getPokemons(PokemonComparators.INDEX).get(0).getName());
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
