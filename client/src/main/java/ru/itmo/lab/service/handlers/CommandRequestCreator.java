package ru.itmo.lab.handlers;

import ru.itmo.lab.CommandToSend;
import ru.itmo.lab.OutputMessage;
import ru.itmo.lab.request.*;

import java.util.Map;
import java.util.TreeMap;

public class CommandRequestCreator {
    public static final Map<String, CommandRequest> AVAILABLE_COMMANDS = new TreeMap<>();

    public CommandRequestCreator() {
        AVAILABLE_COMMANDS.put("help", new WithoutArgsCommandRequest());
        AVAILABLE_COMMANDS.put("info", new WithoutArgsCommandRequest());
        AVAILABLE_COMMANDS.put("show", new WithoutArgsCommandRequest());
        AVAILABLE_COMMANDS.put("insert", new WithIdAndDragonCommandRequest());
        AVAILABLE_COMMANDS.put("update", new WithIdAndDragonCommandRequest());
        AVAILABLE_COMMANDS.put("remove_key", new WithIdCommandRequest());
        AVAILABLE_COMMANDS.put("clear", new WithoutArgsCommandRequest());
        AVAILABLE_COMMANDS.put("execute_script", "А вот тут не ясно шо делать");
        AVAILABLE_COMMANDS.put("exit", new WithoutArgsCommandRequest());
        AVAILABLE_COMMANDS.put("remove_lower", new WithDragonCommandRequest());
        AVAILABLE_COMMANDS.put("history", new WithoutArgsCommandRequest());
        AVAILABLE_COMMANDS.put("remove_lower_key", new WithIdCommandRequest());
        AVAILABLE_COMMANDS.put("min_by_age", new WithoutArgsCommandRequest());
        AVAILABLE_COMMANDS.put("filter_grater_than_type", new WithTypeCommandRequest());
        AVAILABLE_COMMANDS.put("print_field_descending_age", new WithoutArgsCommandRequest());
    }
    public Request createCommandRequest(String line) {
        CommandToSend commandToSend = CommandToSendCreator.createCommandToSend(line);

        Request request = null;
        if (AVAILABLE_COMMANDS.containsKey(commandToSend.getCommandName())) {
            request = AVAILABLE_COMMANDS.get(commandToSend.getCommandName()).createRequest(commandToSend);
        } else {
            OutputMessage.printErrorMessage("\nThere is no such command, for reference, enter command \"help\" ");
        }
        return request;
    }
}
