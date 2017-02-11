package com.havensden.utilities.network;

import com.havensden.utilities.HavensDenUtilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class AbstractMessageHandler<T extends IMessage> implements IMessageHandler<T, IMessage>
{
	@SideOnly(Side.CLIENT)
	public abstract IMessage handleClientMessage(EntityPlayer pPlayer, T pMessage, MessageContext pCtx);
	
	public abstract IMessage handleServerMessage(EntityPlayer pClayer, T pMessage, MessageContext pCtx);

	@Override
	public IMessage onMessage(T pMessage, MessageContext pCtx) 
	{
		if (pCtx.side.isClient()) 
		{
			return handleClientMessage(HavensDenUtilities.proxy.getPlayerEntity(pCtx), pMessage, pCtx);
		} 
		else 
		{
			return handleServerMessage(HavensDenUtilities.proxy.getPlayerEntity(pCtx), pMessage, pCtx);
		}
	}
}
