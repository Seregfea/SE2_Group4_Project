package com.example.se2_group4_project.backend.callbacks;

import java.io.IOException;

public interface ServerClientCallbacks {
    void getMessage(String message) throws IOException;
    void acceptDice(String message) throws IOException;
}
