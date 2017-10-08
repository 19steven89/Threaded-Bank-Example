package bankAccount;

public class RyanandMonicaJob implements Runnable
{
	private BankAccount account = new BankAccount();
	
	public void run()
	{
		for(int i = 0; i < 10; i++)
		{
			makeWithdrawal(10);
			
			if(account.getBalance() < 0)
			{
				System.out.println("Account Overdrawn!");
			}
		}
	}
	
	//the synchronized key word is used to stop the 2 threads accessing the same method at the 
	//same time. this is very important here as it prevents the Threads from potentially
	//putting the account withdrawn, beacuse without this both threads could 
	//withdraw at the same time by accessing this method, causing the account to go overdrawn
	//this is a concurrency problem prevented with the synchronized keyword
	public synchronized void makeWithdrawal(int amount)
	{
		//check the balance and put the thread to sleep, making a withdrawal once awoken
		//if theres not enough funds, print statement to user
		if(account.getBalance() >= amount)
		{
			System.out.println(Thread.currentThread().getName() + " is about to withdraw");
			
			try
			{
				System.out.println(Thread.currentThread().getName() + " is going to sleep");
				Thread.sleep(500);
			}
			catch(InterruptedException ex){ ex.printStackTrace(); }
			
			System.out.println(Thread.currentThread().getName() + " woke up");
			account.withdraw(amount);
			System.out.println(Thread.currentThread().getName() + " completes the withdrawal");
			System.out.println("£" + account.getBalance() + " left in the account");
		}
		else
		{
			System.out.println("Sorry, not enough funds left for " + Thread.currentThread().getName());
		}
	}
	
	public static void main(String[] args)
	{
		//instantiate the Runnable job
		RyanandMonicaJob theJob = new RyanandMonicaJob();
		//create 2 threads which use the same Runnable job, accessing the same object
		Thread one = new Thread(theJob);
		Thread two = new Thread(theJob);
		one.setName("Ryan");
		two.setName("Monica");
		one.start();
		two.start();
		
	}

}
