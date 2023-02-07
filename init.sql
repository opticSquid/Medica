create database if not exists patients;
create database if not exists doctors;
create database if not exists authentications;
create database if not exists appointments;
GRANT ALL PRIVILEGES on patients.* to 'soumalya'@'%';
GRANT ALL PRIVILEGES on doctors.* to 'soumalya'@'%';
GRANT ALL PRIVILEGES on authentications.* to 'soumalya'@'%';
GRANT ALL PRIVILEGES on appointments.* to 'soumalya'@'%';
