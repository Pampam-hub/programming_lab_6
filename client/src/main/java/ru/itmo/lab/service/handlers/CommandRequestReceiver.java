package ru.itmo.lab.handlers;

import ru.itmo.lab.ReceiveResult;

public class CommandRequestReceiver {
    public static void receiveCommandRequest(ClientSocketWorker clientSocketWorker) {
        ReceiveResult.receiveResult(clientSocketWorker);
    }
}
