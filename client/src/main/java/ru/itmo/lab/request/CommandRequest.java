package ru.itmo.lab.request;


import ru.itmo.lab.CommandToSend;

public interface CommandRequest {
    Request createRequest(CommandToSend commandToSend);
}
