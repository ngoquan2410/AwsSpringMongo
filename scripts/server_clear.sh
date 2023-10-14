#!/usr/bin/env bash
sudo service codedeploy-agent stop
sudo service codedeploy-agent start
sudo rm -rf /home/ec2-user/AwsSpringMongo
