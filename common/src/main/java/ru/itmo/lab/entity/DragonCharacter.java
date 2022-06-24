package ru.itmo.lab.entity;

public enum DragonCharacter {
    GOOD,
    CHAOTIC,
    FICKLE;

    public static StringBuilder show() {
        StringBuilder dragonCharacters = new StringBuilder("");
        for(DragonCharacter character: values()) {
            dragonCharacters.append(character);
            dragonCharacters.append(", ");
        }
        dragonCharacters.deleteCharAt(dragonCharacters.length()-1);
        dragonCharacters.deleteCharAt(dragonCharacters.length()-1);
        return dragonCharacters;
    }
}
