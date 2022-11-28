package bank;

import bankaccount.BankAccount;
import bankaccount.HanaBankAccount;
import bankaccount.TossBankAccount;

import java.util.List;

public class HanaBank extends Bank {

    static HanaBank instance;

    private HanaBank() {
        super("Hana Bank", 2);
    }

    public static HanaBank getInstance() {
        if (instance == null) {
            instance = new HanaBank();
        }
        return instance;
    }

    @Override
    protected BankAccount createBankAccount(String name, String id, String password, String newAccountNumber) {
        return new HanaBankAccount(name, id, password, name, newAccountNumber, 0L);
    }

    @Override
    protected String generateAccountNumber() {
        List<BankAccount> bankAccountList = getBankAccountList();
        int size = bankAccountList.size();

        while (true) {
            int num1 = ((int) (Math.random() * 899)) + 100;
            int num2 = ((int) (Math.random() * 899999)) + 100000;
            int num3 = ((int) (Math.random() * 89999)) + 10000;
            String ano = num1 + "-" + num2 + "-" + num3;

            int i;
            for (i = 0; i < size; i++) {
                if (ano.equals(bankAccountList.get(i).getAccountNumber())) {
                    break;
                }
            }

            if (i == size) {
                return ano;
            }
        }
    }
}