package org.example;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String[] product = {"Молоко ", "Хлеб ", "Рис "};
        int[] prices = {50, 14, 80};
        int[] numbers = {1, 2, 3};

        Basket basket = new Basket();

        File file = new File("basket.txt");
        if (file.exists()) {
            basket = basket.loadFromTxtFile(file);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Вы хотите изменить покупки? Введите yes или no");
        String input = scanner.nextLine();
        if ("no".equals(input)) {
            System.out.println("Ваша корзина:");
            basket = basket.loadFromTxtFile(file);
        } else {
            for (int i = 0; i < numbers.length; i++) {
                System.out.println(numbers[i] + " " + product[i] + prices[i] + " руб/штука");
            }
            while (true) {
                System.out.println("Введите номер позиции и количество или введите end");
                String in = scanner.nextLine();
                if ("end".equals(in)) {
                    break;
                } else {
                    String[] parts = in.split(" ");
                    int productNumber = Integer.parseInt(parts[0]) - 1;
                    int productCount = Integer.parseInt(parts[1]);
                    basket.addToCart(productNumber, productCount);
                    basket.saveTxt(file);
                }
            }
            basket.printCart();
        }
    }
}