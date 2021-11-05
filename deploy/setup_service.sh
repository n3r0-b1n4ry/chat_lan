mv ./chatServer.service /lib/systemd/system/chatServer.service
systemctl enable chatServer.service
systemctl daemon-reload
systemctl start chatServer.service
systemctl status chatServer.service