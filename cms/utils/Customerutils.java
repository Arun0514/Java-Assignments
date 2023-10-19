package utils;

import java.util.ArrayList;
import java.util.Iterator;

import static java.time.LocalDate.parse;

import java.time.LocalDate;
import java.time.Period;

import static com.app.core.ServicePlan.*;
import java.util.List;

import com.app.core.Customer;

import Custom_Exception.CustomerHandlingException;

public class Customerutils {
	public static Customer signIn(String emailID, String pass, List<Customer> customers)
			throws CustomerHandlingException {
		Customer c = new Customer(emailID);

		int index = customers.indexOf(c);

		if (index == -1)
			throw new CustomerHandlingException("invalid EmailID ");

		else if ((customers.get(index)).getPassword().equals(pass)) {

			//System.out.println(customers.get(index).getPassword());
			System.out.println("SignIn successfull");
			return customers.get(index);

		} else

			throw new CustomerHandlingException("Invalid password ");

	}
	
	public static void passwordChange(Customer cust, String newPass)
	{
		cust.setPassword(newPass);
		System.out.println("Password Changed successfully");
		
	}
	//"Enter Customer Details: firstname, lastname,DOB(YYYY-MM-DD), email, password,regamt, plan");
	//GOLD(2000),SILVER(1000),DIAMOND(5000),PLATINUM(10000);
	public static List<Customer> populateCustomer() {
		List<Customer> cList= new ArrayList<Customer>();
		
		cList.add(new Customer("sakshi","shinde",parse("2001-06-27"),"s@gmail.com","s123",5000,DIAMOND,parse("2023-01-17")));
		cList.add(new Customer("arun","chavhan",parse("1999-08-05"),"ac@gmail.com","c123",1000.0,SILVER,parse("2023-02-03")));
		cList.add(new Customer("Vedant","pingale",parse("2002-01-16"),"v@gmail.com","v123",10000.0,GOLD,parse("2023-07-23")));
		cList.add(new Customer("Aarti","patil",parse("2000-07-26"),"ap@gmail.com","p123",2000.0,GOLD,parse("2023-05-22")));
		cList.add(new Customer("aditi","chimanpure",parse("2001-06-27"),"ab@gmail.com","a123",2000.0,GOLD,parse("2023-01-04")));
		return cList;
		
	}
	
	public static void validateRegDate(List <Customer> clist ) {
		LocalDate currentDate= LocalDate.now();
		System.out.println("registration payment of given customer is remaining :");
		for(Customer c: clist) {
			Period p=Period.between(c.getRegDate(),currentDate);
			
			if(p.toTotalMonths()>3) {
				System.out.println(c.getFirstname()+", pending since :"+p.toTotalMonths()+ "");
			}
		}
	}

	public static void removePendingSub(List<Customer> customerList) {
		
		LocalDate currentDate= LocalDate.now();
		Iterator<Customer> itr= customerList.iterator();
		while(itr.hasNext()) {
			Customer c= itr.next();
			Period p=Period.between(c.getRegDate(),currentDate);
			if(p.toTotalMonths()>6) {
				itr.remove();
			}
		}
		
	}
}
	

