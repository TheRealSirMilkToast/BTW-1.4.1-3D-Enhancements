package net.minecraft.src;

import java.util.Random;

public class FCBlockReed extends FCBlockReedBase {
	public FCBlockReed(int id) {
		super(id);
		this.setUnlocalizedName("reeds");
	}
	
	@Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        int blockBelowID = world.getBlockId(x, y - 1, z);
        Block blockBelow = Block.blocksList[blockBelowID];

    	return blockBelow instanceof FCBlockReedBase;
    }

	@Override
	public int idDropped(int par1, Random rand, int par3) {
        return Item.reed.itemID;
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

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityReeds();
	}

	@Override
    public boolean RenderBlock( RenderBlocks renderBlocks, int i, int j, int k )
    {
    	return false;
    } 
}