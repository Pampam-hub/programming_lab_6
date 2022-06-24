package ru.itmo.lab.request;

import ru.itmo.lab.entity.Dragon;
import ru.itmo.lab.entity.DragonType;

public class RequestBuilder {
    Request request = new Request();

    public RequestBuilder withName(String commandName) {
        request.commandName = commandName;
        return this;
    }

    public RequestBuilder withLongArgument(Long longArgument) {
        request.longArgument = longArgument;
        return this;
    }

    public RequestBuilder withIntegerArgument(Integer integerArgument) {
        request.integerArgument = integerArgument;
        return this;
    }

    public RequestBuilder withDoubleArgument(Double doubleArgument) {
        request.doubleArgument = doubleArgument;
        return this;
    }

    public RequestBuilder withDragonArgument(Dragon dragonArgument) {
        request.dragonArgument = dragonArgument;
        return this;
    }

    public RequestBuilder withDragonTypeArgument(DragonType dragonTypeArgument) {
        request.dragonTypeArgument = dragonTypeArgument;
        return this;
    }

    public Request build() {
        return request;
    }
}
