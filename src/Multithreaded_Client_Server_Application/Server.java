package Multithreaded_Client_Server_Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Server is on at port 8080");
        while(true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");
            new ServerThread(socket);
        }
    }
}

class ServerThread implements Runnable {
    Socket clientSocket;
    Thread t;
    ServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
        t = new Thread(this);
        t.start();
    }

    public void run() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            while(true) {
                String clientMessage;   // Reading client message
                clientMessage = (String) objectInputStream.readObject();
                if(clientMessage == null)
                    break;
                String serverResponseMessage = "Message received: " + clientMessage.toUpperCase();
                objectOutputStream.writeObject(serverResponseMessage);  // Sending acknowledgement
                System.out.println("Response sent to client");
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}