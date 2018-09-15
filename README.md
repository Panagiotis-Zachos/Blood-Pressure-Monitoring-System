# Blood-Pressure-Monitoring-System

An application where a patient can send blood pressure data to a remote server using a Raspberry Pi fitted with a sensor, and can view said data from his computer.

The server can accept 10 measurments and/or 10 clients reading their data at a time. In it's current state the application only runs when the server, the client and the measurment application are all executed on the localhost. If you want to run them remotely you need to change the IP Address the client and the measurment application send their data in the code. In a future version you will be able to change a .txt file.

To run the code it is necessary to include the gson (v2.6.2) library in all 3 projects.
