/*
INSERT INTO client (id, name, surname, document, telephone, email, password, role, creation_date, modification_date, deleted)

                   VALUES

                   (UUID(), 'Hernán', Costarelli, '26838582', '2614706825', 'admin@admin.com',
                   '$2a$12$i3J/MhV.AZOroIzUXrcIouPobU9wesrEPa76eA7nIN5dnGThhiZF6', 'ADMIN', current_timestamp, current_timestamp, 0)

                   ON DUPLICATE KEY UPDATE
                   name = 'Hernán',
                   surname = 'Costarelli',
                   document = '26838582',
                   telephone = '2614706825',
                   email = 'admin@admin.com',
                   password = '$2a$12$i3J/MhV.AZOroIzUXrcIouPobU9wesrEPa76eA7nIN5dnGThhiZF6',
                   role = 'ADMIN',
                   creation_date = current_timestamp,
                   modification_date = current_timestamp,
                   deleted = 0;*/

                   REPLACE INTO client (id, name, surname, document, telephone, email, password, role, creation_date, modification_date, deleted)

                   VALUES

                   ('2c0a9829-97f7-11ed-b9f3-7c10c9866f8b', 'Hernán', 'Costarelli', '26838582', '2614706825', 'admin@admin.com',
                   '$2a$12$i3J/MhV.AZOroIzUXrcIouPobU9wesrEPa76eA7nIN5dnGThhiZF6', 'ADMIN', current_timestamp, current_timestamp, 0);
