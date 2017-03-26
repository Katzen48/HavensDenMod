package com.havensden.utilities.blocks;

import com.havensden.utilities.HavensDenUtilities;
import com.havensden.utilities.gui.GUIHandler;
import com.havensden.utilities.gui.GuiUtils;

import akka.actor.FSM.State;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class AtmBlock extends BlockDirectional
{
	public AtmBlock() 
	{
		super(Material.IRON);
		
        this.setUnlocalizedName("AtmBlock");
        this.setCreativeTab(CreativeTabs.SEARCH);
        this.setHardness(1.0F);
        this.setResistance(20.0F);
        
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) 
	{
		return new AxisAlignedBB(0.0f, 0.0f, 0.0f, 1.0f, 0.15f, 1.0f);
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) 
	{
		return new AxisAlignedBB(0.0f, 0.0f, 0.0f, 1.0f, 0.15f, 1.0f);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState pBlockstate)
	{
		return false;
	}
	
	@Override
	public void onBlockPlacedBy(World pWorld, BlockPos pPos, IBlockState pState, EntityLivingBase pUser, ItemStack pStack)
    {		
		super.onBlockPlacedBy(pWorld, pPos, pState, pUser,pStack);
    	int dir = pUser.getHorizontalFacing().getHorizontalIndex();
        pWorld.setBlockState(pPos, this.getDefaultState().withProperty(FACING, pUser.getHorizontalFacing()));
    }
	
	@Override
	public IBlockState getStateFromMeta(int pMeta) 
	{
		EnumFacing lEnumFacing = EnumFacing.getFront(pMeta);
		
		if(lEnumFacing.getAxis() == Axis.Y)
		{
			lEnumFacing = EnumFacing.SOUTH;
		}
		
		return this.getDefaultState().withProperty(FACING, lEnumFacing);
	}
	
	@Override
	public int getMetaFromState(IBlockState pState) 
	{
		return pState.getValue(FACING).getIndex();
	}
	
	@Override
	protected BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer(this, new IProperty[] {FACING});
	}
	
	@Override
	public boolean onBlockActivated(World pWorld, BlockPos pPos, IBlockState pState, EntityPlayer pPlayer,
			EnumHand hand, EnumFacing pFacing, float pX, float pY, float pZ) 
	{
		if(FMLCommonHandler.instance().getSide() == Side.CLIENT)
		{
			GuiUtils.openAtmGui();
		}
		
		return true;
	}
}
