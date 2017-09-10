package com.dou361.baseutils.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Debug;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * ========================================
 * <p>
 * 版 权：dou361.com 版权所有 （C） 2015
 * <p>
 * 作 者：chenguanming
 * <p>
 * 个人网站：http://www.dou361.com
 * <p>
 * 版 本：1.0
 * <p>
 * 创建日期：2017/4/19 23:22
 * <p>
 * 描 述：
 * <p>
 * <p>
 * 修订历史：
 * <p>
 * ========================================
 */
public class SystemUtils {

    private SystemUtils() {
    }

    /**
     * 获取android系统版本号
     */
    public static String getOSVersion() {
        String release = Build.VERSION.RELEASE; // android系统版本号
        release = "android" + release;
        return release;
    }

    /**
     * 获得android系统sdk版本号
     */
    public static String getOSVersionSDK() {
        return Build.VERSION.SDK;
    }

    /**
     * 获得android系统sdk版本号
     */
    public static int getOSVersionSDKINT() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 根据packageName获取packageInfo
     */
    public static PackageInfo getPackageInfo(String packageName) {
        Context context = UIUtils.getContext();
        if (null == context) {
            return null;
        }
        if (StringUtils.isEmpty(packageName)) {
            packageName = context.getPackageName();
        }
        PackageInfo info = null;
        PackageManager manager = context.getPackageManager();
        // 根据packageName获取packageInfo
        try {
            info = manager.getPackageInfo(packageName, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
        } catch (PackageManager.NameNotFoundException e) {
            LogUtils.log(e);
        }
        return info;
    }

    /**
     * 获取应用包名
     */
    public static String getPackageName() {
        return UIUtils.getContext().getPackageName();
    }

    /**
     * 获取本应用的VersionCode
     */
    public static int getVersionCode() {
        PackageInfo info = getPackageInfo(null);
        if (info != null) {
            return info.versionCode;
        } else {
            return -1;
        }
    }

    /**
     * 获取本应用的VersionName
     */
    public static String getVersionName() {
        PackageInfo info = getPackageInfo(null);
        if (info != null) {
            return info.versionName;
        } else {
            return null;
        }
    }

    /**
     * 获取手机型号
     */
    public static String getDeviceModel() {
        return Build.MODEL;
    }

    /**
     * 获取设备的IMEI
     */
    public static String getIMEI() {
        Context context = UIUtils.getContext();
        if (null == context) {
            return null;
        }
        String imei = null;
        try {
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            imei = tm.getDeviceId();
        } catch (Exception e) {
            LogUtils.log(e);
        }
        return imei;
    }

    /**
     * 检测手机是否已插入SIM卡
     */
    public static boolean isCheckSimCardAvailable() {
        Context context = UIUtils.getContext();
        if (null == context) {
            return false;
        }
        final TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSimState() == TelephonyManager.SIM_STATE_READY;
    }

    /**
     * sim卡是否可读
     */
    public static boolean isCanUseSim() {
        Context context = UIUtils.getContext();
        if (null == context) {
            return false;
        }
        try {
            TelephonyManager mgr = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            return TelephonyManager.SIM_STATE_READY == mgr.getSimState();
        } catch (Exception e) {
            LogUtils.log(e);
        }
        return false;
    }

    /**
     * 取得当前sim手机卡的imsi
     */
    public static String getIMSI() {
        Context context = UIUtils.getContext();
        if (null == context) {
            return null;
        }
        String imsi = null;
        try {
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            imsi = tm.getSubscriberId();
        } catch (Exception e) {
            LogUtils.log(e);
        }
        return imsi;
    }

    // 获取设备号
    public static String getLocalDeviceId(Activity activity) {

        TelephonyManager tm = (TelephonyManager) activity
                .getSystemService(Context.TELEPHONY_SERVICE);
        if (tm != null)
            return tm.getDeviceId();
        else
            return null;

    }

    /**
     * 返回本地手机号码，这个号码不一定能获取到
     */
    public static String getNativePhoneNumber() {
        Context context = UIUtils.getContext();
        if (null == context) {
            return null;
        }
        TelephonyManager telephonyManager;
        telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String NativePhoneNumber = null;
        NativePhoneNumber = telephonyManager.getLine1Number();
        return NativePhoneNumber;
    }

    /**
     * 返回手机服务商名字
     */
    public static String getProvidersName() {
        String ProvidersName = null;
        // 返回唯一的用户ID;就是这张卡的编号神马的
        String IMSI = getIMSI();
        // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
        if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
            ProvidersName = "中国移动";
        } else if (IMSI.startsWith("46001")) {
            ProvidersName = "中国联通";
        } else if (IMSI.startsWith("46003")) {
            ProvidersName = "中国电信";
        } else {
            ProvidersName = "其他服务商:" + IMSI;
        }
        return ProvidersName;
    }

    /**
     * 获取当前设备的SN
     */
    public static String getSimSN() {
        Context context = UIUtils.getContext();
        if (null == context) {
            return null;
        }
        String simSN = null;
        try {
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            simSN = tm.getSimSerialNumber();
        } catch (Exception e) {
            LogUtils.log(e);
        }
        return simSN;
    }

    /**
     * 尝试打开wifi
     */
    private static boolean tryOpenMAC(WifiManager manager) {
        boolean softOpenWifi = false;
        if (manager != null) {
            int state = manager.getWifiState();
            if (state != WifiManager.WIFI_STATE_ENABLED
                    && state != WifiManager.WIFI_STATE_ENABLING) {
                manager.setWifiEnabled(true);
                softOpenWifi = true;
            }
        }
        return softOpenWifi;
    }

    /**
     * 尝试关闭MAC
     */
    private static void tryCloseMAC(WifiManager manager) {
        if (manager != null) {
            manager.setWifiEnabled(false);
        }
    }

    /**
     * 获取WiFi的mac地址
     */
    private static String getMac(Context context) {
        String mac = "";
        try {
            WifiManager wm = (WifiManager) context
                    .getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wm.getConnectionInfo();
            mac = info.getMacAddress();
        } catch (Exception e) {
            LogUtils.log(e);
        }
        return mac;
    }

    /**
     * cmd命令获取mac地址
     */
    private static String callCmd(String cmd, String filter) {
        String result = "";
        String line = "";
        try {
            Process proc = Runtime.getRuntime().exec(cmd);
            InputStreamReader is = new InputStreamReader(proc.getInputStream());
            BufferedReader br = new BufferedReader(is);

            // 执行命令cmd，只取结果中含有filter的这一行
            while ((line = br.readLine()) != null
                    && line.contains(filter) == false) {
                // result += line;
            }

            result = line;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取当前设备的MAC地址 如果拿不到，则随机生成一个唯一的字符串
     */
    public static String getMacAddress(Context context) {
        if (context == null) {
            context = UIUtils.getContext();
        }
        /** 先从缓存中取 */
        String mac = (String) SPUtils.getData(context, "Mac_Address", "");
        /** 打开WiFi再获取 */
        if (mac == null || "".equals(mac)) {
            WifiManager wifiManager = (WifiManager) context
                    .getSystemService(Context.WIFI_SERVICE);
            if (wifiManager != null) {
                /** 获取失败，尝试打开wifi获取 */
                boolean wifiEnabled = wifiManager.isWifiEnabled();
                /** 如果WiFi沒有打開就打開WiFi */
                tryOpenMAC(wifiManager);
                for (int index = 0; index < 3; index++) {
                    /** 如果第一次没有成功，第二次做100毫秒的延迟。 */
                    if (index != 0) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    mac = getMac(context);
                    if (mac != null && mac.length() > 0) {
                        break;
                    }
                }

                /** 尝试关闭wifi */
                if (!wifiEnabled) {
                    tryCloseMAC(wifiManager);
                }
            }
        }
        /** cmd获取 */
        if (mac == null || "".equals(mac)) {
            String result = callCmd("busybox ifconfig", "Wlan");
            if (result != null && result.length() > 0
                    && result.contains("HWaddr")) {
                mac = result.substring(result.indexOf("HWaddr") + 6,
                        result.length() - 1);
                if (mac.length() > 1) {
                    mac = mac.toLowerCase();
                }
            }
        }
        /** 以上两种方式拿不到，随机生成 */
        if (mac == null || "".equals(mac)) {
            mac = (UUID.randomUUID() + "").toLowerCase();
        }
        SPUtils.putData(context, "Mac_Address", mac.trim());
        return mac.trim();
    }


    /**
     * 获得设备ip地址
     */
    public static String getLocalAddress() {
        try {
            Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface intf = en.nextElement();
                Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                while (enumIpAddr.hasMoreElements()) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            LogUtils.log(e);
        }
        return null;
    }

    /**
     * 获取设备信息
     */
    public static String[] getDivceInfo() {
        String str1 = "/proc/cpuinfo";
        String str2 = "";
        String[] cpuInfo = {"", ""};
        String[] arrayOfString;
        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            for (int i = 2; i < arrayOfString.length; i++) {
                cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
            }
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            cpuInfo[1] += arrayOfString[2];
            localBufferedReader.close();
        } catch (IOException e) {
            LogUtils.log(e);
        }
        return cpuInfo;
    }

    /**
     * 判断手机CPU是否支持NEON指令集
     */
    public static boolean isNEON() {
        boolean isNEON = false;
        String cupinfo = getCPUInfos();
        if (cupinfo != null) {
            cupinfo = cupinfo.toLowerCase();
            isNEON = cupinfo != null && cupinfo.contains("neon");
        }
        return isNEON;
    }

    /**
     * 读取CPU信息文件，获取CPU信息
     */
    @SuppressWarnings("resource")
    private static String getCPUInfos() {
        String str1 = "/proc/cpuinfo";
        String str2 = "";
        StringBuilder resusl = new StringBuilder();
        String resualStr = null;
        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            while ((str2 = localBufferedReader.readLine()) != null) {
                resusl.append(str2);
                // String cup = str2;
            }
            if (resusl != null) {
                resualStr = resusl.toString();
                return resualStr;
            }
        } catch (IOException e) {
            LogUtils.log(e);
        }
        return resualStr;
    }

    /**
     * 获取当前设备cpu的型号
     */
    public static int getCPUModel() {
        return matchABI(getSystemProperty("ro.product.cpu.abi"))
                | matchABI(getSystemProperty("ro.product.cpu.abi2"));
    }

    /**
     * 匹配当前设备的cpu型号
     */
    private static int matchABI(String abiString) {
        if (TextUtils.isEmpty(abiString)) {
            return 0;
        }
        if ("armeabi".equals(abiString)) {
            return 1;
        } else if ("armeabi-v7a".equals(abiString)) {
            return 2;
        } else if ("x86".equals(abiString)) {
            return 4;
        } else if ("mips".equals(abiString)) {
            return 8;
        }
        return 0;
    }

    /**
     * 获取CPU核心数
     */
    public static int getCpuCount() {
        return Runtime.getRuntime().availableProcessors();
    }

    /**
     * 获取Rom版本
     */
    public static String getRomversion() {
        String rom = "";
        try {
            String modversion = getSystemProperty("ro.modversion");
            String displayId = getSystemProperty("ro.build.display.id");
            if (modversion != null && !modversion.equals("")) {
                rom = modversion;
            }
            if (displayId != null && !displayId.equals("")) {
                rom = displayId;
            }
        } catch (Exception e) {
            LogUtils.log(e);
        }
        return rom;
    }

    /**
     * 获取系统配置参数
     */
    public static String getSystemProperty(String key) {
        String pValue = null;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method m = c.getMethod("get", String.class);
            pValue = m.invoke(null, key).toString();
        } catch (Exception e) {
            LogUtils.log(e);
        }
        return pValue;
    }

    /**
     * 获取系统中的Library包
     */
    public static List<String> getSystemLibs() {
        Context context = UIUtils.getContext();
        if (null == context) {
            return null;
        }
        PackageManager pm = context.getPackageManager();
        String[] libNames = pm.getSystemSharedLibraryNames();
        List<String> listLibNames = Arrays.asList(libNames);
        LogUtils.log("SystemLibs: " + listLibNames);
        return listLibNames;
    }

    /**
     * 获取手机外部可用空间大小，单位为byte
     */
    @SuppressWarnings("deprecation")
    public static long getExternalTotalSpace() {
        long totalSpace = -1L;
        if (FileUtils.isSDCardAvailable()) {
            try {
                String path = Environment.getExternalStorageDirectory()
                        .getPath();// 获取外部存储目录即 SDCard
                StatFs stat = new StatFs(path);
                long blockSize = stat.getBlockSize();
                long totalBlocks = stat.getBlockCount();
                totalSpace = totalBlocks * blockSize;
            } catch (Exception e) {
                LogUtils.log(e);
            }
        }
        return totalSpace;
    }

    /**
     * 获取外部存储可用空间，单位为byte
     */
    @SuppressWarnings("deprecation")
    public static long getExternalSpace() {
        long availableSpace = -1L;
        if (FileUtils.isSDCardAvailable()) {
            try {
                String path = Environment.getExternalStorageDirectory()
                        .getPath();
                StatFs stat = new StatFs(path);
                availableSpace = stat.getAvailableBlocks()
                        * (long) stat.getBlockSize();
            } catch (Exception e) {
                LogUtils.log(e);
            }
        }
        return availableSpace;
    }

    /**
     * 获取手机内部空间大小，单位为byte
     */
    @SuppressWarnings("deprecation")
    public static long getTotalInternalSpace() {
        long totalSpace = -1L;
        try {
            String path = Environment.getDataDirectory().getPath();
            StatFs stat = new StatFs(path);
            long blockSize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();// 获取该区域可用的文件系统数
            totalSpace = totalBlocks * blockSize;
        } catch (Exception e) {
            LogUtils.log(e);
        }
        return totalSpace;
    }

    /**
     * 获取手机内部可用空间大小，单位为byte
     */
    @SuppressWarnings("deprecation")
    public static long getAvailableInternalMemorySize() {
        long availableSpace = -1l;
        try {
            String path = Environment.getDataDirectory().getPath();// 获取 Android
            // 数据目录
            StatFs stat = new StatFs(path);// 一个模拟linux的df命令的一个类,获得SD卡和手机内存的使用情况
            long blockSize = stat.getBlockSize();// 返回 Int ，大小，以字节为单位，一个文件系统
            long availableBlocks = stat.getAvailableBlocks();// 返回 Int
            // ，获取当前可用的存储空间
            availableSpace = availableBlocks * blockSize;
        } catch (Exception e) {
            LogUtils.log(e);
        }
        return availableSpace;
    }

    /**
     * 获取单个应用最大分配内存，单位为byte
     */
    public static long getOneAppMaxMemory() {
        Context context = UIUtils.getContext();
        if (context == null) {
            return -1;
        }
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        return activityManager.getMemoryClass() * 1024 * 1024;
    }

    /**
     * 获取指定本应用占用的内存，单位为byte
     */
    public static long getUsedMemory() {
        return getUsedMemory(null);
    }

    /**
     * 获取指定包名应用占用的内存，单位为byte
     */
    public static long getUsedMemory(String packageName) {
        Context context = UIUtils.getContext();
        if (context == null) {
            return -1;
        }
        if (StringUtils.isEmpty(packageName)) {
            packageName = context.getPackageName();
        }
        long size = 0;
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runapps = activityManager
                .getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo runapp : runapps) { // 遍历运行中的程序
            if (packageName.equals(runapp.processName)) {// 得到程序进程名，进程名一般就是包名，但有些程序的进程名并不对应一个包名
                // 返回指定PID程序的内存信息，可以传递多个PID，返回的也是数组型的信息
                Debug.MemoryInfo[] processMemoryInfo = activityManager
                        .getProcessMemoryInfo(new int[]{runapp.pid});
                // 得到内存信息中已使用的内存，单位是K
                size = processMemoryInfo[0].getTotalPrivateDirty() * 1024;
            }
        }
        return size;
    }

    /**
     * 获取手机剩余内存，单位为byte
     */
    public static long getAvailableMemory() {
        Context context = UIUtils.getContext();
        if (context == null) {
            return -1;
        }
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);
        return info.availMem;
    }

    /**
     * 获取手机总内存，单位为byte
     */
    public static long getTotalMemory() {
        long size = 0;
        String path = "/proc/meminfo";// 系统内存信息文件
        try {
            String totalMemory = FileUtils.readProperties(path, "MemTotal",
                    null);// 读出来是带单位kb的，并且单位前有空格，所以去掉最后三位
            if (!StringUtils.isEmpty(totalMemory) && totalMemory.length() > 3) {
                size = Long.valueOf(totalMemory.substring(0,
                        totalMemory.length() - 3)) * 1024;
            }
        } catch (Exception e) {
            LogUtils.log(e);
        }
        return size;
    }

    /**
     * 手机低内存运行阀值，单位为byte
     */
    public static long getThresholdMemory() {
        Context context = UIUtils.getContext();
        if (context == null) {
            return -1;
        }
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);
        return info.threshold;
    }

    /**
     * 手机是否处于低内存运行
     */
    public static boolean isLowMemory() {
        Context context = UIUtils.getContext();
        if (context == null) {
            return false;
        }
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);
        return info.lowMemory;
    }

    /**
     * 安装apk应用
     */
    public static void installApk(String strApkPath) {
        File apkfile = new File(strApkPath);
        if (!apkfile.exists()) {
            return;
        }
        Intent mIntent = new Intent(Intent.ACTION_VIEW);
        mIntent.setDataAndType(Uri.parse("file://" + apkfile.toString()),
                "application/vnd.android.package-archive");
        UIUtils.getContext().startActivity(mIntent);

    }

    /**
     * 判断是否是第三方软件
     */
    public static boolean isThirdPartyApp(String packageName) {
        Context context = UIUtils.getContext();
        if (null == context) {
            return false;
        }
        PackageManager pm = context.getPackageManager();
        PackageInfo packageInfo;
        try {
            packageInfo = pm.getPackageInfo(packageName, 0);
            return isThirdPartyApp(packageInfo);
        } catch (PackageManager.NameNotFoundException e) {
            LogUtils.log(e);
            return false;
        }
    }

    /**
     * 用来判断服务是否运行.
     *
     * @param mContext
     * @param className 判断的服务名字
     * @return true 在运行 false 不在运行
     */
    public static boolean isServiceRunning(Context mContext, String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) mContext
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager
                .getRunningServices(30);
        if (!(serviceList.size() > 0)) {
            return false;
        }
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(className) == true) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }

    /**
     * 判断是否是第三方软件
     */
    public static boolean isThirdPartyApp(PackageInfo packageInfo) {
        if (null == packageInfo || null == packageInfo.applicationInfo) {
            return false;
        }
        return ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0)
                || ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0);
    }

    /**
     * 读取指定路径下APK文件签名
     */
    @SuppressWarnings({"unchecked", "resource"})
    public static String getJarSignature(String filePath) throws Exception {
        if (null == filePath) {
            return null;
        }
        String resultSign = "";
        String resultKey = "";
        List<ZipEntry> names = new ArrayList<ZipEntry>();
        ZipFile zf = new ZipFile(filePath);
        Enumeration<ZipEntry> zi = (Enumeration<ZipEntry>) zf.entries();
        while (zi.hasMoreElements()) {
            ZipEntry ze = zi.nextElement();
            String name = ze.getName();
            if (name.startsWith("META-INF/")
                    && (name.endsWith(".RSA") || name.endsWith(".DSA"))) {
                names.add(ze);
            }
        }
        Collections.sort(names, new Comparator<ZipEntry>() {
            @Override
            public int compare(ZipEntry obj1, ZipEntry obj2) {
                if (obj1 != null && obj2 != null) {
                    return obj1.getName().compareToIgnoreCase(obj2.getName());
                }
                return 0;
            }
        });
        for (ZipEntry ze : names) {
            InputStream is = zf.getInputStream(ze);
            try {
                CertificateFactory cf = CertificateFactory.getInstance("X.509");
                CertPath cp = cf.generateCertPath(is, "PKCS7");
                List<?> list = cp.getCertificates();
                for (Object obj : list) {
                    if (!(obj instanceof X509Certificate))
                        continue;
                    X509Certificate cert = (X509Certificate) obj;
                    StringBuilder builder = new StringBuilder();
                    builder.setLength(0);
                    byte[] key = getPKBytes(cert.getPublicKey());
                    for (byte aKey : key) {
                        builder.append(String.format("%02X", aKey));
                    }
                    resultKey += builder.toString();
                    builder.setLength(0);
                    byte[] signature = cert.getSignature();

                    for (byte aSignature : signature) {
                        builder.append(String.format("%02X", aSignature));
                    }
                    resultSign += builder.toString();
                }
            } catch (CertificateException e) {
                LogUtils.log(e);
            }
            is.close();
        }
        if (!TextUtils.isEmpty(resultKey) && !TextUtils.isEmpty(resultSign)) {
            return hashCode(resultKey) + "," + hashCode(resultSign);
        }
        return null;
    }

    /**
     * 根据公钥获取key
     */
    private static byte[] getPKBytes(PublicKey pk) {
        if (pk instanceof RSAPublicKey) {
            RSAPublicKey k = (RSAPublicKey) pk;
            return k.getModulus().toByteArray();
        } else if (pk instanceof DSAPublicKey) {
            DSAPublicKey k = (DSAPublicKey) pk;
            return k.getY().toByteArray();
        }
        return null;
    }

    /**
     * 计算签名时的hashcode算法
     */
    public static int hashCode(String str) {
        int hash = 0;
        if (str != null) {
            int multiplier = 1;
            int offset = 0;
            int count = str.length();
            char[] value = new char[count];
            str.getChars(offset, count, value, 0);
            for (int i = offset + count - 1; i >= offset; i--) {
                hash += value[i] * multiplier;
                int shifted = multiplier << 5;
                multiplier = shifted - multiplier;
            }
        }
        return hash;
    }

    /**
     * 获取指定路径的apk的资源
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Resources getAPKResources(String apkPath) throws Exception {
        Context context = UIUtils.getContext();
        if (null == context) {
            return null;
        }
        String PathAssetManager = "android.content.res.AssetManager";
        Class assetMagCls = Class.forName(PathAssetManager);
        Constructor assetMagCt = assetMagCls.getConstructor((Class[]) null);
        Object assetMag = assetMagCt.newInstance((Object[]) null);
        Class[] typeArgs = new Class[1];
        typeArgs[0] = String.class;
        Method assetMagAddAssetPathMtd = assetMagCls.getDeclaredMethod(
                "addAssetPath", typeArgs);
        Object[] valueArgs = new Object[1];
        valueArgs[0] = apkPath;
        assetMagAddAssetPathMtd.invoke(assetMag, valueArgs);
        Resources res = context.getResources();
        typeArgs = new Class[3];
        typeArgs[0] = assetMag.getClass();
        typeArgs[1] = res.getDisplayMetrics().getClass();
        typeArgs[2] = res.getConfiguration().getClass();
        Constructor resCt = Resources.class.getConstructor(typeArgs);
        valueArgs = new Object[3];
        valueArgs[0] = assetMag;
        valueArgs[1] = res.getDisplayMetrics();
        valueArgs[2] = res.getConfiguration();
        res = (Resources) resCt.newInstance(valueArgs);
        return res;
    }

}
