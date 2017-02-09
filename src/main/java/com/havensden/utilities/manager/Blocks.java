package com.havensden.utilities.manager;

import com.havensden.utilities.HavensDenUtilities;
import com.havensden.utilities.blocks.BaseBlock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Blocks 
{	
	public static Block baseblock = new BaseBlock("TestBlock");
	public static Block genericblock = new BaseBlock("GenericBlock");
	
	public static void createBlocks()
	{
		baseblock.setRegistryName("testblock").setCreativeTab(HavensDenUtilities.instance.creativetab);
		GameRegistry.register(baseblock);
		genericblock.setRegistryName("generic").setCreativeTab(HavensDenUtilities.instance.creativetab);
		GameRegistry.register(genericblock);
	}
}
