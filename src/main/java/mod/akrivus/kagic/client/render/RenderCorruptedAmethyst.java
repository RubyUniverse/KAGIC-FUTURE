package mod.akrivus.kagic.client.render;

import java.util.Iterator;

import mod.akrivus.kagic.client.model.corrupted.ModelCorruptedQuartz;
import mod.akrivus.kagic.client.render.layers.LayerBirthdayHat;
import mod.akrivus.kagic.client.render.layers.LayerGemPlacement;
import mod.akrivus.kagic.client.render.layers.LayerHair;
import mod.akrivus.kagic.client.render.layers.LayerInsignia;
import mod.akrivus.kagic.client.render.layers.LayerJasperMark1;
import mod.akrivus.kagic.client.render.layers.LayerJasperMark2;
import mod.akrivus.kagic.client.render.layers.LayerQuartzCape;
import mod.akrivus.kagic.client.render.layers.LayerQuartzItem;
import mod.akrivus.kagic.client.render.layers.LayerSkin;
import mod.akrivus.kagic.client.render.layers.LayerUniform;
import mod.akrivus.kagic.client.render.layers.LayerVisor;
import mod.akrivus.kagic.client.render.layers.LayerWitchHat;
import mod.akrivus.kagic.entity.gem.EntityJasper;
import mod.akrivus.kagic.entity.gem.corrupted.EntityCorruptedAmethyst;
import mod.akrivus.kagic.entity.gem.corrupted.EntityCorruptedJasper;
import mod.akrivus.kagic.entity.gem.fusion.EntityGarnet;
import mod.akrivus.kagic.init.KAGIC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderCorruptedAmethyst extends RenderLiving<EntityCorruptedAmethyst> {
	
	public RenderCorruptedAmethyst() {
		super(Minecraft.getMinecraft().getRenderManager(), new ModelCorruptedQuartz(), 2F);

		this.addLayer(new LayerSkin(this, 0F, "corrupted/amethyst"));
		this.addLayer(new LayerHair(this, 0F, "corrupted/amethyst"));
		this.addLayer(new LayerGemPlacement(this, "corrupted/amethyst"));
		/*		
		if (KAGIC.isBirthday()) {
			this.addLayer(new LayerBirthdayHat(this));
		} else if (KAGIC.isHalloween()) {
			this.addLayer(new LayerWitchHat(this));
		}*/
	}
			
	@Override
	protected void preRenderCallback(EntityCorruptedAmethyst amethyst, float partialTickTime) {
		GlStateManager.scale(2F, 2F, 2F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityCorruptedAmethyst amethyst) {
		return new ResourceLocation("kagic:textures/entities/corrupted/amethyst/amethyst.png");
	}
}
