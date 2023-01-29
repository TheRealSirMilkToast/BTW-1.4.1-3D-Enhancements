// FCMOD

package net.minecraft.src;

public class FCBlockWorkbench extends BlockWorkbench
{
    protected FCBlockWorkbench( int iBlockID )
    {
        super( iBlockID );
        
    	SetBlockMaterial( FCBetterThanWolves.fcMaterialPlanks );
    	
        setHardness( 1.5F );
        
    	// Note that there is no appropriate tool to harvest this block
    	
        SetBuoyant();
    	SetFurnaceBurnTime( FCEnumFurnaceBurnTime.WOOD_BASED_BLOCK );    	
        
        setStepSound( soundWoodFootstep );
        
        setUnlocalizedName( "workbench" );
        
        setCreativeTab( null ); 
    }
    
	@Override
    public boolean onBlockActivated( World world, int i, int j, int k, EntityPlayer player, int iFacing, float fClickX, float fClickY, float fClickZ )
    {
		// prevent access if solid block above
		
		if ( FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing( world, i, j + 1, k, 0 ) )
		{
			return true;				
		}			
		
		return super.onBlockActivated( world, i, j, k, player, iFacing, fClickX, fClickY, fClickZ );
    }
	
	@Override
	public boolean DropComponentItemsOnBadBreak( World world, int i, int j, int k, int iMetadata, float fChanceOfDrop )
	{
		DropItemsIndividualy( world, i, j, k, FCBetterThanWolves.fcItemSawDust.itemID, 3, 0, fChanceOfDrop );
		DropItemsIndividualy( world, i, j, k, Item.stick.itemID, 1, 0, fChanceOfDrop );
		
		return true;
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
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    @Override
    public TileEntity createNewTileEntity(World par1World)
    {
        return new TileEntityWorkbench();
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {}
    
    @Override
	public boolean IsBlockRestingOnThatBelow( IBlockAccess blockAccess, int i, int j, int k )
	{
        int iMetadata = blockAccess.getBlockMetadata( i, j, k );
        
        return iMetadata == 1;
	}
	
    @Override
	public boolean CanRotateOnTurntable( IBlockAccess blockAccess, int i, int j, int k )
	{
		return false;
	}
	
    @Override
    public ItemStack GetStackRetrievedByBlockDispenser( World world, int i, int j, int k )
    {
		// don't allow skulls to be retrieved because their code is a mess
		
    	return null;
    }
    
	//----------- Client Side Functionality -----------//
    
    @Override
    public boolean RenderBlock( RenderBlocks renderBlocks, int i, int j, int k )
    {
    	return false;
    }   
	
    //------------- Class Specific Methods ------------//
	
	//----------- Client Side Functionality -----------//
}
