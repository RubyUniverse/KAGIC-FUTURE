package mod.akrivus.kagic.entity.gem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Predicate;

import mod.akrivus.kagic.blocks.BlockRoseTears;
import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.entity.ai.EntityAICommandGems;
import mod.akrivus.kagic.entity.ai.EntityAIFollowDiamond;
import mod.akrivus.kagic.entity.ai.EntityAIFutureVision;
import mod.akrivus.kagic.entity.ai.EntityAIRetroVision;
import mod.akrivus.kagic.entity.ai.EntityAIStandGuard;
import mod.akrivus.kagic.entity.ai.EntityAIStay;
import mod.akrivus.kagic.init.KAGIC;
import mod.akrivus.kagic.init.ModItems;
import mod.akrivus.kagic.init.ModSounds;
import mod.heimrarnadalr.kagic.util.Colors;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.INpc;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class EntitySapphire extends EntityGem implements INpc {
	public static final HashMap<IBlockState, Double> SAPPHIRE_YIELDS = new HashMap<IBlockState, Double>();
	public static final double SAPPHIRE_DEFECTIVITY_MULTIPLIER = 1;
	public static final double SAPPHIRE_DEPTH_THRESHOLD = 0;
	public static final HashMap<Integer, ResourceLocation> SAPPHIRE_HAIR_STYLES = new HashMap<Integer, ResourceLocation>();
	private int luckTicks = 0;
	
	private static final int NUM_HAIRSTYLES = 1;
	private static final int NUM_UNIFORMS = 1;
	private static final int NUM_INSIGNIAS = 1;
	private static final int NUM_GLOVES = 1;
	
	private static final Map<Integer, ArrayList<Integer>> SKIN_COLORS = new LinkedHashMap<Integer, ArrayList<Integer>>();
    static {
    	ArrayList<Integer> white = new ArrayList<Integer>();
        white.add(0xEDEDED);
        white.add(0xF9F9F9);
        white.add(0xEDEDED);
        white.add(0xEDF0F0);
        SKIN_COLORS.put(0, white);
        
        ArrayList<Integer> orange = new ArrayList<Integer>();
        orange.add(0xFFE06D);
        orange.add(0xFFCA2F);
        orange.add(0xFFA800);
        orange.add(0xFF7D00);
        orange.add(0xFF5100);
        SKIN_COLORS.put(1, orange);
        
        ArrayList<Integer> Yellow = new ArrayList<Integer>();
        Yellow.add(0xFEFFAB);
        Yellow.add(0xFFFF7F);
        Yellow.add(0xFFE84C);
        Yellow.add(0xFFDA00);
        Yellow.add(0xFFC700);
        SKIN_COLORS.put(2, Yellow);
        
        ArrayList<Integer> Pink = new ArrayList<Integer>();
        Pink.add(0xFFB5B2);
        Pink.add(0xFF70C8);
        Pink.add(0xFF8F8C);
        Pink.add(0xFF7672);
        Pink.add(0xFF5062);
        Pink.add(0xFF7F8C);
        SKIN_COLORS.put(3, Pink);
        
        ArrayList<Integer> Gray = new ArrayList<Integer>();
        Gray.add(0xD8D8D8);
        Gray.add(0xBABABA);
        Gray.add(0x9A9A9A);
        Gray.add(0x828282);
        Gray.add(0xAFBAB7);
        Gray.add(0xB7A0AE);
        SKIN_COLORS.put(4, Gray);
        
        ArrayList<Integer> Cyan = new ArrayList<Integer>();
        Cyan.add(0x8EFFDB);
        Cyan.add(0x00F0C9);
        Cyan.add(0x00D8B3);
        Cyan.add(0x00BA98);
        Cyan.add(0x0088A0);
        Cyan.add(0x44DEB6);
        SKIN_COLORS.put(5, Cyan);
        
        ArrayList<Integer> Purple = new ArrayList<Integer>();
        Purple.add(0xF6CFFF);
        Purple.add(0xA783F6);
        Purple.add(0xC891FF);
        Purple.add(0x9934FF);
        Purple.add(0x7231FF);
        Purple.add(0x7527C3);
        Purple.add(0x9136DE);
        SKIN_COLORS.put(6, Purple);
        
        ArrayList<Integer> Blue = new ArrayList<Integer>();
        Blue.add(0x7298EB);
        Blue.add(0x8898F8);
        Blue.add(0x3A41D8);
        Blue.add(0x7079EE);
        Blue.add(0x506EF6);
        Blue.add(0x5C90F5);
        Blue.add(0x8CACF5);
        Blue.add(0x4C83E7);
        Blue.add(0x8298FB);
        Blue.add(0x353AD4);
        Blue.add(0x6D72F0);
        Blue.add(0x5770FF);
        SKIN_COLORS.put(7, Blue);
        
        ArrayList<Integer> Brown = new ArrayList<Integer>();
        Brown.add(0x7F4C32);
        Brown.add(0x614131);
        Brown.add(0x9A725E);
        Brown.add(0x552F1B);
        Brown.add(0x67493A);
        Brown.add(0x342118);
        Brown.add(0x463932);
        Brown.add(0x5E4436);
        SKIN_COLORS.put(8, Brown);
        
        ArrayList<Integer> Green = new ArrayList<Integer>();
        Green.add(0xA1FF91);
        Green.add(0x5BEA74);
        Green.add(0x2CC346);
        Green.add(0x14A05F);
        Green.add(0x41FF34);
        Green.add(0x00A61A);
        SKIN_COLORS.put(9, Green);
 
        ArrayList<Integer> Black = new ArrayList<Integer>();
        Black.add(0x222222);
        Black.add(0x2B2B2B);
        Black.add(0x434343);
        Black.add(0x2F2831);
        Black.add(0x1D1C1F);
        SKIN_COLORS.put(10, Black);
        
        ArrayList<Integer> Padparadscha = new ArrayList<Integer>();
        Padparadscha.add(0xFF534C);
        Padparadscha.add(0xFF744C);
        Padparadscha.add(0xFF5A2B);
        Padparadscha.add(0xFF3E2B);
        Padparadscha.add(0xFF2600);
        Padparadscha.add(0xFF6246);
        Padparadscha.add(0xFF5D3B);
        SKIN_COLORS.put(11, Padparadscha);
        
        ArrayList<Integer> Bicolor = new ArrayList<Integer>();
        Bicolor.add(0xFEFFBA);
        Bicolor.add(0xFDFF7F);
        Bicolor.add(0xFDFF4C);
        Bicolor.add(0xFFE24C);
        Bicolor.add(0xFFDB1F);
        Bicolor.add(0xFFF61F);
        Bicolor.add(0xFFD01F);
        SKIN_COLORS.put(12, Bicolor);
        
		 ArrayList<Integer> Winza = new ArrayList<Integer>();
	     Winza.add(0xFF7CEE);
	     Winza.add(0xFF46E7);
	     Winza.add(0xD846FF);
	     Winza.add(0xFFA3F9);
	     Winza.add(0xFF79F6);
	     Winza.add(0xE155FF);
	     SKIN_COLORS.put(13, Winza);
       
    }
    private static final Map<Integer, ArrayList<Integer>> HAIR_COLORS = new LinkedHashMap<Integer, ArrayList<Integer>>();
    static {
    	ArrayList<Integer> white = new ArrayList<Integer>();
        white.add(0xEAEAEA);
        white.add(0xF9F9F9);
        white.add(0xFFFFFF);
        white.add(0xF0F0F0);
        white.add(0xC0C0C0);
        HAIR_COLORS.put(0, white);
        
        ArrayList<Integer> orange = new ArrayList<Integer>();
        orange.add(0xFFF9AB);
        orange.add(0xFFF160);
        orange.add(0xFFD925);
        orange.add(0xFFC400);
        orange.add(0xFF2300);
        HAIR_COLORS.put(1, orange);
      
        ArrayList<Integer> Yellow = new ArrayList<Integer>();
        Yellow.add(0xFFFFFC);
        Yellow.add(0xFFF6D8);
        Yellow.add(0xFFF98E);
        Yellow.add(0xFFF54C);
        Yellow.add(0xFFDE4C);
        Yellow.add(0xFFFFFF);
        HAIR_COLORS.put(2, Yellow);
        
        ArrayList<Integer> Pink = new ArrayList<Integer>();
        Pink.add(0xFFC0E7);
        Pink.add(0xFFE5E8);
        Pink.add(0xFFC9DB);
        Pink.add(0xFFA6C4);
        Pink.add(0xFF73A2);
        Pink.add(0xFF4C88);
        HAIR_COLORS.put(3, Pink);
        
        ArrayList<Integer> Gray = new ArrayList<Integer>();
        Gray.add(0xDBCFD7);
        Gray.add(0xF3F3F3);
        Gray.add(0xC0C0C0);
        Gray.add(0xE1E1E1);
        Gray.add(0x9D9D9D);
        Gray.add(0x585858);
        HAIR_COLORS.put(4, Gray);
        
        ArrayList<Integer> Cyan = new ArrayList<Integer>();
        Cyan.add(0xCCFFEF);
        Cyan.add(0xA9FFEA);
        Cyan.add(0x76FFDE);
        Cyan.add(0x2BFFCC);
        Cyan.add(0x23EABA);
        Cyan.add(0x03CCC4);
        Cyan.add(0x85FFDF);
        HAIR_COLORS.put(5, Cyan);
        
        ArrayList<Integer> Purple = new ArrayList<Integer>();
        Purple.add(0xFDF3FF);
        Purple.add(0xE0D2FF);
        Purple.add(0xCEB7FF);
        Purple.add(0xE191FF);
        Purple.add(0xA379FF);
        Purple.add(0x915EFF);
        Purple.add(0xBC6DFF);
        HAIR_COLORS.put(6, Purple);
        
        ArrayList<Integer> Blue = new ArrayList<Integer>();
        Blue.add(0xA9E8FA);
        Blue.add(0xC3F3FF);
        Blue.add(0x74B1E7);
        Blue.add(0xE2FFFD);
        Blue.add(0x77E6FA);
        Blue.add(0xB7D9FC);
        Blue.add(0x2733AB);
        HAIR_COLORS.put(7, Blue);
        
        ArrayList<Integer> Brown = new ArrayList<Integer>();
        Brown.add(0xC38B6E);
        Brown.add(0x9D7561);
        Brown.add(0xD5BCAF);
        Brown.add(0x825B47);
        Brown.add(0x947363);
        Brown.add(0x583929);
        Brown.add(0x311B0F);
        Brown.add(0x91786B);
        Brown.add(0x311B0F);
        HAIR_COLORS.put(8, Brown);
        
        ArrayList<Integer> Green = new ArrayList<Integer>();
        Green.add(0xD8FFE1);
        Green.add(0xAEFFC0);
        Green.add(0x61FF83);
        Green.add(0x23CC71);
        Green.add(0xB7FF6D);
        Green.add(0x84FF82);
        Green.add(0xAEFFB3);
        Green.add(0x007022);
        HAIR_COLORS.put(9, Green);
        
        ArrayList<Integer> Black = new ArrayList<Integer>();
        Black.add(0x707070);
        Black.add(0x585858);
        Black.add(0x797979);
        Black.add(0x68616D);
        Black.add(0x2E2E2E);
        Black.add(0x070707);
        HAIR_COLORS.put(10, Black);
        
        ArrayList<Integer> Padparadscha = new ArrayList<Integer>();
        Padparadscha.add(0xFFBA70);
        Padparadscha.add(0xFFBB9D);
        Padparadscha.add(0xFFB667);
        Padparadscha.add(0xFF7767);
        Padparadscha.add(0xFF8331);
        Padparadscha.add(0xFFB27F);
        HAIR_COLORS.put(11, Padparadscha);
        
        ArrayList<Integer> Bicolor = new ArrayList<Integer>();
        Bicolor.add(0x00B6FF);
        Bicolor.add(0x007AEA);
        Bicolor.add(0x0033FF);
        Bicolor.add(0x006FFF);
        Bicolor.add(0x243AA0);
        Bicolor.add(0x000055);
        HAIR_COLORS.put(12, Bicolor);
        
		 ArrayList<Integer> Winza = new ArrayList<Integer>();
	     Winza.add(0xB39DFF);
	     Winza.add(0xD176FF);
	     Winza.add(0xF0A9FF);
	     Winza.add(0x9576FF);
	     Winza.add(0xFF9DDB);
	     Winza.add(0xFF9DFF);
	     HAIR_COLORS.put(13, Winza);
       
    }
    private static final Map<Integer, ArrayList<Integer>> GLOVE_COLORS = new LinkedHashMap<Integer, ArrayList<Integer>>();
    static {
    	ArrayList<Integer> white = new ArrayList<Integer>();
        white.add(0xC3C3C3);
        white.add(0xA9A9A9);
        white.add(0x919297);
        white.add(0xA9A9A9);
        white.add(0x6A6A6A);
        GLOVE_COLORS.put(0, white);
        
        ArrayList<Integer> orange = new ArrayList<Integer>();
        orange.add(0xFFFFFF);
        orange.add(0xFFEFD5);
        orange.add(0xFFCDB1);
        orange.add(0xFFBA82);
        orange.add(0xFFD282);
        orange.add(0xD85017);
        GLOVE_COLORS.put(1, orange);
        
        ArrayList<Integer> Yellow = new ArrayList<Integer>();
        Yellow.add(0xFFFFFF);
        Yellow.add(0xFDFFD4);
        Yellow.add(0xFDFFB3);
        Yellow.add(0xFCFF94);
        Yellow.add(0xFFD75C);
        GLOVE_COLORS.put(2, Yellow);
      
        
        ArrayList<Integer> Pink = new ArrayList<Integer>();
        Pink.add(0xFFFFFF);
        Pink.add(0xFFEDEE);
        Pink.add(0xFFB1B7);
        Pink.add(0xFFC8B1);
        Pink.add(0xFFEAE1);
        Pink.add(0xE12538);
        GLOVE_COLORS.put(3, Pink);
        
        ArrayList<Integer> Gray = new ArrayList<Integer>();
        Gray.add(0xFFFFFF);
        Gray.add(0xF6F6F6);
        Gray.add(0xE4E4E4);
        Gray.add(0xCFCFCF);
        Gray.add(0x464646);
        GLOVE_COLORS.put(4, Gray);
        
        ArrayList<Integer> Cyan = new ArrayList<Integer>();
        Cyan.add(0xFFFFFF);
        Cyan.add(0xD8FFFD);
        Cyan.add(0xAEFFFC);
        Cyan.add(0x7CFFFA);
        Cyan.add(0x55FFF8);
        Cyan.add(0x14A5B4);
        GLOVE_COLORS.put(5, Cyan);
        
        ArrayList<Integer> Purple = new ArrayList<Integer>();
        Purple.add(0xFFFFFF);
        Purple.add(0xF9EAFF);
        Purple.add(0xF1CCFF);
        Purple.add(0xE9ABFF);
        Purple.add(0xDB79FF);
        Purple.add(0xC55EEA);
        Purple.add(0x4D1B8B);
        GLOVE_COLORS.put(6, Purple);
        
        ArrayList<Integer> Blue = new ArrayList<Integer>();
        Blue.add(0xFFFFFF);
        Blue.add(0xE3F4FE);
        Blue.add(0xD8E2FF);
        Blue.add(0xABC2FF);
        Blue.add(0x313CB7);
        Blue.add(0x000052);
        GLOVE_COLORS.put(7, Blue);
        
        ArrayList<Integer> Brown = new ArrayList<Integer>();
        Brown.add(0xFFFFFF);
        Brown.add(0xFFE1D2);
        Brown.add(0xE4C5B5);
        Brown.add(0xA0877A);
        Brown.add(0xCC8A68);
        Brown.add(0x613C2A);
        Brown.add(0x2B1307);
        GLOVE_COLORS.put(8, Brown);
        
        ArrayList<Integer> Green = new ArrayList<Integer>();
        Green.add(0xFFFFFF);
        Green.add(0xEEFFD5);
        Green.add(0xDAFFA3);
        Green.add(0x98E79B);
        Green.add(0xF7FFD8);
        Green.add(0x19402B);
        GLOVE_COLORS.put(9, Green);
        
        ArrayList<Integer> Black = new ArrayList<Integer>();
        Black.add(0xFFFFFF);
        Black.add(0xC3C3C3);
        Black.add(0x979797);
        Black.add(0x707070);
        Black.add(0x191919);
        Black.add(0x000000);
        GLOVE_COLORS.put(10, Black);
        
        ArrayList<Integer> Padparadscha = new ArrayList<Integer>();
        Padparadscha.add(0xFFFFFF);
        Padparadscha.add(0xFFFB97);
        Padparadscha.add(0xFFE897);
        Padparadscha.add(0xFFCD73);
        Padparadscha.add(0xFFE861);
        Padparadscha.add(0xC93F19);
        GLOVE_COLORS.put(11, Padparadscha);
        
        ArrayList<Integer> Bicolor = new ArrayList<Integer>();
        Bicolor.add(0xFFFFFF);
        Bicolor.add(0xFFFAE1);
        Bicolor.add(0xE1FFFF);
        Bicolor.add(0xFFF8A0);
        Bicolor.add(0xA0EEFF);
        Bicolor.add(0x58C2FF);
        Bicolor.add(0x0472B1);
        Bicolor.add(0x001961);
        Bicolor.add(0xFF9800);
        GLOVE_COLORS.put(12, Bicolor);
        
		 ArrayList<Integer> Winza = new ArrayList<Integer>();
	     Winza.add(0xBD94FF);
	     Winza.add(0xFC94FF);
	     Winza.add(0xFA6DFF);
	     Winza.add(0xB86DFF);
	     Winza.add(0x9A41ED);
	     Winza.add(0x8894FF);
	     GLOVE_COLORS.put(13, Winza);
        
    }
    
    private static final Map<Integer, ArrayList<Integer>> EYE_COLORS = new LinkedHashMap<Integer, ArrayList<Integer>>();
    static {
        ArrayList<Integer> white = new ArrayList<Integer>();
        white.add(0xD5D5D5);
        white.add(0xB1B1B1);
        white.add(0x949494);
        white.add(0xD2D2D2);
        white.add(0x525252);
        EYE_COLORS.put(0, white);

        ArrayList<Integer> orange = new ArrayList<Integer>();
        orange.add(0xFF9800);
        orange.add(0xFF6700);
        orange.add(0xE15200);
        orange.add(0xF8FF00);
        orange.add(0xFFB500);
        orange.add(0xFF8A00);
        EYE_COLORS.put(1, orange);
        
        ArrayList<Integer> Yellow = new ArrayList<Integer>();
        Yellow.add(0xFFD200);
        Yellow.add(0xFFA100);
        Yellow.add(0xFBFF00);
        Yellow.add(0xFDFF85);
        Yellow.add(0xFFE985);
        Yellow.add(0xFF9D00);
        EYE_COLORS.put(2, Yellow);
        
        ArrayList<Integer> Pink = new ArrayList<Integer>();
        Pink.add(0xFFC3DF);
        Pink.add(0xFFABD2);
        Pink.add(0xFF82BB);
        Pink.add(0xFF55A3);
        Pink.add(0xFFCFD5);
        Pink.add(0xFF2E46);
        EYE_COLORS.put(3, Pink);
        
        ArrayList<Integer> Gray = new ArrayList<Integer>();
        Gray.add(0xD2D2D2);
        Gray.add(0x979797);
        Gray.add(0x7C7C7C);
        Gray.add(0xCFCFCF);
        Gray.add(0x8B8B8B);
        Gray.add(0x404040);
        EYE_COLORS.put(4, Gray);
        
        ArrayList<Integer> Cyan = new ArrayList<Integer>();
        Cyan.add(0xA3FFF0);
        Cyan.add(0x52FFE2);
        Cyan.add(0x52FFC0);
        Cyan.add(0x25D595);
        Cyan.add(0x00A94A);
        Cyan.add(0x00FC76);
        EYE_COLORS.put(5, Cyan);
        
        ArrayList<Integer> Purple = new ArrayList<Integer>();
        Purple.add(0xD88EFF);
        Purple.add(0xBD40FF);
        Purple.add(0x9C24DB);
        Purple.add(0xD724DB);
        Purple.add(0xB90CBD);
        Purple.add(0x810CBD);
        EYE_COLORS.put(6, Purple);
        
        ArrayList<Integer> Blue = new ArrayList<Integer>();
        Blue.add(0x0014FF);
        Blue.add(0x0000CC);
        Blue.add(0x0039CC);
        Blue.add(0x001294);
        Blue.add(0x619AFF);
        Blue.add(0x0026FF);
        EYE_COLORS.put(7, Blue);
        
        ArrayList<Integer> Brown = new ArrayList<Integer>();
        Brown.add(0x8E5440);
        Brown.add(0xC68C79);
        Brown.add(0x88503D);
        Brown.add(0x701F04);
        Brown.add(0x52362D);
        Brown.add(0x4F1B0A);
        EYE_COLORS.put(8, Brown);
        
        ArrayList<Integer> Green = new ArrayList<Integer>();
        Green.add(0x00A92E);
        Green.add(0x00FF6C);
        Green.add(0x00A978);
        Green.add(0x00FF57);
        Green.add(0x00D53B);
        Green.add(0x008200);
        EYE_COLORS.put(9, Green);
        
        ArrayList<Integer> Black = new ArrayList<Integer>();
        Black.add(0xB7B7B7);
        Black.add(0x555555);
        Black.add(0x858585);
        Black.add(0xC0C0C0);
        Black.add(0xEAEAEA);
        EYE_COLORS.put(10, Black);
        
        ArrayList<Integer> Padparadscha = new ArrayList<Integer>();
        Padparadscha.add(0xFF4E00);
        Padparadscha.add(0xFF8279);
        Padparadscha.add(0xFF4437);
        Padparadscha.add(0xFF8237);
        Padparadscha.add(0xFF5F00);
        Padparadscha.add(0xFF3D00);
        Padparadscha.add(0xFF4E00);
        Padparadscha.add(0xE12B00);
        EYE_COLORS.put(11, Padparadscha);
        
        ArrayList<Integer> Bicolor = new ArrayList<Integer>();
        Bicolor.add(0x00D5FF);
        Bicolor.add(0xFFED00);
        Bicolor.add(0x00FFD5);
        Bicolor.add(0x00CBFF);
        Bicolor.add(0x00A7FF);
        Bicolor.add(0xFFBE00);
        EYE_COLORS.put(12, Bicolor);
        
		 ArrayList<Integer> Winza = new ArrayList<Integer>();
	     Winza.add(0xEBC3FF);
	     Winza.add(0xFFA0F4);
	     Winza.add(0xED97FF);
	     Winza.add(0xBF97FF);
	     Winza.add(0x6331B1);
	     Winza.add(0x63009A);
	     EYE_COLORS.put(13, Winza);
        
    }
	public EntitySapphire(World worldIn) {
		super(worldIn);
		//Width must be 0.6, or she will get stuck trying to pass through doors
		this.setSize(0.6F, 1.2F);
		this.seePastDoors();
		
		//Define valid gem cuts and placements
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.BACK_OF_HEAD);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.FOREHEAD);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.LEFT_EYE);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.RIGHT_EYE);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.LEFT_CHEEK);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.RIGHT_CHEEK);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.LEFT_SHOULDER);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.RIGHT_SHOULDER);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.LEFT_HAND);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.RIGHT_HAND);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.LEFT_THIGH);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.RIGHT_THIGH);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.LEFT_KNEE);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.RIGHT_KNEE);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.BACK);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.CHEST);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.BELLY);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.LEFT_EAR);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.RIGHT_EAR);
		this.setCutPlacement(GemCuts.FACETED, GemPlacements.NAPE);

		// Apply entity AI.
		this.stayAI = new EntityAIStay(this);
		this.tasks.addTask(1, new EntityAIAvoidEntity<EntityCreeper>(this, EntityCreeper.class, new Predicate<EntityCreeper>() {
			public boolean apply(EntityCreeper input) {
				return ((EntityCreeper) input).getCreeperState() == 1;
			}
        }, 6.0F, 1.0D, 1.2D));
		this.tasks.addTask(1, new EntityAIFollowDiamond(this, 1.0D));
        this.tasks.addTask(1, new EntityAICommandGems(this, 0.6D));
		this.tasks.addTask(2, new EntityAIFutureVision(this));
		this.tasks.addTask(3, new EntityAIRetroVision(this));
		this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(3, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityMob.class, 16.0F));
        this.tasks.addTask(6, new EntityAIStandGuard(this, 0.6D));
        this.tasks.addTask(7, new EntityAILookIdle(this));
       
        // Apply entity attributes.
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
        this.droppedGemItem = ModItems.SAPPHIRE_GEM;
		this.droppedCrackedGemItem = ModItems.CRACKED_SAPPHIRE_GEM;
	}

	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
		livingdata = super.onInitialSpawn(difficulty, livingdata);
		this.setGemCut(GemCuts.FACETED.id);
		int special = this.rand.nextInt(13);
    	this.itemDataToGemData(special);
		return livingdata;
	}
	@Override
	public void itemDataToGemData(int data) {
		if (data == 0) {
			this.nativeColor = 0;
		} else if (data == 1) {
		  this.nativeColor = 1;
		} else if (data == 2) {
			this.nativeColor = 4;
		} else if (data == 3) {
			this.nativeColor = 6;
		} else if (data == 4) {
			this.nativeColor = 7;
		} else if (data == 5) {
			this.nativeColor = 9;
		} else if (data == 6) {
			this.nativeColor = 10;
		} else if (data == 7) {
			this.nativeColor = 11;
		} else if (data == 8) {
			this.nativeColor = 12;
		} else if (data == 9) {
			this.nativeColor = 13;
		} else if (data == 10) {
			this.nativeColor = 15;
		} else if (data == 11) {
			this.nativeColor = 1;
		} else if (data == 12) {
			this.nativeColor = 11;
		} else if (data == 13) {
			this.nativeColor = 2;
		}
		this.setCustomNameTag(new TextComponentTranslation(String.format("entity.kagic.sapphire_%1$d.name", data)).getUnformattedComponentText());
		this.setSpecial(data);
		this.setInsigniaColor(this.nativeColor);
		this.setUniformColor(this.nativeColor);
		this.setHairStyle(this.generateHairStyle());
		this.setEyeColor(this.generateEyeColor());
		this.setHairColor(this.generateHairColor());
		this.setGloveColor(this.generateGloveColor());
		this.setGemColor(this.generateGemColor());
		this.setSkinColor(this.generateSkinColor());
		}
	@Override
	public int getSpecial() {
		return this.dataManager.get(SPECIAL);
	}
	protected int generateGemColor() {
		if (this.getSpecial() == 0) {
			return 0xA5A5A5;
		}
		else if (this.getSpecial() == 1) {
			return 0xF04600;
		}
		else if (this.getSpecial() == 2) {
			return 0xFFD000;
		}
		else if (this.getSpecial() == 3) {
			return 0xDB50B8;
		}
		else if (this.getSpecial() == 4) {
			return 0x737373;
		}
		else if (this.getSpecial() == 5) {
			return 0x007059;
		}
		else if (this.getSpecial() == 6) {
			return 0x3C2A72;
		}
		else if (this.getSpecial() == 7) {
			return 0x3B54BA;
		}
		else if (this.getSpecial() == 8) {
			return 0x774634;
		}
		else if (this.getSpecial() == 9) {
			return 0x3E7533;
		}
		else if (this.getSpecial() == 10) {
			return 0x1E1E1E;
		}
		else if (this.getSpecial() == 11) {
			return 0xF7370C;
		}
		else if (this.getSpecial() == 12) {
			return 0x47829A;
		}
		else if (this.getSpecial() == 13) {
			return 0xDF00FE;
		}
    	return 0xFFFFFF;
    }
	@Override
	public void convertGems(int placement) {
    	this.setGemCut(GemCuts.FACETED.id);
    	switch (placement) {
    	case 0:
    		this.setGemPlacement(GemPlacements.LEFT_EYE.id);
    		break;
    	case 1:
    		this.setGemPlacement(GemPlacements.CHEST.id);
    		break;
    	}
    }
	
	@Override
	protected void playStepSound(BlockPos pos, Block block) {
		//Sapphires have no legs and are thus completely silent
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
					}
				}
			}
		}
		return super.processInteract(player, hand);
    }
	
	@Override
	public void onLivingUpdate() {
		if (this.isPrimary()) {
	    	BlockPos.MutableBlockPos blockpos = new BlockPos.MutableBlockPos(0, 0, 0);
	        for (BlockPos.MutableBlockPos blockpos1 : BlockPos.getAllInBoxMutable(this.getPosition().add(-2, -1.0D, -2), this.getPosition().add(2, -1.0D, 2))) {
	            if (blockpos1.distanceSqToCenter(this.posX, this.posY, this.posZ) <= 4) {
	                blockpos.setPos(blockpos1.getX(), blockpos1.getY() + 1, blockpos1.getZ());
	                IBlockState iblockstate = this.world.getBlockState(blockpos);
	                if (iblockstate.getMaterial() == Material.AIR) {
	                    IBlockState iblockstate1 = this.world.getBlockState(blockpos1);
	                    if (iblockstate1.getMaterial() == Material.WATER && !(iblockstate1.getBlock() instanceof BlockRoseTears) && ((Integer)iblockstate1.getValue(BlockLiquid.LEVEL)).intValue() == 0 && this.world.mayPlace(Blocks.FROSTED_ICE, blockpos1, false, EnumFacing.DOWN, null)) {
	                        this.world.setBlockState(blockpos1, Blocks.FROSTED_ICE.getDefaultState());
	                        this.world.scheduleUpdate(blockpos1.toImmutable(), Blocks.FROSTED_ICE, this.rand.nextInt(60) + 60);
	                    }
	                    else if (iblockstate1.getMaterial() == Material.LAVA && ((Integer)iblockstate1.getValue(BlockLiquid.LEVEL)).intValue() == 0 && this.world.mayPlace(Blocks.COBBLESTONE, blockpos1, false, EnumFacing.DOWN, null)) {
	                        this.world.setBlockState(blockpos1, Blocks.COBBLESTONE.getDefaultState());
	                    }
	                }
	            }
	        }
		}
        if (this.luckTicks > 80 && !(this.isDead || this.getHealth() <= 0.0F)) {
			this.futureVision();
			this.luckTicks = 0;
		}
		this.luckTicks += 1;
		if (!this.onGround && this.motionY < 0.0D) {
			this.motionY *= 0.5D;
		}
		super.onLivingUpdate();
	}
	
	private void futureVision() {
        if (!this.world.isRemote) {
            AxisAlignedBB axisalignedbb = (new AxisAlignedBB(this.posX, this.posY, this.posZ, (this.posX + 1), (this.posY + 1), (this.posZ + 1))).grow(8.0, (double) this.world.getHeight(), 8.0);
            List<EntityLivingBase> list = this.world.<EntityLivingBase>getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);
            for (EntityLivingBase entity : list) {
            	if (!entity.isDead || entity.getHealth() > 0.0F) {
	            	if (entity instanceof EntityGem) {
	            		EntityGem gem = (EntityGem) entity;
	            		if (this.getServitude() == gem.getServitude()) {
	            			if (this.getServitude() == EntityGem.SERVE_HUMAN) {
	            				if (this.isOwnerId(gem.getOwnerId())) {
	            					if (this.isDefective()) {
	            						entity.addPotionEffect(new PotionEffect(MobEffects.UNLUCK, 100));
	            					}
	            					else {
	            						entity.addPotionEffect(new PotionEffect(MobEffects.LUCK, 100));
	            					}
	            				}
	            			}
	            			else {
	            				if (this.isDefective()) {
            						entity.addPotionEffect(new PotionEffect(MobEffects.UNLUCK, 100));
            					}
            					else {
            						entity.addPotionEffect(new PotionEffect(MobEffects.LUCK, 100));
            					}
	            			}
	            		}
	            	}
	            	if (this.isOwner(entity)) {
	            		if (this.isDefective()) {
    						entity.addPotionEffect(new PotionEffect(MobEffects.UNLUCK, 100));
    					}
    					else {
    						entity.addPotionEffect(new PotionEffect(MobEffects.LUCK, 100));
    					}
	            	}
            	}
            }
        }
    }
	/*********************************************************
     * Methods related to death.                             *
     *********************************************************/
    public void onDeath(DamageSource cause) {
    	switch (this.getSpecial()) {
    	case 0:
    		this.droppedGemItem = ModItems.WHITE_SAPPHIRE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_WHITE_SAPPHIRE_GEM;
    		break;
    	case 1:
    		this.droppedGemItem = ModItems.ORANGE_SAPPHIRE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_ORANGE_SAPPHIRE_GEM;
    		break;
    	case 2:
    		this.droppedGemItem = ModItems.YELLOW_SAPPHIRE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_YELLOW_SAPPHIRE_GEM;
    		break;
    	case 3:
    		this.droppedGemItem = ModItems.PINK_SAPPHIRE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_PINK_SAPPHIRE_GEM;
    		break;
    	case 4:
    		this.droppedGemItem = ModItems.GRAY_SAPPHIRE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_GRAY_SAPPHIRE_GEM;
    		break;
    	case 5:
    		this.droppedGemItem = ModItems.TURQUOISE_SAPPHIRE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_TURQUOISE_SAPPHIRE_GEM;
    		break;
    	case 6:
    		this.droppedGemItem = ModItems.PURPLE_SAPPHIRE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_PURPLE_SAPPHIRE_GEM;
    		break;
    	case 7:
    		this.droppedGemItem = ModItems.BLUE_SAPPHIRE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_BLUE_SAPPHIRE_GEM;
    		break;
    	case 8:
    		this.droppedGemItem = ModItems.BROWN_SAPPHIRE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_BROWN_SAPPHIRE_GEM;
    		break;
    	case 9:
    		this.droppedGemItem = ModItems.GREEN_SAPPHIRE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_GREEN_SAPPHIRE_GEM;
    		break;
    	case 10:
    		this.droppedGemItem = ModItems.BLACK_SAPPHIRE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_BLACK_SAPPHIRE_GEM;
    		break;
    	case 11:
    		this.droppedGemItem = ModItems.PADPARADSCHA_SAPPHIRE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_PADPARADSCHA_SAPPHIRE_GEM;
    		break;
    	case 12:
    		this.droppedGemItem = ModItems.BICOLOR_SAPPHIRE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_BICOLOR_SAPPHIRE_GEM;
    		break;
      	case 13:
    		this.droppedGemItem = ModItems.WINZA_SAPPHIRE_GEM;
    		this.droppedCrackedGemItem = ModItems.CRACKED_WINZA_SAPPHIRE_GEM;
    		break;
    	default:
			this.droppedGemItem = ModItems.SAPPHIRE_GEM;
			this.droppedCrackedGemItem = ModItems.CRACKED_SAPPHIRE_GEM;
			break;
    	}
    	super.onDeath(cause);
	}
	 /*********************************************************
	 * Methods related to entity interaction.				*
	 *********************************************************/	
	@Override
	public boolean alternateInteract(EntityPlayer player) {
		super.alternateInteract(player);
		KAGIC.instance.chatInfoMessage("Special is " + this.getSpecial());
		KAGIC.instance.chatInfoMessage("gloveColor is " + this.getGloveColor());
		KAGIC.instance.chatInfoMessage("skinColor is " + this.getSkinColor());
		KAGIC.instance.chatInfoMessage("hairColor is " + this.getHairColor());
		KAGIC.instance.chatInfoMessage("glove is " + this.getGloveStyle());
		return false;
	}

	/*********************************************************
	 * Methods related to rendering.                         *
	 *********************************************************/
	@Override
	public int generateSkinColor() {
		switch (this.getSpecial()) {
		}
        return Colors.arbiLerp(EntitySapphire.SKIN_COLORS.get(this.getSpecial()));
        
	}
	@Override
    protected int generateHairColor() {
		switch (this.getSpecial()) {
        }
        return Colors.arbiLerp(EntitySapphire.HAIR_COLORS.get(this.getSpecial()));
	}
	protected int generateGloveColor() {
		switch (this.getSpecial()) {
        }
        return Colors.arbiLerp(EntitySapphire.GLOVE_COLORS.get(this.getSpecial()));
	}
	protected int generateEyeColor() {
		switch (this.getSpecial()) {
        }
        return Colors.arbiLerp(EntitySapphire.EYE_COLORS.get(this.getSpecial()));
	}
	@Override
	public boolean hasHairVariant(GemPlacements placement) {
		switch(placement) {
		case RIGHT_EYE:
			return true;
		case LEFT_EYE:
			return true;
		default:
			return false;
		}
	}
}
