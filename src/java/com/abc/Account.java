package com.abc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class Account {

    private final int accountType;
    private final long accountNumber;
    private List<Transaction> transactions;
    private double balance=0.0;
    private Date lastTranscationDate;
   

    public Account(int accountType) {
        this.accountType = accountType;
        this.accountNumber=generateAccountNumber();
        this.transactions = new ArrayList<Transaction>();
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
        	  setBalance(getBalance()+amount);
        	  getTransactions().add(new Transaction(amount));
        }
        return true;
    }

public boolean withdraw(double amount) {
    if (amount <= 0) {
        throw new IllegalArgumentException("amount must be greater than zero");
    } else if(getBalance()>=amount) {
    	setBalance(getBalance()-amount);
    	setLastTranscationDate(DateProvider.getInstance().now());
    	getTransactions().add(new Transaction(-amount));
    }else{
    	 throw new NoSufficientBalanceException("amount must be less than current balance");
    }
    return true;
}

	public double interestEarned() {

		switch (accountType) {
		case AccountTypesConstants.CHECKING:
			return getCheckinAcctInt();
		case AccountTypesConstants.SAVINGS:
			return getSavingsAcctInt();
		case AccountTypesConstants.MAXI_SAVINGS:
			return getMaxiSavingsAcctInt();
		default:
			throw new IllegalArgumentException("Invalid account type");
		}
	}
	
	private double getSavingsAcctInt() {
		if (getBalance() <= InterestRates.SAVINGS_THRESHOLD)
			return getBalance() * InterestRates.SAVINGS_RATE;
		else
			return InterestRates.SAVINGS_THRESHOLD * InterestRates.SAVINGS_RATE
					+ (getBalance() - InterestRates.SAVINGS_THRESHOLD)
					* InterestRates.SAVINGS_RATE_ABOVE_THRESHOLD;
	}

	private double getMaxiSavingsAcctInt() {
		double interest = 0;
		if (getBalance() <= InterestRates.MAXI_SAVINGS_THRESHOLD)
			interest = getBalance() * InterestRates.MAXI_SAVINGS_RATE_BELOW_THRESHOLD;
		if (getBalance() >= InterestRates.MAXI_SAVINGS_THRESHOLD
				&& getBalance() < 2 * InterestRates.MAXI_SAVINGS_THRESHOLD) {
			interest = InterestRates.MAXI_SAVINGS_THRESHOLD
					* InterestRates.MAXI_SAVINGS_RATE_BELOW_THRESHOLD
					+ (getBalance() - InterestRates.MAXI_SAVINGS_THRESHOLD)
					* InterestRates.MAXI_SAVINGS_RATE_AT_THRESHOLD;
		}
		if (getBalance() > 2 * InterestRates.MAXI_SAVINGS_THRESHOLD) {
			interest = InterestRates.MAXI_SAVINGS_THRESHOLD
					* InterestRates.MAXI_SAVINGS_RATE_BELOW_THRESHOLD
					+ InterestRates.MAXI_SAVINGS_THRESHOLD *InterestRates. MAXI_SAVINGS_RATE_AT_THRESHOLD
					+ (getBalance() - 2 * InterestRates.MAXI_SAVINGS_THRESHOLD)
					* InterestRates.MAXI_SAVINGS_RATE_ABOVE_THRESHOLD;
		}
		return interest;
	}

	private double getCheckinAcctInt() {
		return getBalance() * InterestRates.CHECKING_FLAT_RATE;

	}
	    
	
		public long generateAccountNumber(){
			long acctNumber=0;
			acctNumber = (long)((Math.random() * 9000000)+1000000);
			return acctNumber;
		}
    
    

    public double sumTransactions() {
       return checkIfTransactionsExist(true);
    }

    private double checkIfTransactionsExist(boolean checkAll) {
        double amount = 0.0;
        for (Transaction t: getTransactions())
            amount += t.getAmount();
        return amount;
    }
    
 

    public int getAccountType() {
        return accountType;
    }

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	

	public Date getLastTranscationDate() {
		return lastTranscationDate;
	}

	public void setLastTranscationDate(Date lastTranscationDate) {
		this.lastTranscationDate = lastTranscationDate;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

}
