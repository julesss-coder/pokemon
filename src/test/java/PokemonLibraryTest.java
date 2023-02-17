import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokemonLibraryTest {
    private PokemonLibrary pokemonMap;

    @BeforeEach
    void setUp() {
        PokemonLibrary pokemonMap = new PokemonLibrary();
    }

    /*
    TESTS
    - map is empty upon instantiation OK
    - map size is 1 after adding 1 pokemon
    - id is incremented by 1 after adding one pokemon
    - getter gets pokemonID
     */
    @Test
    void pokemonMapIsEmptyUponCreation() {
        PokemonLibrary pokemonMap = new PokemonLibrary();
        assertEquals(0, pokemonMap.pokedexMap.size());
    }

    @Test
    void mapSizeIncreasesAccordingToNumberOfAddedPokemons() {
        PokemonLibrary pokemonMap = new PokemonLibrary();
        pokemonMap.addPokemon(new Pokemon(2, "TestPokemon2", 100));
        assertEquals(1, pokemonMap.pokedexMap.size());
        pokemonMap.addPokemon(new Pokemon(3, "TestPokemon3", 80));
        assertEquals(2, pokemonMap.pokedexMap.size());
    }

    @Test
    void pokemonIdIsIncrementedByOneAfterAddingOnePokemon() {
        PokemonLibrary pokemonMap = new PokemonLibrary();
        pokemonMap.addPokemon(new Pokemon(2, "TestPokemon2", 100));
        assertEquals(2, pokemonMap.getPokemonId());
        pokemonMap.addPokemon(new Pokemon(3, "TestPokemon3", 80));
        assertEquals(3, pokemonMap.getPokemonId());
    }

    // No need to test if getter `getPokemonId` gets pokemonId, as this is included within test `pokemonIdIsIncrementedByOneAfterAddingOnePokemon()`.
}
