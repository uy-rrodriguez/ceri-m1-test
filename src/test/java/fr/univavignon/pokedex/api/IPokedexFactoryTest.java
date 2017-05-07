package fr.univavignon.pokedex.api;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class IPokedexFactoryTest  {
	
	private Comparator<Pokemon> order = new Comparator<Pokemon>() {
		@Override
		public int compare(Pokemon o1, Pokemon o2) {
			return o1.getIndex() - o2.getIndex();
		}
	};
	
	private Pokemon pokemon = new Pokemon(0, "Bulbizarre", 118, 118, 90, 613, 64, 4000, 4, 56);
	
	
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
	protected static void configureIPokedex(IPokedex mock, Pokemon pokemon, Comparator<Pokemon> order) throws PokedexException {
		when(mock.size()).thenAnswer(a -> 0);
		
		/*
		when(mock.addPokemon(null)).thenAnswer(a -> 0);
		when(mock.getPokemon(0)).thenAnswer(a -> pokemon);
		when(mock.getPokemons()).thenAnswer(a -> Arrays.asList(new Pokemon[] {pokemon} ));
		when(mock.getPokemons(null)).thenAnswer(a -> Arrays.asList(new Pokemon[] {pokemon}  ));
		when(mock.getPokemons(order)).thenAnswer(a -> Arrays.asList(new Pokemon[] {pokemon}));
		*/
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
													Pokemon pokemon,
													Comparator<Pokemon> order,
													IPokemonMetadataProvider metadataProvider,
													IPokemonFactory pokemonFactory) throws PokedexException {
		
		// Mock IPokemonMetadataProvider
		IPokemonMetadataProviderTest.configureIPokemonMetadataProvider(metadataProvider);
		
		// Mock IPokemonFactory
		IPokemonFactoryTest.configureIPokemonFactory(pokemonFactory);

		// Mock IPokedex
		configureIPokedex(pokedex, pokemon, order);

		// Mock IPokedexFactory
		when(mock.createPokedex(metadataProvider, pokemonFactory)).thenAnswer(a -> pokedex);
	}
	
	
	@Before
	public void setUp() throws Exception {
		configureIPokedexFactory(pokedexFactory, pokedex, pokemon, order, metadataProvider, pokemonFactory);
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
			IPokedex localPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
			Assert.assertEquals(0, localPokedex.size());
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
