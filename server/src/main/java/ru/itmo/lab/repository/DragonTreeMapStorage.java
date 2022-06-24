package ru.itmo.lab.repository;

import ru.itmo.lab.entity.Dragon;
import ru.itmo.lab.repository.exceptions.EntityAlreadyExistsException;
import ru.itmo.lab.repository.exceptions.EntityNotFoundException;
import ru.itmo.lab.service.commands.Command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class DragonTreeMapStorage implements Storage<Dragon, Integer> {
    /**
     * Счетчик id элементов, служит для обеспечения уникальности поля id у каждого элемента
     */
    private static Integer idCounter = 1;
    private final Map<Integer, Dragon> dragonTreeMap;
    private final Deque<String> history;
    private LocalDate dateOfInitialization;
    private HashSet<String> previousFiles = new HashSet<>();

    public DragonTreeMapStorage() {
        dateOfInitialization = LocalDate.now();
        dragonTreeMap = new TreeMap<>();
        history = new ArrayDeque<>();
    }

    @Override
    public void setIdCounter(Integer id) {
        idCounter = id;
    }

    @Override
    public void addElement(Integer id, Dragon entity)
            throws EntityAlreadyExistsException {
        if (dragonTreeMap.containsKey(id)) {
            throw new EntityAlreadyExistsException(Dragon.class, id);
        }
        entity.setId(id);
        entity.setCreationDate(LocalDateTime.now());
        dragonTreeMap.put(id, entity);
        if(id > idCounter) {
            idCounter = ++id;
        }
    }

    @Override
    public void save(Dragon entity) {
        if(entity.getId() == null) {
            entity.setId(idCounter++);
            entity.setCreationDate(LocalDateTime.now());
        }
        dragonTreeMap.put(entity.getId(), entity);
    }

    @Override
    public Dragon read(Integer id)
            throws EntityNotFoundException {
        Dragon dragon = dragonTreeMap.get(id);
        if (dragon == null) {
            throw new EntityNotFoundException(Dragon.class, id);
        }
        return dragon;
    }

    @Override
    public void update(Integer id, Dragon entity)
            throws EntityNotFoundException {
        if (!dragonTreeMap.containsKey(id)) {
            throw new EntityNotFoundException(Dragon.class, id);
        }
        entity.setId(idCounter++);
        entity.setCreationDate(LocalDateTime.now());
        dragonTreeMap.replace(id, entity);
    }

    @Override
    public void remove(Integer id)
            throws EntityNotFoundException {
        if (!dragonTreeMap.containsKey(id)) {
            throw new EntityNotFoundException(Dragon.class, id);
        }
        dragonTreeMap.remove(id);
    }

    @Override
    public void removeLower(Dragon entity) {
        entity.setId(idCounter++);
        entity.setCreationDate(LocalDateTime.now());
        for (Integer i = 0; i <= entity.getId(); i++)
            dragonTreeMap.remove(i);
    }

    @Override
    public void removeLowerKey(Integer id) {
        for (Integer i = 0; i < id; i++)
            dragonTreeMap.remove(i);
    }

    @Override
    public void removeAll() {
        dragonTreeMap.clear();
    }

    @Override
    public List<Dragon> readAll() {
        return new ArrayList<>(dragonTreeMap.values());
    }

    @Override
    public Dragon min(Comparator<Dragon> com) {
        List<Dragon> listHelper
                = new ArrayList<>(dragonTreeMap.values());
        listHelper.sort(com);
        return listHelper.get(listHelper.size() - 1);
    }

    @Override
    public List<Dragon> sortDragons(Comparator<Dragon> com) {
        List<Dragon> listHelper
                = new ArrayList<>(dragonTreeMap.values());
        listHelper.sort(com);
        return listHelper;
    }

    @Override
    public void fillHistory(Command command) {
        int numElements = 12;
        if (history.size() == numElements) {
            history.removeFirst();
        }
        history.offerLast(command.getName());
    }

    @Override
    public void addPreviousFiles(String file) {
        this.previousFiles.add(file) ;
    }

    @Override
    public void deleteFromPreviousFiles(String file) {
        this.previousFiles.remove(file);
    }

    @Override
    public Deque<String> getHistory() {
        return history;
    }

    @Override
    public HashSet<String> getPreviousFiles() {
        return previousFiles;
    }

    @Override
    public String getInfo() {
        final int sizeOfDragon = 25;
        final int sizeOfDragons = 16;
        return "Collection type: " + dragonTreeMap.getClass().toString().substring(sizeOfDragons) +
                ", elements type: " + Dragon.class.toString().substring(sizeOfDragon) + ", data of " +
                "initialisation: " + dateOfInitialization + ", number of elements: " +
                dragonTreeMap.size();
    }

}
