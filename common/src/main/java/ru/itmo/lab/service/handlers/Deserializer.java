package ru.itmo.lab.handlers;

import ru.itmo.lab.commandresult.CommandResult;
import ru.itmo.lab.request.Request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;

public final class Deserializer {

    private Deserializer() {
    }

    public static Request deSerializeRequest(byte[] acceptedBuf)
            throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(acceptedBuf);
             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
            return (Request) objectInputStream.readObject();
        }
    }

    public static CommandResult deSerializeResult(ByteBuffer byteBuffer)
            throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
            return (CommandResult) objectInputStream.readObject();
        }
    }
}
