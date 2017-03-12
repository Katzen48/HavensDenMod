package com.havensden.utilities.money;

import java.util.UUID;

import com.havensden.utilities.HavensDenUtilities;

public class BankAccount 
{
	private long balance;
	private UUID owner;
	private String pin;
	private boolean active;
	
	protected BankAccount(long pBalance, UUID pOwner, String pPin, boolean pActive)
	{
		this.balance = pBalance;
		this.owner = pOwner;
		this.pin = pPin;
		this.active = pActive;
	}
	
	protected BankAccount(UUID pOwner, String pPin)
	{
		this(0L, pOwner, pPin, true);
	}

	public long getBalance() 
	{
		return balance;
	}

	public void setBalance(long balance) 
	{
		this.balance = balance;
		
		HavensDenUtilities.instance.dbconnection.setBalance(owner.toString(), balance);
	}

	public UUID getOwner() 
	{
		return owner;
	}

	public void setOwner(UUID owner) 
	{
		this.owner = owner;
	}

	public String getPin() 
	{
		return pin;
	}

	public void setPin(String pin) 
	{
		this.pin = pin;
	}

	public boolean isActive() 
	{
		return active;
	}

	public void setActive(boolean active) 
	{
		this.active = active;
	}
	
	@Override
	public boolean equals(Object pAccount)
	{
		if(pAccount instanceof BankAccount)
		{
			BankAccount lAccount = (BankAccount) pAccount;
			
			if(lAccount.getOwner().equals(getOwner()))
			{
				return true;
			}
		}
		
		return false;
	}
}
