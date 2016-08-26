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
insert into lookup(lookup_id, activestatus, description, name,systemlookup) values(1,true,'Пункты поставки','FOB',true);
insert into lookup(lookup_id, activestatus, description, name,systemlookup) values(2,true,'Единицы измерения','UOM',true);
insert into lookup(lookup_id, activestatus, description, name,systemlookup) values(3,true,'Условия доставки','FREIGHT TERMS',true);
insert into lookup(lookup_id, activestatus, description, name,systemlookup) values(4,true,'Календарные/Банковские дни','DAYTYPES',true);
insert into lookup(lookup_id, activestatus, description, name,systemlookup) values(5,true,'Языки системы','LANGUAGES',true);
--FOB
insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(1,true,'Граница Украина-Белоруссия',1);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(1,'Граница Украина-Белоруссия','Граница Украина-Белоруссия','RU',1);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(2,'Граница Украина-Белоруссия','Граница Украина-Белоруссия','US',1);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(3,'Граница Украина-Белоруссия','Граница Украина-Белоруссия','UA',1);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(2,true,'Граница Украина-Венгрия',1);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(4,'Граница Украина-Венгрия','Граница Украина-Венгрия','RU',2);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(5,'Граница Украина-Венгрия','Граница Украина-Венгрия','UA',2);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(6,'Граница Украина-Венгрия','Граница Украина-Венгрия','US',2);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(3,true,'Граница Украина-Молдова',1);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(7,'Граница Украина-Молдова','Граница Украина-Молдова','RU',3);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(8,'Граница Украина-Молдова','Граница Украина-Молдова','UA',3);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(9,'Граница Украина-Молдова','Граница Украина-Молдова','US',3);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(4,true,'Граница Украина-Польша',1);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(10,'Граница Украина-Польша','Граница Украина-Польша','RU',4);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(11,'Граница Украина-Польша','Граница Украина-Польша','UA',4);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(12,'Граница Украина-Польша','Граница Украина-Польша','US',4);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(5,true,'Граница Украина-Румыния',1);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(13,'Граница Украина-Румыния','Граница Украина-Румыния','RU',5);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(14,'Граница Украина-Румыния','Граница Украина-Румыния','UA',5);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(15,'Граница Украина-Румыния','Граница Украина-Румыния','US',5);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(6,true,'Граница Украина-РФ',1);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(16,'Граница Украина-РФ','Граница Украина-РФ','RU',6);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(17,'Граница Украина-РФ','Граница Украина-РФ','UA',6);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(18,'Граница Украина-РФ','Граница Украина-РФ','US',6);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(7,true,'Граница Украина-Словакия',1);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(19,'Граница Украина-Словакия','Граница Украина-Словакия','RU',7);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(20,'Граница Украина-Словакия','Граница Украина-Словакия','UA',7);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(21,'Граница Украина-Словакия','Граница Украина-Словакия','US',7);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(8,true,'Грузополучатель',1);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(22,'Грузополучатель','Грузополучатель','RU',8);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(23,'Грузополучатель','Грузополучатель','UA',8);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(24,'Грузополучатель','Грузополучатель','US',8);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(9,true,'Порт Бердянск',1);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(25,'Порт Бердянск','Порт Бердянск','RU',9);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(26,'Порт Бердянск','Порт Бердянск','UA',9);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(27,'Порт Бердянск','Порт Бердянск','US',9);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(10,true,'Порт Измаил',1);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(28,'Порт Измаил','Порт Измаил','RU',10);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(29,'Порт Измаил','Порт Измаил','UA',10);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(30,'Порт Измаил','Порт Измаил','US',10);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(11,true,'Порт Ильичевск',1);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(31,'Порт Ильичевск','Порт Ильичевск','RU',11);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(32,'Порт Ильичевск','Порт Ильичевск','UA',11);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(33,'Порт Ильичевск','Порт Ильичевск','US',11);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(12,true,'Порт Ильичевск-Паромная',1);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(34,'Порт Ильичевск-Паромная','Порт Ильичевск-Паромная','RU',12);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(35,'Порт Ильичевск-Паромная','Порт Ильичевск-Паромная','UA',12);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(36,'Порт Ильичевск-Паромная','Порт Ильичевск-Паромная','US',12);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(13,true,'Порт Ильичевск-Рыбный',1);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(37,'Порт Ильичевск-Рыбный','Порт Ильичевск-Рыбный','RU',13);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(38,'Порт Ильичевск-Рыбный','Порт Ильичевск-Рыбный','UA',13);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(39,'Порт Ильичевск-Рыбный','Порт Ильичевск-Рыбный','US',13);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(14,true,'Порт Мариуполь',1);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(40,'Порт Мариуполь','Порт Мариуполь','RU',14);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(41,'Порт Мариуполь','Порт Мариуполь','UA',14);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(42,'Порт Мариуполь','Порт Мариуполь','US',14);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(15,true,'Порт Николаев',1);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(43,'Порт Николаев','Порт Николаев','RU',15);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(44,'Порт Николаев','Порт Николаев','UA',15);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(45,'Порт Николаев','Порт Николаев','US',15);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(16,true,'Порт Одесса',1);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(46,'Порт Одесса','Порт Одесса','RU',16);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(47,'Порт Одесса','Порт Одесса','UA',16);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(48,'Порт Одесса','Порт Одесса','US',16);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(17,true,'Порт Южный',1);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(49,'Порт Южный','Порт Южный','RU',17);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(50,'Порт Южный','Порт Южный','UA',17);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(51,'Порт Южный','Порт Южный','US',17);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(18,true,'Склад АМК',1);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(52,'Склад АМК','Склад АМК','RU',18);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(53,'Склад АМК','Склад АМК','UA',18);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(54,'Склад АМК','Склад АМК','US',18);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(19,true,'Склад ДМКД',1);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(55,'Склад ДМКД','Склад ДМКД','RU',19);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(56,'Склад ДМКД','Склад ДМКД','UA',19);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(57,'Склад ДМКД','Склад ДМКД','US',19);
--UOM
insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(20,true,'Тн',2);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(58,'Тонны','Тонны','RU',20);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(59,'Тонны','Тонны','US',20);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(60,'Тонны','Тонны','UA',20);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(21,true,'Пч',2);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(61,'Пачки','Пачки','RU',21);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(62,'Пачки','Пачки','US',21);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(63,'Пачки','Пачки','UA',21);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(22,true,'Шт',2);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(64,'Штуки','Штуки','RU',22);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(65,'Штуки','Штуки','US',22);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(66,'Штуки','Штуки','UA',22);

--FREIGHT TERMS
insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(23,true,'CFR',3);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(67,'Стоимость и фрахт','Стоимость и фрахт','RU',23);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(68,'Стоимость и фрахт','Стоимость и фрахт','US',23);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(69,'Стоимость и фрахт','Стоимость и фрахт','UA',23);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(24,true,'CIF',3);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(70,'Стоимость, страхование и фрахт','Стоимость, страхование и фрахт','RU',24);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(71,'Стоимость, страхование и фрахт','Стоимость, страхование и фрахт','US',24);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(72,'Стоимость, страхование и фрахт','Стоимость, страхование и фрахт','UA',24);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(25,true,'CIP',3);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(73,'Фрахт/перевозка и страхование оплачены до','Фрахт/перевозка и страхование оплачены до','RU',25);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(74,'Фрахт/перевозка и страхование оплачены до','Фрахт/перевозка и страхование оплачены до','US',25);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(75,'Фрахт/перевозка и страхование оплачены до','Фрахт/перевозка и страхование оплачены до','UA',25);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(26,true,'CPT',3);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(76,'Фрахт/перевозка оплачены до','Фрахт/перевозка оплачены до','RU',26);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(77,'Фрахт/перевозка оплачены до','Фрахт/перевозка оплачены до','US',26);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(78,'Фрахт/перевозка оплачены до','Фрахт/перевозка оплачены до','UA',26);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(27,true,'DAF',3);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(79,'Поставка до границы','Поставка до границы','RU',27);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(80,'Поставка до границы','Поставка до границы','US',27);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(81,'Поставка до границы','Поставка до границы','UA',27);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(28,true,'DAP',3);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(82,'Поставка в месте назначения','Поставка в месте назначения','RU',28);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(83,'Поставка в месте назначения','Поставка в месте назначения','US',28);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(84,'Поставка в месте назначения','Поставка в месте назначения','UA',28);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(29,true,'DDP',3);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(85,'Поставка с оплатой пошлины','Поставка с оплатой пошлины','RU',29);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(86,'Поставка с оплатой пошлины','Поставка с оплатой пошлины','US',29);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(87,'Поставка с оплатой пошлины','Поставка с оплатой пошлины','UA',29);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(30,true,'DDU',3);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(88,'Поставка без оплаты пошлины','Поставка без оплаты пошлины','RU',30);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(89,'Поставка без оплаты пошлины','Поставка без оплаты пошлины','US',30);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(90,'Поставка без оплаты пошлины','Поставка без оплаты пошлины','UA',30);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(31,true,'DEQ',3);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(91,'Поставка с пристани','Поставка с пристани','RU',31);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(92,'Поставка с пристани','Поставка с пристани','US',31);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(93,'Поставка с пристани','Поставка с пристани','UA',31);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(32,true,'DES',3);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(94,'Поставка с судна','Поставка с судна','RU',32);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(95,'Поставка с судна','Поставка с судна','US',32);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(96,'Поставка с судна','Поставка с судна','UA',32);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(33,true,'EXW',3);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(97,'Франко завод','Франко завод','RU',33);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(98,'Франко завод','Франко завод','US',33);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(99,'Франко завод','Франко завод','UA',33);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(34,true,'FAS',3);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(100,'Поставка в доль борта судна','Поставка в доль борта судна','RU',34);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(101,'Поставка в доль борта судна','Поставка в доль борта судна','US',34);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(102,'Поставка в доль борта судна','Поставка в доль борта судна','UA',34);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(35,true,'FCA',3);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(103,'Франко перевозчик','Франко перевозчик','RU',35);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(104,'Франко перевозчик','Франко перевозчик','US',35);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(105,'Франко перевозчик','Франко перевозчик','UA',35);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(36,true,'FOB',3);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(106,'Франко борт','Франко борт','RU',36);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(107,'Франко борт','Франко борт','US',36);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(108,'Франко борт','Франко борт','UA',36);

insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(37,true,'FOB ST L/S/D',3);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(109,'Стоимость укладки груза на борту судна включена','Стоимость укладки груза на борту судна включена','RU',37);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(110,'Стоимость укладки груза на борту судна включена','Стоимость укладки груза на борту судна включена','US',37);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(111,'Стоимость укладки груза на борту судна включена','Стоимость укладки груза на борту судна включена','UA',37);

--DAYTYPES
insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(38,true,'BANKING',4);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(112,'Банковские','Банковские','RU',38);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(113,'Банковские','Банковские','US',38);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(114,'Банковские','Банковские','UA',38);
insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(39,true,'CALENDAR',4);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(115,'Календарные','Календарные','RU',39);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(116,'Календарные','Календарные','US',39);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(117,'Календарные','Календарные','UA',39);
--LANGUAGES
insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(40,true,'RU',5);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(118,'Русский','Русский','RU',40);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(119,'Русский','Русский','US',40);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(120,'Русский','Русский','UA',40);
insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(41,true,'US',5);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(121,'Английский','Английский','RU',41);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(122,'Английский','Английский','US',41);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(123,'Английский','Английский','UA',41);
insert into lookupitem(lookupitem_id,activestatus,valuez,lookup_id) values(42,true,'UA',5);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(124,'Украинский','Украинский','RU',42);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(125,'Украинский','Украинский','US',42);
insert into lookupitemtl(lookupitemtl_id,meaning,description,language,lookupitem_id) values(126,'Украинский','Украинский','UA',42);



