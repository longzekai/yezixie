package com.dou361.baseutils.listener;

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
 * 创建日期：2017/4/19 23:17
 * <p>
 * 描 述：双击退出
 * <p>
 * <p>
 * 修订历史：
 * <p>
 * ========================================
 */
public interface OnDoubleClickListener {

    /**
     * 多击事件的监听
     */
    void onFinishClick();

    /**
     * 没完成点击前触发的点击
     */
    void onContinueClick();
}