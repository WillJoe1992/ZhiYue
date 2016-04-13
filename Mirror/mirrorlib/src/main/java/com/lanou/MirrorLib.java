package com.lanou;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MirrorLib {
    public static void main(String[] args) {
        // 创建Schema对象
        // 构造方法第一个参数为数据库版本号
        // 第二个参数为自动生成的实体类将要存放的位置,前面为我的Android Module的包名
        Schema schema = new Schema(1000, "com.lanou.mirror.greendaodemo.entity.greendao");
        // 添加需要创建的实体类信息
        addNote(schema);
        try {
            // 创建实体类.第二个参数填Android Module的路径
            new DaoGenerator().generateAll(schema, "./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加将要创建的实体类的信息,会根据类名生成数据库的表,属性名生成数据库的字段<p>
     * 当然如果想建多张表,可以创建多个Entity对象
     *
     * @param schema
     */
    private static void addNote(Schema schema) {
        // 指定需要生成实体类的类名,类名确定了那么表名也是根据这个类名来自动命名的,例如下面这个,生成的表名叫做LabelEntity
        Entity entity = schema.addEntity("LabelEntity");
        // 指定自增长主键
        entity.addIdProperty().autoincrement().primaryKey();
        // 添加类的属性,根据属性生成数据库表中的字段
        entity.addStringProperty("labelname");

        Entity entity1=schema.addEntity("AllHolder");
        entity1.addIdProperty().autoincrement().primaryKey();
        entity1.addStringProperty("Goods_img");
        entity1.addStringProperty("Brand");
        entity1.addStringProperty("Model");
        entity1.addStringProperty("Product_area");
        entity1.addStringProperty("Goods_price");
        entity1.addStringProperty("Story_title");
        entity1.addStringProperty("Story_img");
        entity1.addStringProperty("Type");

        Entity homePager=schema.addEntity("HomePager");
        homePager.addIdProperty().autoincrement().primaryKey();
        homePager.addStringProperty("Goods_img");
        homePager.addStringProperty("Brand");
        homePager.addStringProperty("Model");
        homePager.addStringProperty("Product_area");
        homePager.addStringProperty("Goods_price");

        Entity special=schema.addEntity("Special");
        special.addIdProperty().autoincrement().primaryKey();
        special.addStringProperty("Story_title");
        special.addStringProperty("Story_img");

        Entity login=schema.addEntity("Login");
        login.addIdProperty().autoincrement().primaryKey();
        login.addStringProperty("token");
        login.addStringProperty("uid");

    }
}
