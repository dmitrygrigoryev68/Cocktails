create table cocktail
(
  cocktail_id               bigint auto_increment
    constraint `PRIMARY`
    primary key,
  cocktail_announce         varchar(255) null,
  cocktail_cooking_time     int          null,
  cocktail_prep_time_minute int          null,
  cocktail_publication_date datetime     null,
  cocktail_title            varchar(255) null,
  fk_user_id                bigint       null,
  fk_photo_id               bigint       null
)
  engine = MyISAM;

create index FK625gtyjwialrv6tt82nyhkpin
  on cocktail (fk_user_id);

create index FKjjv9bxdujnmn37ywqtuewwclj
  on cocktail (fk_photo_id);

create table cocktail_image
(
  cocktail_id bigint not null,
  image_id    bigint not null,
  constraint UK_61gxv2o7mf6kqw4twdpnan8l4
    unique (image_id)
)
  engine = MyISAM;

create index FKbdh739lygluvpi4cdc5r52ukg
  on cocktail_image (cocktail_id);

create table cocktail_ingredients
(
  fk_cocktail_id   bigint not null,
  fk_ingredient_id bigint not null
)
  engine = MyISAM;

create index FK6e1l1q0u6wadvikafq737fxye
  on cocktail_ingredients (fk_cocktail_id);

create index FKl6m8ayp44kg8o74afygwe9qe0
  on cocktail_ingredients (fk_ingredient_id);

create table flyway_schema_history
(
  installed_rank int                                 not null
    constraint `PRIMARY`
    primary key,
  version        varchar(50)                         null,
  description    varchar(200)                        not null,
  type           varchar(20)                         not null,
  script         varchar(1000)                       not null,
  checksum       int                                 null,
  installed_by   varchar(100)                        not null,
  installed_on   timestamp default CURRENT_TIMESTAMP not null,
  execution_time int                                 not null,
  success        tinyint(1)                          not null
);

create index flyway_schema_history_s_idx
  on flyway_schema_history (success);

create table ingredient
(
  ingredient_id          bigint auto_increment
    constraint `PRIMARY`
    primary key,
  ingredient_description varchar(255) null,
  ingredient_title       varchar(255) null,
  fk_ingredient_photo_id bigint       null
)
  engine = MyISAM;

create index FKso2eljjdt5fj5cu93hm8muhu2
  on ingredient (fk_ingredient_photo_id);

create table photo
(
  photo_id    bigint auto_increment
    constraint `PRIMARY`
    primary key,
  photo_alt   varchar(255) null,
  photo_path  varchar(255) null,
  photo_title varchar(255) null
)
  engine = MyISAM;

create table user
(
  user_id        bigint auto_increment
    constraint `PRIMARY`
    primary key,
  user_loginname varchar(255) null
)
  engine = MyISAM;

