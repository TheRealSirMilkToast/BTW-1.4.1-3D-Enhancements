package net.minecraft.src;

public class TDEAddon extends FCAddOn {

	public static TDEAddon instance = new TDEAddon();

	private static final String ADDON_NAME = "3D Enhancements";
	private static final String ADDON_VERSION = "0.3";
	private static final String LANGUAGE_PREFIX = "TDE";

	private TDEAddon()
	{
		super(ADDON_NAME, ADDON_VERSION, LANGUAGE_PREFIX);
	}

	@Override
	public void Initialize()
	{
		FCAddOnHandler.LogMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");

		SetupTileEntities();

		FCRecipes.AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockWorkbench, 1), new Object[]
		{
			"###", "#X#", "###", '#', Block.planks, 'X', Item.ingotIron
		});

		FCAddOnHandler.LogMessage(this.getName() + " Initialized");
	}

	@Override
	public String GetLanguageFilePrefix() {
		return LANGUAGE_PREFIX;
	}

	public void SetupTileEntities()
	{
		TileEntity.addMapping(TileEntityWorkbench.class, "FCBlockWorkbench");
		TileEntityRenderer.instance.addSpecialRendererForClass (TileEntityWorkbench.class, new TDETileEntityRenderer() );

		TileEntity.addMapping(TileEntityReeds.class, "FCBlockReeds");
		TileEntityRenderer.instance.addSpecialRendererForClass (TileEntityReeds.class, new TDETileEntityRenderer() );

		TileEntity.addMapping(TileEntityReedRoots.class, "FCBlockReedRoots");
		TileEntityRenderer.instance.addSpecialRendererForClass (TileEntityReedRoots.class, new TDETileEntityRenderer() );
	}

}
