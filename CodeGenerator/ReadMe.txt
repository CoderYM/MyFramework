代码生成工具使用说明

一、根据数据库表生成DAO相关代码和配置
1、修改core/src/main/java/com/allinpay/generator/ibatis/context-dao.xml中的数据库配置
2、修改CodeGeneratorFactory类中main方法下数据库表名称和模块简称
3、执行CodeGeneratorFactory，到相应路径查看生成的代码