package mod.akrivus.kagic.client.render.layers;

import java.util.ArrayList;

import mod.akrivus.kagic.client.render.RenderAmethyst;
import mod.akrivus.kagic.entity.gem.EntityAmethyst;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;

public class LayerLaceMark1 implements LayerRenderer<EntityAmethyst> {
	private final RenderAmethyst gemRenderer;
	private final ModelBase gemModel;
	
	public LayerLaceMark1(RenderAmethyst renderAmethyst) {
		this.gemRenderer = renderAmethyst;
		this.gemModel = renderAmethyst.getMainModel();
	}

	@Override
	public void doRenderLayer(EntityAmethyst amethyst, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.gemRenderer.bindTexture(this.getMark1(amethyst));
		int mark1Color = amethyst.getMark1Color();
	        float r = (float) ((mark1Color & 16711680) >> 16) / 255f;
	        float g = (float) ((mark1Color & 65280) >> 8) / 255f;
	        float b = (float) ((mark1Color & 255) >> 0) / 255f;
			GlStateManager.color(r, g, b/*, 0.99f*/);
			//GlStateManager.enableBlend();
			//GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
			this.gemModel.setModelAttributes(this.gemRenderer.getMainModel());
	        this.gemModel.render(amethyst, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		}

	public ResourceLocation getMark1(EntityAmethyst amethyst) {
		ResourceLocation loc = EntityList.getKey(amethyst);
		return new ResourceLocation(loc.getResourceDomain() + ":textures/entities/amethyst/marks/mark1/mark1_" + amethyst.getSpecial() + "_" + amethyst.getMark1() + ".png");
	}

	@Override
	public boolean shouldCombineTextures() {
		return true;
	}
}
