# Items schema
 
# --- !Ups

CREATE SEQUENCE tag_id_seq;
CREATE TABLE tag (
    id integer NOT NULL DEFAULT nextval('tag_id_seq'),
    name varchar(255)
);

CREATE SEQUENCE item_id_seq;
CREATE TABLE item (
    id integer NOT NULL DEFAULT nextval('item_id_seq'),
    name varchar(255)
);

CREATE TABLE item_tag (
    item_id integer NOT NULL,
    tag_id integer NOT NULL
);

# --- !Downs
 
DROP TABLE task;
DROP SEQUENCE task_id_seq;