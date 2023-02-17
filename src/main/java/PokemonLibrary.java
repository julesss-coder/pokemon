import java.util.HashMap;
import java.util.Map;

public class PokemonLibrary {
    // TODO Should I instantiate a new HashMap, or use a different data structure?
    // TODO Should the type of `pokedexMap` be Map or HashMap or something else?

    // Key: pokemonId, value: new Pokemon()
    Map<Integer, Pokemon> pokedexMap = new HashMap<>();
    private int pokemonId = 1;
    
    public void addPokemon(Pokemon pokemon) {
        pokedexMap.put(pokemonId, pokemon);
        pokemonId++;
    }
}
