package mod.akrivus.kagic.entity.gem.corrupted;

import java.util.ArrayList;

import mod.akrivus.kagic.entity.EntityCorruptedGem;
import mod.akrivus.kagic.entity.gem.EntityAmethyst;
import mod.akrivus.kagic.entity.gem.GemCuts;
import mod.akrivus.kagic.entity.gem.GemPlacements;
import mod.akrivus.kagic.init.ModItems;
import mod.akrivus.kagic.init.ModSounds;
import mod.heimrarnadalr.kagic.util.Colors;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityCorruptedAmethyst extends EntityCorruptedGem {

	public EntityCorruptedAmethyst(World world) {
		super(world);
		this.setSize(1.9F, 2.8F);

		this.setCutPlacement(GemCuts.FACETED, GemPlacements.CHEST);

		// Apply entity attributes.
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(24.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
		
		this.droppedGemItem = ModItems.CORRUPTED_AMETHYST_GEM;
		this.droppedCrackedGemItem = ModItems.CRACKED_CORRUPTED_AMETHYST_GEM;
	}

	/*********************************************************
	 * Methods related to sounds.							*
	 *********************************************************/
	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.CORRUPTED_QUARTZ_AMBIENT;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.CORRUPTED_QUARTZ_HURT;
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.CORRUPTED_QUARTZ_DEATH;
	}

	/*********************************************************
	 * Methods related to rendering.						 *
	 *********************************************************/
	@Override
    protected int generateGemColor() {
    	return 0xDC64FD;
    }
	@Override
	protected int generateHairColor() {
		return Colors.arbiLerp(EntityAmethyst.HAIR_COLORS.get(this.getSpecial()));
	}
	@Override
	protected int generateSkinColor() {
		return Colors.arbiLerp(EntityAmethyst.SKIN_COLORS.get(this.getSpecial()));
	}

}