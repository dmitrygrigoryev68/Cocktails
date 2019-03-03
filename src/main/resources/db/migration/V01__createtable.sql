create table cocktails
(
  cocktails_id     bigint auto_increment
    primary key,
  announce         varchar(3000) null,
  cooking_time     int           null,
  prep_time_minute int           null,
  publication_date datetime      not null,
  title            varchar(255)  null,
  user_id          bigint        null
)
  engine = MyISAM;

create index FKhvesmdy030q8x8y18840up5ju
  on cocktails (user_id);

create table cocktails_image
(
  cocktail_cocktails_id bigint not null,
  image                 bigint not null
)
  engine = MyISAM;

create index FKjtq3ie937yikrl5pnmn6kljjh
  on cocktails_image (cocktail_cocktails_id);

create index FKnl5podlf3hgdeleaigq2a2ua7
  on cocktails_image (image);

create table cocktails_ingredients
(
  cocktail_cocktails_id bigint not null,
  ingredients           bigint not null
)
  engine = MyISAM;

create index FKhcgdaj6neu2yl7tpv5vvc8yoq
  on cocktails_ingredients (ingredients);

create index FKowuuc49l12pp2flwwqlp2eekl
  on cocktails_ingredients (cocktail_cocktails_id);

create table ingredient
(
  ingredient_id bigint auto_increment
    primary key,
  description   varchar(3000) not null,
  name          varchar(3000) not null

)
  engine = MyISAM;

create table photo
(
  photo_id bigint auto_increment
    primary key,
  patch    varchar(5000) not null
)
  engine = MyISAM;

create table user
(
  user_id bigint auto_increment
    primary key,
  name    varchar(255) not null
)
  engine = MyISAM;

