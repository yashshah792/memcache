#Yash Shah
#1001111714
#CSE 6331 Cloud Computing
#Assignment-2

Read Me:

The program that uploads the file to amazon s3 is copyfile.py and the screemshot that shows time is copyfilescreenshot.png

The program that insert the file to RDS Mysql is rdsinsert.py and the screenshot that shows time is rdsinsertscreenshot.png

The program that runs the queries is a java program and its main class is amazon
-> The fucntion connectionfunt() is called to execute 2000 random queries and the scrrenshot showing this is Random_Query_SC.png
-> The fucntion randomquery() is called to execute 2000 limited scope queries and the scrrenshot showing this is Limited_Scope_SC.png
-> The fucntion memrandomfunt() is called to execute 2000 random queries with memcache
	The logic employed is correct but the libraries that are used to implement the logic is having some errors
	The errors that are encontered are:
	"22:00:10.456 [main] ERROR com.meetup.memcached.SockIOPool - ++++ failed to get SockIO obj for: 127.0.0.1:11211
	 22:00:10.459 [main] ERROR com.meetup.memcached.SockIOPool - Connection refused
	 java.net.ConnectException: Connection refused"