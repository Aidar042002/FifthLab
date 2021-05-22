package fifthLab.parameters;

import fifthLab.InputProvider;

/**
 * Это класс-набор экземпляров InputProvider, которые отвечают за
 * ввод полей при создании новых элементов.
 */

public class FieldInputProviders {
    private static InputProvider<Boolean> confirmationInputProviderBuilder(String fieldName) {
        return new InputProvider<>("Надо вводить " + fieldName + "? (Надо написать да или вообще ничего): ",
                FieldCheck.confirmationValidatorBuilder(fieldName));
    }

    public static InputProvider<UnitOfMeasure> unitOfMeasureInputProvider =
            new EnumFieldBuilder<>("единица измерения", UnitOfMeasure.class).inputProvider;
    public static InputProvider<OrganizationType> organizationTypeInputProvider   =
            new EnumFieldBuilder<>("тип",      OrganizationType.class).inputProvider;

    public static InputProvider<Boolean> unitOfMeasureConfirmationInputProvider   = confirmationInputProviderBuilder(
            "единица измерения");
    public static InputProvider<Boolean> organizationTypeConfirmationInputProvider   = confirmationInputProviderBuilder(
            "тип");


    public static InputProvider<Boolean> addressConfirmationInputProvider  = confirmationInputProviderBuilder(
            "адрес");
    public static InputProvider<Boolean> locationConfirmationInputProvider   = confirmationInputProviderBuilder(
            "местонахождение");
    ///


    public static InputProvider<String> productNameInputProvider = new InputProvider<>(
            "Введите имя : ", FieldCheck.productNameValidator);

    public static InputProvider<Long> coordinatesXInputProvider = new InputProvider<>(
            "Введите координату X: ", FieldCheck.coordinatesXValidator);

    public static InputProvider<Long> coordinatesYInputProvider = new InputProvider<>(
            "Введите координату Y : ", FieldCheck.coordinatesYValidator);

    public static InputProvider<Long> priceInputProvider = new InputProvider<>(
            "Введите стоимость (> 0): ", FieldCheck.priceValidator);

    public static InputProvider<Float> manufacturerCostProvider = new InputProvider<>(
            "Введите стоимость производства: ", FieldCheck.manufacturerCostValidator);

    public static InputProvider<String> partNumberProvider = new InputProvider<>(
            "Введите стоимость производства: ", FieldCheck.partNumberValidator);

    public static InputProvider<String> organizationNameInputProvider = new InputProvider<>(
            "Введите имя производства: ", FieldCheck.productNameValidator);

    public static InputProvider<String> organizationFullNameInputProvider = new InputProvider<>(
            "Введите  полное имя производства: ", FieldCheck.productNameValidator);

    public static InputProvider<String> zipCodeInputProvider = new InputProvider<>(
            "Введите  zip code: ", FieldCheck.zipCodeValidator);

    public static InputProvider<String> postalAddressInputProvider = new InputProvider<>(
            "Введите  почтовый адрес: ", FieldCheck.postalAddressValidator);

    public static InputProvider<String> locationInputProvider = new InputProvider<>(
            "Введите  местонахождение: ", FieldCheck.locationValidator);


    public static InputProvider<Long> locationXInputProvider = new InputProvider<>(
            "Введите координату X location: ", FieldCheck.locationXValidator);

    public static InputProvider<Long> locationYInputProvider = new InputProvider<>(
            "Введите координату Y location: ", FieldCheck.locationYValidator);

    public static InputProvider<Integer> locationZInputProvider = new InputProvider<>(
            "Введите координату Z location: ", FieldCheck.locationZValidator);
}
