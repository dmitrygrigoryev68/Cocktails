INSERT INTO USER(id, name)VALUES (1,'VASEA');
INSERT INTO photo(id,patch)VALUES (1,'google');
insert INTO ingredient(id,TITLE,description)VALUE (1,'Wodka','4 cl Wodka');
INSERT INTO cocktail_ingredients(cocktail_ingredients.Cocktail_id,cocktail_ingredients.ingredients_id)VALUES (1,1);
INSERT INTO cocktail_image(Cocktail_id,image_id)VALUES (1,1);
INSERT INTO cocktail(id,title,announce,publicationDate,cookingTime,prepTimeMinute,author_id)VALUES (1,'Sex on the Beach Cocktail Recipe','So I have to say one of the best drink''s I''ve drunk so far has become my favorite drink as well','2019-03-04',112,15,1);