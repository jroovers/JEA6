-- select relevant kweets
SELECT k.* FROM kweet k, followers f, user u WHERE k.author_id = f.follower AND f.following = u.id AND u.username = 'antonio';
-- select relevant kweets and your own
SELECT k.* FROM kweet k, followers f, user u WHERE k.author_id = f.follower AND f.following = u.id AND u.username = 'jeroen' UNION SELECT  k.* FROM kweet k, user u WHERE k.author_id = u.id AND u.username = 'jeroen' ORDER BY createdTime desc;
-- select following table
select * from followers;
-- select user id of people you are following
select f.follower from followers f where f.following = 11;
-- select user id of people being followed by people followed by you (potential mutual friends)
select * from
(select u.id, u.biography, u.location, u.name, u.username, u.website, count(f.follower) as nr from followers f, user u where f.follower != 11 and f.following in 
		(select f.follower from followers f where f.following = 11)
        and f.follower not in
        (select f.follower from followers f where f.following = 11)
        and u.id = f.follower
	GROUP By f.follower
    ORDER BY nr DESC) as q;

-- select same as above but sort by strongest bond (accurate mutual friends)
select f.follower as nr from followers f where f.follower != 11 and f.following in 
		(select f.follower from followers f where f.following = 11)
        and f.follower not in
        (select f.follower from followers f where f.following = 11)
	GROUP By f.follower
    ORDER BY count(f.follower) DESC;
-- select users with above query
select u.* from user u where (u.id, nr) in (
	select f.follower, count(f.follower) as nr 
	from followers f where f.follower != 11 and f.following in 
		(select f.follower from followers f where f.following = 11)
        and f.follower not in
        (select f.follower from followers f where f.following = 11)
	GROUP By f.follower
    ORDER BY nr DESC);

select u.*, q.count from user u where u.id in (select q.follower from (
select f.follower, count(f.follower) as nr from followers f where f.follower != 11 and f.following in 
		(select f.follower from followers f where f.following = 11)
        and f.follower not in
        (select f.follower from followers f where f.following = 11)
	GROUP By f.follower
    ORDER BY nr DESC
) as q);
    
select f.follower from followers f where f.following = 11; 
select distinct f.follower from followers f where f.following in
	(select f.follower from followers f where f.following = 11);
 
select u.* from user u;

SELECT u.id, u.username
FROM user u, followers f
WHERE u.id = f.follower AND f.following = 11;

select u.* FROM user u, followers f WHERE u.id = f.follower and f.following = u.id and u.username ="jeroen";