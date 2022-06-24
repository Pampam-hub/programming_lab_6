package ru.itmo.lab.request;

import ru.itmo.lab.CommandToSend;
import ru.itmo.lab.ConsoleDragonFactory;
import ru.itmo.lab.DragonValidator;

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
        ConsoleDragonFactory consoleDragonFactory = new ConsoleDragonFactory();
        consoleDragonFactory.generateDragonData();
        return new RequestBuilder().withName(commandToSend.getCommandName()).withIntegerArgument(id)
                .withDragonArgument(consoleDragonFactory.getDragon()).build();
    }
}
