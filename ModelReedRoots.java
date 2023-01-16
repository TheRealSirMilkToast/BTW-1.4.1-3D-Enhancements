// Made with Blockbench 4.5.2
// Exported for Minecraft version 1.5.2
// Paste this class into your mod and call render() in your Entity Render class
// Note: You may need to adjust the y values of the 'setRotationPoint's

package net.minecraft.src;

public class ModelReedRoots extends ModelBase {
	private final ModelRenderer bb_main;

	public ModelReedRoots() {
		textureWidth = 64;
		textureHeight = 64;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		this.bb_main.setTextureOffset(34, 43).addBox(-9.0F, -37.5F, 3.0F, 2, 16, 2, 0.0F);
		this.bb_main.setTextureOffset(20, 43).addBox(-14.0F, -35.5F, 2.0F, 2, 16, 2, 0.0F);
		this.bb_main.setTextureOffset(3, 44).addBox(-15.0F, -33.5F, 13.0F, 2, 16, 2, 0.0F);
		this.bb_main.setTextureOffset(31, 21).addBox(-10.5F, -38.5F, 9.0F, 3, 16, 3, 0.0F);
		this.bb_main.setTextureOffset(19, 22).addBox(-5.0F, -34.5F, 10.5F, 2, 16, 2, 0.0F);
		this.bb_main.setTextureOffset(2, 22).addBox(-4.0F, -37.5F, 1.0F, 3, 16, 3, 0.0F);
		this.bb_main.setTextureOffset(48, 14).addBox(-8.0F, -26.0F, 3.0F, 5, 2, 3, 0.0F);
		this.bb_main.setTextureOffset(46, 35).addBox(-14.0F, -25.0F, 10.0F, 5, 1, 4, 0.0F);
		this.bb_main.setTextureOffset(49, 45).addBox(-13.0F, -25.0F, 3.0F, 3, 1, 4, 0.0F);
		this.bb_main.setTextureOffset(46, 24).addBox(-6.0F, -25.0F, 9.0F, 5, 1, 4, 0.0F);
	}

	/**
	* Sets the models various rotation angles then renders the model.
	*/
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		bb_main.render(f5);
	}

	/**
	* Sets the model's various rotation angles. For bipeds, f and f1 are used for animating the movement of arms
	* and legs, where f represents the time(so that arms and legs swing back and forth) and f1 represents how
	* "far" arms and legs can swing at most.
	*/
	@Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {

    }
	
	/**
	*	Sets the rotation of a ModelRenderer. Only called if the ModelRenderer has a rotation
	*/
    public void setRotation(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}