package ru.itmo.lab;

import ru.itmo.lab.repository.Storage;
import ru.itmo.lab.repository.commandresult.CommandResult;
import ru.itmo.lab.request.Request;
import ru.itmo.lab.service.handlers.ConsoleReader;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleThread extends Thread {
    private final ConsoleReader consoleReader;
    private final Storage storage;

    public ConsoleThread(ConsoleReader consoleReader, Storage storage) {
        this.consoleReader = consoleReader;
        this.storage = storage;
    }

    @Override
    public void run() {
        while (true) {
            consoleReader.readFromConsole(storage);
        }
    }
}
