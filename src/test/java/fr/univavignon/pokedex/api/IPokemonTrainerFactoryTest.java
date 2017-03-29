package fr.univavignon.pokedex.api;

import static org.mockito.Mockito.when;

import java.util.Comparator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * Factory interface for class that aims to create PokemonTrainer instance.
 * 
 * @author fv
 */
public class IPokemonTrainerFactoryTest {

	private Comparator<Pokemon> order = new Comparator<Pokemon>() {
		@Override
		public int compare(Pokemon o1, Pokemon o2) {
			return o1.getIndex() - o2.getIndex();
		}
	};
	
	private Pokemon pokemon = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
	
	
	@Mock
	private static IPokedexFactory pokedexFactory;
	
	@Mock
	private static IPokemonMetadataProvider metadataProvider;
	
	@Mock
	private static IPokemonFactory pokemonFactory;
	
	@Mock
	private static IPokedex pokedex;
	
	@Mock
	private static IPokemonTrainerFactory trainerFactory;
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	/**
	 * Configuration du mock pour IPokemonTrainerFactory.
	 * 
	 * @param mock
	 * @param pokedexFactory
	 * @param pokedex
	 * @param pokemon
	 * @param order
	 * @param metadataProvider
	 * @param pokemonFactory
	 * @throws PokedexException
	 */
	protected static void configureIPokemonTrainerFactory(IPokemonTrainerFactory mock,
															IPokedexFactory pokedexFactory,
															IPokedex pokedex,
															Pokemon pokemon,
															Comparator<Pokemon> order,
															IPokemonMetadataProvider metadataProvider,
															IPokemonFactory pokemonFactory) throws PokedexException {
		
		// Mock IPokedexFactory
		IPokedexFactoryTest.configureIPokedexFactory(pokedexFactory, pokedex,pokemon, order,
				metadataProvider, pokemonFactory);
		
		// Mock IPokemonTrainerFactory
		when(mock.createTrainer("Moi", Team.VALOR, pokedexFactory)).thenAnswer(a -> {
			return new PokemonTrainer("Moi", Team.VALOR, pokedex);
		});
	}
	
	
	@Before
	public void setUp() throws Exception {
		configureIPokemonTrainerFactory(trainerFactory, pokedexFactory, pokedex, pokemon,
				order, metadataProvider, pokemonFactory);
	}
	
	
	/**
	 * Creates and returns a PokemonTrainer instance.
	 * 
	 * @param name Name of the created trainer.
	 * @param team Team of the created trainer.
	 * @param pokedexFactory Factory to use for creating associated pokedex instance.
	 * @return Created trainer instance.
	 */
	
	@Test
	public void testCreateTrainer() {
		String name = "Moi";
		Team team = Team.VALOR;
		
		PokemonTrainer trainer = trainerFactory.createTrainer(name, team, pokedexFactory);
		Assert.assertEquals(name, trainer.getName());
		Assert.assertEquals(team, trainer.getTeam());
	}
	
}
