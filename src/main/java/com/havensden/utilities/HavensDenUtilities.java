package com.havensden.utilities;

import com.havensden.utilities.config.ConfigHandler;
import com.havensden.utilities.database.DatabaseConnection;
import com.havensden.utilities.database.MySqlConnection;
import com.havensden.utilities.inventory.HavensDenTab;
import com.havensden.utilities.manager.CommandFactory;
import com.havensden.utilities.money.BankSystem;
import com.havensden.utilities.money.MoneyHandler;
import com.havensden.utilities.proxy.CommonProxy;

import net.minecraftforge.common.config.Configuration;
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
    public static final String VERSION = "0.1.7-A";
    
    public Configuration config;
    
    public DatabaseConnection dbconnection;
    
    public HavensDenTab creativetab;
    
    public MoneyHandler moneyhandler;
    
    @Instance
    public static HavensDenUtilities instance = new HavensDenUtilities();
    
    @SidedProxy(clientSide="com.havensden.utilities.proxy.ClientProxy", serverSide="com.havensden.utilities.proxy.ServerProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent pEvent) 
    {
		HavensDenUtilities.instance.config = new Configuration(pEvent.getSuggestedConfigurationFile());
    	
    	ConfigHandler.syncConfig();
    	
    	if(ConfigHandler.isModEnabled())
    	{
            this.creativetab = new HavensDenTab();
        	this.proxy.preInit(pEvent);
    	}
    }

    @EventHandler
    public void init(FMLInitializationEvent pEvent)
    {
    	if(ConfigHandler.isModEnabled())
    	{
    		this.proxy.init(pEvent);
    	}
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent pEvent) 
    {
    	if(ConfigHandler.isModEnabled())
    	{
    		this.proxy.postInit(pEvent);
    	}
    }
    
    @EventHandler
    public void serverStart(FMLServerStartingEvent pEvent)
    {
    	if(ConfigHandler.isModEnabled())
    	{
    		CommandFactory.registerCommands(pEvent);
    	
    		moneyhandler = new MoneyHandler();
    		
    		startDbConnection();
    		
    		BankSystem.loadAccounts();
    	}
    }
    
	private void startDbConnection()
	{
		String lDbType = ConfigHandler.getDatabasetype();
		
		if(lDbType.equalsIgnoreCase("mysql"))
		{
			HavensDenUtilities.instance.dbconnection = new MySqlConnection();
			
			HavensDenUtilities.instance.dbconnection.connect();
		}
	}
}
