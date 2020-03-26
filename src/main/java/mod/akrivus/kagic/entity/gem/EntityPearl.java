package mod.akrivus.kagic.entity.gem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.common.base.Predicate;

import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.entity.ai.EntityAICommandGems;
import mod.akrivus.kagic.entity.ai.EntityAIDiamondHurtByTarget;
import mod.akrivus.kagic.entity.ai.EntityAIDiamondHurtTarget;
import mod.akrivus.kagic.entity.ai.EntityAIFollowDiamond;
import mod.akrivus.kagic.entity.ai.EntityAIPickUpItems;
import mod.akrivus.kagic.entity.ai.EntityAISitStill;
import mod.akrivus.kagic.entity.ai.EntityAIStay;
import mod.akrivus.kagic.init.ModItems;
import mod.akrivus.kagic.init.ModSounds;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.INpc;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IInventoryChangedListener;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.oredict.DyeUtils;

public class EntityPearl extends EntityGem implements IInventoryChangedListener, INpc {
	public static final HashMap<IBlockState, Double> PEARL_YIELDS = new HashMap<IBlockState, Double>();
	public static final double PEARL_DEFECTIVITY_MULTIPLIER = 1;
	public static final double PEARL_DEPTH_THRESHOLD = 0;
	public static final ArrayList<ResourceLocation> PEARL_HAIR_STYLES = new ArrayList<ResourceLocation>();
	public static final ArrayList<ResourceLocation> PEARL_DRESS_STYLES = new ArrayList<ResourceLocation>();
	private static final DataParameter<Integer> COLOR = EntityDataManager.<Integer>createKey(EntityPearl.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> HAIR_COLOR = EntityDataManager.<Integer>createKey(EntityPearl.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> VISOR_COLOR = EntityDataManager.<Integer>createKey(EntityPearl.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> DRESS_STYLE = EntityDataManager.<Integer>createKey(EntityPearl.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> NAKED = EntityDataManager.<Boolean>createKey(EntityPearl.class, DataSerializers.BOOLEAN);
	public String soulSong = "392935392935392939293929395";
	public InventoryBasic gemStorage;
	public InvWrapper gemStorageHandler;
	
	public EntityPearl(World worldIn) {
		super(worldIn);
		this.setSize(0.6F, 1.9F);
		this.initGemStorage();
		this.seePastDoors();
		this.soulSong = Integer.toString(this.rand.nextInt(999998999) + 1000);
		
		//Define valid gem cuts and placements
		this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.BACK_OF_HEAD);
		this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.FOREHEAD);
		this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.BACK);
		this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.CHEST);
		this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.BELLY);

		// Apply entity AI.
		this.stayAI = new EntityAIStay(this);
		this.tasks.addTask(1, new EntityAIAvoidEntity<EntityCreeper>(this, EntityCreeper.class, new Predicate<EntityCreeper>() {
			public boolean apply(EntityCreeper input) {
				return ((EntityCreeper)input).getCreeperState() == 1;
			}
        }, 6.0F, 1.0D, 1.2D));
		this.tasks.addTask(1, new EntityAIPickUpItems(this, 0.9D));
        this.tasks.addTask(1, new EntityAICommandGems(this, 0.6D));
		this.tasks.addTask(2, new EntityAIFollowDiamond(this, 1.0D));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAISitStill(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        
        // Apply entity attributes.
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
        this.droppedGemItem = ModItems.PEARL_GEM;
		this.droppedCrackedGemItem = ModItems.CRACKED_PEARL_GEM;
        
        // Register entity data.
        this.dataManager.register(COLOR, this.rand.nextInt(16));
        this.dataManager.register(HAIR_COLOR, this.dataManager.get(COLOR));
        this.dataManager.register(VISOR_COLOR, this.dataManager.get(COLOR));
        this.dataManager.register(DRESS_STYLE, 1);
        this.dataManager.register(NAKED, false);
	}

	@Override
	public int generateGemColor() {
		switch (this.getColor()) {
    	case 0:
    		return 0xFFFFFF;
    	case 1:
    		return 0xFEFFC9;
    	case 2:
    		return 0xFFF0FC;
    	case 3:
    		return 0xF3FCFF;
    	case 4:
    		return 0xFEFFD2;
    	case 5:
    		return 0xFBFFD5;
    	case 6:
    		return 0xFFEAF0;
    	case 7:
    		return 0xE1E1E1;
    	case 8:
    		return 0xF0F0F0;
    	case 9:
    		return 0xE4FFEC;
    	case 10:
    		return 0xF3DEFF;
    	case 11:
    		return 0x0000AA;
    	case 12:
    		return 0xFFECDB;
    	case 13:
    		return 0xF1FFC3;
    	case 14:
    		return 0xFFEDF0;
    	case 15:
    		return 0x7C7C7C;
		default:
			break;
     
    	}
		return 0xFFFFFF;
    }
	@Override
	public int generateSkinColor() {
		switch (this.getColor()) {
    	case 0:
    		return 0xFCFCFC;
    	case 1:
    		return 0xFFDBA3;
    	case 2:
    		return 0xFFD5FF;
    	case 3:
    		return 0xD8F6FF;
    	case 4:
    		return 0xFDFFA0;
    	case 5:
    		return 0xECFF9A;
    	case 6:
    		return 0xFFCFDE;
    	case 7:
    		return 0xA0A0A0;
    	case 8:
    		return 0xDBDBDB;
    	case 9:
    		return 0xA6FFE2;
    	case 10:
    		return 0xDEA9FF;
    	case 11:
    		return 0xC9E0FF;
    	case 12:
    		return 0xE4A773;
    	case 13:
    		return 0xC9FF88;
    	case 14:
    		return 0xFFB5B4;
    	case 15:
    		return 0x404040;
		default:
			break;
     
    	}
		return 0xFFFFFF;
    }
	@Override
	public int generateHairColor() {
		switch (this.getColor()) {
    	case 0:
    		return 0xC9C9C9;
    	case 1:
    		return 0xFFB33D;
    	case 2:
    		return 0xFF88FF;
    	case 3:
    		return 0xA3E1F3;
    	case 4:
    		return 0xFAFF34;
    	case 5:
    		return 0xDCFF43;
    	case 6:
    		return 0xF38BAA;
    	case 7:
    		return 0x828282;
    	case 8:
    		return 0xA0A0A0;
    	case 9:
    		return 0x00FFAE;
    	case 10:
    		return 0xC464FF;
    	case 11:
    		return 0x7399FF;
    	case 12:
    		return 0x97663C;
    	case 13:
    		return 0x82FF64;
    	case 14:
    		return 0xFF6161;
    	case 15:
    		return 0x191919;
		default:
			break;
     
    	}
		return 0xFFFFFF;
    }
	
	public void convertGems(int placement) {
    	this.setGemCut(GemCuts.CABOCHON.id);
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
	
	@Override
	public boolean canChangeInsigniaColorByDefault() {
		return false;
	}
	
	@Override
	public boolean canChangeUniformColorByDefault() {
		return false;
	}
	
	/*********************************************************
	 * Methods related to loading.                           *
	 *********************************************************/
	public void writeEntityToNBT(NBTTagCompound compound) {
        compound.setInteger("color", this.getColor());
        compound.setInteger("hairColor", this.getHairColor());
        compound.setInteger("gloveColor", this.getGloveColor());
        compound.setInteger("visorColor", this.getInsigniaColor());
        compound.setInteger("dressStyle", this.getDressStyle());
        compound.setBoolean("naked", this.isNaked());
        compound.setString("song", this.soulSong);
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.gemStorage.getSizeInventory(); ++i) {
            ItemStack itemstack = this.gemStorage.getStackInSlot(i);
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.setByte("slot", (byte)i);
            itemstack.writeToNBT(nbttagcompound);
            nbttaglist.appendTag(nbttagcompound);
        }
        compound.setTag("items", nbttaglist);
        super.writeEntityToNBT(compound);
	}

	public void readEntityFromNBT(NBTTagCompound compound) {
        this.setColor(compound.getInteger("color"));
        if (compound.hasKey("hairColor")) {
        	this.setHairColor(compound.getInteger("hairColor"));
        }
        else {
        	this.setHairColor(this.getColor());
        }
        if (compound.hasKey("gloveColor")) {
        	this.setHairColor(compound.getInteger("gloveColor"));
        }
        if (compound.hasKey("visorColor")) {
        	this.setVisorColor(compound.getInteger("visorColor"));
        }
        else {
        	this.setVisorColor(this.getColor());
        }
        this.setDressStyle(compound.getInteger("dressStyle"));
        this.setNaked(compound.getBoolean("naked"));
        this.soulSong = compound.getString("song");
        NBTTagList nbttaglist = compound.getTagList("items", 10);
        this.initGemStorage();
        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound.getByte("slot") & 255;
            if (j >= 0 && j < this.gemStorage.getSizeInventory()) {
                this.gemStorage.setInventorySlotContents(j, new ItemStack(nbttagcompound));
            }
        }
        super.readEntityFromNBT(compound);
    }
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
    	livingdata = super.onInitialSpawn(difficulty, livingdata);
    	this.setGemCut(GemCuts.CABOCHON.id);
    	this.itemDataToGemData(this.getColor());
    	this.setDressStyle(this.rand.nextInt(EntityPearl.PEARL_DRESS_STYLES.size()));
        return livingdata;
    }
	@Override
    public void itemDataToGemData(int data) {
		this.setColor(data);
		this.setHairColor(this.getColor());
		this.setSkinColor(this.getColor());
		this.setVisorColor(data);
		this.setInsigniaColor(data);
		this.setUniformColor(data);
		this.setGemColor(this.generateGemColor());
	}
    protected int generateHairStyle() {
    	return this.rand.nextInt(EntityPearl.PEARL_HAIR_STYLES.size());
    }

    /*********************************************************
	 * Methods related to interaction.                       *
	 *********************************************************/
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		if (!this.world.isRemote) {
			if (hand == EnumHand.MAIN_HAND) {
				ItemStack stack = player.getHeldItemMainhand();
				if (this.isTamed()) {
					if (this.isOwner(player)) {  
						//player.addStat(ModAchievements.THAT_WILL_BE_ALL);
		    			if (stack.getItem() == Items.SHEARS) {
		    				if (player.isSneaking()) {
		    					if (!this.isNaked()) {
		    						this.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, this.getSoundVolume(), this.getSoundPitch());
		    						this.entityDropItem(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, this.getInsigniaColor()), 0.0F);
		    						this.setNaked(true);
		    					}
		    				}
		    				else {
			    				int hair = this.getHairStyle() + 1;
			    				if (hair >= EntityPearl.PEARL_HAIR_STYLES.size() || hair < 0) {
			    					hair = 0;
			    				}
				        		this.setHairStyle(hair);
		    				}
		    				this.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, this.getSoundVolume(), this.getSoundPitch());
			        		if (!player.capabilities.isCreativeMode) {
			        			stack.damageItem(1, this);
			        		}
			        		return true;
		        		}
		    			else if (DyeUtils.isDye(stack)) {
		    				if (player.isSneaking()) {
		    					int oldColor = this.getHairColor();
				        		this.setHairColor(DyeUtils.metaFromStack(stack).orElse(0));
				        		if (!player.capabilities.isCreativeMode && oldColor != this.getHairColor()) {
				        			stack.shrink(1);
				        		}
		    				}
		    				else if (DyeUtils.isDye(stack)) {
			    				if (player.isSneaking()) {
			    					int oldColor = this.getEyeColor();
					        		this.setEyeColor(DyeUtils.metaFromStack(stack).orElse(0));
					        		if (!player.capabilities.isCreativeMode && oldColor != this.getEyeColor()) {
					        			stack.shrink(1);
					        		}
			    				}
		    				else {
			    				int oldColor = this.getColor();
				        		this.setColor(15 - stack.getItemDamage());
				        		if (!player.capabilities.isCreativeMode && oldColor != this.getColor()) {
				        			stack.shrink(1);
				        		}
		    				}
			        		return true;
		    			}

		    			else if (stack.getItem() == Item.getItemFromBlock(Blocks.STAINED_GLASS_PANE)) {
		    				if (player.isSneaking()) {
		    					if (this.hasVisor()) {
			    					ItemStack newstack = new ItemStack(Item.getItemFromBlock(Blocks.STAINED_GLASS_PANE));
			    					Item.getItemFromBlock(Blocks.STAINED_GLASS_PANE).setDamage(newstack, this.getVisorColor());
			    					this.entityDropItem(newstack, 0.0F);
			    					this.setHasVisor(false);
		    					}
		    				}
		    				else {
			    				if (this.hasVisor()) {
			    					ItemStack newstack = new ItemStack(Item.getItemFromBlock(Blocks.STAINED_GLASS_PANE));
			    					Item.getItemFromBlock(Blocks.STAINED_GLASS_PANE).setDamage(newstack, this.getVisorColor());
			    					this.entityDropItem(newstack, 0.0F);
			    				}
			    				else {
			    					this.setHasVisor(true);
			    				}
			    				this.setVisorColor(Item.getItemFromBlock(Blocks.STAINED_GLASS_PANE).getMetadata(stack));
			    				stack.shrink(1);
		    				}
		    			}
		    			else if (stack.getItem() == Item.getItemFromBlock(Blocks.WOOL)) {
		    				if (this.isNaked()) {
		    					this.setInsigniaColor(stack.getItemDamage());
		    					this.setNaked(false);
		    					stack.shrink(1);
		    				}
		    				if (stack.getCount() > 0) {
			    				if (player.isSneaking()) {
			    					int dress = this.getDressStyle() + 1;
				    				if (dress >= EntityPearl.PEARL_DRESS_STYLES.size() || dress < 0) {
				    					dress = 0;
				    				}
					        		this.setDressStyle(dress);
					        		this.playSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, this.getSoundVolume(), this.getSoundPitch());
					        		return true;
			    				}
			    			}
			    			else if (stack.getItem() == Item.getItemFromBlock(Blocks.CARPET)) {
			    				if (this.isNaked()) {
			    					this.setUniformColor(stack.getItemDamage());
			    					this.setNaked(false);
			    					stack.shrink(1);
			    				}
			  
			    				else {
				    				int oldColor = this.getInsigniaColor();
					        		this.setInsigniaColor(stack.getItemDamage());
					        		if (!player.capabilities.isCreativeMode) {
					        			if (oldColor != this.getInsigniaColor()) {
					        				stack.shrink(1);
					        			}
					        		}
			    				}
		    				}
			        		return true;
		    			}
		    			else if (this.isCoreItem(stack)) {
		    				return super.processInteract(player, hand);
		    			}
		    			else {
		            		this.openGUI(player);
		            		this.playObeySound();
		            		return true;
		            	}
		        	}
				}
			}
		}
		}
		return super.processInteract(player, hand);
    }
	public void onInventoryChanged(IInventory inventory) {
		ItemStack firstItem = this.gemStorage.getStackInSlot(0);
		this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, firstItem);
		/*if (firstItem.getItem() instanceof ItemSword) {
			if (this.getServitude() == EntityGem.SERVE_HUMAN && this.getOwner() != null) {
            	this.getOwner().addStat(ModAchievements.RENEGADE_PEARL);
            }
		}*/
	}
	protected void updateEquipmentIfNeeded(EntityItem itementity) {
        ItemStack itemstack = itementity.getItem();
        ItemStack itemstack1 = this.gemStorage.addItem(itemstack);
        if (itemstack1.isEmpty()) {
            itementity.setDead();
        }
        else {
            itemstack.setCount(itemstack1.getCount());
        }
    }
	public boolean canPickUpItem(Item itemIn) {
		return true;
	}
	public void setDefective(boolean defective) {
		if (defective) {
			this.targetTasks.addTask(1, new EntityAIDiamondHurtByTarget(this));
	        this.targetTasks.addTask(2, new EntityAIDiamondHurtTarget(this));
	        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, false, new Class[0]));
	        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<EntityLiving>(this, EntityLiving.class, 10, true, false, new Predicate<EntityLiving>() {
	            public boolean apply(EntityLiving input) {
	                return input != null && IMob.VISIBLE_MOB_SELECTOR.apply(input);
	            }
	        }));
	        this.setAttackAI();
		}
		super.setDefective(defective);
	}
	
	public void setColor(int color) {
		this.dataManager.set(COLOR, color);
		this.setGemColor(this.generateGemColor());
	}
	public int getColor() {
		return this.dataManager.get(COLOR);
	}
	public void setVisorColor(int color) {
		this.dataManager.set(VISOR_COLOR, color);
	}
	public int getVisorColor() {
		return this.dataManager.get(VISOR_COLOR);
	}
	
	@Override
	public void setHairColor(int color) {
		this.dataManager.set(HAIR_COLOR, color);
	}
	public int getShoeColor() {
		return this.dataManager.get(SHOE_COLOR);
	}
	@Override
	public void setShoeColor(int color) {
		this.dataManager.set(SHOE_COLOR, color);
	}
	@Override
	public int getHairColor() {
		return this.dataManager.get(HAIR_COLOR);
	}
	public void setDressStyle(int dress) {
		this.dataManager.set(DRESS_STYLE, dress);
	}
	public int getDressStyle() {
		return this.dataManager.get(DRESS_STYLE);
	}
	public void setNaked(boolean naked) {
		this.dataManager.set(NAKED, naked);
	}
	public boolean isNaked() {
		return this.dataManager.get(NAKED);
	}	public String getSpecialSkin() {
		return "_" + this.getSpecial();
	}
	public void setSpecialSkin(int special) {
		super.setSpecial(special);
		switch (special) {
		case 1:
        	this.setCustomNameTag("Blue Pearl");
        	this.setGemPlacement(GemPlacements.CHEST.id);
        	this.setSpecial(1);
        	this.setColor(3);
	        break;
		case 2:
        	this.setCustomNameTag("Yellow Pearl");
        	this.setGemPlacement(GemPlacements.CHEST.id);
        	this.setSpecial(2);
        	this.setColor(4);
        	break;
		case 3:
			this.setCustomNameTag("Pink Pearl");
        	this.setGemPlacement(GemPlacements.BELLY.id);
        	this.setSpecial(3);
        	this.setColor(6);
        	break;
		case 4:
			this.setCustomNameTag("Default Pearl");
        	this.setSpecial(0);
        	this.setColor(0);
        	break;
		}
	}
	public InventoryBasic getInventory() {
		return this.gemStorage;
	}
	
	/*********************************************************
	 * Methods related to living.                            *
	 *********************************************************/
	public void onLivingUpdate() {
		if (!this.canPickUpLoot()) {
			this.setCanPickUpLoot(this.isTamed());
		}
		if (!this.world.isRemote && this.ticksExisted % 20 == 0) {
			List<EntityPlayer> list = this.world.<EntityPlayer>getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().grow(Math.cos(this.rotationYaw), 2.0D, Math.sin(this.rotationYaw)));
			for (EntityPlayer entity : list) {
				if (this.isOwnedBy(entity)) {
					
				}
	        }
		}
		super.onLivingUpdate();
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		return super.attackEntityFrom(source, amount);
	}
	
	public void spawnHoloPearl() {
		EntityHoloPearl holoPearl = new EntityHoloPearl(this.world, this);
		holoPearl.onInitialSpawn(this.world.getDifficultyForLocation(this.getPosition()), null);
		this.world.spawnEntity(holoPearl);
	}
	
	/*********************************************************
     * Methods related to death.                             *
     *********************************************************/
    public void onDeath(DamageSource cause) {
    	this.setCanPickUpLoot(false);
    	switch (this.getColor()) {
    	case 0:
    		this.droppedGemItem = ModItems.WHITE_PEARL_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_WHITE_PEARL_GEM;
    		break;
    	case 1:
    		this.droppedGemItem = ModItems.ORANGE_PEARL_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_ORANGE_PEARL_GEM;
    		break;
    	case 2:
    		this.droppedGemItem = ModItems.MAGENTA_PEARL_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_MAGENTA_PEARL_GEM;
    		break;
    	case 3:
    		this.droppedGemItem = ModItems.LIGHT_BLUE_PEARL_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_LIGHT_BLUE_PEARL_GEM;
    		break;
    	case 4:
    		this.droppedGemItem = ModItems.YELLOW_PEARL_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_YELLOW_PEARL_GEM;
    		break;
    	case 5:
    		this.droppedGemItem = ModItems.LIME_PEARL_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_LIME_PEARL_GEM;
    		break;
    	case 6:
    		this.droppedGemItem = ModItems.PINK_PEARL_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_PINK_PEARL_GEM;
    		break;
    	case 7:
    		this.droppedGemItem = ModItems.GRAY_PEARL_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_GRAY_PEARL_GEM;
    		break;
    	case 8:
    		this.droppedGemItem = ModItems.LIGHT_GRAY_PEARL_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_LIGHT_GRAY_PEARL_GEM;
    		break;
    	case 9:
    		this.droppedGemItem = ModItems.CYAN_PEARL_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_CYAN_PEARL_GEM;
    		break;
    	case 10:
    		this.droppedGemItem = ModItems.PURPLE_PEARL_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_PURPLE_PEARL_GEM;
    		break;
    	case 11:
    		this.droppedGemItem = ModItems.BLUE_PEARL_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_BLUE_PEARL_GEM;
    		break;
    	case 12:
    		this.droppedGemItem = ModItems.BROWN_PEARL_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_BROWN_PEARL_GEM;
    		break;
    	case 13:
    		this.droppedGemItem = ModItems.GREEN_PEARL_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_GREEN_PEARL_GEM;
    		break;
    	case 14:
    		this.droppedGemItem = ModItems.RED_PEARL_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_RED_PEARL_GEM;
    		break;
    	case 15:
    		this.droppedGemItem = ModItems.BLACK_PEARL_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_BLACK_PEARL_GEM;
    		break;
    	}
    	super.onDeath(cause);
    }
	
	/*********************************************************
	 * Methods related to storage.                           *
	 *********************************************************/
	private void initGemStorage() {
        InventoryBasic gemstorage = this.gemStorage;
        this.gemStorage = new InventoryBasic("gemStorage", false, this.getMaxInventorySlots());
        if (gemstorage != null) {
            gemstorage.removeInventoryChangeListener(this);
            for (int i = 0; i < this.gemStorage.getSizeInventory(); ++i) {
                ItemStack itemstack = gemstorage.getStackInSlot(i);
                this.gemStorage.setInventorySlotContents(i, itemstack.copy());
            }
        }
        this.gemStorage.addInventoryChangeListener(this);
        this.gemStorageHandler = new InvWrapper(this.gemStorage);
        this.setCanPickUpLoot(this.isTamed());
    }
	public void openGUI(EntityPlayer playerEntity) {
        if (!this.world.isRemote && this.isTamed()) {
            this.gemStorage.setCustomName(this.getName());
            playerEntity.displayGUIChest(this.gemStorage);
        }
    }
	public int getMaxInventorySlots() {
		int slots = 36;
		if (this.getGemPlacement() == GemPlacements.FOREHEAD) {
			slots += 9;
		}
		if (this.isDefective()) {
			slots -= 9;
		}
		else if (this.isPrimary()) {
			slots += 9;
		}
		return slots;
	}

	

}
