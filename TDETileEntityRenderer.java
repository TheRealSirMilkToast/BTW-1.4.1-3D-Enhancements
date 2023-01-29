package net.minecraft.src;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class TDETileEntityRenderer extends TileEntitySpecialRenderer
{
    private ModelWorkbench modelWorkbench = new ModelWorkbench();
    private ModelReeds modelReeds = new ModelReeds();
    private ModelReedRoots modelReedRoots = new ModelReedRoots();
    private ModelBook enchantmentBook = new ModelBook();
    private ModelCandles modelCandles = new ModelCandles();
    

    public void renderTileEntityAt(TileEntity TE, double par2, double par4, double par6, float par8)
    {
        ModelBase model = null;

        if (TE instanceof TileEntityWorkbench)
        {
            this.bindTextureByName("/ModelWorkbenchTexture.png");
            model = this.modelWorkbench;
        }
        else if(TE instanceof TileEntityReeds)
        {
            this.bindTextureByName("/ModelReeds.png");
            model = this.modelReeds;
        }
        else if(TE instanceof TileEntityReedRoots)
        {
            this.bindTextureByName("/ModelReedRoots.png");
            model = this.modelReedRoots;
        }
        else if(TE instanceof FCTileEntityInfernalEnchanter)
        {
            //need to bind texture
            renderTileEntityInfernalEnchanterAt((FCTileEntityInfernalEnchanter)TE, par2, par4, par6, par8);
            return;
        }

        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glEnable(GL11.GL_CULL_FACE);
        model.render((Entity)null, 0F, 0F, 0F, 0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }




    public void renderTileEntityInfernalEnchanterAt(FCTileEntityInfernalEnchanter par1TileEntityEnchantmentTable, double par2, double par4, double par6, float par8)
    {
        //Book Code:
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 0.75F, (float)par6 + 0.5F);
        float var9 = (float)par1TileEntityEnchantmentTable.tickCount + par8;
        GL11.glTranslatef(0.0F, 0.1F + MathHelper.sin(var9 * 0.1F) * 0.01F, 0.0F);
        float var10;

        for (var10 = par1TileEntityEnchantmentTable.bookRotation2 - par1TileEntityEnchantmentTable.bookRotationPrev; var10 >= (float)Math.PI; var10 -= ((float)Math.PI * 2F))
        {
            ;
        }

        while (var10 < -(float)Math.PI)
        {
            var10 += ((float)Math.PI * 2F);
        }

        float var11 = par1TileEntityEnchantmentTable.bookRotationPrev + var10 * par8;
        GL11.glRotatef(-var11 * 180.0F / (float)Math.PI, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
        this.bindTextureByName("/item/book.png");
        float var12 = par1TileEntityEnchantmentTable.pageFlipPrev + (par1TileEntityEnchantmentTable.pageFlip - par1TileEntityEnchantmentTable.pageFlipPrev) * par8 + 0.25F;
        float var13 = par1TileEntityEnchantmentTable.pageFlipPrev + (par1TileEntityEnchantmentTable.pageFlip - par1TileEntityEnchantmentTable.pageFlipPrev) * par8 + 0.75F;
        var12 = (var12 - (float)MathHelper.truncateDoubleToInt((double)var12)) * 1.6F - 0.3F;
        var13 = (var13 - (float)MathHelper.truncateDoubleToInt((double)var13)) * 1.6F - 0.3F;

        if (var12 < 0.0F)
        {
            var12 = 0.0F;
        }

        if (var13 < 0.0F)
        {
            var13 = 0.0F;
        }

        if (var12 > 1.0F)
        {
            var12 = 1.0F;
        }

        if (var13 > 1.0F)
        {
            var13 = 1.0F;
        }

        float var14 = par1TileEntityEnchantmentTable.bookSpreadPrev + (par1TileEntityEnchantmentTable.bookSpread - par1TileEntityEnchantmentTable.bookSpreadPrev) * par8;
        GL11.glEnable(GL11.GL_CULL_FACE);
        this.enchantmentBook.render((Entity)null, var9, var12, var13, var14, 0.0F, 0.0625F);
        GL11.glPopMatrix();





        //Candle Code:
        for(int numCandles = 0; numCandles < 5; numCandles++)
        {
        	double xpos = par1TileEntityEnchantmentTable.xCoord;
            double ypos = par1TileEntityEnchantmentTable.yCoord;
            double zpos = par1TileEntityEnchantmentTable.zCoord;
            
            GL11.glPushMatrix();
            GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 0.75F, (float)par6 + 0.5F);

            //rotate based on which candle
            GL11.glRotatef((360.0F/5.0F)*numCandles, 0.0F, 1.0F, 0.0F);

            GL11.glTranslatef(0.0F, par1TileEntityEnchantmentTable.CandleRandomCurrentHeight[numCandles], 0.0F);

            
            //Default yPos for second flame particle to spawn at
            double secondFlameYPos = ypos + par1TileEntityEnchantmentTable.CandleRandomMaxHeightMulti[numCandles] + 0.4 - 0.1875;
            
            //Rise or Fall based on whether or not someone is near
            if(par1TileEntityEnchantmentTable.CandleRandomCurrentHeight[numCandles] >= 1.0F * par1TileEntityEnchantmentTable.CandleRandomMaxHeightMulti[numCandles])
            {

                if(!par1TileEntityEnchantmentTable.isLit[numCandles])
                {
                	par1TileEntityEnchantmentTable.isLit[numCandles] = true;
                	par1TileEntityEnchantmentTable.allLit =
                			   par1TileEntityEnchantmentTable.isLit[0]
        					&& par1TileEntityEnchantmentTable.isLit[1]
							&& par1TileEntityEnchantmentTable.isLit[2]
							&& par1TileEntityEnchantmentTable.isLit[3]
							&& par1TileEntityEnchantmentTable.isLit[4];

                    par1TileEntityEnchantmentTable.waitBeforeGoDown[numCandles] = 0;
                	
	                //Flame particles that display when the candles light
	                par1TileEntityEnchantmentTable.DisplayCandleFlameAtLoc
	                (
	                	false,
	                	numCandles,
	                	(double)xpos + 0.5,
	                    (double)ypos + par1TileEntityEnchantmentTable.CandleRandomMaxHeightMulti[numCandles] + 0.4 - 0.5,
	                    (double)zpos + 0.5,
	                    0.0D,
	                    0.0D,
	                    (2.0F*(float)Math.PI/5.0F)*numCandles
	                );
            	}
                
                if(par1TileEntityEnchantmentTable.isLit[numCandles])
                {
	                //Bounce up and down
	                var9 = (float)par1TileEntityEnchantmentTable.CandleTickCounts[numCandles] + par8;
	                double bounceAmmount = (MathHelper.sin(var9*0.075F + par1TileEntityEnchantmentTable.CandleRandomHoverOffset[numCandles]) * 0.05F) * par1TileEntityEnchantmentTable.CandleRandomHoverSpeedMulti[numCandles];
	                GL11.glTranslatef(0.0F, (float) bounceAmmount, 0.0F);
	                secondFlameYPos = secondFlameYPos + bounceAmmount;
                }
            }

            //Bind the unlit texture
            this.bindTextureByName("/textures/blocks/fcBlockCandle_infernal.png");

            if(par1TileEntityEnchantmentTable.isLit[numCandles])
            {
                //Bind the lit texture
                if(par1TileEntityEnchantmentTable.litTexture[numCandles] == true)
                {
                    this.bindTextureByName("/textures/blocks/fcBlockCandle_infernal_lit.png");
                }

	            //Spawn second dose of flame particles if possible
	    		par1TileEntityEnchantmentTable.DisplayCandleFlameAtLoc
	            (
	            	false,
	            	numCandles,
	            	(double)xpos + 0.5,
	            	secondFlameYPos,
	                (double)zpos + 0.5,
	                0.0D,
	                1.0D,
	                (2.0F*(float)Math.PI/5.0F)*numCandles
	            );
            }

            GL11.glEnable(GL11.GL_CULL_FACE);
            this.modelCandles.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
        }
    }
}
