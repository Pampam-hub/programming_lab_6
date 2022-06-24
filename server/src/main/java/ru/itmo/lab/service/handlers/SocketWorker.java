package ru.itmo.lab.service.handlers;

import ru.itmo.lab.repository.commandresult.CommandResult;
import ru.itmo.lab.service.OutputMessage;
import ru.itmo.lab.request.Request;

import java.io.IOException;
import java.net.*;

public class ServerSocketWorker {
    private static final int DEFAULT_PORT = 1425;
    private DatagramSocket datagramSocket;
    private int port = DEFAULT_PORT;
    private SocketAddress socketAddress;
    private InetAddress host;

    private String address = "localhost";

    public ServerSocketWorker() {
        try {
            host = InetAddress.getByName(address);
            datagramSocket = new DatagramSocket(port, host);
        } catch (UnknownHostException | SocketException e) {
            OutputMessage.printErrorMessage(e.getMessage());
        }
    }

    public Request receiveRequest() throws IOException, ClassNotFoundException {
        int receivedSize = datagramSocket.getReceiveBufferSize();
        byte[] byteArray = new byte[receivedSize];
        DatagramPacket dpToReceive = new DatagramPacket(byteArray, byteArray.length);
        datagramSocket.receive(dpToReceive);
        socketAddress = dpToReceive.getSocketAddress();
        byteArray = dpToReceive.getData();
        return Deserializer.deSerializeRequest(byteArray);
    }

    public void sendResult(CommandResult result) throws IOException {
        byte[] bufferToSend = Serializer.serializeResult(result);
        DatagramPacket datagramPacket = new DatagramPacket(bufferToSend, bufferToSend.length, socketAddress);
        datagramSocket.send(datagramPacket);
    }

    public int getPort() {
        return port;
    }

    public String getAddress() {
        return address;
    }
}
