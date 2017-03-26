package com.havensden.utilities.gui;

import com.havensden.utilities.atm.AtmContainer;
import com.havensden.utilities.atm.AtmGuiScreen;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GUIHandler implements IGuiHandler
{
	public static final int atmguiid = 0;
	
	@Override
	public Object getServerGuiElement(int pID, EntityPlayer pPlayer, World pWorld, int pX, int pY, int pZ) 
	{
		if(pID == atmguiid)
		{
			return new AtmContainer(pWorld, new BlockPos(pX, pY, pZ));
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int pID, EntityPlayer pPlayer, World pWorld, int pX, int pY, int pZ) 
	{
		if(pID == atmguiid)
		{
			return new AtmGuiScreen();
		}
		
		return null;
	}
	
}
