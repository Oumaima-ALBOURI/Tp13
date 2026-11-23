package com.example.tp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ChatHistory {

    // Sauvegarder l'historique
    public static void save(String path, List<ChatMessage> history)
            throws IOException {

        try (ObjectOutputStream oos =
                new ObjectOutputStream(new FileOutputStream(path))) {

            // Écrire le nombre de messages
            oos.writeInt(history.size());

            // Écrire chaque message
            for (ChatMessage msg : history) {
                msg.writeExternal(oos);
            }
        }
    }

    // Charger l'historique
    public static List<ChatMessage> load(String path)
            throws IOException, ClassNotFoundException {

        List<ChatMessage> history = new ArrayList<>();

        try (ObjectInputStream ois =
                new ObjectInputStream(new FileInputStream(path))) {

            // Lire le nombre de messages
            int count = ois.readInt();

            // Lire chaque message
            for (int i = 0; i < count; i++) {

                ChatMessage msg = new ChatMessage();

                msg.readExternal(ois);

                history.add(msg);
            }
        }

        return history;
    }
}