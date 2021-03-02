package com.andruidteam.andruid.db;

import com.andruidteam.andruid.db.entity.CharacterEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Equivalent des Fixtures chez symphony
 */
public class DataGenerator {

    public static List<CharacterEntity> generateCharacters() {
        List<CharacterEntity> character = new ArrayList<>();

        CharacterEntity first = new CharacterEntity();
        first.setFirstName("drizzt");
        first.setLastName("do'urden");
        first.setClasse("clerc");
        first.setLevel(10);
        first.setRace("Elfe noir");

        character.add(first);
        return character;
    }


}
