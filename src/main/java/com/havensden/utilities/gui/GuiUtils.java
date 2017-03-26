package com.havensden.utilities.gui;

import java.util.ArrayList;

import com.havensden.utilities.atm.AtmGuiScreen;
import com.havensden.utilities.packets.AtmRespondPacket;

import net.minecraft.client.Minecraft;

public class GuiUtils 
{
	private static AtmRespondPacket atmrespondpacket = null;
	
	public static void setAtmRespondPacket(AtmRespondPacket pPacket)
	{
		atmrespondpacket = pPacket;
	}
	
	public static AtmRespondPacket getAtmRespondPacket()
	{
		return atmrespondpacket;
	}
	
	public static void openAtmGui()
	{
		Minecraft.getMinecraft().displayGuiScreen(new AtmGuiScreen());
	}
}
