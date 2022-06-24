package ru.itmo.lab.service.handlers;

import ru.itmo.lab.repository.ConsoleWorker;
import ru.itmo.lab.repository.Storage;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScriptReader {

    public void readScript(Storage storage, String file) throws FileNotFoundException {
        if (storage.getPreviousFiles().contains(file)) {
            throw new IllegalArgumentException("Possible looping");
        }
        storage.addPreviousFiles(file);
        Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(file)));
        ConsoleWorker.getConsoleWorker().setScanner(scanner);
        CommandExecutor commandExecutor = ConsoleReader.getCommandExecutor();
        while(scanner.hasNext() & ConsoleWorker.getConsoleWorker().isExecutedScript()) {
            String line = scanner.nextLine();
            commandExecutor.executeCommand(storage, line);
        }
        scanner.close();
    }

}
