package sssss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

/**
 * ModelQuartz - taaffeite
 * Created using Tabula 7.1.0
 */
public class ModelQuartz extends ModelBase {
    public ModelRenderer bipedTopBun;
    public ModelRenderer bipedLeftBun;
    public ModelRenderer bipedRightBun;
    public ModelRenderer bipedBackBun;
    public ModelRenderer bipedMohawk;
    public ModelRenderer bipedHeadwear;
    public ModelRenderer bipedHead;
    public ModelRenderer bipedBody;
    public ModelRenderer bipedDress;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftLeg;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedLeftShoulderpad;
    public ModelRenderer bipedRightShoulderpad;

    public ModelQuartz() {
        this.textureWidth = 96;
        this.textureHeight = 80;
        this.bipedLeftBun = new ModelRenderer(this, 64, 8);
        this.bipedLeftBun.setRotationPoint(5.0F, -8.0F, 0.0F);
        this.bipedLeftBun.addBox(-1.0F, -2.5F, -2.5F, 2, 5, 5, 0.0F);
        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.bipedLeftLeg = new ModelRenderer(this, 48, 16);
        this.bipedLeftLeg.setRotationPoint(-2.5F, 10.0F, 0.0F);
        this.bipedLeftLeg.addBox(-2.5F, 0.0F, -2.0F, 5, 14, 4, 0.0F);
        this.bipedRightLeg = new ModelRenderer(this, 48, 34);
        this.bipedRightLeg.setRotationPoint(2.5F, 10.0F, 0.0F);
        this.bipedRightLeg.addBox(-2.5F, 0.0F, -2.0F, 5, 14, 4, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 0, 16);
        this.bipedLeftArm.setRotationPoint(-7.0F, -4.0F, 0.0F);
        this.bipedLeftArm.addBox(-2.0F, 0.0F, -2.0F, 4, 14, 4, 0.0F);
        this.setRotateAngle(bipedLeftArm, -0.02487404805461284F, 0.0F, 0.0F);
        this.bipedBackBun = new ModelRenderer(this, 66, 18);
        this.bipedBackBun.setRotationPoint(0.0F, -8.0F, 5.0F);
        this.bipedBackBun.addBox(-2.5F, -2.5F, -1.0F, 5, 5, 2, 0.0F);
        this.bipedLeftShoulderpad = new ModelRenderer(this, 0, 0);
        this.bipedLeftShoulderpad.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedLeftShoulderpad.addBox(-3.4F, -0.4F, -3.2F, 6, 5, 6, 0.0F);
        this.setRotateAngle(bipedLeftShoulderpad, 0.0F, -0.04145674675768791F, 0.0F);
        this.bipedMohawk = new ModelRenderer(this, 66, 25);
        this.bipedMohawk.setRotationPoint(0.0F, -12.0F, -2.0F);
        this.bipedMohawk.addBox(-2.5F, -2.0F, -4.0F, 5, 4, 8, 0.0F);
        this.setRotateAngle(bipedMohawk, -0.4363323129985824F, 0.0F, 0.0F);
        this.bipedHeadwear = new ModelRenderer(this, 32, 0);
        this.bipedHeadwear.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.bipedRightBun = new ModelRenderer(this, 78, 8);
        this.bipedRightBun.setRotationPoint(-5.0F, -8.0F, 0.0F);
        this.bipedRightBun.addBox(-1.0F, -2.5F, -2.5F, 2, 5, 5, 0.0F);
        this.bipedDress = new ModelRenderer(this, 16, 44);
        this.bipedDress.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.bipedDress.addBox(-5.5F, 0.0F, -4.0F, 11, 18, 8, 0.0F);
        this.bipedTopBun = new ModelRenderer(this, 64, 0);
        this.bipedTopBun.setRotationPoint(0.0F, -14.0F, 0.0F);
        this.bipedTopBun.addBox(-2.5F, 0.0F, -2.5F, 5, 2, 5, 0.0F);
        this.bipedBody = new ModelRenderer(this, 16, 16);
        this.bipedBody.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.bipedBody.addBox(-5.0F, 0.0F, -3.0F, 10, 14, 6, 0.0F);
        this.bipedRightArm = new ModelRenderer(this, 0, 34);
        this.bipedRightArm.setRotationPoint(7.0F, -4.0F, 0.0F);
        this.bipedRightArm.addBox(-2.0F, 0.0F, -2.0F, 4, 14, 4, 0.0F);
        this.bipedRightShoulderpad = new ModelRenderer(this, 0, 0);
        this.bipedRightShoulderpad.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedRightShoulderpad.addBox(-2.8F, -0.4F, -3.2F, 6, 5, 6, 0.0F);
        this.bipedLeftArm.addChild(this.bipedLeftShoulderpad);
        this.bipedRightArm.addChild(this.bipedRightShoulderpad);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.bipedLeftBun.render(f5);
        this.bipedHead.render(f5);
        this.bipedLeftLeg.render(f5);
        this.bipedRightLeg.render(f5);
        this.bipedLeftArm.render(f5);
        this.bipedBackBun.render(f5);
        this.bipedMohawk.render(f5);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.bipedHeadwear.offsetX, this.bipedHeadwear.offsetY, this.bipedHeadwear.offsetZ);
        GlStateManager.translate(this.bipedHeadwear.rotationPointX * f5, this.bipedHeadwear.rotationPointY * f5, this.bipedHeadwear.rotationPointZ * f5);
        GlStateManager.scale(1.1D, 1.1D, 1.1D);
        GlStateManager.translate(-this.bipedHeadwear.offsetX, -this.bipedHeadwear.offsetY, -this.bipedHeadwear.offsetZ);
        GlStateManager.translate(-this.bipedHeadwear.rotationPointX * f5, -this.bipedHeadwear.rotationPointY * f5, -this.bipedHeadwear.rotationPointZ * f5);
        this.bipedHeadwear.render(f5);
        GlStateManager.popMatrix();
        this.bipedRightBun.render(f5);
        this.bipedDress.render(f5);
        this.bipedTopBun.render(f5);
        this.bipedBody.render(f5);
        this.bipedRightArm.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
