package pl.edu.pjwstk.lab6;

/**
 * Created by tp on 20.03.17.
 */
public interface Cart {
    void addProduct(Product p);
    double getSummaryPrice();
    void delProduct(Product p);

}
