# Payara 5.184 kwetter configuration

- [Payara 5.184 kwetter configuration](#payara-5184-kwetter-configuration)
  - [Copy MySQL 8 driver](#copy-mysql-8-driver)
  - [Configure MySQL8 connection pool](#configure-mysql8-connection-pool)
    - [Additional Properties](#additional-properties)
- [| Property | Value](#-property--value)
  - [Configure kwetter JDBC resource](#configure-kwetter-jdbc-resource)
  - [Set up security Realm](#set-up-security-realm)

## Copy MySQL 8 driver

Kwetter uses MySQL 8 which is not supported by Payara out-of-the-box. Grab the mysql-connector-java-8.0.15.jar (the version matters.) and put it in your payara 5.184 domain folder. For example `C:\users\jeroen\payara-5.184\glassfish\domains\domain1\lib\mysql-connector-java-8.0.15.jar`.

## Configure MySQL8 connection pool

Create a connection pool in payara console:

**Pool Name:** MySQLPool  
**Resource Type:** javax.sql.DataSource  
**Datasource Classname:** com.mysql.cj.jdbc.MysqlDataSource  
**Driver Classname:** *null*  

### Additional Properties

You'll need to set additional properties in the connection pool for payara to be able to connect to MySQL 8

| #   | Property        | Value
| --- | ------------    | ---
| 1   | useSSL          | false
| 2   | useTimezone     | true
| 3   | serverTimezone  | CET
| 4   | serverName      | localhost
| 5   | portNumber      | 3306
| 6   | databaseName    | kwetter or your schema name
| 7   | user            | username for schema access
| 8   | password        | password for schema access

## Configure kwetter JDBC resource

After making a resouce pool, create a JDBC resource with the following settings:

**JNDI Name:** jdbc/kwetter  
**Pool Name:** MySQLPool

## Set up security Realm

*Finally* we can set up a security realm for the app to authenticate uses against... In the Payara console go to:

> server-config > Security > Realms

And create a new realm called `	KwetterSecRealm` (the name matters.) All settings are as follows:

**Configuration Name:** server-config  
**Realm Name:** KwetterSecRealm  
**Class Name:** 
com.sun.enterprise.security.auth.realm.jdbc.JDBCRealm  

Additional properties are below. Any not mentioned are not set:

Property | Value
--- | --- 
JAAS Context | jdbcRealm
JNDI | jdbc/kwetter (same as jdbc resource)
User Table | user
User Name Column | USERNAME
Password Column | PASSWORDHASH
Group Table | v_user_role (create script in sql folder)
Group Table User Name Column | USERNAME
Group Name Column | NAME
Digest Algorithm | none
