public class TestObserverPattern {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();

        Observer mobileApp = new MobileApp("Mobile App 1");
        Observer webApp = new WebApp("Web App 1");

        stockMarket.registerObserver(mobileApp);
        stockMarket.registerObserver(webApp);

        stockMarket.setStockPrice(100.00);
        System.out.println();

        stockMarket.setStockPrice(105.50);
        System.out.println();

        stockMarket.deregisterObserver(mobileApp);
        stockMarket.setStockPrice(110.75);
    }
}
