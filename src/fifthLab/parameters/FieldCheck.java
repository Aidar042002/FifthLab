package fifthLab.parameters;

import fifthLab.CheckInterface;
import fifthLab.exceptions.EmptyFieldException;
import fifthLab.exceptions.FieldValueTooSmallException;
import fifthLab.exceptions.UncorrectFieldException;

public class FieldCheck {
    public static CheckInterface<Boolean> confirmationValidatorBuilder(String fieldName) {
        return input -> {
            if (input.equals(""))
                return false;
            if (input.equalsIgnoreCase("да"))
                return true;
            throw new UncorrectFieldException("да, ввод верный " + fieldName + " или нет?");
        };
    }

    ///
    public static CheckInterface<Boolean> addressConfirmationValidator   = confirmationValidatorBuilder("адрес" );
    public static CheckInterface<Boolean> locationConfirmationValidator = confirmationValidatorBuilder(
            "местонахождение");


    ///

    public static CheckInterface<String> fullNameValidator = input -> {
        if (input.equals(""))
            throw new EmptyFieldException("полное имя");
        return input;
    };

    public static CheckInterface<String> postalAddressValidator = input -> {
        if (input.equals(""))
            throw new EmptyFieldException("почтовый адрес");
        return input;
    };

    public static CheckInterface<String> locationValidator = input -> {
        if (input.equals(""))
            throw new EmptyFieldException("местонахождение");
        return input;
    };

    public static CheckInterface<String> zipCodeValidator = input -> {
        if (input.equals(""))
            throw new EmptyFieldException("zip code");
        return input;
    };

    public static CheckInterface<Integer> integerCoordinateValidator = input -> {
        if (input.equals(""))
            throw new EmptyFieldException("координата");
        try {
            return Integer.valueOf(input);
        } catch (NumberFormatException exception) {
            throw new UncorrectFieldException("координату");
        }
    };

    public static CheckInterface<Integer> locationZValidator = integerCoordinateValidator;

    public static CheckInterface<UnitOfMeasure> unitOfMeasureValidator =
            new EnumFieldBuilder<>("единица измерения", UnitOfMeasure.class).validator;
    public static CheckInterface<OrganizationType> organizationTypeValidator   =
            new EnumFieldBuilder<>("тип",      OrganizationType.class).validator;

    public static CheckInterface<Boolean> unitOfMeasureConfirmationValidator   = confirmationValidatorBuilder("единица измерения");
    public static CheckInterface<Boolean> organizationTypeConfirmationValidator   = confirmationValidatorBuilder("тип");

    public static CheckInterface<String> productNameValidator = input -> {
        if (input.equals(""))
            throw new EmptyFieldException("имя");
        return input;
    };

    public static CheckInterface<Long> priceValidator = input -> {
        if (input.equals(""))
            throw new EmptyFieldException("стоимость");
        int price;
        try {
            price = Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new UncorrectFieldException("стоимость");
        }
        if (price <= 0)
            throw new FieldValueTooSmallException("0");
        return Long.valueOf(price);
    };

    public static CheckInterface<Long> longCoordinateValidator = input -> {
        if (input.equals(""))
            throw new EmptyFieldException("координата");
        try {
            return Long.valueOf(input);
        } catch (NumberFormatException exception) {
            throw new UncorrectFieldException("координату");
        }
    };

    public static CheckInterface<Long> coordinatesXValidator = longCoordinateValidator;
    public static CheckInterface<Long> coordinatesYValidator = longCoordinateValidator;
    public static CheckInterface<Long> locationXValidator = longCoordinateValidator;
    public static CheckInterface<Long> locationYValidator = longCoordinateValidator;

    public static CheckInterface<Float> manufacturerCostValidator = input -> {
        if (input.equals(""))
            throw new EmptyFieldException("стоимость производства");
        int manufacturerCost;
        try {
            manufacturerCost = (int) Float.parseFloat(input);
        } catch (NumberFormatException exception) {
            throw new UncorrectFieldException("стоимость производства");
        }
        return Float.valueOf(manufacturerCost);
    };

    public static CheckInterface<String> partNumberValidator = input -> {
        if (input.equals(""))
            throw new EmptyFieldException("номер части");
        int partNumber;
        try {
            partNumber = Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new UncorrectFieldException("номер части");
        }
        return String.valueOf(partNumber);
    };
}