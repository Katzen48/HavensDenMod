package com.havensden.utilities.events;

import com.havensden.utilities.atm.AtmGuiScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientEvents 
{
		/*							//For Testing of the Guiscreen
	@SubscribeEvent
	public void onGuiOpened(GuiOpenEvent pEvent)
	{
		if(pEvent.getGui() instanceof GuiMainMenu)
		{
			pEvent.setGui(new AtmGuiScreen());
		}
	} */
	
	@SubscribeEvent
	public void onInteract(PlayerInteractEvent pEvent)
	{
		if(pEvent.getItemStack().hasDisplayName())
		{
			if(pEvent.getItemStack().getDisplayName().equalsIgnoreCase("atm"))
			{
				Minecraft.getMinecraft().displayGuiScreen(new AtmGuiScreen());
			}
			
		}
	}
	
}
