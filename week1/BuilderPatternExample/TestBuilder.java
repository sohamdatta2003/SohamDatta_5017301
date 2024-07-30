public class TestBuilderjava {
    public static void main(String[] args) {
        // Creating different configurations of Computer
        Computer gamingComputer = new Computer.Builder()
                .setCPU("Intel Core i9")
                .setRAM("32GB")
                .setStorage("1TB SSD")
                .setGPU("NVIDIA RTX 3080")
                .setMotherboard("ASUS ROG Strix")
                .setPowerSupply("750W")
                .build();

        Computer officeComputer = new Computer.Builder()
                .setCPU("Intel Core i5")
                .setRAM("16GB")
                .setStorage("512GB SSD")
                .setMotherboard("Gigabyte B450M")
                .setPowerSupply("500W")
                .build();

        // Printing the configurations
        System.out.println("Gaming Computer Configuration:");
        System.out.println("CPU: " + gamingComputer.getCPU());
        System.out.println("RAM: " + gamingComputer.getRAM());
        System.out.println("Storage: " + gamingComputer.getStorage());
        System.out.println("GPU: " + gamingComputer.getGPU());
        System.out.println("Motherboard: " + gamingComputer.getMotherboard());
        System.out.println("Power Supply: " + gamingComputer.getPowerSupply());

        System.out.println("\nOffice Computer Configuration:");
        System.out.println("CPU: " + officeComputer.getCPU());
        System.out.println("RAM: " + officeComputer.getRAM());
        System.out.println("Storage: " + officeComputer.getStorage());
        System.out.println("Motherboard: " + officeComputer.getMotherboard());
        System.out.println("Power Supply: " + officeComputer.getPowerSupply());
    }
}
