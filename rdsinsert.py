#Yash Shah
#1001111714
#CSE 6331 Cloud Computing
#Assignment-2

import mysql.connector
import urllib
import csv
import time
# function to open  the url 
# Refrence :-  https://docs.python.org/2/library/urllib.html
opener = urllib.FancyURLopener({})

# Refrence :- http://dev.mysql.com/doc/connector-python/en/connector-python-example-connecting.html
conn = mysql.connector.connect(user='project2',
			 password='project2',
			 host='earthquakerecords.catdaxm0w3sn.us-west-2.rds.amazonaws.com',
             database = 'earthquakerecords')


s3file = opener.open("https://s3.amazonaws.com/myawsbucket1-9676955d-2ef3-4429-8ab4-3fa61a9d5cd0/all_month.csv")
#f.read()

# creating array where in data of each line will be taken in this array and then will be copied to rds.
eachline = []

#link of s3 file to get each line 1 at a time
# Refrence :-  https://docs.python.org/2/library/urllib.html
compfile=opener.open("https://s3.amazonaws.com/myawsbucket1-9676955d-2ef3-4429-8ab4-3fa61a9d5cd0/all_month.csv")
i = 1
starttime = time.time()
print starttime
while(compfile.readline!=''):
    try:
        line1 = str(compfile.readline())
        eachline = line1.split(",")
        time1 = eachline[0]
        latitude = eachline[1]
        longitude = eachline[2]
        depth = eachline[3]
        mag = eachline[4]
        magType = eachline[5]
        nst = eachline[6]
        gap = eachline[7]
        dmin = eachline[8]
        rms = eachline[9]
        net = eachline[10]
        id1 = eachline[11]
        updated = eachline[12]
        place = eachline[13]
        type1 = eachline[14]
        pid = str(i)
        i += 1
        query = """INSERT INTO project2 VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"""
        abc = conn.cursor()

        abc.execute(query,[pid,eachline[0],eachline[1], eachline[2],eachline[3],eachline[4],eachline[5],eachline[6],eachline[7],eachline[8],eachline[9],eachline[10],eachline[11], eachline[12],eachline[13],eachline[14]])
    conn.commit()
    except Exception, e:
        break
stoptime = time.time()
#abc.close()
print "Time take to upload the file" + str(stoptime - starttime)
conn.close()