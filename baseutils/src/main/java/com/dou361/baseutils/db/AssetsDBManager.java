package com.dou361.baseutils.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;

import com.dou361.baseutils.utils.LogUtils;
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
 * 创建日期：2015/11/11
 * <p>
 * 描 述：放在资产目录下的数据库，复制到sd卡中
 * <p>
 * <p>
 * 修订历史：
 * <p>
 * ========================================
 */
public class AssetsDBManager {

	private Context context;

	public AssetsDBManager(Context context) {
		this.context = context;
	}

	/**
	 * savePath数据库要复制到的位置如：data/data/+包名 dbName
	 * Assets文件夹下的数据库文件名称如：sqldata/DianJiaoShi.db
	 */
	public void copyDatabase(String savePath,String copyPath,String dbName) {
		File path = new File(savePath);
		/** 不存在先创建文件夹 */
		if (!path.exists()) {
			path.mkdirs();
		}
		File filepath = new File(savePath+"/"+dbName);
		/** 查看数据库文件是否存在 */
		if (filepath.exists()) {
			/** 存在则删除 */
			filepath.delete();
		}
		try {
			/** 得到资源 */
			AssetManager am = context.getAssets();
			/** 得到数据库的输入流 */
			InputStream is = am.open(copyPath+"/"+dbName);
			/** 用输出流写到指定位置上面 */
			FileOutputStream fos = new FileOutputStream(filepath);
			/** 创建byte数组 用于1KB写一次 */
			byte[] buffer = new byte[1024];
			int count = 0;
			while ((count = is.read(buffer)) > 0) {
				fos.write(buffer, 0, count);
			}
			fos.flush();
			fos.close();
			is.close();
		} catch (IOException e) {
			LogUtils.log(e);
		}
	}
}
