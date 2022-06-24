package ru.itmo.lab.service.commands.clientcommands;

import ru.itmo.lab.repository.commandresult.CommandResult;
import ru.itmo.lab.repository.commandresult.CommandResultBuilder;
import ru.itmo.lab.repository.Storage;
import ru.itmo.lab.repository.commandresult.CommandStatus;
import ru.itmo.lab.request.Request;

public class RemoveLowerKeyCommand extends ClientCommand {
    public RemoveLowerKeyCommand() {
        super("remove_lower_key", "remove elements that have key " +
                "lower than specified from collection", "id");
    }

    @Override
    public CommandResult execute(Storage storage, Request request)  {
        try {
            storage.removeLowerKey(request.getIntegerArgument());
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
