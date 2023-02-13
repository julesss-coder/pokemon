import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Pokemon pokemonGame = new Pokemon();
        // TODO Ich brauche eine Methode wie `playRound`, mit der ich eine ganze Runde durchspielen kann und die alle notwendigen Methoden aufruft.
        pokemonGame.readPokemonsFromFile();
//        pokemonGame.choosePokemonByIndex();
    }
}
