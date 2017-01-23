package com.bitfire.postprocessing.filters;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.bitfire.utils.ShaderLoader;

public final class Pixelate extends Filter<Pixelate>{
	private float pixelation, aspectRatio, nightvision;
	public enum Param implements Parameter {
		// @formatter:off
		Texture0("u_texture0", 0), Pixelation("pixelation", 0), AspectRatio("aspectRatio",0), NightVision("nightvision",0), Time("time",0), Resolution("iResolution",2), Saturation("saturation", 1);
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

	
	public Pixelate() {
		super(ShaderLoader.fromFile("screenspace", "pixelate"));
		rebind();
		setPixelation(1f);
		setAspectRatio((float) Gdx.graphics.getWidth()/ Gdx.graphics.getHeight());
	}
	
	

	public void setPixelation (float pixelation) {
		this.pixelation = pixelation;
		setParam(Param.Pixelation, this.pixelation);
	}
	
	public void setNightVision(Boolean nv)
	{
		if(nv)nightvision=1;else nightvision=0;
		setParam(Param.NightVision, this.nightvision);
	}
	
	public void setTime(float time)
	{
		setParam(Param.Time, time);
	}
	
	 public float getPixelation() {
		return pixelation;
	}
	 
	 
	public void setSaturation(float saturation)
	{
		setParam(Param.Saturation, saturation);
	}
	
	@Override
	public void rebind() {
		// TODO Auto-generated method stub
		setParams(Param.Texture0, u_texture0);
		setParams(Param.Pixelation, pixelation);
		setParams(Param.AspectRatio, aspectRatio);
		setParams(Param.Resolution, new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		endParams();
	}

	@Override
	protected void onBeforeRender() {
		inputTexture.bind(u_texture0);
		
	}



	public float getAspectRatio() {
		return aspectRatio;
	}



	public void setAspectRatio(float aspectRatio) {
		this.aspectRatio = aspectRatio;
		setParam(Param.AspectRatio, this.aspectRatio);
	}

}
