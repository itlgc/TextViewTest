package org.it.textviewdemo;

import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    private TextView tvPhoto;
    private TextView tvIcon;
    private TextView tvContent;
    private TextView tvRich;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvIcon = (TextView) findViewById(R.id.tv_icon);
        tvPhoto = (TextView) findViewById(R.id.tv_photo);
        tvContent = (TextView) findViewById(R.id.tv_content);
        tvRich = (TextView) findViewById(R.id.tv_rich);
        mTextView = (TextView) findViewById(R.id.tv_rich2);

        setCompoundDrawables();
        insertPhoto();
        setPartClick();
        setSpanText();
        setSpanText2();
    }

    private void setSpanText2() {
        //创建一个 SpannableString对象
        SpannableString msp = new SpannableString("字体测试字体大小一半两倍前景色背景色正常粗体斜体粗斜体下划线删除线x1x2电话邮件网站短信彩信地图X轴综合/bot");

        //设置字体(default,default-bold,monospace,serif,sans-serif)
        msp.setSpan(new TypefaceSpan("monospace"), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        msp.setSpan(new TypefaceSpan("serif"), 2, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //设置字体大小（绝对值,单位：像素）
        msp.setSpan(new AbsoluteSizeSpan(20), 4, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //第二个参数boolean dip，如果为true，表示前面的字体大小单位为dip，否则为像素，同上。
        msp.setSpan(new AbsoluteSizeSpan(20,true), 6, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //设置字体大小（相对值,单位：像素） 参数表示为默认字体大小的多少倍
        msp.setSpan(new RelativeSizeSpan(0.5f), 8, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //0.5f表示默认字体大小的一半
        msp.setSpan(new RelativeSizeSpan(2.0f), 10, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //2.0f表示默认字体大小的两倍

        //设置字体前景色
        msp.setSpan(new ForegroundColorSpan(Color.MAGENTA), 12, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //设置前景色为洋红色

        //设置字体背景色
        msp.setSpan(new BackgroundColorSpan(Color.CYAN), 15, 18, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //设置背景色为青色

        //设置字体样式正常，粗体，斜体，粗斜体
        msp.setSpan(new StyleSpan(android.graphics.Typeface.NORMAL), 18, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //正常
        msp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 20, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //粗体
        msp.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC), 22, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //斜体
        msp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD_ITALIC), 24, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //粗斜体

        //设置下划线
        msp.setSpan(new UnderlineSpan(), 27, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //设置删除线
        msp.setSpan(new StrikethroughSpan(), 30, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //设置上下标
        msp.setSpan(new SubscriptSpan(), 34, 35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     //下标
        msp.setSpan(new SuperscriptSpan(), 36, 37, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);   //上标

        //超级链接（需要添加setMovementMethod方法附加响应）
        msp.setSpan(new URLSpan("tel:4155551212"), 37, 39, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     //电话
        msp.setSpan(new URLSpan("mailto:webmaster@google.com"), 39, 41, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     //邮件
        msp.setSpan(new URLSpan("http://www.baidu.com"), 41, 43, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     //网络
        msp.setSpan(new URLSpan("sms:4155551212"), 43, 45, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     //短信   使用sms:或者smsto:
        msp.setSpan(new URLSpan("mms:4155551212"), 45, 47, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     //彩信   使用mms:或者mmsto:
        msp.setSpan(new URLSpan("geo:38.899533,-77.036476"), 47, 49, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     //地图

        //设置字体大小（相对值,单位：像素） 参数表示为默认字体宽度的多少倍
        msp.setSpan(new ScaleXSpan(2.0f), 49, 51, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //2.0f表示默认字体宽度的两倍，即X轴方向放大为默认字体的两倍，而高度不变
        //设置项目符号
        msp.setSpan(new BulletSpan(android.text.style.BulletSpan.STANDARD_GAP_WIDTH,Color.GREEN), 0 ,msp.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //第一个参数表示项目符号占用的宽度，第二个参数为项目符号的颜色

        //设置图片
        Drawable drawable = getResources().getDrawable(R.drawable.house);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        msp.setSpan(new ImageSpan(drawable), 53, 57, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        mTextView.setText(msp);
        mTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }


    private void setSpanText() {
        SpannableString span = new SpannableString("红色打电话斜体删除线绿色下划线图片.");
        //设置背景色   flag: Spanned.SPAN_EXCLUSIVE_EXCLUSIVE  前后都不包括
        span.setSpan(new BackgroundColorSpan(Color.RED),0,2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //用超链接标记文本
        span.setSpan(new URLSpan("tel:1234567890"),2,5,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //用样式标记文本(斜体)
        span.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),5,7,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //用删除线标记文本
        span.setSpan(new StrikethroughSpan(),7,10,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //用下滑线标记文本
        span.setSpan(new UnderlineSpan(),10,16,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //用前景色颜色标记
        span.setSpan(new ForegroundColorSpan(Color.GREEN),10,13,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvRich.setText(span);

    }

    private void setPartClick() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append("好友").append(i).append(",");
        }
        //取得要改变样式的String
        String likeUsers = sb.substring(0, sb.lastIndexOf(","));
        //建立超链接
        tvContent.setMovementMethod(LinkMovementMethod.getInstance());
        tvContent.setText(addClickPart(likeUsers), TextView.BufferType.SPANNABLE);
    }

    private SpannableStringBuilder addClickPart(String str) {
        //添加最开始的图形
        ImageSpan imgspan = new ImageSpan(this, R.drawable.house);
        SpannableString spanStr = new SpannableString("p.");
        spanStr.setSpan(imgspan,0,1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        //连接多个"好友"字符串
        SpannableStringBuilder ssb = new SpannableStringBuilder(spanStr);
        ssb.append(str);
        String[] likeUsers = str.split(",");
        if (likeUsers.length>0) {
            for (final String name : likeUsers) {
                int start = str.indexOf(name) + spanStr.length();
                ssb.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        //删除下滑线,设置字体颜色为蓝色
                        ds.setColor(Color.BLUE);
                        ds.setUnderlineText(false);
                    }
                }, start, start + name.length(), 0);
            }
        }
        return ssb.append("等").append((char) likeUsers.length).append("个人觉得很赞");
    }

    private void setCompoundDrawables() {
        Drawable[] drawables = tvIcon.getCompoundDrawables();
        drawables[1].setBounds(0,0,100,100);
        tvIcon.setCompoundDrawables(drawables[0],drawables[1],drawables[2],drawables[3]);
    }

    /**Html中src标签,插入图片*/
    private void insertPhoto() {
        String str = "图片:<img src='house'/>";

        //当解析到<img>标签时就会回调getDrawable()方法，并需要返回一个Drawable对象；
        // 当前我们需要定义类并实现ImageGetter接口以及在getDrawable方法中做相应的处理
        tvPhoto.setText(Html.fromHtml(str, new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                Drawable draw =null;
                try {
                    //利用反射
                    Field field = R.drawable.class.getField(source);
                    int resourceId = Integer.parseInt(field.get(null).toString());
                    draw = getResources().getDrawable(resourceId);
                    draw.setBounds(0,0,draw.getIntrinsicWidth(),draw.getIntrinsicHeight());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return draw;
            }
        },null));
    }
}
