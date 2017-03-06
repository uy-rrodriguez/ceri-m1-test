package fr.univavignon.pokedex.api;

import org.junit.Assert;
import org.junit.Test;

public class IPokedexFactoryTest  {
	
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
			IPokemonMetadataProvider metadataProvider = MegaFactory.getIPokemonMetadataProvider();
			IPokemonFactory pokemonFactory = MegaFactory.getIPokemonFactory();
			IPokedexFactory pokedexFactory = MegaFactory.getIPokedexFactory();
			
			IPokedex pokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
			
			int indexPika = 25;
			Pokemon pikachu = new Pokemon(indexPika, "Pikachu", 3, 2, 2, 2, 2, 0, 0, 0);
			int idPikachu = pokedex.addPokemon(pikachu);
			Assert.assertTrue(pokedex.getPokemon(idPikachu).equals(pikachu));
			
			/*
			Pokemon autrePika = pokedex.createPokemon(indexPika, 2, 3, 2, 1);
			pokedex.getPokemonMetadata(indexPika).get
			*/
		}
		catch (PokedexException pex) {
			pex.printStackTrace();
		}
	}
	
}
