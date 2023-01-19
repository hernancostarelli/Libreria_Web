/*
INSERT INTO client (id, name, surname, document, telephone, email, password, role, creation_date, modification_date, deleted)

                   VALUES

                   (UUID(), 'Name', 'Surname', '12345678', '2613333333', 'admin@library.org',
                   '$2a$12$i3J/MhV.AZOroIzUXrcIouPobU9wesrEPa76eA7nIN5dnGThhiZF6', 'ADMIN', current_timestamp, current_timestamp, 0)

                   ON DUPLICATE KEY UPDATE
                   name = 'Name',
                   surname = 'Surname',
                   document = '12345678',
                   telephone = '2613333333',
                   email = 'admin@library.org',
                   password = '$2a$12$i3J/MhV.AZOroIzUXrcIouPobU9wesrEPa76eA7nIN5dnGThhiZF6',
                   role = 'ADMIN',
                   creation_date = current_timestamp,
                   modification_date = current_timestamp,
                   deleted = 0;*/

                   REPLACE INTO client (id, name, surname, document, telephone, email, password, role, creation_date, modification_date, deleted)

                   VALUES

                   ('2c0a9829-97f7-11ed-b9f3-7c10c9866f8b', 'Name', 'Surname', '12345678', '2613333333', 'admin@library.org',
                   '$2a$12$i3J/MhV.AZOroIzUXrcIouPobU9wesrEPa76eA7nIN5dnGThhiZF6', 'ADMIN', current_timestamp, current_timestamp, 0);
