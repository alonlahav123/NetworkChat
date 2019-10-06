import javax.xml.crypto.Data;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyServer {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            Socket client = serverSocket.accept();
            Scanner sc = new Scanner(System.in);
            boolean run = true;

            DataInputStream in = new DataInputStream(new BufferedInputStream(client.getInputStream()));
            DataOutputStream out = new DataOutputStream(client.getOutputStream());

            ReadingThread reader = new ReadingThread(in);
            reader.start();

            System.out.println("Say hi to the client!");

            while (run) {
                String serverText = sc.nextLine();
                if(serverText.equals("exit")) {
                    run = false;
                    System.out.println("You have disconnected from client");
                } else {
                    out.writeBytes(serverText + "\n");
                }
            }

            out.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
