package ru.itmo.lab.request;

import ru.itmo.lab.CommandToSend;
import ru.itmo.lab.ConsoleDragonFactory;
import ru.itmo.lab.DragonValidator;

public class WithDragonCommandRequest implements CommandRequest {
    public WithDragonCommandRequest() {
    }

    @Override
    public Request createRequest(CommandToSend commandToSend) {
        DragonValidator.validateNumberOfArgs(commandToSend.getCommandArgs(), 0);
        ConsoleDragonFactory consoleDragonFactory = new ConsoleDragonFactory();
        consoleDragonFactory.generateDragonData();
        return new RequestBuilder().withName(commandToSend.getCommandName())
                .withDragonArgument(consoleDragonFactory.getDragon()).build();
    }
}
