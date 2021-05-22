package fifthLab.parameters;
import fifthLab.CSVBuilder;
import fifthLab.exceptions.InputException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Элемент коллекции - Product.
 * Может распарсить строку из CSV для инициализации своих полей.
 * Способен упаковываться в CSV строку.
 * С помощью Comparable осушествляет сортировку по умолчанию,
 * сравниваю стоимость
 *
 */

public class Product implements Comparable<Product>{
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long price; //Значение поля должно быть больше 0
    private String partNumber; //Поле может быть null
    private float manufactureCost;
    private UnitOfMeasure unitOfMeasure; //Поле не может быть null
    private Organization manufacturer; //Поле может быть null

    private String infoString;

    public  Product(){}

    public Product(String line) throws InputException {
        ArrayList<String> tokens = CSVBuilder.parse(line);
        id = Long.parseLong(tokens.get(0));
        name = tokens.get(1);
        coordinates = new Coordinates();
        coordinates.setX(FieldCheck.coordinatesXValidator.get(tokens.get(2)));
        coordinates.setY(FieldCheck.coordinatesYValidator.get(tokens.get(3)));
        creationDate = ZonedDateTime.of(LocalDateTime.parse(tokens.get(4),
                DateTimeFormatter.ofPattern("dd.MM.yyyy.HH.mm")), ZoneId.systemDefault());
        /*creationDate = Date.from(LocalDateTime(tokens.get(4), DateTimeFormatter.ofPattern("dd.MM.yyyy.HH.mm"));*/
        price = FieldCheck.priceValidator.get(tokens.get(5));
        manufactureCost = FieldCheck.manufacturerCostValidator.get(tokens.get(6));
        partNumber = FieldCheck.partNumberValidator.get(tokens.get(7));
        if (!tokens.get(8).equals(""))
            unitOfMeasure = FieldCheck.unitOfMeasureValidator.get(tokens.get(8));
        if (!tokens.get(9).equals("")) {
            manufacturer = new Organization();
            manufacturer.setId(Long.parseLong(tokens.get(9)));
            manufacturer.setName(FieldCheck.productNameValidator.get(tokens.get(10)));
            manufacturer.setFullName(FieldCheck.fullNameValidator.get(tokens.get(11)));
            if (!tokens.get(12).equals(""))
                manufacturer.setType(FieldCheck.organizationTypeValidator.get(tokens.get(12)));
            if (!tokens.get(13).equals("")) {
                Address address = new Address();
                address.setZipCode(tokens.get(13));
                if (!tokens.get(14).equals("")) {
                    Location location = new Location();
                    location.setX(FieldCheck.locationXValidator.get(tokens.get(14)));
                    location.setY(FieldCheck.locationYValidator.get(tokens.get(15)));
                    location.setZ(FieldCheck.locationZValidator.get(tokens.get(16)));
                }
            }
        }
        generateInfoString();
    }

    public int compareTo(Product anotherProduct){
        return (int) (price - anotherProduct.getPrice());
    }

    public String toString() {
        CSVBuilder csvStringBuilder = new CSVBuilder();
        csvStringBuilder.append(String.valueOf(id));
        csvStringBuilder.append(name);
        csvStringBuilder.append(String.valueOf(coordinates.getX()));
        csvStringBuilder.append(String.valueOf(coordinates.getY()));
        csvStringBuilder.append(DateTimeFormatter.ofPattern("dd.MM.yyyy.HH.mm").format(creationDate));
        csvStringBuilder.append(String.valueOf(price));
        csvStringBuilder.append(String.valueOf(manufactureCost));
        csvStringBuilder.append(String.valueOf(partNumber));
        csvStringBuilder.append(unitOfMeasure.name());
        if (manufacturer == null) {
            for (int i = 0; i < 8; i++)
                csvStringBuilder.append("");
        } else {
            csvStringBuilder.append(String.valueOf(manufacturer.getId()));
            csvStringBuilder.append(manufacturer.getName());
            csvStringBuilder.append(manufacturer.getFullName());
            csvStringBuilder.append(manufacturer.getType().name());
            if (manufacturer.getPostalAddress() == null) {
                for (int i = 0; i < 4; i++)
                    csvStringBuilder.append("");
            } else {
                csvStringBuilder.append(manufacturer.getPostalAddress().getZipCode());
                if(manufacturer.getPostalAddress().getLocation() == null){
                    csvStringBuilder.append(String.valueOf(manufacturer.getPostalAddress().getLocation().getX()));
                    csvStringBuilder.append(String.valueOf(manufacturer.getPostalAddress().getLocation().getY()));
                    csvStringBuilder.append(String.valueOf(manufacturer.getPostalAddress().getLocation().getZ()));
                }
            }
        }
        return csvStringBuilder.line;
    }
    public void generateInfoString() {
        infoString = "\nID: " + id +
                "\nИмя : " + name +
                "\nКоординаты:" +
                "\n\tX: " + coordinates.getX() +
                "\n\tY: " + coordinates.getY() +
                "\nВремя создания: " + DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy").format(creationDate) + //?????
                "\nЦена: " + price +
                "\nСтоимость изготовления: " + manufactureCost +
                "\nНомер части: " + partNumber +
                "\nЕдиница измерения: " + unitOfMeasure +
                "\nПроизводитель: " + (manufacturer == null ? "null" :
                "\n\tId: " + manufacturer.getId() +
                "\n\tИмя: " + manufacturer.getName() +
                "\n\tПолное имя: " + manufacturer.getFullName() +
                "\n\tТип: " + manufacturer.getType() +
                        "\n\tПочтовый адрес: " + (manufacturer.getPostalAddress() == null ? "null" :
                        "\n\t\tИндекс: " + manufacturer.getPostalAddress().getZipCode() +
                                "\n\tМесто расположения: " + (manufacturer.getPostalAddress().getLocation() == null ?
                                "null" :
                                "\n\t\tX: " + manufacturer.getPostalAddress().getLocation().getX() +
                                "\n\t\tY: " + manufacturer.getPostalAddress().getLocation().getY() +
                                "\n\t\tZ: " + manufacturer.getPostalAddress().getLocation().getZ()))) + "\n";
    }

    public String getInfoString() {
        return infoString;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public float getManufactureCost() {
        return manufactureCost;
    }

    public void setManufactureCost(float manufactureCost) {
        this.manufactureCost = manufactureCost;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Organization getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Organization manufacturer) {
        this.manufacturer = manufacturer;
    }
}
