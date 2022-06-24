package ru.itmo.lab.service.commands.clientcommands;

import ru.itmo.lab.repository.commandresult.CommandResult;
import ru.itmo.lab.repository.commandresult.CommandResultBuilder;
import ru.itmo.lab.entity.Dragon;
import ru.itmo.lab.repository.Storage;
import ru.itmo.lab.repository.comparators.AgeDragonComparator;
import ru.itmo.lab.repository.commandresult.CommandStatus;
import ru.itmo.lab.request.Request;

import java.util.ArrayList;
import java.util.List;

public class PrintFieldDescendingAgeCommand extends ClientCommand {
    public PrintFieldDescendingAgeCommand() {
        super("print_field_descending_age","you can see values of field " +
                "age in descending order","arguments aren't needed");
    }

    @Override
    public CommandResult execute(Storage storage, Request request)  {
        try {

            List<Dragon> dragons = storage.sortDragons(new AgeDragonComparator());
            List<Integer> ages = new ArrayList<>();
            for(Dragon d: dragons) {
                ages.add(d.getAge());
            }
            CommandResult commandResult = new CommandResultBuilder()
                    .setMessage("Fields age in descending: ")
                    .setStatus(CommandStatus.SUCCESSFUL)
                    .setIntegerList(ages).build();

            return commandResult;
        } catch (IllegalArgumentException e) {
            return new CommandResultBuilder()
                    .setMessage(e.getMessage())
                    .setStatus(CommandStatus.UNSUCCESSFUL).build();
        }
    }
}
