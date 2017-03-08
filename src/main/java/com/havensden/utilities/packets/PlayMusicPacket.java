package com.havensden.utilities.packets;

import com.havensden.utilities.HavensDenUtilities;
import com.havensden.utilities.sounds.Music;
import com.havensden.utilities.sounds.MusicPlayer;
import com.havensden.utilities.sounds.SoundFactory;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PlayMusicPacket implements IMessage
{
	private String musicFileName;
	private boolean loop;

	public PlayMusicPacket() {}
	
	public PlayMusicPacket(String pMusicFileName, boolean pLoop)
	{
		this.musicFileName = pMusicFileName;
		this.loop = pLoop;
	}
	
	@Override
	public void fromBytes(ByteBuf pBuf) 
	{
		this.musicFileName = ByteBufUtils.readUTF8String(pBuf).toLowerCase();
		this.loop = pBuf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf pBuf) 
	{
		ByteBufUtils.writeUTF8String(pBuf, musicFileName);
		pBuf.writeBoolean(loop);
	}

	public static class Handler extends AbstractClientMessageHandler<PlayMusicPacket>
	{

		@Override
		public IMessage handleClientMessage(EntityPlayer pPlayer, PlayMusicPacket pMessage, MessageContext pCtx) 
		{		
			MusicPlayer.playMusic(pMessage.musicFileName, pMessage.loop);
			
			return null;
		}
	}
}
