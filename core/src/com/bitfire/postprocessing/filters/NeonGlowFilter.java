package com.bitfire.postprocessing.filters;

import com.bitfire.utils.ShaderLoader;

public class NeonGlowFilter extends Filter<NeonGlowFilter> {

	public NeonGlowFilter() {
		super(ShaderLoader.fromFile("screenspace", "neonglow"));
		rebind();
	}
	
	public enum Param implements Parameter {
		// @formatter:off
		Texture0("u_texture0", 0), Texture1("u_texture1", 1);
		// @formatter:on

		private final String mnemonic;
		private int elementSize;

		private Param (String m, int elementSize) {
			this.mnemonic = m;
			this.elementSize = elementSize;
		}

		@Override
		public String mnemonic () {
			return this.mnemonic;
		}

		@Override
		public int arrayElementSize () {
			return this.elementSize;
		}
	}

	@Override
	public void rebind() {
		// TODO Auto-generated method stub
		setParams(Param.Texture0, u_texture0);
		setParams(Param.Texture1, u_texture1);
		endParams();
	}

	@Override
	protected void onBeforeRender() {
		// TODO Auto-generated method stub
		inputTexture.bind(0);
		//PlayingScreen.sceneBuffer.getColorBufferTexture().bind(1);
		
	}

}
