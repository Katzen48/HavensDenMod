package com.havensden.utilities.commands;

import com.havensden.utilities.money.BankSystem;

import ibxm.Player;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class BankAccountCommand extends CommandBase
{

	@Override
	public String getName() 
	{
		return "bankaccount";
	}

	@Override
	public String getUsage(ICommandSender pSender) 
	{
		return "/bankaccount <subccommand>";
	}
	
	@Override
	public int getRequiredPermissionLevel() 
	{
		return 4;
	}

	@Override
	public void execute(MinecraftServer pServer, ICommandSender pSender, String[] pArgs) throws CommandException 
	{
		if(pArgs.length>0)
		{
			if(pArgs[0].equalsIgnoreCase("create"))
			{
				if(pArgs.length >1)
				{
					EntityPlayer lPlayer = (EntityPlayer) pSender;
					
					String lSessionid = BankSystem.createSession();
					
					if(!BankSystem.getSession(lSessionid).hasAccount(lPlayer.getUniqueID()))
					{
						BankSystem.getSession(lSessionid).createAccount(lPlayer.getUniqueID(), pArgs[1]);
						
						pSender.sendMessage(new TextComponentString("Your Bankaccount has beed created. Please remember your password."));
					}
					else
					{
						pSender.sendMessage(new TextComponentString("You already have a Bankaccount"));
					}
					
					BankSystem.removeSession(lSessionid);
				}
				else
				{
					pSender.sendMessage(new TextComponentString("You have to enter a pin for your new Bankaccount"));
				}
			}
		}
		
	}

}
