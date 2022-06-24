package ru.itmo.lab.service.clientcommands;

import ru.itmo.lab.repository.Storage;
import ru.itmo.lab.repository.commandresult.CommandResult;
import ru.itmo.lab.repository.exceptions.EntityNotFoundException;
import ru.itmo.lab.request.Request;

import java.util.Arrays;
import java.util.List;

public abstract class Command {
    private String name;
    private String description;
    private List<String> args;


    // commandEntry(всякая шняга типа "кто дал аргументы")

    public Command(String name, String description, String... args){
        this.name = name;
        this.description = description;
        this.args = Arrays.asList(args);
    }

    public abstract CommandResult execute(Storage storage, Request request);

    public String getName() {
        return name;
    }

    public List<String> getArgs() {
        return args;
    }

    @Override
    public String toString() {
        return name + " - " + description
                + (args.get(0).equals("arguments aren't needed") ? "" : "\n args: " + args.get(0));
    }
}
