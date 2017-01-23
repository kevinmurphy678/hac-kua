package com.bitfire.postprocessing.effects;

import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.bitfire.postprocessing.PostProcessorEffect;
import com.bitfire.postprocessing.filters.Pixelate;

public class Pixelator extends PostProcessorEffect{
	private Pixelate pixelate;

	public Pixelator(){
		pixelate = new Pixelate();

	}
	
	public void setPixelation(float pixelation)
	{
		pixelate.setPixelation(pixelation);
	}
	
	public void setNightVision(Boolean nv)
	{
		pixelate.setNightVision(nv);
	}
	
	public void setTime(float time){
		pixelate.setTime(time);
	}
	
	public float getPixelation()
	{
		return pixelate.getPixelation();
	}
	
	public void setSaturation(float sat)
	{
		pixelate.setSaturation(sat);
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		pixelate.dispose();
	}

	@Override
	public void rebind() {
		// TODO Auto-generated method stub
		pixelate.rebind();
	}

	@Override
	public void render(FrameBuffer src, FrameBuffer dest) {
		// TODO Auto-generated method stub
		restoreViewport(dest);
		pixelate.setInput(src).setOutput(dest).render();
	}

}

