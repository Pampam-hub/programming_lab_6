package ru.itmo.lab;

import ru.itmo.lab.repository.DragonTreeMapStorage;
import ru.itmo.lab.service.OutputMessage;
import ru.itmo.lab.repository.Storage;
import ru.itmo.lab.service.commands.servercommands.ExitServerCommand;
import ru.itmo.lab.service.commands.servercommands.HelpServerCommand;
import ru.itmo.lab.service.commands.servercommands.SaveServerCommand;
import ru.itmo.lab.service.commands.clientcommands.*;
import ru.itmo.lab.service.handlers.*;

import java.io.IOException;

public class Server {
    public static void main(String[] args) {
        String file = System.getenv("DATA_FILEPATH");
        if(file == null) {
            OutputMessage.printErrorMessage("DATA_FILEPATH env variable is not specified ");
            System.exit(1);
        }

        try {
            FileChecker.checkFile(file);
            Storage storage = new DragonTreeMapStorage();
            CommandExecutor commandExecutor = new CommandExecutor(new HelpCommand(),
                    new InfoCommand(), new ShowCommand(), new InsertCommand(),
                    new UpdateCommand(), new RemoveKeyCommand(), new ClearCommand(),
                    new ExitCommand(), new RemoveLowerCommand(),
                    new HistoryCommand(), new RemoveLowerKeyCommand(),
                    new MinByAgeCommand(), new FilterGreaterThanTypeCommand(),
                    new PrintFieldDescendingAgeCommand(),
                    new HelpServerCommand(), new SaveServerCommand(),
                    new ExitServerCommand());
            
            XMLReader.readFromXML(file, storage);
            OutputMessage.printSuccessfulMessage("Collection from file was add successfully");
            SocketWorker socketWorker = startSocketWorker();

            RequestThread requestThread = new RequestThread(socketWorker, commandExecutor, storage);
            ConsoleReader consoleReader = new ConsoleReader(commandExecutor);
            ConsoleThread consoleThread = new ConsoleThread(consoleReader, storage);

            requestThread.start();
            consoleThread.start();
        } catch (IOException e) {
            OutputMessage.printErrorMessage(e.getMessage());
        }

    }

    private static SocketWorker startSocketWorker() {
        return new SocketWorker();
    }
}
