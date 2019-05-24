# 集成springboot

### 说明文档
For further reference, please consider the following sections:
```
java-1.8
akka-2.5.14
springboot-2.1.5.RELEASE
```
#### 思想
```
AKKA的Extension机制将AKKA的Actor注入到Spring的IoC容器中。然后通过依赖注入来使用Actor。
1-将ActorSystem集成到SpringApplicationContext中
2-将SpringBeen注入到AkkaActors
```


