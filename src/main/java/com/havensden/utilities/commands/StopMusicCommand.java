package com.havensden.utilities.commands;

import java.awt.TextComponent;

import com.havensden.utilities.network.PacketDispatcher;
import com.havensden.utilities.packets.StopMusicPacket;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class StopMusicCommand extends CommandBase
{

	@Override
	public String getName() {
		return "stopmusic";
	}

	@Override
	public String getUsage(ICommandSender pSender) 
	{
		return "/stopmusic <player> (filename)";
	}
	

	@Override
	public int getRequiredPermissionLevel() 
	{
		return 4;
	}

	@Override
	public void execute(MinecraftServer pServer, ICommandSender pSender, String[] pArgs) throws CommandException 
	{		
		if(pArgs.length >= 1)
		{
			EntityPlayerMP lPlayer = pServer.getPlayerList().getPlayerByUsername(pArgs[0]);
			
			if(lPlayer != null)
			{			
				if(pArgs.length < 2)
				{				
					PacketDispatcher.sendTo(new StopMusicPacket("none"), lPlayer);
				}
				else
				{
					PacketDispatcher.sendTo(new StopMusicPacket(pArgs[1]), lPlayer);
				}
			}
		}
		else
		{
			pSender.sendMessage(new TextComponentString(getUsage(pSender)));
		}
	}
}
