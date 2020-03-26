package mod.akrivus.kagic.client.render;

import mod.akrivus.kagic.client.model.ModelGarnat;
import mod.akrivus.kagic.client.model.ModelGarney;
import mod.akrivus.kagic.client.model.ModelHessonite;
import mod.akrivus.kagic.client.render.layers.LayerBirthdayHat;
import mod.akrivus.kagic.client.render.layers.LayerGemPlacement;
import mod.akrivus.kagic.client.render.layers.LayerHair;
import mod.akrivus.kagic.client.render.layers.LayerInsignia;
import mod.akrivus.kagic.client.render.layers.LayerNoDyeOverlay;
import mod.akrivus.kagic.client.render.layers.LayerQuartzCape;
import mod.akrivus.kagic.client.render.layers.LayerQuartzItem;
import mod.akrivus.kagic.client.render.layers.LayerSantaHat;
import mod.akrivus.kagic.client.render.layers.LayerSkin;
import mod.akrivus.kagic.client.render.layers.LayerUniform;
import mod.akrivus.kagic.client.render.layers.LayerVisor;
import mod.akrivus.kagic.client.render.layers.LayerWitchHat;
import mod.akrivus.kagic.entity.gem.EntityHessonite;
import mod.akrivus.kagic.entity.gem.EntityRuby;
import mod.akrivus.kagic.init.KAGIC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class RenderHessonite extends RenderGemBase<EntityHessonite> {

	public RenderHessonite() {
		super(Minecraft.getMinecraft().getRenderManager(), new ModelGarney(), 0.5F);

		this.addLayer(new LayerQuartzItem(this));
		if (KAGIC.isBirthday()) {
			this.addLayer(new LayerBirthdayHat(this));
		} else if (KAGIC.isHalloween()) {
			this.addLayer(new LayerWitchHat(this));
		} else if (KAGIC.isChristmas()) {
			this.addLayer(new LayerSantaHat(this));
		}
	}

	@Override
	protected void preRenderCallback(EntityHessonite gem, float partialTickTime) {
		if (gem.isDefective()) {
			GlStateManager.scale(0.8F, 0.7F, 0.8F);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityHessonite entity) {
		return new ResourceLocation("kagic:textures/entities/hessonite/garnet_test.png");
	}
}
