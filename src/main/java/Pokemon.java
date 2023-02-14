import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class Pokemon {

    private Map<Integer, List<String>> pokemonList;
    private List playerPokemon;
    private String playerPokemonName;
    private List computerPokemon;
    private String computerPokemonName;

    // Ich werfe hier keine Exception, sondern die Klasse `XSSFWorkbook` hat ein Exception Handling.
    // D.h. bereits "eingebauten" Java-Code muss ich nicht testen. Ich muss nur testen, was ich selbst schreibe.
    // Warum kann ich keine HashMap returnen, sondern nur eine Map?

    public Map<Integer, List<String>> readPokemonsFromFile() throws IOException {
        FileInputStream file = new FileInputStream(new File("src/main/resources/PokedexAttacks.xlsx"));
        Workbook workbook = new XSSFWorkbook(file);

        Sheet sheet = workbook.getSheetAt(0);

        Map<Integer, List<String>> data = new HashMap<>();
        int i = 0;
        for (Row row : sheet) {
            data.put(i, new ArrayList<String>());
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING:
                        // Replaced `new Integer(i)` (deprecated) with `Integer.valueOf(i)` (Source: https://stackoverflow.com/questions/47095474/the-constructors-integerint-doubledouble-longlong-and-so-on-are-deprecat)
                        // TODO Look up "boxing"
                        data.get(Integer.valueOf(i)).add(cell.getRichStringCellValue().getString());
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            data.get(i).add(cell.getDateCellValue() + "");
                        } else {
                            data.get(i).add(cell.getNumericCellValue() + "");
                        }
                        break;
                    case BOOLEAN:
                        data.get(i).add(cell.getBooleanCellValue() + "");
                        break;
                    case FORMULA:
                        data.get(i).add(cell.getCellFormula() + "");
                        break;
                        // Replaced `new Integer(i)` (deprecated) with `Integer.valueOf(i)` (Source: https://stackoverflow.com/questions/47095474/the-constructors-integerint-doubledouble-longlong-and-so-on-are-deprecat)
                    default: data.get(Integer.valueOf(i)).add(" ");
                }
            }
            i++;
        }
        System.out.println(data.size());
        System.out.println(data);
        pokemonList = data;
        return data;
    }

    public String choosePokemonByIndex(int index) {
        if (index <= 0) {
            throw new IllegalArgumentException("The index must be > 0.");
        }

        String pokemon = pokemonList.get(index).get(29);
        printPlayerPokemonName(pokemon);
//        System.out.println("You chose " + pokemon + ".");
        // TODO Put in separate method?
        playerPokemon = pokemonList.get(index);
        playerPokemonName = pokemon;
        return pokemon;
    }

    public String choosePokemonByIndex() {
        int index = getIndexFromPlayer();
        // Write second method where user has to enter index

        if (index <= 0) {
            throw new IllegalArgumentException("The index must be > 0.");
        }

        String pokemon = pokemonList.get(index).get(29);
        printPlayerPokemonName(pokemon);
//        System.out.println("You chose " + pokemon + ".");
        // TODO Put in separate method?
        playerPokemon = pokemonList.get(index);
        playerPokemonName = pokemon;
        return pokemon;
    }

    // TODO Continue here
    public List<String> computerChoosesPokemon() {

    }

    public int getIndexFromPlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose your Pokemon by index (from 1 to 153). See list in src/main/resources/PokedexAttacks.xlsx for Pokemon names and indices.");
        return scanner.nextInt();
    }

    public List<String> choosePokemonByName(String pokemonName) throws IOException {
        // The last two lines in pokemonList are empty, so I don't iterate through them.
        for (int i = 1; i < pokemonList.size() - 2; i++) {
            if (pokemonList.get(i).get(29).equals(pokemonName)) {
                printPlayerPokemonName(pokemonName);
                playerPokemonName = pokemonName;
                playerPokemon = pokemonList.get(i);
                printPokemonASCII();
                return playerPokemon;
            }
        }

        // Case: pokemon name does not exist
        throw new IllegalArgumentException("This pokemon does not exist.");
    }

    public List<String> choosePokemonByName() throws IOException {
        String pokemonName = getPokemonNameFromPlayer();
        // Search pokemonList for name

        // Case: pokemon name found
        // The last two lines in pokemonList are empty, so I don't iterate through them.
        for (int i = 1; i < pokemonList.size() - 2; i++) {
            if (pokemonList.get(i).get(29).equals(pokemonName)) {
                printPlayerPokemonName(pokemonName);
                playerPokemonName = pokemonName;
                playerPokemon = pokemonList.get(i);
                printPokemonASCII();
                return playerPokemon;
            }
        }

        // Case: pokemon name does not exist
        throw new IllegalArgumentException("This pokemon does not exist.");
    }

    public void printPlayerPokemonName(String pokemonName) {
        System.out.println("You chose " + pokemonName + ".");
    }

    public String getPokemonNameFromPlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose your pokemon by name.");
        // TODO Deal with invalid entry
        return scanner.nextLine(); // Does this work? Should I use `next()` instead?
    }

    public void printPokemonASCII() throws IOException {
        String pokemonASCCIFile = generateASCII_ArtString();

        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader(pokemonASCCIFile));
            String line = in.readLine();
            while(line != null)
            {
                System.out.println(line);
                line = in.readLine();
            }
            in.close();
        } catch (FileNotFoundException fnf) {
            in = new BufferedReader(new FileReader("src/main/resources/Pokedex -ASCII-art/fallbackASCII.txt"));
            String line = in.readLine();
            while(line != null)
            {
                System.out.println(line);
                line = in.readLine();
            }
            in.close();
        }
    }

    public String generateASCII_ArtString() {
        String pokemonASCCIFile = "src/main/resources/Pokedex -ASCII-art/";

        // The last two lines in pokemonList are empty, so I don't iterate through them.
        for (int i = 1; i < pokemonList.size() - 2; i++) {
            List<String> entry = pokemonList.get(i);
            if (entry.get(29).equals(playerPokemonName)) {
                int pokedexID = (int) Double.parseDouble(entry.get(31));
                if (pokedexID < 10) {
                    pokemonASCCIFile += "00" + Integer.toString(pokedexID) + ".txt";
                } else if (pokedexID > 9 && pokedexID < 100) {
                    pokemonASCCIFile += "0" + Integer.toString(pokedexID) + ".txt";
                } else if (pokedexID > 99) {
                    pokemonASCCIFile += Integer.toString(pokedexID) + ".txt";
                }
            }
        }

        return pokemonASCCIFile;
    }

    // Getter methods
    public Map<Integer, List<String>> getPokemonList() {
        return pokemonList;
    }

    public List getPlayerPokemon() {
        return playerPokemon;
    }

    public List getComputerPokemon() {
        return computerPokemon;
    }

    public String getComputerPokemonName() {
        return computerPokemonName;
    }

}
