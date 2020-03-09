package mod.akrivus.kagic.client.render;

import mod.akrivus.kagic.client.model.ModelRuby;
import mod.akrivus.kagic.client.render.layers.LayerBirthdayHat;
import mod.akrivus.kagic.client.render.layers.LayerEye;
import mod.akrivus.kagic.client.render.layers.LayerFusionPlacement;
import mod.akrivus.kagic.client.render.layers.LayerGemPlacement;
import mod.akrivus.kagic.client.render.layers.LayerHair;
import mod.akrivus.kagic.client.render.layers.LayerInsignia;
import mod.akrivus.kagic.client.render.layers.LayerRubyItem;
import mod.akrivus.kagic.client.render.layers.LayerSantaHat;
import mod.akrivus.kagic.client.render.layers.LayerSkin;
import mod.akrivus.kagic.client.render.layers.LayerUniform;
import mod.akrivus.kagic.client.render.layers.LayerVisor;
import mod.akrivus.kagic.client.render.layers.LayerWitchHat;
import mod.akrivus.kagic.entity.gem.EntityRuby;
import mod.akrivus.kagic.init.KAGIC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderRuby extends RenderGemBase<EntityRuby> {
	public RenderRuby() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelRuby(), 0.3F);
        this.addLayer(new LayerRubyItem(this));
        this.addLayer(new LayerSkin(this));
        this.addLayer(new LayerUniform(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerEye(this));
        this.addLayer(new LayerVisor(this));
        this.addLayer(new LayerHair(this));
        this.addLayer(new LayerGemPlacement(this));
        this.addLayer(new LayerFusionPlacement(this));
		if (KAGIC.isBirthday()) {
			this.addLayer(new LayerBirthdayHat(this));
		} else if (KAGIC.isHalloween()) {
			this.addLayer(new LayerWitchHat(this));
		} else if (KAGIC.isChristmas()) {
			this.addLayer(new LayerSantaHat(this));
		}
    }
	
	@Override
	protected void preRenderCallback(EntityRuby gem, float partialTickTime) {
		if (gem.isFusion()) {
			GlStateManager.scale(0.8F * gem.getFusionCount(), 0.8F * gem.getFusionCount(), 0.8F * gem.getFusionCount());
		}
		else if (gem.isDefective()) {
			GlStateManager.scale(0.5F, 0.5F, 0.5F);
		}
		else {
			GlStateManager.scale(0.8F, 0.8F, 0.8F);
		}
		this.shadowSize = 0.3F * gem.getFusionCount();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityRuby entity) {
		return new ResourceLocation("kagic:textures/entities/ruby/ruby.png");
	}
}
