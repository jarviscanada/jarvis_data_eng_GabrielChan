package ca.jrvs.practice.dataStructure;

public class Employee {
    private int id;
    private String name;
    private int age;
    private long salary;

    public Employee(int id, String name, int age, long salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Employee && this.id == ((Employee) obj).id && this.name.equals(((Employee) obj).name)
                && this.age == ((Employee) obj).age && this.salary == ((Employee) obj).salary);
    }

    @Override
    public int hashCode() {
        return (int) (Math.pow(id, 2) * name.hashCode() * Math.pow(age, 3) * Math.floor(Math.pow(salary,4)));
    }
}
