package com.bitfire.postprocessing.effects;

import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.bitfire.postprocessing.PostProcessorEffect;
import com.bitfire.postprocessing.filters.GlitchEffect;

public class Glitcher extends PostProcessorEffect{
	
	public Glitcher(){
		glitchEffect = new GlitchEffect();
	}
	
	GlitchEffect glitchEffect;
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rebind() {
		// TODO Auto-generated method stub
		glitchEffect.rebind();
	}

	@Override
	public void render(FrameBuffer src, FrameBuffer dest) {
		// TODO Auto-generated method stub
		if(glitchEffect.getAmplitude()>0)
		{
			restoreViewport(dest);
			glitchEffect.setInput(src).setOutput(dest).render();
		}
	}
	
	public void setTime(float time, float delta)
	{
		setAmplitude(glitchEffect.getAmplitude() - 2f * delta);
		if(glitchEffect.getAmplitude()<=0) glitchEffect.setAmplitude(0);
		
		glitchEffect.setTime(time);
		
		if(glitchEffect.getAmplitude() > 0)
		{
			this.setEnabled(true);
		}
		else
		{
			this.setEnabled(false);
		}
	}
	
	public void setAmplitude(float amp)
	{
		glitchEffect.setAmplitude(amp);
	}
	
	public void setSpeed(float speed)
	{
		glitchEffect.setSpeed(speed);
	}

}
