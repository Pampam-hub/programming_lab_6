package ru.itmo.lab.handlers;

import ru.itmo.lab.request.Request;

import java.io.IOException;

public class CommandRequestWorker {
    public static void superpupermethod(String line, ClientSocketWorker clientSocketWorker) throws IOException {
        CommandRequestCreator requestCreator = new CommandRequestCreator();
        Request request = requestCreator.createCommandRequest(line);

        if(request != null) {
            CommandRequestSender.sendCommandRequest(request, clientSocketWorker);

            CommandRequestReceiver.receiveCommandRequest(clientSocketWorker);
        }
    }
}
