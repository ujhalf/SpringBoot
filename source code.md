### DispatcherServlet

HTTP请求处理的核心调度器，将请求分配到注册的处理器，提供方便地映射和异常处理功能。

这个控制器非常灵活:  通过装配恰当的适配器类，能适配于几乎任意工作流。

It offers the following functionality that distinguishes it from other request-driven web MVC frameworks:

- 基于JavaBean的配置机制
- 能使用任意`HandlerMapping`——预定义的或者是提供的，来作为应用的一部分，用以控制请求到处理器对象的路由。默认使用的是:`BeanNameUrlHandlerMapping`和`RequestMappingHandlerMapping`.
	- 通过在上下文中定义实现`HandleMapping`的接口的bean，能够覆盖的HandlerMapping
- 能使用任意的`HandlerAdapter`;这支持使用任何handler interface
	- 默认适配器包含:`HttpRequestHandlerAdapter`、`SimpleControllerAdapter`,用于Spring的`HttpRequestHandler`以及`Controller`接口，以及`RequestMappingHandlerAdapter`也会被注册
	- 通过在上下文中自定义`HandlerAdapter`能够覆盖默认的实现。
- 控制器的异常解析策略可通过配置`HandlerExceptionResolver`来指明，比如将特定的异常映射到错误页面。
	- 默认包含了:`ExceptionHandlerExceptionResolver`、`ResponseStatusExceptionResolver`|、`DefaultHandlerExceptionResolver`
- 通过指明`ViewResolver`的实现能够配置视图解析策略，将抽象的视图名解析为视图对象。
	- 默认为`InternalResourceResolver`
- 当视图或者是视图名称并不是用户提供的，那么已配置的`RequestToViewNameTranslator`将会把当前请求解析为视图名
	- 默认的bean id为`viewNameTranslator` class为`DefaultRequestToViewNameTranslator`
- 通过指明`MultipartResolver`的实现能够配置文件解析请求。
	- 通常使用的`CommonsMultipartResolver`
- locale解析可通过配置`LocaleResolver`
	- 开箱即用的实现是:`AcceptHeaderLocaleResolver`
- 配置`ThemeResolver`能够指明主题解析策略
	- 默认实现为:`FixedThemeResolver`
- 一个web应用可以定义任意数量的`DsipatcherServlet`，各自包含了独立的命名空间，加载各自的上下文及映射、处理器。