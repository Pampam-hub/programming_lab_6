package ru.itmo.lab.service.handlers;

import ru.itmo.lab.repository.Factory;
import ru.itmo.lab.entity.Dragon;
import ru.itmo.lab.repository.factories.FileDragonFactory;
import ru.itmo.lab.service.OutputMessage;

import java.util.ArrayList;
import java.util.List;

public class XMLFileChecker {
    static Integer fileIsCorrect(Dragon[] dragons) {
        List<Integer> ids = new ArrayList<>();
        Integer maxId = -1;

        for (Dragon dragon : dragons) {
            try {
                if (ids.contains(dragon.getId())) {
                    throw new IllegalArgumentException("the input collection has elements with equal id");
                } else {
                    ids.add(dragon.getId());
                    maxId = Math.max(maxId, dragon.getId());
                }

                Factory factory = new FileDragonFactory(dragon);
                factory.generateDragonData();
            } catch (IllegalArgumentException e) {
                OutputMessage.printErrorMessage(e.getMessage());
                System.exit(0);
            }
        }

        return ++maxId;

    }
}
