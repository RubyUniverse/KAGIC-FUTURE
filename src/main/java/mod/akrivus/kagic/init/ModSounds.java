package mod.akrivus.kagic.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;

public class ModSounds {
	public static final SoundEvent BLOCK_GEM_SEED_HATCH = new SoundEvent(new ResourceLocation("kagic:blocks.gem_seed_hatch"));
	public static final SoundEvent BLOCK_INJECTOR_OPEN = new SoundEvent(new ResourceLocation("kagic:blocks.injector_open"));
	public static final SoundEvent BLOCK_INJECTOR_CLOSE = new SoundEvent(new ResourceLocation("kagic:blocks.injector_close"));
	public static final SoundEvent BLOCK_INJECTOR_FIRE = new SoundEvent(new ResourceLocation("kagic:blocks.injector_fire"));
	public static final SoundEvent GEM_POOF = new SoundEvent(new ResourceLocation("kagic:entities.gem.poof"));
	public static final SoundEvent GEM_SHATTER = new SoundEvent(new ResourceLocation("kagic:entities.gem.shatter"));

	public static final SoundEvent PEPO_LIVING = new SoundEvent(new ResourceLocation("kagic:entities.pepo.living"));
	public static final SoundEvent RUBY_EXPLODE = new SoundEvent(new ResourceLocation("kagic:entities.ruby.explode"));
	public static final SoundEvent TOPAZ_STEP = new SoundEvent(new ResourceLocation("kagic:entities.topaz.step"));
	
	public static final SoundEvent CORRUPTED_QUARTZ_AMBIENT = new SoundEvent(new ResourceLocation("kagic:entities.corrupted_quartz.living"));
	public static final SoundEvent CORRUPTED_QUARTZ_HURT = new SoundEvent(new ResourceLocation("kagic:entities.corrupted_quartz.hurt"));
	public static final SoundEvent CORRUPTED_QUARTZ_DEATH = new SoundEvent(new ResourceLocation("kagic:entities.corrupted_quartz.death"));
	public static final SoundEvent TONGUE_MONSTER_AMBIENT = new SoundEvent(new ResourceLocation("kagic:entities.tongue_monster.living"));
	public static final SoundEvent TONGUE_MONSTER_HURT = new SoundEvent(new ResourceLocation("kagic:entities.tongue_monster.hurt"));
	public static final SoundEvent TONGUE_MONSTER_DEATH = new SoundEvent(new ResourceLocation("kagic:entities.tongue_monster.death"));
	
	public static final SoundEvent WARP_PAD = new SoundEvent(new ResourceLocation("kagic:blocks.warp_pad"));
	
	public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
		registerSound(BLOCK_GEM_SEED_HATCH, new ResourceLocation("kagic:blocks.gem_seed_hatch"), event);
		registerSound(BLOCK_INJECTOR_OPEN, new ResourceLocation("kagic:blocks.injector_open"), event);
		registerSound(BLOCK_INJECTOR_CLOSE, new ResourceLocation("kagic:blocks.injector_close"), event);
		registerSound(BLOCK_INJECTOR_FIRE, new ResourceLocation("kagic:blocks.injector_fire"), event);
		registerSound(GEM_POOF, new ResourceLocation("kagic:entities.gem.poof"), event);
		registerSound(GEM_SHATTER, new ResourceLocation("kagic:entities.gem.shatter"), event);
		registerSound(PEPO_LIVING, new ResourceLocation("kagic:entities.pepo.living"), event);
		registerSound(RUBY_EXPLODE, new ResourceLocation("kagic:entities.ruby.explode"), event);
		registerSound(TOPAZ_STEP, new ResourceLocation("kagic:entities.topaz.step"), event);
		registerSound(WARP_PAD, new ResourceLocation(KAGIC.MODID, "warp_pad"), event);
		
	}
	
	private static void registerSound(SoundEvent sound, ResourceLocation location, RegistryEvent.Register<SoundEvent> event) {
		sound.setRegistryName(location);
		event.getRegistry().register(sound);
	}
}
