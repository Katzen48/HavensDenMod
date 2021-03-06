package com.havensden.utilities.money;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import com.havensden.utilities.HavensDenUtilities;

public class BankSystem 
{
	private static HashMap<String, AtmSession> atmsessions = new HashMap<String, AtmSession>();
	
	private static ArrayList<BankAccount> bankaccounts = new ArrayList<BankAccount>();
	
	protected BankSystem() {}
	
	protected static boolean addBankaccount(long pBalance, UUID pOwner, String pPin, boolean pActive)
	{
		if(!hasAccount(pOwner))
		{
			BankAccount lAccount = new BankAccount(pBalance, pOwner, pPin, pActive);
			
			bankaccounts.add(lAccount);
			
			HavensDenUtilities.instance.dbconnection.createBankaccount(pOwner.toString(), pPin);
			
			return true;
		}
		else
		{
			return false;
		}
				
	}
	
	protected static boolean hasAccount(UUID pUUID)
	{
		if(getAccount(pUUID) != null)
		{
			return true;
		}
		
		return false;
	}
	
	private static BankAccount getAccount(UUID pUUID)
	{
		for(BankAccount lAccount : bankaccounts)
		{
			if(lAccount.getOwner().equals(pUUID))
			{
				return lAccount;
			}
		}
		
		return null;
	}
	
	protected static long getBalance(UUID pUUID)
	{
		return getAccount(pUUID).getBalance();
	}
	
	protected static boolean transferToBank(UUID pUUID, long pAmount)
	{
		if(HavensDenUtilities.instance.moneyhandler.getBalance(pUUID) >= pAmount)
		{
			BankAccount lAccount = getAccount(pUUID);
			
			if(lAccount != null)
			{
				lAccount.setBalance(lAccount.getBalance() + pAmount);
				
				HavensDenUtilities.instance.moneyhandler.removeMoney(pUUID, pAmount);
				
				return true;
			}
			
			return false;
		}
		else
		{
			return false;
		}
	}
	
	protected static boolean transferFromBank(UUID pUUID, long pAmount)
	{
		BankAccount lAccount = getAccount(pUUID);
		
		if(lAccount != null)
		{
			if(lAccount.getBalance() >= pAmount)
			{
				HavensDenUtilities.instance.moneyhandler.addMoney(pUUID, pAmount);
				
				lAccount.setBalance(lAccount.getBalance() - pAmount);
			
				return true;
			}
			
			return false;
		}
		else
		{
			return false;
		}
	}
	
	protected static boolean isPinValid(UUID pUUID, String pPin)
	{
		BankAccount lAccount = getAccount(pUUID);
		
		if(lAccount != null)
		{
			if(lAccount.getPin().equals(pPin))
			{
				return true;
			}
			
			return false;
		}
		else
		{
			return false;
		}
	}
	
	public static String createSession()
	{
		UUID lSessionId = UUID.randomUUID();
		
		AtmSession lSession = new AtmSession(lSessionId.toString());
		
		atmsessions.put(lSessionId.toString(), lSession);
		
		return lSession.getSessionID();
	}
	
	public static AtmSession getSession(String pSessionId)
	{
		return atmsessions.get(pSessionId);
	}
	
	public static void removeSession(String pSessionId)
	{
		atmsessions.remove(pSessionId);
	}
	
	public static void loadAccounts()
	{
		HavensDenUtilities.instance.dbconnection.getAllBankaccounts();
	}
}
