import bank.*;
import util.BankingSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class BankManagementSystemApplication {

    public static void main(String[] args) {
        List<Bank> banks = new ArrayList<>(Arrays.asList(
                TossBank.getInstance(), KbKookminBank.getInstance(),
                ShinhanBank.getInstance(), WooriBank.getInstance(),
                HanaBank.getInstance())
        );

        System.out.println("Welcome to bank management system!");

        while (true) {
            try {
                String bankNumber = BankingSystem.chooseBank();

                Bank bank;

                if (bankNumber.equals("1")) {
                    bank = banks.get(0);
                } else if (bankNumber.equals("2")) {
                    bank = banks.get(1);
                } else if (bankNumber.equals("3")) {
                    bank = banks.get(2);
                } else if (bankNumber.equals("4")) {
                    bank = banks.get(3);
                } else if (bankNumber.equals("5")) {
                    bank = banks.get(4);
                } else if (bankNumber.equals("6")) {
                    break;
                } else {
                    throw new RuntimeException("Invalid number.");
                }

                while (true) {
                    try {
                        String input = BankingSystem.chooseMenu();

                        if (input.equals("1")) {
                            BankingSystem.transaction(bank);
                        } else if (input.equals("2")) {
                            BankingSystem.checkBalance(bank);
                        } else if (input.equals("3")) {
                            BankingSystem.checkAllTransactions(bank);
                        } else if (input.equals("4")) {
                            BankingSystem.createAccount(bank);
                        } else if (input.equals("5")) {
                            BankingSystem.setting(bank);
                        } else if (input.equals("6")) {
                            break;
                        } else if (input.equals("7")) {
                            return;
                        } else if (input.equals("admin")) {
                            BankingSystem.admin(bank);
                        } else {
                            throw new RuntimeException("Invalid number.");
                        }
                    } catch (NoSuchElementException e) {
                        System.out.printf("%n%s%n", e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.printf("%n%s%n", e.getMessage());
                    } catch (RuntimeException e) {
                        System.out.printf("%n%s%n", e.getMessage());
                    }
                }
            } catch (NoSuchElementException e) {
                System.out.printf("%n%s%n", e.getMessage());
            } catch (NumberFormatException e) {
                System.out.printf("%n%s%n", e.getMessage());
            } catch (RuntimeException e) {
                System.out.printf("%n%s%n", e.getMessage());
            }
        }
    }
}
