package com.havensden.utilities.proxy;

import com.havensden.utilities.manager.ModelManager;
import com.havensden.utilities.sounds.SoundFactory;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ClientProxy extends CommonProxy
{

	@Override
	public void preInit(FMLPreInitializationEvent pEvent) 
	{
		super.preInit(pEvent);
	}

	@Override
	public void init(FMLInitializationEvent pEvent) 
	{
		super.init(pEvent);
		ModelManager.registerModels();
	}

	@Override
	public void postInit(FMLPostInitializationEvent pEvent) 
	{
		super.postInit(pEvent);
	}

	@Override
	public EntityPlayer getPlayerEntity(MessageContext pCtx) 
	{
		return (pCtx.side.isClient() ? Minecraft.getMinecraft().player : super.getPlayerEntity(pCtx));
	}
}
