package Client_Server_Application_Without_Threading;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("127.0.0.1", 8080);
        System.out.println("Client ready");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

        Scanner scanner = new Scanner(System.in);
        String clientMessage = scanner.nextLine();  // Getting input message from client
        objectOutputStream.writeObject(clientMessage);

        String serverResponseMessage;   // Reading response from server
        serverResponseMessage = (String) objectInputStream.readObject();
        System.out.println("Response from server: " + serverResponseMessage);
    }
}
