package _MADuser_.tutorial.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import _MADuser_.tutorial.Reference;
import _MADuser_.tutorial.items.ItemSlimeball;

public class TutorialItems {
	
	public static Item test_item;
	public static Item slimeball_item;
	public static Item Slimeball_entity;
	
	public static void init(){
		test_item = new Item().setUnlocalizedName("test_item");
		slimeball_item = new ItemSlimeball().setUnlocalizedName("slimeball_item");
		//Slimeball_entity = new EntitySlimeball().setUnlocalizedName("Slimeball_entity");
	}
	
	public static void register(){
		GameRegistry.registerItem(test_item, test_item.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(slimeball_item, slimeball_item.getUnlocalizedName().substring(5));
		//GameRegistry.registerItem(Slimeball_entity, Slimeball_entity.getUnlocalizedName().substring(5));
	}
	
	public static void registerRenders(){
		registerRender(test_item);
		registerRender(slimeball_item);
		//registerRender(Slimeball_entity);
	}
	
	public static void registerRender(Item item){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}
