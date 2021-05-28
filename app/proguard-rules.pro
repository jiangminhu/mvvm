#-applymapping mapping.txt
#hotfix
-keep class com.taobao.sophix.**{*;}
-keep class com.ta.utdid2.device.**{*;}
-dontwarn com.alibaba.sdk.android.utils.**
-keepclassmembers class com.example.weightdemo.MyApplication {
    public <init>();
}

#java bean 混淆
-keep class com.example.weightdemo.bean* {*;}