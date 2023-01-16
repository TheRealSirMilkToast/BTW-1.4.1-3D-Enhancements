package net.minecraft.src;

public class TileEntityReeds extends TileEntity
{
    /** Entity type for this skull. */
    private int skullType;

    /** The skull's rotation. */
    private int skullRotation;

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
    }

    /**
     * Reads a tile entity from NBT.
     */
   public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
    }

    /**
     * Overriden in a sign to provide the text.
     */
    /*public Packet getDescriptionPacket()
    {
        NBTTagCompound var1 = new NBTTagCompound();
        this.writeToNBT(var1);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 4, var1);
    }*/

	/*@Override
	public void readNBTFromPacket(NBTTagCompound tag) {
        return;
	}*/
	
    /**
     * Set the entity type for the skull
     */
    /*public void setHatType(int par1)
    {
        this.skullType = par1;
    }*/

    /**
     * Get the entity type for the skull
     */
    /*public int getHatType()
    {
        return this.skullType;
    }*/

    /*public int getHatRotation()
    {
        return this.skullRotation;
    }*/

    /**
     * Set the skull's rotation
     */
    /*public void setHatRotation(int par1)
    {
        this.skullRotation = par1;
    }*/
    
    // FCMOD: Code added
    public int GetSkullRotationServerSafe()
    {
    	return this.skullRotation;
}
    // END FCMOD
}
