package com.havensden.utilities.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BaseBlock extends Block
{

    public BaseBlock(String pUnlocalizedName, Material pMaterial, float pHardness, float pResistance) 
    {
        super(pMaterial);
        this.setUnlocalizedName(pUnlocalizedName);
        this.setCreativeTab(CreativeTabs.SEARCH);
        this.setHardness(pHardness);
        this.setResistance(pResistance);
    }

    public BaseBlock(String pUnlocalizedName, float pHardness, float pResistance) 
    {
        this(pUnlocalizedName, Material.ROCK, pHardness, pResistance);
    }

    public BaseBlock(String pUnlocalizedName) 
    {
        this(pUnlocalizedName, 2.0f, 10.0f);
    }
	
}
