Dubbo SPI在JDK SPI基础上，增强了如下功能：

1、JDK不支持缓存；Dubbo设计了缓存对象：spi的key与value 缓存在 cachedInstances对象里面，它是一个ConcurrentHashMap

2、JDK不支持默认值，Dubbo设计默认值：@SPI("Dubbo") 代表默认的spi对象，例如Protocol的@SPI("Dubbo")就是 DubboProtocol，
  通过 ExtensionLoader.getExtensionLoader(Protocol.class).getDefaultExtension()获取默认对象

3、JDK要用for循环判断对象，Dubbo设计getExtension灵活方便，动态获取spi对象，
  例如 ExtensionLoader.getExtensionLoader(Protocol.class).getExtension(spi的key)来提取对象

4、Dubbo增加了对扩展点AOP的支持，使用一个Wrapper 类实现扩展点接口，可以对实现类进行切面操作(类似于静态代理)

5、Dubbo增加了对扩展点IOC的支持，一个扩展点可以直接setter注入其它扩展点(通过构造函数注入)

6、Dubbo提供了自适应扩展注解@Adaptive，放在接口方法时，可以通过URL的方式，动态的获取扩展接口对象(实现类)
