touch config2.csv
echo "0,RAID0DRIVE,`pwd`/Pendrives/Pendrive9,`pwd`/Pendrives/Pendrive10">>config2.csv
echo "1,RAID1DRIVE,`pwd`/Pendrives/Pendrive11,`pwd`/Pendrives/Pendrive12">>config2.csv
echo "2,RAID10DRIVE,RAID1CONFIGURATION_1_INSIDE_RAID10DRIVE,RAID1CONFIGURATION_2_INSIDE_RAID10DRIVE">>config2.csv
echo "1,RAID1CONFIGURATION_1_INSIDE_RAID10DRIVE,`pwd`/Pendrives/Pendrive5,`pwd`/Pendrives/Pendrive6">>config2.csv
echo "1,RAID1CONFIGURATION_2_INSIDE_RAID10DRIVE,`pwd`/Pendrives/Pendrive7,`pwd`/Pendrives/Pendrive8">>config2.csv
echo "3,RAID01DRIVE,RAID0CONFIGURATION_1_INSIDE_RAID01DRIVE,RAID0CONFIGURATION_2_INSIDE_RAID01DRIVE">>config2.csv
echo "0,RAID0CONFIGURATION_1_INSIDE_RAID01DRIVE,`pwd`/Pendrives/Pendrive1,`pwd`/Pendrives/Pendrive2">>config2.csv
echo "0,RAID0CONFIGURATION_2_INSIDE_RAID01DRIVE,`pwd`/Pendrives/Pendrive3,`pwd`/Pendrives/Pendrive4">>config2.csv
sed 's/\/home/home/g' config2.csv >config.csv
rm config2.csv
