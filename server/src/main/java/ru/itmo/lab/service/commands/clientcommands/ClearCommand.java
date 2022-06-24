package ru.itmo.lab.service.clientcommands;

import ru.itmo.lab.repository.commandresult.CommandResult;
import ru.itmo.lab.repository.commandresult.CommandResultBuilder;
import ru.itmo.lab.repository.commandresult.CommandStatus;
import ru.itmo.lab.repository.Storage;
import ru.itmo.lab.request.Request;


public class ClearCommand extends Command {
    public ClearCommand() {
        super("clear","clear all collection",
                "arguments aren't needed");
    }

    @Override
    public CommandResult execute(Storage storage, Request request) {
        try {
            storage.removeAll();
            return new CommandResultBuilder()
                    .setMessage("Collection has been cleared")
                    .setStatus(CommandStatus.SUCCESSFUL).build();
        } catch (IllegalArgumentException e) {
            return new CommandResultBuilder()
                    .setMessage(e.getMessage())
                    .setStatus(CommandStatus.UNSUCCESSFUL).build();
        }
    }
}
