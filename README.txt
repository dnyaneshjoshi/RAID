This file describes how to setup the environment and run the RAID simulation project

Step 1: Setting RAID drives
Create 12 folders (or insert 12 pendrives) which are used as disks inside RAID drives.
You can alternatively use the setup.sh script (located inside RaidProject folder) to create folders.
Run the script by typing
		sh setup.sh
in the terminal.

Step 2: Configuring the path of drives
Sample format of config.csv file is as follows:
		0,RAID0DRIVE,home/dj/PenDrives/PenDrive1,home/dj/PenDrives/PenDrive2
Replace the drives path with absolute path of folders/drives you created/inserted.
Alternatively you can run createconfig.sh (located inside RaidProject folder) to create config.csv file by typing
		sh createconfig.sh
in the terminal

Step 3: Running the project 
Run the project by typing 
java RaidSelection
in the Terminal

(To see RAID0 performance improvement demonstration, please run 'java Raid0PerformanceTester' in the terminal.
It may show more than 50% improvement sometimes, but theoritically it's not possible. It's only a result of function call overhead
in case of writeByte and readByte methods as they are called 100,000 times as opposed to writeByteArray and readByteArray which
are called only once. Block I/O may also play a part.)
