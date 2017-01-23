//package com.bitfire.postprocessing.filters;
//
//import com.badlogic.gdx.graphics.glutils.ShaderProgram;
//import com.badlogic.gdx.math.Vector2;
//import com.bitfire.postprocessing.filters.Shockwave.Param;
//
//public class Effect extends Filter<Effect> {
//
//	public float timeAlive;
//	public Boolean convertCenterToWorld=false;
//	public Effect(ShaderProgram program) {
//		super(program);
//		// TODO Auto-generated constructor stub
//
//	}
//
//	@Override
//	public void rebind() {
//		// TODO Auto-generated method stub
//	}
//
//	@Override
//	protected void onBeforeRender() {
//		//setParam(Param.Center, this.center);
//		//endParams();
//
//		inputTexture.bind(u_texture0);
//
//	}
//
//	public float currentTime=0;
//	public Vector2 center = new Vector2();
//	public float lifeTime;
//
//	public void setTime(float time)
//	{
//		currentTime+=time;
//		setParam(Param.Time, currentTime);
//		endParams();
//	}
//
//	public void resetTime()
//	{
//		currentTime=0;
//	}
//
//	public void setCenter(Vector2 center) {
//		this.center = center;
//	}
//
//	public void setAspectRatio(float ratio) {
//		// TODO Auto-generated method stub
//
//	}
//
//}
