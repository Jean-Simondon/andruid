package com.andruidteam.andruid.db;

import com.andruidteam.andruid.db.entity.CharacterEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Equivalent des Fixtures chez symphony
 */
public class DataGenerator {

    public static ArrayList<CharacterEntity> generateCharacters() {

        ArrayList<CharacterEntity> listCharacter = new ArrayList<>();

        listCharacter.add(new CharacterEntity(
                1,
                "Acérérak",
                "Le dévoreur",
                "Cambion",
                "Clerc",
                1
        ));

        listCharacter.add(new CharacterEntity(
                2,
                "Drizzt",
                "Do'urden",
                "Elfe noir",
                "Rôdeur",
                1
        ));

        listCharacter.add(new CharacterEntity(
                3,
                "Minsc",
                null,
                "Humain",
                "Ranger",
                1
        ));

        listCharacter.add(new CharacterEntity(
                4,
                "Imoen",
                null,
                "Humaine",
                "Voleuse",
                1
        ));

        return listCharacter;
    }


}
