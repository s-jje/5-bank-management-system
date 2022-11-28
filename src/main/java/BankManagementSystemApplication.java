import bank.*;
import util.BankingSystem;

import java.util.HashMap;
import java.util.Map;

public class BankManagementSystemApplication {

    public static void main(String[] args) {
        Map<String, Bank> banks = new HashMap<String, Bank>(){{
            put("Toss Bank", TossBank.getInstance());
            put("Kb Kookmin Bank", KbKookminBank.getInstance());
            put("Shinhan Bank", ShinhanBank.getInstance());
            put("Woori Bank", WooriBank.getInstance());
            put("Hana Bank", HanaBank.getInstance());
        }};

        System.out.println("Welcome to bank management system!");

        while (true) {
            try {
                String bankNumber = BankingSystem.chooseBank();

                Bank bank;

                if (bankNumber.equals("1")) {
                    bank = banks.get("Toss Bank");
                } else if (bankNumber.equals("2")) {
                    bank = banks.get("Kb Kookmin Bank");
                } else if (bankNumber.equals("3")) {
                    bank = banks.get("Shinhan Bank");
                } else if (bankNumber.equals("4")) {
                    bank = banks.get("Woori Bank");
                } else if (bankNumber.equals("5")) {
                    bank = banks.get("Hana Bank");
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
                        } else {
                            throw new RuntimeException("Invalid number.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.printf("%n%s%n", e.getMessage());
                    } catch (RuntimeException e) {
                        System.out.printf("%n%s%n", e.getMessage());
                    }
                }
            } catch (NumberFormatException e) {
                System.out.printf("%n%s%n", e.getMessage());
            } catch (RuntimeException e) {
                System.out.printf("%n%s%n", e.getMessage());
            }
        }
    }
}
