package edu.hw4;

import edu.hw4.rules.Rule;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jetbrains.annotations.Range;

public class Methods {
    private Methods() {
    }

    /**
     * Отсортировать животных по росту от самого маленького к самому большому -> List&lt;Animal&gt;
     *
     * @param animals лист для сортировки
     * @return новый отсортированный лист
     */
    public static List<Animal> task1(List<Animal> animals) {
        return animals.stream().sorted(Comparator.comparingInt(Animal::height)).toList();
    }

    /**
     * Отсортировать животных по весу от самого тяжелого к самому легкому, выбрать k первых -> List&lt;Animal&gt;
     *
     * @param animals лист для сортировки
     * @param count   сколько взять животных
     * @return новый отсортированный лист заданного количества (или меньше)
     */
    public static List<Animal> task2(List<Animal> animals, @Range(from = 0, to = Integer.MAX_VALUE) int count) {
        if (count == 0) {
            return new ArrayList<>();
        }

        return animals.stream()
                .sorted((animal1, animal2) -> animal2.weight() - animal1.weight())
                .limit(count)
                .toList();
    }

    /**
     * Сколько животных каждого вида -> Map&lt;Animal.Type, Long&gt;
     *
     * @param animals лист животных
     * @return частотный словарь по типам
     */
    public static Map<Animal.Type, Long> task3(List<Animal> animals) {
        return animals.stream().collect(Collectors.groupingBy(Animal::type, java.util.stream.Collectors.counting()));
    }

    /**
     * У какого животного самое длинное имя -> Animal
     *
     * @param animals лист животных
     * @return животное с самым длинным именем
     */
    public static Animal task4(List<Animal> animals) {
        return animals.stream()
                .max(Comparator.comparingInt((animal) -> animal.name().length()))
                .orElse(null);
    }

    /**
     * Каких животных больше: самцов или самок -> Sex
     *
     * @param animals лист животных
     * @return пол
     */
    public static Animal.Sex task5(List<Animal> animals) {
        return animals.stream()
                .collect(Collectors.groupingBy(
                        Animal::sex,
                        Collectors.counting()
                ))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    /**
     * Самое тяжелое животное каждого вида -> Map&lt;Animal.Type, Animal&gt;
     *
     * @param animals лист животных
     * @return самое тяжелое животное каждого вида
     */
    public static Map<Animal.Type, Animal> task6(List<Animal> animals) {
        return animals.stream().collect(Collectors.toMap(
                Animal::type,
                Function.identity(),
                BinaryOperator.maxBy(Comparator.comparingInt(Animal::weight))
        ));
    }

    /**
     * K-е самое старое животное -> Animal
     *
     * @param animals лист животных
     * @param index   k-ое животное (начиная с 0)
     * @return k-ое самое старое животное
     */
    public static Animal task7(List<Animal> animals, @Range(from = 0, to = Integer.MAX_VALUE) int index) {
        return animals
                .stream()
                .sorted((animal1, animal2) -> animal2.age() - animal1.age())
                .toList()
                .get(Math.min(index, animals.size()));
    }

    /**
     * Самое тяжелое животное среди животных ниже k см -> Optional&lt;Animal&gt;
     *
     * @param animals   лист животных
     * @param maxHeight верхняя граница роста
     * @return самое тяжелое животное
     */
    public static Optional<Animal> task8(List<Animal> animals, @Range(from = 0, to = Integer.MAX_VALUE) int maxHeight) {
        return animals
                .stream()
                .filter(animal -> animal.height() < maxHeight)
                .max(Comparator.comparingInt(Animal::weight));
    }

    /**
     * Сколько в сумме лап у животных в списке -> Integer
     *
     * @param animals лист животных
     * @return сумма лап
     */
    public static int task9(List<Animal> animals) {
        return animals.stream().mapToInt(Animal::paws).sum();
    }

    /**
     * Список животных, возраст у которых не совпадает с количеством лап -> List&lt;Animal&gt;
     *
     * @param animals лист животных
     * @return список животных, возраст которых не совпадает с количеством лап
     */
    public static List<Animal> task10(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.age() != animal.paws()).toList();
    }

    /**
     * Список животных, которые могут укусить (bites == null или true)
     * и рост которых превышает 100 см -> List&lt;Animal&gt;
     *
     * @param animals лист животных
     * @return список животных, удовлетворяющих условию
     */
    @SuppressWarnings("MagicNumber")
    public static List<Animal> task11(List<Animal> animals) {
        return animals
                .stream()
                .filter(animal -> animal.bites() && animal.height() > 100) //boolean не может быть null
                .toList();
    }

    /**
     * Сколько в списке животных, вес которых превышает рост -> Integer
     *
     * @param animals список животных
     * @return количество животных, удовлетворяющих условию
     */
    public static long task12(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.weight() > animal.height()).count();
    }

    /**
     * Список животных, имена которых состоят из более чем двух слов -> List&lt;Animal&gt;
     *
     * @param animals лист животных
     * @return животные, чьи имена состоят из более чем 2 слов
     */
    public static List<Animal> task13(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.name().split(" ").length > 2).toList();
    }

    /**
     * Есть ли в списке собака ростом более k см -> Boolean
     *
     * @param animals   лист животных
     * @param minHeight минимальная высота собаки
     * @return наличие собаки
     */
    public static boolean task14(List<Animal> animals, @Range(from = 0, to = Integer.MAX_VALUE) int minHeight) {
        return animals
                .stream()
                .anyMatch(animal -> animal.type().equals(Animal.Type.DOG) && animal.height() > minHeight);
    }

    /**
     * Найти суммарный вес животных каждого вида, которым от k до l лет -> Map&lt;Animal.Type, Integer&gt;
     *
     * @param animals список животных
     * @param ageFrom минимальный возраст (включительно)
     * @param ageTo   максимальный возраст (включительно)
     * @return суммарный вес каждого вида заданного возраста
     */
    public static Map<Animal.Type, Integer> task15(
            List<Animal> animals,
            @Range(from = 0, to = Integer.MAX_VALUE) int ageFrom,
            @Range(from = 0, to = Integer.MAX_VALUE) int ageTo
    ) {
        return animals
                .stream()
                .filter(animal -> animal.age() >= ageFrom && animal.age() <= ageTo)
                .collect(Collectors.groupingBy(
                        Animal::type,
                        Collectors.summingInt(Animal::weight)
                ));
    }

    /**
     * Список животных, отсортированный по виду, затем по полу, затем по имени -> List&lt;Animal&gt;
     *
     * @param animals лист животных
     * @return отсортированный лист
     */
    public static List<Animal> task16(List<Animal> animals) {
        return animals.stream().sorted(
                Comparator
                        .comparing(Animal::type)
                        .thenComparing(Animal::sex)
                        .thenComparing(Animal::name)
        ).toList();
    }

    /**
     * Правда ли, что пауки кусаются чаще, чем собаки -> Boolean (если данных для ответа недостаточно, вернуть false)
     *
     * @param animals лист животных
     * @return флаг, что пауки кусаются чаще
     */
    public static boolean task17(List<Animal> animals) {
        // Получаем частоту укусов собак и пауков
        var map = animals
                .stream()
                .filter(animal -> animal.type().equals(Animal.Type.SPIDER) || animal.type().equals(Animal.Type.DOG))
                .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(animal -> animal.bites() ? 1 : 0)));

        // если нет информации о ком-то из них
        if (map.size() != 2) {
            return false;
        }

        // Проверка на то, что пауки кусаются чаще собак
        return map.get(Animal.Type.SPIDER).compareTo(map.get(Animal.Type.DOG)) > 0;
    }

    /**
     * Найти самую тяжелую рыбку в 2-х или более списках -> Animal
     *
     * @param animals список животных
     * @return самая тяжелая рыба
     */
    public static Animal task18(List<Animal> animals) {
        throw new IllegalArgumentException("Недостаточное количество списков");
    }

    /**
     * Найти самую тяжелую рыбку в 2-х или более списках -> Animal
     *
     * @param animals переданные списки животных
     * @return самая тяжелая рыба
     */
    @SafeVarargs
    public static Animal task18(List<Animal>... animals) {
        return Stream.of(animals)
                .flatMap(Collection::stream)
                .toList()
                .stream()
                .filter(animal -> animal.type().equals(Animal.Type.FISH))
                .max(Comparator.comparingInt(Animal::weight))
                .orElse(null);
    }

    /**
     * Животные, в записях о которых есть ошибки:
     * вернуть имя и список ошибок -> Map&lt;String, Set&lt;ValidationError&gt;&gt;.
     * Класс ValidationError и набор потенциальных проверок нужно придумать самостоятельно.
     *
     * @param animal животное для валидации
     * @param rules  правила валидации
     * @return результат валидации
     */
    public static Map<String, Set<ValidationError>> task19(Animal animal, Map<String, List<Rule>> rules) {
        Iterator<Map.Entry<String, List<Rule>>> iterator = rules.entrySet().iterator();
        Map<String, Set<ValidationError>> errorsMap = new HashMap<>();

        while (iterator.hasNext()) {
            var entry = iterator.next();
            String variable = entry.getKey(); // валидируемое поле
            List<Rule> varRules = entry.getValue(); // правила валидации

            try {
                var method = Animal.class.getMethod(variable); // получение метода
                var value = method.invoke(animal); // вызов метода для получения значения переменной
                Set<ValidationError> errors = new HashSet<>(); // набор ошибок для текущей переменной

                // валидация правилами
                for (Rule rule : varRules) {
                    try {
                        errors.add(rule.check(value));
                    } catch (Exception ignored) { // игнорируем ошибки (например, вызов length() для null-строки)
                    }
                }

                // оставляем только те правила, которые вернули ошибку
                var filteredErrors = errors.stream().filter(Objects::nonNull).collect(Collectors.toSet());

                //
                if (!filteredErrors.isEmpty()) {
                    errorsMap.put(variable, filteredErrors);
                }

            } catch (Exception ignored) { // игнорируем поля, которых не существует
            }
        }

        return errorsMap;
    }

    /**
     * Сделать результат предыдущего задания более читабельным:
     * вернуть имя и названия полей с ошибками, объединенные в строку -> Map&lt;String, String&gt;
     *
     * @param animal животное для валидации
     * @param rules  правила валидации
     * @return результат валидации
     */
    public static Map<String, String> task20(Animal animal, Map<String, List<Rule>> rules) {
        return task19(animal, rules).entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .map(ValidationError::message)
                                .collect(Collectors.joining("\n"))
                ));
    }
}
