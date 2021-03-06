CREATE SEQUENCE order_header_number_sq START 1000 increment 1 minvalue 1 maxvalue 4294967295;
delete from public.logs;
INSERT INTO ITEM(item_id,name, description,uom1,uom2) VALUES (1,'ЛИСТ', 'ПРОКАТ ТОЛСТОЛИСТОВОЙ','Тн','Шт');
INSERT INTO ITEM(item_id,name, description,uom1,uom2) VALUES (2,'УГОЛОК', 'УГОЛОК','Тн',null);
INSERT INTO ITEM(item_id,name, description,uom1,uom2) VALUES (3,'ШВЕЛЛЕР', 'ШВЕЛЛЕР','Тн',null);
INSERT INTO ITEM(item_id,name, description,uom1,uom2) VALUES (4,'БАЛКА', 'БАЛКА ДВУТАВРОВАЯ','Тн',null);
INSERT INTO ITEM(item_id,name, description,uom1,uom2) VALUES (5,'КАТАНКА', 'КАТАНКА ИЗ УГЛЕРОДИСТОЙ СТАЛИ ОБЫКНОВЕННОГО КАЧЕСТВА','Тн',null);


insert into users(user_id, username, fullName, password,trader) values(1,'admin','Админчик','4813494d137e1631bba301d5acab6e7bb7aa74ce1185d456565ef51d737677b2',false);
insert into users(user_id, username, fullName, password,trader) values(2,'user','Юзерок','04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb',false);
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

insert into views(view_id,description,viewname,url) values(19,'','items','/client/item/items');
insert into views(view_id,description,viewname,url) values(20,'','openItem','/client/item/openItem');
insert into views(view_id,description,viewname,url) values(21,'','newItem','/client/item/newItem');

insert into views(view_id,description,viewname,url) values(22,'','lookups','/client/lookup/lookups');
insert into views(view_id,description,viewname,url) values(23,'','openLookup','/client/lookup/openLookup');
insert into views(view_id,description,viewname,url) values(24,'','newLookup','/client/lookup/newLookup');

insert into views(view_id,description,viewname,url) values(25,'','payments','/client/payments/payments');
insert into views(view_id,description,viewname,url) values(26,'','openPayment','/client/payments/openPayment');
insert into views(view_id,description,viewname,url) values(27,'','newPayment','/client/payments/newPayment');

insert into views(view_id,description,viewname,url) values(28,'','countries','/client/country/countries');
insert into views(view_id,description,viewname,url) values(29,'','openCountry','/client/country/openCountry');
insert into views(view_id,description,viewname,url) values(30,'','newCountry','/client/country/newCountry');

insert into views(view_id,description,viewname,url) values(31,'','roads','/client/rw/rwroad/roads');
insert into views(view_id,description,viewname,url) values(32,'','openRoad','/client/rw/rwroad/openRoad');
insert into views(view_id,description,viewname,url) values(33,'','newRoad','/client/rw/rwroad/newRoad');

insert into views(view_id,description,viewname,url) values(34,'','stations','/client/rw/rwstation/stations');
insert into views(view_id,description,viewname,url) values(35,'','openStation','/client/rw/rwstation/openStation');
insert into views(view_id,description,viewname,url) values(36,'','newStation','/client/rw/rwstation/newStation');

insert into views(view_id,description,viewname,url) values(37,'','customers','/client/customer/customers');
insert into views(view_id,description,viewname,url) values(38,'','openCustomer','/client/customer/openCustomer');
insert into views(view_id,description,viewname,url) values(39,'','newCustomer','/client/customer/newCustomer');

insert into views(view_id,description,viewname,url) values(40,'','contracts','/client/contract/contracts');
insert into views(view_id,description,viewname,url) values(41,'','openContract','/client/contract/openContract');
insert into views(view_id,description,viewname,url) values(42,'','newContract','/client/contract/newContract');

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
insert into lookup(lookup_id, activestatus, description, name,systemlookup) values(nextval('lookup_sq'),true,'Валюты','CURRENCY',true);
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
insert into lookup(lookup_id, activestatus, description, name,systemlookup) values(nextval('lookup_sq'),true,'Цели контракта','CONTRACT PURPOSES',true);
insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(nextval('lookupitem_sq'),true,'PURCHASE',currval('lookup_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Покупка','Покупка','RU',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Purchase','Purchase','US',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Покупка','Покупка','UA',currval('lookupitem_sq'));

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(nextval('lookupitem_sq'),true,'SALE',currval('lookup_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Продажа','Продажа','RU',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Sale','Sale','US',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Продажа','Продажа','UA',currval('lookupitem_sq'));
--Contract Groups
insert into lookup(lookup_id, activestatus, description, name,systemlookup) values(nextval('lookup_sq'),true,'Группы контрактов','CONTRACT GROUPS',true);
insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(nextval('lookupitem_sq'),true,'METAL_GROUP',currval('lookup_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Поставка металлопродукции','','RU',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Поставка металлопродукции','','US',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Поставка металлопродукции','','UA',currval('lookupitem_sq'));
--Contract Roles
insert into lookup(lookup_id, activestatus, description, name,systemlookup) values(nextval('lookup_sq'),true,'Роли в контрактах','CONTRACT ROLES',true);
insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(nextval('lookupitem_sq'),true,'SUPPLIER',currval('lookup_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Поставщик','','RU',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Supplier','','US',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Постачальник','','UA',currval('lookupitem_sq'));

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(nextval('lookupitem_sq'),true,'BUYER',currval('lookup_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Покупатель','','RU',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Buyer','','US',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Покупець','','UA',currval('lookupitem_sq'));
--Yes/No
insert into lookup(lookup_id, activestatus, description, name,systemlookup) values(nextval('lookup_sq'),true,'YES_NO','YES_NO',true);
insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(nextval('lookupitem_sq'),true,'Y',currval('lookup_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Да','','RU',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Yes','','US',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Да','','UA',currval('lookupitem_sq'));
insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(nextval('lookupitem_sq'),true,'N',currval('lookup_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Нет','','RU',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'No','','US',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Ні','','UA',currval('lookupitem_sq'));
--CONFIG RULE TYPES
insert into lookup(lookup_id, activestatus, description, name,systemlookup) values(nextval('lookup_sq'),true,'Типы правил конфигурации','CONFIG RULE TYPES',true);
insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(nextval('lookupitem_sq'),true,'NUMERIC',currval('lookup_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Числовое правило','','RU',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Numeric rule','','US',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Числовое правило','','UA',currval('lookupitem_sq'));

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(nextval('lookupitem_sq'),true,'LOGIC',currval('lookup_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Логическое правило','','RU',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Logic rule','','US',currval('lookupitem_sq'));
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(nextval('lookupitemtl_sq'),'Логическое правило','','UA',currval('lookupitem_sq'));


