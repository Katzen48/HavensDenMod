package com.havensden.utilities.network;

import com.havensden.utilities.HavensDenUtilities;
import com.havensden.utilities.packets.AbstractBiMessageHandler;
import com.havensden.utilities.packets.AtmMoneyChangePacket;
import com.havensden.utilities.packets.AtmRespondPacket;
import com.havensden.utilities.packets.AtmSessionChangePacket;
import com.havensden.utilities.packets.CreateBankAccountPacket;
import com.havensden.utilities.packets.PlayMusicPacket;
import com.havensden.utilities.packets.StopMusicPacket;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketDispatcher 
{
	private static final SimpleNetworkWrapper dispatcher = NetworkRegistry.INSTANCE.newSimpleChannel(HavensDenUtilities.MODID);

	
	
	//Enter packets to register here
	public static final void registerPackets()
	{
		//*if(FMLCommonHandler.instance().getSide()==Side.CLIENT)
	//*	{
			registerMessage(PlayMusicPacket.Handler.class, PlayMusicPacket.class, (byte) 0, Side.CLIENT);
			registerMessage(StopMusicPacket.Handler.class, StopMusicPacket.class, (byte) 1, Side.CLIENT);
			registerMessage(CreateBankAccountPacket.Handler.class, CreateBankAccountPacket.class, (byte) 2, Side.SERVER);
			registerMessage(AtmSessionChangePacket.Handler.class, AtmSessionChangePacket.class, (byte) 3, Side.SERVER);
			registerMessage(AtmRespondPacket.Handler.class, AtmRespondPacket.class, (byte) 4, Side.CLIENT);
			registerMessage(AtmMoneyChangePacket.Handler.class, AtmMoneyChangePacket.class, (byte) 5, Side.SERVER);
		//*}
		
	}
	
	
	
	private static final void registerMessage(Class pHandlerClass, Class pMessageClass, byte pPacketId)
	{
		if(AbstractBiMessageHandler.class.isAssignableFrom(pHandlerClass))
		{
			PacketDispatcher.registerMessage(pHandlerClass, pMessageClass, pPacketId, Side.CLIENT);
			PacketDispatcher.registerMessage(pHandlerClass, pMessageClass, pPacketId, Side.SERVER);
			
			return;
		}
		throw new IllegalArgumentException("Cannot register " + pHandlerClass.getName() + " on both sides - must extend AbstractBiMessageHandler!");
	}
	
	private static final void registerMessage(Class pHandlerClass, Class pMessageClass, byte pPacketId, Side pSide)
	{
		PacketDispatcher.dispatcher.registerMessage(pHandlerClass, pMessageClass, pPacketId, pSide);
	}
		
	public static final void sendTo(IMessage pMessage, EntityPlayerMP pPlayer)
	{
		PacketDispatcher.dispatcher.sendTo(pMessage, pPlayer);
	}
	
	public static final void sendToAll(IMessage pMessage, NetworkRegistry.TargetPoint pPoint)
	{
		PacketDispatcher.dispatcher.sendToAllAround(pMessage, pPoint);
	}
	
	public static final void sendToAll(IMessage pMessage, int pDimension, double pX, double pY, double pZ, double pRange)
	{
		PacketDispatcher.dispatcher.sendToAllAround(pMessage, new NetworkRegistry.TargetPoint(pDimension, pX, pY, pZ, pRange));
	}
	
	public static final void sendToAll(IMessage pMessage, EntityPlayer pPlayer, double pRange)
	{
		PacketDispatcher.sendToAll(pMessage, pPlayer.world.provider.getDimension(), pPlayer.posX, pPlayer.posY, pPlayer.posZ, pRange);
	}
	
	public static final void sendToDimension(IMessage pMessage, int pDimensionId)
	{
		PacketDispatcher.dispatcher.sendToDimension(pMessage, pDimensionId);
	}
	
	public static final void sendToServer(IMessage pMessage)
	{
		PacketDispatcher.dispatcher.sendToServer(pMessage);
	}
}
