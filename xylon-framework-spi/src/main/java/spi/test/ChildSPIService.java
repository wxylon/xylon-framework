package spi.test;

public class ChildSPIService implements SPIService {

    @Override
    public void test() {
        System.out.println("test");
    }
}
