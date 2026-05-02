package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void testConstructorAndGetters() {
        Student student = new Student("S101", "Alice Wonderland", 20, 1.65, 55.0, "A00123");

        assertEquals("S101", student.getId());
        assertEquals("Alice Wonderland", student.getName());
        assertEquals(20, student.getAge());
        assertEquals(1.65, student.getHeight(), 0.001);
        assertEquals(55.0, student.getWeight(), 0.001);
        assertEquals("A00123", student.getCarne());
    }

    @Test
    void testSetCarne() {
        Student student = new Student("S102", "Bob The Builder", 22, 1.80, 75.0, "B00456");

        student.setCarne("B00789");
        assertEquals("B00789", student.getCarne());
    }

    @Test
    void testGetRoleDescription() {
        Student student = new Student("S103", "Charlie Chaplin", 21, 1.70, 60.0, "C00987");
        assertEquals("Rol: Estudiante, carne=C00987", student.getRoleDescription());
    }

    @Test
    void testToString() {
        Student student = new Student("S104", "Diana Prince", 23, 1.78, 68.0, "D00654");
        String expectedToString = "Student{" +
                "id='S104', name='Diana Prince', age=23, height=1.78, weight=68.0, carne='D00654'}";
        assertEquals(expectedToString, student.toString());
    }

    @Test
    void testInheritedSetters() {
        Student student = new Student("S105", "Eve Adams", 19, 1.60, 50.0, "E00321");

        student.setId("S106");
        assertEquals("S106", student.getId());

        student.setName("Eve Smith");
        assertEquals("Eve Smith", student.getName());

        student.setAge(20);
        assertEquals(20, student.getAge());

        student.setHeight(1.62);
        assertEquals(1.62, student.getHeight(), 0.001);

        student.setWeight(52.0);
        assertEquals(52.0, student.getWeight(), 0.001);
    }
}
