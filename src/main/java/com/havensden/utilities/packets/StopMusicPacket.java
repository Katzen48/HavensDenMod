package com.havensden.utilities.packets;

import com.havensden.utilities.sounds.Music;
import com.havensden.utilities.sounds.MusicPlayer;
import com.havensden.utilities.sounds.SoundFactory;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class StopMusicPacket implements IMessage
{
	private String musicFileName;

	public StopMusicPacket() {}
	
	public StopMusicPacket(String pMusicFileName)
	{
		this.musicFileName = pMusicFileName;
	}
	
	@Override
	public void fromBytes(ByteBuf pBuf) 
	{ 
		this.musicFileName = ByteBufUtils.readUTF8String(pBuf).toLowerCase();
	}

	@Override
	public void toBytes(ByteBuf pBuf) 
	{
		ByteBufUtils.writeUTF8String(pBuf, musicFileName);
	}

	public static class Handler extends AbstractClientMessageHandler<StopMusicPacket>
	{

		@Override
		public IMessage handleClientMessage(EntityPlayer pPlayer, StopMusicPacket pMessage, MessageContext pCtx) 
		{
			MusicPlayer.stopMusic(pMessage.musicFileName);
			
			return null;
		}
	}
}
