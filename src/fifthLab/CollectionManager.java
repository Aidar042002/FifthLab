package fifthLab;

import fifthLab.exceptions.FileDoesNotExistException;
import fifthLab.exceptions.InputException;
import fifthLab.parameters.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class CollectionManager {
    public final LinkedHashSet<Product> elements = new LinkedHashSet<>();
    public final HashMap<Long, Product> productsByID = new HashMap<>();
    //
    public final HashMap<Long, Organization> organizationByID = new HashMap<>();
    //
    private final String filePath;//?
    private LocalDateTime initializationDate;//?
    private long nextID;

    public CollectionManager(String filePath) {
        this.filePath = filePath;
        boolean createNew = false;
        try {
            String[] lines = FileControl.readFromFile(filePath).split("\\r?\\n");
            String[] meta = lines[0].split(",");
            initializationDate = LocalDateTime.parse(meta[0], DateTimeFormatter.ofPattern("dd.MM.yyyy.HH.mm")); //?
            nextID = Long.parseLong(meta[1]);
            for (int i = 1; i < lines.length; i++) {
                Product product;
                try {
                    product = new Product(lines[i]);
                } catch (InputException exception) {
                    System.out.println("При загрузке работника возникла ошибка: " + exception.getMessage());
                    continue;
                }
                elements.add(product);
                productsByID.put(product.getId(), product);
            }
        } catch (FileDoesNotExistException exception) {
            System.out.println("Внимание! Файла не было, поэтому вот новая коллекция.");
            createNew = true;
        } catch (InputException exception) {
            System.out.println(exception.getMessage());
            createNew = true;
        } catch (RuntimeException exception) {
            exception.printStackTrace();
            System.out.println("\nКороче файл попорчен, поэтому вот новая коллекция.");
            createNew = true;
        }
        if (createNew) {
            initializationDate = LocalDateTime.now();
            clear();
        }
    }

    public String getInfo() {
        return "Тип коллекции: LinkedHashSet" +
                "\nДата инициализации: " + DateTimeFormatter.ofPattern("hh:mm dd.MM.yyyy").format(initializationDate) +
                "\nКоличество элементов: " + elements.size() +
                "\nЗагружена из файла: " + filePath +
                "\nСледующий элемент будет иметь ID " + nextID;
    }

    public long ElementAdding() {
        while (productsByID.containsKey(nextID))
            nextID++;
        Product product = new Product();
        product.setId(nextID);
        product.setCreationDate(ZonedDateTime.now());
        elements.add(product);
        productsByID.put(nextID, product);
        initiateElementUpdatingProcedure(nextID);
        return nextID++;
    }

    ///
    public long ElementAddingOrganization() {
        while (organizationByID.containsKey(nextID))
            nextID++;
        Organization organization = new Organization();
        organization.setId(nextID);
        //organization.setCreationDate(ZonedDateTime.now());
        //elements.add(organization);
        organizationByID.put(nextID, organization);
        initiateElementUpdatingProcedure(nextID);
        return nextID++;
    }
    ///

    public boolean initiateElementUpdatingProcedure(long ID) {
        Product product = productsByID.get(ID);
        if (product == null)
            return false;
        product.setName(FieldInputProviders.productNameInputProvider.provide());
        Coordinates coordinates = new Coordinates();
        coordinates.setX(FieldInputProviders.coordinatesXInputProvider.provide());
        coordinates.setY(FieldInputProviders.coordinatesYInputProvider.provide());
        product.setCoordinates(coordinates);
        product.setPrice(FieldInputProviders.priceInputProvider.provide());
        product.setManufactureCost(FieldInputProviders.manufacturerCostProvider.provide());
        product.setPartNumber(FieldInputProviders.partNumberProvider.provide());
        if (FieldInputProviders.unitOfMeasureConfirmationInputProvider.provide())
            product.setUnitOfMeasure(FieldInputProviders.unitOfMeasureInputProvider.provide());
        if (FieldInputProviders.organizationTypeConfirmationInputProvider.provide()) {
            Organization organization = organizationByID.get(ID);
            organization.setName(FieldInputProviders.organizationNameInputProvider.provide());
            organization.setFullName(FieldInputProviders.organizationFullNameInputProvider.provide());
            if(FieldInputProviders.organizationTypeConfirmationInputProvider.provide())
                organization.setType(FieldInputProviders.organizationTypeInputProvider.provide());
            if (FieldInputProviders.addressConfirmationInputProvider.provide()) {
                Address address = new Address();
                address.setZipCode(FieldInputProviders.zipCodeInputProvider.provide());
                if(FieldInputProviders.locationConfirmationInputProvider.provide()){
                    Location location = new Location();
                    location.setX(FieldInputProviders.locationXInputProvider.provide());
                    location.setY(FieldInputProviders.locationYInputProvider.provide());
                    location.setZ(FieldInputProviders.locationZInputProvider.provide());
                    address.setLocation(location);
                }
            }
            product.setManufacturer(organization);
        }
        product.generateInfoString();
        return true;
    }

    public boolean removeElement(long ID) {
        Product product = productsByID.get(ID);
        if (product == null)
            return false;
        elements.remove(product);
        productsByID.remove(ID);
        if (ID == nextID-1)
            nextID--; // позволяет повторно использовать освободившиеся ID
        return true;
    }

    public void clear() {
        elements.clear();
        productsByID.clear();
        nextID = 1;
    }

    public void saveToFile() throws InputException {
        StringBuilder csv = new StringBuilder();
        csv.append(DateTimeFormatter.ofPattern("dd.MM.yyyy.HH.mm").format(initializationDate));
        csv.append(",").append(nextID).append(",,,,,,,,,,,,\n"); // Запятых ровно столько, чтобы первая строчка имела с остальными одинаковое количество столбцов
        for (Product product : elements)
            csv.append(product.toString()).append("\n");
        FileControl.saveToFile(filePath, csv.toString().trim());
    }
}
