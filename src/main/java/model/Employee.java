package model;

import java.time.LocalDate;

public class Employee extends Person {
    private String jobPosition;
    private LocalDate hireDate;

    public Employee(String id, String name, String jobPosition, LocalDate hireDate) {
        super(id, name, 0, 0, 0);
        this.jobPosition = jobPosition;
        this.hireDate = hireDate;
    }

    public String getJobPosition() { return jobPosition; }
    public void setJobPosition(String jobPosition) { this.jobPosition = jobPosition; }

    public LocalDate getHireDate() { return hireDate; }
    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }

    @Override
    public String getRoleDescription() { return jobPosition; }

    @Override
    public String toString() {
        return getId() + " | " + getName() + " | " + jobPosition + " | " + hireDate;
    }
}