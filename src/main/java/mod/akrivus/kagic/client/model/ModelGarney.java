package mod.akrivus.kagic.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

/**
 * Garnets - pezz
 * Created using Tabula 7.1.0
 */
public class ModelGarney extends ModelGem {
    public ModelRenderer bipedBody;
    public ModelRenderer bipedNeck;
    public ModelRenderer bipedLeftLegBottom;
    public ModelRenderer bipedLeftShoulderpad;
    public ModelRenderer bipedRightShoulderpad;
    public ModelRenderer bipedRightLegBottom;
    public ModelRenderer bipedBody_1;
    public ModelRenderer bipedCapeTop;
    public ModelRenderer bipedHessoniteHeadHair;
    public ModelRenderer bipedPyropeHair;
    public ModelRenderer bipedDemantoidHair;
    public ModelRenderer bipedLeftLeg;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedLeftArmPuff;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedRightArmPuff;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedHips;
    public ModelRenderer bipedDress;
    public ModelRenderer bipedCape;

    public ModelGarney() {
    	super(0.0F, 0.0F, 104, 130, false, -1F);
        this.bipedLeftShoulderpad = new ModelRenderer(this, 44, 0);
        this.bipedLeftShoulderpad.setRotationPoint(-5.0F, -0.8F, 0.0F);
        this.bipedLeftShoulderpad.addBox(0.0F, 0.0F, 0.0F, 5, 5, 5, 0.0F);
        this.bipedRightArm = new ModelRenderer(this, 12, 48);
        this.bipedRightArm.mirror = true;
        this.bipedRightArm.setRotationPoint(0.0F, 1.0F, 1.5F);
        this.bipedRightArm.addBox(0.0F, 0.0F, 0.0F, 2, 13, 2, 0.0F);
        this.setRotateAngle(bipedRightArm, 0.0F, 0.0F, -0.22689280275926282F);
        this.bipedLeftArm = new ModelRenderer(this, 32, 48);
        this.bipedLeftArm.setRotationPoint(3.5F, 0.5F, 1.5F);
        this.bipedLeftArm.addBox(0.0F, 0.0F, 0.0F, 2, 13, 2, 0.0F);
        this.setRotateAngle(bipedLeftArm, 0.0F, 0.0F, 0.22759093446006054F);
        this.bipedRightLeg = new ModelRenderer(this, 20, 48);
        this.bipedRightLeg.mirror = true;
        this.bipedRightLeg.setRotationPoint(1.0F, 3.0F, 1.0F);
        this.bipedRightLeg.addBox(0.0F, 0.0F, 0.0F, 3, 15, 3, 0.0F);
        this.bipedCape = new ModelRenderer(this, 54, 54);
        this.bipedCape.setRotationPoint(0.0F, 5.0F, 5.0F);
        this.bipedCape.addBox(-1.7F, 0.0F, 0.0F, 20, 18, 0, 0.0F);
        this.setRotateAngle(bipedCape, 0.10471975511965977F, 0.0F, 0.0F);
        this.bipedCapeTop = new ModelRenderer(this, 0, 66);
        this.bipedCapeTop.setRotationPoint(-4.0F, -4.0F, -0.1F);
        this.bipedCapeTop.addBox(0.0F, 0.0F, 0.0F, 16, 7, 8, 0.0F);
        this.bipedHessoniteHeadHair = new ModelRenderer(this, 1, 1);
        this.bipedHessoniteHeadHair.setRotationPoint(-5.0F, -8.7F, -4.0F);
        this.bipedHessoniteHeadHair.addBox(0.0F, 0.0F, 0.0F, 12, 11, 10, 0.0F);
        this.bipedDemantoidHair = new ModelRenderer(this, 46, 88);
        this.bipedDemantoidHair.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.bipedDemantoidHair.addBox(0.0F, 0.0F, 0.0F, 12, 13, 9, 0.0F);
        this.bipedPyropeHair = new ModelRenderer(this, 0, 82);
        this.bipedPyropeHair.setRotationPoint(0.0F, -5.0F, 0.0F);
        this.bipedPyropeHair.addBox(0.0F, 0.0F, 0.0F, 12, 16, 10, 0.0F);
        
        this.bipedRightShoulderpad = new ModelRenderer(this, 44, 0);
        this.bipedRightShoulderpad.setRotationPoint(8.0F, -0.8F, 0.0F);
        this.bipedRightShoulderpad.addBox(0.0F, 0.0F, 0.0F, 5, 5, 5, 0.0F);
        this.bipedHips = new ModelRenderer(this, 57, 14);
        this.bipedHips.setRotationPoint(-1.0F, 8.0F, 0.0F);
        this.bipedHips.addBox(0.0F, 0.0F, 0.0F, 8, 3, 4, 0.0F);
        this.bipedRightLegBottom = new ModelRenderer(this, 20, 36);
        this.bipedRightLegBottom.mirror = true;
        this.bipedRightLegBottom.setRotationPoint(4.0F, 8.0F, 0.0F);
        this.bipedRightLegBottom.addBox(0.0F, 0.0F, 0.0F, 5, 7, 5, 0.0F);
        this.bipedBody_1 = new ModelRenderer(this, 57, 73);
        this.bipedBody_1.setRotationPoint(1.0F, 0.0F, 0.5F);
        this.bipedBody_1.addBox(0.0F, 0.0F, 0.0F, 6, 9, 4, 0.0F);
        this.bipedBody = new ModelRenderer(this, 0, 22);
        this.bipedBody.setRotationPoint(-4.0F, -2.0F, -2.5F);
        this.bipedBody.addBox(0.0F, 0.0F, 0.0F, 8, 5, 5, 0.0F);
        this.bipedNeck = new ModelRenderer(this, 0, 0);
        this.bipedNeck.setRotationPoint(3.0F, -4.0F, 1.5F);
        this.bipedNeck.addBox(0.0F, 0.0F, 0.0F, 2, 4, 2, 0.0F);
        this.bipedLeftArmPuff = new ModelRenderer(this, 40, 55);
        this.bipedLeftArmPuff.setRotationPoint(-0.5F, 8.0F, -0.5F);
        this.bipedLeftArmPuff.addBox(0.0F, 0.0F, 0.0F, 3, 4, 3, 0.0F);
        this.bipedDress = new ModelRenderer(this, 42, 26);
        this.bipedDress.setRotationPoint(-2.5F, 7.9F, -3.5F);
        this.bipedDress.addBox(0.0F, 0.0F, 0.0F, 11, 17, 11, 0.0F);
        this.bipedRightArmPuff = new ModelRenderer(this, 40, 55);
        this.bipedRightArmPuff.setRotationPoint(-0.5F, 8.0F, -0.5F);
        this.bipedRightArmPuff.addBox(0.0F, 0.0F, 0.0F, 3, 4, 3, 0.0F);
        this.bipedHessoniteHeadHair = new ModelRenderer(this, 1, 1);
        this.bipedHessoniteHeadHair.setRotationPoint(-3.0F, -6.9F, -2.9F);
        this.bipedHessoniteHeadHair.addBox(0.0F, 0.0F, 0.0F, 8, 8, 8, 0.0F);
        this.bipedLeftLegBottom = new ModelRenderer(this, 0, 36);
        this.bipedLeftLegBottom.setRotationPoint(-1.0F, 8.0F, 0.0F);
        this.bipedLeftLegBottom.addBox(0.0F, 0.0F, 0.0F, 5, 7, 5, 0.0F);
        this.bipedLeftLeg = new ModelRenderer(this, 0, 48);
        this.bipedLeftLeg.setRotationPoint(1.0F, 3.0F, 1.0F);
        this.bipedLeftLeg.addBox(0.0F, 0.0F, 0.0F, 3, 15, 3, 0.0F);
        this.bipedHessoniteHeadHair.addChild(this.bipedDemantoidHair);
        this.bipedHessoniteHeadHair.addChild(this.bipedPyropeHair);
        this.bipedBody.addChild(this.bipedLeftShoulderpad);
        this.bipedRightShoulderpad.addChild(this.bipedRightArm);
        this.bipedLeftShoulderpad.addChild(this.bipedLeftArm);
        this.bipedRightLegBottom.addChild(this.bipedRightLeg);
        this.bipedCapeTop.addChild(this.bipedCape);
        this.bipedBody.addChild(this.bipedCapeTop);
        this.bipedHessoniteHeadHair.addChild(this.bipedHeadwear);
        this.bipedBody.addChild(this.bipedRightShoulderpad);
        this.bipedBody_1.addChild(this.bipedHips);
        this.bipedBody.addChild(this.bipedRightLegBottom);
        this.bipedBody.addChild(this.bipedBody_1);
        this.bipedBody.addChild(this.bipedNeck);
        this.bipedLeftArm.addChild(this.bipedLeftArmPuff);
        this.bipedBody_1.addChild(this.bipedDress);
        this.bipedRightArm.addChild(this.bipedRightArmPuff);
        this.bipedNeck.addChild(this.bipedHessoniteHeadHair);
        this.bipedBody.addChild(this.bipedLeftLegBottom);
        this.bipedLeftLegBottom.addChild(this.bipedLeftLeg);
    }

    @Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.bipedBody.render(f5);
    }

    @Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
	{
		boolean flag = entityIn instanceof EntityLivingBase && ((EntityLivingBase)entityIn).getTicksElytraFlying() > 4;
		this.bipedHead.rotateAngleY = netHeadYaw * 0.017453292F;

		if (flag)
		{
			this.bipedHead.rotateAngleX = -((float)Math.PI / 4F);
		}
		else
		{
			this.bipedHead.rotateAngleX = headPitch * 0.017453292F;
		}

		this.bipedBody.rotateAngleY = 0.0F;
		/*this.bipedRightArm.rotationPointZ = 0.0F;
		this.bipedRightArm.rotationPointX = -5.0F;
		this.bipedLeftArm.rotationPointZ = 0.0F;
		this.bipedLeftArm.rotationPointX = 5.0F;*/
		float f = 1.0F;

		if (flag)
		{
			f = (float)(entityIn.motionX * entityIn.motionX + entityIn.motionY * entityIn.motionY + entityIn.motionZ * entityIn.motionZ);
			f = f / 0.2F;
			f = f * f * f;
		}

		if (f < 1.0F)
		{
			f = 1.0F;
		}

		this.bipedRightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
		this.bipedLeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
		this.bipedRightArm.rotateAngleZ = 0.0F;
		this.bipedLeftArm.rotateAngleZ = 0.0F;
		this.bipedRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.7F * limbSwingAmount / f;
		this.bipedLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 0.7F * limbSwingAmount / f;
		this.bipedRightLeg.rotateAngleY = 0.0F;
		this.bipedLeftLeg.rotateAngleY = 0.0F;
		this.bipedRightLeg.rotateAngleZ = 0.0F;
		this.bipedLeftLeg.rotateAngleZ = 0.0F;

		
		if (this.swingProgress > 0.0F)
		{
			EnumHandSide enumhandside = this.getMainHand(entityIn);
			ModelRenderer modelrenderer = this.getArmForSide(enumhandside);
			float f1 = this.swingProgress;
			this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float)Math.PI * 2F)) * 0.2F;

			if (enumhandside == EnumHandSide.LEFT)
			{
				this.bipedBody.rotateAngleY *= -1.0F;
			}

			this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
			this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
			this.bipedLeftArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
			this.bipedLeftArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
			this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
			this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
			this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
			f1 = 1.0F - this.swingProgress;
			f1 = f1 * f1;
			f1 = f1 * f1;
			f1 = 1.0F - f1;
			float f2 = MathHelper.sin(f1 * (float)Math.PI);
			float f3 = MathHelper.sin(this.swingProgress * (float)Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
			modelrenderer.rotateAngleX = (float)((double)modelrenderer.rotateAngleX - ((double)f2 * 1.2D + (double)f3));
			modelrenderer.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
			modelrenderer.rotateAngleZ += MathHelper.sin(this.swingProgress * (float)Math.PI) * -0.4F;
		}

		if (this.isSneak)
		{
			this.bipedBody.rotateAngleX = 0.5F;
			this.bipedRightArm.rotateAngleX += 0.4F;
			this.bipedLeftArm.rotateAngleX += 0.4F;
			this.bipedRightLeg.rotationPointZ = 4.0F;
			this.bipedLeftLeg.rotationPointZ = 4.0F;
			this.bipedRightLeg.rotationPointY = 9.0F;
			this.bipedLeftLeg.rotationPointY = 9.0F;
		}
		else
		{
			this.bipedBody.rotateAngleX = 0.0F;
		}

		this.bipedRightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
		this.bipedRightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		this.bipedLeftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;

		if (this.rightArmPose == ModelBiped.ArmPose.BOW_AND_ARROW)
		{
			this.bipedRightArm.rotateAngleY = -0.1F + this.bipedHead.rotateAngleY;
			this.bipedLeftArm.rotateAngleY = 0.1F + this.bipedHead.rotateAngleY + 0.4F;
			this.bipedRightArm.rotateAngleX = -((float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
			this.bipedLeftArm.rotateAngleX = -((float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
		}
		else if (this.leftArmPose == ModelBiped.ArmPose.BOW_AND_ARROW)
		{
			this.bipedRightArm.rotateAngleY = -0.1F + this.bipedHead.rotateAngleY - 0.4F;
			this.bipedLeftArm.rotateAngleY = 0.1F + this.bipedHead.rotateAngleY;
			this.bipedRightArm.rotateAngleX = -((float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
			this.bipedLeftArm.rotateAngleX = -((float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
		}

		copyModelAngles(this.bipedHead, this.bipedHeadwear);
		copyModelAngles(this.bipedNeck, this.bipedBody);
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
