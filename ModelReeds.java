// Made with Blockbench 4.5.2
// Exported for Minecraft version 1.5.2
// Paste this class into your mod and call render() in your Entity Render class
// Note: You may need to adjust the y values of the 'setRotationPoint's

package net.minecraft.src;

public class ModelReeds extends ModelBase {
	private final ModelRenderer bb_main;

	public ModelReeds() {
		textureWidth = 64;
		textureHeight = 64;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		this.bb_main.setTextureOffset(45, 42).addBox(-9.0F, -37.5F, 3.0F, 2, 16, 2, 0.0F);
		this.bb_main.setTextureOffset(27, 42).addBox(-14.0F, -35.5F, 2.0F, 2, 16, 2, 0.0F);
		this.bb_main.setTextureOffset(9, 42).addBox(-15.0F, -33.5F, 13.0F, 2, 16, 2, 0.0F);
		this.bb_main.setTextureOffset(45, 9).addBox(-10.5F, -38.5F, 9.0F, 3, 16, 3, 0.0F);
		this.bb_main.setTextureOffset(27, 10).addBox(-5.0F, -34.5F, 10.5F, 2, 16, 2, 0.0F);
		this.bb_main.setTextureOffset(6, 7).addBox(-4.0F, -37.5F, 1.0F, 3, 16, 3, 0.0F);
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