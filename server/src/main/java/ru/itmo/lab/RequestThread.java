package ru.itmo.lab;


import ru.itmo.lab.repository.Storage;
import ru.itmo.lab.repository.commandresult.CommandResult;
import ru.itmo.lab.request.Request;
import ru.itmo.lab.service.OutputMessage;
import ru.itmo.lab.service.handlers.CommandExecutor;
import ru.itmo.lab.service.handlers.SocketWorker;

import javax.xml.transform.Result;
import java.io.IOException;

public class RequestThread extends Thread {
    private final SocketWorker socketWorker;
    private final Storage storage;
    private final CommandExecutor commandExecutor;

    public RequestThread(SocketWorker socketWorker, CommandExecutor commandExecutor, Storage storage) {
        this.socketWorker = socketWorker;
        this.storage = storage;
        this.commandExecutor = commandExecutor;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Request acceptedRequest = socketWorker.receiveRequest();
                CommandResult result = commandExecutor.executeClientCommand(storage, acceptedRequest);
                socketWorker.sendResult(result);
            } catch (IOException e) {
                OutputMessage.printErrorMessage("During processing a request from a client");
            } catch (ClassNotFoundException e) {
                OutputMessage.printErrorMessage("Invalid request from the client");
            }
        }
    }
}

