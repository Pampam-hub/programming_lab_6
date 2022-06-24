package ru.itmo.lab.service.clientcommands;

import ru.itmo.lab.repository.commandresult.CommandResult;
import ru.itmo.lab.repository.commandresult.CommandResultBuilder;
import ru.itmo.lab.repository.Storage;
import ru.itmo.lab.repository.commandresult.CommandStatus;
import ru.itmo.lab.request.Request;


public class InfoCommand extends Command {
    public InfoCommand() {
        super("info","you can see info about the collection",
                "arguments aren't needed");
    }

    @Override
    public CommandResult execute(Storage storage, Request request) {
        try {
            return new CommandResultBuilder()
                    .setMessage("Here you're collection: ")
                    .setStatus(CommandStatus.SUCCESSFUL)
                    .setData(storage.getInfo()).build();
        } catch (IllegalArgumentException e) {
        return new CommandResultBuilder()
                .setMessage(e.getMessage())
                .setStatus(CommandStatus.UNSUCCESSFUL).build();
    }
    }
}
