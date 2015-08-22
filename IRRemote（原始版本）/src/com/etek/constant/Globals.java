package com.etek.constant;

import java.util.ArrayList;
import java.util.List;

import com.etek.bean.Brand;
import com.etek.bean.IRKey;

import com.etek.bean.ModelNum;
import com.etek.bean.Remote;
import com.etek.irremote.R;

public class Globals {
	public static int BUTTON_WIDTH = 70;
	public static float DPI = 1.0F;
	// public static CommandManager commandManager;
	public final static int ID_TERMINAL = 1;
	public final static int ID_INFORLOCATION = 2;
	public final static int ID_LEARN_EDIT = 3;
	public final static int ID_ADDREMOTE = 4;
	public final static int WHAT_LEARN = 1;
	public static int DEVICE = DeviceType.DUMMY;
	// public static final String REMOTES = null;
	public static String ADD_REMOTE;
	public static boolean isAdd;
	// public static final HashMap<String, Integer> BG_COLORS;
	public static String BRANDS;
	public static String CACHE_FOLDER;
	public static String CODES;
	// public static String DEVICEs;
	// public static String TERMINAL_TYPE;

	public static Boolean VIBRATION;
	public static Boolean POWER;
	public static String TEST_MODE;
	public static String NEW_REMOTE;
	public static String REMOTE_SHARED_PREFF;

	public static boolean ISLEARN;
	public static boolean INITIAL;
	public static boolean NETCONNECT;
	public static ArrayList<Brand> MBrands;
	public static ArrayList<Remote> mRemotes = new ArrayList<Remote>();
	public static int mIndex = 0;
	public static Brand MBrand;
	// public static int TYPE = ApplianceType.TV;
	public final static int LEARN_RESUIT_REQUEST = 100;

	public static String GETSERVERBRAND = "http://222.191.229.234:10068/SmartHomeServer/wyf/getBrand/";
	public static String GETSERVERKEYWOARDS = "http://222.191.229.234:10068/SmartHomeServer/wyf/getModelNumberByKeywords/";
	public static String GETSERVERREMOTEKEY = "http://222.191.229.234:10068/SmartHomeServer/wyf/getRemoteKey/";
	public static String GETSERVERSEARCHREMOTE = "http://222.191.229.234:10068/SmartHomeServer/wyf/getSearchRemoteKey/";
	

//	static {
//
//		CACHE_FOLDER = "cache";
//		REMOTE_SHARED_PREFF = "SHARED_REMOTE";
//		VIBRATION = false;
//		ISLEARN = false;
//		POWER = false;
//		NEW_REMOTE = "new_remote";
//
//	}

	public static int toPx(float paramFloat) {
		return (int) (paramFloat * DPI);
	}

	public static int HEADER = 0x99;

	public static int flag = 0;

	public static String[] REMOTE_TYPE;
	public static int selectStatus = 0;

	public static Boolean audio = true;
	// public static Boolean powerSupply= false;
	public static Boolean modeLearning = false;
	public static String currentKey = null;

	public static Boolean exist = false;

	// public static int cKey = 0;

	public static int screenWidth;
	public static int screenHeight;

	// public static HashMap<String, String> keyRemoteTab ;
	// public static ArrayList<KeyValue> keyValueTab ;
	public static int test_mode = 0;
	public static int totalRemote = 0;

	// public static HashMap<String,String > KeyTable = new HashMap<String,
	// String>();

	public static boolean vibrate = false;
	public static boolean animation = false;
	public static boolean proOrCh = false;

	public static String tvLocation;
	public static String tvProvider;

	public static final int APP_TYPE = 6;
	public static final String CONF_NAME = "version.conf";
	public static final boolean CONTAIN_LOCAL_DATA = true;

	public static int MAX_DEV_ID = 6;

	public static final boolean STUDY_FUNC = true;
	public static IRKey KeyValue;

	// public static final String SYS_DB_NAME = "system.db";
	// public static final String SYS_DB_VER = "1.0";
	//
	// public static final String USER_DB_NAME = "user.db";
	// public static final String USER_DB_VER = "1.3";
	// public static final int WEIBO_CONTENT_COUNT = 10;

	public static boolean isDebug;
	public static boolean isFullDev;
	public static boolean isMultiThreadSend;
	public static int LocalLanguage;
	public static Remote mRemote;
	public static int deviceID = ApplianceType.TV;

	public static List<ModelNum> modelSearchs;;
	public final static int[] bckImgIDs = { R.drawable.btn_normal_selector, // normal//
			R.drawable.main_btn_power_selector, // power_on//
			R.drawable.btn_normal_selector, // num_1//
			R.drawable.btn_normal_selector, // num_2//
			R.drawable.btn_normal_selector, // num_3//
			R.drawable.btn_normal_selector, // num_4//
			R.drawable.btn_normal_selector, // num_5//
			R.drawable.btn_normal_selector, // num_6//
			R.drawable.btn_normal_selector, // num_7//
			R.drawable.btn_normal_selector, // num_8//
			R.drawable.btn_normal_selector, // num_9//
			R.drawable.btn_normal_selector, // num_10//
			R.drawable.btn_normal_selector, // num_0//
			R.drawable.btn_normal_selector, // back//
			R.drawable.btn_normal_selector, // eject//
			R.drawable.btn_normal_selector, // exit//
			R.drawable.btn_normal_selector, // favorite//
			R.drawable.btn_normal_selector, // ff//
			R.drawable.btn_normal_selector, // home//
			R.drawable.btn_normal_selector, // horizontal//
			R.drawable.btn_normal_selector, // input//
			R.drawable.btn_normal_selector, // mute//
			R.drawable.btn_normal_selector, // next//
			R.drawable.btn_normal_selector, // pause//
			R.drawable.btn_normal_selector, // play//
			R.drawable.btn_normal_selector, // prev//
			R.drawable.btn_normal_selector, // quickskip//
			R.drawable.btn_normal_selector, // rec//
			R.drawable.btn_normal_selector, // rew//
			R.drawable.btn_normal_selector, // setting//
			R.drawable.btn_normal_selector, // stop//
			R.drawable.btn_normal_selector, // vertical//
			R.drawable.btn_normal_selector, // hyphen//
			R.drawable.btn_normal_selector, // down//
			R.drawable.btn_normal_selector, // left//
			R.drawable.btn_normal_selector, // info//
			R.drawable.btn_normal_selector, // menu//
			R.drawable.btn_normal_selector, // play_tri//
			R.drawable.btn_normal_selector, // play_cir//
			R.drawable.btn_normal_selector, // play_rec//
			R.drawable.btn_normal_selector, // play_cro//
			R.drawable.btn_normal_selector, // repeat//
			R.drawable.btn_normal_selector, // right//
			R.drawable.btn_normal_selector, // shuffle//
			R.drawable.btn_normal_selector, // up//
			R.drawable.btn_normal_selector, // play_pause//
			R.drawable.btn_normal_selector, // blue//
			R.drawable.btn_normal_selector, // green//
			R.drawable.btn_normal_selector, // red//
			R.drawable.btn_normal_selector, // yellow//
	};
	public final static int[] srcImgIDs = { R.drawable.btn_normal_selector, // normal//
			R.drawable.main_ic_power, // power_on//
			R.drawable.main_num_1, // num_1//
			R.drawable.main_num_2, // num_2//
			R.drawable.main_num_3, // num_3//
			R.drawable.main_num_4, // num_4//
			R.drawable.main_num_5, // num_5//
			R.drawable.main_num_6, // num_6//
			R.drawable.main_num_7, // num_7//
			R.drawable.main_num_8, // num_8//
			R.drawable.main_num_9, // num_9//
			R.drawable.main_num_10, // num_10//
			R.drawable.main_num_0, // num_0//
			R.drawable.main_ic_back, // back//
			R.drawable.main_ic_eject, // eject//
			R.drawable.main_ic_exit, // exit//
			R.drawable.main_ic_favorite, // favorite//
			R.drawable.main_ic_ff, // ff//
			R.drawable.main_ic_home, // home//
			R.drawable.main_ic_horizontal, // horizontal//
			R.drawable.main_ic_input, // input//
			R.drawable.main_ic_mute, // mute//
			R.drawable.main_ic_next, // next//
			R.drawable.main_ic_pause, // pause//
			R.drawable.main_ic_play, // play//
			R.drawable.main_ic_prev, // prev//
			R.drawable.main_ic_quickskip, // quickskip//
			R.drawable.main_ic_rec, // rec//
			R.drawable.main_ic_rew, // rew//
			R.drawable.main_ic_setting, // setting//
			R.drawable.main_ic_stop, // stop//
			R.drawable.main_ic_vertical, // vertical//
			R.drawable.main_num_hyphen, // hyphen//
			R.drawable.universal_ic_down, // down//
			R.drawable.universal_ic_left, // left//
			R.drawable.universal_ic_info, // info//
			R.drawable.universal_ic_menu, // menu//
			R.drawable.universal_ic_playstation_01, // play_tri//
			R.drawable.universal_ic_playstation_02, // play_cir//
			R.drawable.universal_ic_playstation_03, // play_rec//
			R.drawable.universal_ic_playstation_04, // play_cro//
			R.drawable.universal_ic_repeat, // repeat//
			R.drawable.universal_ic_right, // right//
			R.drawable.universal_ic_shuffle, // shuffle//
			R.drawable.universal_ic_up, // up//
			R.drawable.widget_noti_ic_play_pause, // play_pause//
			R.drawable.main_color_b_selector, // blue//
			R.drawable.main_color_g_selector, // green//
			R.drawable.main_color_r_selector, // red//
			R.drawable.main_color_y_selector, // yellow//

	};

	public final static int[] srcStrIDs = { R.string.remote, R.string.power_on, // power_on//
			R.string.num_1, // num_1//
			R.string.num_2, // num_2//
			R.string.num_3, // num_3//
			R.string.num_4, // num_4//
			R.string.num_5, // num_5//
			R.string.num_6, // num_6//
			R.string.num_7, // num_7//
			R.string.num_8, // num_8//
			R.string.num_9, // num_9//
			R.string.num_10, // num_10//
			R.string.num_0, // num_0//
			R.string.back, // back//
			R.string.eject, // eject//
			R.string.exit, // exit//
			R.string.favorite, // favorite//
			R.string.ff, // ff//
			R.string.home, // home//
			R.string.horizontal, // horizontal//
			R.string.input, // input//
			R.string.mute, // mute//
			R.string.next, // next//
			R.string.pause, // pause//
			R.string.play, // play//
			R.string.prev, // prev//
			R.string.quickskip, // quickskip//
			R.string.rec, // rec//
			R.string.rew, // rew//
			R.string.setting, // setting//
			R.string.stop, // stop//
			R.string.vertical, // vertical//
			R.string.hyphen, // hyphen//
			R.string.down, // down//
			R.string.left, // left//
			R.string.info, // info//
			R.string.menu, // menu//
			R.string.play_tri, // play_tri//
			R.string.play_cir, // play_cir//
			R.string.play_rec, // play_rec//
			R.string.play_cro, // play_cro//
			R.string.repeat, // repeat//
			R.string.right, // right//
			R.string.shuffle, // shuffle//
			R.string.up, // up//
			R.string.play_pause, // play_pause//
			R.string.blue, // blue//
			R.string.green, // green//
			R.string.red, // red//
			R.string.yellow, // yellow//

	};

	public static int getImgID(int type) {
		int bmp = 0;
		switch (type) {
		case ApplianceType.TV: // tv
			bmp = R.drawable.m_tv;
			break;
		case ApplianceType.DVD: // dvd
			bmp = R.drawable.m_dvd;
			break;
		case ApplianceType.STB: // stb
			bmp = R.drawable.m_stb;
			break;
		case ApplianceType.PROJECTOR: // pjt
			bmp = R.drawable.m_pjt;
			break;
		case ApplianceType.FAN: // fan
			bmp = R.drawable.m_fan;
			break;
		case ApplianceType.LIGHT: // light
			bmp = R.drawable.m_light;
			break;
		case ApplianceType.CAMERA: // camera
			bmp = R.drawable.m_camera;
			break;
		case ApplianceType.AMPLIFIER: // amplify
			bmp = R.drawable.m_amplify;
			break;
		case ApplianceType.GAME: // game
			bmp = R.drawable.m_game;
			break;
		case ApplianceType.OTHER: // switch
			bmp = R.drawable.m_remotel;
			break;
		case ApplianceType.IPTV: // iptv
			bmp = R.drawable.m_tv;
			break;
		case ApplianceType.AIR_CONDITIONER: // air conditioner
			bmp = R.drawable.m_air;
			break;
		default:
			bmp = R.drawable.m_tv;
			break;
		}

		// TODO Auto-generated method stub
		return bmp;
	}

	public static int getTypeStrID(int type) {
		int str = 0;
		switch (type) {
		case ApplianceType.TV: // tv
			str = (R.string.tv);
			break;
		case ApplianceType.DVD: // dvd
			str = (R.string.dvd);
			break;
		case ApplianceType.STB: // stb
			str = (R.string.stb);
			break;
		case ApplianceType.PROJECTOR: // pjt
			str = (R.string.pjt);
			break;
		case ApplianceType.FAN: // fan
			str = (R.string.fan);
			break;
		case ApplianceType.AIR_CONDITIONER: // air
			str = (R.string.air);
			break;
		case ApplianceType.LIGHT: // light
			str = (R.string.light);
			break;
		case ApplianceType.CAMERA: // camera
			str = (R.string.camera);
			break;
		case ApplianceType.AMPLIFIER: // amplify
			str = (R.string.amplify);
			break;
		case ApplianceType.GAME: // game
			str = (R.string.game);
			break;
		case ApplianceType.OTHER: // switch
			str = (R.string.other);
			break;
		case ApplianceType.IPTV: // iptv
			str = (R.string.iptv);
			break;

		default:
			str = (R.string.tv);
			break;
		}

		// TODO Auto-generated method stub
		return str;
	}

	public final static int TypeArray[] = { ApplianceType.TV,
			ApplianceType.DVD, ApplianceType.STB, ApplianceType.FAN,
			ApplianceType.PROJECTOR, ApplianceType.AIR_CONDITIONER,
			ApplianceType.LIGHT, ApplianceType.CAMERA, ApplianceType.AMPLIFIER,
			ApplianceType.GAME, ApplianceType.OTHER };

	public static String getTypeStr(int type) {
		String str = "tv";
		switch (type) {
		case ApplianceType.TV: // tv
			str = "TV";
			break;
		case ApplianceType.DVD: // dvd
			str = "DVD";
			break;
		case ApplianceType.STB: // stb
			str = "STB";
			break;
		case ApplianceType.PROJECTOR: // pjt
			str = "PJT";
			break;
		case ApplianceType.FAN: // fan
			str = "FAN";
			break;
		case ApplianceType.AIR_CONDITIONER: // air
			str = "AIR";
			break;
		case ApplianceType.LIGHT: // light
			str = "LIGHT";
			break;
		case ApplianceType.CAMERA: // camera
			str = "CAM";
			break;
		case ApplianceType.AMPLIFIER: // amplify
			str = "AMP";
			break;
		case ApplianceType.GAME: // game
			str = "BOX";
			break;
		case ApplianceType.OTHER: // switch
			str = "SLR";
			break;
		case ApplianceType.IPTV: // iptv
			str = "IPT";
			break;

		default:
			str = "TV";
			break;
		}

		// TODO Auto-generated method stub
		return str;
	}

	public static int getDBType(int type) {
		int dbType = 0;
		switch (type) {
		case ApplianceType.TV: // tv
			dbType = 0;
			break;
		case ApplianceType.DVD: // dvd
			dbType = 1;
			break;
		case ApplianceType.STB: // stb
			dbType = 2;
			break;
		case ApplianceType.PROJECTOR: // pjt
			dbType = 4;
			break;
		case ApplianceType.FAN: // fan
			dbType = 3;
			break;
		case ApplianceType.AIR_CONDITIONER: // air
			dbType = 5;
			break;

		default:
			dbType = 0;
			break;
		}

		// TODO Auto-generated method stub
		return dbType;
	}

	public int getKeyTypeImgId(IRKey key) {
		int imgId;
		switch (key.getType()) {
		case KeyType.BASE_OVAL:

		case KeyType.BASE_OVAL_CYAN:
		case KeyType.BASE_OVAL_ORANGE:
		case KeyType.BASE_OVAL_PURPLE:

		case KeyType.BASE_ROUND:
		case KeyType.BASE_SQUARE:
		case KeyType.MEMORYKEY:
		case KeyType.MEMORYKEY_ONE:
		case KeyType.MEMORYKEY_TWO:

			imgId = R.drawable.btn_normal_selector;
			break;
		case KeyType.POWER:

			imgId = R.drawable.btn_normal_selector;
			break;

		case KeyType.ONE:

			imgId = R.drawable.main_num_1;

			break;
		case KeyType.TWO:

			imgId = R.drawable.main_num_2;

			break;
		case KeyType.THREE:

			imgId = R.drawable.main_num_3;

			break;
		case KeyType.FOUR:

			imgId = R.drawable.main_num_4;

			break;
		case KeyType.FIVE:

			imgId = R.drawable.main_num_5;

			break;
		case KeyType.SIX:

			imgId = R.drawable.main_num_6;

			break;
		case KeyType.SEVEN:

			imgId = R.drawable.main_num_7;

			break;
		case KeyType.EIGHT:

			imgId = R.drawable.main_num_8;

			break;
		case KeyType.NINE:

			imgId = R.drawable.main_num_9;

			break;
		case KeyType.ZERO:

			imgId = R.drawable.main_num_0;

			break;
		case KeyType.BACK:

			imgId = R.drawable.main_ic_back;

			break;
		case KeyType.OPEN:

			imgId = R.drawable.main_ic_eject;

			break;
		case KeyType.MENU_EXIT:

			imgId = R.drawable.main_ic_exit;

			break;
		case KeyType.FAVORITES:

			imgId = R.drawable.main_ic_favorite;

			break;
		case KeyType.FORWARD:

			imgId = R.drawable.main_ic_ff;

			break;
		case KeyType.HOME:

			imgId = R.drawable.main_ic_home;

			break;
		case KeyType.SIGNAL:

			imgId = R.drawable.main_ic_input;

			break;
		case KeyType.MUTE:

			imgId = R.drawable.main_ic_mute;

			break;
		case KeyType.NEXT:

			imgId = R.drawable.main_ic_next;

			break;
		case KeyType.PAUSE:

			imgId = R.drawable.main_ic_pause;

			break;
		case KeyType.PLAY:

			imgId = R.drawable.main_ic_play;

			break;
		case KeyType.PREVIOUS:

			imgId = R.drawable.main_ic_prev;

			break;
		case KeyType.REWIND:

			imgId = R.drawable.main_ic_quickskip;

			break;
		case KeyType.REC:

			imgId = R.drawable.main_ic_rec;

			break;
		case KeyType.SETTING:

			imgId = R.drawable.main_ic_setting;

			break;
		case KeyType.STOP:

			imgId = R.drawable.main_ic_stop;

			break;
		case KeyType.STEP_SLOW:

			imgId = R.drawable.main_ic_rew;

			break;
		case KeyType.WIND_VERTICAL:

			imgId = R.drawable.main_ic_vertical;

			break;
		case KeyType.TEN_PLUS:

			imgId = R.drawable.main_num_hyphen;

			break;
		case KeyType.REPEAT:

			imgId = R.drawable.universal_ic_repeat;

			break;
		case KeyType.INFORMATION:

			imgId = R.drawable.universal_ic_info;

			break;
		case KeyType.MENU:

			imgId = R.drawable.universal_ic_menu;

			break;
		case KeyType.SHUTTER_ONE:

			imgId = R.drawable.universal_ic_shuffle;

			break;
		case KeyType.PLAY_PAUSE:

			imgId = R.drawable.widget_noti_ic_play_pause;

			break;
		case KeyType.BASE_OVAL_BLUE:

			imgId = R.drawable.main_color_b_selector;

			break;
		case KeyType.BASE_OVAL_GREEN:

			imgId = R.drawable.main_color_g_selector;

			break;
		case KeyType.BASE_OVAL_YELLOW:

			imgId = R.drawable.main_color_r_selector;

			break;
		case KeyType.BASE_OVAL_RED:

			imgId = R.drawable.main_color_y_selector;

			break;

		default:
			imgId = R.drawable.btn_normal_selector;

			break;

		}
		return imgId;
	}

}
