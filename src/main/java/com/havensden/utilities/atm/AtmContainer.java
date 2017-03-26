package com.havensden.utilities.atm;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AtmContainer extends Container
{
	private World world;
	private BlockPos pos;
	
	public AtmContainer(World pWorld, BlockPos pPos) 
	{
		this.world = pWorld;
		this.pos = pPos;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer pPlayer) 
	{
		if(pPlayer.getDistanceSq(pos) < 1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
