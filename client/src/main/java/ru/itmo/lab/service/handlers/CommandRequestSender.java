package ru.itmo.lab.handlers;

import ru.itmo.lab.request.Request;

import java.io.IOException;

public class CommandRequestSender {
    public static void sendCommandRequest(Request request, ClientSocketWorker clientSocketWorker)
            throws IOException {
        request.setClientInfo(clientSocketWorker.getAddress() + clientSocketWorker.getPort());
        clientSocketWorker.sendRequest(request);
    }
}
