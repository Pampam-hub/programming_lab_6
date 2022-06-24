package ru.itmo.lab.request;

import ru.itmo.lab.entity.DragonType;
import ru.itmo.lab.service.handlers.DragonValidator;
import ru.itmo.lab.service.CommandToSend;

public class WithTypeCommandRequest implements CommandRequest {
    public WithTypeCommandRequest() {
    }

    @Override
    public Request createRequest(CommandToSend commandToSend) {
        DragonValidator.validateNumberOfArgs(commandToSend.getCommandArgs(), 1);

        DragonValidator<DragonType> dragonValidator =
                new DragonValidator<>(commandToSend.getCommandArgs()[0], null);
        dragonValidator.validateNull(false);
        dragonValidator.validateFunction(DragonType::valueOf, "value of dragon type " +
                "must be from list " + DragonType.show() + " letter case must be the same");
        DragonType type = dragonValidator.getValue();

        return new RequestBuilder()
                .withName(commandToSend.getCommandName())
                .withDragonTypeArgument(type).build();
    }

}
