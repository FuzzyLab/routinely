CREATE TABLE if not exists apex_article (
	id SERIAL PRIMARY KEY NOT NULL,
	title char(50) DEFAULT NULL,
    content text DEFAULT NULL,
	created timestamp NOT NULL
);

CREATE TABLE if not exists apex_image (
	id SERIAL PRIMARY KEY NOT NULL,
	content_type char(20) NOT NULL,
	content bytea NOT NULL,
    article_id integer REFERENCES apex_article
);

CREATE TABLE if not exists apex_user (
	id SERIAL PRIMARY KEY NOT NULL,
	name char(50) NOT NULL,
	email char(50) NOT NULL,
	password char(100) NOT NULL,
	created timestamp NOT NULL,
	roles char(50) NOT NULL
);
