package com.havensden.utilities.config;

import com.havensden.utilities.HavensDenUtilities;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ConfigHandler 
{
	private static boolean modEnabled;
	private static String databasetype;
	private static String mysqlhost;
	private static String mysqluser;
	private static String mysqlpassword;
	private static String mysqldatabase;
	
	public static void syncConfig()
	{
		loadConfig();
		
		loadValues();
	}
		
	private static void loadConfig()
	{		
		HavensDenUtilities.instance.config.load();
	}
	
	private static void loadValues()
	{
		Property lIsModEnabled = HavensDenUtilities.instance.config.get(Configuration.CATEGORY_GENERAL, "IsModEnabled", true, "Whetever the Mod is enabled and will be loaded");
		modEnabled = lIsModEnabled.getBoolean();
		
		Property lDatabasetype = HavensDenUtilities.instance.config.get("server", "databasetype", "mysql");
		databasetype = lDatabasetype.getString();
		
		Property lMysqlhost = HavensDenUtilities.instance.config.get("mysql", "host", "localhost");
		mysqlhost = lMysqlhost.getString();
		
		Property lMysqluser = HavensDenUtilities.instance.config.get("mysql", "user", "root");
		mysqluser = lMysqluser.getString();
		
		Property lMysqlpassword = HavensDenUtilities.instance.config.get("mysql", "password", "");
		mysqlpassword = lMysqlpassword.getString();
		
		Property lMysqldatabase = HavensDenUtilities.instance.config.get("mysql", "database", "havensden");
		mysqldatabase = lMysqldatabase.getString();
		
		saveConfig();
	}
	
	public static void saveConfig()
	{
		HavensDenUtilities.instance.config.save();
	}
	
	public static boolean isModEnabled()
	{
		return modEnabled;
	}
	
	public static String getDatabasetype()
	{
		return databasetype;
	}

	public static String getMysqlhost() 
	{
		return mysqlhost;
	}

	public static String getMysqluser() 
	{
		return mysqluser;
	}

	public static String getMysqlpassword() 
	{
		return mysqlpassword;
	}

	public static String getMysqldatabase() 
	{
		return mysqldatabase;
	}
}
