// FCMOD

package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class FCTileEntityInfernalEnchanter extends TileEntity
{
	/** Used by the render to make the book 'bounce' */
    public int tickCount;

    /** Value used for determining how the page flip should look. */
    public float pageFlip;

    /** The last tick's pageFlip value. */
    public float pageFlipPrev;
    public float field_70373_d;
    public float field_70374_e;

    /** The amount that the book is open. */
    public float bookSpread;

    /** The amount that the book is open. */
    public float bookSpreadPrev;
    public float bookRotation2;
    public float bookRotationPrev;
    public float bookRotation;

    //Floating Candle vars
    public float candleRotation2;
    public float candleRotationPrev;
    public float candleRotation;
    public boolean shouldBounce;
    public float height = 0.0F;
    public float[] CandleRandomMaxHeightMulti = {1.0F, 1.0F, 1.0F, 1.0F, 1.0F};
    public float[] CandleRandomSpeedMulti = {1.0F, 1.0F, 1.0F, 1.0F, 1.0F};
    public float[] CandleRandomCurrentHeight = {0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
    public float[] CandleRandomHoverFreq = {};


    private static Random rand = new Random();
    //private String field_94136_s;
	
	private int m_iTimeSinceLastCandleFlame[];
	public boolean m_bPlayerNear;
	
	private static final int m_iMaxTimeBetweenFlameUpdates = 10;
	
	public FCTileEntityInfernalEnchanter()
	{
		super();
		
		m_iTimeSinceLastCandleFlame = new int[4];
		
		for ( int iTemp = 0; iTemp < 4; iTemp++ )
		{
			m_iTimeSinceLastCandleFlame[iTemp] = 0;
		}

        //Generate random max heights
        for(int i = 0; i < 5; i++)
        {
            CandleRandomMaxHeightMulti[i] = (float) (rand.nextFloat()/2 + .5);
            CandleRandomSpeedMulti[i] = (float) (rand.nextFloat()/2 + .5);
        }
		
		m_bPlayerNear = false;
	}
	
    @Override
    public void updateEntity()
    {
        super.updateEntity();
        
        drawBook();
        drawCandles();
    
        // note that this is done on the client as well, since it's entirely display related
        
        EntityPlayer entityplayer = worldObj.getClosestPlayer((float)xCoord + 0.5F, (float)yCoord + 0.5F, (float)zCoord + 0.5F, 4.5D );
        
        if (entityplayer != null)
        {
        	if ( !m_bPlayerNear )
        	{
        		//LightCandles();
        		
        		m_bPlayerNear = true;
        	}
        	else
        	{
        		//UpdateCandleFlames();
        	}
        }
        else
        {
        	m_bPlayerNear = false;
        }
    }
    
    
    
    public void drawBook()
    {
        //super.updateEntity();
        this.bookSpreadPrev = this.bookSpread;
        this.bookRotationPrev = this.bookRotation2;
        EntityPlayer var1 = this.worldObj.getClosestPlayer((double)((float)this.xCoord + 0.5F), (double)((float)this.yCoord + 0.5F), (double)((float)this.zCoord + 0.5F), 3.0D);

        if (var1 != null)
        {
            double var2 = var1.posX - (double)((float)this.xCoord + 0.5F);
            double var4 = var1.posZ - (double)((float)this.zCoord + 0.5F);
            this.bookRotation = (float)Math.atan2(var4, var2);
            this.bookSpread += 0.1F;

            if (this.bookSpread < 0.5F || rand.nextInt(40) == 0)
            {
                float var6 = this.field_70373_d;

                do
                {
                    this.field_70373_d += (float)(rand.nextInt(4) - rand.nextInt(4));
                }
                while (var6 == this.field_70373_d);
            }
        }
        else
        {
            this.bookRotation += 0.02F;
            this.bookSpread -= 0.1F;
        }

        while (this.bookRotation2 >= (float)Math.PI)
        {
            this.bookRotation2 -= ((float)Math.PI * 2F);
        }

        while (this.bookRotation2 < -(float)Math.PI)
        {
            this.bookRotation2 += ((float)Math.PI * 2F);
        }

        while (this.bookRotation >= (float)Math.PI)
        {
            this.bookRotation -= ((float)Math.PI * 2F);
        }

        while (this.bookRotation < -(float)Math.PI)
        {
            this.bookRotation += ((float)Math.PI * 2F);
        }

        float var7;

        for (var7 = this.bookRotation - this.bookRotation2; var7 >= (float)Math.PI; var7 -= ((float)Math.PI * 2F))
        {
            ;
        }

        while (var7 < -(float)Math.PI)
        {
            var7 += ((float)Math.PI * 2F);
        }

        this.bookRotation2 += var7 * 0.4F;

        if (this.bookSpread < 0.0F)
        {
            this.bookSpread = 0.0F;
        }

        if (this.bookSpread > 1.0F)
        {
            this.bookSpread = 1.0F;
        }

        ++this.tickCount;
        this.pageFlipPrev = this.pageFlip;
        float var3 = (this.field_70373_d - this.pageFlip) * 0.4F;
        float var8 = 0.2F;

        if (var3 < -var8)
        {
            var3 = -var8;
        }

        if (var3 > var8)
        {
            var3 = var8;
        }

        this.field_70374_e += (var3 - this.field_70374_e) * 0.9F;
        this.pageFlip += this.field_70374_e;
    }

    public void drawCandles()
    {
        this.candleRotationPrev = this.candleRotation2;
        EntityPlayer var1 = this.worldObj.getClosestPlayer((double)((float)this.xCoord + 0.5F), (double)((float)this.yCoord + 0.5F), (double)((float)this.zCoord + 0.5F), 3.0D);

        if (var1 != null)//if someone is near
        {
            this.candleRotation += 0.02F;
            this.shouldBounce = true;
            //Parabola function for height
            for(int i = 0; i < 5; i++)
            {
                //this.height = (float) (this.height + (-0.3F*(Math.pow((this.height - 0.5F), 2.0F)) + 0.1F));
                CandleRandomCurrentHeight[i] = (float) (CandleRandomCurrentHeight[i] + (-0.3F*(Math.pow((CandleRandomCurrentHeight[i] - 0.5F), 2.0F)) + 0.1F) * CandleRandomSpeedMulti[i]);
            }
        }
        else//if not
        {
            this.shouldBounce = false;
            for(int i = 0; i < 5; i++)
            {
                //this.height = (float) (this.height - (-0.3F*(Math.pow((this.height - 0.5F), 2.0F)) + 0.1F));
                CandleRandomCurrentHeight[i] = (float) (CandleRandomCurrentHeight[i] - (-0.3F*(Math.pow((CandleRandomCurrentHeight[i] - 0.5F), 2.0F)) + 0.1F) * CandleRandomSpeedMulti[i]);
            }
        }

        //Bound candle heights
        for(int i = 0; i < 5; i++)
        {
            while (CandleRandomCurrentHeight[i] > 1.0F * CandleRandomMaxHeightMulti[i])
            {
                CandleRandomCurrentHeight[i] = 1.0F * CandleRandomMaxHeightMulti[i];
            }

            while (CandleRandomCurrentHeight[i] < 0.0F)
            {
                CandleRandomMaxHeightMulti[i] = (float) (rand.nextFloat()/2 + .5);
                CandleRandomSpeedMulti[i] = (float) (rand.nextFloat()/2 + .5);

                CandleRandomCurrentHeight[i] = 0.0F;
            }
        }
        

        while (this.candleRotation2 >= (float)Math.PI)
        {
            this.candleRotation2 -= ((float)Math.PI * 2F);
        }

        while (this.candleRotation2 < -(float)Math.PI)
        {
            this.candleRotation2 += ((float)Math.PI * 2F);
        }

        while (this.candleRotation >= (float)Math.PI)
        {
            this.candleRotation -= ((float)Math.PI * 2F);
        }

        while (this.candleRotation < -(float)Math.PI)
        {
            this.candleRotation += ((float)Math.PI * 2F);
        }

        float var7;

        for (var7 = this.candleRotation - this.candleRotation2; var7 >= (float)Math.PI; var7 -= ((float)Math.PI * 2F))
        {
            ;
        }

        while (var7 < -(float)Math.PI)
        {
            var7 += ((float)Math.PI * 2F);
        }

        this.candleRotation2 += var7 * 0.4F;
    }
    
    
    
    //************* Class Specific Methods ************//
    
    private void LightCandles()
    {
    	for ( int iTemp = 0; iTemp < 4; iTemp++ )
    	{
    		DisplayCandleFlameAtIndex( iTemp );
    	}
    	
        worldObj.playSoundEffect( xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, "mob.ghast.fireball", 1.0F, worldObj.rand.nextFloat() * 0.4F + 0.8F );
    }
    
    private void UpdateCandleFlames()
    {
    	for ( int iTemp = 0; iTemp < 4; iTemp++ )
    	{
    		m_iTimeSinceLastCandleFlame[iTemp]++;
    		
    		if ( m_iTimeSinceLastCandleFlame[iTemp] > m_iMaxTimeBetweenFlameUpdates || worldObj.rand.nextInt( 5 ) == 0 )
    		{   		    		
    			DisplayCandleFlameAtIndex( iTemp );
    		}
    	}
    }
    
    private void DisplayCandleFlameAtIndex( int iCandleIndex )
    {
        double flameX = xCoord + ( 2.0D / 16.0D );
        double flameY = yCoord + FCBlockInfernalEnchanter.m_fBlockHeight + FCBlockInfernalEnchanter.m_fCandleHeight + 0.175F;
        double flameZ = zCoord  + ( 2.0D / 16.0D );
        
        if ( iCandleIndex == 1 || iCandleIndex == 3 )
        {
        	flameX = xCoord + ( 14.0D / 16.0D );
        }
        
        if ( iCandleIndex == 2 || iCandleIndex == 3 )
        {
        	flameZ = zCoord + ( 14.0D / 16.0D );
        }
        
        //DisplayCandleFlameAtLoc( flameX, flameY, flameZ );
        
		m_iTimeSinceLastCandleFlame[iCandleIndex] = 0; 		
    }

	public void DisplayCandleFlameAtLoc( double xCoord, double yCoord, double zCoord, double velX, double velY, double velZ )
	{
        worldObj.spawnParticle( "smoke", xCoord, yCoord, zCoord, velX, velY, velZ);
        worldObj.spawnParticle( "flame", xCoord, yCoord, zCoord, velX, velY, velZ);
	}
	
	
	/**
     * Writes a tile entity to NBT.
     */
    /*public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);

        if (this.func_94135_b())
        {
            par1NBTTagCompound.setString("CustomName", this.field_94136_s);
        }
    }*/

    /**
     * Reads a tile entity from NBT.
     */
    /*public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);

        if (par1NBTTagCompound.hasKey("CustomName"))
        {
            this.field_94136_s = par1NBTTagCompound.getString("CustomName");
        }
    }*/

    /*public String func_94133_a()
    {
        return this.func_94135_b() ? this.field_94136_s : "container.enchant";
    }

    public boolean func_94135_b()
    {
        return this.field_94136_s != null && this.field_94136_s.length() > 0;
    }

    public void func_94134_a(String par1Str)
    {
        this.field_94136_s = par1Str;
    }*/
}
