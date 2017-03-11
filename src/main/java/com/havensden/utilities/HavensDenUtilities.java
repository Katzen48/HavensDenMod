package com.havensden.utilities;

import com.havensden.utilities.inventory.HavensDenTab;
import com.havensden.utilities.manager.CommandFactory;
import com.havensden.utilities.money.MoneyHandler;
import com.havensden.utilities.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = HavensDenUtilities.MODID, name = HavensDenUtilities.MODNAME, version = HavensDenUtilities.VERSION)
public class HavensDenUtilities
{
    public static final String MODID = "havensden";
    public static final String MODNAME = "Havens Den Utilities";
    public static final String VERSION = "0.1.4-A";
    
    public HavensDenTab creativetab;
    
    public MoneyHandler moneyhandler;
    
    @Instance
    public static HavensDenUtilities instance = new HavensDenUtilities();
    
    @SidedProxy(clientSide="com.havensden.utilities.proxy.ClientProxy", serverSide="com.havensden.utilities.proxy.ServerProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent pEvent) 
    {
        this.creativetab = new HavensDenTab();
    	this.proxy.preInit(pEvent);
    }

    @EventHandler
    public void init(FMLInitializationEvent pEvent)
    {
        this.proxy.init(pEvent);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent pEvent) 
    {
        this.proxy.postInit(pEvent);
    }
    
    @EventHandler
    public void serverStart(FMLServerStartingEvent pEvent)
    {
    	CommandFactory.registerCommands(pEvent);
    	
    	moneyhandler = new MoneyHandler();
    }
}
