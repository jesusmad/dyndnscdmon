# dyndnscdmon
Small Java program that send a GET request to the cdmon Dynamic DNS update API if our server's IP has changed

# Prerequisites
Java

# Installing
Compile the .java file and configure a cron job

### To add a new cron job: 
```
crontab -e 
```
Cron job example, it runs the program every minute
```
#No mail will be sent
MAILTO=""
* * * * * cd /path/to/class && java DynIP
```

