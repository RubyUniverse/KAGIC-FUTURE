package mod.akrivus.kagic.client.model;

import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.entity.gem.EntityAmethyst;
import mod.akrivus.kagic.entity.gem.EntityPeridot;
import mod.akrivus.kagic.entity.gem.EntityQuartzSoldier;
import mod.akrivus.kagic.init.KAGIC;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class ModelQuartz extends ModelGem {
	private final ModelRenderer bipedCape;
	protected ModelRenderer bipedDress;
    public ModelRenderer bipedLeftShoulderpad;
    public ModelRenderer bipedRightShoulderpad;
	
	public ModelQuartz() {
		this(0F, false);
	}
	
	public ModelQuartz(float modelSize, boolean isArmor) {
		super(modelSize, 0.0F, 96, isArmor ? 32 : 80, isArmor, -4F);
				// Head.
				this.bipedHead = new ModelRenderer(this, 0, 0);
				if (!isArmor) {
					this.bipedHead.addBox(-4F, -12F, -4F, 8, 8, 8, modelSize);
				} else {
					this.bipedHead.addBox(-4F, -12F, -4F, 8, 8, 8, modelSize + 0.5F);
				}
				this.bipedHead.setRotationPoint(0F, 0F, 0F);
				if (!isArmor && (KAGIC.isHalloween() || KAGIC.isBirthday() || KAGIC.isChristmas())) {
					this.bipedHead.addChild(this.witchHat);
				}
				// Hair.
				this.bipedHeadwear = new ModelRenderer(this, 32, 0);
				this.bipedHeadwear.addBox(-4F, -12F, -4F, 8, 8, 8, modelSize + 1.1F);
				this.bipedHeadwear.setRotationPoint(0F, 0F, 0F);
				
				// Body.
				this.bipedBody = new ModelRenderer(this, 16, 16);
				if (!isArmor) {
					this.bipedBody.addBox(-5F, -4F, -3F, 10, 16, 6, modelSize);
				} else {
					this.bipedBody.addBox(-4F, -1.25F, -2F, 8, 12, 4, modelSize + 1F);
				}
				this.bipedBody.setRotationPoint(0F, 0F, 0F);
				
				// Right arm.
				this.bipedRightArm = new ModelRenderer(this, 48, 16);
				if (isArmor) {
					this.bipedRightArm.setTextureOffset(40, 16);
				}
				this.bipedRightArm.addBox(-4F, -4F, -2F, 4, 14, 4, modelSize);
				this.bipedRightArm.setRotationPoint(0F, 0F, 0F);
				
				// Left arm.
				this.bipedLeftArm = new ModelRenderer(this, 48, 34);
				if (isArmor) {
					this.bipedLeftArm.setTextureOffset(40, 16);
					this.bipedLeftArm.mirror = true;
				}
				this.bipedLeftArm.addBox(0F, -4F, -2F, 4, 14, 4, modelSize);
				this.bipedLeftArm.setRotationPoint(0F, 0F, 0F);
				
				// Right leg.
				this.bipedRightLeg = new ModelRenderer(this, 0, 16);
				if (isArmor) {
					this.bipedRightLeg.setTextureOffset(0, 16);
				}
				this.bipedRightLeg.addBox(1F, 0F, -2F, 4, 12, 4, modelSize);
				this.bipedRightLeg.setRotationPoint(0F, 0F, 0F);
				
			  	// Left leg.
				this.bipedLeftLeg = new ModelRenderer(this, 0, 32);
				if (isArmor) {
					this.bipedLeftLeg.setTextureOffset(0, 16);
					this.bipedLeftLeg.mirror = true;
				}
				this.bipedLeftLeg.addBox(-5F, 0F, -2F, 4, 12, 4, modelSize);
				this.bipedLeftLeg.setRotationPoint(0F, 0F, 0F);

				this.bipedCape = new ModelRenderer(this, 0, 0);
				this.bipedCape.setTextureSize(64, 32);
				this.bipedCape.addBox(-5.0F, -4.0F, -2.f, 10, 20, 1, modelSize);
				// Dress.
				this.bipedDress = new ModelRenderer(this, 6, 45);
				this.bipedDress.setRotationPoint(0.0F, 6.0F, 0.0F);
				this.bipedDress.addBox(-7F, 0.0F, -6.0F, 14, 18, 12, 0.7F);
				
				// Right Shoulderpads.
				this.bipedRightShoulderpad = new ModelRenderer(this, 66, 61);
			    this.bipedRightShoulderpad.setRotationPoint(0.0F, -4.5F, 0.0F);
			    this.bipedRightShoulderpad.addBox(-5.0F, 0.0F, -2.8F, 6, 5, 6, 0.0F);
			    
				// Left Shoulderpads.
				this.bipedLeftShoulderpad = new ModelRenderer(this, 66, 50);
		        this.bipedLeftShoulderpad.setRotationPoint(0.0F, -4.5F, 0.0F);
		        this.bipedLeftShoulderpad.addBox(-1.2F, 0.0F, -2.8F, 6, 5, 6, 0.0F);
		        
		        this.bipedRightArm.addChild(this.bipedRightShoulderpad);
		        this.bipedLeftArm.addChild(this.bipedLeftShoulderpad);
}
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		this.bipedHead.render(scale);
		this.bipedHeadwear.render(scale);
		this.bipedBody.render(scale);
		this.bipedRightArm.render(scale);
		this.bipedLeftArm.render(scale);
		this.bipedDress.render(scale);
		if (entityIn instanceof EntityQuartzSoldier) {
			EntityQuartzSoldier gem = (EntityQuartzSoldier) entityIn;
			if (gem.getUniformStyle() != 2) {
		        this.bipedRightLeg.render(scale);
		        this.bipedLeftLeg.render(scale);
			}
			else if (gem.getUniformStyle() != 4) {
			        this.bipedRightLeg.render(scale);
			        this.bipedLeftLeg.render(scale);
				}
				else if (gem.getUniformStyle() != 5) {
							        this.bipedRightLeg.render(scale);
							        this.bipedLeftLeg.render(scale);
								}
					else if (gem.getUniformStyle() != 6) {
						this.bipedRightLeg.render(scale);
						this.bipedLeftLeg.render(scale);
						}
					}
				}
					        
			        	
		
	@Override
	public void renderCape(float scale)
	{
		this.bipedCape.render(scale);
	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
		super.copyModelAngles(this.bipedHead, this.bipedHeadwear);
		
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
	
	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float p_78086_2_, float p_78086_3_, float partialTickTime) {
		this.rightArmPose = ModelBiped.ArmPose.EMPTY;
		this.leftArmPose = ModelBiped.ArmPose.EMPTY;
		if (entitylivingbaseIn instanceof EntityGem) {
			ItemStack itemstack = entitylivingbaseIn.getHeldItem(EnumHand.MAIN_HAND);
			EntityGem gem = (EntityGem) entitylivingbaseIn;
			if (itemstack != null && itemstack.getItem() == Items.BOW && gem.isSwingingArms()) {
				if (entitylivingbaseIn.getPrimaryHand() == EnumHandSide.RIGHT) {
					this.rightArmPose = ModelBiped.ArmPose.BOW_AND_ARROW;
				}
				else {
					this.leftArmPose = ModelBiped.ArmPose.BOW_AND_ARROW;
				}
			}
		}
		super.setLivingAnimations(entitylivingbaseIn, p_78086_2_, p_78086_3_, partialTickTime);
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


