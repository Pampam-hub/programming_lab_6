package ru.itmo.lab.service.handlers;
import ru.itmo.lab.service.OutputMessage;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleReader {
    socketWorker socketWorker;

    public ConsoleReader(socketWorker socketWorker) {
        this.socketWorker = socketWorker;
    }

    public void readFromConsole() throws IOException {
        while(true) {
            Scanner scanner = new Scanner(System.in);

            while(true) {
                try {
                    System.out.println("\nEnter command, please");
                    String line = scanner.nextLine();
                    CommandRequestWorker.superpupermethod(line, socketWorker);
                } catch(NoSuchElementException e) {
                    OutputMessage.printErrorMessage("\nInvalid character entered");
                    System.exit(0);
                }
            }
        }
    }
}
