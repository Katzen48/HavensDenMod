package com.havensden.utilities.packets;

import java.lang.reflect.Field;

import com.havensden.utilities.HavensDenUtilities;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound.AttenuationType;
import net.minecraft.client.audio.PositionedSound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.Sound;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

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
			if(pMessage.musicFileName != null)
			{
				SoundEvent lSoundEvent;
				ResourceLocation lLocation = new ResourceLocation(HavensDenUtilities.MODID + ":" + pMessage.musicFileName);
				
				
				if((lSoundEvent = SoundEvent.REGISTRY.getObject(lLocation)) != null)
				{					
					if(pMessage.loop)
					{
						pPlayer.playSound(lSoundEvent, 100, 1F);
					}
					else
					{
						pPlayer.playSound(lSoundEvent, 100, 1F);
					}
				}
			}
			
			return null;
		}
	}
}
