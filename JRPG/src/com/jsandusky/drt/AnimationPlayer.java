package com.jsandusky.drt;
import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.*;

public class AnimationPlayer
{
	public interface TriggerListener {
		public void onTrigger(String trigger);
	}
	
	public String CurrentAnimation() {return currentAnimation;}
	public int getCurrentKeyframe() { return animations.get(currentAnimation).Keyframes.get(currentKeyframeIndex).FrameNumber; }
	public boolean Transitioning() {return transitioning;}
	
	String currentAnimation;
	float currentAnimationTime;
	int currentKeyframeIndex;

	boolean transitioning = false;
	String transitionAnimation;
	float transitionTime;
	float transitionTotalTime;

	ArrayList<BoneTransitionState> transitionStates;

	HashMap<String, Animation> animations = new HashMap<String, Animation>();

	public ArrayList<BoneTransformation> BoneTransformations;
	
	public void setCurrentTime(float time) {
		currentAnimationTime = time;
	}	

	public void add(String name, Animation animation)
	{
		animations.put(name, animation);

		if (BoneTransformations == null || animation.Keyframes.get(0).Bones.size() > BoneTransformations.size())
		{
			BoneTransformations = new ArrayList<BoneTransformation>(animation.Keyframes.get(0).Bones.size());
			for (int i = 0; i < animation.Keyframes.get(0).Bones.size(); ++i) {
				BoneTransformations.add(new BoneTransformation());
			}
			transitionStates = new ArrayList<BoneTransitionState>(animation.Keyframes.get(0).Bones.size());
			for (int i = 0; i < animation.Keyframes.get(0).Bones.size(); ++i) {
				transitionStates.add(new BoneTransitionState());
			}
		}
	}

	public void startAnimation(String animation)
	{
		startAnimation(animation, false);
	}

	public void startAnimation(String animation, boolean allowRestart)
	{
		transitioning = false;

		if (currentAnimation != animation || allowRestart)
		{
			currentAnimation = animation;
			currentAnimationTime = 0;
			currentKeyframeIndex = 0;

			for (Bone b : animations.get(currentAnimation).Keyframes.get(0).Bones)
			{
				transitionStates.get(b.UpdateIndex).Position = b.Position;
				transitionStates.get(b.UpdateIndex).Rotation = b.Rotation;
			}
		}

		update(0);
	}

	public void forceAnimationSwitch(String animation)
	{
		currentAnimation = animation;
	}

	public void transitionToAnimation(String animation, float time)
	{
		if (transitioning)
		{
			Animation.UpdateBoneTransitions(transitionStates, animations.get(currentAnimation), animations.get(transitionAnimation), transitionTime / transitionTotalTime);
		}

		transitioning = true;
		transitionTime = 0;
		transitionTotalTime = time;
		transitionAnimation = animation;
	}

	public int getBoneTransformIndex(String boneName)
	{
		Animation animation = animations.get(currentAnimation);

		for (int boneIndex = 0; boneIndex < animation.Keyframes.get(0).Bones.size(); boneIndex++)
		{
			Bone bone = animation.Keyframes.get(currentKeyframeIndex).Bones.get(boneIndex);
			if (bone.Name == boneName)
				return boneIndex;
		}

		return -1;
	}

	public boolean update(float deltaSeconds)
	{
		if (currentAnimation == null || currentAnimation.isEmpty())
			return false;

		boolean returnValue = false;
		int startKeyframeIndex = currentKeyframeIndex;

		if (transitioning)
		{
			transitionTime += deltaSeconds;

			if (transitionTime > transitionTotalTime)
			{
				transitioning = false;

				currentAnimation = transitionAnimation;
				currentAnimationTime = transitionTime - transitionTotalTime;
				currentKeyframeIndex = 0;

				Animation animation = animations.get(currentAnimation);
				animation.GetBoneTransformations(BoneTransformations, transitionStates, currentKeyframeIndex, currentAnimationTime - animation.Keyframes.get(currentKeyframeIndex).FrameTime);
			}
			else
			{
				Animation.GetBoneTransformationsTransition(BoneTransformations, transitionStates, animations.get(currentAnimation), animations.get(transitionAnimation), transitionTime / transitionTotalTime);
			}
		}
		else
		{
			boolean reachedEnd = false;

			currentAnimationTime += deltaSeconds;

			Animation animation = animations.get(currentAnimation);

			if (currentKeyframeIndex == animation.Keyframes.size() - 1)
			{
				if (animation.Loop)
				{
					if (currentAnimationTime > animation.LoopTime)
					{
						currentAnimationTime -= animation.LoopTime;
						currentKeyframeIndex = 0;
					}
				}
				else
				{
					currentAnimationTime = animation.Keyframes.get(currentKeyframeIndex).FrameTime;
					reachedEnd = true;
				}
			}
			else
			{
				if (currentAnimationTime > animation.Keyframes.get(currentKeyframeIndex + 1).FrameTime)
					currentKeyframeIndex++;
			}

			animation.GetBoneTransformations(BoneTransformations, transitionStates, currentKeyframeIndex, currentAnimationTime - animation.Keyframes.get(currentKeyframeIndex).FrameTime);

			returnValue = reachedEnd;
		}

/*		if (currentKeyframeIndex != startKeyframeIndex && KeyframeTriggerEvent != null &&
			!string.IsNullOrEmpty(animations[currentAnimation].Keyframes[currentKeyframeIndex].Trigger))
		{
			triggerEventArgs.TriggerString = animations[currentAnimation].Keyframes[currentKeyframeIndex].Trigger;
			KeyframeTriggerEvent(this, triggerEventArgs);
		}*/

		return returnValue;
	}

	public void draw(SpriteBatch spriteBatch, Vector2 position) {
		
		Animation animation = animations.get(currentAnimation);
		boolean flipHorizontal = animation.Keyframes.get(currentKeyframeIndex).FlipHorizontally;
		boolean flipVertical = animation.Keyframes.get(currentKeyframeIndex).FlipVertically;

		for (int boneIndex = 0; boneIndex < animation.Keyframes.get(0).Bones.size(); boneIndex++)
		{
			Bone bone = animation.Keyframes.get(currentKeyframeIndex).Bones.get(boneIndex);
			if (bone.Hidden)
				continue;

			/*Sprite sp = animation.Textures.get(bone.TextureIndex);
			sp.setOrigin(sp.getRegionWidth()/2,sp.getRegionHeight()/2);
			Matrix4 mate = mat.cpy();
			mate.mul(BoneTransformations.get(boneIndex).Transform.cpy().inv());
			spriteBatch.setTransformMatrix(mate);
			
			if (BoneTransformations.get(boneIndex).Position != null)
				sp.setPosition(BoneTransformations.get(boneIndex).Position.x,Gdx.graphics.getHeight()/2-BoneTransformations.get(boneIndex).Position.y);
			sp.setRotation(BoneTransformations.get(boneIndex).Rotation);
			
			if (BoneTransformations.get(boneIndex).Position != null)
				sp.setScale(BoneTransformations.get(boneIndex).Scale.x,BoneTransformations.get(boneIndex).Scale.y);
			sp.draw(spriteBatch);*/
			
			spriteBatch.draw(animation.Textures.get(bone.TextureIndex).Region, 
					position.x + BoneTransformations.get(boneIndex).Position.x - animation.Textures.get(bone.TextureIndex).Bounds.Origin.x, position.y - BoneTransformations.get(boneIndex).Position.y + animation.Textures.get(bone.TextureIndex).Bounds.Origin.y,
					//0.5f,0.5f,
					animation.Textures.get(bone.TextureIndex).Bounds.Origin.x, animation.Textures.get(bone.TextureIndex).Bounds.Origin.y,
					animation.Textures.get(bone.TextureIndex).Bounds.Location.width,
					animation.Textures.get(bone.TextureIndex).Bounds.Location.height,
					//animation.Textures.get(bone.TextureIndex).Bounds.Location.width,
					//animation.Textures.get(bone.TextureIndex).Bounds.Location.height, 
					1f, 1f, 
					BoneTransformations.get(boneIndex).Rotation, false);
			
			/*spriteBatch.draw(animation.Textures.get(bone.TextureIndex).getTexture(),
				BoneTransformations[boneIndex].Position.x, BoneTransformations[boneIndex].Position.y, 
				animation.Textures.get(bone.TextureIndex).getRegionWidth()/2,animation.Textures.get(bone.TextureIndex).getRegionHeight()/2,
				animation.Textures.get(bone.TextureIndex).getRegionWidth(),animation.Textures.get(bone.TextureIndex).getRegionHeight(),
							 BoneTransformations[boneIndex].Scale.x,BoneTransformations[boneIndex].Scale.y,
				BoneTransformations[boneIndex].Rotation);*/
		}
	}
	/*public void draw(SpriteBatch spriteBatch, Vector2 position)
	{
		Matrix4 idt = new Matrix4();
		idt.idt();
		draw(spriteBatch, position, false, false, 0, Color.WHITE, new Vector2(1, 1), idt);
	}*/

	public void draw(SpriteBatch spriteBatch, Vector2 position, boolean flipHorizontal, boolean flipVertical, float rotation, Color tintColor, Vector2 scale, Matrix4 cameraTransform)
	{
		if (currentAnimation == null || currentAnimation.isEmpty())
			return;

		Animation animation = animations.get(currentAnimation);

		flipHorizontal |= animation.Keyframes.get(currentKeyframeIndex).FlipHorizontally;
		flipVertical |= animation.Keyframes.get(currentKeyframeIndex).FlipVertically;

		for (int boneIndex = 0; boneIndex < animation.Keyframes.get(0).Bones.size(); boneIndex++)
		{
			Bone bone = animation.Keyframes.get(currentKeyframeIndex).Bones.get(boneIndex);
			if (bone.Hidden)
				continue;
			

			/*spriteBatch.Draw(animation.Textures[bone.TextureIndex].Texture, BoneTransformations[boneIndex].Position, animation.Textures[bone.TextureIndex].TextureBounds.Location, tintColor,
							 BoneTransformations[boneIndex].Rotation, animation.Textures[bone.TextureIndex].TextureBounds.Origin,
							 BoneTransformations[boneIndex].Scale, spriteEffects, 0);
							 */
		}
	}
}
