package net.minecraft.src;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class TDETileEntityRenderer extends TileEntitySpecialRenderer
{
    private ModelWorkbench modelWorkbench = new ModelWorkbench();
    private ModelReeds modelReeds = new ModelReeds();
    private ModelReedRoots modelReedRoots = new ModelReedRoots();
    

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

        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        GL11.glEnable(GL11.GL_CULL_FACE);
        model.render((Entity)null, 0F, 0F, 0F, 0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }
}
