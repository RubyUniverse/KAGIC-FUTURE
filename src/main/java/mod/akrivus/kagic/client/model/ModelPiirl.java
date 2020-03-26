package mod.akrivus.kagic.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

/**
 * Pearl - Undefined
 * Created using Tabula 7.1.0
 */
public class ModelPiirl extends ModelGem {
    public ModelRenderer bipedBody;
    public ModelRenderer bipedHead;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftLeg;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedDress;
    public ModelRenderer Shawl;
    public ModelRenderer Jacket;
    public ModelRenderer bipedCape;
    public ModelRenderer bipedHair;
    public ModelRenderer HatBase;
    public ModelRenderer LongHair;
    public ModelRenderer bipedNose;
    public ModelRenderer LeftHairBun;
    public ModelRenderer RightHairBun;
    public ModelRenderer BushyHair;
    public ModelRenderer BackHairBun;
    public ModelRenderer TiaraHairDiamonds;
    public ModelRenderer hat1;
    public ModelRenderer Hat2;
    public ModelRenderer Hat3;
    public ModelRenderer Hat4;
    public ModelRenderer LeftShoulderPuff;
    public ModelRenderer LeftYellowPearlShoulderPuff;
    public ModelRenderer bipedLeftArmPuff;
    public ModelRenderer RightShoulderPuff;
    public ModelRenderer RightYellowPearlShoulderPuff;
    public ModelRenderer bipedRightArmPuff;

    public ModelPiirl() {
    	super(0.0F, 0.0F, 130, 75, false, -1F);
        this.bipedBody = new ModelRenderer(this, 0, 18);
        this.bipedBody.setRotationPoint(-3.0F, -1.0F, -2.0F);
        this.bipedBody.addBox(0.0F, 0.0F, 0.0F, 6, 12, 4, 0.0F);
        this.bipedHead = new ModelRenderer(this, 36, 0);
        this.bipedHead.setRotationPoint(-1.0F, -8.0F, -2.0F);
        this.bipedHead.addBox(0.0F, 0.0F, 0.0F, 8, 8, 8, 0.0F);
        this.RightShoulderPuff = new ModelRenderer(this, 0, 34);
        this.RightShoulderPuff.mirror = true;
        this.RightShoulderPuff.setRotationPoint(-2.5F, -0.5F, -0.5F);
        this.RightShoulderPuff.addBox(0.0F, 0.0F, 0.0F, 3, 4, 3, 0.0F);
        this.hat1 = new ModelRenderer(this, 90, 15);
        this.hat1.setRotationPoint(2.5F, -3.0F, 2.5F);
        this.hat1.addBox(0.0F, 0.0F, 0.0F, 9, 3, 9, 0.0F);
        this.Hat2 = new ModelRenderer(this, 98, 27);
        this.Hat2.setRotationPoint(1.0F, -2.0F, 1.0F);
        this.Hat2.addBox(0.0F, 0.0F, 0.0F, 7, 2, 7, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 20, 18);
        this.bipedLeftArm.setRotationPoint(0.0F, 0.0F, 1.0F);
        this.bipedLeftArm.addBox(6.0F, 0.0F, 0.0F, 2, 14, 2, 0.0F);
        this.Jacket = new ModelRenderer(this, 56, 18);
        this.Jacket.setRotationPoint(-1.0F, 0.0F, -0.5F);
        this.Jacket.addBox(0.0F, 0.0F, 0.0F, 8, 8, 5, 0.0F);
        this.bipedRightLeg = new ModelRenderer(this, 44, 16);
        this.bipedRightLeg.setRotationPoint(4.0F, 12.0F, 1.0F);
        this.bipedRightLeg.addBox(0.0F, 0.0F, 0.0F, 2, 13, 2, 0.0F);
        this.LeftShoulderPuff = new ModelRenderer(this, 0, 34);
        this.LeftShoulderPuff.setRotationPoint(-0.5F, -0.5F, -0.5F);
        this.LeftShoulderPuff.addBox(0.0F, 0.0F, 0.0F, 3, 4, 3, 0.0F);
        this.LeftHairBun = new ModelRenderer(this, 46, 38);
        this.LeftHairBun.setRotationPoint(-2.0F, 1.5F, 1.5F);
        this.LeftHairBun.addBox(0.0F, 0.0F, 0.0F, 2, 5, 5, 0.0F);
        this.bipedLeftArmPuff = new ModelRenderer(this, 22, 34);
        this.bipedLeftArmPuff.setRotationPoint(-2.5F, 7.0F, -0.5F);
        this.bipedLeftArmPuff.addBox(0.0F, 0.0F, 0.0F, 3, 5, 3, 0.0F);
        this.BushyHair = new ModelRenderer(this, 80, 57);
        this.BushyHair.setRotationPoint(-2.0F, -0.5F, -0.5F);
        this.BushyHair.addBox(0.0F, 0.0F, 0.0F, 12, 9, 9, 0.0F);
        this.bipedDress = new ModelRenderer(this, 0, 41);
        this.bipedDress.setRotationPoint(-0.5F, 10.0F, -0.5F);
        this.bipedDress.addBox(0.0F, 0.0F, 0.0F, 7, 12, 5, 0.0F);
        this.bipedRightArmPuff = new ModelRenderer(this, 22, 34);
        this.bipedRightArmPuff.setRotationPoint(-0.5F, 7.0F, -0.5F);
        this.bipedRightArmPuff.addBox(0.0F, 0.0F, 0.0F, 3, 5, 3, 0.0F);
        this.Shawl = new ModelRenderer(this, 0, 58);
        this.Shawl.setRotationPoint(-2.5F, 0.0F, -0.5F);
        this.Shawl.addBox(0.0F, 0.0F, 0.0F, 11, 12, 5, 0.0F);
        this.TiaraHairDiamonds = new ModelRenderer(this, 72, 32);
        this.TiaraHairDiamonds.setRotationPoint(-1.5F, -4.0F, 4.5F);
        this.TiaraHairDiamonds.addBox(0.0F, 0.0F, 0.0F, 12, 8, 0, 0.0F);
        this.Hat3 = new ModelRenderer(this, 106, 36);
        this.Hat3.setRotationPoint(1.0F, -2.0F, 1.0F);
        this.Hat3.addBox(0.0F, 0.0F, 0.0F, 5, 2, 5, 0.0F);
        this.bipedCape = new ModelRenderer(this, 61, 42);
        this.bipedCape.setRotationPoint(-3.0F, 0.0F, 4.0F);
        this.bipedCape.addBox(0.0F, 0.0F, 0.0F, 12, 16, 0, 0.0F);
        this.setRotateAngle(bipedCape, 0.08726646259971647F, 0.0F, 0.0F);
        this.BackHairBun = new ModelRenderer(this, 36, 35);
        this.BackHairBun.setRotationPoint(2.5F, 2.5F, 9.0F);
        this.BackHairBun.addBox(0.0F, 0.0F, 0.0F, 4, 4, 3, 0.0F);
        this.RightHairBun = new ModelRenderer(this, 56, 31);
        this.RightHairBun.setRotationPoint(8.0F, 1.5F, 1.5F);
        this.RightHairBun.addBox(0.0F, 0.0F, 0.0F, 2, 5, 5, 0.0F);
        this.bipedHair = new ModelRenderer(this, 0, 0);
        this.bipedHair.setRotationPoint(-0.5F, -0.5F, -0.5F);
        this.bipedHair.addBox(0.0F, 0.0F, 0.0F, 9, 9, 9, 0.0F);
        this.RightYellowPearlShoulderPuff = new ModelRenderer(this, 24, 52);
        this.RightYellowPearlShoulderPuff.mirror = true;
        this.RightYellowPearlShoulderPuff.setRotationPoint(0.0F, -0.5F, -0.5F);
        this.RightYellowPearlShoulderPuff.addBox(0.0F, 0.0F, 0.0F, 4, 3, 3, 0.0F);
        this.bipedNose = new ModelRenderer(this, 0, 0);
        this.bipedNose.setRotationPoint(3.5F, 4.0F, -2.0F);
        this.bipedNose.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
        this.Hat4 = new ModelRenderer(this, 114, 43);
        this.Hat4.setRotationPoint(1.0F, -2.0F, 1.0F);
        this.Hat4.addBox(0.0F, 0.0F, 0.0F, 3, 2, 3, 0.0F);
        this.LeftYellowPearlShoulderPuff = new ModelRenderer(this, 24, 52);
        this.LeftYellowPearlShoulderPuff.setRotationPoint(-4.0F, -0.5F, -0.5F);
        this.LeftYellowPearlShoulderPuff.addBox(0.0F, 0.0F, 0.0F, 4, 3, 3, 0.0F);
        this.HatBase = new ModelRenderer(this, 68, 0);
        this.HatBase.setRotationPoint(-3.0F, -0.7F, -3.0F);
        this.HatBase.addBox(0.0F, 0.0F, 0.0F, 14, 1, 14, 0.0F);
        this.bipedRightArm = new ModelRenderer(this, 28, 18);
        this.bipedRightArm.setRotationPoint(0.0F, 0.0F, 1.0F);
        this.bipedRightArm.addBox(-2.0F, 0.0F, 0.0F, 2, 14, 2, 0.0F);
        this.LongHair = new ModelRenderer(this, 32, 53);
        this.LongHair.setRotationPoint(-0.5F, -0.5F, -0.5F);
        this.LongHair.addBox(0.0F, 0.0F, 0.0F, 9, 13, 9, 0.0F);
        this.bipedLeftLeg = new ModelRenderer(this, 36, 16);
        this.bipedLeftLeg.setRotationPoint(0.0F, 12.0F, 1.0F);
        this.bipedLeftLeg.addBox(0.0F, 0.0F, 0.0F, 2, 13, 2, 0.0F);
        this.bipedBody.addChild(this.bipedHead);
        this.bipedRightArm.addChild(this.RightShoulderPuff);
        this.HatBase.addChild(this.hat1);
        this.hat1.addChild(this.Hat2);
        this.bipedBody.addChild(this.bipedLeftArm);
        this.bipedBody.addChild(this.Jacket);
        this.bipedBody.addChild(this.bipedRightLeg);
        this.bipedLeftArm.addChild(this.LeftShoulderPuff);
        this.bipedHead.addChild(this.LeftHairBun);
        this.bipedLeftArm.addChild(this.bipedLeftArmPuff);
        this.bipedHead.addChild(this.BushyHair);
        this.bipedBody.addChild(this.bipedDress);
        this.bipedRightArm.addChild(this.bipedRightArmPuff);
        this.bipedBody.addChild(this.Shawl);
        this.bipedHair.addChild(this.TiaraHairDiamonds);
        this.Hat2.addChild(this.Hat3);
        this.bipedBody.addChild(this.bipedCape);
        this.bipedHair.addChild(this.BackHairBun);
        this.bipedHead.addChild(this.RightHairBun);
        this.bipedHead.addChild(this.bipedHair);
        this.bipedRightArm.addChild(this.RightYellowPearlShoulderPuff);
        this.bipedHead.addChild(this.bipedNose);
        this.Hat3.addChild(this.Hat4);
        this.bipedLeftArm.addChild(this.LeftYellowPearlShoulderPuff);
        this.bipedHead.addChild(this.HatBase);
        this.bipedBody.addChild(this.bipedRightArm);
        this.bipedHead.addChild(this.LongHair);
        this.bipedBody.addChild(this.bipedLeftLeg);
    }

    @Override
   	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
   		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
           this.bipedBody.render(scale);
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

