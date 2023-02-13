import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Pokemon {
    private Map<Integer, List<String>> pokemonList;

    public List getPlayerPokemon() {
        return playerPokemon;
    }

    private List playerPokemon;

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
        pokemonList = data;
        return data;
    }

    public String choosePokemonByIndex(int index) {
        if (index <= 0) {
            throw new IllegalArgumentException("The index must be > 0.");
        }

        String pokemon = pokemonList.get(index).get(29);
        System.out.println("You chose " + pokemon + ".");
        // TODO Put in separate method?
        playerPokemon = pokemonList.get(index);
        return pokemon;
    }

    public String choosePokemonByIndex() {
        int index = getIndexFromPlayer();
        // Write second method where user has to enter index

        if (index <= 0) {
            throw new IllegalArgumentException("The index must be > 0.");
        }

        String pokemon = pokemonList.get(index).get(29);
        System.out.println("You chose " + pokemon + ".");
        // TODO Put in separate method?
        playerPokemon = pokemonList.get(index);
        return pokemon;
    }

    public int getIndexFromPlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose your Pokemon by index (from 1 to 153). See list in src/main/resources/PokedexAttacks.xlsx for Pokemon names and indices.");
        return scanner.nextInt();
    }

}
