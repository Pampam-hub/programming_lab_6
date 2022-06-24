package ru.itmo.lab.request;

import ru.itmo.lab.CommandToSend;
import ru.itmo.lab.DragonValidator;

public class WithoutArgsCommandRequest implements CommandRequest {
    public WithoutArgsCommandRequest() {
    }

    @Override
    public Request createRequest(CommandToSend commandToSend) {
        DragonValidator.validateNumberOfArgs(commandToSend.getCommandArgs(), 0);
        return new RequestBuilder().withName(commandToSend.getCommandName()).build();
    }
}
