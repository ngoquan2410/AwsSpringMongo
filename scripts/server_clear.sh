#!/usr/bin/env bash
#!/bin/bash
sudo yum install -y httpd
sudo service httpd stop
sudo rm -rf /home/ec2-user/AwsSpringMongo
