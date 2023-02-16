package org.example;

public class Pokemon {

    int pokemonId;
    String name;
    int hpInitial;
    int hpCurrent;

    public Pokemon(int pokemonId, String name, int hpInitial) {
        this.pokemonId = pokemonId;
        this.name = name;
        this.hpInitial = hpInitial;
        this.hpCurrent = hpInitial;
    }

    public void attack(int attackNumber) {

    }

    // Getters
    // FIXME I'm not using the getters
    public String getName() {
        return name;
    }

    public int getHpInitial() {
        return hpInitial;
    }

    public int getHpCurrent() {
        return hpCurrent;
    }

    // Setter
    public void setHpCurrent(int hpCurrent) {
        this.hpCurrent = hpCurrent;
    }
}
