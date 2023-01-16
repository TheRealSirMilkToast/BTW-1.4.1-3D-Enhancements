package net.minecraft.src;

import java.util.Random;

// FCMOD: Changed
//public class BlockFlower extends Block
public class BlockFlower extends FCBlockPlants
// END FCMOD
{
	// Entry Point for 3D Enhancements Mod
    static {
    	TDEAddon.instance.getVersionString();
    }
    
	/**
	 * FCNOTE: This class would be better called BlockPlants, as it acts as a common base class
	 * for vegetation and crops.
	 */
    protected BlockFlower(int par1, Material par2Material)
    {
        super(par1, par2Material);
        this.setTickRandomly(true);
        float var3 = 0.2F;
        // FCMOD: Changed
        //this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 3.0F, 0.5F + var3);
        InitBlockBounds( 0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 3.0F, 0.5F + var3 );
        // END FCMOD
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    protected BlockFlower(int par1)
    {
        this(par1, Material.plants);
    }

    // FCMOD: Removed and replaced
    /*
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return super.canPlaceBlockAt(par1World, par2, par3, par4) && this.canThisPlantGrowOnThisBlockID(par1World.getBlockId(par2, par3 - 1, par4));
    }
    */
    // END FCMOD

    /**
     * Gets passed in the blockID of the block below and supposed to return true if its allowed to grow on the type of
     * blockID passed in. Args: blockID
     */
    // FCMOD: Removed as deprecated
    /*
    protected boolean canThisPlantGrowOnThisBlockID(int par1)
    {
        return par1 == Block.grass.blockID || par1 == Block.dirt.blockID || par1 == Block.tilledField.blockID;
    }
    */
    // END FCMOD

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
        this.checkFlowerChange(par1World, par2, par3, par4);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        this.checkFlowerChange(par1World, par2, par3, par4);
    }

    protected final void checkFlowerChange(World par1World, int par2, int par3, int par4)
    {
        if (!this.canBlockStay(par1World, par2, par3, par4))
        {
            this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
            par1World.setBlockToAir(par2, par3, par4);
        }
    }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    // FCMOD: Removed and replaced
    /*
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        return (par1World.getFullBlockLightValue(par2, par3, par4) >= 8 || par1World.canBlockSeeTheSky(par2, par3, par4)) && this.canThisPlantGrowOnThisBlockID(par1World.getBlockId(par2, par3 - 1, par4));
    }
    */
    // END FCMOD

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 1;
    }
    
    // FCMOD: Added New
    @Override
    public boolean canBlockStay( World world, int i, int j, int k )
    {
        return ( world.getFullBlockLightValue( i, j, k ) >= 8 || 
        	world.canBlockSeeTheSky( i, j, k ) ) && super.canBlockStay( world, i, j, k ); 
}
    
    @Override
    public boolean CanBeGrazedOn( IBlockAccess blockAccess, int i, int j, int k, 
    	EntityAnimal animal )
    {
		return animal.CanGrazeOnRoughVegetation();
    }
    
    //------------- Class Specific Methods ------------//
    
	//----------- Client Side Functionality -----------//
    // END FCMOD
}
