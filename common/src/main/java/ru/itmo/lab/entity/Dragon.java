package ru.itmo.lab.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.time.LocalDateTime;

@XStreamAlias("dragon")
public class Dragon {

    @XStreamAlias("id")
    private Integer id;

    @XStreamAlias("name")
    private String name;

    @XStreamAlias("coordinates")
    private Coordinates coordinates;
    // Поле не может быть null, значение генерируется автоматически
    @XStreamAlias("creationDate")
    private java.time.LocalDateTime creationDate;
    // Значение поля > 0, поле не может быть null
    @XStreamAlias("age")
    private Integer age;
    // Значение поля > 0
    @XStreamAlias("wingspan")
    private int wingspan;
    // Поле не может быть null
    @XStreamAlias("dragonType")
    private DragonType type;
    // Поле не может быть null
    @XStreamAlias("dragonCharacter")
    private DragonCharacter dragonCharacter;
    @XStreamAlias("dragonHead")
    private DragonHead dragonHead;

    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * Метод, возвращающий значение поля id у текущего элемента коллекции
     *
     * @return id дракона
     */
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setCreationDate() {
        creationDate = LocalDateTime.now();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public int getWingspan() {
        return wingspan;
    }

    public void setWingspan(int wingspan) {
        this.wingspan = wingspan;
    }

    public DragonType getType() {
        return type;
    }

    public void setType(DragonType type) {
        this.type = type;
    }

    public DragonCharacter getDragonCharacter() {
        return dragonCharacter;
    }

    public void setCharacter(DragonCharacter dragonCharacter) {
        this.dragonCharacter = dragonCharacter;
    }

    public DragonHead getDragonHead() {
        return dragonHead;
    }

    public void setDragonHead(DragonHead dragonHead) {
        this.dragonHead = dragonHead;
    }

    @Override
    public String toString() {
        return "Dragon{ " +
                "id: " + id +
                ", name: '" + name + '\'' +
                ", " + coordinates +
                ", creationDate: " + creationDate +
                ", age: " + age +
                ", wingspan: " + wingspan +
                ", type: " + type +
                ", dragonCharacter: " + dragonCharacter +
                ", " + dragonHead + " }";
    }
}
