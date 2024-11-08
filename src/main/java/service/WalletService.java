package service;

import entities.Transaction;
import entities.Wallet;
import entities.enums.TransactionType;
import repository.Impl.TransactionRepositoryImpl;
import repository.Impl.WalletRepositoryImpl;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class WalletService {
    TransactionRepositoryImpl transactionRepository = new TransactionRepositoryImpl();

    public void withdraw(double amount,Wallet wallet) throws SQLException {
          if(wallet.getBalance() >= amount){
              wallet.setBalance(wallet.getBalance() - amount);
              Transaction transaction = new Transaction(
                      amount, TransactionType.WITHDRAW, Date.valueOf(LocalDate.now()),wallet);
              transactionRepository.create(transaction);
          }
        WalletRepositoryImpl.updateAmount(wallet.getBalance(),wallet.getId());

    }
    public void deposit(double amount,Wallet wallet) throws SQLException {

         wallet.setBalance(wallet.getBalance() + amount);
        Transaction transaction = new Transaction(
                amount, TransactionType.DEPOSIT, Date.valueOf(LocalDate.now()),wallet);
        transactionRepository.create(transaction);
        WalletRepositoryImpl.updateAmount(wallet.getBalance(),wallet.getId());
    }
    public double displayRecentBalance(Wallet wallet){
        return wallet.getBalance();
    }
}
