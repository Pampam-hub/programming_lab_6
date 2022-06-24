package ru.itmo.lab.service.handlers;

import ru.itmo.lab.repository.Storage;
import ru.itmo.lab.repository.exceptions.EntityNotFoundException;
import ru.itmo.lab.service.OutputMessage;
import ru.itmo.lab.service.commands.Command;
import ru.itmo.lab.service.commandresult.CommandResult;

import java.util.*;

public class CommandExecutor {
    public static Map<String, Command> AVAILABLE_COMMANDS = new LinkedHashMap<>();

    CommandExecutor(Command helpCommand, Command infoCommand,
                    Command showCommand, Command insertCommand,
                    Command updateCommand, Command removeKeyCommand,
                    Command clearCommand, Command saveCommand,
                    Command executeScriptCommand, Command exitCommand,
                    Command removeLowerCommand, Command historyCommand,
                    Command removeLowerKeyCommand, Command minByAgeCommand,
                    Command filterGreaterThanTypeCommand,
                    Command printFieldDescendingAgeCommand) {

        AVAILABLE_COMMANDS.put(helpCommand.getName(), helpCommand);
        AVAILABLE_COMMANDS.put(infoCommand.getName(), infoCommand);
        AVAILABLE_COMMANDS.put(showCommand.getName(), showCommand);
        AVAILABLE_COMMANDS.put(insertCommand.getName(), insertCommand);
        AVAILABLE_COMMANDS.put(updateCommand.getName(), updateCommand);
        AVAILABLE_COMMANDS.put(removeKeyCommand.getName(), removeKeyCommand);
        AVAILABLE_COMMANDS.put(clearCommand.getName(), clearCommand);
        AVAILABLE_COMMANDS.put(saveCommand.getName(), saveCommand);
        AVAILABLE_COMMANDS.put(executeScriptCommand.getName(),
                executeScriptCommand);
        AVAILABLE_COMMANDS.put(exitCommand.getName(), exitCommand);
        AVAILABLE_COMMANDS.put(removeLowerCommand.getName(),
                removeLowerCommand);
        AVAILABLE_COMMANDS.put(historyCommand.getName(), historyCommand);
        AVAILABLE_COMMANDS.put(removeLowerKeyCommand.getName(),
                removeLowerKeyCommand);
        AVAILABLE_COMMANDS.put(minByAgeCommand.getName(), minByAgeCommand);
        AVAILABLE_COMMANDS.put(filterGreaterThanTypeCommand.getName(),
                filterGreaterThanTypeCommand);
        AVAILABLE_COMMANDS.put(printFieldDescendingAgeCommand.getName(),
                printFieldDescendingAgeCommand);
    }

    public CommandResult executeCommand(Storage storage, String string) {
        CommandResult commandResult = null;

        string = string.trim();
        String[] splintedString = string.split(" ");
        String commandName = splintedString[0].toLowerCase();
        String[] commandsArgs = Arrays.copyOfRange(splintedString, 1, splintedString.length);

        if(AVAILABLE_COMMANDS.containsKey(commandName)) {
            try {
                Command command = AVAILABLE_COMMANDS.get(commandName);
                storage.fillHistory(command);
                commandResult = command.execute(storage, commandsArgs);
                commandResult.showCommandResult();
            } catch (EntityNotFoundException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        } else {
            OutputMessage.printErrorMessage("\nThere is no such command, for reference, enter command \"help\" ");
        }

        return commandResult;
    }

}
