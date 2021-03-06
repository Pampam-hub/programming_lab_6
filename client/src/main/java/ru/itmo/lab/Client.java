package ru.itmo.lab;

import ru.itmo.lab.service.OutputMessage;
import ru.itmo.lab.service.handlers.SocketWorker;
import ru.itmo.lab.service.handlers.ConsoleReader;

import java.io.IOException;

public class Client {
    public static void main(String[] args) {
        try {
            SocketWorker socketWorker = startClientWorker();

            ConsoleReader consoleReader = new ConsoleReader(socketWorker);
            consoleReader.readFromConsole();
        } catch (IOException e) {
            OutputMessage.printErrorMessage("failed to open datagram channel");
        }
    }

    private static SocketWorker startClientWorker() throws IOException {
        return new SocketWorker();
    }
}
