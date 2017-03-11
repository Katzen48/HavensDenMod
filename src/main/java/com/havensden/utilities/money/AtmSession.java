package com.havensden.utilities.money;

import java.util.UUID;

import com.havensden.utilities.HavensDenUtilities;

public class AtmSession 
{
	private UUID uuid = null;
	
	private String sessionid = null;
	
	protected AtmSession(String pSessionsId) 
	{
		this.sessionid = pSessionsId;
	}
	
	public boolean login(UUID pUUID, String pPin)
	{
		if(BankSystem.isPinValid(pUUID, pPin))
		{
			uuid = pUUID;
			
			return true;
		}
		
		return false;
	}
	
	public boolean deposit(long pAmount)
	{
		if(uuid != null)
		{
			if(BankSystem.transferToBank(uuid, pAmount))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		return false;
	}
	
	public boolean withdraw(long pAmount)
	{
		if(uuid != null)
		{
			if(BankSystem.transferFromBank(uuid, pAmount))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		return false;
	}
	
	public boolean createAccount(long pBalance, UUID pOwner, String pPin, boolean pActive)
	{
		return BankSystem.addBankaccount(pBalance, pOwner, pPin, pActive);
	}
	
	public boolean createAccount(UUID pOwner, String pPin)
	{
		return createAccount(0, pOwner, pPin, true);
	}
	
	public String getSessionID()
	{
		return sessionid;
	}
	
	public boolean hasAccount(UUID pUUID)
	{
		return BankSystem.hasAccount(pUUID);
	}
}
