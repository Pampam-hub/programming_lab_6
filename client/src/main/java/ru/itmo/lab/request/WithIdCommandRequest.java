package ru.itmo.lab.request;

import ru.itmo.lab.CommandToSend;
import ru.itmo.lab.DragonValidator;

public class WithIdCommandRequest implements CommandRequest {
    public WithIdCommandRequest() {
    }

    @Override
    public Request createRequest(CommandToSend commandToSend) {
        DragonValidator.validateNumberOfArgs(commandToSend.getCommandArgs(), 1);
        DragonValidator<Integer> dragonValidator = new DragonValidator<>(commandToSend.getCommandArgs()[0],null);
        dragonValidator.validateNull(false);
        dragonValidator.validateFunction(Integer::parseInt, "value of id must be an integer");
        Integer id = dragonValidator.getValue();
        return new RequestBuilder().withName(commandToSend.getCommandName()).withIntegerArgument(id).build();
    }
}
