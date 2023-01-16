package net.minecraft.src;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class TileEntityWorkbenchRenderer extends TileEntitySpecialRenderer
{
    private ModelWorkbench model = new ModelWorkbench();

    public void renderTileEntityAt(TileEntity tileEntity, double par2, double par4, double par6, float par8)
    {
        renderTileEntityWorkbenchTableAt((TileEntityWorkbench)tileEntity, par2, par4, par6, par8);
    }

    public void renderTileEntityWorkbenchTableAt(TileEntityWorkbench par1TileEntityWorkbenchTable, double par2, double par4, double par6, float par8)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        this.bindTextureByName("/ModelWorkbenchTexture.png");
        GL11.glEnable(GL11.GL_CULL_FACE);
        this.model.render((Entity)null, 0F, 0F, 0F, 0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }
}
