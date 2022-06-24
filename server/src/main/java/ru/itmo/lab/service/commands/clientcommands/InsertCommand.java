package ru.itmo.lab.service.commands.clientcommands;

import ru.itmo.lab.repository.commandresult.CommandResult;
import ru.itmo.lab.repository.commandresult.CommandResultBuilder;
import ru.itmo.lab.repository.Storage;
import ru.itmo.lab.repository.exceptions.EntityAlreadyExistsException;
import ru.itmo.lab.repository.commandresult.CommandStatus;
import ru.itmo.lab.request.Request;


public class InsertCommand extends ClientCommand {
    public InsertCommand() {
        super("insert","add a new element " +
                        "to the collection", "id");
    }

    @Override
    public CommandResult execute(Storage storage, Request request) {
        try {
            storage.addElement(request.getIntegerArgument(), request.getDragonArgument());
            return new CommandResultBuilder()
                    .setMessage("Element has been added")
                    .setStatus(CommandStatus.SUCCESSFUL).build();
        } catch (EntityAlreadyExistsException e) {
            return new CommandResultBuilder()
                    .setMessage(e.getMessage())
                    .setStatus(CommandStatus.UNSUCCESSFUL).build();
        }
    }
}
