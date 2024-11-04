package client;

import java.io.*;
import java.net.Socket;

public class ServerListener implements Runnable {
    private Socket socket;
    private BufferedReader in;

    public ServerListener(Socket socket, BufferedReader in) {
        this.socket = socket;
        this.in = in;
    }

    @Override
    public void run() {
        String message;
        try {
            while ((message = in.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Disconnesso dal server.");
        }
    }
}
