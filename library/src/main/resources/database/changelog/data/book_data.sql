insert into authors (first_name, middle_name, last_name) values
('James', 'Alexander', 'Smith'),
('Emily', 'Rose', 'Johnson'),
('Michael', 'Grace', 'Williams'),
('Sophia', 'Grace', 'Brown'),
('William', 'Thomas', 'Davis'),
('Olivia', 'Elizabeth', 'Taylor');

INSERT INTO books (id, name, isbn, genre, description) VALUES
(1, 'The Silent Forest', '978-3-16-148410-0', 'SCIENCE_FICTION', 
    'A haunting tale of a village surrounded by an ancient forest that holds dark secrets. The protagonist must unravel the mystery before the forest consumes them.'),
(2, 'Quantum Shadows', '978-1-23-456789-7', 'SCIENCE_FICTION',
    'In 2150, a rogue scientist discovers parallel dimensions where humanity never existed. But crossing dimensions has catastrophic consequences for reality.'),
(3, 'Whispers in the Library', '978-0-12-345678-9', 'MYSTERY',
    'When rare manuscripts start disappearing from the oldest library in Europe, a young curator uncovers a century-old conspiracy involving secret societies.'),
(4, 'Eternal Flames', '978-2-34-567890-1', 'ROMANCE',
    'Two star-crossed lovers from rival families in Renaissance Italy defy societal norms in this passionate historical romance filled with art and intrigue.'),
(5, 'Kings of Ashen Fields', '978-3-45-678901-2', 'HISTORICAL_FICTION',
    'Based on true events, this epic follows three generations of a noble family during the turbulent Wars of the Roses in 15th century England.');

INSERT INTO books_authors (book_id, author_id) VALUES
(1, 4),
(2, 4),
(3, 5),
(3, 2),
(4, 3),
(4, 4),
(5, 1), 
(5, 3), 
(5, 6); 