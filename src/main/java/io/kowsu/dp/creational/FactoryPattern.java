package io.kowsu.dp.creational;

/*
    @created February/19/2024 - 3:43 PM
    @project spring-boot-web
    @author k.ramanjineyulu
*/
public class FactoryPattern {

    public static void main(String[] args) {
        IMobile lenovo = MobileFactory.createMobile("lenovo");
        lenovo.displayProductName();
    }

}

class MobileFactory {
    public static IMobile createMobile(String name) {
        switch (name) {
            case "lenovo" -> {
                return new Lenovo();
            }
            case "micromax" -> {
                return new MicroMax();
            }
        }
        throw new UnsupportedOperationException(String.format("%s not supported", name));
    }
}

class Lenovo implements IMobile {

    @Override
    public void displayProductName() {
        System.out.println("Displaying product name as lenovo");
    }

    @Override
    public void displayPrice() {
        System.out.println("Displaying product price as 160$");
    }
}


class MicroMax implements IMobile {
    @Override
    public void displayProductName() {
        System.out.println("Displaying product name as MicroMax");
    }

    @Override
    public void displayPrice() {
        System.out.println("Displaying product price as 200$");
    }
}

interface IMobile {
    void displayProductName();
    void displayPrice();
}
