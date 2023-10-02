// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.5.2
// Paste this class into your mod and call render() in your Entity Render class
// Note: You may need to adjust the y values of the 'setRotationPoint's

package net.minecraft.src;

public class ModelSoulforge extends ModelBase {
	private final ModelRenderer bb_main;

	public ModelSoulforge() {
		textureWidth = 52;
		textureHeight = 50;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		this.bb_main.setTextureOffset(2, 32).addBox(-12.0F, -26.0F, 0.0F, 8, 2, 16, 0.0F);
		this.bb_main.setTextureOffset(18, 21).addBox(-10.0F, -33.0F, 6.0F, 4, 7, 4, 0.0F);
		this.bb_main.setTextureOffset(14, 12).addBox(-11.0F, -36.0F, 5.0F, 6, 3, 6, 0.0F);
		this.bb_main.setTextureOffset(12, 0).addBox(-11.0F, -40.0F, 4.0F, 6, 4, 8, 0.0F);
		this.bb_main.setTextureOffset(0, 0).addBox(-11.0F, -40.0F, 12.0F, 6, 1, 4, 0.0F);
		this.bb_main.setTextureOffset(32, 0).addBox(-10.0F, -40.0F, 2.0F, 4, 3, 2, 0.0F);
		this.bb_main.setTextureOffset(44, 0).addBox(-9.0F, -40.0F, 0.0F, 2, 2, 2, 0.0F);
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