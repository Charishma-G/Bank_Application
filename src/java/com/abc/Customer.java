package com.abc;

import static java.lang.Math.abs;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private String ssn;
    private List<Account> accounts;

    public Customer(String ssn,String name) {
        this.name = name;
        this.ssn=ssn;
        this.accounts = new ArrayList<Account>();
    }

    

    public Customer openAccount(Account account) {
    	if(findAccountExistOrNot(account))
               getAccounts().add(account);
        return this;
    }

    public int getNumberOfAccounts() {
        return getAccounts().size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Account a : getAccounts())
            total += a.interestEarned();
        return total;
    }

    public String getStatement() {
        String statement = null;
        statement = "Statement for " + name  +":" +"\n";
        double total = 0.0;
        for (Account a : getAccounts()) {
            statement += "\n" + statementForAccount(a) + "\n";
            total += a.sumTransactions();
        }
        statement += "\nTotal balance in " +name +"'s" +" "+"Account: "+ toDollars(total);
        return statement;
    }

    private String statementForAccount(Account a) {
        String s = "";

       //Translate to pretty account type
        switch(a.getAccountType()){
            case AccountTypesConstants.CHECKING:
                s += "Checking Account:\n";
                break;
            case AccountTypesConstants.SAVINGS:
                s += "Savings Account:\n";
                break;
            case AccountTypesConstants.MAXI_SAVINGS:
                s += "Maxi Savings Account\n";
                break;
        }

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.getTransactions()) {
            s += "  " + (t.getAmount() < 0 ? "Amount withdrawn: " : "Amount Deposited: ") + " " + toDollars(t.getAmount()) + "\n";
            total += t.getAmount();
        }
        s += "  Total \t  :  " + toDollars(total);
        return s;
    }
    
    public void transferFundsOfCust(Account fromAccount,Account toAccount,double transferAmount){
    	Bank bank= Bank.getInstance();
    	if(bank!=null){
    		List<Customer> customers= bank.getCustomers();
    		if(customers!=null&&customers.size()>0){
    			   boolean custFlag=findCustomer(customers);
    			   if(custFlag){
    				   boolean flag=findAccountsOfCust(fromAccount,toAccount);
    				   if(flag){
    					   try{
    					  boolean withdrawFlag=fromAccount.withdraw(transferAmount);
    					  if(withdrawFlag){
    						  try{
    						  boolean depositFlag=toAccount.deposit(transferAmount);
    						  }catch(Exception e){
    							  fromAccount.deposit(transferAmount);
    						  }
    						  
    					  }
    					   }catch(IllegalArgumentException lae){
    						   throw lae;
    					   }catch(NoSufficientBalanceException nsbe){
    						   throw nsbe;
    					   }
    					  
    				   }else{
    					   throw new RuntimeException("Both acconts doesn't belong to same customer");
    				   }
    			   }
    		}
    	}
    }
   private boolean findCustomer(List<Customer> customers){
	   for(Customer customer : customers){
		     if(getSsn().equals(customer.getSsn())){
		    	 return true;
		     }
	   }
	   return false;
   }
   
  private boolean findAccountsOfCust(Account fromAccount,Account toAccount){
	  int count=0;
	  List<Account> accounts =getAccounts();
	  if(accounts!=null&&accounts.size()>1){
		     for(Account acct: accounts){
		    	 if(acct.getAccountNumber()==fromAccount.getAccountNumber()||acct.getAccountNumber()==toAccount.getAccountNumber()){
		    		 count++;
		    	 }
		     }
	  }
	  if(count==2)
		  return true;
	  else
	  return false;
  }
  
  private boolean findAccountExistOrNot(Account account){
	  Bank bank=Bank.getInstance();
	  if(bank!=null&&bank.getCustomers()!=null&&bank.getCustomers().size()>0){
		  for(Customer cust:bank.getCustomers() ){
			  List<Account> accounts= cust.getAccounts();
			  	if(accounts!=null){
			  		if(accounts.size()>0){
			  			for(Account acct: accounts){
			  				if(acct.getAccountNumber()==account.getAccountNumber()){
			  					return false;
			  				}
			  			}
			  		}
			  	}
		  }
	  }
	  
  	return true;
  }
  
    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }

	public String getSsn() {
		return ssn;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
        return name;
    }
    
}
