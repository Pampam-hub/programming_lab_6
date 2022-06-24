package ru.itmo.lab.service.handlers;

public class CommandRequestReceiver {
    public static void receiveCommandRequest(socketWorker socketWorker) {
        ResultReceiver.receiveResult(socketWorker);
    }
}
