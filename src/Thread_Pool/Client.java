package Thread_Pool;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket clientSocket = new Socket(HOST, PORT);
        System.out.println("Client connected to server: " + clientSocket.getRemoteSocketAddress());

//        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

        Scanner sc = new Scanner(System.in);
        while(true) {
            String message = sc.nextLine();
            if(message.equals("Exit"))
                break;
            // Write a message to the server
            objectOutputStream.writeObject(message);

            // Reading the response from server
            String response = (String)objectInputStream.readObject();
            System.out.println("Response from server: " + response);
            objectOutputStream.flush();
        }

        clientSocket.close();
    }
}
