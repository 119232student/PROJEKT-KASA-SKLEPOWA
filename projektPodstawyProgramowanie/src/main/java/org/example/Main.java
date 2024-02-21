package org.example;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static ArrayList<Product> productsList = new ArrayList<Product>();
    private static ArrayList<Product> totalProductsList = new ArrayList<Product>();
    private static final float vat = 1.23f;
    private static float totalPrice = 0f;
    public static void main(String[] args) {
        productsList.add (new Product("Masło Extra", "001", 6.5f));
        productsList.add (new Product("Chleb wiejski","002",4.5f));
        productsList.add (new Product("Makaron Babuni","003",9.2f));
        productsList.add (new Product("Dżem truskawkowy","004",8.1f));
        productsList.add (new Product("Kiełbasa myśliwska","005",29f));
        productsList.add (new Product("Szynka konserwowa","006",22f));
        productsList.add (new Product("Chipsy paprykowe","007",6f));
        productsList.add (new Product("Serek wiejski","008",3.5f));
        productsList.add (new Product("Sól kuchenna","009",2.7f));
        productsList.add (new Product("Cukier kryształ","010",3.2f));
        chooseOption();
    }
    public static void chooseOption(){
        Scanner scanner = new Scanner(System.in);
        String choosedOption = "";
        do {
            System.out.println("WYBIERZ OPCJĘ:");
            System.out.println("1 => LISTA WSZYSTKICH PRODUKTÓW");
            System.out.println("2 => ZAKUPY");
            System.out.println("3 => ZAKOŃCZ PROGRAM");
            System.out.println("WYBIERZ 1, 2, 3:");
            choosedOption = scanner.next();
            if (!choosedOption.equals("1") && !choosedOption.equals("2") && !choosedOption.equals("3")){
                System.out.println("NIE MA TAKIEJ OPCJI");
            } else if (choosedOption.equals("1")){
                showProducts();
            } else if (choosedOption.equals("2")) {
                shopping();
            }
        } while (!choosedOption.equals("3") && !choosedOption.equals("2"));

    }

        public static void showProducts(){
            System.out.println("KOD KRESKOWY | NAZWA");
            for(Product product : productsList){
                System.out.println(product.getBarCode() + " | " + product.getProductName());
            }
        }
        public static void shopping(){
        System.out.println("KOD KRESKOWY LUB WYDRUK PARAGONU (P)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        while(!input.equalsIgnoreCase("p")){
            Product product = null;
            for(int i = 0; i<productsList.size(); i++){
                if(input.equals(productsList.get(i).getBarCode())){
                    product = productsList.get(i);
                }
            }
            if (product == null){
                System.out.println("NIEPRAWIDŁOWY KOD PRODUKTU");
            }else {
                System.out.println(product.getProductName());
                totalPrice += product.getPrice();
                System.out.println("CENA ŁĄCZNA: " + round(totalPrice) + "ZŁ");
                totalProductsList.add(product);
            }
            System.out.println("KOD KRESKOWY LUB WYDRUK PARAGONU (P)");
            input = scanner.next();
        }
        receipt();
        }
        public static void receipt(){
        System.out.println("---------------------------------");
        System.out.println("PARAGON");
        Date data = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        System.out.println("DATA ZAKUPU: " + dateFormat.format(data));
        System.out.println("---------------------------------");
        for(int j = 0; j<totalProductsList.size(); j++){
            System.out.println(totalProductsList.get(j).getProductName() + " | " + round(totalProductsList.get(j).getPrice()) + "ZŁ");
        }
        System.out.println("---------------------------------");
        System.out.println("DO ZAPŁATY: " + round(totalPrice) + "ZŁ");
        float total_vat = totalPrice - (totalPrice / vat);
        System.out.println("W TYM VAT: " + round(total_vat) + "ZŁ");
        }
        public static String round(float var){
           return String.format("%.2f", Math.round(var * 100)/100f);
        }
}