package org.example;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;


public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String[] product = {"Молоко ", "Хлеб ", "Рис "};
        int[] prices = {50, 14, 80};
        int[] numbers = {1, 2, 3};

        Basket basket = new Basket();

        ClientLog clientLog = new ClientLog();
        File xml = new File("shop.xml");
    }
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(xml);

    XPathFactory xPathFactory = XPathFactory.newInstance();
    XPath xPath = xPathFactory.newXPath();
    loadEnable = Boolean.parseBoolean(xPath.evaluate("/config/load/enabled", doc));
    fileFormat = (xPath.evaluate("/config/load/format", doc));
    fileName = new File((xPath.evaluate("/config/load/fileName", doc)) + "." + fileFormat);
    saveEnable = Boolean.parseBoolean(xPath.evaluate("/config/save/enabled", doc));
    saveFormat = (xPath.evaluate("/config/save/format", doc));
    saveName = new File((xPath.evaluate("/config/save/fileName", doc)) + "." + saveFormat);
    logEnable = Boolean.parseBoolean(xPath.evaluate("/config/log/enabled", doc));
    logFileName = new File((xPath.evaluate("/config/log/fileName", doc)));

    if (loadEnable)

    {
        basket.deserialization(fileName);
    }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Вы хотите изменить покупки? Введите yes или no");
        String input = scanner.nextLine();
        if ("no".equals(input)) {
        if (loadEnable) {
            System.out.println("Ваша корзина:");
            basket.deserialization(fileName);
        }
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
                    if (logEnable) {
                        clientLog.log(productNumber, productCount);
                        clientLog.exportAsCSV(logFileName);
                    }
                }
            }
            basket.printCart();
        }
    }
}