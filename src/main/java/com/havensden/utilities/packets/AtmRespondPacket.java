package com.havensden.utilities.packets;

import com.havensden.utilities.gui.GuiUtils;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class AtmRespondPacket implements IMessage
{
	private byte respondId;
	private String parameters;
	
	public AtmRespondPacket() {}
	
	public AtmRespondPacket(byte pRespondId, String pParameters)
	{
		this.respondId = pRespondId;
		this.parameters = pParameters;
	}
	
	@Override
	public void fromBytes(ByteBuf pBuf) 
	{
		this.respondId = pBuf.readByte();
		this.parameters = ByteBufUtils.readUTF8String(pBuf);
	}

	@Override
	public void toBytes(ByteBuf pBuf) 
	{
		pBuf.writeByte(respondId);
		ByteBufUtils.writeUTF8String(pBuf, parameters);		
	}
	
	public byte getRespondId()
	{
		return this.respondId;
	}
	
	public String getParameters()
	{
		return this.parameters;
	}
	
	public static class Handler extends AbstractClientMessageHandler<AtmRespondPacket>
	{

		@Override
		public IMessage handleClientMessage(EntityPlayer pPlayer, AtmRespondPacket pMessage, MessageContext pCtx) 
		{
			GuiUtils.setAtmRespondPacket(pMessage);
			
			return null;
		}
		
	}

}
