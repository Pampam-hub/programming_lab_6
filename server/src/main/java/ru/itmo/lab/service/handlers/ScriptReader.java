package ru.itmo.lab.service.handlers;

import ru.itmo.lab.repository.Storage;
import ru.itmo.lab.service.commands.*;

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
        Command.setScanner(scanner);
        CommandExecutor commandExecutor = ConsoleReader.getCommandExecutor();
        while(scanner.hasNext() & Command.isExecutedScript()) {
            String line = scanner.nextLine();
            commandExecutor.executeCommand(storage, line);
        }
        scanner.close();
    }

}
