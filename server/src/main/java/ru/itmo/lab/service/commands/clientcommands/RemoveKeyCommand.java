package ru.itmo.lab.service.commands.clientcommands;

import ru.itmo.lab.repository.commandresult.CommandResult;
import ru.itmo.lab.repository.commandresult.CommandResultBuilder;
import ru.itmo.lab.repository.Storage;
import ru.itmo.lab.repository.exceptions.EntityNotFoundException;
import ru.itmo.lab.repository.commandresult.CommandStatus;
import ru.itmo.lab.request.Request;


public class RemoveKeyCommand extends ClientCommand {
    public RemoveKeyCommand() {
        super("remove_key","remove element from " +
                "collection by key", "id");
    }

    @Override
    public CommandResult execute(Storage storage, Request request) {
        try {
            storage.remove(request.getIntegerArgument());
            return new CommandResultBuilder()
                    .setMessage("The removal has been completed")
                    .setStatus(CommandStatus.SUCCESSFUL).build();
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            return new CommandResultBuilder()
                    .setMessage(e.getMessage())
                    .setStatus(CommandStatus.UNSUCCESSFUL).build();
        }
    }
}
