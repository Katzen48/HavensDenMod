package com.havensden.utilities.money;

import java.util.UUID;

import com.havensden.utilities.HavensDenUtilities;
import com.kamildanak.minecraft.enderpay.api.EnderPayApi;
import com.kamildanak.minecraft.enderpay.api.InsufficientCreditException;
import com.kamildanak.minecraft.enderpay.api.NoSuchAccountException;
import com.kamildanak.minecraft.enderpay.api.NotABanknoteException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class MoneyHandler 
{
	private EnderPayApi api;
	
	public MoneyHandler()
	{
		api = new EnderPayApi();
	}
	
	public long getBalance(UUID pUUID)
	{	
		try 
		{
			return api.getBalance(pUUID);
		} 
		catch (NoSuchAccountException e) 
		{
			e.printStackTrace();
		}
		
		return 0L;
	}
	
	public void setBalance(UUID pUUID, long pBalance)
	{
		long lCurrentBalance = getBalance(pUUID);
		
		if(lCurrentBalance == pBalance)
		{
			return;
		}
		else if(lCurrentBalance < pBalance)
		{
			addMoney(pUUID, pBalance-lCurrentBalance);
		}
		else
		{
			removeMoney(pUUID, pBalance);
		}
	}
	
	public void addMoney(UUID pUUID, long pBalance)
	{
		try 
		{
			api.addToBalance(pUUID, pBalance);
		} 
		catch (NoSuchAccountException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void removeMoney(UUID pUUID, long pBalance)
	{
		try 
		{
			api.takeFromBalance(pUUID, pBalance);
		} 
		catch (InsufficientCreditException e) 
		{
			e.printStackTrace();
		} 
		catch (NoSuchAccountException e) 
		{
			e.printStackTrace();
		}
	}
	
	public ItemStack getBanknote(long pBalance)
	{
		return api.getBanknote(pBalance);
	}
	
	public long getBanknoteValue(ItemStack pItem)
	{
		try 
		{
			return api.getBanknoteCurrentValue(pItem);
		} 
		catch (NotABanknoteException e) 
		{
			e.printStackTrace();
		}
		
		return 0L;
	}
	
	public void givePlayerBanknote(long pAmount, EntityPlayer pPlayer)
	{
		UUID lUUID = pPlayer.getUniqueID();
		
		long lCurrentBalance = getBalance(lUUID);
		
		if(lCurrentBalance >= pAmount)
		{
			removeMoney(lUUID, pAmount);
			pPlayer.inventory.addItemStackToInventory(getBanknote(pAmount));
		}
		else
		{
			return;
		}
	}
}
