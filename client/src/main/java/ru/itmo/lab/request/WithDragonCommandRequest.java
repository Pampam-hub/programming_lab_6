package ru.itmo.lab.request;

import ru.itmo.lab.repository.ConsoleWorker;
import ru.itmo.lab.service.factories.ScriptDragonFactory;
import ru.itmo.lab.service.CommandToSend;
import ru.itmo.lab.service.factories.ConsoleDragonFactory;
import ru.itmo.lab.service.handlers.DragonValidator;

public class WithDragonCommandRequest implements CommandRequest {
    public WithDragonCommandRequest() {
    }

    @Override
    public Request createRequest(CommandToSend commandToSend) {
        DragonValidator.validateNumberOfArgs(commandToSend.getCommandArgs(), 0);

        if(ConsoleWorker.getConsoleWorker().isExecutedScript()) {
            ScriptDragonFactory scriptDragonFactory =
                    new ScriptDragonFactory(ConsoleWorker.getConsoleWorker().getScanner());
            scriptDragonFactory.generateDragonData();
            return new RequestBuilder()
                    .withName(commandToSend.getCommandName())
                    .withDragonArgument(scriptDragonFactory.getDragon())
                    .build();
        } else {
            ConsoleDragonFactory consoleDragonFactory = new ConsoleDragonFactory();
            consoleDragonFactory.generateDragonData();
            return new RequestBuilder()
                    .withName(commandToSend.getCommandName())
                    .withDragonArgument(consoleDragonFactory.getDragon())
                    .build();
        }
    }
}