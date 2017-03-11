package com.havensden.utilities.packets;

import com.havensden.utilities.money.AtmSession;
import com.havensden.utilities.money.BankSystem;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class AtmMoneyChangePacket implements IMessage
{
	private String atmsessionid;
	private long amount;
	
	public AtmMoneyChangePacket() {}
	
	public AtmMoneyChangePacket(String pAtmsessionid, long pAmount)
	{
		this.atmsessionid = pAtmsessionid;
		this.amount = pAmount;
	}
	
	@Override
	public void fromBytes(ByteBuf pBuf) 
	{
		this.atmsessionid = ByteBufUtils.readUTF8String(pBuf);
		this.amount = pBuf.readLong();
	}

	@Override
	public void toBytes(ByteBuf pBuf) 
	{
		ByteBufUtils.writeUTF8String(pBuf, atmsessionid);
		pBuf.writeLong(amount);
	}

	public static class Handler extends AbstractServerMessageHandler<AtmMoneyChangePacket>
	{

		@Override
		public IMessage handleServerMessage(EntityPlayer pPlayer, AtmMoneyChangePacket pMessage, MessageContext pCtx) 
		{
			AtmSession lSession = BankSystem.getSession(pMessage.atmsessionid);
			
			if(pMessage.amount < 0)
			{
				if(lSession.deposit(pMessage.amount*(-1)))
				{
					pPlayer.sendMessage(new TextComponentString("You deposited " + pMessage.amount*(-1)));
					
					return new AtmRespondPacket((byte) 2, "s");
				}
				else
				{
					pPlayer.sendMessage(new TextComponentString("Couldn't deposit " + pMessage.amount*(-1)));
					
					return new AtmRespondPacket((byte) 2, "f");
				}
			}
			else
			{
				if(lSession.withdraw(pMessage.amount))
				{
					pPlayer.sendMessage(new TextComponentString("You withdrawed " + pMessage.amount));
					
					return new AtmRespondPacket((byte) 2, "s");
				}
				else
				{
					pPlayer.sendMessage(new TextComponentString("Couldn't withdraw " + pMessage.amount));
					
					return new AtmRespondPacket((byte) 2, "f");
				}
			}
		}
		
	}
}
