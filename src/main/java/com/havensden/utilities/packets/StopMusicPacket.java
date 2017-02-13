package com.havensden.utilities.packets;

import com.havensden.utilities.HavensDenUtilities;
import com.havensden.utilities.sounds.Music;
import com.havensden.utilities.sounds.SoundFactory;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.Sound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

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
		this.musicFileName = ByteBufUtils.readUTF8String(pBuf);
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
			if(!pMessage.musicFileName.equals("null"))
			{
				Music lMusic = SoundFactory.getMusic(pMessage.musicFileName);
				
				if(lMusic != null)
				{
					Minecraft.getMinecraft().getSoundHandler().stopSound(lMusic);
				}	
			}
			else
			{
				Music[] lMusics = SoundFactory.getMusics();
				
				for(Music lMusic : lMusics)
				{
					Minecraft.getMinecraft().getSoundHandler().stopSound(lMusic);
				}
			}
			
			return null;
		}
	}
}
