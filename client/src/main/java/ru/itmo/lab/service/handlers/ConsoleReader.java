package ru.itmo.lab.handlers;


import ru.itmo.lab.OutputMessage;
import ru.itmo.lab.service.commands.*;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleReader {
    ClientSocketWorker clientSocketWorker;

    ConsoleReader(ClientSocketWorker clientSocketWorker) {
        this.clientSocketWorker = clientSocketWorker;
    }

    public void sendRequest() throws IOException {
        while(true) {
            // метод 1
            Scanner scanner = new Scanner(System.in);
            while(true) {
                try {
                    System.out.println("\nEnter command, please");
                    String line = scanner.nextLine();
                    CommandRequestWorker.superpupermethod(line, clientSocketWorker);
                } catch(NoSuchElementException e) {
                    OutputMessage.printErrorMessage("\nInvalid character entered");
                    System.exit(0);
                }
            }
        }
    }
}
