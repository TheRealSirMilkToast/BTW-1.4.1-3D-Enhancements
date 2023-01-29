// FCMOD

package net.minecraft.src;

public class FCBlockLadder extends FCBlockLadderBase
{
	private static final double BEDROLL_HEIGHT = 1D;
	private Icon sandstone_top;
	
    protected FCBlockLadder( int iBlockID )
    {
        super( iBlockID );
        
        setUnlocalizedName( "fcBlockLadder" );
        
        setCreativeTab( CreativeTabs.tabDecorations );
        
        //protected void InitBlockBounds( double dMinX, double dMinY, double dMinZ, double dMaxX, double dMaxY, double dMaxZ )
        //InitBlockBounds(0D, 0D, 0D, .5D, BEDROLL_HEIGHT, .5D);
    }

    @Override
    public boolean GetCanBeSetOnFireDirectly( IBlockAccess blockAccess, int i, int j, int k )
    {
    	return true;
    }
    
    @Override
    public boolean GetCanBeSetOnFireDirectlyByItem( IBlockAccess blockAccess, int i, int j, int k )
    {
    	return false;
    }
    
    @Override
    public boolean SetOnFireDirectly( World world, int i, int j, int k )
    {
    	int iNewMetadata = FCBetterThanWolves.fcBlockLadderOnFire.SetFacing( 0, GetFacing( world, i, j, k ) ); 
		
    	world.setBlockAndMetadataWithNotify( i, j, k, FCBetterThanWolves.fcBlockLadderOnFire.blockID, iNewMetadata );
		
    	return true;
    }
    
    @Override
    public int GetChanceOfFireSpreadingDirectlyTo( IBlockAccess blockAccess, int i, int j, int k )
    {
		return 60; // same chance as leaves and other highly flammable objects
    }
    
    @Override
    public boolean CanBeCrushedByFallingEntity( World world, int i, int j, int k, EntityFallingSand entity )
    {
    	return true;
    }
    
    @Override
    public void OnCrushedByFallingEntity( World world, int i, int j, int k, EntityFallingSand entity )
    {
    	if ( !world.isRemote )
    	{
            dropBlockAsItem( world, i, j, k, world.getBlockMetadata( i, j, k ), 0 );    		
    	}
    }

    @Override
    public boolean RenderBlock(RenderBlocks renderer, int x, int y, int z)
    {
    	IBlockAccess blockAccess = renderer.blockAccess;
    	int iFacing = GetFacing( blockAccess, x, y, z );
    	
    	switch(iFacing)
    	{
    		case(0):
    		{
    			
    		}
    		case(1):
    		{
    			
    		}
    		case(2):
    		{
    			renderer.setRenderBounds(0.75D, 0.0D, 0.90625D, 0.90625D, 1.0D, 0.96875D);
    			renderer.renderStandardBlock(this, x, y, z);
    			
    			renderer.setRenderBounds(0.09375D, 0.0D, 0.90625D, 0.25D, 1.0D, 0.96875D);
    			renderer.renderStandardBlock(this, x, y, z);
    			
    			renderer.setRenderBounds(0.0625D, 0.21875D, 0.90625D, 0.9375D, 0.4375D, 0.96875D);
    			renderer.renderStandardBlock(this, x, y, z);
    			
    			renderer.setRenderBounds(0.0625D, 0.71875D, 0.90625D, 0.9375D, 0.9375D, 0.96875D);
    			renderer.renderStandardBlock(this, x, y, z);
    			
    			renderer.setRenderBounds(0.03125D, 0.25D, 0.875D, 0.96875D, 0.40625D, 1.0D);
    			renderer.renderStandardBlock(this, x, y, z);
    			
    			renderer.setRenderBounds(0.03125D, 0.75D, 0.875D, 0.96875D, 0.90625D, 1.0D);
    			renderer.renderStandardBlock(this, x, y, z);
    			
    			return true;
    		}
    		case(3):
    		{
    			renderer.setRenderBounds(0.25D, 0.0D, 0.09375D, 0.09375D, 1.0D, 0.03125D);
    			renderer.renderStandardBlock(this, x, y, z);
    			
    			renderer.setRenderBounds(0.90625D, 0.0D, 0.09375D, 0.75D, 1.0D, 0.03125D);
    			renderer.renderStandardBlock(this, x, y, z);
    			
    			renderer.setRenderBounds(0.9375D, 0.21875D, 0.09375D, 0.0625D, 0.4375D, 0.03125D);
    			renderer.renderStandardBlock(this, x, y, z);
    			
    			renderer.setRenderBounds(0.9375D, 0.71875D, 0.09375D, 0.0625D, 0.9375D, 0.03125D);
    			renderer.renderStandardBlock(this, x, y, z);
    			
    			renderer.setRenderBounds(0.96875D, 0.25D, 0.125D, 0.03125D, 0.40625D, 0.0D);
    			renderer.renderStandardBlock(this, x, y, z);
    			
    			renderer.setRenderBounds(0.96875D, 0.75D, 0.125D, 0.03125D, 0.90625D, 0.0D);
    			renderer.renderStandardBlock(this, x, y, z);
    			
    			return true;
    		}
    		case(4):
    		{
    			renderer.setRenderBounds(0.90625D, 0.0D, 0.75D, 0.96875D, 1.0D, 0.90625D);
    			renderer.renderStandardBlock(this, x, y, z);
    			
    			renderer.setRenderBounds(0.90625D, 0.0D, 0.09375D, 0.96875D, 1.0D, 0.25D);
    			renderer.renderStandardBlock(this, x, y, z);
    			
    			renderer.setRenderBounds(0.90625D, 0.21875D, 0.0625D, 0.96875D, 0.4375D, 0.9375D);
    			renderer.renderStandardBlock(this, x, y, z);
    			
    			renderer.setRenderBounds(0.90625D, 0.71875D, 0.0625D, 0.96875D, 0.9375D, 0.9375D);
    			renderer.renderStandardBlock(this, x, y, z);
    			
    			renderer.setRenderBounds(0.875D, 0.25D, 0.03125D, 1.0D, 0.40625D, 0.96875D);
    			renderer.renderStandardBlock(this, x, y, z);
    			
    			renderer.setRenderBounds(0.875D, 0.75D, 0.03125D, 1.0D, 0.90625D, 0.96875D);
    			renderer.renderStandardBlock(this, x, y, z);
    			
    			return true;
    		}
    		case(5):
    		{
    			renderer.setRenderBounds(0.09375D, 0.0D, 0.25D, 0.03125D, 1.0D, 0.09375D);
    			renderer.renderStandardBlock(this, x, y, z);
    			
    			renderer.setRenderBounds(0.09375D, 0.0D, 0.90625D, 0.03125D, 1.0D, 0.75D);
    			renderer.renderStandardBlock(this, x, y, z);
    			
    			renderer.setRenderBounds(0.09375D, 0.21875D, 0.9375D, 0.03125D, 0.4375D, 0.0625D);
    			renderer.renderStandardBlock(this, x, y, z);
    			
    			renderer.setRenderBounds(0.09375D, 0.71875D, 0.9375D, 0.03125D, 0.9375D, 0.0625D);
    			renderer.renderStandardBlock(this, x, y, z);
    			
    			renderer.setRenderBounds(0.125D, 0.25D, 0.96875D, 0.0D, 0.40625D, 0.03125D);
    			renderer.renderStandardBlock(this, x, y, z);
    			
    			renderer.setRenderBounds(0.125D, 0.75D, 0.96875D, 0.0D, 0.90625D, 0.03125D);
    			renderer.renderStandardBlock(this, x, y, z);
    			
    			return true;
    		}
    	}
    	return true;
    }

@Override
public boolean DoesItemRenderAsBlock( int iItemDamage )
{
    return false;
}

/*@Override
public void RenderBlockAsItem(RenderBlocks renderBlocks, int iItemDamage, float fBrightness) {
	renderBlocks.setRenderBounds(0.25D, 0.0D, 0.09375D, 0.09375D, 1.0D, 0.03125D);
	FCClientUtilsRender.RenderInvBlockWithMetadata( renderBlocks, this, -0.8F, -0.75F, -0.5F, iItemDamage << 1 );
	
	renderBlocks.setRenderBounds(0.90625D, 0.0D, 0.09375D, 0.75D, 1.0D, 0.03125D);
	FCClientUtilsRender.RenderInvBlockWithMetadata( renderBlocks, this, -0.8F, -0.75F, -0.5F, iItemDamage << 1 );
	
	renderBlocks.setRenderBounds(0.9375D, 0.21875D, 0.09375D, 0.0625D, 0.4375D, 0.03125D);
	FCClientUtilsRender.RenderInvBlockWithMetadata( renderBlocks, this, -0.8F, -0.75F, -0.5F, iItemDamage << 1 );
	
	renderBlocks.setRenderBounds(0.9375D, 0.71875D, 0.09375D, 0.0625D, 0.9375D, 0.03125D);
	FCClientUtilsRender.RenderInvBlockWithMetadata( renderBlocks, this, -0.8F, -0.75F, -0.5F, iItemDamage << 1 );
	
	renderBlocks.setRenderBounds(0.96875D, 0.25D, 0.125D, 0.03125D, 0.40625D, 0.0D);
	FCClientUtilsRender.RenderInvBlockWithMetadata( renderBlocks, this, -0.8F, -0.75F, -0.5F, iItemDamage << 1 );
	
	renderBlocks.setRenderBounds(0.96875D, 0.75D, 0.125D, 0.03125D, 0.90625D, 0.0D);
	FCClientUtilsRender.RenderInvBlockWithMetadata( renderBlocks, this, -0.8F, -0.75F, -0.5F, iItemDamage << 1 );
	
	//renderBlocks.setRenderBounds(0D, 0D, 0D, .5D, BEDROLL_HEIGHT, .5D);
	//FCClientUtilsRender.RenderInvBlockWithTexture( renderBlocks, this, -0.5F, -0.5F, -0.5F, sideTexture );
	//FCClientUtilsRender.RenderInvBlockWithMetadata( renderBlocks, this, -0.5F, -0.5F, -0.5F, iItemDamage << 1 );
}*/

@Override
public boolean renderAsNormalBlock()
{
    return false;
}

@Override
public void registerIcons( IconRegister register )
{
	blockIcon = register.registerIcon( "3DLadderFront" );
	sandstone_top = register.registerIcon("3DLadderSide");
}

@Override
public Icon getIcon(int par1, int par2)
{
	int facing = GetFacing( par2 );
	
	if(facing == 4 || facing == 5)
	{
		switch(par1)
		{
			case(0)://bottom
			{
				return sandstone_top;
			}
			case(1)://top
			{
				return sandstone_top;
			}
			case(2):
			{
				return sandstone_top;
			}
			case(3):
			{
				return sandstone_top;
			}
			case(4):
			{
				return blockIcon;
			}
			case(5):
			{
				return blockIcon;
			}
		};
	}
	
	switch(par1)
	{
		case(0)://bottom
		{
			return sandstone_top;
		}
		case(1)://top
		{
			return sandstone_top;
		}
		case(2):
		{
			return blockIcon;
		}
		case(3):
		{
			return blockIcon;
		}
		case(4):
		{
			return sandstone_top;
		}
		case(5):
		{
			return sandstone_top;
		}
	};
	return blockIcon;
}

@Override
public String getItemIconName()
{
    return "3DLadderFront";
}
    
    //------------- Class Specific Methods ------------//
    
	//----------- Client Side Functionality -----------//
}
