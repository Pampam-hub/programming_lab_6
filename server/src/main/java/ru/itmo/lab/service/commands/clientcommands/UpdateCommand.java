package ru.itmo.lab.service.commands.clientcommands;

import ru.itmo.lab.repository.commandresult.CommandResult;
import ru.itmo.lab.repository.commandresult.CommandResultBuilder;
import ru.itmo.lab.repository.Storage;
import ru.itmo.lab.repository.exceptions.EntityNotFoundException;
import ru.itmo.lab.repository.commandresult.CommandStatus;
import ru.itmo.lab.request.Request;

public class UpdateCommand extends ClientCommand {
    public UpdateCommand() {
        super("update","update element from collection",
                "id");
    }

    @Override
    public CommandResult execute(Storage storage, Request request)  {
        try {
            storage.update(request.getIntegerArgument(), request.getDragonArgument());
            return new CommandResultBuilder()
                    .setMessage("Collection has been update")
                    .setStatus(CommandStatus.SUCCESSFUL).build();
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            return new CommandResultBuilder()
                    .setMessage(e.getMessage())
                    .setStatus(CommandStatus.UNSUCCESSFUL).build();
        }

    }
}
