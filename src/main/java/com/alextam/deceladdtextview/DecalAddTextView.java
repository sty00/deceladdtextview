package com.alextam.deceladdtextview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created on 5/12/2015 15:09
 * 自增/自减数字文本 
 * 
 * @author KinHO
 *
 */
public class DecalAddTextView extends TextView{
	private String TAG = "DecalAddTextView";
	
	private DecimalFormat format = new DecimalFormat("0.000");
	//真实值
	private double realNum;
	//自增或自减量
	private double variable;
	//TextView显示的值
	private double tvValue;
	private boolean ifCancel = false;
	
	private int decalOpType = 0;
	private static int PLUS = 1;
	private static int MINUS = 2;
	private static int FINISHED = 3;
	
	
	private Handler mHandler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			if(msg.what == PLUS)
			{
				if(tvValue < realNum)
				{
					setText(format.format(tvValue));
					if(!ifCancel)
					{
						tvValue += variable;
						mHandler.sendEmptyMessageDelayed(PLUS, 30);
					}
				}
				else
				{
					decalOpType = FINISHED;
					setText(format.format(realNum));
				}
			}
			else if(msg.what == MINUS)
			{
				if(tvValue > 0)
				{
					setText(format.format(tvValue));
					if(!ifCancel)
					{
						tvValue -= variable;
						mHandler.sendEmptyMessageDelayed(MINUS, 30);
					}
				}
				else
				{
					decalOpType = FINISHED;
					setText("0.000");
				}
			}
		}
	};
	
	
	public DecalAddTextView(Context context){
		this(context, null);
	}
	
	
	public DecalAddTextView(Context context, AttributeSet attrs){
		this(context, attrs, 0);
	}
	
	
	public DecalAddTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	
	public void setValue(double d)
	{
		realNum = d;
	}
	
	/**
	 * 开始自增
	 */
	public void startDecalPlus()
	{
		decalOpType = PLUS;
		tvValue = 0.000;
		//控制变化
		variable = realNum / 30.000;
		mHandler.sendEmptyMessage(PLUS);
	}
	
	/**
	 * 开始自减
	 */
	public void startDecalMinus()
	{
		decalOpType = MINUS;
		tvValue = realNum;
		variable = realNum / 30.000;
		mHandler.sendEmptyMessage(MINUS);
	}
	
	public void cancel()
	{
		ifCancel = true;
	}
	
	public void continueDecalNum()
	{
		if(decalOpType == PLUS)
		{
			mHandler.sendEmptyMessage(PLUS);
		}
		else if(decalOpType == MINUS)
		{
			mHandler.sendEmptyMessage(MINUS);
		}
		else
		{
			Log.e(TAG, "tvValue may be zero that PLUS or MINUS's method can not be invoked...");
		}
	}
	
}
