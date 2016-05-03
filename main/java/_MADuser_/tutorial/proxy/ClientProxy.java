package _MADuser_.tutorial.proxy;

import _MADuser_.tutorial.init.TutorialItems;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenders(){
		TutorialItems.registerRenders();
	}

}
