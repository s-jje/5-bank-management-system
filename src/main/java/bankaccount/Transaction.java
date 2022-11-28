package bankaccount;

public interface Transaction {

    void deposit();

    void withdrawal();

    void transfer();

    void receive(BankAccount srcBankAccount, BankAccount dstBankAccount, long amount);
}
