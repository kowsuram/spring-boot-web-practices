package io.kowsu.dp.creational;

/*
    @created February/19/2024 - 3:57 PM
    @project spring-boot-web
    @author k.ramanjineyulu
*/
public class AbstractFactoryPattern {
    public static void main(String[] args) {
        IAutomobilesFactory automobilesFactory = new AutomobilesFactory();
        SuzukiAutomobilesFactory suzuki = (SuzukiAutomobilesFactory) automobilesFactory.getFactory("suzuki");
        suzuki.createSuzukiCar().displayCar();
    }
}

class AutomobilesFactory implements IAutomobilesFactory {
    @Override
    public IAutomobilesFactory getFactory(String brand) {
        switch (brand) {
            case "hyundai" -> {
                return new HyundaiAutomobilesFactory();
            }
            case "suzuki" -> {
                return new SuzukiAutomobilesFactory();
            }
        }
        throw new UnsupportedOperationException(String.format("%s not supported", brand));
    }
}


interface IAutomobilesFactory {
    IAutomobilesFactory getFactory(String brand);
}

class SuzukiAutomobilesFactory extends AutomobilesFactory {

    public ICar createSuzukiCar() {
        return new Suzuki();
    }
}

class HyundaiAutomobilesFactory extends AutomobilesFactory {
    public ICar createHyundaiCar() {
        return new Hyundai();
    }
}

interface ICar {
    void displayCar();
}

class Hyundai implements ICar {
    @Override
    public void displayCar() {
        System.out.println("Displaying car name as  Hyundai");
    }
}

class Suzuki implements ICar {
    @Override
    public void displayCar() {
        System.out.println("Displaying car name as  Suzuki");
    }
}