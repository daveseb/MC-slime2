package _MADuser_.tutorial.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import _MADuser_.tutorial.Reference;
import _MADuser_.tutorial.TutorialMod;
import _MADuser_.tutorial.items.EntitySlimeball;
import _MADuser_.tutorial.items.ItemSlimeball;

public class TutorialItems {
	
	public static Item test_item;
	public static Item slimeball_item;
	public static Item slimethrow;
	
	public static void init(){
		test_item = new Item().setUnlocalizedName("test_item");
		slimeball_item = new ItemSlimeball().setUnlocalizedName("slimeball_item");
		slimethrow = new Item().setUnlocalizedName("slimethrow");
	}
	
	public static void register(){
		GameRegistry.registerItem(test_item, test_item.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(slimeball_item, slimeball_item.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(slimethrow, slimethrow.getUnlocalizedName().substring(5));
		
		EntityRegistry.registerModEntity(EntitySlimeball.class, "slimethrow", 1, TutorialMod.instance, 80, 3, true);
	}
	
	public static void registerRenders(){
		registerRender(test_item);
		registerRender(slimeball_item);
		registerRender(slimethrow);
		
		RenderingRegistry.registerEntityRenderingHandler(EntitySlimeball.class, new RenderSnowball<EntitySnowball>(Minecraft.getMinecraft().getRenderManager(), slimethrow, Minecraft.getMinecraft().getRenderItem()));
	}

	
	public static void registerRender(Item item){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}
