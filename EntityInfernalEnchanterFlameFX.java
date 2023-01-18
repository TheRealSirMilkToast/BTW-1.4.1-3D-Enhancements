package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class EntityInfernalEnchanterFlameFX extends EntityFX
{
    /** the scale of the flame FX */
    private float flameScale;
    private double initialRotation;
    private double type;

    //par12 has been changed to initial rotation;
    //par10 has been changed to type of flame (0 for the one that lights the candles. 1 for the spinning ones)
    public EntityInfernalEnchanterFlameFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12)
    {
        super(par1World, par2, par4, par6, par8, 0.0D, 0.0D);
        this.initialRotation = par12;
        this.type = par10;
        par12 = 0.0D;
        par10 = 0.0D;
        
        //this.motionX = this.motionX * 0.009999999776482582D + par8;
        //this.motionY = this.motionY * 0.009999999776482582D + par10;
        //this.motionZ = this.motionZ * 0.009999999776482582D + par12;
        this.motionX = 0.0D;
        this.motionY = this.motionY * 0.009999999776482582D + par10;
        this.motionZ = 0.0D;
        
        double var10000 = par2 + (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
        var10000 = par4 + (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
        var10000 = par6 + (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
        this.flameScale = this.particleScale;
        
        if(this.type == 1.0)
        {
        	this.flameScale = this.flameScale/2;
        }
        
        this.particleRed = this.particleGreen = this.particleBlue = 1.0F;
        this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D)) + 4;
        //this.particleMaxAge = 10;
        this.noClip = true;
        this.setParticleTextureIndex(48);
    }

    public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        float var8 = ((float)this.particleAge + par2) / (float)this.particleMaxAge;
        this.particleScale = this.flameScale * (1.0F - var8 * var8 * 0.5F);
        super.renderParticle(par1Tessellator, par2, par3, par4, par5, par6, par7);
    }

    public int getBrightnessForRender(float par1)
    {
        float var2 = ((float)this.particleAge + par1) / (float)this.particleMaxAge;

        if (var2 < 0.0F)
        {
            var2 = 0.0F;
        }

        if (var2 > 1.0F)
        {
            var2 = 1.0F;
        }

        int var3 = super.getBrightnessForRender(par1);
        int var4 = var3 & 255;
        int var5 = var3 >> 16 & 255;
        var4 += (int)(var2 * 15.0F * 16.0F);

        if (var4 > 240)
        {
            var4 = 240;
        }

        return var4 | var5 << 16;
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness(float par1)
    {
        float var2 = ((float)this.particleAge + par1) / (float)this.particleMaxAge;

        if (var2 < 0.0F)
        {
            var2 = 0.0F;
        }

        if (var2 > 1.0F)
        {
            var2 = 1.0F;
        }

        float var3 = super.getBrightness(par1);
        return var3 * var2 + (1.0F - var2);
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }

        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        
        if(this.type == 0.0)
        {
        	if(this.particleAge < 8)
        	{
        		this.motionX = MathHelper.sin((float)Math.PI + (float)this.initialRotation + (this.particleAge/10))/9.0F;
        		this.motionZ = MathHelper.cos((float)Math.PI + (float)this.initialRotation + (this.particleAge/10))/9.0F;
        		this.motionY = 0.75/9F;
        	}
        	else
        	{
        		this.motionX = 0;
        		this.motionZ = 0;
        	}
        }
        else if(this.type == 1.0)
        {
        	
        }
        
        
        //this.motionX *= 0.9599999785423279D;
        //this.motionY *= 0.9599999785423279D;
        //this.motionZ *= 0.9599999785423279D;

        /*if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }*/
    }
}
