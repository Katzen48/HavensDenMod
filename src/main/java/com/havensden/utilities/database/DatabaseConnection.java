package com.havensden.utilities.database;

public abstract class DatabaseConnection 
{
	public abstract void connect();
	
	public abstract long getBalance(String pPlayer);
	
	public abstract boolean setBalance(String pPlayer, long pBalance);
	
	public abstract boolean hasAccount(String pPlayer);
	
	public abstract void getAllBankaccounts();
	
	public abstract boolean createBankaccount(String pPlayer, String pPin);
}
