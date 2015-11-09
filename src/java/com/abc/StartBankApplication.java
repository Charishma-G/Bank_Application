package com.abc;

import com.abc.Account;
import com.abc.Bank;
import com.abc.Customer;

public class StartBankApplication {

	public static void main(String[] args) {
		
		Bank bank=Bank.getInstance();
		
		Customer customer =new Customer("982018957","John");
		bank.addCustomer(customer);
		
		Account account1 =new Account(AccountTypesConstants.CHECKING);
		customer.openAccount(account1);
		
		Account account2 =new Account(AccountTypesConstants.SAVINGS);
		customer.openAccount(account2);
		account1.deposit(5000);
		account1.withdraw(200);
		
		account2.deposit(2000);
		account2.withdraw(1000);
		
		Customer customer2 =new Customer("84514026","Smith");
		bank.addCustomer(customer2);
		
		Account account3 =new Account(AccountTypesConstants.CHECKING);
		customer2.openAccount(account3);
		
		Account account4 =new Account(AccountTypesConstants.SAVINGS);
		customer2.openAccount(account4);
		account3.deposit(10000);
		account3.withdraw(890);
		
		account4.deposit(8000);
		account4.withdraw(1000);
		
		System.out.println("---- Statement generated for first Customer ----\n");
		
		System.out.println(customer.getStatement());
		System.out.println("\n---- End of First Customer Statement ----\n");
		
		System.out.println("---- Statement generated for second Customer ----\n");
		
		System.out.println(customer2.getStatement());
		System.out.println("\n---- End of second Customer Statement ----\n");
		
		System.out.println("First Customer of Bank is :  "+bank.getFirstCustomer());
		System.out.println("\n---- Bank Summary ----");
		System.out.println(bank.customerSummary());
		
		System.out.println("\nTotal Interest Paid by Bank is :"+bank.totalInterestPaid());
	}

}
