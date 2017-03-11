package com.havensden.utilities.packets;

import java.util.UUID;

import com.havensden.utilities.money.AtmSession;
import com.havensden.utilities.money.BankSystem;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CreateBankAccountPacket implements IMessage
{
	private UUID owner;
	private String pin;
	private String atmsessionid;
	
	public CreateBankAccountPacket() {}
	
	public CreateBankAccountPacket(UUID pOwner, String pPin, String pAtmsessionid)
	{
		this.owner = pOwner;
		this.pin = pPin;
		this.atmsessionid = pAtmsessionid;
	}
	
	@Override
	public void fromBytes(ByteBuf pBuf) 
	{
		this.owner = UUID.fromString(ByteBufUtils.readUTF8String(pBuf));
		this.pin = ByteBufUtils.readUTF8String(pBuf);
		this.atmsessionid = ByteBufUtils.readUTF8String(pBuf);
	}

	@Override
	public void toBytes(ByteBuf pBuf) 
	{
		ByteBufUtils.writeUTF8String(pBuf, owner.toString());
		ByteBufUtils.writeUTF8String(pBuf, pin);
		ByteBufUtils.writeUTF8String(pBuf, atmsessionid);
	}

	public static class Handler extends AbstractServerMessageHandler<CreateBankAccountPacket>
	{

		@Override
		public IMessage handleServerMessage(EntityPlayer pPlayer, CreateBankAccountPacket pMessage,
				MessageContext pCtx) 
		{
			BankSystem.getSession(pMessage.atmsessionid).createAccount(pMessage.owner, pMessage.pin);
			
			return null;
		}
		
	}
}
