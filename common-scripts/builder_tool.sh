#!/bin/bash
#################################################################
# Author: Long Duping                                           #
# Date  : 2018-12-12                                            #
#                                                               #
#  说明：                                                       #
#      1. 自动拉取最新代码
#      2. 自动构建
#      3. 自动分发
################################################################# build
git_url=$1
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

echo "[build] start build..."
mvn package -Dmaven.test.skip=true -Pprod >> mvn.log

success_flag=$(cat mvn.log | grep "BUILD SUCCESS")

if [ -z "$success_flag" ] 
then
	tail -n 10 $build_path/mvn.log
	echo "[INFO] -------------------------------------------------"
	echo "[build] faild. for more infomation at: $build_path/mvn.log"
else
	echo "[build] success."

fi

################################################################## distribute
function distribute ()
{
    file=$2
    cd ./archive
    for line in $(cat /etc/server.conf | grep "^[^#]")
    do
        dq=$(echo $line | cut -d ":" -f1)
        group=$(echo $line | cut -d ":" -f2)
        user=$(echo $line | cut -d ":" -f3)
        passwd=$(echo $line | cut -d ":" -f4)
        address=$(echo $line | cut -d ":" -f5)

        if [[ $group == $1 ]];then
            echo "[distribute] $file to $address"

            # 调用分发工具
	    	scp_tool $address $user $passwd $file /home/app/$file &>/dev/null
            
            if [[ -f /tmp/$file.lock ]]
			then
				echo "[distribute] success."
				rm -rf /tmp/$file.lock
			else
				echo "[distribute] failed."
			fi
            
        fi
    done

    cd ../
}

# 归档文件
mkdir archive

for path in $(ls)
do
    #echo "check: $path"
    if [[ -d $path ]];then
		if [[ -n $(echo $path | grep '^ccsu-.*-server$') ]]
			then
			# echo -e "\t server."
			# 将 server 服务分发到基础服务集群
			filename=$(ls $path/target/*.jar | cut -d "/" -f3)
			cp $path/target/*.jar archive/

			distribute "G0" $filename	    
		elif [[ -n $(echo $path | grep '^ccsu-.*-service$') ]]
			then
			# echo -e "\t service."
			# 将 service 服务分发到业务服务集群
			filename=$(ls $path/target/*.jar | cut -d "/" -f3)
			cp $path/target/*.jar archive/
			distribute "G1" $filename	    
		
		# 其它文件夹不处理
		fi
    fi
done
# TODO 分发端口配置文件

echo "done."