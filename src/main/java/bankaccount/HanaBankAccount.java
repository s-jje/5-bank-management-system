package bankaccount;

import bank.Bank;
import util.BankingSystem;
import util.MoneyFormatter;
import util.TimeFormatter;

import java.time.ZonedDateTime;

import static util.Time.getCurrentDateTime;

public class HanaBankAccount extends BankAccount {

    public HanaBankAccount(String name, String id, String password, String bankName, String accountNumber, long balance) {
        super(name, id, password, bankName, accountNumber, balance);
    }

    @Override
    public void deposit() {
        System.out.println("==============================");
        System.out.println("★예금★");
        System.out.println("==============================");

        while (true) {
            System.out.print("입금하실 금액을 입력해주세요 : ");
            long money = Long.parseLong(scanner.nextLine());
            if (money > 0) {
                long balance = getBalance() + money;
                setBalance(balance);

                ZonedDateTime zonedDateTime = null;
                addTransactionData(new TransactionData(TimeFormatter.format(zonedDateTime), getAccountNumber(), true, money, balance, "Hana Bank"));
                System.out.println("==============================");
                System.out.println(MoneyFormatter.formatToWon(money) + "원이 성공적으로 입금되었습니다.");
                System.out.println("현재잔액 : " + MoneyFormatter.formatToWon(getBalance()) + "원");
                break;
            } else {
                System.out.println("※ 금액을 잘못 입력하셨습니다. 다시 입력해주세요.");
            }
        }
    }

    @Override
    public void withdrawal() {
        System.out.println("==============================");
        System.out.println("★출금★");
        System.out.println("==============================");
        System.out.println("출금가능잔액 : " + MoneyFormatter.formatToWon(getBalance()) + "원");

        while (true) {
            System.out.print("출금하실 금액을 입력해주세요 : ");
            long money = Long.parseLong(scanner.nextLine());
            if (money < getBalance() && money > 0) {
                long balance = getBalance() - money;
                setBalance(balance);

                ZonedDateTime zonedDateTime = null;
                addTransactionData(new TransactionData(TimeFormatter.format(zonedDateTime), getAccountNumber(), false, money, balance, "Hana Bank"));
                System.out.println("==============================");
                System.out.println(MoneyFormatter.formatToWon(money) + "원이 성공적으로 출금되었습니다.");
                System.out.println("현재잔액 : " + MoneyFormatter.formatToWon(getBalance()) + "원");
                break;
            } else {
                System.out.println("※ 금액을 잘못 입력하셨습니다. 다시 입력해주세요.");
            }
        }
    }

    @Override
    public void transfer() {
        System.out.println("==============================");
        System.out.println("★송금★");
        System.out.println("==============================");
        while (true) {
            String bankNumber = BankingSystem.chooseBank();
            if (bankNumber.equals("6")) {
                return;
            }
            Bank toBank = BankingSystem.setDstBank(bankNumber);
            System.out.print(toBank.getName() + "로 송금하실 계좌번호를 입력해주세요 : ");

            String toAno = scanner.nextLine();
            BankAccount toBankAccount = toBank.getBankAccount(toAno);

            if (getBalance() > 0) {
                System.out.println("==============================");
                System.out.println("보내시는분 : " + getName() + " / " + getBankName() + " : " + getAccountNumber());
                System.out.println("받으시는분 : " + toBankAccount.getName() + " / " + toBankAccount.getBankName() + " : " + toBankAccount.getAccountNumber());
                System.out.println("==============================");
                System.out.print("위 정보가 맞으시면 1번 아니시면 2번을 눌러주세요 : ");
                int num = Integer.parseInt(scanner.nextLine());
                if (num == 1) {
                    System.out.println("==============================");
                    System.out.println("송금가능잔액 : " + MoneyFormatter.formatToWon(getBalance()) + "원");
                    System.out.print("송금하실 금액을 입력해주세요 : ");
                    long money = Long.parseLong(scanner.nextLine());
                    long balance = getBalance();

                    if (money > 0 && getBalance() >= money) {
                        balance -= money;
                        toBankAccount.receive(this, toBankAccount, money);
                        setBalance(balance);

                        StringBuilder dstStr = new StringBuilder(toBankAccount.getBankName() + " " + toAno);
                        addTransactionData(new TransactionData(TimeFormatter.format(getCurrentDateTime()), getAccountNumber(), false, money, balance, dstStr.toString()));
                        System.out.println("[" + toBankAccount.getName() + "님께 " + MoneyFormatter.formatToWon(money) + "원 송금이 완료되었습니다.]");
                        break;
                    } else {
                        System.out.println("==============================");
                        System.out.println("[※ 금액을 잘못 입력하셨습니다. 다시 입력해주세요.]");
                        System.out.println("==============================");
                    }
                } else if (num == 2) {
                    System.out.println("[※ 처음부터 다시 입력해주세요.]");
                }
            }
        }
    }

    @Override
    public void receive(BankAccount srcBankAccount, BankAccount dstBankAccount, long amount) {
        long balance = getBalance() + amount;
        dstBankAccount.setBalance(balance);
        dstBankAccount.addTransactionData(new TransactionData(TimeFormatter.format(getCurrentDateTime()), dstBankAccount.getAccountNumber(), true, amount, balance, srcBankAccount.getBankName() + " " + srcBankAccount.getAccountNumber() + " " + srcBankAccount.getName()));
    }

    @Override
    public void showBalance() {
        System.out.println("==============================");
        System.out.println("★잔액확인★");
        System.out.println("==============================");
        System.out.println("현재잔액은 " + MoneyFormatter.formatToWon(getBalance()) + "원입니다.");
    }
}