--insert initial users
--insert into user(id, name, password, creation_date, last_access) values (1, 'jason', '$2a$10$UuFhe87yrYuYclREa1t6oeY8c2DwGPSl6hqcb.apPPGbfs/a2YPuC', current_timestamp, current_timestamp);
--insert into user(id, name, password, creation_date, last_access) values (2, 'douglas', '$2a$10$4VipmSRa6eu9KMVcRnls4uykSDtIBd0goaqYpUzAitUDyRf84i8Cq', current_timestamp, current_timestamp);
--insert into user(id, name, password, creation_date, last_access) values (3, 'jezinho_silva', '$2a$10$UuFhe87yrYuYclREa1t6oeY8c2DwGPSl6hqcb.apPPGbfs/a2YPuC', current_timestamp, current_timestamp);
--insert into user(id, name, password, creation_date, last_access) values (4, 'teste1', '$2a$10$X225PGoG.PBYgPaQd0QiaOp1sIESISJ4EMPW2SykNNURPRinDV08y', current_timestamp, current_timestamp);
--insert into user(id, name, password, creation_date, last_access) values (5, 'teste2', '$2a$10$DangJ1hlYjb2b4xHNGkuQuqF38eOdYvP596g.Gz5XAb1XK3DMc04C', current_timestamp, current_timestamp);

--
--insert into tax_one_api (id, url, username, password) values (1, 'https://safx.prod.taxone.thomsonreuters.com/api/', 'douglas_trindade-lkm', 'Lkm@2030')


--dummy data
--insert into safxtable (description, name, id) values ('Arquivo Cont�bil', 'SAFX01', 1001);
--insert into safxcolumn (column_type, ds_column_id, name, position, required, safx_table_id, size, id) values(1,null,'COD_EMPRESA',1,true,1001,3,1001);

--insert into upload (id, file_name, layout_version, creation_date, status, user_id) values (1001, 'Manual_Layout_MastersafeDE.xls', '240', '2021-08-09', 'ACTIVE', 1);

--insert into DATA_SOURCE_CONFIGURATION(id, url, username, password, table_names) values(1001, 'jdbc:orable:thin:localhost:peoplesoft', 'jason', 'DEUSjason', 'TB_SAFX01,TB_SAFX02');
--insert into DSTABLE values (1001, 'TB_SAFX01', 1001);
--insert into DSCOLUMN(id, name, column_Type, size, ds_Table_id) values (1001, 'ID', 'NUMBER', 7, 1001); 
--insert into DSTABLE values (1002, 'TB_SAFX02', 1001);
--insert into DSCOLUMN(id, name, column_Type, size, ds_Table_id) values (1002, 'ID', 'NUMBER', 7, 1002); 
--insert into DSCOLUMN(id, name, column_Type, size, ds_Table_id) values (1003, 'NAME', 'VARCHAR', 255, 1002); 
