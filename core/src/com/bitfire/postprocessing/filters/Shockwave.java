//package com.bitfire.postprocessing.filters;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.math.Vector3;
//import com.bitfire.postprocessing.effects.Effects;
//import com.bitfire.utils.ShaderLoader;
//
//public final class Shockwave extends Effect{
//	private Vector3 shockParams;
//	private float aspectRatio;
//
//	public enum Param implements Parameter {
//		// @formatter:off
//		Texture0("u_texture0", 0), Time("time",0), Center("center", 2), ShockParams("shockParams",3),Resolution("resolution",2);
//		// @formatter:on
//
//		private final String mnemonic;
//		private int elementSize;
//
//		private Param (String m, int elementSize) {
//			this.mnemonic = m;
//			this.elementSize = elementSize;
//		}
//
//		@Override
//		public String mnemonic () {
//			return this.mnemonic;
//		}
//
//		@Override
//		public int arrayElementSize () {
//			return this.elementSize;
//		}
//	}
//
//
//	public Shockwave() {
//		super(ShaderLoader.fromFile("screenspace", "shockwave"));
//
//		this.lifeTime = 2;
//
//		setAspectRatio((float) Gdx.graphics.getWidth()/ Gdx.graphics.getHeight());
//		setShockWaveParams(new Vector3(10f,0.8f,0.1f));
//		//setCenter(center);
//		setParam(Param.Resolution, new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
//		rebind();
//
//	}
//
//	public void setShockWaveParams (Vector3 params) {
//		this.shockParams = params;
//		setParam(Param.ShockParams, this.shockParams);
//		endParams();
//	}
//
//	@Override
//	public void rebind() {
//		// TODO Auto-generated method stub
//		setParams(Param.Texture0, u_texture0);
//
//		setParams(Param.Center, Effects.worldToScreen(center, true));
//
//		setParams(Param.ShockParams, shockParams);
//		endParams();
//	}
//
//	@Override
//	protected void onBeforeRender() {
//		rebind();
//
//		super.onBeforeRender();
//	}
//
//
//}
