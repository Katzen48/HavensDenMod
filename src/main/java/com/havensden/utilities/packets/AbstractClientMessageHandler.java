package com.havensden.utilities.packets;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public abstract class AbstractClientMessageHandler<T extends IMessage> extends AbstractMessageHandler<T>
{
	public final IMessage handleServerMessage(EntityPlayer pPlayer, T pMessage, MessageContext pCtx) 
	{
		return null;
	}
}
