#Yash Shah
#1001111714
#CSE 6331 Cloud Computing
#Assignment-2

import boto
import uuid
import time
from boto.s3.key import Key

#Reference :- https://github.com/awslabs/aws-python-sample/commit/d8d32eb83736e3103272b04958c791ea5ad471a8
AWS_ACCESS_KEY_ID = 'AKIAJ6UIPJCORLF3GRNA'
AWS_SECRET_ACCESS_KEY = 'Volx2ewl7UUgmi1k6FGsn5lM203+iOlKMVC2Zoa1'

bucket_name = "myawsbucket1-%s" % uuid.uuid4()
s3 = boto.connect_s3(AWS_ACCESS_KEY_ID,AWS_SECRET_ACCESS_KEY)

bucket = s3.create_bucket(bucket_name)

start_time = time.time()
k = Key(bucket)
k.key = 'all_month.csv'
k.set_contents_from_filename('/Users/yashshah792/Desktop/Cloud/Amazon Web Services/partial_month.csv')
stop_time = time.time()

print "Time take to upload the file" 
print stop_time - start_time