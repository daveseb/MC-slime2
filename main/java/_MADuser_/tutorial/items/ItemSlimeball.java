package _MADuser_.tutorial.items;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import _MADuser_.tutorial.Reference;

public class ItemSlimeball extends Item
{
    public ItemSlimeball()
    {
        this.maxStackSize = 16;
        this.setCreativeTab(CreativeTabs.tabMisc);
    }

    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        if (!playerIn.capabilities.isCreativeMode)
        {
            --itemStackIn.stackSize;
        }

        worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.entity_snowball_throw, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        //worldIn.playSoundAtEntity(playerIn, Reference.MOD_ID + ":emp_gun", 0.5F, 1.0F);
               
        if (!worldIn.isRemote)
        {
            EntitySlimeball entityslimeball = new EntitySlimeball(worldIn, playerIn);
            entityslimeball.func_184538_a(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntityInWorld(entityslimeball);
        }

        playerIn.addStat(StatList.func_188057_b(this));
        return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
    }
}