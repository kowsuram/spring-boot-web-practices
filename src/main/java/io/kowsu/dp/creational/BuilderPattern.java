package io.kowsu.dp.creational;

/*
    @created February/20/2024 - 5:40 PM
    @project spring-boot-web
    @author k.ramanjineyulu
*/
public class BuilderPattern {

    public static void main(String[] args) {
        Employee employee = new Employee.Builder()
                .address("SKP").name("RAM").email("ram@gmail.com").build();
        System.out.printf("Employee name %s, address %s, email %s",
                employee.name(), employee.address(), employee.email());
    }
}

class Employee {
    private String name;
    private String email;
    private String address;


    public String name() {
        return name;
    }

    public String email() {
        return email;
    }

    public String address() {
        return address;
    }

    static class Builder {
        private String name;
        private String email;
        private String address;

        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }

    public Employee(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.address = builder.address;
    }
}
