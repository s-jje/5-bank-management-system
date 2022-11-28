package bankaccount;

import bank.Bank;
import util.BankingSystem;
import util.MoneyFormatter;
import util.TimeFormatter;

import static util.Time.getCurrentDateTime;

public class HanaBankAccount extends BankAccount {

    public HanaBankAccount(String name, String id, String password, String bankName, String accountNumber, long balance) {
        super(name, id, password, bankName, accountNumber, balance, 6.34196e-10); // 2.0%
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
    protected String formatAccountNumber(String accountNumber) {
        StringBuilder sb = new StringBuilder();
        accountNumber = accountNumber.replace("-", "");
        sb.append(accountNumber, 0, 3).append("-").append(accountNumber, 3, 9).append("-").append(accountNumber, 9, 14);
        return sb.toString();
    }
}