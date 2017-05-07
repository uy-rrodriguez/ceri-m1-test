package fr.univavignon.pokedex.api.impl;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokedexFactory;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.IPokemonTrainerFactory;
import fr.univavignon.pokedex.api.PokemonTrainer;
import fr.univavignon.pokedex.api.Team;

public class PokemonTrainerFactory implements IPokemonTrainerFactory {

	@Override
	public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
		IPokemonMetadataProvider metadataProvider = new PokemonMetadataProvider();
		IPokemonFactory pokemonFactory = new PokemonFactory();
		IPokedex pokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
		
		PokemonTrainer trainer =new PokemonTrainer(name, team, pokedex);
		return trainer;
	}

}
