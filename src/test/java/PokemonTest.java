import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PokemonTest {
    private Pokemon pokemonGame;

    @BeforeEach
    void setUp() {
        Pokemon pokemonGame = new Pokemon();
    }

    // Test if returned list has expected length
    @Test
    void pokemonsShouldBeReadfromFileIntoArray() throws IOException {
        Pokemon pokemonGame = new Pokemon();

        Map<Integer, List<String>> pokemonMap = pokemonGame.readPokemonsFromFile();
        assertEquals(154, pokemonMap.size());
    }

    // TODO Should assign the returned list to pokemonGame.pokemonList.
    @Test
    void userCanChoosePokemonFromListByIndex() throws IOException {
        Pokemon pokemonGame = new Pokemon();
        // TODO: Look up "Java Junit testing simulate user input"

        // Test: Does choosing a pokemon from pokemonList work?
        assertEquals("Bulbasaur", pokemonGame.readPokemonsFromFile().get(1).get(29));
        assertEquals("Metapod", pokemonGame.readPokemonsFromFile().get(11).get(29));

        // Test: Does the method `choosePokemonByIndex(int i)` work?
        assertEquals("Metapod", pokemonGame.choosePokemonByIndex(11));
        assertEquals("Bulbasaur", pokemonGame.choosePokemonByIndex(1));
        // Not testing method `choosePokemonByIndex()`, as it requires user input, but its functionality has been tested in the above two tests with `userCanChoosePokemonFromListByIndex()`.

        // TODO How do I test that `playerPokemon` was assigned a specific pokemon? use setter and getter and test both at the same time?
    }

    @Test
    void index_0_ThrowsIllegalArgumentException() throws IOException {
        Pokemon pokemonGame = new Pokemon();
        assertThrows(IllegalArgumentException.class, () -> pokemonGame.choosePokemonByIndex(0));
    }


    // user can choose pokemon by name
    // user chooses "Bulbasaur"
        // find the line in pokemon list where index 29 === "Bulbasaur"
        // assign this line to pokemonList
    // i.e. playerPokemon = chosen pokemon
    @Test
    void userCanChoosePokemonByName() {
        Pokemon pokemonGame = new Pokemon();
//        pokemonGame.choosePokemonByName("BulbaSaur")

    }

    @Test
    void invalidUserInputForPokemonNameThrowsIllegalArgumentException() {}

    // player's pokemon name is printed to console
    // player's pokemon ASCII art representation is printed to console



}
