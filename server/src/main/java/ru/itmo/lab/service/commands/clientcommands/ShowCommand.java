package ru.itmo.lab.service.clientcommands;

import ru.itmo.lab.repository.commandresult.CommandResult;
import ru.itmo.lab.repository.commandresult.CommandResultBuilder;
import ru.itmo.lab.entity.Dragon;
import ru.itmo.lab.repository.Storage;
import ru.itmo.lab.repository.commandresult.CommandStatus;
import ru.itmo.lab.request.Request;
import ru.itmo.lab.service.handlers.DragonValidator;


public class ShowCommand extends Command {
    public ShowCommand() {
        super("show","you can see elements of the collection",
                "arguments aren't needed");
    }

    @Override
    public CommandResult execute(Storage storage, Request request)  {
        try {
            CommandResult commandResult = new CommandResultBuilder()
                    .setMessage("Here your collection:")
                    .setStatus(CommandStatus.SUCCESSFUL)
                    .setListOfDragons(storage.readAll()).build();

            return commandResult;
        } catch (IllegalArgumentException e) {
            return new CommandResultBuilder()
                    .setMessage(e.getMessage())
                    .setStatus(CommandStatus.UNSUCCESSFUL).build();
        }

    }
}
