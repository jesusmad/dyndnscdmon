# dyndnscdmon
Simple Java program to send a GET request to the cdmon Dynamic DNS update API

# Prerequisites
Java

# Installing
Compile the .java file and configure a cron job

To add a new cron job: 

crontab -e 

Cron job example, it runs the program every minute
```
MAILTO=""
* * * * * cd /path/to/class && java DynIP
```
MAILTO="" -> no mail will be sent
