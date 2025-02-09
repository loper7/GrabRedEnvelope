package com.carlos.grabredenvelope

import android.app.ActivityManager
import android.content.Context
import android.os.Process
import cn.jpush.android.api.JPushInterface
import com.carlos.cutils.util.LogUtils
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure

/**
 *                             _ooOoo_
 *                            o8888888o
 *                            88" . "88
 *                            (| -_- |)
 *                            O\  =  /O
 *                         ____/`---'\____
 *                       .'  \\|     |//  `.
 *                      /  \\|||  :  |||//  \
 *                     /  _||||| -:- |||||-  \
 *                     |   | \\\  -  /// |   |
 *                     | \_|  ''\---/''  |   |
 *                     \  .-\__  `-`  ___/-. /
 *                   ___`. .'  /--.--\  `. . __
 *                ."" '<  `.___\_<|>_/___.'  >'"".
 *               | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *               \  \ `-.   \_ __\ /__ _/   .-` /  /
 *          ======`-.____`-.___\_____/___.-`____.-'======
 *                             `=---='
 *          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 *                     佛祖保佑        永无BUG
 *            佛曰:
 *                   写字楼里写字间，写字间里程序员；
 *                   程序人员写程序，又拿程序换酒钱。
 *                   酒醒只在网上坐，酒醉还来网下眠；
 *                   酒醉酒醒日复日，网上网下年复年。
 *                   但愿老死电脑间，不愿鞠躬老板前；
 *                   奔驰宝马贵者趣，公交自行程序员。
 *                   别人笑我忒疯癫，我笑自己命太贱；
 *                   不见满街漂亮妹，哪个归得程序员？
 */

/**
 * Created by Carlos on 2019/2/20.
 */
class AppInit {

    private var context: Context = MyApplication.instance.applicationContext

    init {

        if ("com.carlos.grabredenvelope:pushcore" == getCurrentProcessName()) {
            initJpush()
        }

        initUmeng()
    }

    private fun initJpush() {
        JPushInterface.setDebugMode(BuildConfig.DEBUG)
        JPushInterface.init(context)
        val id = JPushInterface.getRegistrationID(context)
        LogUtils.d("id:" + id)
    }

    private fun initUmeng() {
        UMConfigure.setLogEnabled(BuildConfig.DEBUG)
        UMConfigure.init(
            context,
            BuildConfig.UMENG_APPKEY_DEV,
            BuildConfig.VERSION_NAME,
            UMConfigure.DEVICE_TYPE_PHONE,
            null
        )
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO)
    }

    /**
     * 获取当前进程名
     */
    private fun getCurrentProcessName(): String? {
        val pid = Process.myPid()
        var processName = ""
        val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (process in manager.runningAppProcesses) {
            if (process.pid == pid) {
                processName = process.processName
            }
        }
        return processName
    }

}