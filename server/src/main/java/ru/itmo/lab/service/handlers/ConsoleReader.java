package ru.itmo.lab.service.handlers;


import ru.itmo.lab.repository.Storage;
import ru.itmo.lab.service.OutputMessage;
import ru.itmo.lab.service.commands.*;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleReader {
    public static CommandExecutor commandExecutor;

    public ConsoleReader() {
        commandExecutor = new CommandExecutor(new HelpCommand(), new InfoCommand(),
                new ShowCommand(), new InsertCommand(), new UpdateCommand(),
                new RemoveKeyCommand(), new ClearCommand(), new SaveCommand(),
                new ExecuteScriptCommand(), new ExitCommand(), new RemoveLowerCommand(),
                new HistoryCommand(), new RemoveLowerKeyCommand(), new MinByAgeCommand(),
                new FilterGreaterThanTypeCommand(), new PrintFieldDescendingAgeCommand());

    }
    public void readFromConsole(Storage storage) {
        Scanner scanner = new Scanner(System.in);
        Command.setScanner(scanner);
        while(true) {
            try {
                System.out.println("\nEnter command, please");
                String line = scanner.nextLine();
                commandExecutor.executeCommand(storage, line);
            } catch(NoSuchElementException e) {
                OutputMessage.printErrorMessage("\nInvalid character entered");
                System.exit(0);
            }
        }
    }

    public static CommandExecutor getCommandExecutor() {
        return commandExecutor;
    }
}
