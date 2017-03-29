package fr.univavignon.pokedex.api;

import static org.mockito.Mockito.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * Factory interface for class that aims to create Pokemon instance.
 * 
 * @author fv
 */
public class IPokemonFactoryTest {
	
	@Mock
	private IPokemonFactory pokemonFactory;
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	/**
	 * Configuration du mock pour IPokemonFactory.
	 * 
	 * @param mock
	 * @throws Exception
	 */
	protected static void configureIPokemonFactory(IPokemonFactory mock) {
		when(mock.createPokemon(0, 613, 64, 4000, 4)).thenAnswer(a -> {
			return new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
		});
	}
	
	@Before
	public void setUp() {
		configureIPokemonFactory(pokemonFactory);
	}
	
	/**
	 * Creates a pokemon instance computing it IVs.
	 * 
	 * @param index Pokemon index.
	 * @param cp Pokemon CP.
	 * @param hp Pokemon HP.
	 * @param dust Required dust for upgrading pokemon.
	 * @param candy Required candy for upgrading pokemon.
	 * @return Created pokemon instance.
	 */
	
	@Test
	public void testCreatePokemon() {
		int index = 0;
		int cp = 613;
		int hp = 64;
		int dust = 4000;
		int candy = 4;
		
		Pokemon poke = pokemonFactory.createPokemon(index, cp, hp, dust, candy);
		
		Assert.assertEquals("Bulbizarre", poke.getName());
		Assert.assertEquals(126, poke.getAttack());
		Assert.assertEquals(126, poke.getDefense());
		Assert.assertEquals(90, poke.getStamina());
		Assert.assertEquals(56, poke.getIv(), 0);
		
		Assert.assertEquals(index, poke.getIndex());
		Assert.assertEquals(cp, poke.getCp());
		Assert.assertEquals(hp, poke.getHp());
		Assert.assertEquals(dust, poke.getDust());
		Assert.assertEquals(candy, poke.getCandy());
	}
	
}
