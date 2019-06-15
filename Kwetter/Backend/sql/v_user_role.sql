-- NIEUW: 
-- create view
CREATE VIEW v_user_role AS
SELECT 
		user_role.users_ID, 
        user_role.roles_ID, 
        user.USERNAME, 
        user.PASSWORDHASH, 
        permission.NAME
FROM user_role
        inner join user on user_role.users_id = user.id
        inner join role on user_role.roles_id = role.id
        inner join role_permission on role_permission.roles_id = role.id
        inner join permission on role_permission.permissions_id = permission.id
GROUP BY user.USERNAME, permission.NAME;
-- select
SELECT 
		user_role.users_ID, 
        user_role.roles_ID, 
        user.USERNAME, 
        user.PASSWORDHASH, 
        permission.NAME
FROM user_role
        inner join user on user_role.users_id = user.id
        inner join role on user_role.roles_id = role.id
        inner join role_permission on role_permission.roles_id = role.id
        inner join permission on role_permission.permissions_id = permission.id
GROUP BY user.USERNAME, permission.NAME;

-- OUD:
-- view data
SELECT 
        `user_role`.`users_id` AS `users_ID`,
        `user_role`.`roles_id` AS `roles_ID`,
        `user`.`username` AS `USERNAME`,
        `user`.`passwordHash` AS `PASSWORDHASH`,
        `role`.`name` AS `NAME`
    FROM
        ((`user_role`
        JOIN `user` ON ((`user_role`.`users_id` = `user`.`id`)))
        JOIN `role` ON ((`user_role`.`roles_id` = `role`.`id`)));
-- create view
CREATE VIEW v_user_role AS
select user_role.users_ID, user_role.roles_ID, user.USERNAME,
user.PASSWORDHASH, role.NAME
from user_role inner join user on user_role.users_ID = user.ID
inner join role on user_role.roles_ID = role.ID;
-- select
select user_role.users_ID, user_role.roles_ID, user.USERNAME,
user.PASSWORDHASH, role.NAME
from user_role inner join user on user_role.users_ID = user.ID
inner join role on user_role.roles_ID = role.ID;