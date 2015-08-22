package com.etek.constant;

public class InfraredFunc
{
	
	//######################### 空调模式 #####################
//	AUTO(0), WIND(1), DRY(2), HOT(3), COOL(4);

	
	/**信号功能：空调模式->“自动模式”*/
	public final static int AIR_MODE_AUTO = 0;
	
	/**信号功能：空调模式->“送风模式”*/
	public final static int AIR_MODE_WIND = 1;
	
	/**信号功能：空调模式->“抽湿模式”*/
	public final static int AIR_MODE_DRY = 2;
	
	/**信号功能：空调模式->“制冷模式”*/
	public final static int AIR_MODE_COOL = 4;
	
	/**信号功能：空调模式->“取暖模式”*/
	public final static int AIR_MODE_WARM = 3;
	
	//######################### 空调风向 #####################

	/**信号功能：空调风向->“自动”*/
	public final static int AIR_WIND_DIRECTION_AUTO = 0;
	
	/**信号功能：空调风向->“上”*/
	public final static int AIR_WIND_DIRECTION_UP = 1;
	
	/**信号功能：空调风向->“中”*/
	public final static int AIR_WIND_DIRECTION_MIDDLE = 2;
	
	/**信号功能：空调风向->“下”*/
	public final static int AIR_WIND_DIRECTION_DOWN = 3;
	
	
	//######################### 空调风量 #####################
//	LEVEL_1(0), LEVEL_2(1), LEVEL_3(2), LEVEL_4(3), AUTO(4);
	

	/**信号功能：空调风量->“一档”*/
	public final static int AIR_WIND_AMOUNT_LEVEL_1 = 0;
	
	/**信号功能：空调风量->的“一档”*/
	public final static int AIR_WIND_AMOUNT_LEVEL_2 = 1;
	
	/**信号功能：空调风量->“一档”*/
	public final static int AIR_WIND_AMOUNT_LEVEL_3 = 2;
	
	/**信号功能：空调风量->“一档”*/
	public final static int AIR_WIND_AMOUNT_LEVEL_4 = 3;
	
	/**信号功能：空调风量->“自动档”*/
	public final static int AIR_WIND_AMOUNT_AUTO = 4;
	
	
	//######################### 空调 水平风向 #################
//	HOZ_OFF(0), HOZ_ON(1);

	/**信号功能：空调水平风向->“关闭”*/
	public final static int AIR_WIND_HOZ_OFF = 0;
	
	/**信号功能：空调水平风向->“开启”*/
	public final static int AIR_WIND_HOZ_ON = 1;
	
	//######################### 空调 垂直风向 #################
//	VER_OFF(0), VER_ON(1);
	
	/**信号功能：空调垂直风向->“关闭”*/
	public final static int AIR_WIND_VER_OFF = 0;
	
	/**信号功能：空调垂直风向->“开启”*/
	public final static int AIR_WIND_VER_ON = 1;
	
}
