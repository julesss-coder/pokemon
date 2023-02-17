

public class Pokemon {
    private int pokemonId;
    private String name;
    private int hpInitial;
    private int hpCurrent;

    public Pokemon(int pokemonId, String name, int hpInitial) {
        this.pokemonId = pokemonId;
        this.name = name;
        this.hpInitial = hpInitial;
        this.hpCurrent = hpInitial;
    }

    @Override
    public String toString() {
        return pokemonId + ", " + name + ", HP: " + hpInitial;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getHpInitial() {
        return hpInitial;
    }

    public int getHpCurrent() {
        return hpCurrent;
    }

    public int getPokemonId() {
        return pokemonId;
    }

    // Setter
    public void setHpCurrent(int hpCurrent) {
        this.hpCurrent = hpCurrent;
    }
}
