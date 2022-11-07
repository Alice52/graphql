## graphql

1. 简介

   - 是一种自校验的严格**数据驱动{声明式}**的查询语言{也是规范}, 只有一个端点, 且是 POST 请求
     1. 数据类型
     2. 自校验
     3. 数据驱动{声明式}
     4. 查询语言{规范}
   - 无状态的数据交换功能的语言, 且具有很大的**查询灵活度**和自带数据校验
   - **graphql 中的 graph 是领域模型图**

2. diagram

   ![avatar](/static/image/graphql-layout.png)

3. operation type

   - query: 查询
   - mutation: 修改
   - subscription: 实时推送数据

4. 概念模型

   ![avatar](/static/image/docs-flow.png)

   - schema: type & validation
   - 领域对象
   - 对象的 resolver: DB | API

## graphql - java

1. 相关框架

   ```js
   graphql java kickstart  | netflix dgs  |  spring graphql
         ↑                   ↑                  ↑
                           graphql-java
                              ↑
                        graphql spec
   ```

## graphql & restful & soap

1. restful: 无状态的数据交换功能的设计风格, 但是在应用领域模型超复杂时具有很大弊端

   - 缺陷: _状态流转_ | 字段选择(?fileds 也能实现) | api 数量激增(通过新 api 查询相关数据)

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

2. https://github.com/Alice52/http/blob/521d1a61b3e06aa1522af3aa7859530d6852cdb1/v2/99.questions.md#L72-L73
3. https://mp.weixin.qq.com/s/d3DNfcyBjb8ayKq5AcvePQ

## rpc & http

---

## reference

1. [framework](https://github.com/Alice52/graphql/issues/3)
2. [pros & cons](https://github.com/Alice52/graphql/issues/51)
3. [github graphql api](https://docs.github.com/en/graphql)
   - [dependency](https://ivangoncharov.github.io/graphql-voyager/)
   - [migrating](https://docs.github.com/en/graphql/guides/migrating-from-rest-to-graphql)
4. [graphql introduce](https://blog.csdn.net/u012206617/article/details/125330474)
