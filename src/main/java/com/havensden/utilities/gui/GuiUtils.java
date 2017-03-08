package com.havensden.utilities.gui;

import java.util.ArrayList;

import com.havensden.utilities.packets.AtmRespondPacket;

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
}
