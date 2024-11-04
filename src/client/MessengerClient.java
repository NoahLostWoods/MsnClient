package client;

import client.handlers.ServerListener;
import java.io.*;
import java.net.*;

public class MessengerClient {
    private static final String SERVER_ADDRESS = "localhost"; // Indirizzo IP del server
    private static final int SERVER_PORT = 12345; // Porta del server

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public MessengerClient() {
        try {
            // Connessione al server
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("Connesso al server!");

            // Avvia un thread per ascoltare i messaggi dal server
            new Thread(new ServerListener(socket, in)).start();

            // Richiedi nome utente
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Inserisci il tuo nome utente: ");
            String username = consoleInput.readLine();

            // Messaggio di benvenuto
            out.println(username + " si è connesso!");

            // Legge input da console per inviare messaggi al server
            String message;
            while ((message = consoleInput.readLine()) != null) {
                // Invia un messaggio specificando il destinatario
                out.println(message); // formato: DESTINATARIO:MESSAGGIO
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MessengerClient();
    }
}
