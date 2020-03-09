package mod.akrivus.kagic.client.render;

import mod.akrivus.kagic.client.model.ModelSapphire;
import mod.akrivus.kagic.client.model.ModelSapphiru;
import mod.akrivus.kagic.client.render.layers.LayerBirthdayHat;
import mod.akrivus.kagic.client.render.layers.LayerEye;
import mod.akrivus.kagic.client.render.layers.LayerGemPlacement;
import mod.akrivus.kagic.client.render.layers.LayerGlove;
import mod.akrivus.kagic.client.render.layers.LayerHair;
import mod.akrivus.kagic.client.render.layers.LayerInsignia;
import mod.akrivus.kagic.client.render.layers.LayerNoDyeOverlay;
import mod.akrivus.kagic.client.render.layers.LayerSantaHat;
import mod.akrivus.kagic.client.render.layers.LayerSapphireItem;
import mod.akrivus.kagic.client.render.layers.LayerSkin;
import mod.akrivus.kagic.client.render.layers.LayerUniform;
import mod.akrivus.kagic.client.render.layers.LayerVisor;
import mod.akrivus.kagic.client.render.layers.LayerWitchHat;
import mod.akrivus.kagic.entity.gem.EntitySapphire;
import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.entity.gem.EntitySapphire;
import mod.akrivus.kagic.init.KAGIC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class RenderSapphire extends RenderGemBase<EntitySapphire> {
	public RenderSapphire() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelSapphiru(), 0.25F);
        this.addLayer(new LayerSapphireItem(this));
        this.addLayer(new LayerSkin(this));
        this.addLayer(new LayerUniform(this));
        this.addLayer(new LayerGlove(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerHair(this));
        this.addLayer(new LayerVisor(this));
        this.addLayer(new LayerEye(this));
        this.addLayer(new LayerGemPlacement(this));

		if (KAGIC.isBirthday()) {
			this.addLayer(new LayerBirthdayHat(this));
		} else if (KAGIC.isHalloween()) {
			this.addLayer(new LayerWitchHat(this));
		} else if (KAGIC.isChristmas()) {
			this.addLayer(new LayerSantaHat(this));
		}
	}
	
	@Override
	protected void preRenderCallback(EntitySapphire gem, float partialTickTime) {
		GlStateManager.scale(0.97F, 0.97F, 0.97F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntitySapphire entity) {
		return new ResourceLocation("kagic:textures/entities/sapphire/sapphire.png");
	}
}