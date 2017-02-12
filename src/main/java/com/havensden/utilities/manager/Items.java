package com.havensden.utilities.manager;

import com.havensden.utilities.HavensDenUtilities;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Items 
{
	public static ItemBlock baseblock_item = new ItemBlock(Blocks.baseblock);
	public static ItemBlock genericblock_item = new ItemBlock(Blocks.genericblock);
	public static ItemBlock cautiontopblock_item = new ItemBlock(Blocks.cautiontopblock);
	
	public static void createItems()
	{
		baseblock_item.setUnlocalizedName(Blocks.baseblock.getUnlocalizedName()).setRegistryName("testblock_item");
		GameRegistry.register(baseblock_item);
		genericblock_item.setUnlocalizedName(Blocks.genericblock.getUnlocalizedName()).setRegistryName("genericblock_item");
		GameRegistry.register(genericblock_item);
		cautiontopblock_item.setUnlocalizedName(Blocks.cautiontopblock.getUnlocalizedName()).setRegistryName("cautiontopblock_item");
		GameRegistry.register(cautiontopblock_item);
	}
}
