package _MADuser_.tutorial.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntitySlimeball extends EntityThrowable
{
    public EntitySlimeball(World worldIn)
    {
        super(worldIn);
    }

    public EntitySlimeball(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntitySlimeball(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }
    
     /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null)
        {
            int i = 0;
            if (result.entityHit instanceof EntityCow)
            {
                i = 0;
            }
            else if (result.entityHit instanceof EntityLiving)
            {
                i = 4;
            }

            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)i);
        }

/*        for (int j = 0; j < 1000; ++j)
        {
            this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
        }
*/      //  this.worldObj.createExplosion(this,this.posX, this.posY, this.posZ, 5.0F, true);
		applyPotions(result);
		
        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
    }
    
    private void applyPotions(RayTraceResult result){
    	
    	List<PotionEffect> listEffects = new ArrayList<PotionEffect>();
    	
    	//2 is slowness potion
        Potion potion = Potion.getPotionById(2);
        PotionEffect potioneffect = new PotionEffect(potion,600,0);  //600 ticks is 30 seconds
    	listEffects.add(potioneffect);

        if (result.entityHit instanceof EntityCow)
        {
        	//6 is instant health potion
        	potion = Potion.getPotionById(6);
            potioneffect = new PotionEffect(potion,600,0);  //600 ticks is 30 seconds
        	listEffects.add(potioneffect);
        }
    	
        AxisAlignedBB axisalignedbb = this.getEntityBoundingBox().expand(1.0D, 1.0D, 1.0D);
        List<EntityLivingBase> list1 = this.worldObj.<EntityLivingBase>getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);

        if (!list1.isEmpty())
        {
            for (EntityLivingBase entitylivingbase : list1)
            {
                if (entitylivingbase.canBeHitWithPotion())
                {
                    double d0 = this.getDistanceSqToEntity(entitylivingbase);

                    if (d0 < 16.0D)
                    {
                        double d1 = 1.0D - Math.sqrt(d0) / 4.0D;

                        if (entitylivingbase == result.entityHit)
                        {
                            d1 = 10.0D;
                        }

                        for (PotionEffect potioneffect1 : listEffects)
                        {
                            potion = potioneffect1.getPotion();
                           
                            if (potion.isInstant())
                            {
                                potion.affectEntity(this, this.getThrower(), entitylivingbase, potioneffect1.getAmplifier(), d1);
                            }
                            else
                            {
                                int i = (int)(d1 * (double)potioneffect1.getDuration() + 0.5D);

                                if (i > 5)
                                {
                                    entitylivingbase.addPotionEffect(new PotionEffect(potion, i, potioneffect1.getAmplifier()));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}