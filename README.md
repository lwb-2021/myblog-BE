### 本项目前后端分离，前端项目位于https://gitee.com/lwb-2021/myblog-FE
请这样配置application.yml

``` yaml
lwb2021:
    jwt:
        secret: f4e2e52034348f86b67cde581c0f9eb5
        expire: 604800
        header: Authorization
mybatis:
    mapper-locations: classpath:mappers/*xml
    type-aliases-package: com.github.lwb2021.myblog.mybatis.entity
server:
    port: 8080

spring:
    application:
        name: my-blog
    datasource:
        dynamic:# 这么做的原因是方便之后用户中心
            primary: myblog
            datasource:
                user_center:
                    url: jdbc:mysql://localhost:3306/user_center?characterEncoding=utf8&useUnicode=true&useSSL=false&serverTimezone=GMT%2B8
                    username: root
                    password: 你的数据库密码
                    driver-class-name: com.mysql.cj.jdbc.Driver
                myblog:
                    url: jdbc:mysql://localhost:3306/myblog?characterEncoding=utf8&useUnicode=true&useSSL=false&serverTimezone=GMT%2B8
                    username: root
                    password: 你的数据库密码
                    driver-class-name: com.mysql.cj.jdbc.Driver
    autoconfigure:
        exclude:

```
