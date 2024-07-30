public class TestProxyPattern {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("photo1.jpg");
        Image image2 = new ProxyImage("photo2.jpg");

        // The image is loaded from the remote server the first time it's displayed
        image1.display();
        System.out.println("");

        // The image is displayed directly from the cache the second time
        image1.display();
        System.out.println("");

        // The image is loaded from the remote server the first time it's displayed
        image2.display();
        System.out.println("");

        // The image is displayed directly from the cache the second time
        image2.display();
    }
}
