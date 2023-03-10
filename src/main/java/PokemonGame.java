

import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class PokemonGame {
    String currentPlayer = "player";
    Pokemon playerPokemon;
    Pokemon computerPokemon;
    PokemonLibrary pokedex = new PokemonLibrary();
    String winner = "";

    public void readPokemonFile() throws IOException {
        BufferedReader reader = new BufferedReader(
                new FileReader("src/main/resources/PokedexAttacks.csv")
        );
        // .readLine() is like next()
        String currentLine = reader.readLine();
        // Skip first line, as it contains headings
        currentLine = reader.readLine();
        while (currentLine != null) {
            String[] entry = currentLine.split(",");
            Pokemon pokemon = new Pokemon(Integer.parseInt(entry[0]), entry[1], Integer.parseInt(entry[2]));
            pokedex.addPokemon(pokemon);
            currentLine = reader.readLine();
        }
        reader.close();
    }

    public void playerChoosesPokemon() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a pokemon by its name or its pokemon ID.");
        String pokemon = scanner.next();
        // if `pokemon` is a number:
        if (pokemon.matches("-?(0|[1-9]\\d*)")) {
            playerPokemon = choosePokemonByIndex(Integer.parseInt(pokemon));
        } else {
            playerPokemon = choosePokemonByName(pokemon);
        }
        System.out.println("You chose " + playerPokemon.getName() + ".");
        printPlayerPokemonNameAndASCII(playerPokemon.getPokemonId());
    }

    public void computerChoosesPokemon() throws IOException {
        int pokemonIndex = (int) ((Math.random() * (153 - 1)) + 1);
        computerPokemon = choosePokemonByIndex(pokemonIndex);
        System.out.println("The computer chose " + computerPokemon.getName() + ".");
        printPlayerPokemonNameAndASCII(pokemonIndex);
    }

    public Pokemon choosePokemonByIndex(int index) {
        return pokedex.pokedexMap.get(index);
    }

    public Pokemon choosePokemonByName(String pokemonName) {
        // TODO Downloaded ASCII art only includes the first 96 pokemons. The website it is taken from has the first 134, to be downloaded individually after the 96th pokemon.
        for (Map.Entry<Integer, Pokemon> pokemon : pokedex.pokedexMap.entrySet()) {
            if (pokemon.getValue().getName().equals(pokemonName)) {
                int pokemonId = pokemon.getKey();
                return pokedex.pokedexMap.get(pokemonId);
            }
        }

        throw new IllegalArgumentException("This pokemon does not exist.");
    }

    public void printPlayerPokemonNameAndASCII(int pokemonIndex) throws IOException {
        // open file, if it does not exist, print fallback
        String pokemonASCCIFile = generateASCII_ArtString(pokemonIndex);

        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader(pokemonASCCIFile));
            String line = in.readLine();
            while(line != null) {
                System.out.println(line);
                line = in.readLine();
            }
            in.close();
        } catch (IOException fnf) {
            in = new BufferedReader(new FileReader("src/main/resources/PokemonASCIIArt/pokemon_fallback_ASCIIArt.txt"));
            String line = in.readLine();
            while(line != null) {
                System.out.println(line);
                line = in.readLine();
            }
            in.close();
        }
    }

    public String generateASCII_ArtString(int pokemonIndex) {
        String pokemonASCCIFile = "src/main/resources/PokemonASCIIArt/";

        if (pokemonIndex < 10) {
            pokemonASCCIFile += "00" + Integer.toString(pokemonIndex) + ".txt";
        } else if (pokemonIndex > 9 && pokemonIndex < 100) {
            pokemonASCCIFile += "0" + Integer.toString(pokemonIndex) + ".txt";
        } else if (pokemonIndex > 99) {
            pokemonASCCIFile += Integer.toString(pokemonIndex) + ".txt";
        }

        return pokemonASCCIFile;

    }

    public void printPokemonStats() {
        System.out.println("Player's pokemon: " + playerPokemon.getName() + ", current HP/initial HP: " + playerPokemon.getHpCurrent() + "/" + playerPokemon.getHpInitial());
        System.out.println("Computer's pokemon: " + computerPokemon.getName() + ", current HP/initial HP: " + computerPokemon.getHpCurrent() + "/" + computerPokemon.getHpInitial());
    }

    public void printAllPokemons() {
        System.out.println("All pokemons:");

        for (Map.Entry<Integer, Pokemon> pokemon: pokedex.pokedexMap.entrySet()) {
            System.out.println(pokemon.getValue().toString());
            Pokemon entry = pokemon.getValue();
            // cannot access attribute as they are private
            // for each attribute in entry
                // print attribute

        }
    }

    public void attack() {
        int attackPoints = generateAttackPoints();

        if (currentPlayer.equals("player")) {
            computerPokemon.setHpCurrent(computerPokemon.getHpCurrent() - attackPoints);
            System.out.println(playerPokemon.getName() + " deals " + attackPoints + " damage.");
            System.out.println("Your current HP points: " + playerPokemon.getHpCurrent() + ". Computer's current HP points: " + computerPokemon.getHpCurrent());
            changeCurrentPlayer();
        } else if (currentPlayer.equals("computer")) {
            playerPokemon.setHpCurrent(playerPokemon.getHpCurrent() - attackPoints);
            System.out.println(computerPokemon.getName() + " deals " + attackPoints + " damage.");
            System.out.println("Your current HP points: " + playerPokemon.getHpCurrent() + ". Computer's current HP points: " + computerPokemon.getHpCurrent());
            changeCurrentPlayer();
        }
    }

    public void changeCurrentPlayer() {
        if (currentPlayer.equals("player")) {
            currentPlayer = "computer";
        } else  if (currentPlayer.equals("computer")) {
            currentPlayer = "player";
        }
    }

    // Generate random attack points between 1 and 20
    public int generateAttackPoints() {
        return (int) ((Math.random() * (21 - 1)) + 1);
    }

    public String compareCurrentHP() {
        if ((playerPokemon.getHpCurrent() <= 0)
            && (playerPokemon.getHpCurrent() < computerPokemon.getHpCurrent())) {
            System.out.println("The computer won!");
            winner = "computer";
            System.exit(0);
        } else if ((computerPokemon.getHpCurrent() <= 0)
                && (computerPokemon.getHpCurrent() < playerPokemon.getHpCurrent())) {
            System.out.println("You won the game!");
            winner = "player";
        }
        return winner;
    }

    public void startGame() throws IOException {
        System.out.println("NEW ROUND...\n");
        readPokemonFile();
        printAllPokemons();
        playerChoosesPokemon();
        computerChoosesPokemon();
        printPokemonStats();
        while (winner.equals("")) {
            attack();
            compareCurrentHP();
        }
    }
}
