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
    public int[] CandleTickCounts = {0, 0, 0, 0, 0};
    public float height = 0.0F;
    public float[] CandleRandomMaxHeightMulti = {1.0F, 1.0F, 1.0F, 1.0F, 1.0F};
    public float[] CandleRandomSpeedMulti = {1.0F, 1.0F, 1.0F, 1.0F, 1.0F};
    public float[] CandleRandomCurrentHeight = {0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
    public float[] CandleRandomHoverOffset = {0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
    public float[] CandleRandomHoverSpeedMulti = {0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
    public int m_iTimeSinceLastCandleFlame[];
    public boolean[] isLit = {false, false, false, false, false};
    public boolean[] litTexture = {false, false, false, false, false};
    public boolean allLit = false;
    public int[] waitBeforeGoDown = {0, 0, 0, 0, 0};

    private static Random rand = new Random();
	
	public boolean m_bPlayerNear;
	
	//This needs to match the max age of the flame particles
	private static final int m_iMaxTimeBetweenFlameUpdates = 10;
	
	public FCTileEntityInfernalEnchanter()
	{
		super();
		
		m_iTimeSinceLastCandleFlame = new int[5];
		
		for ( int iTemp = 0; iTemp < 5; iTemp++ )
		{
			m_iTimeSinceLastCandleFlame[iTemp] = -1;
		}

        //Generate random max heights
        for(int i = 0; i < 5; i++)
        {
            CandleRandomMaxHeightMulti[i] = (float) (rand.nextFloat()/4 + 1);
            CandleRandomSpeedMulti[i] = (float) (rand.nextFloat()/3 + .5);
            CandleRandomHoverOffset[i] = (float) ((float) rand.nextFloat()*2*Math.PI);
            CandleRandomHoverSpeedMulti[i] = (float) (rand.nextFloat()/3 + .5);
        }
		
		m_bPlayerNear = false;
	}
	
    @Override
    public void updateEntity()
    {
        super.updateEntity();
        
        drawBook();
        drawCandles();
        UpdateCandleFlames();
    
        // note that this is done on the client as well, since it's entirely display related
        
        EntityPlayer entityplayer = worldObj.getClosestPlayer((float)xCoord + 0.5F, (float)yCoord + 0.5F, (float)zCoord + 0.5F, 4.5D );
        
        if (entityplayer != null)
        {
        	if ( !m_bPlayerNear )
        	{        		
        		m_bPlayerNear = true;
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
        EntityPlayer var1 = this.worldObj.getClosestPlayer((double)((float)this.xCoord + 0.5F), (double)((float)this.yCoord + 0.5F), (double)((float)this.zCoord + 0.5F), 3.0D);

        if (var1 != null)//if someone is near
        {
            //Parabola function for height
            for(int i = 0; i < 5; i++)
            {
                CandleRandomCurrentHeight[i] = (float) (CandleRandomCurrentHeight[i] + (-0.3F*(Math.pow((CandleRandomCurrentHeight[i] - 0.6F), 2.0F)) + 0.13F) * CandleRandomSpeedMulti[i]);
            }
        }
        else//if not
        {
            for(int i = 0; i < 5; i++)
            {
                if(isLit[i] && waitBeforeGoDown[i] < 15)
                {
                    waitBeforeGoDown[i]++;
                }
                else
                {
                    CandleRandomCurrentHeight[i] = (float) (CandleRandomCurrentHeight[i] - (-0.3F*(Math.pow((CandleRandomCurrentHeight[i] - 0.6F), 2.0F)) + 0.13F) * CandleRandomSpeedMulti[i] * 0.5F - 0.01F);
                    
                    isLit[i] = false;
                    litTexture[i] = false;
                    allLit = false;
                    CandleTickCounts[i] = 0;
                }
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
            	CandleRandomMaxHeightMulti[i] = (float) (rand.nextFloat()/4 + 1);
                CandleRandomSpeedMulti[i] = (float) (rand.nextFloat()/3 + .5);
                CandleRandomHoverOffset[i] = (float) ((float) rand.nextFloat()*2*Math.PI);
                CandleRandomHoverSpeedMulti[i] = (float) (rand.nextFloat()/3 + .5);

                CandleRandomCurrentHeight[i] = 0.0F;
            }
            
            if(isLit[i])
        	{
        		++CandleTickCounts[i];
        	}
        }
    }
    
    
    
    //************* Class Specific Methods ************//
    
    //schedules candle flames to be redrawn
    private void UpdateCandleFlames()
    {
    	for ( int iTemp = 0; iTemp < 5; iTemp++ )
    	{
    		if(m_iTimeSinceLastCandleFlame[iTemp] > -1)
    		{
	    		m_iTimeSinceLastCandleFlame[iTemp]++;
	    		
	    		if ( m_iTimeSinceLastCandleFlame[iTemp] > m_iMaxTimeBetweenFlameUpdates)
	    		{   		    		
	    			m_iTimeSinceLastCandleFlame[iTemp] = -1;
	    		}
    		}
    	}
    }

    //overrode some vars
    //velZ == initial rotation
    //velY == type
	public void DisplayCandleFlameAtLoc(boolean IgnoreTimer, int candleIndex, double xCoord, double yCoord, double zCoord, double velX, double velY, double velZ )
	{
		//if the type of flame is the second one
		if(velY == 1.0)
		{
			double randValue = 0.03125;
			xCoord = xCoord + (MathHelper.sin((float)Math.PI + (float)velZ)/9.0F)*7F + rand.nextFloat()*randValue*2 - randValue;
			zCoord = zCoord + (MathHelper.cos((float)Math.PI + (float)velZ)/9.0F)*7F + rand.nextFloat()*randValue*2 - randValue;
            yCoord = yCoord + 0.25 + rand.nextFloat()*randValue*2 - randValue;
		}
		else
		{
			this.worldObj.playSound(this.xCoord, this.yCoord, this.zCoord, "mob.ghast.fireball", 1.0F, 0.8F + this.rand.nextFloat() * 0.4F, false);
		}
		
		if (m_iTimeSinceLastCandleFlame[candleIndex] == -1)
		{
			m_iTimeSinceLastCandleFlame[candleIndex] = 0;

            //Only switch to lit texture if this is the first time a "second type" of flame particle is spawned
            if(velY == 1.0)
            {
                litTexture[candleIndex] = true;
            }
			
			worldObj.spawnParticle( "smoke", xCoord, yCoord, zCoord, 0F, 0F, 0F);
			worldObj.spawnParticle( "TDEInfernalEnchanterFlame", xCoord, yCoord, zCoord, velX, velY, velZ);
			worldObj.spawnParticle( "TDEInfernalEnchanterFlame", xCoord, yCoord, zCoord, velX, velY, velZ);
			worldObj.spawnParticle( "TDEInfernalEnchanterFlame", xCoord, yCoord, zCoord, velX, velY, velZ);
		}
		else if(IgnoreTimer)
		{
			worldObj.spawnParticle( "TDEInfernalEnchanterFlame", xCoord, yCoord, zCoord, velX, velY, velZ);
		}
	}
}
