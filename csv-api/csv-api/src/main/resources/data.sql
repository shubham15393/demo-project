create table data
(
 id IDENTITY NOT NULL PRIMARY KEY,
 source varchar(10),
 code_list_code varchar(20),
 code varchar(20),
 display_value varchar(200),
 long_description varchar(500),
 from_date date,
 to_date date,
 sorting_priority integer,
 CONSTRAINT data_pkey PRIMARY KEY (id)
);
