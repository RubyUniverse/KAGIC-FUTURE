package mod.akrivus.kagic.entity.gem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.common.base.Predicate;

import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.entity.ai.EntityAICommandGems;
import mod.akrivus.kagic.entity.ai.EntityAIExtinguishEntities;
import mod.akrivus.kagic.entity.ai.EntityAIExtinguishFires;
import mod.akrivus.kagic.entity.ai.EntityAIFollowDiamond;
import mod.akrivus.kagic.entity.ai.EntityAIGoToWater;
import mod.akrivus.kagic.entity.ai.EntityAIStandGuard;
import mod.akrivus.kagic.entity.ai.EntityAIStay;
import mod.akrivus.kagic.entity.ai.EntityAITillFarmland;
import mod.akrivus.kagic.init.ModItems;
import mod.akrivus.kagic.init.ModSounds;
import mod.heimrarnadalr.kagic.util.Colors;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.INpc;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IInventoryChangedListener;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.items.wrapper.InvWrapper;

public class EntityLapisLazuli extends EntityGem implements IInventoryChangedListener, INpc {
	public static final HashMap<IBlockState, Double> LAPIS_LAZULI_YIELDS = new HashMap<IBlockState, Double>();
	public static final double LAPIS_LAZULI_DEFECTIVITY_MULTIPLIER = 1;
	public static final double LAPIS_LAZULI_DEPTH_THRESHOLD = 72;
	public static final HashMap<Integer, ResourceLocation> LAPIS_LAZULI_HAIR_STYLES = new HashMap<Integer, ResourceLocation>();
	public int ticksFlying = 0;
	public boolean atWater = false;
	public InventoryBasic harvest;
	public InvWrapper harvestHandler;
	private int harvestTimer = 0;

	private static final int SKIN_COLOR_BEGIN = 0x4FEEFB;
	private static final int SKIN_COLOR_END = 0x5EC2FA;
	private static final int NUM_HAIRSTYLES = 1;
	private static final int HAIR_COLOR_BEGIN = 0x1B69D5;
	private static final int HAIR_COLOR_END = 0x4D4CBA;
	
	public EntityLapisLazuli(World worldIn) {
		super(worldIn);
		this.nativeColor = 11;
		this.setSize(0.6F, 1.9F);
		this.setPathPriority(PathNodeType.WATER, -1.0F);
		this.initGemStorage();
		this.visorChanceReciprocal = 20;
		
		//Define valid gem cuts and placements
		this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.BACK_OF_HEAD);
		this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.FOREHEAD);
		this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.LEFT_EYE);
		this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.RIGHT_EYE);
		this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.BACK);
		this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.CHEST);
		this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.BELLY);
		
		// Apply entity AI.
		this.stayAI = new EntityAIStay(this);
		this.tasks.addTask(1, new EntityAIAvoidEntity<EntityCreeper>(this, EntityCreeper.class, new Predicate<EntityCreeper>() {
			public boolean apply(EntityCreeper input) {
				return ((EntityCreeper)input).getCreeperState() == 1;
			}
        }, 6.0F, 1.0D, 1.2D));
		this.tasks.addTask(1, new EntityAIFollowDiamond(this, 1.0D));
        this.tasks.addTask(1, new EntityAICommandGems(this, 0.6D));
        this.tasks.addTask(3, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(3, new EntityAIExtinguishFires(this, 0.6D, 8));
        this.tasks.addTask(4, new EntityAIMoveTowardsTarget(this, 0.414D, 32.0F));
        this.tasks.addTask(4, new EntityAIExtinguishEntities(this, 1.2D));
        this.tasks.addTask(4, new EntityAITillFarmland(this, 0.6D));
        this.tasks.addTask(4, new EntityAIGoToWater(this, 0.6D));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityMob.class, 16.0F));
        this.tasks.addTask(6, new EntityAIStandGuard(this, 0.6D));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        
        // Apply target AI.
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
        
        // Apply entity attributes.
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
        this.droppedGemItem = ModItems.LAPIS_LAZULI_GEM;
		this.droppedCrackedGemItem = ModItems.CRACKED_LAPIS_LAZULI_GEM;
	}

	@Override
	protected int generateGemColor() {
    	return 0x1E8FF4;
    }

	@Override
	public void convertGems(int placement) {
    	this.setGemCut(GemCuts.TEARDROP.id);
    	switch (placement) {
    	case 0:
    		this.setGemPlacement(GemPlacements.FOREHEAD.id);
    		break;
    	case 1:
    		this.setGemPlacement(GemPlacements.CHEST.id);
    		break;
    	case 2:
    		this.setGemPlacement(GemPlacements.BACK.id);
    		break;
    	}
    }
	
	/*********************************************************
	 * Methods related to loading.                           *
	 *********************************************************/
	@Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        return super.onInitialSpawn(difficulty, livingdata);
    }
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < this.harvest.getSizeInventory(); ++i) {
			ItemStack itemstack = this.harvest.getStackInSlot(i);
			NBTTagCompound nbttagcompound = new NBTTagCompound();
			nbttagcompound.setByte("slot", (byte) i);
			itemstack.writeToNBT(nbttagcompound);
			nbttaglist.appendTag(nbttagcompound);
		}
		compound.setTag("harvestItems", nbttaglist);
		compound.setInteger("harvestTimer", this.harvestTimer);
		super.writeEntityToNBT(compound);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		this.initGemStorage();
		NBTTagList nbttaglist = compound.getTagList("harvestItems", 10);
		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound.getByte("slot") & 255;
			if (j >= 0 && j < this.harvest.getSizeInventory()) {
				this.harvest.setInventorySlotContents(j, new ItemStack(nbttagcompound));
			}
		}
		this.harvestTimer = compound.getInteger("harvestTimer");
		super.readEntityFromNBT(compound);
	}

	/*********************************************************
	 * Methods related to interaction.                       *
	 *********************************************************/
	public boolean alternateInteract(EntityPlayer player) {
		if (!this.world.isRemote) {
			if (this.isTamed()) {
				if (this.isOwner(player)) {
					if (this.isFarmer() || this.isAngler()) {
						this.entityDropItem(this.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND), 0.0F);
						this.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
						return true;
					}
					else if (this.isPrimary()) {
						this.world.getWorldInfo().setCleanWeatherTime(0);
			    		this.world.getWorldInfo().setRainTime(1200);
			    		this.world.getWorldInfo().setThunderTime(1200);
			    		this.world.getWorldInfo().setRaining(true);
			    		return true;
					}
				}
				else {
					player.sendMessage(new TextComponentTranslation("command.kagic.does_not_serve_you", this.getName()));
					return true;
				}
			}
		}
		return super.alternateInteract(player);
	}
	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		if (!this.world.isRemote) {
			if (hand == EnumHand.MAIN_HAND) {
				ItemStack stack = player.getHeldItemMainhand();
				if (this.isTamed()) {
					if (this.isOwner(player)) {
						if (this.isCoreItem(stack)) {
							return super.processInteract(player, hand);
						}
						else if (stack.getItem() instanceof ItemHoe || stack.getItem() instanceof ItemFishingRod) {
		        			boolean toolChanged = true;
							if (!this.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).isItemEqualIgnoreDurability(stack)) {
								this.entityDropItem(this.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND), 0.0F);
							} else {
								toolChanged = false;
							}
		        			if (toolChanged) {
								ItemStack heldItem = stack.copy();
								this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, heldItem);
								this.playObeySound();
								if (!player.capabilities.isCreativeMode) {
									stack.shrink(1);
								}
		        			}
							return true;
		        		}
						else if (this.isAngler()) {
							if (this.harvest.isEmpty()) {
								player.sendMessage(new TextComponentString("<" + this.getName() + "> " + new TextComponentTranslation("command.kagic.lapis_no_harvest").getUnformattedComponentText()));
							}
							else {
								player.sendMessage(new TextComponentString("<" + this.getName() + "> " + new TextComponentTranslation("command.kagic.lapis_harvest").getUnformattedComponentText()));
							}
							this.playObeySound();
							this.openGUI(player);
							return true;
						}
						else if (!this.isDefective()){
							player.rotationYaw = this.rotationYaw;
					        player.rotationPitch = this.rotationPitch;
					        player.startRiding(this);
					        //player.addStat(ModAchievements.GIVE_ME_A_LIFT);
					        this.playObeySound();
							return true;
						}
					}
				}
			}
		}
		return super.processInteract(player, hand);
    }

	@Override
	public void updatePassenger(Entity passenger) {
        super.updatePassenger(passenger);
        passenger.setPosition(this.posX, this.posY - 1.25F, this.posZ);
    }

	@Override
	public boolean shouldDismountInWater(Entity rider) {
		return false;
	}

	@Override
    public Entity getControllingPassenger() {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }

	@Override
	public boolean canBeSteered() {
		if (this.world.isRemote) {
			return true;
		}
		else {
	        Entity entity = this.getControllingPassenger();
	        if (entity instanceof EntityLivingBase) {
	        	EntityLivingBase rider = (EntityLivingBase) entity;
	        	return this.isOwner(rider);
	        }
	        return false;
        }
    }

	@Override
	public void travel(float strafe, float up, float forward) {
		Entity entity = this.getPassengers().isEmpty() ? null : (Entity)this.getPassengers().get(0);
        if (this.isBeingRidden() && this.canBeSteered()) {
        	this.rotationYaw = entity.rotationYaw;
            this.prevRotationYaw = this.rotationYaw;
            this.rotationPitch = Math.max(-90f, -90f - entity.rotationPitch * 2f);
            this.setRotation(this.rotationYaw, this.rotationPitch);
            this.renderYawOffset = this.rotationYaw;
            this.rotationYawHead = this.rotationYaw;
            this.stepHeight = 3.0F;
            this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;
            forward = ((EntityLivingBase) entity).moveForward;
            strafe = ((EntityLivingBase) entity).moveStrafing;
            if (this.canPassengerSteer()) {
                if (this.isInLava()) {
                	this.moveRelative(strafe, up, 0.91F, 0.02F);
                	this.motionY = forward / 10;
                    this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
                    this.motionX *= 0.5D;
                    this.motionY *= 0.5D;
                    this.motionZ *= 0.5D;
                }
                else {
	                float f = 0.91F * (this.isPrimary() ? 1.06F : 1.0F);
	                if (!this.onGround) {
		                float f1 = 0.16277136F / (f * f * f);
			            this.moveRelative(strafe, up, 0.91F, 0.2F * f1);
	                }
	                this.motionY = forward / 10;
		            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
		            this.motionX *= (double) f;
		            this.motionY *= (double) f;
		            this.motionZ *= (double) f;
                }
	        }
	        else {
	            this.motionX = 0.0D;
	            this.motionY = 0.0D;
	            this.motionZ = 0.0D;
	        }
	        this.prevLimbSwingAmount = 0f;/*this.limbSwingAmount;
	        double d1 = this.posX - this.prevPosX;
	        double d0 = this.posZ - this.prevPosZ;
	        float f2 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;
	        if (f2 > 1.0F) {
	            f2 = 1.0F;
	        }
	        this.limbSwingAmount += (f2 - this.limbSwingAmount) * 0.4F;
	        this.limbSwing += this.limbSwingAmount;*/
            this.limbSwingAmount = 0f;
        }
        else {
            this.stepHeight = 1.0F;
            this.jumpMovementFactor = 0.02F;
            super.travel(strafe, up, forward);
        }
	}
	
	public boolean isFarmer() {
		return this.isTamed() && this.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemHoe;
	}
	public boolean isAngler() {
		return this.isTamed() && this.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemFishingRod;
	}
	
	@Override
	public void onInventoryChanged(IInventory inventory) {
		this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, this.harvest.getStackInSlot(0));
	}
	
	public void openGUI(EntityPlayer playerEntity) {
		if (!this.world.isRemote && this.isTamed()) {
			this.harvest.setCustomName(new TextComponentTranslation("command.kagic.lapis_inventory", this.getName()).getUnformattedComponentText());
			playerEntity.displayGUIChest(this.harvest);
		}
	}
	
	private void initGemStorage() {
		InventoryBasic harvest = this.harvest;
		this.harvest = new InventoryBasic("harvest", true, 36);
		if (harvest != null) {
			harvest.removeInventoryChangeListener(this);
			for (int i = 0; i < this.harvest.getSizeInventory(); ++i) {
				ItemStack itemstack = harvest.getStackInSlot(i);
				this.harvest.setInventorySlotContents(i, itemstack.copy());
			}
		}
		this.harvest.addInventoryChangeListener(this);
		this.harvestHandler = new InvWrapper(this.harvest);
	}
	
	/*********************************************************
	 * Methods related to living.                            *
	 *********************************************************/
	@Override
	public void onLivingUpdate() {
		if (this.isBeingRidden() && !this.onGround) {
			++this.ticksFlying;
		}
		else {
			this.ticksFlying = 0;
		}
		if (this.isAngler() && this.atWater && this.ticksExisted % (this.rand.nextInt(1000) + 200) == 0) {
			LootTable table = this.world.getLootTableManager().getLootTableFromLocation(LootTableList.GAMEPLAY_FISHING);
			int luck = 0;
			for (PotionEffect effect : this.getActivePotionEffects()) {
				if (effect.getPotion() == MobEffects.UNLUCK) {
					luck -= effect.getAmplifier();
				}
				else if (effect.getPotion() == MobEffects.LUCK) {
					luck += effect.getAmplifier();
				}
			}
			LootContext context = new LootContext.Builder(this.world.getMinecraftServer().getWorld(this.dimension)).withLuck(luck).build();
			List<ItemStack> stacks = table.generateLootForPools(this.rand, context);
			this.harvest.addItem(stacks.get(this.rand.nextInt(stacks.size())));
			for (int i = 0; i < 5; ++i) {
				this.world.spawnParticle(EnumParticleTypes.WATER_DROP, this.posX + this.rand.nextFloat(), this.posY + this.rand.nextFloat(), this.posX + this.rand.nextFloat(), this.rand.nextFloat(), this.rand.nextFloat(), this.rand.nextFloat());
			}
		}
		else if (this.isFarmer() && this.ticksExisted % 20 == 0) {
			for (BlockPos.MutableBlockPos pos : BlockPos.getAllInBoxMutable(this.getPosition().add(-2, -2, -2), this.getPosition().add(2, -1, 2))) {
				IBlockState iblockstate = this.world.getBlockState(pos);
		        if (iblockstate.getBlock() == Blocks.FARMLAND && iblockstate.getValue(BlockFarmland.MOISTURE) < 7) {
		            this.world.setBlockState(pos, iblockstate.withProperty(BlockFarmland.MOISTURE, 7), 2);
		        }
			}
		}
		if (this.isTamed()) {
            if (!this.world.isRemote && !this.isDefective()) {
                List<EntityPlayer> list = this.world.<EntityPlayer>getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().grow(2.0D, 2.0D, 2.0D));
                for (EntityPlayer entity : list) {
                    if (entity.isWet() && this.isOwnedBy(entity) && !entity.isCreative()) {
                        for (int i = 0; i < 2; ++i) {
                            if (this.world.getBlockState(entity.getPosition().add(0, i, 0)).getBlock() == Blocks.WATER) {
                            	this.world.setBlockToAir(entity.getPosition().add(0, i, 0));
                            }
                        }
                    }
                }
            }
        }
		super.onLivingUpdate();
	}
	
	/*********************************************************
	 * Methods related to combat.                            *
	 *********************************************************/
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (this.isBeingRidden() && source.getTrueSource() != null) {
			if (this.getPassengers().get(0).equals(source.getTrueSource())) {
				return false;
			}
		}
		return super.attackEntityFrom(source, amount);
	}

	@Override
	public void fall(float distance, float damageMultiplier) {
		if (!this.isBeingRidden()) {
			super.fall(distance, damageMultiplier);
		}
	}
	
	@Override
    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {
		if (!this.isBeingRidden()) {
			super.updateFallState(y, onGroundIn, state, pos);
			if (this.isTamed()) {
				if (!this.world.isRemote && !this.isDefective()) {
					List<EntityPlayer> list = this.world.<EntityPlayer>getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().grow(2.0D, 2.0D, 2.0D));
					for (EntityPlayer entity : list) {
						if (this.isOwnedBy(entity) && !entity.isCreative()) {
							int blocksThatAreAir = 0;
							for (int i = -4; i < 0; ++i) {
								if (this.world.isAirBlock(entity.getPosition().add(0, i, 0))) {
									++blocksThatAreAir;
								}
							}
							if (blocksThatAreAir == 4) {
								entity.startRiding(this);
							}
						}
			        }
				}
			}
		}
	}
	
	@Override
	public boolean isOnLadder() {
        return false;
    }
	
	/*********************************************************
	 * Methods related to death.                             *
	 *********************************************************/
	@Override
	public void onDeath(DamageSource cause) {
		if (!this.world.isRemote) {
			
		}
		super.onDeath(cause);
	}

	/*********************************************************
	 * Methods related to rendering.                         *
	 *********************************************************/
	@Override
	protected int generateSkinColor() {
		ArrayList<Integer> skinColors = new ArrayList<Integer>();
		skinColors.add(EntityLapisLazuli.SKIN_COLOR_BEGIN);
		skinColors.add(EntityLapisLazuli.SKIN_COLOR_END);
		return Colors.arbiLerp(skinColors);
	}
	
	@Override
	protected int generateHairStyle() {
		return this.rand.nextInt(EntityLapisLazuli.NUM_HAIRSTYLES);
	}
	
	@Override
	protected int generateHairColor() {
		ArrayList<Integer> hairColors = new ArrayList<Integer>();
		hairColors.add(EntityLapisLazuli.HAIR_COLOR_BEGIN);
		hairColors.add(EntityLapisLazuli.HAIR_COLOR_END);
		return Colors.arbiLerp(hairColors);
	}
}
