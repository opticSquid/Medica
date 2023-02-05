create database patients;
create database doctors;
create database authentications;
create database appointments;
GRANT ALL PRIVILEGES on patients.* to 'soumalya'@'%';
GRANT ALL PRIVILEGES on doctors.* to 'soumalya'@'%';
GRANT ALL PRIVILEGES on authentications.* to 'soumalya'@'%';
GRANT ALL PRIVILEGES on appointments.* to 'soumalya'@'%';
