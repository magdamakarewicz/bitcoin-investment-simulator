package com.enjoythecode.bitcoininvestmentsimulator.app;

import com.enjoythecode.bitcoininvestmentsimulator.exception.InvalidInputDataException;
import com.enjoythecode.bitcoininvestmentsimulator.service.IBtcService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * BitcoinInvestmentSimulator is an application for calculating potential profits from investing in Bitcoin.
 * <p>
 * It uses the IBtcService interface to retrieve Bitcoin exchange rates from an external API and perform calculations
 * based on user input. The application allows the user to select a potential purchase date, currency, and amount to
 * invest in Bitcoin, and calculates the amount of Bitcoin they would have received on that date, as well as the current
 * value of that Bitcoin investment in the chosen currency.
 * <p>
 * The main method of the application initializes an AnnotationConfigApplicationContext to load the application context,
 * then retrieves a bean of type IBtcService from the context. It then prompts the user for input and handles exceptions
 * using the handleException method.
 * <p>
 * The application is configured to scan for components in the "com.enjoythecode" package using the @ComponentScan
 * annotation on the BitcoinInvestmentSimulator class.
 *
 * @author Magda M.
 */

@ComponentScan(basePackages = "com.enjoythecode")
public class BitcoinInvestmentSimulator {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BitcoinInvestmentSimulator.class);
        IBtcService bitcoinService = ctx.getBean(IBtcService.class);

        Scanner scanner = new Scanner(System.in);
        String datePattern = "dd-MM-yyyy";

        System.out.println("Welcome to the Bitcoin Investment Simulator! Would you like to see how much money " +
                "you would have made if you invested in BTC x years ago?");
        printOptions();
        int option = -1;

        do {
            try {
                switch ((option = scanner.nextInt())) {
                    case 1:
                        scanner.nextLine();
                        System.out.println("Select a potential purchase date in the format: " + datePattern);
                        String date = scanner.nextLine();
                        System.out.println("Enter the currency you would have used to purchase BTC (USD/PLN):");
                        String currency = scanner.nextLine().toUpperCase();
                        System.out.println("Enter the amount you would have invested:");
                        BigDecimal amount = BigDecimal.valueOf(scanner.nextDouble());
                        scanner.nextLine();
                        BigDecimal btcPrice = bitcoinService.getBtcCalculationPrice(date, currency);
                        System.out.println("BTC price in " + currency + " on " + date + " was " +
                                btcPrice.setScale(4, RoundingMode.HALF_UP));
                        BigDecimal btcAmount = amount.divide(btcPrice, 2, RoundingMode.HALF_UP);
                        System.out.println("After purchasing for the specified amount, you would have " +
                                btcAmount.setScale(2, RoundingMode.HALF_UP) + " BTC");
                        BigDecimal btcSale = bitcoinService.btcSaleEvent(date, currency, amount);
                        System.out.println("Selling the BTC today would result in " +
                                btcSale.setScale(2, RoundingMode.HALF_UP) +
                                " " + currency);
                        BigDecimal profit = btcSale.subtract(amount);
                        double percentageProfit = profit.divide(amount, 2, RoundingMode.HALF_UP)
                                .doubleValue() * 100;
                        System.out.println("Which means a profit percentage of: " + percentageProfit);
                        printOptions();
                        break;
                    case 0:
                        System.out.println("Goodbye");
                        break;
                    default:
                        System.err.println("Option not recognized");
                        printOptions();
                }
            } catch (InvalidInputDataException e) {
                handleException(e);
            } catch (InputMismatchException e) {
                handleException(e);
                scanner.nextLine();
            }
        } while (option != 0);
        scanner.close();
    }

    private static void printOptions() {
        System.out.println("Select an option:");
        System.out.println("1 - Let's do this!");
        System.out.println("0 - Exit");
    }

    private static void handleException(Throwable e) {
        if (e.getMessage() != null) System.err.println(e.getMessage());
        else System.err.println("Input error");
        printOptions();
    }

}