package edu.miu.cs.cs489appsd.lab1a.productmgmtapp;


import edu.miu.cs.cs489appsd.lab1a.productmgmtapp.model.Product;
import java.util.Arrays;
import java.util.Comparator;

public class ProductMgmtApp {
    public static void main(String[] args) {
        Product[] products = {
                new Product(3128874119L, "Banana", "2023-01-24", 124, 0.55),
                new Product(2927458265L, "Apple", "2022-12-09", 18, 1.09),
                new Product(9189927460L, "Carrot", "2023-03-31", 89, 2.99)
        };

        printProducts(products);
    }

    private static void printProducts(Product[] products) {
        Arrays.sort(products, Comparator.comparing(Product::getUnitPrice).reversed());

        System.out.println("\n--- Products in JSON Format ---");
        printJSON(products);

        System.out.println("\n--- Products in XML Format ---");
        printXML(products);

        System.out.println("\n--- Products in CSV Format ---");
        printCSV(products);
    }

    private static void printJSON(Product[] products) {
        System.out.println("[");
        for (int i = 0; i < products.length; i++) {
            Product p = products[i];
            System.out.printf("  { \"productId\": %d, \"name\": \"%s\", \"dateSupplied\": \"%s\", \"quantityInStock\": %d, \"unitPrice\": %.2f }%s\n",
                    p.getProductId(), p.getName(), p.getDateSupplied(), p.getQuantityInStock(), p.getUnitPrice(),
                    (i < products.length - 1) ? "," : "");
        }
        System.out.println("]");
    }

    private static void printXML(Product[] products) {
        System.out.println("<Products>");
        for (Product p : products) {
            System.out.printf("  <Product>\n    <productId>%d</productId>\n    <name>%s</name>\n    <dateSupplied>%s</dateSupplied>\n    <quantityInStock>%d</quantityInStock>\n    <unitPrice>%.2f</unitPrice>\n  </Product>\n",
                    p.getProductId(), p.getName(), p.getDateSupplied(), p.getQuantityInStock(), p.getUnitPrice());
        }
        System.out.println("</Products>");
    }

    private static void printCSV(Product[] products) {
        System.out.println("productId,name,dateSupplied,quantityInStock,unitPrice");
        for (Product p : products) {
            System.out.printf("%d,%s,%s,%d,%.2f\n",
                    p.getProductId(), p.getName(), p.getDateSupplied(), p.getQuantityInStock(), p.getUnitPrice());
        }
    }
}

