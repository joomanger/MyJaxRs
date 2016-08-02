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
--FOB
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(1,true,'Граница Украина-Белоруссия','Граница Украина-Белоруссия',1);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(2,true,'Граница Украина-Венгрия','Граница Украина-Венгрия',1);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(3,true,'Граница Украина-Молдова','Граница Украина-Молдова',1);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(4,true,'Граница Украина-Польша','Граница Украина-Польша',1);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(5,true,'Граница Украина-Румыния','Граница Украина-Румыния',1);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(6,true,'Граница Украина-РФ','Граница Украина-РФ',1);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(7,true,'Граница Украина-Словакия','Граница Украина-Словакия',1);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(8,true,'Грузополучатель','Грузополучатель',1);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(9,true,'Порт Бердянск','Порт Бердянск',1);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(10,true,'Порт Измаил','Порт Измаил',1);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(11,true,'Порт Ильичевск','Порт Ильичевск',1);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(12,true,'Порт Ильичевск-Паромная','Порт Ильичевск-Паромная',1);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(13,true,'Порт Ильичевск-Рыбный','Порт Ильичевск-Рыбный',1);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(14,true,'Порт Мариуполь','Порт Мариуполь',1);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(15,true,'Порт Николаев','Порт Николаев',1);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(16,true,'Порт Одесса','Порт Одесса',1);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(17,true,'Порт Южный','Порт Южный',1);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(18,true,'Склад АМК','Склад АМК',1);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(19,true,'Склад ДМКД','Склад ДМКД',1);
--UOM
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(20,true,'Тн','Тонны',2);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(21,true,'Пч','Пачки',2);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(22,true,'Шт','Штуки',2);
--FREIGHT TERMS
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(23,true,'CFR','Стоимость и фрахт',3);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(24,true,'CIF','Стоимость, страхование и фрахт',3);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(25,true,'CIP','Фрахт/перевозка и страхование оплачены до',3);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(26,true,'CPT','Фрахт/перевозка оплачены до',3);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(27,true,'DAF','Поставка до границы',3);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(28,true,'DAP','Поставка в месте назначения',3);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(29,true,'DDP','Поставка с оплатой пошлины',3);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(30,true,'DDU','Поставка без оплаты пошлины',3);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(31,true,'DEQ','Поставка с пристани',3);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(32,true,'DES','Поставка с судна',3);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(33,true,'EXW','Франко завод',3);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(34,true,'FAS','Поставка в доль борта судна',3);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(35,true,'FCA','Франко перевозчик',3);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(36,true,'FOB','Франко борт',3);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(37,true,'FOB ST L/S/D','Стоимость укладки груза на борту судна включена',3);
--DAYTYPES
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(38,true,'BANKING','Банковские',4);
insert into lookupitem(lookupitem_id,activestatus,valuez,valuezdescription,lookup_id) values(39,true,'CALENDAR','Календарные',4);



