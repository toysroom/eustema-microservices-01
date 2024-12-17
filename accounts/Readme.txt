INSERT INTO customers (customer_id, name, email, mobile_number, created_at, created_by, updated_at, updated_by) VALUES
(1, 'pippo', 'pippo@pluto.it', '353446456', , 'admin', NULL, 'admin');


h2
Spring jpa


accountsdb


		accounts (L customer_id, L account_number, S account_type, S branchAddress)
		customers (L id, S firstname, S lastname, S email, S mobile_number)
		
	Hibernate (ORM)
	
		Account -> accounts
		Customer -> customers
		
	AccountContoller
	CustomerController