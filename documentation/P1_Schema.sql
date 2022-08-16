DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS reimbursements;

CREATE TABLE users (
	user_id SERIAL,
	first_name VARCHAR(200) NOT NULL,
	last_name VARCHAR(200) NOT NULL,
	user_name VARCHAR(200) NOT NULL UNIQUE,
	email VARCHAR(200) NOT NULL,
	"password" VARCHAR(200) NOT NULL,
	CONSTRAINT users_pk PRIMARY KEY (user_id) /*We could also simply put "PRIMARY KEY" on the user_id definition*/
);


CREATE TABLE reimbursements (
	reimbursement_id SERIAL PRIMARY KEY,
	reimbursement_type VARCHAR(60) NOT NULL,
	date_of_request VARCHAR(10),
	amount DECIMAL(10,2) NOT NULL,
	"comment" VARCHAR(2000),
	status_of_request VARCHAR(20) NOT NULL,
	user_id INT,
	CONSTRAINT reimbursements_users_fk FOREIGN KEY (user_id) REFERENCES users(user_id)
);
