package ru.itmo.lab.service.handlers;

import ru.itmo.lab.service.OutputMessage;
import ru.itmo.lab.request.Request;
import ru.itmo.lab.service.CommandToSend;

import java.io.IOException;

public class CommandRequestWorker {
    private final static CommandRequestCreator requestCreator = new CommandRequestCreator();

    public static void superpupermethod(String line, SocketWorker socketWorker) throws IOException {
        CommandToSend commandToSend = CommandToSendCreator.createCommandToSend(line);
        try {
            if ("execute_script".equals(commandToSend.getCommandName())) {
                ScriptWorker.startWorkWithScript(commandToSend, socketWorker);
            } else {
                Request request = requestCreator.createCommandRequest(line, commandToSend);

                if (request != null) {
                    CommandRequestSender.sendCommandRequest(request, socketWorker);

                    CommandRequestReceiver.receiveCommandRequest(socketWorker);
                }
            }
        } catch (IllegalArgumentException e) {
            OutputMessage.printErrorMessage(e.getMessage());
        }
    }
}
