package ru.itmo.lab;

import ru.itmo.lab.commandresult.CommandResult;
import ru.itmo.lab.service.handlers.ClientSocketWorker;

import java.io.IOException;

public final class ResultReceiver {
    private ResultReceiver() {
    }

    public static void receiveResult (ClientSocketWorker clientSocketWorker) {
        try {
            CommandResult commandResult = null;
            for (int i = 0; i < 50 && commandResult == null; i++) {
                OutputMessage.printSuccessfulMessage("Waiting for response from server...");
                commandResult = clientSocketWorker.receiveResult();
            }
            if (commandResult == null) {
                OutputMessage.printErrorMessage("I'm tired!");
                return;
            }
            commandResult.showCommandResult();
        } catch (IOException e) {
            System.out.println(("error while "));
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println(("Пришел некорректный ответ от сервера"));
        }
    }
}
