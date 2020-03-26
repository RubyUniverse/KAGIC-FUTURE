package mod.akrivus.kagic.items;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

import mod.akrivus.kagic.entity.EntityCorruptedGem;
import mod.akrivus.kagic.entity.gem.EntityPearl;
import mod.akrivus.kagic.entity.gem.EntityRuby;
import mod.akrivus.kagic.entity.shardfusion.EntityShardFusion;
import mod.akrivus.kagic.init.ModBlocks;
import mod.akrivus.kagic.init.ModConfigs;
import mod.akrivus.kagic.init.ModCreativeTabs;
import mod.akrivus.kagic.init.ModEntities;
import mod.akrivus.kagic.init.ModItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ColorChangeStaff extends Item {
	public ColorChangeStaff() {
		super();
		this.setUnlocalizedName("color_change_staff");
		this.setCreativeTab(ModCreativeTabs.CREATIVE_TAB_OTHER);
		this.setMaxStackSize(1);
	}
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		super.onCreated(stack, worldIn, playerIn);
		//playerIn.addStat(ModAchievements.GEM_COMMANDER);
	}
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
		ItemStack stack = playerIn.getHeldItem(hand);
		if (!worldIn.isRemote) {
			List<EntityPearl> list = playerIn.world.<EntityPearl>getEntitiesWithinAABB(EntityPearl.class, playerIn.getEntityBoundingBox().grow(24.0D, 8.0D, 24.0D));
	        for (EntityPearl gem : list) {
	        	gem.setRevengeTarget(null);
	        	gem.setAttackTarget(null);
	            gem.isPeaceful = true;
	        }

	        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
		}
		playerIn.swingArm(hand);
		return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
	}
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
	@SideOnly(Side.CLIENT)
    public boolean isFull3D() {
        return true;
    }
}
