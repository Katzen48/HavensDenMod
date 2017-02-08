package com.havensden.utilities.manager;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class ModelManager 
{
	private static void registerModel(Block pBlock)
	{
		ModelResourceLocation lLocation = new ModelResourceLocation(pBlock.getRegistryName(), "inventory");
		Item lItem = Item.getItemFromBlock(pBlock);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(lItem, 0, lLocation);
	}
	
	public static void registerModels()
	{
		registerModel(Blocks.baseblock);
		registerModel(Items.baseblock_item);
	}
	
	public static void registerModel(Item lItem)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(lItem, 0, new ModelResourceLocation(lItem.getRegistryName(), "inventory"));
	}
}
