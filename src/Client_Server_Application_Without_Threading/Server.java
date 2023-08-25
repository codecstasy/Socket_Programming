package Client_Server_Application_Without_Threading;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Server is on at port 8080");

        while(true) {
            System.out.println("Listening to port 8080");
            Socket socket = serverSocket.accept();
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            String clientMessage;   // Reading client message
            clientMessage = (String) objectInputStream.readObject();
            String serverResponseMessage = "Message received from client: " + clientMessage.toUpperCase();
            objectOutputStream.writeObject(serverResponseMessage);  // Sending acknowledgement
            System.out.println("Response sent to client");
        }
    }
}
