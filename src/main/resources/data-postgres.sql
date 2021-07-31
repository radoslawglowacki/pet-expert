INSERT INTO role(id, name) VALUES (1, 'USER');
INSERT INTO role(id, name) VALUES (2, 'PROVIDER');
INSERT INTO role(id, name) VALUES (3, 'ADMIN');

INSERT INTO services(id, name) VALUES (1, 'VET');
INSERT INTO services(id, name) VALUES (2, 'BEH');
INSERT INTO services(id, name) VALUES (3, 'GRO');
INSERT INTO services(id, name) VALUES (4, 'HOT');

INSERT INTO address(id, city, local, number, street, zip) VALUES (1, 'Warszawa', '0', '1', 'Wiejska', '00010');
INSERT INTO address(id, city, local, number, street, zip) VALUES (2, 'Warszawa', '0', '2', 'Wiejska', '00010');
INSERT INTO address(id, city, local, number, street, zip) VALUES (3, 'Warszawa', '0', '3', 'Wiejska', '00010');
INSERT INTO address(id, city, local, number, street, zip) VALUES (4, 'Warszawa', '0', '4', 'Wiejska', '00010');
INSERT INTO address(id, city, local, number, street, zip) VALUES (5, 'Warszawa', '0', '5', 'Wiejska', '00010');
INSERT INTO address(id, city, local, number, street, zip) VALUES (6, 'Warszawa', '0', '6', 'Wiejska', '00010');
INSERT INTO address(id, city, local, number, street, zip) VALUES (7, 'Warszawa', '0', '7', 'Wiejska', '00010');

INSERT Into details(id, description, nip, phone, socials_id) VALUES (1, 'description one', 1234567890, 987654321, null);
INSERT Into details(id, description, nip, phone, socials_id) VALUES (2, 'description two', 1234567890, 987654321, null);
INSERT Into details(id, description, nip, phone, socials_id) VALUES (3, 'description three', 1234567890, 987654321, null);

-- Password Admin1234#
INSERT INTO users(id, email, enabled, first_name, last_name, locked, password, address_id, details_id) VALUES (1, 'admin@petexpert.pl', true , 'admin', 'admin', false, '$2a$10$uOe18TRjkAsG5cD9dcjh2OuQXGpBZiLddbx4IZEhWAfP6ldMr7xui', 1, 1);
INSERT INTO users(id, email, enabled, first_name, last_name, locked, password, address_id, details_id) VALUES (2, 'radek@petexpert.pl', true , 'Radek', 'Radek', false, '$2a$10$uOe18TRjkAsG5cD9dcjh2OuQXGpBZiLddbx4IZEhWAfP6ldMr7xui', 2, 2);
INSERT INTO users(id, email, enabled, first_name, last_name, locked, password, address_id, details_id) VALUES (3, 'dawid@petexpert.pl', true , 'Dawid', 'Dawid', false, '$2a$10$uOe18TRjkAsG5cD9dcjh2OuQXGpBZiLddbx4IZEhWAfP6ldMr7xui', 3, 3);

INSERT INTO company(id, description, name) VALUES(1, 'description', 'vet tani');
INSERT INTO company(id, description, name) VALUES(2, 'description', 'vet drogi');
INSERT INTO company(id, description, name) VALUES(3, 'description', 'beh cat');

INSERT INTO offers(id, city, created_at, description, driving_radius, driving_to_client, name, price, company_id, service_id) VALUES(1, 'Warszawa', current_date, 'description', 10, true, 'name offer 1', 100, 1, 1);
INSERT INTO offers(id, city, created_at, description, driving_radius, driving_to_client, name, price, company_id, service_id) VALUES(2, 'Warszawa', current_date, 'description', 10, true, 'name offer 2', 100, 1, 2);
INSERT INTO offers(id, city, created_at, description, driving_radius, driving_to_client, name, price, company_id, service_id) VALUES(3, 'Warszawa', current_date, 'description', 10, true, 'name offer 3', 100, 2, 1);
INSERT INTO offers(id, city, created_at, description, driving_radius, driving_to_client, name, price, company_id, service_id) VALUES(4, 'Warszawa', current_date, 'description', 10, true, 'name offer 3', 100, 3, 2);
INSERT INTO offers(id, city, created_at, description, driving_radius, driving_to_client, name, price, company_id, service_id) VALUES(5, 'Warszawa', current_date, 'description', 10, true, 'name offer 3', 100, 3, 3);

INSERT INTO company_addresses(company_id, addresses_id) VALUES(1,4);
INSERT INTO company_addresses(company_id, addresses_id) VALUES(2,5);
INSERT INTO company_addresses(company_id, addresses_id) VALUES(3,6);
INSERT INTO company_addresses(company_id, addresses_id) VALUES(3,7);

INSERT INTO company_offers(company_id, offers_id) VALUES(1, 1);
INSERT INTO company_offers(company_id, offers_id) VALUES(1, 2);
INSERT INTO company_offers(company_id, offers_id) VALUES(2, 3);
INSERT INTO company_offers(company_id, offers_id) VALUES(3, 4);
INSERT INTO company_offers(company_id, offers_id) VALUES(3, 5);

INSERT INTO users_companies(user_id, companies_id) VALUES(2, 1);
INSERT INTO users_companies(user_id, companies_id) VALUES(2, 2);
INSERT INTO users_companies(user_id, companies_id) VALUES(3, 3);

INSERT INTO users_roles(user_id, roles_id) VALUES(1,1);
INSERT INTO users_roles(user_id, roles_id) VALUES(1,2);
INSERT INTO users_roles(user_id, roles_id) VALUES(1,3);
INSERT INTO users_roles(user_id, roles_id) VALUES(2,1);
INSERT INTO users_roles(user_id, roles_id) VALUES(2,2);
INSERT INTO users_roles(user_id, roles_id) VALUES(2,3);
INSERT INTO users_roles(user_id, roles_id) VALUES(3,1);
INSERT INTO users_roles(user_id, roles_id) VALUES(3,2);
INSERT INTO users_roles(user_id, roles_id) VALUES(3,3);

INSERT INTO users_services(user_id, services_id) VALUES(1,1);
INSERT INTO users_services(user_id, services_id) VALUES(1,2);
INSERT INTO users_services(user_id, services_id) VALUES(1,3);
INSERT INTO users_services(user_id, services_id) VALUES(1,4);
INSERT INTO users_services(user_id, services_id) VALUES(2,1);
INSERT INTO users_services(user_id, services_id) VALUES(2,2);
INSERT INTO users_services(user_id, services_id) VALUES(2,3);
INSERT INTO users_services(user_id, services_id) VALUES(2,4);
INSERT INTO users_services(user_id, services_id) VALUES(3,1);
INSERT INTO users_services(user_id, services_id) VALUES(3,2);
INSERT INTO users_services(user_id, services_id) VALUES(3,3);
INSERT INTO users_services(user_id, services_id) VALUES(3,4);