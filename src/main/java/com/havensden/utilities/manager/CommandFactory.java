package com.havensden.utilities.manager;

import com.havensden.utilities.commands.PlayMusicCommand;
import com.havensden.utilities.commands.StopMusicCommand;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommandFactory 
{
	public static void registerCommands(FMLServerStartingEvent pEvent)
	{
		pEvent.registerServerCommand(new PlayMusicCommand());
		pEvent.registerServerCommand(new StopMusicCommand());
	}
}
