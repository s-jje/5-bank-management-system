package bankaccount;

import bank.Bank;
import util.BankingSystem;
import util.MoneyFormatter;
import util.TimeFormatter;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static util.Time.convertDateTimeToSecond;
import static util.Time.getCurrentDateTime;

public class TossBankAccount extends BankAccount {

    private final double INTEREST_RATE_PER_SECOND = 3.1709792e-9;

    private long prevTime;

    public TossBankAccount(String name, String id, String password, String bankName, String accountNumber, long balance) {
        super(name, id, password, bankName, accountNumber, balance);
        this.prevTime = 0;
    }

    @Override
    public void deposit() {
        System.out.print("Please enter the amount you want to deposit: ");
        long amount = Long.parseLong(scanner.nextLine());

        if (amount > 0) {
            ZonedDateTime zonedDateTime = getCurrentDateTime();
            long epochSecond = convertDateTimeToSecond(zonedDateTime);

            if (prevTime == 0) {
                prevTime = epochSecond;
            }

            long balance = getBalance() + amount;
            setBalance(balance);

            StringBuilder description = new StringBuilder();
            description.append(getBankName()).append(" ").append(getAccountNumber()).append(" ").append(getName());
            addTransactionData(new TransactionData(TimeFormatter.format(zonedDateTime), getAccountNumber(), true, amount, balance, description.toString()));
            System.out.printf("%nDeposit successful! The interest rate is %.1f%%%n", INTEREST_RATE_PER_SECOND * 100.0 * 60.0 * 60.0 * 24.0 * 365.0);
        } else {
            System.out.printf("%nYou can deposit more than ₩0.%n");
        }
    }

    @Override
    public void withdrawal() {
        if (getBalance() > 0) {
            System.out.print("Please enter the amount you want to withdrawal: ");
            long amount = Long.parseLong(scanner.nextLine());

            if (amount > 0) {
                long balance = getBalance() - amount;

                if (balance >= 0) {
                    setBalance(balance);

                    StringBuilder description = new StringBuilder();
                    description.append(getBankName()).append(" ").append(getAccountNumber()).append(" ").append(getName());
                    addTransactionData(new TransactionData(TimeFormatter.format(getCurrentDateTime()), getAccountNumber(), false, amount, balance, description.toString()));
                    System.out.printf("%nWithdrawal successful!%n");
                } else {
                    System.out.printf("%nWithdrawal failed.%n");
                }
            } else {
                System.out.printf("%nYou can deposit more than ₩0.%n");
            }
        } else {
            System.out.printf("%nYou have no balance to withdrawal.%n");
        }
    }

    public void transfer() {
        String bankNumber = BankingSystem.chooseBank();

        if (bankNumber.equals("6")) {
            return;
        }

        String srcNum = getAccountNumber();

        System.out.print("Please enter the account number to transfer: ");
        String dstNum = scanner.nextLine();

        Pattern pattern = Pattern.compile("\\d{3,5}-?\\d{2,5}-?\\d{3,6}");
        Matcher matcher = pattern.matcher(dstNum);

        while (!matcher.find()) {
            System.out.println("Invalid account number form.");
            System.out.print("Please enter the account number to transfer: ");
            dstNum = scanner.nextLine();
        }

        Bank dstBank = BankingSystem.setDstBank(bankNumber);
        List<BankAccount> dstBankAccounts = dstBank.getIdBankAccountListMap().values().stream().flatMap(List::stream).collect(Collectors.toList());

        while (true) {
            int i;
            for (i = 0; i < dstBankAccounts.size(); i++) {
                if (dstNum.equals(dstBankAccounts.get(i).getAccountNumber())) {
                    BankAccount dstBankAccount = dstBankAccounts.get(i);

                    long balance = getBalance();

                    if (balance > 0) {
                        System.out.print("Please enter the amount: ");
                        long amount = Long.parseLong(scanner.nextLine());

                        if (amount > 0) {
                            StringBuilder dstDescription = new StringBuilder(dstBankAccount.getBankName() + " " + formatAccountNumber(dstNum) + " " + dstBankAccount.getName());
                            System.out.printf("Would you like to transfer to %s? [yes/no]: ", dstDescription);
                            String input = scanner.nextLine();

                            if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
                                if (balance >= amount) {
                                    dstBankAccount.receive(this, dstBankAccount, amount);
                                    balance -= amount;
                                    setBalance(balance);
                                    addTransactionData(new TransactionData(TimeFormatter.format(getCurrentDateTime()), srcNum, false, amount, balance, dstDescription.toString()));
                                    System.out.printf("%nTransfer successful! All transfer fees are ₩0 forever at Toss Bank!%n");
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
                System.out.printf("%nNot found account number: %s%n", formatAccountNumber(dstNum));
                System.out.print("Do you want exit transfer process? [yes/no]: ");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
                    return;
                } else if (!input.equalsIgnoreCase("n") && !input.equalsIgnoreCase("no")) {
                    throw new RuntimeException("Invalid input.");
                }
                System.out.print("Please enter the account number to transfer: ");
                dstNum = scanner.nextLine();
            } else {
                break;
            }
        }
    }

    @Override
    public void receive(BankAccount srcBankAccount, BankAccount dstBankAccount, long amount) {
        long balance = getBalance() + amount;
        dstBankAccount.setBalance(balance);
        dstBankAccount.addTransactionData(new TransactionData(TimeFormatter.format(getCurrentDateTime()), dstBankAccount.getAccountNumber(), true, amount, balance, srcBankAccount.getBankName() + " " + srcBankAccount.getAccountNumber()));
    }

    @Override
    public void showBalance() {
        ZonedDateTime zonedDateTime = getCurrentDateTime();
        long epochSecond = convertDateTimeToSecond(zonedDateTime);
        long interest = applyInterest(epochSecond);
        String accountNumber = getAccountNumber();

        if (interest > 0) {
            addTransactionData(new TransactionData("-", accountNumber, true, interest, getBalance(), "Toss Bank Interest"));
        }
        System.out.printf("| %-13s: %20s |%n", accountNumber, MoneyFormatter.formatToWon(getBalance()));
    }

    private long applyInterest(long epochSecond) {
        long balance = getBalance();
        long interest = (long) (balance * (INTEREST_RATE_PER_SECOND * (epochSecond - prevTime)));
        setBalance(balance + interest);
        prevTime = epochSecond;
        return interest;
    }

    private String formatAccountNumber(String accountNumber) {
        StringBuilder sb = new StringBuilder();
        accountNumber = accountNumber.replace("-", "");
        sb.append(accountNumber, 0, 3).append("-").append(accountNumber, 3, 8).append("-").append(accountNumber, 8, 11);
        return sb.toString();
    }
}
