package ru.itmo.lab.service.commands.servercommands;

import ru.itmo.lab.repository.commandresult.CommandResult;
import ru.itmo.lab.repository.commandresult.CommandResultBuilder;
import ru.itmo.lab.entity.Dragon;
import ru.itmo.lab.repository.Storage;
import ru.itmo.lab.repository.commandresult.CommandStatus;
import ru.itmo.lab.service.commands.clientcommands.ClientCommand;
import ru.itmo.lab.service.handlers.XMLWriter;

import java.io.IOException;


public class SaveCommand extends ClientCommand {
    public SaveCommand() {
        super("save", "save collection into file",
                "arguments aren't needed");
    }

    @Override
    public CommandResult execute(Storage storage, String[] args, Dragon dragon)  {
        try {
            XMLWriter.writeToXML(storage.readAll());
            return new CommandResultBuilder()
                    .setMessage("Collection has been saved")
                    .setStatus(CommandStatus.SUCCESSFUL).build();
        } catch (IllegalArgumentException | IOException e) {
            return new CommandResultBuilder()
                    .setMessage(e.getMessage())
                    .setStatus(CommandStatus.UNSUCCESSFUL).build();
        }

    }
}
