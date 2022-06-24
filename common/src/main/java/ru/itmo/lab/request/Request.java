package ru.itmo.lab.request;

import ru.itmo.lab.entity.Dragon;
import ru.itmo.lab.entity.DragonType;

import java.io.Serializable;
import java.time.LocalTime;

public class Request implements Serializable {
    protected String commandName;
    private String clientInfo;
    private LocalTime currentTime;
    protected Long longArgument;
    protected Integer integerArgument;
    protected Double doubleArgument;
    protected DragonType dragonTypeArgument;
    protected Dragon dragonArgument;

    public String getCommandName() {
        return commandName;
    }

    public Long getLongArgument() {
        return longArgument;
    }

    public Integer getIntegerArgument() {
        return integerArgument;
    }

    public Double getDoubleArgument() {
        return doubleArgument;
    }

    public Dragon getDragonArgument() {
        return dragonArgument;
    }

    public String getClientInfo() {
        return clientInfo;
    }

    public LocalTime getCurrentTime() {
        return currentTime;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }

    public void setCurrentTime(LocalTime currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public String toString() {
        return "Request{" +
                "commandName='" + commandName + '\'' +
                ", clientInfo='" + clientInfo + '\'' +
                ", currentTime=" + currentTime +
                ", longArgument=" + longArgument +
                ", integerArgument=" + integerArgument +
                ", doubleArgument=" + doubleArgument +
                ", dragonArgument=" + dragonArgument +
                '}';
    }
}
