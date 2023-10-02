package net.minecraft.src;

import java.util.Map;

public class TDEAddon extends FCAddOn {

	public static Boolean EnableCraftingBenchRecipe = false;
	public static Boolean UseCustomStokedFireTextures = false;
	public static Boolean AllowSeparateCandleLitTextures = false;

	public static TDEAddon instance = new TDEAddon();

	private static final String ADDON_NAME = "3D Enhancements";
	private static final String ADDON_VERSION = "1.2";
	private static final String LANGUAGE_PREFIX = "TDE";

	private TDEAddon()
	{
		super(ADDON_NAME, ADDON_VERSION, LANGUAGE_PREFIX);
	}

	@Override
	public void PreInitialize()
	{
		registerDefaultConfigValues();
	}

	@Override
	public void Initialize()
	{
		FCAddOnHandler.LogMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");

		SetupTileEntities();

		if(EnableCraftingBenchRecipe)
		{
			FCRecipes.AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockWorkbench, 1), new Object[]
			{
				"###", "#X#", "###", '#', Block.planks, 'X', Item.ingotIron
			});
		}

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

		TileEntityRenderer.instance.addSpecialRendererForClass (FCTileEntityInfernalEnchanter.class, new TDETileEntityRenderer() );

		TileEntityRenderer.instance.addSpecialRendererForClass (FCTileEntityAnvil.class, new TDETileEntityRenderer() );
	}

	public void registerDefaultConfigValues()
	{
		registerProperty("EnableCraftingBenchRecipe", "false", "Whether or not to enable the crafting bench recipe of 8 planks around 1 iron ingot");

		registerProperty("UseCustomStokedFireTextures", "false", "If false the game will use the default Stoked Fire textures.\n# If true the game will instead use:\n# fireStokedBottom_0, fireStokedBottom_1, fireStokedTop_0, fireStokedTop_1");

		registerProperty("AllowSeparateCandleLitTextures", "false", "If false the game will use the same texture for unlit and lit candles.\n# If true the game will instead use separate textures for unlit and lit candles. The lit textures have the same name as the regular textures except with '_lit' at the end.");
	}

	@Override
	public void handleConfigProperties(Map<String, String> propertyValues)
	{
		this.EnableCraftingBenchRecipe = Boolean.parseBoolean(propertyValues.get("EnableCraftingBenchRecipe"));
		this.UseCustomStokedFireTextures = Boolean.parseBoolean(propertyValues.get("UseCustomStokedFireTextures"));
		this.AllowSeparateCandleLitTextures = Boolean.parseBoolean(propertyValues.get("AllowSeparateCandleLitTextures"));
	}
}
