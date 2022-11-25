import bank.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BankManagementSystemApplication {

    public static void main(String[] args) {
        List<Bank> bankList = new ArrayList<>(Arrays.asList(
                TossBank.getInstance(), KbKookminBank.getInstance(),
                ShinhanBank.getInstance(), WooriBank.getInstance(),
                HanaBank.getInstance()
        ));

        System.out.println("Welcome to bank management system!");

        while (true) {
            try {
                String bankNumber = BankingSystem.chooseBank();
                System.out.println();

                Bank bank;

                if (bankNumber.equals("1")) {
                    bank = bankList.get(0);
                } else if (bankNumber.equals("2")) {
                    bank = bankList.get(1);
                } else if (bankNumber.equals("3")) {
                    bank = bankList.get(2);
                } else if (bankNumber.equals("4")) {
                    bank = bankList.get(3);
                } else if (bankNumber.equals("5")) {
                    bank = bankList.get(4);
                } else if (bankNumber.equals("6")) {
                    break;
                } else {
                    throw new RuntimeException("Invalid number.");
                }

                while (true) {
                    try {
                        String input = BankingSystem.chooseMenu();
                        System.out.println();

                        if (input.equals("1")) {
                            BankingSystem.transaction(bank);
                        } else if (input.equals("2")) {
                            BankingSystem.checkBalance(bank);
                        } else if (input.equals("3")) {
                            BankingSystem.checkAllTransactions(bank);
                        } else if (input.equals("4")) {
                            BankingSystem.createAccount(bank);
                        } else if (input.equals("5")) {
                            break;
                        } else if (input.equals("6")) {
                            return;
                        } else if (input.equals("manage")) {
                            BankingSystem.management(bank);
                        } else {
                            throw new RuntimeException("Invalid number.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.printf("%n%s%n%n", e.getMessage());
                    } catch (RuntimeException e) {
                        System.out.printf("%n%s%n%n", e.getMessage());
                    }
                }
            } catch (NumberFormatException e) {
                System.out.printf("%n%s%n%n", e.getMessage());
            } catch (RuntimeException e) {
                System.out.printf("%n%s%n%n", e.getMessage());
            }
        }
    }
}
