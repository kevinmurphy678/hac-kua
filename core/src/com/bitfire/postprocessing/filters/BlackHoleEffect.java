//package com.bitfire.postprocessing.filters;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.glutils.ShaderProgram;
//import com.badlogic.gdx.math.Vector2;
//import com.bitfire.utils.ShaderLoader;
//
//public final class BlackHoleEffect extends Effect{
//
//	public enum Param implements Parameter {
//		// @formatter:off
//		Texture0("u_texture0", 0), Time("time",0), Center("center", 2),Resolution("resolution",2);
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
//	public static ShaderProgram shader = ShaderLoader.fromFile("screenspace", "blackHole");
//	public BlackHoleEffect() {
//		super(shader);
//
//		this.lifeTime = 10;
//
//		setAspectRatio((float) Gdx.graphics.getWidth()/ Gdx.graphics.getHeight());
//		setCenter(center);
//		setParam(Param.Resolution, new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
//		rebind();
//
//	}
//
//	@Override
//	public void rebind() {
//		// TODO Auto-generated method stub
//		setParams(Param.Texture0, u_texture0);
//		setParams(Param.Center, center);
//		endParams();
//	}
//
//	@Override
//	protected void onBeforeRender() {
//		setParam(Param.Center, this.center);
//		endParams();
//
//		super.onBeforeRender();
//	}
//
//
//}
