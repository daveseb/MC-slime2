package _MADuser_.tutorial;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import _MADuser_.tutorial.init.TutorialItems;
import _MADuser_.tutorial.items.EntitySlimeball;
import _MADuser_.tutorial.proxy.CommonProxy;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class TutorialMod {

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		TutorialItems.init();
		TutorialItems.register();
		EntityRegistry.registerModEntity(EntitySlimeball.class, "tm:slimeball_entity", 1, this, 80, 3, true);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		proxy.registerRenders();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		
	}

}
