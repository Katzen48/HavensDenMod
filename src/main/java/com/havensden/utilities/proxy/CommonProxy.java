package com.havensden.utilities.proxy;

import com.havensden.utilities.HavensDenUtilities;
import com.havensden.utilities.gui.GUIHandler;
import com.havensden.utilities.network.PacketDispatcher;
import com.havensden.utilities.sounds.SoundFactory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public abstract class CommonProxy 
{
	public void preInit(FMLPreInitializationEvent pEvent)
	{
		com.havensden.utilities.manager.Blocks.createBlocks();
		com.havensden.utilities.manager.Items.createItems();
	}
	
	public void init(FMLInitializationEvent pEvent)
	{
		PacketDispatcher.registerPackets();
		NetworkRegistry.INSTANCE.registerGuiHandler(HavensDenUtilities.instance, new GUIHandler());
	}
	
	public void postInit(FMLPostInitializationEvent pEvent)
	{
		
	}
	
	public EntityPlayer getPlayerEntity(MessageContext pCtx) 
	{
		 return pCtx.getServerHandler().playerEntity;
	}
}
