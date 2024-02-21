package io.kowsu.streams;

import io.kowsu.utils.StreamUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
    @created February/14/2024 - 1:39 PM
    @project spring-boot-web
    @author k.ramanjineyulu
*/
public class StreamsTest {

    record Person(String name, String professsion, double salary) {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    List<Person> personList;

    @BeforeEach
    public void setup() {
        personList = Arrays.asList(
                new Person("RAM", "JD", 10.0d),
                new Person("RAM", "MNG", 90.0d),
                new Person("RAGHU", "JD", 11.0d),
                new Person("MADHU", "PO", 10.0d),
                new Person("DHANESH", "QA", 9.0d)
        );

    }

    @Test
    public void groupingTest() {

        Map<String, List<Person>> collect = personList.stream().collect(Collectors.groupingBy(Person::professsion));
        assertTrue(collect.containsKey("JD") && collect.get("JD").size() == 2);
    }

    @Test
    public void reduceTest() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Integer reduce = numbers.stream().reduce(0, (a, b) -> a + b);
        assertEquals(21, reduce);

        //alternative way
        double totalSal = personList.stream().mapToDouble(a -> a.salary).sum();
        assertEquals(40, totalSal);

        //reduce way
        Double totalSal_1 = personList.stream().map(a -> a.salary).reduce(0d, (a, b) -> a + b);
        assertEquals(40, totalSal_1);

        Optional<Person> personOptional = personList.stream().reduce((p1, p2) -> p1.salary() > p2.salary ? p1 : p2);
        assertTrue(personOptional.isPresent());
        assertEquals("RAGHU", personOptional.get().name());

    }

    @Test
    public void collectTest() {
        //toListCollection
        LinkedList<Person> personsList =
                personList.stream().collect(Collectors.toCollection(LinkedList::new));
        assertEquals(5, personsList.size());

        //toSetCollection
        Set<Person> personSet = personList.stream().collect(Collectors.toSet());
        assertEquals(4, personSet.size());

        //toUnmodifiableCollection
        List<Person> unModifiablePersons = personList.stream().collect(Collectors.toUnmodifiableList());
        assertEquals(5, unModifiablePersons.size());
        assertThrows(UnsupportedOperationException.class, () -> unModifiablePersons.remove(0), "Operation not supported");

        //toMap
        Map<String, String> nameVsProfession = personList.stream().collect(Collectors.toMap(t -> t.name(), v -> v.professsion(), (m, n) -> n));
        assertEquals("MNG", nameVsProfession.get("RAM"));
        assertEquals("QA", nameVsProfession.get("DHANESH"));

        //listToMap
        Map<String, String> reverseNames =
                personList.stream().map(Person::name).collect(Collectors.toMap(Function.identity(), StreamUtils::reverseString, (m, n) -> n));
        assertEquals("MAR", reverseNames.get("RAM"));

        //toUnmodifiableMap
        Map<String, String> toUnmodifiableMap =
                personList.stream().collect(Collectors.toUnmodifiableMap(Person::name, Person::professsion, (a, b) -> b));
        assertThrows(UnsupportedOperationException.class, () -> toUnmodifiableMap.remove("RAM"), "Operation not permitted");

        //collectingAndThen
        Double avgSalary = personList.stream().map(a -> a.salary()).collect(Collectors.averagingDouble(b -> b));
        assertEquals(26d, avgSalary);

        //summarizingDouble
        DoubleSummaryStatistics salStats = personList.stream().map(a -> a.salary()).collect(Collectors.summarizingDouble(a -> a));
        assertEquals(90.0d, salStats.getMax());
        assertEquals(9.0d, salStats.getMin());
        assertEquals(26d, salStats.getAverage());
        assertEquals(5, salStats.getCount());

    }

    @Test
    public void retryTest() throws Exception {
        int maxRetries = 3;
        int i = 0;
        while (maxRetries > 0) {
            Thread.sleep(1000);
            maxRetries--;
            i++;
        }
        assertEquals(3, i);
    }


    //name vs salary
    @Test
    public void highSalaryPersonList() {
        Optional<Person> collect = personList.stream().collect(Collectors.maxBy((a, b) -> a.salary() > b.salary ? 1 : -1));
        assertTrue(collect.isPresent());
        Person person = collect.get();
        assertEquals("RAM", person.name());

        Map<String, Double> collect1 =
                personList.stream().collect(Collectors.maxBy((a, b) -> a.salary() > b.salary ? 1 : -1))
                        .stream().collect(Collectors.toMap(p -> p.name(), q -> q.salary()));
        assertTrue(collect1.values().contains(90.0d));
        assertTrue(collect1.keySet().contains("RAM"));
    }

    @Test
    public void partitionByTest() {
        Map<Boolean, List<Person>> paritionMap = personList.stream()
                .collect(Collectors.partitioningBy(p -> p.professsion().equals("JD")));
        System.out.println(paritionMap);
        assertTrue(paritionMap.containsKey(true));
    }


}
