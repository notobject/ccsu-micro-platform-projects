#!/bin/bash

git_url=$1
dir_name=$2
project_name=$(echo "$git_url" | cut -d "/" -f5 | cut -d "." -f1)
build_path=/tmp/$project_name
if [ -d $build_path ];then
	rm -rf $build_path
fi
mkdir -p $build_path
echo "[build] created path: $build_path"
cd $build_path
echo "[build] pull repo from: $git_url"
git clone $git_url . &>/dev/null
echo "[build] pull done."
echo "[build] mvn install..."
mvn clean install -Dmaven.test.skip=true >> mvn_install.log
install_flag=$(cat mvn_install.log | grep "BUILD SUCCESS")
if [ -z "install_flag" ]
then
	tail -n 10 $build_path/mvn_install.log
	echo "[ERROR] -------------------------------------------------"
	echo "[build] faild. for more infomation at: $build_path/mvn_install.log"
else
	cd $build_path/$dir_name
	echo "[build] mvn package..."
    mvn clean package -Dmaven.test.skip=true -Pprod > mvn_package.log
    package_flag=$(cat mvn_package.log | grep "BUILD SUCCESS")
    if [ -z "package_flag" ]
    then
        tail -n 10 $build_path/mvn_package.log
	    echo "[ERROR] -------------------------------------------------"
	    echo "[build] faild. for more infomation at: $build_path/$dir_name/mvn_package.log"
    else
        echo "[build] success."
        cd target/
        echo $(pwd)/$(ls *.jar)
    fi
fi