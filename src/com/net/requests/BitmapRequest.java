/**
 *
 *	created by Mr.Simple, Dec 20, 20142:22:41 PM.
 *	Copyright (c) 2014, hehonghui@umeng.com All Rights Reserved.
 *
 *                #####################################################
 *                #                                                   #
 *                #                       _oo0oo_                     #   
 *                #                      o8888888o                    #
 *                #                      88" . "88                    #
 *                #                      (| -_- |)                    #
 *                #                      0\  =  /0                    #   
 *                #                    ___/`---'\___                  #
 *                #                  .' \\|     |# '.                 #
 *                #                 / \\|||  :  |||# \                #
 *                #                / _||||| -:- |||||- \              #
 *                #               |   | \\\  -  #/ |   |              #
 *                #               | \_|  ''\---/''  |_/ |             #
 *                #               \  .-\__  '-'  ___/-. /             #
 *                #             ___'. .'  /--.--\  `. .'___           #
 *                #          ."" '<  `.___\_<|>_/___.' >' "".         #
 *                #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 *                #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 *                #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 *                #                       `=---='                     #
 *                #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 *                #                                                   #
 *                #               佛祖保佑         永无BUG              #
 *                #                                                   #
 *                #####################################################
 */

package com.net.requests;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.net.base.Request;
import com.net.base.Response;

/**
 * @author mrsimple
 */
public class BitmapRequest extends Request<Bitmap> {

    public BitmapRequest(HttpMethod method, String url, RequestListener<Bitmap> listener) {
        super(method, url, listener);
    }

    @Override
    public Bitmap parseResponse(Response response) {
        return BitmapFactory.decodeByteArray(response.getRawData(), 0,
                response.getRawData().length);
    }

    @Override
    public int compareTo(Request<Bitmap> another) {
        Priority myPriority = this.getPriority();
        Priority anotherPriority = another.getPriority();
        // 注意Bitmap请求要先执行最晚加入队列的请求,ImageLoader的策略
        return myPriority.equals(another) ? another.getSerialNumber() - this.getSerialNumber()
                : myPriority.ordinal() - anotherPriority.ordinal();
    }

}