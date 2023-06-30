INSERT INTO users (login, password, avatar, access_rights)
VALUES ('admin',
        '12345',
        'noImage.png',
        0),
       ('user',
        '12345',
        'noImage.png',
        1);

INSERT INTO sources (title)
VALUES ('Comics'),
       ('Movie'),
       ('Book'),
       ('Video game'),
       ('cartoon');

INSERT INTO planets (title)
VALUES ('Xenomorph Prime'),
       ('Furia'),
       ('Krypton'),
       ('K-PAX'),
       ('Vulcan'),
       ('TarKossia'),
       ('Kweltikwan');

INSERT INTO abilities (title)
VALUES ('Enhanced Strength and Agility'),
       ('Telekinesis'),
       ('Acidic Blood'),
       ('Regeneration'),
       ('Light manipulation'),
       ('Elasticity'),
       ('Night vision');

INSERT INTO aliens (name, description, logo, full_size_logo, first_appearance, ability_id, planet_id)
VALUES ('Alien', 'The Xenomorph XX121[3], better known just as Xenomorph or the "Alien", is an extraterrestrial hive-based endoparasitoid with a multi-staged life cycle.
                  One of the deadliest and most hostile of all known alien species, these creatures require a host organism in order to reproduce.',
        'xeno.jpg', 'xeno_full.jpg', 1979, 3, 1),
       ('Riddick', 'Richard B. Riddick is the titular main protagonist of The Chronicles of Riddick franchise. Although occasionally steps up to rise above himself,
                    he is in general an amoral criminal who gets extremely cynical about anything and everything that does not benefit him.',
        'riddick.jpg', 'riddick_full.jpg', 2000, 7, 2),
       ('Superman', 'Superman is a fictional character and one of the most famous and popular comic book superheroes of all time.
                     Created by American writer Jerry Siegel and Canadian-born artist Joe Shuster in 1932 while both were growing up in Cleveland, Ohio, and sold to Detective Comics Inc.',
        'super.jpg', 'super_full.jpg', 1938, 1, 3),
       ('Prot', 'After claiming he is an extraterrestrial from the planet K-PAX, 1,000 light years away in the Lyra constellation, Prot (not capitalized and pronounced with a long O, rhyming with boat)
                 is committed to the Psychiatric Institute of Manhattan.',
        'prot.jpg', 'prot_full.jpg', 1995, 5, 4),
       ('Darth Vader', 'Before he became a disciple of the dark side, Darth Vader was Anakin Skywalker, a goodhearted Jedi and hero of the Clone Wars. While he was considered one of the most powerful Jedi in the galaxy,
                    Anakin had broken the Order''s code by secretly marrying Senator Padmé Amidala.',
        'vader.jpg', 'vader_full.jpg', 1977, 2, 5),
       ('Sarah Kerrigan', 'Sarah Louise Kerrigan was a psychic terran female. She began her career as a Confederate ghost and later became the second-in-command of the Sons of Korhal. Following Arcturus Mengsk''s betrayal,
                           she was captured and infested by the Zerg Swarm, becoming the self-proclaimed Queen of Blades (a.k.a. the Zerg Queen) and leader of the Swarm',
        'kerrigan.jpg', 'kerrigan_full.jpg', 1998, 2, 6),
       ('Stitch', '"Experiment 626" was created by "evil genius" Dr. Jumba Jookiba to cause and create chaos around the galaxy.',
        'stitch.jpg', 'stitch_full.jpg', 2002, 4, 7);

INSERT INTO comments (user_id, alien_id, comment, publication_date)
VALUES (1, 1 ,'Text of Comment', DEFAULT),
       (1, 2 ,'Text of Comment', DEFAULT),
       (2, 3 ,'Text of Comment', DEFAULT),
       (2, 4 ,'Text of Comment', DEFAULT),
       (1, 5 ,'Text of Comment', DEFAULT),
       (1, 6 ,'Text of Comment', DEFAULT),
       (2, 7 ,'Text of Comment', DEFAULT),
       (2, 5 ,'Text of Comment', DEFAULT),
       (1, 6 ,'Text of Comment', DEFAULT),
       (1, 4 ,'Text of Comment', DEFAULT),
       (2, 3 ,'Text of Comment', DEFAULT);

INSERT INTO viewed (user_id, alien_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (2, 4),
       (1, 5),
       (1, 6),
       (2, 7),
       (2, 6),
       (1, 3),
       (1, 4),
       (2, 1),
       (2, 2);

INSERT INTO alien_abilities(alien_id, ability_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (3, 4),
       (3, 7),
       (4, 5),
       (5, 1),
       (5, 2),
       (6, 1),
       (6, 2),
       (7, 1),
       (7, 6);