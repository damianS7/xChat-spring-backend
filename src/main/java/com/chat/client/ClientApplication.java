package com.chat.client;

import java.awt.EventQueue;
import java.io.File;

import com.chat.client.ui.ClientUI;

public class ClientApplication {
    public static ClientUI ui;
    public static ClientChat client;
    public static Preferences config;

    public static void main(String[] args) {
        config = new Preferences(new File(""));
        client = new ClientChat();

        // Inicio de la interfaz grafica en un hilo separado
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                ui = new ClientUI();
            }
        });
    }
}
