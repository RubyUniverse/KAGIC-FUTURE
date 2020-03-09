package mod.akrivus.kagic.entity.gem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.annotation.Nullable;

import mod.akrivus.kagic.entity.ai.EntityAIScareMobs;
import mod.akrivus.kagic.entity.ai.EntityAISitStill;
import mod.akrivus.kagic.init.KAGIC;
import mod.akrivus.kagic.init.ModItems;
import mod.akrivus.kagic.init.ModSounds;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityAgate extends EntityQuartzSoldier implements IAnimals {
	public static final HashMap<IBlockState, Double> AGATE_YIELDS = new HashMap<IBlockState, Double>();
	public static final double AGATE_DEFECTIVITY_MULTIPLIER = 1;
	public static final double AGATE_DEPTH_THRESHOLD = 64;
	
	public static final ArrayList<ResourceLocation> AGATE_HAIR_STYLES = new ArrayList<ResourceLocation>();
	public static final ArrayList<ResourceLocation> AGATE_BAND_STYLES = new ArrayList<ResourceLocation>();
	private static final DataParameter<Integer> COLOR = EntityDataManager.<Integer>createKey(EntityAgate.class, DataSerializers.VARINT);

	public static final float[][] AGATECOLORS = {
			{(float)0xF9/255f, (float)0xFF/255f, (float)0xFE/255f}, //#F9FFFE	White
			{(float)0xF9/255f, (float)0x80/255f, (float)0x1D/255f}, //#F9801D	Orange
			{(float)0xC7/255f, (float)0x4E/255f, (float)0xBD/255f}, //#C74EBD	Magenta
			{(float)0x3A/255f, (float)0xB3/255f, (float)0xDA/255f}, //#3AB3DA	Light blue
			{(float)0xFE/255f, (float)0xD8/255f, (float)0x3D/255f}, //#FED83D	Yellow
			{(float)0x80/255f, (float)0xC7/255f, (float)0x1F/255f}, //#80C71F	Lime
			{(float)0xF3/255f, (float)0x8B/255f, (float)0xAA/255f}, //#F38BAA	Pink
			{(float)0x47/255f, (float)0x4F/255f, (float)0x52/255f}, //#474F52	Gray
			{(float)0x9D/255f, (float)0x9D/255f, (float)0x97/255f}, //#9D9D97	Light gray
			{(float)0x16/255f, (float)0x9C/255f, (float)0x9C/255f}, //#169C9C	Cyan
			{(float)0x89/255f, (float)0x32/255f, (float)0xB8/255f}, //#8932B8	Purple
			{(float)0x3C/255f, (float)0x44/255f, (float)0xAA/255f}, //#3C44AA	Blue
			{(float)0x83/255f, (float)0x54/255f, (float)0x32/255f}, //#835432	Brown
			{(float)0x5E/255f, (float)0x7C/255f, (float)0x16/255f}, //#5E7C16	Green
			{(float)0xB0/255f, (float)0x2E/255f, (float)0x26/255f}, //#B02E26	Red
			{(float)0x1D/255f, (float)0x1D/255f, (float)0x21/255f}  //#1D1D21	Black
	};
	
	public static final float[][] BANDCOLORS = {
			{(float)0xF9/255f, (float)0xFF/255f, (float)0xFE/255f}, //#F9FFFE	White
			{(float)0xF9/255f, (float)0x80/255f, (float)0x1D/255f}, //#F9801D	Orange
			{(float)0x9D/255f, (float)0x9D/255f, (float)0x97/255f}, //Magenta Crazy Lace has gray bands
			{(float)0xF3/255f, (float)0x8B/255f, (float)0xAA/255f}, //persian
			{(float)0xFE/255f, (float)0xD8/255f, (float)0x3D/255f}, //#FED83D	Yellow
			{(float)0xF9/255f, (float)0xFF/255f, (float)0xFE/255f}, //Lime Moss has White bands
			{(float)0xF3/255f, (float)0x8B/255f, (float)0xAA/255f}, //#F38BAA	Pink
			{(float)0x47/255f, (float)0x4F/255f, (float)0x52/255f}, //#474F52	Gray
			{(float)0x9D/255f, (float)0x9D/255f, (float)0x97/255f}, //#9D9D97	Light gray
			{(float)0xFE/255f, (float)0xD8/255f, (float)0x3D/255f}, //polyherdoid
			{(float)0xF9/255f, (float)0x80/255f, (float)0x1D/255f}, //Purple Agua Nueva has Orange bands
			{(float)0x3A/255f, (float)0xB3/255f, (float)0xDA/255f}, //Blue Lace has Light blue bands
			{(float)0x3A/255f, (float)0xB3/255f, (float)0xDA/255f}, //Zimbabwe has Light blue bands
			{(float)0x9D/255f, (float)0x9D/255f, (float)0x97/255f}, //Green Moss has Light gray bands
			{(float)0xF9/255f, (float)0xFF/255f, (float)0xFE/255f}, //Red Lake Superior has White bands
			{(float)0xF9/255f, (float)0xFF/255f, (float)0xFE/255f}, //Black Onyx has White bands
	};
	
	public EntityAgate(World worldIn) {
		super(worldIn);

		//Define valid gem cuts and placements
		this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.BACK_OF_HEAD);
		this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.FOREHEAD);
		this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.LEFT_EYE);
		this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.RIGHT_EYE);
		this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.LEFT_CHEEK);
		this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.RIGHT_CHEEK);
		this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.LEFT_SHOULDER);
		this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.RIGHT_SHOULDER);
		this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.LEFT_HAND);
		this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.RIGHT_HAND);
		this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.BACK);
		this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.CHEST);
		this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.BELLY);
		this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.LEFT_THIGH);
		this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.RIGHT_THIGH);
		this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.LEFT_KNEE);
		this.setCutPlacement(GemCuts.TEARDROP, GemPlacements.RIGHT_KNEE);

		// Apply entity AI.
		this.tasks.addTask(2, new EntityAISitStill(this, 1.0D));
		this.tasks.addTask(3, new EntityAIScareMobs(this));
        
        // Apply entity attributes.
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
        
        this.droppedGemItem = ModItems.AGATE_GEM;
		this.droppedCrackedGemItem = ModItems.CRACKED_AGATE_GEM;
        
        // Register entity data.
        this.dataManager.register(COLOR, Integer.valueOf(new Random().nextInt(16)));
        this.nativeColor = this.dataManager.get(COLOR);
	}

    protected int generateGemColor() {
    	switch (this.getColor()) {
    	case 0:
    		return 0xFFFFFF;
    	case 1:
    		return 0xCB7226;
    	case 2:
    		return 0xAE48D4;
    	case 3:
    		return 0x215493;
    	case 4:
    		return 0xFEFE4C;
    	case 5:
    		return 0x469300;
    	case 6:
    		return 0xE8759B;
    	case 7:
    		return 0x939393;
    	case 8:
    		return 0x8F8F8F;
    	case 9:
    		return 0x6699B3;
    	case 10:
    		return 0x7B3BAE;
    	case 11:
    		return 0x3B54BA;
    	case 12:
    		return 0x4E341B;
    	case 13:
    		return 0x4C6519;
    	case 14:
    		return 0x963030;
    	case 15:
    		return 0x333333;
    	}
    	return 0x9367F8;
    }
    public void convertGems(int placement) {
    	this.setGemCut(GemCuts.TEARDROP.id);
    	switch (placement) {
    	case 0:
    		this.setGemPlacement(GemPlacements.BACK_OF_HEAD.id);
    		break;
    	case 1:
    		this.setGemPlacement(GemPlacements.FOREHEAD.id);
    		break;
    	case 2:
    		this.setGemPlacement(GemPlacements.RIGHT_SHOULDER.id);
    		break;
    	case 3:
    		this.setGemPlacement(GemPlacements.LEFT_SHOULDER.id);
    		break;
    	}
    }
    
	/*********************************************************
	 * Methods related to loading.                           *
	 *********************************************************/
	public void writeEntityToNBT(NBTTagCompound compound) {
        compound.setInteger("color", this.dataManager.get(COLOR).intValue());
        super.writeEntityToNBT(compound);
	}
    public void readEntityFromNBT(NBTTagCompound compound) {
        this.dataManager.set(COLOR, compound.getInteger("color"));
        if (compound.hasKey("holly")) {
        	this.setSpecial(compound.getBoolean("holly") ? 1 : 0);
        	this.setGemPlacement(GemPlacements.BACK_OF_HEAD.id);
        	this.setGemCut(GemCuts.TEARDROP.id);
        	if (this.getUniformColor() > 15) {
            	this.setUniformColor(3);
            	this.nativeColor = 3;
            	this.setSpecial(1);
        	}
        }
        super.readEntityFromNBT(compound);
    }
    
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
    	boolean holly = this.rand.nextInt(9) == 0 || this.getSpecial() == 1;
        this.itemDataToGemData(this.dataManager.get(COLOR));
    	if (holly) {
        	this.setCustomNameTag(new TextComponentTranslation("entity.kagic.agate_16.name").getUnformattedComponentText());
        	this.setGemPlacement(GemPlacements.BACK_OF_HEAD.id);
        	this.setGemCut(GemCuts.TEARDROP.id);
        	this.nativeColor = 3;
        	this.setSpecial(1);
        } else {
            //this.setHairStyle(this.rand.nextInt(EntityAgate.AGATE_HAIR_STYLES.size()));
            //KAGIC.instance.chatInfoMessage("Set hairstyle to " + this.getHairStyle());
        	this.setCustomNameTag(new TextComponentTranslation(String.format("entity.kagic.agate_%1$d.name", this.getColor())).getUnformattedComponentText());
        	this.setUniformColor(this.dataManager.get(COLOR));
    		this.nativeColor = this.getUniformColor();
        }
        return super.onInitialSpawn(difficulty, livingdata);
    }
    
    @Override
    public void itemDataToGemData(int data) {
    	this.dataManager.set(COLOR, data);
		this.setSkinColor(this.generateSkinColor());
		this.setHairStyle(this.generateHairStyle());
		this.setHairColor(this.generateHairColor());
		this.setGemColor(this.generateGemColor());
        this.setCustomNameTag(new TextComponentTranslation(String.format("entity.kagic.agate_%1$d.name", data)).getUnformattedComponentText());
        if (data > 15) {
        	this.setUniformColor(3);
        	this.nativeColor = 3;
        	this.setSpecial(1);
        } else {
        	this.setUniformColor(data);
    		this.nativeColor = data;
        	this.setSpecial(0);
        }
	}

    /*********************************************************
	 * Methods related to interaction.                       *
	 *********************************************************/
	@Override
	public boolean alternateInteract(EntityPlayer player) {
		super.alternateInteract(player);
		KAGIC.instance.chatInfoMessage("Agate type is " + this.getColor());
		return false;
	}

	public int getColor() {
		if (this.isHolly()) {
			return 3;
		}
		return this.dataManager.get(COLOR);
	}
	
	public boolean isHolly() {
		return this.getSpecial() == 1 || this.getName().toLowerCase().contains("holly");
	}	
	
	@Override
	public void whenDefective() {
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0D);
        this.setSize(0.72F, 1.84F);
	}

	/*********************************************************
     * Methods related to death.                             *
     *********************************************************/
    public void onDeath(DamageSource cause) {
    	switch (this.getColor()) {
    	case 0:
    		this.droppedGemItem = ModItems.WHITE_AGATE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_WHITE_AGATE_GEM;
    		break;
    	case 1:
    		this.droppedGemItem = ModItems.ORANGE_AGATE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_ORANGE_AGATE_GEM;
    		break;
    	case 2:
    		this.droppedGemItem = ModItems.MAGENTA_AGATE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_MAGENTA_AGATE_GEM;
    		break;
    	case 3:
    		this.droppedGemItem = ModItems.LIGHT_BLUE_AGATE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_LIGHT_BLUE_AGATE_GEM;
    		break;
    	case 4:
    		this.droppedGemItem = ModItems.YELLOW_AGATE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_YELLOW_AGATE_GEM;
    		break;
    	case 5:
    		this.droppedGemItem = ModItems.LIME_AGATE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_LIME_AGATE_GEM;
    		break;
    	case 6:
    		this.droppedGemItem = ModItems.PINK_AGATE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_PINK_AGATE_GEM;
    		break;
    	case 7:
    		this.droppedGemItem = ModItems.GRAY_AGATE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_GRAY_AGATE_GEM;
    		break;
    	case 8:
    		this.droppedGemItem = ModItems.LIGHT_GRAY_AGATE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_LIGHT_GRAY_AGATE_GEM;
    		break;
    	case 9:
    		this.droppedGemItem = ModItems.CYAN_AGATE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_CYAN_AGATE_GEM;
    		break;
    	case 10:
    		this.droppedGemItem = ModItems.PURPLE_AGATE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_PURPLE_AGATE_GEM;
    		break;
    	case 11:
    		this.droppedGemItem = ModItems.BLUE_AGATE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_BLUE_AGATE_GEM;
    		break;
    	case 12:
    		this.droppedGemItem = ModItems.BROWN_AGATE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_BROWN_AGATE_GEM;
    		break;
    	case 13:
    		this.droppedGemItem = ModItems.GREEN_AGATE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_GREEN_AGATE_GEM;
    		break;
    	case 14:
    		this.droppedGemItem = ModItems.RED_AGATE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_RED_AGATE_GEM;
    		break;
    	case 15:
    		this.droppedGemItem = ModItems.BLACK_AGATE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_BLACK_AGATE_GEM;
    		break;
    	}
    	if (this.isHolly()) {
    		this.droppedGemItem = ModItems.HOLLY_BLUE_AGATE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_HOLLY_BLUE_AGATE_GEM;
    	}
    	super.onDeath(cause);
    }
	

	/*********************************************************
	 * Methods related to rendering.                         *
	 *********************************************************/
	@Override
	protected int generateHairStyle() {
		if (this.isHolly()) {
			return 0;
		}
		return this.rand.nextInt(EntityAgate.AGATE_HAIR_STYLES.size());
	}
	
	@Override
	public boolean hasUniformVariant(GemPlacements placement) {
		switch(placement) {
		case BELLY:
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean hasCape() {
		return true;
	}
	
	public boolean hasBands() {
		if (this.isHolly()) {
			return false;
		}
		
		switch(this.getColor()) {
		case 2: return true;
		case 3: return true;
		case 5: return true;
		case 9: return true;
		case 10: return true;
		case 11: return true;
		case 12: return true;
		case 13: return true;
		case 14: return true;
		case 15: return true;
		default: return false;
		}
	}
}