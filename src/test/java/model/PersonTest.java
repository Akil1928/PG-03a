package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    // A concrete subclass to allow testing of the abstract Person class
    private static class ConcretePerson extends Person {
        public ConcretePerson(String id, String name, int age, double height, double weight) {
            super(id, name, age, height, weight);
        }

        @Override
        public String getRoleDescription() {
            return "Concrete Person Role";
        }
    }

    @Test
    void testConstructorAndGetters() {
        ConcretePerson person = new ConcretePerson("123", "John Doe", 30, 1.75, 70.0);

        assertEquals("123", person.getId());
        assertEquals("John Doe", person.getName());
        assertEquals(30, person.getAge());
        assertEquals(1.75, person.getHeight(), 0.001); // Delta for double comparison
        assertEquals(70.0, person.getWeight(), 0.001); // Delta for double comparison
    }

    @Test
    void testSetters() {
        ConcretePerson person = new ConcretePerson("123", "John Doe", 30, 1.75, 70.0);

        person.setId("456");
        assertEquals("456", person.getId());

        person.setName("Jane Smith");
        assertEquals("Jane Smith", person.getName());

        person.setAge(25);
        assertEquals(25, person.getAge());

        person.setHeight(1.80);
        assertEquals(1.80, person.getHeight(), 0.001);

        person.setWeight(75.5);
        assertEquals(75.5, person.getWeight(), 0.001);
    }

    @Test
    void testGetRoleDescription() {
        ConcretePerson person = new ConcretePerson("123", "John Doe", 30, 1.75, 70.0);
        assertEquals("Concrete Person Role", person.getRoleDescription());
    }

    @Test
    void testToString() {
        ConcretePerson person = new ConcretePerson("123", "John Doe", 30, 1.75, 70.0);
        String expectedToString = "Person{" +
                "id='123', name='John Doe', age=30, height=1.75, weight=70.0}";
        assertEquals(expectedToString, person.toString());
    }
}
