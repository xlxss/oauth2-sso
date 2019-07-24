# 使用Spring-security、OAuth2、vue实现前后端分离单点登录
---
### ·测试方法
1. 启动认证服务auth-server((port:8000))
2. 启动资源服务/sso客户端client-wx(port:8010)、client-qq(port:8020)
3. 启动前端应用web/client-wx(port:8081)、web/client-qq(port:8082)
4. 访问http://localhost:8081/about点击登录按钮
5. 输入用户名密码admin/admin，登录并授权
6. 成功跳转页面并打印从后端获取的数据：Hello, Client-wx
7. 访问http://localhost:8082/about点击登录按钮，或者直接访问http://localhost:8082/login/success可以直接获取到结果

---
### ·未实现功能
1. 前端捕获异常后处理
2. 单点登出
3. 前端的各自资源端访问通用资源（api-server）
4. 前端状态管理
5. 资源端接口细化权限控制

---
### ·未来计划内容
1. 用户、权限管理
2. 自定义统一登录页，适配多种登录方式（用户名/密码、手机号/密码、邮箱/密码等）
3. 各客户端使用自己的登录页，输入用户名、密码，调用认证中心进行静默登录并返回认证令牌