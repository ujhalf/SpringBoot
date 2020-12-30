# 1.注解

## @ConfigurationProperties

- 用于外部化配置的注解，用于将外部属性绑定和校验
- 注解在class definition或者是`@Configuration`注解的类中的`@Bean`注解的方法上
- 注意:  绑定是通过setter方法完成的，因此不会进行SpEl表达式的计算(区别于`@Value`注解)
- 仅当注解的类是容器的组件才能使用

使用方式1:

​		直接在application.proterties文件中添加属性,并添加prefix属性:

```properties
mycar.brand = BYD
mycar.price = 2000
```

```java
@ConfigurationProperties(prefix = "mycar")
public class Car {
    private String brand;
    private Integer price;
}

//同时 将该类加入容器
@Configuration
public class MyConfig {
    @Bean
    public Car car(){
        return new Car();
    }
}
```

使用方式2:

不在Configuration类中添加bean定义，而是使用`@EnableConfigurationProperties`注解:

```java
@ConfigurationProperties(prefix = "mycar")
public class Car {
    private String brand;
    private Integer price;
}
```

```java
@EnableConfigurationProperties(Car.class)  //使用这个注解 配合ConfigurationProperties注解
@Configuration
public class MyConfig {

}
```



使用方式3:

​		配合`@PropertySource`注解使用，自定义一个properties文件。

```properties
#mycar.properties
mycar.brand = BYD
mycar.price = 2000
```

​		在要注解的类上:

```java
/注意 仅在容器中的组件 才能使用该注解
@Component
@PropertySource("classpath:mycar.properties")
@ConfigurationProperties(prefix = "mycar")
public class Car {
    private String brand;
    private Integer price;
}
```



# 2.yaml

​		以数据为中心，轻量级节省空间。

### 2.1基本语法

- key: value; key value之间有空格
- 大小写敏感
- 使用缩进表示层级关系
- 缩进不允许使用Tab，仅能使用空格
- 缩进使用的空格数不限制，相同层级满足左对齐即可
- `#`用于表示注释
- 字符串无需加引号，如果要加，''与""表示字符串内容 会被 转义/不转义 
	- 单引号会转义 原来表示转义字符的现在只是普通字符
	- 双引号不转义 特殊字符仍然当做特殊字符处理

### 2.2 数据类型

- 字面量:单个的、不可再分的值，如:date、boolean、String、number、null

	- ```yaml
		k: v
		```

- 对象:

	- ```yam
		#行内写法:k:{k1:v1,k2:v2}
		#或者是:
		k:
		  k1: v1
		  k2: v2
		```

	- 

- 数组:

	- ```yaml
		# 行内写法: k: {v1,v2,v3}
		#或者
		k: 
		  - v1
		  - v2
		  - v3
		```



# 3.web开发

### 1.静态资源目录

默认情况下静态资源目录包含:类路径下的: `/static`、`/public`、`/resources`、`/META-INF/resources`

​		静态映射/**

- 请求到达后优先由controller进行处理，controller无法处理后由静态资源访问。

- 默认情况下静态资源无前缀，为了便于配置拦截器，可以配置前缀:

- ```yaml
	spring: 
		mvc: 
		  static-path-pattern: /res/**
	```

- 修改默认的静态资源路径:

- ```yaml
	spring:
		resources:
		 static-locations:[classpath:/haha/]
	```

### 2.欢迎页

- 默认情况下Index.html会被当做欢迎页

### 3.自定义favicon

- 默认情况下static/favicon.gif会被当做icon

### 4.静态资源配置原理

# 4.拦截器

`HadnlerInterceptor`

## 1.拦截器的使用

拦截器接口包含`preHandle`、`postHandle`、`afterCompletion`三个方法用于对请求进行拦截。

可自定义拦截器实现`HandlerInterceptor`接口，并将其注册到容器，定义拦截的路径和拦截的逻辑即可实现功能:

```java
package com.half.boot05webadmin.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.Interceptor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/26 15:03
 * @Version 1.0
 * @Description
 *
 * 登陆检查
 * 1.配置拦截器的拦截路径
 * 2.将拦截器加入容器
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String requestURI = request.getRequestURI();
        log.info(requestURI);
        HttpSession session=request.getSession();
        if (session.getAttribute("loginUser") != null) {
            System.out.println("拦截器放行");
            return true;
        }
        System.out.println("拦截器拦截");
        session.setAttribute("msg","请先登录");
        response.sendRedirect("/login");
        return false;
    }
}

```

```java
package com.half.boot05webadmin.config;

import com.half.boot05webadmin.interceptor.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Hui-min Lu
 * @Date 2020/12/26 15:07
 * @Version 1.0
 * @Description
 */
@Configuration
public class AdminWebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")//所有请求都拦截 包括静态资源
             .excludePathPatterns("/login","/","/css/**","/js/**","/fonts/**","/images/**");
               //放行的资源
    }
}

```

## 2.拦截器原理

1. 查找【HandlerExcetuonChain】可以处理当前请求的handler以及handler的所有的拦截器

2. 顺序执行拦截器的preHandle()方法，如果当前为true，则进入下一个拦截器的preHandle()方法；为false,则倒序执行已经执行了的所有拦截器的afterCompletion()方法

	

	1. ```java
		//DispatcherServlet 1035
					if (!mappedHandler.applyPreHandle(processedRequest, response)) {
							return;
					}
		```

	2. ```java
		//HandlerExecutionChain() 125 依次执行定义的拦截器的preHandle()方法
		boolean applyPreHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {
				HandlerInterceptor[] interceptors = getInterceptors();
				if (!ObjectUtils.isEmpty(interceptors)) {
					for (int i = 0; i < interceptors.length; i++) {
						HandlerInterceptor interceptor = interceptors[i];
						if (!interceptor.preHandle(request, response, this.handler)) {
							triggerAfterCompletion(request, response, null);
							return false;
						}
						this.interceptorIndex = i;
					}
				}
				return true;
			}
		```

	3. ```java
		//如果存在一个拦截器的preHandle()方法返回值为false,则倒序执行已经执行了的所有拦截器的afterCompletion()方法
		triggerAfterCompletion(request, response, null);
		```

3. 如果存在一个拦截器的preHandleI()方法返回false,则不执行目标方法；否则，执行目标方法。

4. 目标方法执行后会倒序执行拦截器的`postHandle()`方法。

5. 如果执行过程种存在任何异常，则会触发所有已经执行了的拦截器的`afterCompletion()`

# 5.文件上传

2.自动配置原理

自动配置封装在`MultipartAutoConfiguration`中

# 6.异常处理

## 1.默认规则

- 默认情况下，将错误映射到`/error`
- 对于非浏览器客户端，生成JSON响应(包含错误、http状态、异常消息的详细信息)；对于浏览器客户端，生成`Whitelabel`，以HTML的形式呈现相同的数据。
- **自定义的方案:**
	- 添加`View`解析为error
	- 完全替换默认行为可实现`ErrorContoller`并注册到容器中；或者是添加`ErrorAttributes`类型的组件使用现有机制替换其内容。
	- `error/`目录内的4XX、5XX会被自动解析

## 2.定制错误处理逻辑

- 自定义错误处理页面:error/404.html error/5xx.html

- `@ControllerAdvice`+`@ExceptionHandler` 通过调用`ExceptionHandlerExceptionResolver`处理`@ExceptionHandler`注解的错误处理

- 实现`HandlerExceptionResolver`

- `@ResponseStatus`+抛出自定义异常:通过`ResponseStatusExceptionResolver`进行处理

- `DefaultHandlerExceptionResolver`:处理框架底层的异常

- ```java
	@Order(value = Ordered.HIGHEST_PRECEDENCE)
	@Component
	public class CustomHandlerExceptionResolver implements HandlerExceptionResolver {
	    @SneakyThrows
	    @Override
	    public ModelAndView resolveException(HttpServletRequest request,
	                                         HttpServletResponse response,
	                                         Object handler, Exception ex) {
	        response.sendError(511,"自定义的错误");
	        return new ModelAndView();
	    }
	}
	
	```

- 

## 3.异常处理原理

- 在`ErrorMvcAutoConfiguration`定义了自动配置规则
	- 在容器中注册了一个`DefaultErrorAttributes`类型的组件
		- ``public class DefaultErrorAttributes implements ErrorAttributes, HandlerExceptionResolver, Ordered `
		- 定义错误页面中包含的数据类型
	- 在容器中注册了`BasicErrorController`
		- 默认处理`/error`下的请求，可修改`server.error.path`来改变默认值
		- 通过内容协商选择响应json或者是一个名为`error`的视图，new ModelAndView("error",model)
	- 容器中注册了一个视图，名为`view`
	- 注册了`BeanNameViewResolver`用于通过视图名称寻找视图
	- 注册了`DefaultErroViewResolver`：
		- 发生错误会去`error/`寻找视图名称
	- 
- 处理流程



# 7.原生组件注入(Servlet、Filter、Listener)

## 1.使用ServletAPI

使用注解`ServletComponentScan`和`@WebServlet`组合使用:

```java
@WebServlet(urlPatterns = "/my")
public class MyServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("66666");
    }
}

```

```java
@ServletComponentScan
@SpringBootApplication
public class Boot05WebAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(Boot05WebAdminApplication.class, args);
    }

}
```

​		这种方式注入的组件不经过过滤器。

## 2.使用RegistrationBean

# 8.嵌入式的servlet容器

- 默认支持的webServer：
	- `Tomcat`、`Jetty`、`Undertow`

原理:

- 导入了web场景的依赖->SpringBoot应用启动时创建适用于web的context:`ServletWebServerApplicationContext`
- `ServletWebServerApplicationContext`会查找`ServletWebServerFactory`(Servlet的web服务器工厂)->用于创建`Servlet`
- SpringBoot中默认包含`TomcatServletWebServerFactory`、`JettyServletWebServerFactory`、`UndertowServletWebServerFactory`

切换:

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-tomcat</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-undertow</artifactId>
        </dependency>
```

## 9.数据访问

### steps:

#### 1. 导入场景坐标

```xml
        <!--导入jdbc场景-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jdbc</artifactId>
        </dependency>
```

#### 2.分析自动配置

导入场景后就默认导入了:数据源、jdbc、事务所需的依赖:

导入mysql驱动坐标,由于自动配置已经配置了mysql的版本，但是可能和使用的mysql版本不一致，可以添加<version/>指明版本。

```xml
 <!--mysql-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
指明自定义的版本的两种方案:
1.<version>8.0.17<version/>
2. <properties>
        <java.version>11</java.version>
        <mysql.version>8.0.17</mysql.version>
    </properties>
```

3.整合druid



# 9.整合mybatis

## 1.导入坐标

```xml
 <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.4</version>
        </dependency>
```

## 2.