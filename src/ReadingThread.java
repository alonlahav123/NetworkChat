import java.io.DataInputStream;
import java.io.IOException;

public class ReadingThread extends Thread{

    private DataInputStream in;

    public ReadingThread(DataInputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        while(true) {
            try {
                String msg = in.readLine();
                System.out.println("[CLIENT]: " + msg);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
