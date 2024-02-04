import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        System.out.println("Server started");
        int port = 8890;

        try(ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try(Socket clientSocket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    System.out.printf("New connection accepted. Port: %d%n", clientSocket.getPort());

                    out.println("Хотите зарегистрироваться на нашем сервисе? (yes/no)");
                    if (in.readLine().equals("yes")) {
                        out.println("Введите имя: ");
                        String name = in.readLine();
                        out.println("Введите пароль: ");
                        String key = in.readLine();
                        out.println("Спасибо, " + name + ", что Вы с нами! Регистрация пройдена успешно!");
                    } else {
                        out.println("Очень жаль!");
                        clientSocket.close();
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
