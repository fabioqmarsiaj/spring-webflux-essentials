set session my.number_of_animes = '1';

INSERT INTO anime
select id, concat('Hellsing ', id)
FROM GENERATE_SERIES(1, current_setting('my.number_of_animes')::int) as id;