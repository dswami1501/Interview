I)Please follow these steps to create database.

1.CREATE TABLE IF NOT EXISTS interview."user"
(
    user_id integer NOT NULL DEFAULT nextval('interview.user_user_id_seq'::regclass),
    user_name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT user_pkey PRIMARY KEY (user_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS interview."user"
    OWNER to postgres;

2.CREATE TABLE IF NOT EXISTS interview.comments
(
    comment_id integer NOT NULL DEFAULT nextval('interview.comments_comment_id_seq'::regclass),
    comment_datetime timestamp with time zone,
    comment_from character varying(255) COLLATE pg_catalog."default",
    comment_to character varying(255) COLLATE pg_catalog."default",
    message character varying(255) COLLATE pg_catalog."default",
    posted_by_userid integer,
    CONSTRAINT comments_pkey PRIMARY KEY (comment_id),
    CONSTRAINT fke0wmc7p8ssrw938du2upc0lar FOREIGN KEY (posted_by_userid)
        REFERENCES interview."user" (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS interview.comments
    OWNER to postgres;
	
II)Refer to TestingVideo folder for testing video	