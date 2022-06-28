package ru.itmo.lab.request;

import ru.itmo.lab.repository.ConsoleWorker;
import ru.itmo.lab.service.factories.ScriptDragonFactory;
import ru.itmo.lab.service.CommandToSend;
import ru.itmo.lab.service.factories.ConsoleDragonFactory;
import ru.itmo.lab.service.handlers.DragonValidator;

public class WithIdAndDragonCommandRequest implements CommandRequest {
    public WithIdAndDragonCommandRequest() {
    }

    @Override
    public Request createRequest(CommandToSend commandToSend) {
        DragonValidator.validateNumberOfArgs(commandToSend.getCommandArgs(), 1);

        DragonValidator<Integer> dragonValidator = new DragonValidator<>(commandToSend.getCommandArgs()[0],null);
        dragonValidator.validateNull(false);
        dragonValidator.validateFunction(Integer::parseInt, "value of id must be an integer");
        Integer id = dragonValidator.getValue();

        if(ConsoleWorker.getConsoleWorker().isExecutedScript()) {
            ScriptDragonFactory scriptDragonFactory =
                    new ScriptDragonFactory(ConsoleWorker.getConsoleWorker().getScanner());
            scriptDragonFactory.generateDragonData();
            return new RequestBuilder()
                    .withName(commandToSend.getCommandName())
                    .withIntegerArgument(id)
                    .withDragonArgument(scriptDragonFactory.getDragon())
                    .build();
        } else {
            ConsoleDragonFactory consoleDragonFactory = new ConsoleDragonFactory();
            consoleDragonFactory.generateDragonData();
            return new RequestBuilder()
                    .withName(commandToSend.getCommandName())
                    .withIntegerArgument(id)
                    .withDragonArgument(consoleDragonFactory.getDragon())
                    .build();
        }
    }
}