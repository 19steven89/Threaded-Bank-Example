package bankAccount;

public class BankAccount 
{
	private int balance = 100;
	
	public int getBalance()
	{
		return balance;
	}
	
	public void withdraw(int amount)
	{
		//updtae balance by decreasing it by the amount withdrawn
		balance = balance - amount;
	}

}
