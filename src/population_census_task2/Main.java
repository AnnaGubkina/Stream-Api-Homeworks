package population_census_task2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        //определяем несовершеннолетних
        long underage =
                persons.stream()
                        .filter(n -> n.getAge() < 18)
                        .count();
        System.out.println(underage);

        System.out.println();

        //определяем список фамилий призывников (т.е. мужчин от 18 и до 27 лет).
        List<String> armyAge = persons.stream()
                .filter(n -> n.getAge() >= 18)
                .filter(n -> n.getAge() <= 27)
                .filter(n -> n.getSex() == Sex.MAN)
                .map(p -> p.getFamily())
                .collect(Collectors.toList());
        System.out.println(armyAge);

//        for (String s : armyAge) {
//            System.out.println(s);
//        }

        //получаем отсортированный по фамилии список потенциально работоспособных людей с высшим образованием
        List<Person> workableWoman = persons.stream()
                .filter(n -> n.getEducation() == Education.HIGHER)
                .filter(n -> n.getAge() >= 18)
                .filter(t -> t.getAge() <= 60)
                .filter(t -> t.getSex() == Sex.WOMAN)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(workableWoman);

        List<Person> workableMan = persons.stream()
                .filter(n -> n.getEducation() == Education.HIGHER)
                .filter(n -> n.getAge() >= 18)
                .filter(t -> t.getAge() <= 65)
                .filter(t -> t.getSex() == Sex.MAN)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(workableMan);

    }
}
