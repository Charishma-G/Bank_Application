package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Customer> customers;
private static Bank bank;
    private Bank() {
        customers = new ArrayList<Customer>();
        
    }
    
    public static  Bank getInstance(){
    	 if(bank==null){
    		 bank = new Bank();
    	 }
    	 return bank;
    }

    public boolean addCustomer(Customer customer) {
    	boolean custFlag=findCustomer(customer);
    	if(custFlag){
    	getCustomers().add(customer);
    	}
    	
    	return custFlag;
    	
    } 
    
    private boolean findCustomer(Customer customer){
    	if(getCustomers()!=null&&getCustomers().size()>0){
    		
    		for(Customer cust : getCustomers()){
    			if(customer.getSsn().equals(cust.getSsn())){
    				return false;
    			}
    		}
    		//return true;
    	}
    	return true;
    }

    public String customerSummary() {
        String summary = "Customer Summary";
        for (Customer c : getCustomers())
            summary += "\n - " + c.getName() + " (" + format(c.getNumberOfAccounts(), "account") + ")";
        return summary;
    }

    //Make sure correct plural of word is created based on the number passed in:
    //If number passed in is 1 just return the word otherwise add an 's' at the end
    private String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }

    public double totalInterestPaid() {
        double total = 0;
        for(Customer c: getCustomers())
            total += c.totalInterestEarned();
        
        return total;
    }

    public String getFirstCustomer() {
        try {
                  if(getCustomers().size()>0){
                	  return  getCustomers().get(0).getName();
                  }
            return "No Customers Found";
        } catch (Exception e){
            e.printStackTrace();
            return "Error";
        }
    }

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}    
}
