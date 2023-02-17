import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PokemonTest {
    private Pokemon pokemon;

    @BeforeEach
    void setUp() {
        Pokemon pokemon = new Pokemon(1, "TestPokemon", 100);
    }

    @Test
    void returnsNewPokemon() {
        Pokemon returnedPokemon = new Pokemon(1, "TestPokemon", 100);
        assertTrue(returnedPokemon instanceof Pokemon);
    }

    @Test
    void getterGetsPokemonId() {
        Pokemon pokemon = new Pokemon(1, "TestPokemon", 100);
        assertEquals(1, pokemon.getPokemonId());
    }

    @Test
    void getterGetsPokemonHPInitial() {
        Pokemon pokemon = new Pokemon(1, "TestPokemon", 100);
        assertEquals(100, pokemon.getHpInitial());
    }

    @Test
    void getterGetsPokemonHPCurrent() {
        Pokemon pokemon = new Pokemon(1, "TestPokemon", 100);
        assertEquals(100, pokemon.getHpCurrent());
    }

    @Test
    void setterSetsHPCurrent() {
        Pokemon pokemon = new Pokemon(1, "TestPokemon", 100);
        pokemon.setHpCurrent(80);
        assertEquals(80, pokemon.getHpCurrent());
    }
}
