package com.havensden.utilities.commands;

import java.awt.TextComponent;

import com.havensden.utilities.network.PacketDispatcher;
import com.havensden.utilities.packets.PlayMusicPacket;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class PlayMusicCommand extends CommandBase
{

	@Override
	public String getName() {
		return "playmusic";
	}

	@Override
	public String getUsage(ICommandSender pSender) 
	{
		return "/playmusic <player> <filename> (loop)";
	}
	

	@Override
	public int getRequiredPermissionLevel() 
	{
		return 4;
	}

	@Override
	public void execute(MinecraftServer pServer, ICommandSender pSender, String[] pArgs) throws CommandException 
	{
		if(pArgs.length > 1)
		{
			EntityPlayerMP lPlayer = pServer.getPlayerList().getPlayerByUsername(pArgs[0]);
			
			if(lPlayer != null)
			{
				String lMusicFileName = pArgs[1];
				boolean lLoop = false;
				
				if(pArgs.length > 2 && pArgs[2].equalsIgnoreCase("loop"))
				{
					lLoop = true;
				}
				
				PacketDispatcher.sendTo(new PlayMusicPacket(lMusicFileName, lLoop), lPlayer);
			}
		}
		else
		{
			pSender.sendMessage(new TextComponentString(getUsage(pSender)));
		}
	}
}
