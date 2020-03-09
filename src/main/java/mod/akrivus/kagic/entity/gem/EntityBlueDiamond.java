package mod.akrivus.kagic.entity.gem;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.entity.ai.EntityAIAlignGems;
import mod.akrivus.kagic.entity.ai.EntityAIDiamondHurtByTarget;
import mod.akrivus.kagic.entity.ai.EntityAIDiamondHurtTarget;
import mod.akrivus.kagic.entity.ai.EntityAIStay;
import mod.akrivus.kagic.init.ModItems;
import mod.akrivus.kagic.init.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityBlueDiamond extends EntityGem {
	private static final DataParameter<Boolean> HOODED = EntityDataManager.<Boolean>createKey(EntityBlueDiamond.class, DataSerializers.BOOLEAN);
	private BossInfoServer healthBar = new BossInfoServer(this.getDisplayName(), BossInfo.Color.BLUE, BossInfo.Overlay.PROGRESS);
	private int hoodTimer = 0;
	private int lastSpecialAttack = 0;
	private int lastRecruitAttack = 0;
	
	public EntityBlueDiamond(World worldIn) {
		super(worldIn);
		this.setSize(3.0F, 13.8F);
		this.stepHeight = 2.0F;

		this.setCutPlacement(GemCuts.DIAMOND, GemPlacements.CHEST);
		
		// Boss stuff.
		this.experienceValue = 12000;
		this.isImmuneToFire = true;
		this.isDiamond = true;
		
		// Apply entity AI.
		this.stayAI = new EntityAIStay(this);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAlignGems(this, 1.0D));
        this.tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(4, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        
        // Apply targetting.
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.targetTasks.addTask(1, new EntityAIDiamondHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIDiamondHurtTarget(this));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityLivingBase>(this, EntityLivingBase.class, 10, true, false, new Predicate<EntityLivingBase>() {
            public boolean apply(EntityLivingBase input) {
                if (input instanceof EntityGem) {
                	return ((EntityGem) input).getServitude() == EntityGem.SERVE_HUMAN || input instanceof EntityBlueDiamond;
                }
                return input instanceof EntityPlayer;
            }
        }));
        
        // Apply entity attributes.
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(800.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(4.0D);
        this.droppedGemItem = ModItems.BLUE_DIAMOND_GEM;
		this.droppedCrackedGemItem = ModItems.CRACKED_BLUE_DIAMOND_GEM;
        
        // Register entity properties.
        this.dataManager.register(HOODED, true);
	}
	
	/*********************************************************
	 * Methods related to loading.                           *
	 *********************************************************/
	public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setBoolean("hooded", this.isHooded());
        compound.setInteger("lastSpecialAttack", this.lastSpecialAttack);
        compound.setInteger("lastRecruitAttack", this.lastRecruitAttack);
        this.setServitude(EntityGem.SERVE_BLUE_DIAMOND);
	}
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setHooded(compound.getBoolean("hooded"));
        this.lastSpecialAttack = compound.getInteger("lastSpecialAttack");
        this.lastRecruitAttack = compound.getInteger("lastRecruitAttack");
        this.setServitude(EntityGem.SERVE_BLUE_DIAMOND);
    }
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
    	//KAGIC.instance.chatInfoMessage("Blue Diamond onInitialSpawn called");
    	this.setServitude(EntityGem.SERVE_BLUE_DIAMOND);
    	EntityPearl pearl = new EntityPearl(this.world);
		pearl.setPosition(this.posX, this.posY, this.posZ);
		pearl.setServitude(EntityGem.SERVE_BLUE_DIAMOND);
		//pearl.setGemPlacement(GemPlacements.CHEST.id);
		pearl.setSpecialSkin(1);
		this.world.spawnEntity(pearl);
		pearl.onInitialSpawn(difficulty, null);
		return super.onInitialSpawn(difficulty, livingdata);
    }
    public boolean isHooded() {
    	return this.dataManager.get(HOODED);
    }
    public void setHooded(boolean hooded) {
    	this.dataManager.set(HOODED, hooded);
    }
    
    /*********************************************************
     * Methods related to combat.                            *
     *********************************************************/
    protected void updateAITasks() {
    	super.updateAITasks();
    	this.healthBar.setPercent(this.getHealth() / this.getMaxHealth());
    	if (this.getAttackTarget() == null) {
    		if (this.isHooded() && this.hoodTimer > 200 && this.rand.nextInt(20) == 0) {
    			this.setHooded(false);
    			this.hoodTimer = 0;
    		}
    	}
    	else {
    		this.setHooded(true);
    	}
    	++this.hoodTimer;
    	if (this.getAttackTarget() != null) {
	    	if (this.hurtResistantTime > 0 && this.lastRecruitAttack < 200 && this.lastRecruitAttack % 10 == 0 && this.rand.nextBoolean()) {
	    		EntityAmethyst amethyst = new EntityAmethyst(this.world);
				amethyst.setPosition(this.posX, this.posY, this.posZ);
				amethyst.setServitude(EntityGem.SERVE_BLUE_DIAMOND);
				amethyst.setRevengeTarget(this.getAttackTarget());
				amethyst.onInitialSpawn(this.world.getDifficultyForLocation(this.getPosition()), null);
				this.world.spawnEntity(amethyst);
	    	}
	    	++this.lastRecruitAttack;
	    	if (this.lastSpecialAttack < 0 && this.rand.nextBoolean()) {
	    		this.world.getWorldInfo().setCleanWeatherTime(0);
	    		this.world.getWorldInfo().setRainTime(1200);
	    		this.world.getWorldInfo().setThunderTime(1200);
	    		this.world.getWorldInfo().setRaining(true);
	    		this.lastSpecialAttack = 2400;
	    	}
	    	--this.lastSpecialAttack;
    	}
    }
    public void addTrackingPlayer(EntityPlayerMP player) {
        super.addTrackingPlayer(player);
        this.healthBar.addPlayer(player);
    }
    public void removeTrackingPlayer(EntityPlayerMP player) {
    	super.removeTrackingPlayer(player);
        this.healthBar.removePlayer(player);
    }
    public boolean attackEntityFrom(DamageSource source, float amount) {
    	if (source.getTrueSource() instanceof EntityLivingBase && this.getHealth() / 20 < amount && this.rand.nextInt(4) == 0 && !this.world.isRemote) {
			EntityAmethyst amethyst = new EntityAmethyst(this.world);
			amethyst.setPosition(this.posX, this.posY, this.posZ);
			amethyst.setServitude(EntityGem.SERVE_BLUE_DIAMOND);
			amethyst.setRevengeTarget((EntityLivingBase) source.getTrueSource());
			amethyst.onInitialSpawn(this.world.getDifficultyForLocation(this.getPosition()), null);
			this.world.spawnEntity(amethyst);
		}
    	if (this.lastRecruitAttack > 800 && this.rand.nextInt(3) == 0) {
    		this.lastRecruitAttack = 0;
    	}
    	if (this.rand.nextInt(30) > 0) {
    		this.lastSpecialAttack = 2400;
    	}
		return super.attackEntityFrom(source, amount);
	}
	public boolean attackEntityAsMob(Entity entityIn) {
		int divider = entityIn instanceof EntityPlayer ? 5 : 1;
		if (entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)((40 + this.rand.nextInt(40))) / divider)) {
            entityIn.motionX += -this.motionX;
            entityIn.motionZ += -this.motionZ;
            entityIn.motionY += 0.4D;
        }
		return super.attackEntityAsMob(entityIn);
	}
    
    /*********************************************************
	 * Methods related to entity death.                      *
	 *********************************************************/
	public void onDeath(DamageSource cause) {
		if (!this.world.isRemote) {
			if (cause.getTrueSource() instanceof EntityPlayer) {
				List<EntityGem> list = this.world.<EntityGem>getEntitiesWithinAABB(EntityGem.class, this.getEntityBoundingBox().grow(24.0D, 8.0D, 24.0D));
				EntityPlayer player = (EntityPlayer) cause.getTrueSource();
				for(EntityGem gem : list) {
					if (gem.getServitude() == EntityGem.SERVE_BLUE_DIAMOND && !gem.equals(this)) {
			    		gem.setOwnerId(player.getUniqueID());
			    		gem.setLeader(player);
			    		gem.setServitude(EntityGem.SERVE_HUMAN);
			        	gem.getNavigator().clearPath();
			        	gem.setAttackTarget(null);
			        	gem.setHealth(gem.getMaxHealth());
			        	gem.playTameEffect();
			        	gem.world.setEntityState(gem, (byte) 7);
			        	gem.playObeySound();
					}
				}
			}
		}
		super.onDeath(cause);
	}
    

}
