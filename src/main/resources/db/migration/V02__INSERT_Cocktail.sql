INSERT INTO USER(id, name)
VALUES (1, 'https://www.cocktail-rezepte-4u.de/rezepte/Sex-on-the-Beach.html');
INSERT INTO photo(id, patch)
VALUES (1, 'google');
insert INTO ingredient(id, TITLE, description)
VALUES (1, 'Wodka', '4 cl Wodka'),
       (2, 'Peach liqueur', '4 cl peach liqueur'),
       (3, ' Cranberry', '8 cl cranberry juice or Cranberry Rectal'),
       (4, 'Orange juice', '8 cl of orange juice'),
       (5, 'Ice', 'Ice cubes');

INSERT INTO cocktail_ingredients(cocktail_ingredients.Cocktail_id, cocktail_ingredients.ingredients_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5);
INSERT INTO cocktail_image(Cocktail_id, image_id)
VALUES (1, 1);
INSERT INTO cocktail(id, title, announce, publicationDate, cookingTime, prepTimeMinute, author_id)
VALUES (1, 'Sex on the Beach Cocktail Recipe',
        'So I have to say one of the best drink''s I''ve drunk so far has become my favorite drink as well',
        '2019-03-04', 112, 15, 1);
