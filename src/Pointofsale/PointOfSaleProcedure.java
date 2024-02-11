package pos2.src.Pointofsale;

import java.io.IOException;
import java.util.*;

public class PointOfSaleProcedure {

    Scanner scan = new Scanner(System.in);
    ArrayList<PointOfSale> product = new ArrayList<PointOfSale>();
    HashMap<Integer, PointOfSale> identifier = new HashMap<Integer, PointOfSale>();

    public void choices() {
        System.out.println("================Point Of Sale=================");
        System.out.format("%s %-34s %10s", "|", "[1]Display Inventory.", "|\n");
        System.out.format("%s %-34s %10s", "|", "[2]Add Product Quantity.", "|\n");
        System.out.format("%s %-34s %10s", "|", "[3]Enter Sale Transaction.", "|\n");
        System.out.format("%s %-34s %10s", "|", "[4]Display all Sale Transaction.", "|\n");
        System.out.format("%s %-34s %10s", "|", "[Q]To quit.", "|\n");
        System.out.println("==============================================");
    }

    public void productChoices() {
        System.out.println("================");
        System.out.println("|Product name  |");
        System.out.println("|--------------|");

        for (int index = 0; index < product.size(); index++) {
            System.out.format("%2s%s%s%-9s %2s", "| [", index + 1, "]", product.get(index).getName(), "|\n");
        }
        System.out.println("================");
    }

    public void choose() {
        addProduct();
        String pick = " ";
        int prod, item;
        while (!pick.equalsIgnoreCase("q")) {

            choices();
            System.out.print("Please enter your choice: ");
            pick = scan.next();
            clearConsole();

            switch (pick) {
                case "1":
                    System.out.println("===================Product===================");
                    System.out.format("%1s %-21s %6s %12s %s", "|", "Name", "Price", "Quantity", "|\n");
                    for (int index = 0; index < product.size(); index++) {
                        System.out.format("%1s %-20s %6s %10s  %4s", "|", product.get(index).getName(),
                                product.get(index).getPrice(), product.get(index).getQuantity(), "|\n");
                    }
                    System.out.println("=============================================");
                    break;

                case "2":
                    productChoices();
                    prod = trycatch("Please enter your choice: ");
                    prod--;
                    if (prod >= 0 || prod < 4) {
                        item = trycatch("Please enter how many " + product.get(prod).getName() + " you want to add: ");
                        if (item > 0) {
                            product.get(prod).setQuantityAdd(item);
                            System.out.println("================");
                            System.out.println("|Added!!!!!    |");
                            System.out.println("================");
                        } else {
                            System.out.println("invalid input!!!!");
                        }
                    }

                    else {
                        System.out.println("invalid input!!!!");
                    }
                    break;

                case "3":
                    String repeatOrder = " ";

                    while (!repeatOrder.equalsIgnoreCase("n")) {
                        productChoices();
                        prod = trycatch("Please enter your choice: ");
                        prod--;
                        double total;
                        if (prod >= 0 || prod < product.size()) {
                            item = trycatch("Please enter how many quantity of" + product.get(prod).getName()
                                    + " you are selling: ");
                            if (item < product.get(prod).getQuantity()) {
                                product.get(prod).setQuantitySold(item);
                                total = product.get(prod).getPrice() * Double.valueOf(item);
                                product.get(prod).setSold(total);

                                for (int x = 0; x < 58; x++)
                                    System.out.print("=");
                                System.out.format("%-16s %-14s %-15s %9s %1s", "\n| Item", "Quantity", "Unit price",
                                        "Subtotal", "|\n");

                                System.out.format("%s %-16s %-15s %-15s %-5s %1s", "|", product.get(prod).getName(),
                                        item, product.get(prod).getPrice(), total, "|\n");

                                for (int x = 0; x < 58; x++)
                                    System.out.print("-");
                                System.out.println("");
                            } else if (item >= product.get(prod).getQuantity()) {
                                System.out.println("Item is in short stock.");
                            } else {
                                System.out.println("invalid input!!!!");
                            }
                        } else {
                            System.out.println("invalid input!!!!");
                        }
                        boolean isloop=false;
                        System.out.println("Do you want another transaction?");
                        while (!isloop) {
                            System.out.println("[Y]Yes/[N]No");
                            System.out.print("Please enter your choice: ");
                            repeatOrder = scan.next();

                            switch(repeatOrder){
                                case "y": case"Y": case"n": case"N":
                                isloop=true;
                                break;

                                default:
                                    System.out.println("Invalid input!!");
                                break;
                            }
                        }

                    }

                    break;

                case "4":
                    break;

                case "q":
                case "Q":
                    System.out.println("Thank you for using the program, goodbye.");
                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }

            if (!pick.equalsIgnoreCase("q")) {
                System.out.println("Please enter to continue:");
                scan.nextLine();
                scan.nextLine();
                clearConsole();
            }
        }
        scan.close();
    }

    public void addProduct() {
        product.add(new PointOfSale("Pen", 10, 5));
        product.add(new PointOfSale("Pencil", 20, 2));
        product.add(new PointOfSale("Notebook", 10, 8));
        product.add(new PointOfSale("Eraser", 30, 2));
    }

    public void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");

            }
        } catch (IOException | InterruptedException ex) {
        }
    }

    public int trycatch(String outprint) {
        int number = 0;

        try {
            System.out.print(outprint);
            number = scan.nextInt();
        } catch (InputMismatchException e)// catch if the user input other data type than int
        {
        }

        return number;
    }

}
