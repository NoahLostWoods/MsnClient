package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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

            // Legge input da console per inviare messaggi al server
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
            String message;
            while ((message = consoleInput.readLine()) != null) {
                sendMessage(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodo per inviare messaggi al server
    public void sendMessage(String message) {
        out.println(message);
    }

    public static void main(String[] args) {
        new MessengerClient();
    }
}
