CREATE SEQUENCE order_header_number_sq START 1000 increment 1 minvalue 1 maxvalue 4294967295;
INSERT INTO ITEM(id,name, description) VALUES (1,'ЛИСТ', 'ПРОКАТ ТОЛСТОЛИСТОВОЙ');
INSERT INTO ITEM(id,name, description) VALUES (2,'УГОЛОК', 'УГОЛОК');
INSERT INTO ITEM(id,name, description) VALUES (3,'ШВЕЛЛЕР', 'ШВЕЛЛЕР');
INSERT INTO ITEM(id,name, description) VALUES (4,'БАЛКА', 'БАЛКА ДВУТАВРОВАЯ');
INSERT INTO ITEM(id,name, description) VALUES (5,'КАТАНКА', 'КАТАНКА ИЗ УГЛЕРОДИСТОЙ СТАЛИ ОБЫКНОВЕННОГО КАЧЕСТВА');
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
insert into groups(group_id, groupname,description) values(1,'admin','Группа админов');
insert into users_groups values(1,1);
insert into users_groups values(2,1);
create or replace view users_groups_v as select u.username, g.groupname from users_groups ug, users u, groups g where u.user_id=ug.user_fk and g.group_id=ug.group_fk;


-- Заполним въюшки
insert into views(view_id,description,viewname,url) values(1,'','configs','/client/config/configs');
insert into views(view_id,description,viewname,url) values(2,'','newConfig','/client/config/newConfig');
insert into views(view_id,description,viewname,url) values(3,'','openConfig','/client/config/openConfig');
insert into views(view_id,description,viewname,url) values(4,'','params','/client/config/param/params');
insert into views(view_id,description,viewname,url) values(5,'','openParameter','/client/config/param/openParameter');
insert into views(view_id,description,viewname,url) values(6,'','newParameter','/client/config/param/newParameter');
-- Заполним менюхи
insert into menu values(1,true, 'Автозагрузка user');
insert into menuitem(menuitem_id,menuitem,line_num, menu_id, view_id) values(1,'Конфигурации user',1,1,null);
insert into menuitem(menuitem_id,menuitem,line_num, menu_id, view_id) values(2,'Параметры конфигурации user',2,1,null);
insert into menu values(2,true, 'Автозагрузка admin');
insert into menuitem(menuitem_id,menuitem,line_num, menu_id, view_id) values(3,'Конфигурации admin',1,2,1);
insert into menuitem(menuitem_id,menuitem,line_num, menu_id, view_id) values(4,'Параметры конфигурации admin',2,2,4);

--для юзера работа с конфигурациями недоступна
--------------------------------

-- Конфигурации admin
--insert into menuitems_views values(3,1);--configs
--insert into menuitems_views values(3,2);--newConfig
--insert into menuitems_views values(3,3);--openConfig

--Заполним роли
insert into roles values(1,'','user');
insert into roles values(2,'','admin');
insert into roles_menus values(1,1);
insert into roles_menus values(2,2);

insert into users_roles values(1,2);
insert into users_roles values(2,1);
