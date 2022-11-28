package bankaccount;

import bank.Bank;
import util.BankingSystem;
import util.MoneyFormatter;
import util.TimeFormatter;

import java.util.List;
import java.util.stream.Collectors;

import static util.Time.getCurrentDateTime;

public class ShinhanBankAccount extends BankAccount {

    public ShinhanBankAccount(String name, String id, String password, String bankName, String accountNumber, long balance) {
        super(name, id, password, bankName, accountNumber, balance, 1.58549e-9, 300); // 5.0%
    }

    @Override
    public void transfer() {
        int dstBankNumber = Integer.parseInt(BankingSystem.chooseBank());

        if (dstBankNumber == 6) {
            return;
        } else if (dstBankNumber < 1 || dstBankNumber > 6) {
            throw new RuntimeException("Invalid input.");
        }

        System.out.print("Please enter the account number to transfer: ");
        String dstAccountNum = scanner.nextLine();
        Bank dstBank = BankingSystem.setDstBank(String.valueOf(dstBankNumber));

        String[] pattern = dstBank.getAccountNumberRegex();

        if (dstAccountNum.matches(pattern[1])) {
            dstAccountNum = dstAccountNum.replace("-", "");
        } else if (!dstAccountNum.matches(pattern[0])) {
            throw new RuntimeException("Invalid input.");
        }
        dstAccountNum = formatAccountNumber(dstAccountNum);

        List<BankAccount> dstBankAccounts = dstBank.getIdBankAccountListMap().values().stream().flatMap(List::stream).collect(Collectors.toList());

        while (true) {
            int i;
            for (i = 0; i < dstBankAccounts.size(); i++) {
                if (dstAccountNum.equals(dstBankAccounts.get(i).getAccountNumber())) {
                    BankAccount dstBankAccount = dstBankAccounts.get(i);
                    long balance = getBalance();

                    if (balance > 0) {
                        System.out.print("Please enter the amount: ");
                        long amount = Long.parseLong(scanner.nextLine());

                        if (amount > 0) {
                            StringBuilder dstDescription = new StringBuilder(dstBankAccount.getBankName() + " " + formatAccountNumber(dstAccountNum) + " " + dstBankAccount.getName());
                            System.out.printf("Would you like to transfer to %s? [yes/no]: ", dstDescription);
                            String input = scanner.nextLine();

                            if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
                                if (balance >= amount) {
                                    dstBankAccount.receive(this, dstBankAccount, amount);
                                    balance -= amount;
                                    setBalance(balance);
                                    addTransactionData(new TransactionData(TimeFormatter.format(getCurrentDateTime()), getAccountNumber(), false, amount, balance, dstDescription.toString()));
                                    System.out.printf("%nTransfer successful! All transfer fees are %s forever at Toss Bank!%n", MoneyFormatter.formatToWon(getTransferFee()));
                                    break;
                                } else {
                                    System.out.printf("%nTransfer failed.%n%n");
                                }
                            } else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
                                System.out.printf("%nTransfer process has been canceled.%n");
                            } else {
                                throw new RuntimeException("Invalid input.");
                            }
                        } else {
                            System.out.printf("%nYou can deposit more than ₩0.%n");
                        }
                    } else {
                        System.out.printf("%nYou have no balance to transfer.%n");
                    }
                }
            }

            if (i == dstBankAccounts.size()) {
                System.out.printf("%nNot found account number: %s%n", formatAccountNumber(dstAccountNum));
                System.out.print("Do you want exit transfer process? [yes/no]: ");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
                    return;
                } else if (!input.equalsIgnoreCase("n") && !input.equalsIgnoreCase("no")) {
                    throw new RuntimeException("Invalid input.");
                }
                System.out.print("Please enter the account number to transfer: ");
                dstAccountNum = scanner.nextLine();
            } else {
                break;
            }
        }
    }

    @Override
    protected String formatAccountNumber(String accountNumber) {
        StringBuilder sb = new StringBuilder();
        accountNumber = accountNumber.replace("-", "");
        sb.append(accountNumber, 0, 3).append("-").append(accountNumber, 3, 6).append("-").append(accountNumber, 6, 12);
        return sb.toString();
    }
}