CREATE TABLE recipe (
id 			long 			not null,
description varchar(20) 	not null,
instruciton varchar(300)	not null,
ingredients varchar(100)	not null,
preparetime int 			not null, 
primary key(id)
);
