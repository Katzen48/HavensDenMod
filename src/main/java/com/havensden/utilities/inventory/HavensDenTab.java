package com.havensden.utilities.inventory;

import com.havensden.utilities.manager.Blocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class HavensDenTab extends CreativeTabs
{

	public HavensDenTab() 
	{
		super("Havens Den Utilities");
	}
	
	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Item.getItemFromBlock(Blocks.baseblock));
	}
	
}
