
![](http://upload-images.jianshu.io/upload_images/2022038-6a900d93d8acb091.jpg)

# Novate
   a  safety client by Https for Android,  (Android网路库，基于Retrofit和RxJava打造的链式网络库, 支持okhttp的调用风格，又兼容Retrofit注解方式，并支持rxJava链式操作。方便扩展，并能实现高速加载)
  
   
# Summary


- Join based API, reduce API redundancy
- Offline caching
- Support a variety of ways to access the network (a get, put, post, delete)
- Support file download and upload
- Unified support request header to join
- The unity of the support to return the result
- Support custom extensions API
- Support the unified request access to the network flow control
 


#dependencies

**Eclipse:**

    Download the laster JAR:( com.tamic.novate:novate:-1.x.aar)
    
    copy to libs dirPath!

**AS Gradle**:
   
- root：
     
       
         repositories {
            maven { url "https://jitpack.io" }
            jcenter()
        }
    
- app:
     
```
      
          dependencies {
             compile 'com.tamic.novate:novate:1.5.0'
          }
```


        
Snapshots of the development version are available in Sonatype's snapshots repository.

Retrofit requires at minimum Java 7 or Android 2.3.

Laster vension: https://bintray.com/neglectedbyboss/maven/Novate  最新版本点击去查询

**加入权限**


```
<uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    
```
    
**混淆**   
 
     -keep class com.tamic.novate.** {*;}

--------------------------


# 中文文档

  基于Retrofit和RxJava封装的链式网络库, 支持okhttp的调用分格，又兼容Retrofit注入方式，并支持RxJava调用的链式操作，
  不仅支持开发者自己扩展，还沿用Okhttp的高效的网络加载！最重要的novate自带的异常驱动机制，帮开发者解决了绝大部分的异常
  错误处理，减少API或者业务代码出错的导致崩溃概率。
  
  为何起名 Novate？ 
  
  Novate 的英文原意是用新事物代替
  目的是用新的东西来代替Retrofit的有些不易操作的地方，因此起名新奇的东西，所以结合了原来的Http用法习惯，又加入了Retrofit的特性，因此起名 ：Novate，LOGO也是加速的意思，本框架提供了一种封装架构思路，如果不喜欢本设计思路的朋友可以直接拿源码修改扩展。

功能
----
  - 优化设计：加入基础API，减少Api冗余
 - 强大的缓存模式： 支持离线缓存， 无网络智能加载缓存，可配置是否需要缓存
 - cookie管理：自带cookie管理机制
 - 全方位请求模式：支持多种方式访问网络（get,put, post ,delete）
 - 轻送调用：支持表单,图文一起，json上传。
 - 文件传输：支持文件下载和上传，支持进度
 - 动态添加：支持请求头和参数统一添加，分别添加。
 - 结果处理：支持对返回结果的统一处理
 - 扩展性强：支持自定义的扩展API，默认Api无法满足时可自定义自己的Service
 - 悠雅方便：支持统一请求访问网络的流程控制，以方便帮你完美加入Processbar进度。
 - RxJava结合： 结合RxJava，线程智能控制

   请求网络无需关心是否在主线程和非UI线程，操作UI直接可在回调处理, 保留了HttpClient的编码习惯，又加入了Builder模式编程！
   
用法
----

        Novate novate = new Novate.Builder(this)
                .baseUrl(baseUrl)
                .build();
  
  
# 更多API

```
         novate = new Novate.Builder(this)
                .addHeader(headers) //添加公共请求头
                .addParameters(parameters)//公共参数
                .connectTimeout(10)  //连接时间 可以忽略
                .addCookie(false)  //是否同步cooike 默认不同步
                .addCache(true)  //是否缓存 默认缓存
                .addCache(cache, cacheTime)   //自定义缓存
                .baseUrl("Url") //base URL
                .addLog(true) //是否开启log
                .cookieManager(new NovateCookieManager()) // 自定义cooike，可以忽略
                .addInterceptor() // 自定义Interceptor
                .addNetworkInterceptor() // 自定义NetworkInterceptor
                .proxy(proxy) //代理
                .client(client)  //clent 默认不需要
                .build(); 
                
   ```
   
#  RxApi 
   
RxGet为例子：多种方式供你选择,通过不同返回需求，选择不同方式。

**String**
```
novate.rxGet("path or url", parameters, new RxStringCallback() {


 });
 ```
**Bean**


```

novate.rxGet("path or url", parameters, new RxResultCallback<ResultModel>() {



   });
   
   ```
**List**

```
novate.rxGet("path or url", parameters, new RxListCallback<List<ResultModel>>() {


      ....

   });
   
```
   
**File**

```

novate.rxGet("path or url", null, new RxFileCallBack(filePath, "name.jpg") {



   });
   
   ```
   
其他方式RxApi同RxGet的用法，有RxPost, RxPut,RxDelete,RxBody， RxJson, RxUpload等 
  
# GET

```
        
        novate.executeGet("pathUrl", parameters（k-v）, new Novate.ResponseCallBack<NovateResponse<MyModel>>() {
        
            .....
        
        });
        
 ```       
# POST        
        
  ```     
        novate.executePost("pathUrl", parameters（k-v）, new Novate.ResponseCallBack<NovateResponse<MyModel>>() {
        
           .............
        
        });
        
```
        
# BODY


```
     novate.body(url, Object, new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {

            }
        });
```

# FORM

```

        novate.form(url, new HashMap<K-V>(), new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {

            }
        });
        
 ```       

# JSON #
        
        
          novate.json(url, jsonString, new BaseSubscriber<ResponseBody>() {
            @Override
            public void onError(Throwable e) {
                
            }

            @Override
            public void onNext(ResponseBody responseBody) {

            }
        });        
        
# UpLoad

 **upLoadImage**

     RequestBody requestFile =
                    RequestBody.create(MediaType.parse("image/jpg"), new File(you file path));

      novate.upload(url, requestFile, new BaseSubscriber<ResponseBody>{
      
        '''''''''''''''
      });
      
      
   带进度：
   
   
       RequestBody requestFile = Utils.createFile(str);

        NovateRequestBody novateRequestBody = Utils.createNovateRequestBody(requestFile, new UpLoadCallback() {
            @Override
            public void onProgress(Object tag, int progress, long speed, boolean done) {

            }
        });

        novate.upload(url, novateRequestBody, new BaseSubscriber<ResponseBody>() {
           '''''''''''''''
           
           
        });
      
      
      
 **upLoadFile**  
 
     ```
         File file = new File(path);

        // 创建 RequestBody，用于封装 请求RequestBody
        RequestBody requestFile = Utils.createFile(file);
         // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);

       String descriptionString = "hello, 这是文件描述";
        RequestBody description = Utils.createPartFromString(descriptionString);
        
        novate.uploadFlie(url, description,  body,new BaseSubscriber<ResponseBody>() {
        
        .......
         });
        
        ```
        
    
**upLoadFiles**  
       
      
     
        Map<String, RequestBody> fileMaps = new HashMap<>();
        maps.put("key1", requestFile1);
        maps.put("key2", requestFile2);
        
        novate.uploadFlies(url, fileMaps, new BaseSubscriber<ResponseBody>(Context) {
           ......
        } );
        
        
   带进度：
      
        File file = new File(path);
      
        RequestBody requestFile =  Utils.createFile(file);

        Map<String, RequestBody> maps = new HashMap<>();
        
        maps.put("file1", Utils.createNovateRequestBody(requestFile, callback));
        maps.put("file2", Utils.createNovateRequestBody(requestFile, callback));

        novate.uploadFlies(url, maps, new BaseSubscriber<ResponseBody>(ExempleActivity.this) {
            ......
        } );


  
# DownLoad   
     

**downLoad for MaxFile**


   
      novate.download(downUrl, new DownLoadCallBack() {
      
         ''''''''''''
      });


**downLoad for minFile**   


     novate.downloadMin(downUrl, new DownLoadCallBack() {
      
         ''''''''''''
      });
      
 
 
   
# Custom Api 


如果默认的BaseApiService无法满足你的需求时，novate同样支持你自己的ApiService。


 **MyApi**
    
       
     public interface MyApi {

      @GET("url")
      Observable<MyBean> getdata(@QueryMap Map<String, String> maps);
   
     }
     
 **Execute**

     MyApi myApi = novate.create(MyApi.class);

     novate.call(myAPI.getdata(parameters),
                new BaseSubscriber<MyBean>{
                '''''''
                });

    }
 




# 注意

 如果你觉得此框架的业务码和错误码定的太死，其实框架已提供定制化方案，比如可以在你的项目中Assets中修改config文件：

如果想用自带的成功状态码0，不成功为非零的情况，可忽略下面的配置，无需改动。
`
  {
  "isFormat": "false",
  "sucessCode": [
    "1",
    "0",
    "1001"
  ],
  "error": {
    "1001": "网络异常"
  }
  }`


如果不想对结果格式化检查，请将`isFormat`设置为：`false`

如果想修改sucessCode的成功业务码，请将你的成功的业务码加入到`sucessCode `节点中。

**错误码**

需要对错误码进行自定义翻译，请配置相关`error`节点信息，具体可配置成：

                 `{
               "isFormat": "false",
                  "sucessCode": [
                    "1",
                 "0",
                  "1001"
                ],
                "error": {
                  "1001": "网络异常"，
                  "1002": "加入你的异常信息"
                         }
                 }
 


 **统一网络和Loading**
 
   继承Novate自带的的`BaseSubscriber<T>`,复写`onStart()`和`onCompleted()` 前者显示loading,后者结束loading.
   
   
   
  ``` 
    @Override
    public void onStart() {
        super.onStart();
        Log.v("Novate", "-->http is start");
        // todo some common as show loadding  and check netWork is NetworkAvailable
        // if  NetworkAvailable no !   must to call onCompleted
    }

    @Override
    public void onCompleted() {
        Log.v("Novate", "-->http is Complete");
        // todo some common as  dismiss loadding
    }
 ```
 
Q&A
---
 
1 Q：为什么服务器改变了数据，本地测试接口数据还是以前的旧数据？
  
  A： 在开发测试阶段，联调频繁的API时候，请将缓存关闭
  
2 Q: 为什么 我退出或杀进程 cookie就无效了？
 
 A： 由于有些机型在退出时候novate实例被回收了，请在application判断是存在novate真实实例 ，如重不存在请重新新初始化。本人建议用BaseActivity的context去初始化novate。因为退出再起启动application的oncreat()不会被触发，导致novate无法初始化。
 
3 Q: 为什么出现后端返回数据为空错误和API异常？

 
 A：由于Novate自动的异常驱动会捕获开发上层的异常，为了防止app闪退有一定的容错帮助，遇到错误时请先检查业务上层的代码中国任何实例是否初始化过，不然就会被novate处理，提示以上错误。
  
4 Q 我不想使用系统限制的数据格式，怎么办？
 
 A：请将你业务下的Assets中修改config.json文件中`isFormat`设置为false。
  
5 Q:我想Novate默认的成功码，因为我的后端返回的100是成功的，怎么办？
  
  A:请将你业务工程下下的Assets中修改config.json 文件中`sucessCode`节点加入自己的成功码即可，也可以支持加入多个。

  
5 Q:我不想用Novate默认的错误码，怎么办？
  A:请将你业务工程下的Assets中修改config.json文件中`error`加入自己的结果码和msg信息。

 
Update Log   
-----
版本历史: https://bintray.com/neglectedbyboss/maven/Novate
更新历史请看：https://bintray.com/neglectedbyboss/maven/Novate/view/release#release
  
   
#License
--------

    Copyright 2016 Tamic, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.   

**更多介绍：https://tamicer.github.io/2016/08/10/novate10/**


**技术交流QQ群： 458542940**

