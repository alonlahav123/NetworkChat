import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MyClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            Scanner sc = new Scanner(System.in);
            boolean run = true;

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            ReadingThread reader = new ReadingThread(in);
            reader.start();

            System.out.println("Write a message to Server!");

            while (run) {
                String clientText = sc.nextLine();
                if(clientText.equals("exit")) {
                    run = false;
                    System.out.println("You have disconnected from server");
                } else {
                    out.writeBytes(clientText + "\n");
                    out.flush();
                }
            }

            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
