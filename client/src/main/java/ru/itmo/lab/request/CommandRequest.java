package ru.itmo.lab.request;


import ru.itmo.lab.service.CommandToSend;

public interface CommandRequest {
    Request createRequest(CommandToSend commandToSend);
}
