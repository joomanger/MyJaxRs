CREATE SEQUENCE order_header_number_sq START 1000 increment 1 minvalue 1 maxvalue 4294967295;
INSERT INTO ITEM(item_id,name, description,uom1,uom2) VALUES (1,'ЛИСТ', 'ПРОКАТ ТОЛСТОЛИСТОВОЙ','Тн','Шт');
INSERT INTO ITEM(item_id,name, description,uom1,uom2) VALUES (2,'УГОЛОК', 'УГОЛОК','Тн',null);
INSERT INTO ITEM(item_id,name, description,uom1,uom2) VALUES (3,'ШВЕЛЛЕР', 'ШВЕЛЛЕР','Тн',null);
INSERT INTO ITEM(item_id,name, description,uom1,uom2) VALUES (4,'БАЛКА', 'БАЛКА ДВУТАВРОВАЯ','Тн',null);
INSERT INTO ITEM(item_id,name, description,uom1,uom2) VALUES (5,'КАТАНКА', 'КАТАНКА ИЗ УГЛЕРОДИСТОЙ СТАЛИ ОБЫКНОВЕННОГО КАЧЕСТВА','Тн',null);
INSERT INTO parameterconfiguration values(1,'ATTRIBUTE1','L,mm',false,'Длина, мм', 1,35);
INSERT INTO parameterconfiguration values(2,'ATTRIBUTE2','T,mm',false,'Толщина, мм', 1,35);
INSERT INTO parameterconfiguration values(3,'ATTRIBUTE3','W,mm',false,'Ширина, мм', 1,35);
INSERT INTO parameterconfiguration values(4,'ATTRIBUTE4','Марка',true,'Марка стали', 3,300);
INSERT INTO parameterconfiguration values(5,'ATTRIBUTE5','Стан',false,'Прокатный стан', 3,60);
insert into parameterconfigurationvalues values(1,1,'Марка 1',4);
insert into parameterconfigurationvalues values(2,2,'Марка 2',4);
insert into parameterconfigurationvalues values(3,3,'Марка 3',4);
insert into parameterconfigurationvalues values(4,4,'Марка 4',4);
insert into parameterconfigurationvalues values(5,5,'Марка 5',4);
insert into parameterconfigurationvalues values(6,1,'Стан 1',5);
insert into parameterconfigurationvalues values(7,2,'Стан 2',5);
insert into parameterconfigurationvalues values(8,3,'Стан 3',5);
insert into parameterconfigurationvalues values(9,4,'Стан 4',5);
insert into parameterconfigurationvalues values(10,5,'Стан 5',5);
INSERT INTO CONFIGURATION values(1,1,'AUTOLOAD',1);
INSERT INTO CONFIGURATION values(2,1,'AUTOLOAD',2);
INSERT INTO configurationline values(1,1,1,1);
INSERT INTO configurationline values(2,1,2,2);
INSERT INTO configurationline values(3,1,3,3);
INSERT INTO configurationline values(4,1,4,4);
INSERT INTO configurationline values(5,1,5,5);
INSERT INTO configurationline values(6,2,1,1);
INSERT INTO configurationline values(7,2,2,2);
INSERT INTO configurationline values(8,2,3,3);
INSERT INTO configurationline values(9,2,4,4);
INSERT INTO configurationline values(10,2,5,5);
insert into saleorder values(1,'customer',1000,null,null);

insert into users(user_id, username, fullName, password) values(1,'admin','Админчик','SBNJTRN+FjG7owHVrKtue7eqdM4RhdRWVl71HXN2d7I=');
insert into users(user_id, username, fullName, password) values(2,'user','Юзерок','BPiZbadjt6lpsQKO4wB1aerzpjVIbdqyEdUSyFud+Ps=');
insert into groups(group_id, groupname,description) values(1,'users','Все пользователи системы');
insert into users_groups values(1,1);
insert into users_groups values(2,1);
create or replace view users_groups_v as select u.username, g.groupname from users_groups ug, users u, groups g where u.user_id=ug.user_fk and g.group_id=ug.group_fk;
create or replace view users_views_v as select u.username,v.view_id, v.viewName, v.description, v.url from views v, menuitem mi,roles_menus rm,users_roles ur,users u where mi.view_id=v.view_id and rm.menu_fk=mi.menu_id and ur.role_fk=rm.role_fk and u.user_id=ur.user_fk;


-- Заполним въюшки
insert into views(view_id,description,viewname,url) values(1,'','configs','/client/config/configs');
insert into views(view_id,description,viewname,url) values(2,'','newConfig','/client/config/newConfig');
insert into views(view_id,description,viewname,url) values(3,'','openConfig','/client/config/openConfig');

insert into views(view_id,description,viewname,url) values(4,'','params','/client/config/param/params');
insert into views(view_id,description,viewname,url) values(5,'','openParameter','/client/config/param/openParameter');
insert into views(view_id,description,viewname,url) values(6,'','newParameter','/client/config/param/newParameter');

insert into views(view_id,description,viewname,url) values(7,'','menus','/client/sys/menu/menus');
insert into views(view_id,description,viewname,url) values(8,'','newMenu','/client/sys/menu/newMenu');
insert into views(view_id,description,viewname,url) values(9,'','openMenu','/client/sys/menu/openMenu');

insert into views(view_id,description,viewname,url) values(10,'','views','/client/sys/view/views');
insert into views(view_id,description,viewname,url) values(11,'','openView','/client/sys/view/openView');
insert into views(view_id,description,viewname,url) values(12,'','newView','/client/sys/view/newView');

insert into views(view_id,description,viewname,url) values(13,'','roles','/client/sys/role/roles');
insert into views(view_id,description,viewname,url) values(14,'','openRole','/client/sys/role/openRole');
insert into views(view_id,description,viewname,url) values(15,'','newRole','/client/sys/role/newRole');

insert into views(view_id,description,viewname,url) values(16,'','users','/client/sys/user/users');
insert into views(view_id,description,viewname,url) values(17,'','openUser','/client/sys/user/openUser');
insert into views(view_id,description,viewname,url) values(18,'','newUser','/client/sys/user/newUser');

-- Заполним менюхи
insert into menu values(1,true, 'System Administrator');
insert into menuitem(menuitem_id,menuitem,line_num, menu_id, view_id, visibleitem) values(1,'Конфигурации',1,1,1,true);
insert into menuitem(menuitem_id,menuitem,line_num, menu_id, view_id, visibleitem) values(2,'Открыть конфиг',2,1,3,false);
insert into menuitem(menuitem_id,menuitem,line_num, menu_id, view_id, visibleitem) values(3,'Создать конфиг',3,1,2,false);

insert into menuitem(menuitem_id,menuitem,line_num, menu_id, view_id, visibleitem) values(4,'Параметры конфигурации',4,1,4,true);
insert into menuitem(menuitem_id,menuitem,line_num, menu_id, view_id, visibleitem) values(5,'Открыть параметр',5,1,5,false);
insert into menuitem(menuitem_id,menuitem,line_num, menu_id, view_id, visibleitem) values(6,'Создать параметры',6,1,6,false);

insert into menuitem(menuitem_id,menuitem,line_num, menu_id, view_id, visibleitem) values(7,'Меню',7,1,7,true);
insert into menuitem(menuitem_id,menuitem,line_num, menu_id, view_id, visibleitem) values(8,'Открыть меню',8,1,8,false);
insert into menuitem(menuitem_id,menuitem,line_num, menu_id, view_id, visibleitem) values(9,'Создать меню',9,1,9,false);

insert into menuitem(menuitem_id,menuitem,line_num, menu_id, view_id, visibleitem) values(10,'Представления',10,1,10,true);
insert into menuitem(menuitem_id,menuitem,line_num, menu_id, view_id, visibleitem) values(11,'Открыть представление',11,1,11,false);
insert into menuitem(menuitem_id,menuitem,line_num, menu_id, view_id, visibleitem) values(12,'Создать представление',12,1,12,false);

insert into menuitem(menuitem_id,menuitem,line_num, menu_id, view_id, visibleitem) values(13,'Роли',13,1,13,true);
insert into menuitem(menuitem_id,menuitem,line_num, menu_id, view_id, visibleitem) values(14,'Открыть роль',14,1,14,false);
insert into menuitem(menuitem_id,menuitem,line_num, menu_id, view_id, visibleitem) values(15,'Создать роль',15,1,15,false);

insert into menuitem(menuitem_id,menuitem,line_num, menu_id, view_id, visibleitem) values(16,'Пользователи',16,1,16,true);
insert into menuitem(menuitem_id,menuitem,line_num, menu_id, view_id, visibleitem) values(17,'Открыть пользователя',17,1,17,false);
insert into menuitem(menuitem_id,menuitem,line_num, menu_id, view_id, visibleitem) values(18,'Создать пользователя',18,1,18,false);


--Заполним роли
insert into roles values(1,'','admin');
insert into roles values(2,'','user');
insert into roles_menus values(1,1);

insert into users_roles values(1,1);
insert into users_roles values(2,2);

--Заполним лукапы
--Заполнение перенесено в скрипт pg_to_orcl.sql
--insert into lookup(lookup_id, activestatus, description, name,systemlookup) values(nextval('lookup_sq'),true,'Пункты поставки','FOB',true);
--Заполнение перенесено в скрипт pg_to_orcl.sql
--insert into lookup(lookup_id, activestatus, description, name,systemlookup) values(nextval('lookup_sq'),true,'Условия доставки','FREIGHT TERMS',true);




--UOM
insert into lookup(lookup_id, activestatus, description, name,systemlookup) values(nextval('lookup_sq'),true,'Единицы измерения','UOM',true);
insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(nextval('lookupitem_sq'),true,'Тн',currval('lookup_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Тонна','','RU',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Ton','','US',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Тонна','','UA',currval('lookupitem_sq'));

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(nextval('lookupitem_sq'),true,'Пч',currval('lookup_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Пачка','','RU',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Pack','','US',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Пачка','','UA',currval('lookupitem_sq'));

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(nextval('lookupitem_sq'),true,'Шт',currval('lookup_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Штука','','RU',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Piece','','US',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Штука','','UA',currval('lookupitem_sq'));
--DAYTYPES
insert into lookup(lookup_id, activestatus, description, name,systemlookup) values(nextval('lookup_sq'),true,'Календарные/Банковские дни','DAYTYPES',true);
insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(nextval('lookupitem_sq'),true,'BANKING',currval('lookup_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Банковские','Банковские','RU',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Банковские','Банковские','US',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Банковские','Банковские','UA',currval('lookupitem_sq'));
insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(nextval('lookupitem_sq'),true,'CALENDAR',currval('lookup_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Календарные','Календарные','RU',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Календарные','Календарные','US',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Календарные','Календарные','UA',currval('lookupitem_sq'));
--LANGUAGES
insert into lookup(lookup_id, activestatus, description, name,systemlookup) values(nextval('lookup_sq'),true,'Языки системы','LANGUAGES',true);
insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(nextval('lookupitem_sq'),true,'RU',currval('lookup_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Русский','Русский','RU',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Русский','Русский','US',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Русский','Русский','UA',currval('lookupitem_sq'));
insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(nextval('lookupitem_sq'),true,'US',currval('lookup_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Английский','Английский','RU',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Английский','Английский','US',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Английский','Английский','UA',currval('lookupitem_sq'));
insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(nextval('lookupitem_sq'),true,'UA',currval('lookup_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Украинский','Украинский','RU',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Украинский','Украинский','US',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Украинский','Украинский','UA',currval('lookupitem_sq'));
--CURRENCY
insert into lookup(lookup_id, activestatus, description, name,systemlookup) values(nextval('lookup_sq'),true,'Валюты','Currency',true);
insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(nextval('lookupitem_sq'),true,'UAH',currval('lookup_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Гривня','Гривня','RU',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Гривня','Гривня','US',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Гривня','Гривня','UA',currval('lookupitem_sq'));

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(nextval('lookupitem_sq'),true,'USD',currval('lookup_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Доллар США','Доллар США','RU',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Доллар США','Доллар США','US',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Доллар США','Доллар США','UA',currval('lookupitem_sq'));

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(nextval('lookupitem_sq'),true,'RUB',currval('lookup_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Росс.рубль','Росс.рубль','RU',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Росс.рубль','Росс.рубль','US',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Росс.рубль','Росс.рубль','UA',currval('lookupitem_sq'));

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(nextval('lookupitem_sq'),true,'EUR',currval('lookup_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'ЕВРО','ЕВРО','RU',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'ЕВРО','ЕВРО','US',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'ЕВРО','ЕВРО','UA',currval('lookupitem_sq'));
--Contract Purposes
insert into lookup(lookup_id, activestatus, description, name,systemlookup) values(nextval('lookup_sq'),true,'Цели контракта','Contract purposes',true);
insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(nextval('lookupitem_sq'),true,'PURCHASE',currval('lookup_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Покупака','Покупака','RU',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Purchase','Purchase','US',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Покупака','Покупака','UA',currval('lookupitem_sq'));

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(nextval('lookupitem_sq'),true,'SALE',currval('lookup_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Продажа','Продажа','RU',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Sale','Sale','US',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Продажа','Продажа','UA',currval('lookupitem_sq'));
--Contract Groups
insert into lookup(lookup_id, activestatus, description, name,systemlookup) values(nextval('lookup_sq'),true,'Группы контрактов','Contract groups',true);
insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(nextval('lookupitem_sq'),true,'METAL_GROUP',currval('lookup_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Поставка металлопродукции','','RU',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Поставка металлопродукции','','US',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Поставка металлопродукции','','UA',currval('lookupitem_sq'));
--Contract Roles
insert into lookup(lookup_id, activestatus, description, name,systemlookup) values(nextval('lookup_sq'),true,'Роли в контрактах','Contract roles',true);
insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(nextval('lookupitem_sq'),true,'SUPPLIER',currval('lookup_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Поставщик','','RU',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Supplier','','US',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Постачальник','','UA',currval('lookupitem_sq'));

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(nextval('lookupitem_sq'),true,'BUYER',currval('lookup_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Покупатель','','RU',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Buyer','','US',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Покупець','','UA',currval('lookupitem_sq'));



