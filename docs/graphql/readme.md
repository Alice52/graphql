[toc]

- **`graphql 在客户端和服务器之间转移合约的 api: 表述服务器具有的能力 + 客户端描述需求=功能产品`**

## graphql

1. 简介

   - facebook(12 年) 为了解决相互关联, 嵌套, 递归的数据操作而创建的, graph query language{sql(struct)}
   - 是一种无状态的自校验的严格**数据驱动{声明式}**的查询语言{也是规范}, 只有一个端点, 且是 post 请求
     1. 数据类型
     2. 自带数据校验
     3. 数据驱动{声明式}
     4. 查询语言{规范}: 查询灵活度
     5. 无状态的数据交换功能的语言
   - **graphql 中的 graph 是领域模型图(类型区分资源)**: **`多资源(领域图)之间有明确的分层与数据关系`**

2. diagram

   ![avatar](/static/image/graphql-layout.png)

3. 概念模型: 查询 + **解析器** + 模式

   ![avatar](/static/image/docs-flow.png)

   - schema: type & validation
   - 领域对象: 分层且有关
   - 对象的 **resolver**: db | api
   - operation type
     1. query: 查询
     2. mutation: 修改
     3. subscription: 实时推送数据

4. graphql 请求的执行过程

   - 服务器收到 http 请求, 取出其中的 graphql 查询
   - 遍历查询语句, 调用里面每个字段所对应的 resolver: db | rpc | etc
   - resolver 函数被执行并返回相应结果
   - **graphql 框架把结果根据查询语句的要求进行组装**

## [concept](https://graphql.org/learn/queries/)

1. schema: 定义数据类型与关系
2. type:
3. operation: Q/M/S
4. fields: `每个类型上的每个字段提供解析函数`
5. arguments: `(参数名: 参数值)`
6. aliases: 同一操作下解决冲突
7. fragments: 相当于<sql/>被复

   ```js
   // client
   query {
     user1: getUser(id: 1) {
       ...USER_FILED
     }

     user2: getUser(id: 2) {
       ...USER_FILED
     }
   }

   fragment USER_FILED on UserVo {
     id
     name
     age
   }
   ```

8. 标量类型:

   - Int
   - Float
   - String
   - Boolean
   - ID
   - `scalar Date`: 这个不是 graphql 自带的, 但是大部分实现都会有

   ```js
   type Human implements Character {
   id: ID!
   name: String!
   friends: [Character]
   appearsIn: [Episode]!
   starships: [Starship]
   totalCredits: Int
   }

   type Droid implements Character {
   id: ID!
   name: String!
   friends: [Character]
   appearsIn: [Episode]!
   primaryFunction: String
   }
   ```

9. Enumeration

   ```js
   enum Episode {
      NEWHOPE
      EMPIRE
      JEDI
   }
   ```

10. Interfaces

    ```js
    interface Character {
       id: ID!
       name: String!
       friends: [Character]
       appearsIn: [Episode]!
    }
    ```

## graphql & restful _& soap_

0. api flow

   ![avatar](/static/image/api-flow.png)
   ![avatar](/static/image/api-style.png)

1. [graphql](link)

   - pros: **`将 Vo 前置到前段 + Dto 自校验`**
   - pros: 带宽 + 请求数量 + 灵活
   - pros: [subscription] 接受服务端的推送
   - cons-N+1: 可以通过 DataLoader 异步查询聚合成一条语句解决
   - cons-性能: sql 字段要全选以供 resolver, 不能重复利用覆盖索引等特性`{内存和 CPU 不值钱, 是可以接受的}`
   - cons-使用: 需要预先创建 api schema[强类型]

2. [restful](link)

   - intros: 统一接口 | 无状态 | 缓存 | BS 结构 |服务端向客户端提供可执行代码的能力
   - **`HATEOAS 才是 REST 的关键功能`** + REST 是针对 API 的最高级别的抽象和最佳模型
   - pros: 客户端和服务端的解耦 | 可发现性 | 缓存友好
   - cons-api 数量: 在应用领域模型超复杂时具有很大弊端: 不同 vo + 关联数据导致的 api 数量与管理间的矛盾
   - cons-带宽: 庞大的负载

3. diff

   - (**带宽**)核心概念: `对数据粒度的控制(fileds 可以但是不友好)`
     1. rest: url 区分资源(最小控制粒度)
     2. graphql: 类型(模式{m/q/s})区分资源, 关心的是资源中的字段(最小控制粒度)
   - (**关联**)资源与其获取方式相分离:
     1. rest: [服务端决定一切返回], 一个资源与获取它的方式是耦合的(比如获取图书信息就是后端写死的逻辑)
     2. graphql: [客户端决定一切返回], 分层是数据是前端控制并获取的, 不是服务端替我们决定的(比如前端主动获取 book 关联的作者等灵活信息)
   - (**资源**)处理流程
     1. rest: 一个请求对应一个路由处理器处理(内部可以操作多个资源)
     2. graphql: 一个 graphql 的请求可以唤起多个解析器
   - 响应
     1. rest: 服务端完全自定义响应
     2. graphql: 由查询方指定结构, **之后由 graphql 进行构建组装的**
   - 请求方式
     1. rest: get | post | put | delete
     2. graphql: post
   - (**请求数量**)api 数量: 关联数据|不同 vo
     1. rest: 很多(为了实现不同的关联数据 || 为了返回不同的 vo 会更多{fields 不友好})
     2. graphql: 1 个, 分层资源会在 resolver 自动检索
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
   - 请求校验
     1. rest: 规范层没有校验, 但是可以在 spring 生态下校验并完成**自定义消息**
     2. graphql: 规范层面自校验, 不易自定义错误提示
   - sql 语句 & 性能: `现在服务器内存和 CPU 不值钱, 是可以接受的`
     1. rest: 能充分利用覆盖索引等特性进行优化
     2. graphql:[n+1 问题] SQL 查询必须对应的类型的所有字段以供选择, 否则就会报错
   - 开发过程中向后兼容
     1. rest: 不需要的字段也会返回占用带宽等资源
     2. graphql: 自向后兼容, 不需要的字段不操作就相当于不存在
   - 调试 & 文档
     1. rest: postman | swagger
     2. graphql: Altair-GraphQL-Client(_graphiql_) | introspection
   - 生态
     1. rest: spring 的支持(全局异常处理{自定义异常 code} | 预处理{权限}等)支持友好
     2. graphql: [dgs 可以预处理{auth}等, 但是不是很友好](https://graphql.cn/learn/authorization/)
   - 服务端推流
     1. rest: 长链接可以做到
     2. graphql: subscription

4. same

   - 都是基于 http 的无状态的数据交换规范(设计风格)(语言无关)
   - 都有资源的概念, 只是 graphql 中叫到类型
   - 都响应 json

5. _soap_
   - intros: 只支持 xml 数据交换协议, 支持有状态和无状态消息传
   - pros: 独立于语言和平台 | 绑定到各种协议 | 内置错误处理 | **安全**拓展
   - cons: 大量的消息结构(不是有效内容) | 重量级

---

## reference

1. [graphql web](https://graphql.org/learn/queries/)
2. [graphql introduce](https://blog.csdn.net/u012206617/article/details/125330474)
3. [rest vs graphql](https://www.cnblogs.com/chenwenhao/articles/12687763.html)
4. [graphql usage common issue](https://jerryzou.com/posts/10-questions-about-graphql/)
   - [Relay 风格的分页接口](https://relay.dev/graphql/connections.htm#sec-undefined.PageInfo)
   - 学习应用代价比较大
5. [(rest|graphql) || (rpc|http)](https://mp.weixin.qq.com/s/d3DNfcyBjb8ayKq5AcvePQ)
6. [github graphql api](https://docs.github.com/en/graphql)
   - [dependency](https://ivangoncharov.github.io/graphql-voyager/)
   - [migrating](https://docs.github.com/en/graphql/guides/migrating-from-rest-to-graphql)
7. ~~[framework](https://github.com/Alice52/graphql/issues/3)~~
8. ~~[pros & cons](https://github.com/Alice52/graphql/issues/51)~~
