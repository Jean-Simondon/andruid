package com.andruidteam.andruid.model;

import java.util.ArrayList;

public interface Character {

    int getId();
    String getFirstName();
    String getLastName();
    String getRace();
    String getClasse();
    int getLevel();
    ArrayList<String> getNotes();

}
