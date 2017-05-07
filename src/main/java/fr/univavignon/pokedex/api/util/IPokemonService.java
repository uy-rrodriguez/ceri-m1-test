package fr.univavignon.pokedex.api.util;

import java.util.Map;

public interface IPokemonService {
	public Map<String, Object> getPokemonMetadata(int index);
	public Map<String, Object> getPokemonIVs(int index, int cp, int hp, int dust);
}
