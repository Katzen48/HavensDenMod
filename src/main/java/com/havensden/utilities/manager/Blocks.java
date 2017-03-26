package com.havensden.utilities.manager;

import com.havensden.utilities.HavensDenUtilities;
import com.havensden.utilities.blocks.AtmBlock;
import com.havensden.utilities.blocks.BaseBlock;
import com.havensden.utilities.blocks.CautionTopBlock;
import com.havensden.utilities.blocks.GenericBlock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Blocks 
{	
	public static Block baseblock = new BaseBlock("TestBlock");
	public static Block genericblock = new GenericBlock();
	public static Block cautiontopblock = new CautionTopBlock();
	public static Block atmblock = new AtmBlock();
	
	public static void createBlocks()
	{
		baseblock.setRegistryName("testblock").setCreativeTab(HavensDenUtilities.instance.creativetab);
		GameRegistry.register(baseblock);
		genericblock.setRegistryName("genericblock").setCreativeTab(HavensDenUtilities.instance.creativetab);
		GameRegistry.register(genericblock);
		cautiontopblock.setRegistryName("cautiontopblock").setCreativeTab(HavensDenUtilities.instance.creativetab);
		GameRegistry.register(cautiontopblock);
		atmblock.setRegistryName("atmblock").setCreativeTab(HavensDenUtilities.instance.creativetab);
		GameRegistry.register(atmblock);
		
	}
}
