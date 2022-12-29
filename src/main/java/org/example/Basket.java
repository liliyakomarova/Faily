package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Basket {
    private String[] product = {"Молоко ", "Хлеб ", "Рис "};
    private int[] prices = {50, 14, 80};
    private int[] numbers = {1, 2, 3};

    private int[] sumProducts = new int[3];
    private int[] numberOfProduct = new int[3];
    private int priceOfGoods = 0;

    public Basket() {
        this.prices = prices;
        this.product = product;
        this.numbers = numbers;
    }

    public void addToCart(int productNum, int amount) {
        numberOfProduct[productNum] = productNum;
        priceOfGoods += (amount * prices[productNum]);
        sumProducts[productNum] += amount;
    }

    public void printCart() {
        System.out.println("Ваша корзина: ");
        for (int i = 0; i < numberOfProduct.length; i++) {
            if (sumProducts[i] != 0) {
            }
        }
        for (int i = 0; i < numberOfProduct.length; i++) {
            if (sumProducts[i] != 0) {
                System.out.println(numbers[i] + " " + product[i] + " " + sumProducts[i] + " штук, " + "цена " +
                        prices[i] + " руб/штука, " + "Всего " + (prices[i] * sumProducts[i]) + " руб");
            }
        }
        System.out.println("Итого " + priceOfGoods + " руб");
    }

    public void saveTxt(File textfile) throws IOException {
        try (PrintWriter out = new PrintWriter(textfile);) {
            for (int i = 0; i < numberOfProduct.length; i++) {
                if (sumProducts[i] != 0) {
                    String s = numbers[i] + " " + product[i] + " " + sumProducts[i] + " штук, " + "цена " +
                            prices[i] + " руб/штука, " + "Всего " + (prices[i] * sumProducts[i]) + " руб";
                    out.write(s + "\n");
                }
            }
            out.write("Итого: ");
            String result = priceOfGoods + " руб";
            out.write(result + "\n");
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public static Basket loadFromTxtFile(File textfile) throws IOException {
        Basket basket = new Basket();
        try (InputStreamReader in = new InputStreamReader(new FileInputStream(textfile), StandardCharsets.UTF_8)) {
            System.out.println("Считываем:");
            while (in.ready()) {
                char read = (char) in.read();
                System.out.print(read);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return basket;
    }
}