// Made with Blockbench 4.5.2
// Exported for Minecraft version 1.5.2
// Paste this class into your mod and call render() in your Entity Render class
// Note: You may need to adjust the y values of the 'setRotationPoint's

package net.minecraft.src;

public class ModelWorkbench extends ModelBase {
	private final ModelRenderer bb_main;

	public ModelWorkbench() {
		textureWidth = 128;
		textureHeight = 128;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		this.bb_main.setTextureOffset(39, 19).addBox(-16.0F, -40.0F, 0.0F, 16, 1, 1, 0.0F);
		this.bb_main.setTextureOffset(0, 79).addBox(-16.0F, -39.0F, 0.0F, 16, 2, 16, 0.0F);
		this.bb_main.setTextureOffset(0, 33).addBox(-16.0F, -40.0F, 15.0F, 16, 1, 1, 0.0F);
		this.bb_main.setTextureOffset(0, 52).addBox(-1.0F, -40.0F, 1.0F, 1, 1, 14, 0.0F);
		this.bb_main.setTextureOffset(41, 43).addBox(-15.0F, -40.0F, 5.0F, 14, 1, 1, 0.0F);
		this.bb_main.setTextureOffset(39, 29).addBox(-15.0F, -40.0F, 10.0F, 14, 1, 1, 0.0F);
		this.bb_main.setTextureOffset(0, 36).addBox(-6.0F, -40.0F, 1.0F, 1, 1, 14, 0.0F);
		this.bb_main.setTextureOffset(0, 0).addBox(-11.0F, -40.0F, 1.0F, 1, 1, 14, 0.0F);
		this.bb_main.setTextureOffset(0, 98).addBox(-16.0F, -27.0F, 0.0F, 16, 3, 16, 0.0F);
		this.bb_main.setTextureOffset(61, 114).addBox(-4.0F, -37.0F, 0.0F, 4, 10, 4, 0.0F);
		this.bb_main.setTextureOffset(112, 114).addBox(-16.0F, -37.0F, 0.0F, 4, 10, 4, 0.0F);
		this.bb_main.setTextureOffset(95, 114).addBox(-16.0F, -37.0F, 12.0F, 4, 10, 4, 0.0F);
		this.bb_main.setTextureOffset(78, 114).addBox(-4.0F, -37.0F, 12.0F, 4, 10, 4, 0.0F);
		this.bb_main.setTextureOffset(82, 20).addBox(-12.0F, -29.0F, 0.5F, 8, 2, 15, 0.0F);
		this.bb_main.setTextureOffset(82, 55).addBox(-12.0F, -37.0F, 0.5F, 8, 1, 15, 0.0F);
		this.bb_main.setTextureOffset(82, 38).addBox(-12.0F, -32.5F, 0.5F, 8, 1, 15, 0.0F);
		this.bb_main.setTextureOffset(110, 11).addBox(-12.0F, -36.0F, 7.5F, 8, 7, 1, 0.0F);
		this.bb_main.setTextureOffset(106, 73).addBox(-13.0F, -37.0F, 3.0F, 1, 10, 10, 0.0F);
		this.bb_main.setTextureOffset(108, 94).addBox(-4.0F, -37.0F, 3.5F, 1, 10, 9, 0.0F);
		this.bb_main.setTextureOffset(83, 95).addBox(-2.5F, -35.5F, 4.0F, 1, 1, 4, 0.0F);
		this.bb_main.setTextureOffset(83, 101).addBox(-3.0F, -36.0F, 4.5F, 2, 2, 3, 0.0F);
		this.bb_main.setTextureOffset(86, 107).addBox(-2.5F, -34.0F, 5.5F, 1, 5, 1, 0.0F);
		this.bb_main.setTextureOffset(97, 81).addBox(-15.0F, -35.0F, 5.0F, 2, 2, 2, 0.0F);
		this.bb_main.setTextureOffset(100, 86).addBox(-14.5F, -35.5F, 5.5F, 1, 6, 1, 0.0F);
		this.bb_main.setTextureOffset(82, 81).addBox(-15.0F, -36.0F, 8.5F, 2, 2, 3, 0.0F);
		this.bb_main.setTextureOffset(82, 74).addBox(-14.5F, -35.5F, 8.0F, 1, 1, 4, 0.0F);
		this.bb_main.setTextureOffset(85, 88).addBox(-14.5F, -34.5F, 9.5F, 1, 5, 1, 0.0F);
		this.bb_main.setTextureOffset(99, 98).addBox(-3.0F, -35.0F, 9.0F, 2, 2, 2, 0.0F);
		this.bb_main.setTextureOffset(101, 103).addBox(-2.5F, -35.5F, 9.5F, 1, 7, 1, 0.0F);
		this.bb_main.setTextureOffset(0, 16).addBox(-16.0F, -40.0F, 1.0F, 1, 1, 14, 0.0F);
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