package com.havensden.utilities.atm;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;

public class AtmGuiScreen extends GuiScreen
{	
	private GuiLabel label;
	
	private GuiButton button1;
	private GuiButton button2;
	private GuiButton button3;
	private GuiButton button4;
	private GuiButton button5;
	private GuiButton button6;
	private GuiButton button7;
	private GuiButton button8;
	private GuiButton button9;
	private GuiButton button0;
	
	private GuiButton buttonCancel;
	private GuiButton buttonOk;
	
	private String texturelocation = "havensden:textures/gui/atmgui.png";
	
	private final int xSize = 176;
	private final int ySize = 176;
	
	@Override
	public void initGui()
	{	
		initMainScreen();
		initKeypad();
		initActionButtons();
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) 
	{
		drawDefaultBackground();
		drawModel();
		
		label.drawLabel(mc, mouseX, mouseY);
		
		super.drawScreen(mouseX, mouseY, partialTicks);

	}
	
	public void drawModel()
	{
		this.mc.getTextureManager().bindTexture(new ResourceLocation(texturelocation));
		
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
	
	public void initMainScreen()
	{
		this.labelList.clear();
		
		label = new GuiLabel(fontRendererObj, 0, width/2-76, height/2-60, 154, 40, 0xffaa00);
		
		label.addLine("Default Message");
		
		this.labelList.add(label);
	}
	
	public void initKeypad()
	{
		this.buttonList.clear();
		
		int startX = width/2-26;
		int startY= height/2+6;
		
		button7 = new GuiButton(7, startX, startY, 16, 16, "7");
		button8 = new GuiButton(8, startX+18, startY, 16, 16, "8");
		button9 = new GuiButton(9, startX+36, startY, 16, 16, "9");
		button4 = new GuiButton(4, startX, startY+18, 16, 16, "4");
		button5 = new GuiButton(5, startX+18, startY+18, 16, 16, "5");
		button6 = new GuiButton(6, startX+36, startY+18, 16, 16, "6");
		button1 = new GuiButton(1, startX, startY+36, 16, 16, "1");
		button2 = new GuiButton(2, startX+18, startY+36, 16, 16, "2");
		button3 = new GuiButton(3, startX+36, startY+36, 16, 16, "3");
		button0 = new GuiButton(0, startX+18, startY+54, 16, 16, "0");
		
		this.buttonList.add(button7);
		this.buttonList.add(button8);
		this.buttonList.add(button9);
		this.buttonList.add(button4);
		this.buttonList.add(button5);
		this.buttonList.add(button6);
		this.buttonList.add(button1);
		this.buttonList.add(button2);
		this.buttonList.add(button3);
		this.buttonList.add(button0);
	}
	
	public void initActionButtons()
	{
		buttonOk = new GuiButton(12, width/2+29, height/2+39, 52, 20, "OK");
		buttonCancel = new GuiButton(11, width/2-81, height/2+39, 52, 20, "Cancel");
		
		this.buttonList.add(buttonOk);
		this.buttonList.add(buttonCancel);
	}
	
	}
