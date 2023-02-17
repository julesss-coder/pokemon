//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//public class PokemonTest {
//    private Pokemon pokemonGame;
//
//    @BeforeEach
//    void setUp() {
//        Pokemon pokemonGame = new Pokemon();
//    }
//
//    // Test if returned list has expected length
//    @Test
//    void pokemonsShouldBeReadfromFileIntoArray() throws IOException {
//        Pokemon pokemonGame = new Pokemon();
//
//        Map<Integer, List<String>> pokemonMap = pokemonGame.readPokemonsFromFile();
//        assertEquals(154, pokemonMap.size());
//    }
//
//    // TODO Should assign the returned list to pokemonGame.pokemonList.
//    @Test
//    void userCanChoosePokemonFromListByIndex() throws IOException {
//        Pokemon pokemonGame = new Pokemon();
//        // TODO: Look up "Java Junit testing simulate user input"
//
//        // Test: Does choosing a pokemon from pokemonList work?
//        assertEquals("Bulbasaur", pokemonGame.readPokemonsFromFile().get(1).get(29));
//        assertEquals("Metapod", pokemonGame.readPokemonsFromFile().get(11).get(29));
//
//        // Test: Does the method `choosePokemonByIndex(int i)` work?
//        assertEquals("Metapod", pokemonGame.choosePokemonByIndex(11).get(29));
//        assertEquals("Bulbasaur", pokemonGame.choosePokemonByIndex(1).get(29));
//        // Not testing method `choosePokemonByIndex()`, as it requires user input, but its functionality has been tested in the above two tests with `userCanChoosePokemonFromListByIndex()`.
//
//        // TODO How do I test that `playerPokemon` was assigned a specific pokemon? use setter and getter and test both at the same time?
//    }
//
//    @Test
//    void index_0_ThrowsIllegalArgumentException() throws IOException {
//        Pokemon pokemonGame = new Pokemon();
//        assertThrows(IllegalArgumentException.class, () -> pokemonGame.choosePokemonByIndex(0));
//    }
//
//
//    // FIXME deal with empty list || always assign a value to list on game start
//    @Test
//    void userCanChoosePokemonByName() throws IOException {
//        Pokemon pokemonGame = new Pokemon();
//        pokemonGame.readPokemonsFromFile();
//        // the name in the ith entry in pokemonList === return value of choosePokemonByName(Bulbasaur)
//        // i = 1 (index of pokemonList entry for Bulbasaur), 29 = index of name
//        assertEquals(pokemonGame.getPokemonList().get(1), pokemonGame.choosePokemonByName("Bulbasaur"));
//        assertEquals(pokemonGame.getPokemonList().get(14), pokemonGame.choosePokemonByName("Kakuna"));
//    }
//
//    @Test
//    void invalidUserInputForPokemonNameThrowsIllegalArgumentException() throws IOException {
//        Pokemon pokemonGame = new Pokemon();
//        pokemonGame.readPokemonsFromFile();
//        assertThrows(IllegalArgumentException.class, () -> pokemonGame.choosePokemonByName("Invalid entry"));
//    }
//
//    // TODO Do I need to test `printPLayerPokemonName()`? "player's pokemon name is printed to console".
//
//    // FIXME How do I test this?: Player's pokemon ASCII art representation is printed to console
//
//
//    // play a round
//
//    // let player choose whether to choose pokemon by index or by name
//
//
//
//    // TODO Continue here
//    // Test if computer generates random integer between 1 and 152
//
//
//    @Test
//    void computerChoosesRandomPokemonByIndex() throws IOException {
//        Pokemon pokemonGame = new Pokemon();
//        pokemonGame.readPokemonsFromFile();
//        assertEquals(pokemonGame.getPokemonList().get(1), pokemonGame.haveComputerChoosePokemonByIndex(1));
//        assertEquals(pokemonGame.getPokemonList().get(15), pokemonGame.haveComputerChoosePokemonByIndex(15));
//    }
//
//
//
//
//}
