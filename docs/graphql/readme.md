- **`graphql 在客户端和服务器之间转移合约的 api: 表述服务器具有的能力 + 客户端描述需求=功能产品`**

## graphql

1. 简介

   - facebook(12 年) 为了解决相互关联, 嵌套, 递归的数据操作而创建的, graph query language{sql(struct)}
   - 是一种无状态的自校验的严格**数据驱动{声明式}**的查询语言{也是规范}, 只有一个端点, 且是 POST 请求
     1. 数据类型
     2. 自带数据校验
     3. 数据驱动{声明式}
     4. 查询语言{规范}: 查询灵活度
     5. 无状态的数据交换功能的语言
   - **graphql 中的 graph 是领域模型图(类型区分资源)**: 多资源(领域图)之间有明确的分层与数据关系

2. diagram

   ![avatar](/static/image/graphql-layout.png)

3. 概念模型: 查询 + **解析器** + 模式

   ![avatar](/static/image/docs-flow.png)

   - schema: type & validation
   - 领域对象: 分层且有关
   - 对象的 **resolver**: DB | API
   - operation type
     1. query: 查询
     2. mutation: 修改
     3. subscription: 实时推送数据

## graphql & restful & soap

1. restful: 无状态的数据交换功能的设计风格, 但是在应用领域模型超复杂时具有很大弊端

   - 都是基于 http 的无状态的规范(都语言无关)
   - 核心概念(对待资源与索引是分开的): rest 是资源; graphql 是类型(模式)
   - 我们是主动请求得到与 book 相关的 author 数据的，而不是服务端替我们决定的。
   - 在 RESTful 中，一个资源的种类与你获取它的方式是耦合的: 硬编码；;GraphQL 的一个核心差异：资源的描述信息与其获取方式相分离
   - 处理流程: 一个请求对应一个路由处理器处理, 一个 graphql 的请求可以唤起多个解析器
   - restful 需要你自己构建整个请求的响应; 而 graphql 的请求响应是由查询方指定结构、并由 graphql 进行构建组装的
   - 缺陷: 支持简单查询: 没有对数据粒度的控制(fileds 可以但是不友好)
   - 缺陷: 字段选择(?fileds 也能实现)
   - 缺陷: 多资源的数据检索, 硬编码 | api 数量激增(通过新 api 查询相关数据)

   ```json
   // 此时 rest api 就遇到困难: creator 是否显示{标识参数 | 每次都返回(流量) | 通过新api返回 creator(项目维护)}
   {
     "name": "zack",
     "creator": {
       "id": 2,
       "level": 1
     }
   }
   ```

## rpc & http

---

## reference

1. [graphql introduce](https://blog.csdn.net/u012206617/article/details/125330474)
2. [framework](https://github.com/Alice52/graphql/issues/3)
3. [pros & cons](https://github.com/Alice52/graphql/issues/51)
4. [github graphql api](https://docs.github.com/en/graphql)
   - [dependency](https://ivangoncharov.github.io/graphql-voyager/)
   - [migrating](https://docs.github.com/en/graphql/guides/migrating-from-rest-to-graphql)
5. https://github.com/Alice52/http/blob/521d1a61b3e06aa1522af3aa7859530d6852cdb1/v2/99.questions.md#L72-L73
6. https://mp.weixin.qq.com/s/d3DNfcyBjb8ayKq5AcvePQ
