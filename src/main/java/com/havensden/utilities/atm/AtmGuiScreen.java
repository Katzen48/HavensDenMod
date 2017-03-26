package com.havensden.utilities.atm;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.havensden.utilities.HavensDenUtilities;
import com.havensden.utilities.gui.GuiUtils;
import com.havensden.utilities.network.PacketDispatcher;
import com.havensden.utilities.packets.AtmMoneyChangePacket;
import com.havensden.utilities.packets.AtmRespondPacket;
import com.havensden.utilities.packets.AtmSessionChangePacket;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;

public class AtmGuiScreen extends GuiScreen
{	
	private String atmsessionid = "";
	
	private String input = "";
	
	private int state = 0;
	
	private GuiTextField textfield;
	
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
		PacketDispatcher.sendToServer(new AtmSessionChangePacket("", 0));
		
		initMainScreen();
		initKeypad();
		initActionButtons();
		
		switch(this.state)
		{
		case 0:
			displayStartMenu();
			break;
		case 1:
			startLogin();
			break;
		case 4:
			displayMainMenu();
			break;
		case 5:
			displayDepositMenu();
			break;
		case 6:
			displayWithDrawMenu();
			break;
		default:
			break;
		}
	}
	
	@Override
	public void updateScreen()
	{
		AtmRespondPacket lPacket = GuiUtils.getAtmRespondPacket();
		
		if(lPacket != null)
		{
			switch(lPacket.getRespondId())
			{
			case 0:
				atmsessionid = lPacket.getParameters();
				
				GuiUtils.setAtmRespondPacket(null);
				break;
			case 1:
				if(lPacket.getParameters().equalsIgnoreCase("s"))
				{
					this.state = 2;
					
					displayMainMenu();
				}
				else
				{
					setFieldText("Can not login");
				}
				
				GuiUtils.setAtmRespondPacket(null);
				break;
			case 2:
				this.mc.displayGuiScreen(null);
				
				GuiUtils.setAtmRespondPacket(null);
				break;
			}
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) 
	{
		drawDefaultBackground();
		drawModel();
		
		textfield.drawTextBox();
		
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
		textfield = new GuiTextField(0, fontRendererObj, width/2-77, height/2-59, 154, 35);
		
		textfield.setTextColor(0xffaa00);
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
	
	@Override
	public void onGuiClosed()
	{
		PacketDispatcher.sendToServer(new AtmSessionChangePacket(atmsessionid, 1));
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton)
	{
		switch(this.state)
		{
		case 0:
			handleStartInput();
			break;
		case 1:
			handleLoginInput();
			break;
		case 4:
			handleMainMenuInput();
			break;
		case 5:
			handleDepositInput();
			break;
		case 6:
			handleWithDrawInput();
			break;
		default:
			break;
			
		}
	}
	
	private void startLogin()
	{
		setFieldText("Enter pin");
		
		this.state = 1;
	}
	
	private void displayStartMenu()
	{
		setFieldText("Press OK to login");
	}
	
	private void displayMainMenu()
	{
		setFieldText("1.Withdraw | 2. Deposit");
		
		this.state = 4;
	}
	
	private void displayWithDrawMenu()
	{
		setFieldText("Withdraw: ");
		
		this.state = 6;
	}
	
	private void displayDepositMenu()
	{
		setFieldText("Deposit: ");
		
		this.state = 5;
	}
	
	private void handleDepositInput()
	{
		for(GuiButton lButton : this.buttonList)
		{
			if(lButton.isMouseOver())
			{				
				if(StringUtils.isNumeric(lButton.displayString))
				{
					this.input += lButton.displayString;
						
					setFieldText("Deposit: " + input);
				}
				else
				{
					if(lButton.displayString.equals("Cancel"))
					{
						logout();
					}
					else if(lButton.displayString.equals("OK"))
					{
						if(StringUtils.isNumeric(input))
						{
							PacketDispatcher.sendToServer(new AtmMoneyChangePacket(atmsessionid, 0-(Integer.parseInt(input))));
						}
					}

				}
			}
		}
	}
	
	private void handleWithDrawInput()
	{
		for(GuiButton lButton : this.buttonList)
		{
			if(lButton.isMouseOver())
			{				
				if(StringUtils.isNumeric(lButton.displayString))
				{
					this.input += lButton.displayString;
						
					setFieldText("Withdraw: " + input);
				}
				else
				{
					if(lButton.displayString.equals("Cancel"))
					{
						logout();
					}
					else if(lButton.displayString.equals("OK"))
					{
						PacketDispatcher.sendToServer(new AtmMoneyChangePacket(atmsessionid, Integer.parseInt(input)));
					}

				}
			}
		}
	}
	
	private void handleStartInput()
	{
		for(GuiButton lButton : this.buttonList)
		{
			if(lButton.isMouseOver())
			{
				if(lButton.displayString.equals("OK"))
				{
					startLogin();
				}
				else if(lButton.equals("Cancel"))
				{
					logout();
				}
			}
		}
	}
	
	private void handleMainMenuInput()
	{
		for(GuiButton lButton : this.buttonList)
		{
			if(lButton.isMouseOver())
			{				
				if(StringUtils.isNumeric(lButton.displayString))
				{
					int lNumber = Integer.parseInt(lButton.displayString);
					
					switch(lNumber)
					{
					case 1:
						displayWithDrawMenu();
						break;
					case 2:
						displayDepositMenu();
						break;
					default:
						break;
					}
				}
				else
				{
					if(lButton.displayString.equals("Cancel"))
					{
						logout();
					}
				}
			}
		}
	}
	
	private void handleLoginInput()
	{
		for(GuiButton lButton : this.buttonList)
		{
			if(lButton.isMouseOver())
			{				
				if(StringUtils.isNumeric(lButton.displayString))
				{
					if(getFieldText().contains("Enter pin"))
					{
						this.input = lButton.displayString;
						
						setFieldText("Pin: " + input);
					}
					else if(getFieldText().contains("Pin: "))
					{
						this.input += lButton.displayString;
						
						setFieldText("Pin: " + input);
					}
				}
				else
				{
					if(lButton.displayString.equals("Cancel"))
					{
						logout();
					}
					else if(lButton.displayString.equals("OK"))
					{
						PacketDispatcher.sendToServer(new AtmSessionChangePacket(atmsessionid, (byte) 2, input));
						
						this.state = 4;
						
						this.input = "";
						
						displayMainMenu();
					}

				}
			}
		}
	}
	
	private void logout()
	{
		PacketDispatcher.sendToServer(new AtmSessionChangePacket(atmsessionid, (byte) 1));
		
		this.state = 2;
		
		this.mc.displayGuiScreen(null);
	}
	
	private void setFieldText(String pText)
	{
		textfield.setText("  " + pText);
	}
	
	public String getFieldText()
	{
		return textfield.getText();
	}
		
	}
