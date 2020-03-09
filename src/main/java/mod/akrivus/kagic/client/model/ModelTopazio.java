package mod.akrivus.kagic.client.model;

import mod.akrivus.kagic.entity.gem.EntityTopaz;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

/**
 * ModelTopaz - taaffeite
 * animated by - mike_ultimate2
 * Created using Tabula 7.0.1
 */
public class ModelTopazio extends ModelGem {
    public ModelRenderer bipedHead;
    public ModelRenderer bipedBody;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftLeg;
    public ModelRenderer bipedRightLeg;

    public ModelTopazio() {
    	super(0.0F, 0.0F, 64, 64, false, 7F);
        this.bipedBody = new ModelRenderer(this, 16, 16);
        this.bipedBody.setRotationPoint(0.0F, -3.0F, 0.0F);
        this.bipedBody.addBox(-6.5F, 0.0F, -5.0F, 13, 16, 10, 0.0F);
        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.setRotationPoint(0.0F, -3.0F, 0.0F);
        this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 0, 16);
        this.bipedLeftArm.setRotationPoint(8.5F, 0.0F, 0.0F);
        this.bipedLeftArm.addBox(-2.0F, 2.2F, -2.0F, 4, 15, 4, 0.0F);
        this.bipedRightArm = new ModelRenderer(this, 0, 35);
        this.bipedRightArm.setRotationPoint(-8.5F, 0.0F, 0.0F);
        this.bipedRightArm.addBox(-2.0F, 2.2F, -2.0F, 4, 15, 4, 0.0F);
        this.bipedRightLeg = new ModelRenderer(this, 32, 42);
        this.bipedRightLeg.setRotationPoint(-3.0F, 13.0F, 0.0F);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 11, 4, 0.0F);
        this.bipedLeftLeg = new ModelRenderer(this, 16, 42);
        this.bipedLeftLeg.setRotationPoint(3.0F, 13.0F, 0.0F);
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 11, 4, 0.0F);
    }

    @Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        this.bipedBody.render(scale);
        this.bipedHead.render(scale);
        this.bipedLeftArm.render(scale);
        this.bipedRightArm.render(scale);
        this.bipedRightLeg.render(scale);
        this.bipedLeftLeg.render(scale);
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
  		if (entityIn instanceof EntityTopaz) {
			EntityTopaz topaz = (EntityTopaz) entityIn;
			if (topaz.isHolding()) {
				this.bipedLeftArm.rotationPointY = 3F;
				this.bipedLeftArm.rotateAngleX = 3.15F;
				//this.bipedLeftArm.rotateAngleZ = 0.15F;
				this.bipedRightArm.rotationPointY = 3F;
				this.bipedRightArm.rotateAngleX = 3.15F;
				//this.bipedRightArm.rotateAngleZ = -0.15F;
			}
			else {
				this.bipedLeftArm.rotationPointY = -5F;
				this.bipedRightArm.rotationPointY = -5F;
			}
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
