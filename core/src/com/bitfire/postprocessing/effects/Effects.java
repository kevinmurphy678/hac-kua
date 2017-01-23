//package com.bitfire.postprocessing.effects;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Pixmap.Format;
//import com.badlogic.gdx.graphics.glutils.FrameBuffer;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.math.Vector3;
//import com.badlogic.gdx.utils.Array;
//import com.bitfire.postprocessing.PostProcessorEffect;
//import com.bitfire.postprocessing.filters.Effect;
//import com.bitfire.postprocessing.filters.Shockwave;
//
//
////post processor effect for drawing multiple effects, eg: two shockwaves at once.
//public class Effects extends PostProcessorEffect{
//
//	public Array<Effect> effects = new Array<Effect>();
//
//	public static Boolean ShockWave(Vector2 position)
//	{
//		if(Core.isDedicated)return false;
//
//		Vector2 cameraPos = worldToScreen(position, true);
//		if(cameraPos.x > -0.25f && cameraPos.x < 1.25f && cameraPos.y > -0.25f && cameraPos.y < 1.25f)
//		{
//			Shockwave wave = new Shockwave();
//			wave.setCenter(new Vector2(position.x, position.y));
//			PlayingScreen.effects.effects.add(wave);
//			return true;
//		}
//		else
//			return false;
//	}
//
//	public static Vector2 worldToScreen(Vector2 position, Boolean normalize)
//	{
//		if(Core.isDedicated)
//			return new Vector2();
//		Vector3 cameraPos = PlayingScreen.camera.project(new Vector3(position, 0));
//		if(normalize)
//		{
//			cameraPos.x /= Gdx.graphics.getWidth();
//			cameraPos.y /= Gdx.graphics.getHeight();
//		}
//
//		return new Vector2(cameraPos.x, cameraPos.y);
//	}
//
//	public Effects (int fboWidth, int fboHeight){
//		// TODO Auto-generated constructor stub
//		fbo = new FrameBuffer(Format.RGBA8888, fboWidth, fboHeight, false);
//		fbo2 = new FrameBuffer(Format.RGBA8888, fboWidth, fboHeight, false);
//	}
//
//	@Override
//	public void dispose() {
//		// TODO Auto-generated method stub
//		for(Effect effect : effects)
//		{
//			effect.dispose();
//		}
//	}
//
//	@Override
//	public void rebind() {
//		// TODO Auto-generated method stub
//		for(Effect effect : effects)
//		{
//			effect.rebind();
//		}
//	}
//
//	FrameBuffer fbo;
//	FrameBuffer fbo2;
//	@Override
//	public void render(FrameBuffer src, FrameBuffer dest) {
//		// TODO Auto-generated method stub
//		restoreViewport(dest);
//		int num = 0;
//		int lastFBO=0;
//		//go away don't look at this ugliness
//		for(Effect effect : effects)
//		{
//			num++;
//			if(num==effects.size && effects.size > 1)
//			{
//				if(lastFBO==0)
//				{
//					effect.setInput(fbo).setOutput(dest).render();
//				}else if(lastFBO==1)
//				{
//					effect.setInput(fbo2).setOutput(dest).render();
//				}
//			}
//			else if(num==effects.size && effects.size==1)
//			{
//				effect.setInput(src).setOutput(dest).render();
//			}
//			else if(num==1 && effects.size > 1)
//			{
//				effect.setInput(src).setOutput(fbo).render();
//			}
//			else
//			{
//				if(lastFBO==0)
//				{
//					effect.setInput(fbo.getColorBufferTexture()).setOutput(fbo2).render();
//					lastFBO=1;
//				}
//				else if(lastFBO==1)
//				{
//					effect.setInput(fbo2.getColorBufferTexture()).setOutput(fbo).render();
//					lastFBO=0;
//				}
//			}
//
//			if(effect.timeAlive>effect.lifeTime)
//			{
//				//System.out.println("removing");
//				effects.removeValue(effect, false);
//			}
//		}
//	}
//
//	public void update(float delta) {
//		// TODO Auto-generated method stub
//		for(Effect effect : effects)
//		{
//			effect.setTime(delta);
//			effect.timeAlive+=delta;
//		}
//
//		if(effects.size <= 0)
//		{
//			this.setEnabled(false);
//		}else
//		{
//			this.setEnabled(true);
//		}
//	}
//
//	public void resetTime() {
//		// TODO Auto-generated method stub
//		for(Effect effect : effects)
//		{
//			effect.resetTime();
//		}
//
//	}
//
//}
