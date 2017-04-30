import math
import time
import socket
import serial
x = True
while x:
    try:
        ser = serial.Serial("/dev/ttyUSB0")
        x = False
    except:
        pass
x = True
while x:
    try:
        sock = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
        x = False
    except:
        pass
sock.bind(('',99))
sock.listen(1)
x = sock.accept()
while True:
    ser.write(x[0].recv(5))