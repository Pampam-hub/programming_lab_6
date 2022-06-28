package ru.itmo.lab.service.handlers;

import ru.itmo.lab.repository.ConsoleWorker;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class ScriptReader {
    SocketWorker socketWorker;

    public ScriptReader(SocketWorker socketWorker) {
        this.socketWorker = socketWorker;
    }

    public void readFromScript(String file) throws IOException {
        Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(file)));

        ConsoleWorker.getConsoleWorker().setScanner(scanner);
        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            CommandRequestWorker.superpupermethod(line, socketWorker);
        }

        scanner.close();
    }
}
