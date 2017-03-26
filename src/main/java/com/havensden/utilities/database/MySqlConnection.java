package com.havensden.utilities.database;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.Provider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ServiceLoader;
import java.util.UUID;

import com.havensden.utilities.HavensDenUtilities;
import com.havensden.utilities.config.ConfigHandler;
import com.havensden.utilities.money.AtmSession;
import com.havensden.utilities.money.BankAccount;
import com.havensden.utilities.money.BankSystem;

import net.minecraftforge.common.MinecraftForge;

public class MySqlConnection extends DatabaseConnection
{
	private String mysqlhost;
	private String mysqluser;
	private String mysqlpassword;
	private String mysqldatabase;
	
	private Connection connection = null;
	
	@Override
	public void connect() 
	{
		loadAuthDetails();

		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		try 
		{
			System.out.println("[Havens Den] Connecting to Database");
						
			connection = DriverManager.getConnection("jdbc:mysql://" + mysqlhost + "/" + mysqldatabase
					+ "?user=" + mysqluser + "&password=" + mysqlpassword + "&autoreconnect=true");
			
			System.out.println("[Havens Den] Connected to Database");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean createBankaccount(String pPlayer, String pPin)
	{
		if(!hasAccount(pPlayer))
		{
			String lString = "INSERT INTO " + mysqldatabase + ".bankaccounts (player,balance,pin) VALUES ('" + pPlayer + "', '0', '" + pPin + "')";
			
			return doUpdate(lString);
		}
		
		return false;
	}
	
	public void getAllBankaccounts()
	{
		String lString = "SELECT * FROM bankaccounts";
		
		ResultSet lResult = getResultSet(lString);
		
		if(lResult != null)
		{
			try 
			{
				ArrayList<BankAccount> lAccounts = new ArrayList<BankAccount>();
				
				AtmSession lSession = BankSystem.getSession(BankSystem.createSession());
				
				while(lResult.next())
				{					
					lSession.createAccount(lResult.getLong("balance"), UUID.fromString(lResult.getString("player")), lResult.getString("pin"), true);
				}
				
				BankSystem.removeSession(lSession.getSessionID());
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public long getBalance(String pPlayer) 
	{
		String lString = "SELECT * FROM bankaccounts WHERE player='" + pPlayer + "'";
		
		ResultSet lResult = getResultSet(lString);
		
		if(lResult != null)
		{
			try 
			{				
				return lResult.getLong("balance");
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		return 0L;
	}
	
	@Override
	public boolean setBalance(String pPlayer, long pBalance) 
	{
		String lString = "UPDATE bankaccounts set balance = " + pBalance + "  where player = '" + pPlayer + "'";
				
		return doUpdate(lString);
	}
	
	private ResultSet getResultSet(String pString)
	{
		try 
		{
			Statement lStatement = connection.createStatement();
			
			ResultSet lResult = lStatement.executeQuery(pString);
						
			return lResult;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	private boolean doUpdate(String pString)
	{
		Statement lStatement;
		try 
		{
			lStatement = connection.createStatement();
			
			return lStatement.execute(pString);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public boolean hasAccount(String pPlayer)
	{
		String lString = "SELECT * FROM bankaccounts WHERE player='" + pPlayer + "'";
		
		ResultSet lResult = getResultSet(lString);
		
		if(lResult != null)
		{
			try 
			{
				if(lResult.next())
				{
					return true;
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	private void loadAuthDetails()
	{
		this.mysqldatabase = ConfigHandler.getMysqldatabase();
		this.mysqlhost = ConfigHandler.getMysqlhost();
		this.mysqlpassword = ConfigHandler.getMysqlpassword();
		this.mysqluser = ConfigHandler.getMysqluser();
	}
}
