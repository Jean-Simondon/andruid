package com.andruidteam.andruid.db;

import com.andruidteam.andruid.db.entity.CharacterEntity;
import com.andruidteam.andruid.db.entity.GameEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Equivalent des Fixtures chez symphony
 */
public class DataGenerator {

    public static final String TAG = "DataGenerator";

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

    public static ArrayList<GameEntity> generateGames() {

        ArrayList<GameEntity> listGame = new ArrayList<>();

        listGame.add(new GameEntity(
            1,
            "Aventures de Fitz - 1",
            "'La série des aventures de Fitz est une série de scénarios pour D&D 3.5 pour 4 personnages joueurs. La première quête : À la recherche de l\\'élu Ogre-Dragon est joué par deux joueur. La deuxième quête sera joué par les deux autres joueurs, puis à la fin de cette deuxième aventure, les personnages se rencontreront et la commencera l\\'épopée de 4 personnages hors paires. PS: Pour faire cette quête, les personnages joueurs devront prendre des races et classes que j\\'ai créer."
        ));

        listGame.add(new GameEntity(
            2,
            "Le Sceau des Sept Soeurs",
            "Les PJs reprennent connaissance dans une bien étrange prison, dont les prisonniers semblent aussi très particuliers. Ils comprendront cependant très vite que s'ils n'en sortent pas au plus vite, les Sept Sœurs vont mettre leurs plans à exécution et détruire l'Anneau de l'Hiver. Mais une surprise les attend à la sortie de cette \"prison\", bien plus lointaine qu'ils ne l'imaginent..."
        ));

        return listGame;
    }


}
