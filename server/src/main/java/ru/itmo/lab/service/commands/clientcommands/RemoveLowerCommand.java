package ru.itmo.lab.service.clientcommands;

import ru.itmo.lab.repository.commandresult.CommandResult;
import ru.itmo.lab.repository.commandresult.CommandResultBuilder;
import ru.itmo.lab.repository.Storage;
import ru.itmo.lab.repository.commandresult.CommandStatus;
import ru.itmo.lab.request.Request;

public class RemoveLowerCommand extends Command {
    public RemoveLowerCommand() {
        super("remove_lower", "remove elements lower than " +
                "specified from collection", "arguments aren't needed");
    }

    @Override
    public CommandResult execute(Storage storage, Request request)  {
        try {
            storage.removeLower(request.getDragonArgument());
            return new CommandResultBuilder()
                    .setMessage("The removal has been completed")
                    .setStatus(CommandStatus.SUCCESSFUL).build();
        } catch (IllegalArgumentException e) {
            return new CommandResultBuilder()
                    .setMessage(e.getMessage())
                    .setStatus(CommandStatus.UNSUCCESSFUL).build();
        }

    }
}
