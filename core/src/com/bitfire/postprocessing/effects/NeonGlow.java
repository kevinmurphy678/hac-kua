package com.bitfire.postprocessing.effects;

import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.bitfire.postprocessing.PostProcessorEffect;
import com.bitfire.postprocessing.filters.NeonGlowFilter;

public class NeonGlow extends PostProcessorEffect{

	
	NeonGlowFilter neon = new NeonGlowFilter();
	public NeonGlow(){}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rebind() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(FrameBuffer src, FrameBuffer dest) {
		// TODO Auto-generated method stub
		neon.setInput(src).setOutput(dest).render();
	}

}
