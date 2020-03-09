package mod.akrivus.kagic.client.render.layers;

import mod.akrivus.kagic.entity.EntityGem;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;

public class LayerUniform extends GemLayer implements LayerRenderer<EntityGem> {
	private final RenderLivingBase<?> gemRenderer;
	private final ModelBase gemModel;
	private float offset;
	private String name;

	public LayerUniform(RenderLivingBase<?> gemRenderer) {
		this(gemRenderer, 0F);
	}

	public LayerUniform(RenderLivingBase<?> gemRenderer, float offset) {
		this(gemRenderer, offset, null);
	}
	
	public LayerUniform(RenderLivingBase<?> gemRenderer, float offset, String name) {
		this.gemRenderer = gemRenderer;
		this.gemModel = gemRenderer.getMainModel();
		this.offset = offset;
		this.name = name;
	}
	
	@Override
	public void doRenderLayer(EntityGem gem, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.gemRenderer.bindTexture(this.getUniformStyle(gem, gem.getUniformStyle()));
		float[] afloat = EntitySheep.getDyeRgb(EnumDyeColor.values()[gem.getUniformColor()]);
		GlStateManager.color(afloat[0], afloat[1], afloat[2], 1f);
		this.gemModel.setModelAttributes(this.gemRenderer.getMainModel());
        this.gemModel.render(gem, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	}
	
	public ResourceLocation getUniformStyle(EntityGem gem, int uniform) {
		ResourceLocation loc = EntityList.getKey(gem);
		if (gem.hasUniformVariant(gem.getGemPlacement())) {
			return new ResourceLocation(loc.getResourceDomain() + ":textures/entities/" + this.getName(gem) + "/uniforms/uniform_" + gem.getGemPlacement().toString().toLowerCase() + ".png");
		} else {
				
			return new ResourceLocation(loc.getResourceDomain() + ":textures/entities/" + this.getName(gem) + "/uniforms/uniform_" + gem.getUniformStyle() + ".png");
	
		}
	}
	
	@Override
	public boolean shouldCombineTextures() {
		return true;
	}
}