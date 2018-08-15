# COMPANY_SHORT_URL_API
> 公司要求，要用Node.js写API短链接服务，但是我刚毕业没多久，不太懂有关这方面的东西，
所以去查了相关资料然后自己写了一个来练练手，方便以后自己查阅；东西非常粗糙，在编程的道路上还要加倍努力！
## 说明
未加入数据库/缓存数据库等存储，是用文件的读取和写出来做的，只是一个非常粗糙简单的短链接生成器
## 实现思想
* 是将一串数据用十进制转六十二进制的思想，后续在公司中应该会采用redis作为缓存，然后每次短链接请求的时候查询缓存内有没有对应的KV数据，
如果有则直接返回对应的长链接并有HTTP重定向进行跳转；对于生成短链接尾数那些打算根据大佬们总结的自增id来转换，当然不会是从1开始的
## 开发环境
[JDK8](https://github.com/Sokkam/server_config/blob/master/java_config.md)<br/>
[Maven3.x](https://github.com/Sokkam/server_config/blob/master/maven_config.md)
## 接口
* 请求（url就是原网址）
```
http://localhost:8090/shorturl/generate?url={url}
```
* 响应
```json
{ 
    "id": 1,
    "originalUrl": "originalUrl",
    "shortUrlCode": "shortUrlCode"
}
```
## 项目执行
* 先将项目 ```clone``` 到本地
```
git clone https://github.com/Sokkam/company_short_url_api.git
```
* 然后进入到该项目目录
```
cd company_short_url_api
```
* 执行 ```mvn``` 命令进行打包
```
mvn clean package
```
* 打好的包在 ```target``` 目录下
```
cd target
ls
```
* ```ls``` 会看到一个 ```shorturl-1.0-SNAPSHOT.jar```的包，然后执行命令启动
```
java -jar shorturl-1.0-SNAPSHOT.jar
```
* 运行完后无报错即可访问