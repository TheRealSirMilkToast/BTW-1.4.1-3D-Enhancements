package net.minecraft.src;

import java.util.Random;

public class FCBlockCandle extends Block {
	private int metaDropped;
	private Icon Candle_Texture_Lit;

	protected FCBlockCandle(int id, int metaDropped, String name) {
		super(id, FCBetterThanWolves.fcMaterialCandle);

		setHardness(0F);
		SetPicksEffectiveOn(true);
		SetAxesEffectiveOn(true);

		setLightValue(1F);

		setStepSound(soundStoneFootstep);

		setUnlocalizedName(name);

		this.metaDropped = metaDropped;
	}
	
	//<DK ModPack>
	@Override
    public void registerIcons(IconRegister register)
    {
		//super.registerIcons(register);
		Candle_Texture_Lit = register.registerIcon(getUnlocalizedName2() + "_lit");
		blockIcon = register.registerIcon(getUnlocalizedName2());
    }
	
	@Override
	public Icon getIcon(int par1, int par2)
    {
		//if lit and we allow candle textures, return lit texture
        if((par2 >> 2) == 1 && TDEAddon.AllowSeparateCandleLitTextures)
        {
        	return Candle_Texture_Lit;
        }
        else//else return nonlit texture
        {
        	return blockIcon;
        }
    }
	//</DK ModPack>

	@Override
	public int idDropped(int par1, Random rand, int par3) {
		return FCBetterThanWolves.fcItemCandle.itemID;
	}

	@Override
	public int damageDropped(int meta) {
		return this.metaDropped;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
    
    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		int blockBelowID = world.getBlockId(x, y - 1, z);
		int blockBelowMetadata = world.getBlockMetadata(x, y - 1, z) ;
		
		if (blockBelowID == FCBetterThanWolves.fcAestheticNonOpaque.blockID && blockBelowMetadata == FCBlockAestheticNonOpaque.m_iSubtypeLightningRod) {
			return true;
		}

		return FCUtilsWorld.DoesBlockHaveSmallCenterHardpointToFacing(world, x, y - 1, z, 1, true);
    }
	
	@Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighborBlockID) {
    	// pop the block off if it no longer has a valid anchor point
		if (!canPlaceBlockAt(world, x, y, z)) {
            dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockWithNotify(x, y, z, 0);
		}
    }
	
    @Override
	public boolean IsBlockRestingOnThatBelow(IBlockAccess blockAccess, int x, int y, int z) {
        return true;
	}
    
	@Override
	public void OnNeighborDisrupted(World world, int x, int y, int z, int toFacing) {
		if (toFacing == 0) {
            dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockWithNotify(x, y, z, 0);
		}
	}

    @Override
	public boolean GetPreventsFluidFlow(World world, int x, int y, int z, Block fluidBlock) {
    	return false;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}
	
	@Override
    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess blockAccess, int x, int y, int z) {
		int candleCount = this.getCandleCount(blockAccess, x, y, z);
		
		switch (candleCount) {
		case 1:
			return AxisAlignedBB.getAABBPool().getAABB(7/16F, 0, 7/16F, 9/16F, 7/16F, 9/16F);
		case 2:
			return AxisAlignedBB.getAABBPool().getAABB(5/16F, 0, 6/16F, 11/16F, 7/16F, 9/16F);
		case 3:
			return AxisAlignedBB.getAABBPool().getAABB(6/16F, 0, 6/16F, 11/16F, 7/16F, 11/16F);
		case 4:
			return AxisAlignedBB.getAABBPool().getAABB(5/16F, 0, 5/16F, 11/16F, 7/16F, 10/16F);
		}
		
		return AxisAlignedBB.getAABBPool().getAABB(0, 0, 0, 1, 1, 1);
	}

	@Override
	public int getLightValue(IBlockAccess blockAccess, int x, int y, int z) {
		if (isLit(blockAccess, x, y, z)) {
			int candleCount = this.getCandleCount(blockAccess, x, y, z);

			return (int) (lightValue[this.blockID] * ((float) candleCount / 4));
		} else {
			return 0;
		}
	}
    
    @Override
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float chance, int fortuneModifier) {
        if (!world.isRemote) {
    		this.DropItemsIndividualy(world, x, y, z, 
    				this.idDropped(metadata, world.rand, 0), 
    				this.getCandleCount(world, x, y, z), 
    				this.damageDropped(metadata), 1);
        }
    }

	@Override
	public boolean CanConvertBlock(ItemStack stack, World world, int x, int y, int z) {
		return true;
	}

	@Override
	public boolean ConvertBlock(ItemStack stack, World world, int x, int y, int z, int fromSide) {
		int candleCount = this.getCandleCount(world, x, y, z);
		int metadata = world.getBlockMetadata(x, y, z);
		
		this.setCandleCount(world, x, y, z, candleCount - 1);
		this.DropItemsIndividualy(world, x, y, z, this.idDropped(metadata, world.rand, 0), 1, this.damageDropped(metadata), 1);

		return true;
	}

	@Override
	public boolean GetCanBeSetOnFireDirectly(IBlockAccess blockAccess, int x, int y, int z) {
		return !this.isLit(blockAccess, x, y, z);
	}

	@Override
	public boolean SetOnFireDirectly(World world, int x, int y, int z) {
		if (!this.isLit(world, x, y, z)) {
			this.setLit(world, x, y, z, true);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean GetCanBlockLightItemOnFire(IBlockAccess blockAccess, int x, int y, int z) {
		return isLit(blockAccess, x, y, z);
	}

	// ------ Class specific methods ------//

	public int getCandleCount(IBlockAccess blockAccess, int x, int y, int z) {
		int meta = blockAccess.getBlockMetadata(x, y, z);
		return (meta & 3) + 1;
	}

	public void setCandleCount(World world, int x, int y, int z, int count) {
		if (count == 0) {
			world.setBlockWithNotify(x, y, z, 0);
			return;
		}

		if (count > 4) {
			Exception e = new IllegalArgumentException("Cannot set candle count higher than 4");
			e.printStackTrace();

			count = 4;
		}

		int meta = world.getBlockMetadata(x, y, z);
		int newMeta = meta & 4;

		newMeta += count - 1;

		world.setBlockMetadataWithNotify(x, y, z, newMeta);
	}

	public boolean isLit(IBlockAccess blockAccess, int x, int y, int z) {
		return (blockAccess.getBlockMetadata(x, y, z) >> 2) == 1;
	}

	public void setLit(World world, int x, int y, int z, boolean isLit) {
		int meta = world.getBlockMetadata(x, y, z);
		int newMeta;

		if (isLit) {
			newMeta = meta | 4;
		} else {
			newMeta = meta & 3;
		}

		world.setBlockMetadata(x, y, z, newMeta);
	}
	
	public int setLit(int meta, boolean isLit) {
		int newMeta;
		
		if (isLit) {
			newMeta = meta | 4;
		} else {
			newMeta = meta & 3;
		}
		
		return newMeta;
	}

	// ------ Client side functionality ------//

	/**
	 * A randomly called display update to be able to add particles or other items
	 * for display
	 */
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		// Only display particles if the candle is actually lit
		if (!this.isLit(world, x, y, z)) {
			return;
		}
		
		int meta = world.getBlockMetadata(x, y, z);
		int candleCount = getCandleCount(world, x, y, z);
		
		double xPos = (double) ((float) x + 1 / 16F);
		double yPos = (double) ((float) y + 8 / 16F);
		double zPos = (double) ((float) z + 1 / 16F);

		switch (candleCount) {
		case 1:
			xPos = (double) ((float) x + 8 / 16F);
			yPos = (double) ((float) y + 8 / 16F);
			zPos = (double) ((float) z + 8 / 16F);

			spawnParticles(world, xPos, yPos, zPos);
			break;

		case 2:
			xPos = (double) ((float) x + 10 / 16F);
			yPos = (double) ((float) y + 8 / 16F);
			zPos = (double) ((float) z + 8 / 16F);

			spawnParticles(world, xPos, yPos, zPos);

			xPos = (double) ((float) x + 6 / 16F);
			yPos = (double) ((float) y + 7 / 16F);
			zPos = (double) ((float) z + 7 / 16F);
			spawnParticles(world, xPos, yPos, zPos);
			break;

		case 3:
			xPos = (double) ((float) x + 10 / 16F);
			yPos = (double) ((float) y + 8 / 16F);
			zPos = (double) ((float) z + 8 / 16F);

			spawnParticles(world, xPos, yPos, zPos);

			xPos = (double) ((float) x + 7 / 16F);
			yPos = (double) ((float) y + 7 / 16F);
			zPos = (double) ((float) z + 7 / 16F);

			spawnParticles(world, xPos, yPos, zPos);

			xPos = (double) ((float) x + 8 / 16F);
			yPos = (double) ((float) y + 5 / 16F);
			zPos = (double) ((float) z + 10 / 16F);

			spawnParticles(world, xPos, yPos, zPos);
			break;

		case 4:
			xPos = (double) ((float) x + 10 / 16F);
			yPos = (double) ((float) y + 8 / 16F);
			zPos = (double) ((float) z + 6 / 16F);

			spawnParticles(world, xPos, yPos, zPos);

			xPos = (double) ((float) x + 7 / 16F);
			yPos = (double) ((float) y + 7 / 16F);
			zPos = (double) ((float) z + 6 / 16F);

			spawnParticles(world, xPos, yPos, zPos);

			xPos = (double) ((float) x + 9 / 16F);
			yPos = (double) ((float) y + 5 / 16F);
			zPos = (double) ((float) z + 9 / 16F);

			spawnParticles(world, xPos, yPos, zPos);

			xPos = (double) ((float) x + 6 / 16F);
			yPos = (double) ((float) y + 6 / 16F);
			zPos = (double) ((float) z + 9 / 16F);

			spawnParticles(world, xPos, yPos, zPos);
			break;
		}
	}

	private void spawnParticles(World world, double xPos, double yPos, double zPos) {
		world.spawnParticle("fcsmallflame", xPos, yPos, zPos, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("fcsmallflame", xPos, yPos, zPos, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("fcsmallflame", xPos, yPos, zPos, 0.0D, 0.0D, 0.0D);
	}

	@Override
	public boolean RenderBlock(RenderBlocks render, int x, int y, int z) {
		return renderBlockCandle(render, this, x, y, z);
	}

	/**
	 * Renders a Multi-Candle Block at the given coordinates
	 */
	private boolean renderBlockCandle(RenderBlocks render, Block block, int x, int y, int z) {
		Tessellator tess = Tessellator.instance;
		tess.setBrightness(block.getMixedBrightnessForBlock(render.blockAccess, x, y, z));
		tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		int meta = render.blockAccess.getBlockMetadata(x, y, z);
		Icon icon = getBlockTexture(render.blockAccess, x, y, z, meta);

		int candleCount = this.getCandleCount(render.blockAccess, x, y, z);

		double minU = (double) icon.getInterpolatedU(0);
		double maxU = (double) icon.getInterpolatedU(2);
		double minV = (double) icon.getInterpolatedV(8);
		double maxV = (double) icon.getInterpolatedV(14);

		double minXOffset = 0 / 16.0D;
		double maxXOffset = 2 / 16.0D;
		double minYOffset = 0 / 16.0D;
		double maxYOffset = 6 / 16.0D;
		double minZOffset = 0 / 16.0D;
		double maxZOffset = 2 / 16.0D;

		double minX = (double) x + minXOffset;
		double maxX = (double) x + maxXOffset;
		double minY = (double) y + minYOffset;
		double maxY = (double) y + maxYOffset;
		double minZ = (double) z + minZOffset;
		double maxZ = (double) z + maxZOffset;

		switch (candleCount) {
		case 1:
			minX = (double) x + 7 / 16D;
			maxX = (double) x + 9 / 16D;
			minY = (double) y + 0 / 16D;
			maxY = (double) y + 6 / 16D;
			minZ = (double) z + 7 / 16D;
			maxZ = (double) z + 9 / 16D;

			drawCandle(tess, minX, maxX, minY, maxY, minZ, maxZ, minU, maxU, minV, maxV, icon);
			drawWick(tess, minX, maxX, minY, maxY, minZ, maxZ, minU, maxU, minV, maxV, icon);
			break;

		case 2:
			minX = (double) x + 9 / 16D;
			maxX = (double) x + 11 / 16D;
			minY = (double) y + 0 / 16D;
			maxY = (double) y + 6 / 16D;
			minZ = (double) z + 7 / 16D;
			maxZ = (double) z + 9 / 16D;

			drawCandle(tess, minX, maxX, minY, maxY, minZ, maxZ, minU, maxU, minV, maxV, icon);
			drawWick(tess, minX, maxX, minY, maxY, minZ, maxZ, minU, maxU, minV, maxV, icon);

			minX = (double) x + 5 / 16D;
			maxX = (double) x + 7 / 16D;
			minY = (double) y + 0 / 16D;
			maxY = (double) y + 5 / 16D;
			minZ = (double) z + 6 / 16D;
			maxZ = (double) z + 8 / 16D;
			maxV = (double) icon.getInterpolatedV(13);

			drawCandle(tess, minX, maxX, minY, maxY, minZ, maxZ, minU, maxU, minV, maxV, icon);
			drawWick(tess, minX, maxX, minY, maxY, minZ, maxZ, minU, maxU, minV, maxV, icon);
			break;

		case 3:
			minX = (double) x + 9 / 16D;
			maxX = (double) x + 11 / 16D;
			minY = (double) y + 0 / 16D;
			maxY = (double) y + 6 / 16D;
			minZ = (double) z + 7 / 16D;
			maxZ = (double) z + 9 / 16D;

			drawCandle(tess, minX, maxX, minY, maxY, minZ, maxZ, minU, maxU, minV, maxV, icon);
			drawWick(tess, minX, maxX, minY, maxY, minZ, maxZ, minU, maxU, minV, maxV, icon);

			minX = (double) x + 6 / 16D;
			maxX = (double) x + 8 / 16D;
			minY = (double) y + 0 / 16D;
			maxY = (double) y + 5 / 16D;
			minZ = (double) z + 6 / 16D;
			maxZ = (double) z + 8 / 16D;
			maxV = (double) icon.getInterpolatedV(13);

			drawCandle(tess, minX, maxX, minY, maxY, minZ, maxZ, minU, maxU, minV, maxV, icon);
			drawWick(tess, minX, maxX, minY, maxY, minZ, maxZ, minU, maxU, minV, maxV, icon);

			minX = (double) x + 7 / 16D;
			maxX = (double) x + 9 / 16D;
			minY = (double) y + 0 / 16D;
			maxY = (double) y + 3 / 16D;
			minZ = (double) z + 9 / 16D;
			maxZ = (double) z + 11 / 16D;

			maxV = (double) icon.getInterpolatedV(11);

			drawCandle(tess, minX, maxX, minY, maxY, minZ, maxZ, minU, maxU, minV, maxV, icon);
			drawWick(tess, minX, maxX, minY, maxY, minZ, maxZ, minU, maxU, minV, maxV, icon);
			break;

		case 4:
			minX = (double) x + 9 / 16D;
			maxX = (double) x + 11 / 16D;
			minY = (double) y + 0 / 16D;
			maxY = (double) y + 6 / 16D;
			minZ = (double) z + 5 / 16D;
			maxZ = (double) z + 7 / 16D;

			drawCandle(tess, minX, maxX, minY, maxY, minZ, maxZ, minU, maxU, minV, maxV, icon);
			drawWick(tess, minX, maxX, minY, maxY, minZ, maxZ, minU, maxU, minV, maxV, icon);

			minX = (double) x + 6 / 16D;
			maxX = (double) x + 8 / 16D;
			minY = (double) y + 0 / 16D;
			maxY = (double) y + 5 / 16D;
			minZ = (double) z + 5 / 16D;
			maxZ = (double) z + 7 / 16D;
			maxV = (double) icon.getInterpolatedV(13);

			drawCandle(tess, minX, maxX, minY, maxY, minZ, maxZ, minU, maxU, minV, maxV, icon);
			drawWick(tess, minX, maxX, minY, maxY, minZ, maxZ, minU, maxU, minV, maxV, icon);

			minX = (double) x + 8 / 16D;
			maxX = (double) x + 10 / 16D;
			minY = (double) y + 0 / 16D;
			maxY = (double) y + 3 / 16D;
			minZ = (double) z + 8 / 16D;
			maxZ = (double) z + 10 / 16D;
			maxV = (double) icon.getInterpolatedV(11);

			drawCandle(tess, minX, maxX, minY, maxY, minZ, maxZ, minU, maxU, minV, maxV, icon);
			drawWick(tess, minX, maxX, minY, maxY, minZ, maxZ, minU, maxU, minV, maxV, icon);

			minX = (double) x + 5 / 16D;
			maxX = (double) x + 7 / 16D;
			minY = (double) y + 0 / 16D;
			maxY = (double) y + 4 / 16D;
			minZ = (double) z + 8 / 16D;
			maxZ = (double) z + 10 / 16D;
			maxV = (double) icon.getInterpolatedV(12);

			drawCandle(tess, minX, maxX, minY, maxY, minZ, maxZ, minU, maxU, minV, maxV, icon);
			drawWick(tess, minX, maxX, minY, maxY, minZ, maxZ, minU, maxU, minV, maxV, icon);
			break;
		}

		return true;
	}

	private boolean drawCandle(Tessellator tess, double minX, double maxX, double minY, double maxY, double minZ,
			double maxZ, double minU, double maxU, double minV, double maxV, Icon icon) {
		// SIDES
		tess.addVertexWithUV(minX, minY, minZ, minU, maxV);
		tess.addVertexWithUV(minX, minY, maxZ, maxU, maxV);
		tess.addVertexWithUV(minX, maxY, maxZ, maxU, minV);
		tess.addVertexWithUV(minX, maxY, minZ, minU, minV);
		tess.addVertexWithUV(maxX, minY, maxZ, minU, maxV);
		tess.addVertexWithUV(maxX, minY, minZ, maxU, maxV);
		tess.addVertexWithUV(maxX, maxY, minZ, maxU, minV);
		tess.addVertexWithUV(maxX, maxY, maxZ, minU, minV);
		tess.addVertexWithUV(maxX, minY, minZ, minU, maxV);
		tess.addVertexWithUV(minX, minY, minZ, maxU, maxV);
		tess.addVertexWithUV(minX, maxY, minZ, maxU, minV);
		tess.addVertexWithUV(maxX, maxY, minZ, minU, minV);
		tess.addVertexWithUV(minX, minY, maxZ, minU, maxV);
		tess.addVertexWithUV(maxX, minY, maxZ, maxU, maxV);
		tess.addVertexWithUV(maxX, maxY, maxZ, maxU, minV);
		tess.addVertexWithUV(minX, maxY, maxZ, minU, minV);

		// TOP AND BOTTOM
		minU = (double) icon.getMinU();
		maxU = (double) icon.getInterpolatedU(2);
		minV = (double) icon.getInterpolatedV(6);
		maxV = (double) icon.getInterpolatedV(8);
		tess.addVertexWithUV(minX, maxY, maxZ, minU, maxV);
		tess.addVertexWithUV(maxX, maxY, maxZ, maxU, maxV);
		tess.addVertexWithUV(maxX, maxY, minZ, maxU, minV);
		tess.addVertexWithUV(minX, maxY, minZ, minU, minV);
		tess.addVertexWithUV(minX, minY, minZ, minU, minV);
		tess.addVertexWithUV(maxX, minY, minZ, maxU, minV);
		tess.addVertexWithUV(maxX, minY, maxZ, maxU, maxV);
		tess.addVertexWithUV(minX, minY, maxZ, minU, maxV);

		return true;
	}

	private boolean drawWick(Tessellator tess, double minX, double maxX, double minY, double maxY, double minZ,
			double maxZ, double minU, double maxU, double minV, double maxV, Icon icon) {
		minU = (double) icon.getInterpolatedU(0);
		maxU = (double) icon.getInterpolatedU(1);
		minV = (double) icon.getInterpolatedV(5);
		maxV = (double) icon.getInterpolatedV(6);

		minX = minX + 3 / 64.0D;
		maxX = minX + 2 / 64.0D;
		minY = maxY;
		maxY = maxY + 1 / 16.0D;
		minZ = minZ + 3 / 64.0D;
		maxZ = minZ + 2 / 64.0D;

		// SIDES
		tess.addVertexWithUV(minX, minY, minZ, minU, maxV);
		tess.addVertexWithUV(minX, minY, maxZ, maxU, maxV);
		tess.addVertexWithUV(minX, maxY, maxZ, maxU, minV);
		tess.addVertexWithUV(minX, maxY, minZ, minU, minV);
		tess.addVertexWithUV(maxX, minY, maxZ, minU, maxV);
		tess.addVertexWithUV(maxX, minY, minZ, maxU, maxV);
		tess.addVertexWithUV(maxX, maxY, minZ, maxU, minV);
		tess.addVertexWithUV(maxX, maxY, maxZ, minU, minV);
		tess.addVertexWithUV(maxX, minY, minZ, minU, maxV);
		tess.addVertexWithUV(minX, minY, minZ, maxU, maxV);
		tess.addVertexWithUV(minX, maxY, minZ, maxU, minV);
		tess.addVertexWithUV(maxX, maxY, minZ, minU, minV);
		tess.addVertexWithUV(minX, minY, maxZ, minU, maxV);
		tess.addVertexWithUV(maxX, minY, maxZ, maxU, maxV);
		tess.addVertexWithUV(maxX, maxY, maxZ, maxU, minV);
		tess.addVertexWithUV(minX, maxY, maxZ, minU, minV);

		// TOP AND BOTTOM
		tess.addVertexWithUV(minX, maxY, maxZ, minU, maxV);
		tess.addVertexWithUV(maxX, maxY, maxZ, maxU, maxV);
		tess.addVertexWithUV(maxX, maxY, minZ, maxU, minV);
		tess.addVertexWithUV(minX, maxY, minZ, minU, minV);
		tess.addVertexWithUV(minX, minY, minZ, minU, minV);
		tess.addVertexWithUV(maxX, minY, minZ, maxU, minV);
		tess.addVertexWithUV(maxX, minY, maxZ, maxU, maxV);
		tess.addVertexWithUV(minX, minY, maxZ, minU, maxV);

		return true;
	}
}