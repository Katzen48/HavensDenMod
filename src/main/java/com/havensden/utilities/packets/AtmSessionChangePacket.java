package com.havensden.utilities.packets;

import java.util.UUID;

import com.havensden.utilities.money.BankSystem;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class AtmSessionChangePacket implements IMessage
{
	private String atmsessionid;
	private int action;
	private String parameters = "";
	
	public AtmSessionChangePacket() {}
	
	public AtmSessionChangePacket(String pAtmsessionid, int pAction)
	{
		this.atmsessionid = pAtmsessionid;
		this.action = pAction;
	}
	
	public AtmSessionChangePacket(String pAtmsessionid, int pAction, String pParameters)
	{
		this.atmsessionid = pAtmsessionid;
		this.action = pAction;
		this.parameters = pParameters;
	}
	
	@Override
	public void fromBytes(ByteBuf pBuf) 
	{
		this.atmsessionid = ByteBufUtils.readUTF8String(pBuf);
		this.action = pBuf.readInt();
		this.parameters = ByteBufUtils.readUTF8String(pBuf);
	}

	@Override
	public void toBytes(ByteBuf pBuf) 
	{
		ByteBufUtils.writeUTF8String(pBuf, atmsessionid);
		pBuf.writeInt(action);
		ByteBufUtils.writeUTF8String(pBuf, parameters);
	}

	public static class Handler extends AbstractServerMessageHandler<AtmSessionChangePacket>
	{

		@Override
		public IMessage handleServerMessage(EntityPlayer pPlayer, AtmSessionChangePacket pMessage,
				MessageContext pCtx) 
		{
			switch(pMessage.action)
			{
			case 0:
				return new AtmRespondPacket((byte) 0, BankSystem.createSession());
			case 1:
				BankSystem.removeSession(pMessage.atmsessionid);
				break;
			case 2:
				String lUuid = pPlayer.getUniqueID().toString();
				String lPin = pMessage.parameters;
				
				if(BankSystem.getSession(pMessage.atmsessionid).login(UUID.fromString(lUuid), lPin))
				{
					return new AtmRespondPacket((byte) 1, "s");
				}
				else
				{
					return new AtmRespondPacket((byte) 1, "f");
				}
			}
			
			return null;
		}
		
	}
}
