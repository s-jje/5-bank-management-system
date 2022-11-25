import bank.Bank;

public class BankManagementSystemApplication {

    public static void main(String[] args) {
        try {
            while (true) {
                Bank bank = BankingSystem.chooseBank();
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
                    break;
                } else if (input.equals("manage")) {
                    BankingSystem.mangement(bank);
                } else {
                    throw new RuntimeException("Invalid number.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
