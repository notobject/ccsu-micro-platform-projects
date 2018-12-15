#!/bin/bash
#################################################################
# Author: Long Duping                                           #
# Date  : 2018-12-15                                            #
#                                                               #
#  说明：                                                       #
#  1. 定时检测推送过来的Jar包（配置cron任务，每3分钟执行一次）
#  2. 自动构建镜像
#  3. 自动运行容器
#  4. 清除旧版容器和镜像
#################################################################

cd ~

if [[ -z $(ls *.jar) ]];then
	exit 0
fi

for file in $(ls *.jar)
do
	echo "find: $(pwd)/$file"

    # 提取服务名和版本信息
	prefix=$(echo $file | cut -d "-" -f1) #ccsu
	name=$(echo $file | cut -d "-" -f2)   #config
	s_type=$(echo $file | cut -d "-" -f3)   #server
	major_v=$(echo $file | cut -d "-" -f4 | cut -d "." -f1) #1
	minor_v=$(echo $file | cut -d "-" -f4 | cut -d "." -f2) #0
	build_v=$(echo $file | cut -d "-" -f4 | cut -d "." -f3) #0
	if [[ -n $(echo $file | grep "RELEASE") ]]
		then 
			v_type=$(echo $file | cut -d "-" -f4 | cut -d "." -f4) #RELEASE
			suffix=$(echo $file | cut -d "-" -f4 | cut -d "." -f5) #jar
		else
			v_type=$(echo $file | cut -d "-" -f5 | cut -d "." -f1) #SNAPSHOT
			suffix=$(echo $file | cut -d "-" -f5 | cut -d "." -f2) #jar
	fi
	echo "$name-$s_type:$major_v.$minor_v.$build_v.$v_type"
	
	workdir=$(echo $prefix-$name-$s_type)
	if [[ -d $workdir ]];then
		rm -rf $workdir
	fi
	mkdir $workdir
	cp -f $file $workdir

    # 解析 ports.conf
	port=""
	if [[ -f "~/ports.conf" ]];then
		for line in $(cat ~/ports.conf | grep "^[^#]")
		do
			if [[ $(echo $line | cut -d ":" -f1) == $(echo $prefix-$name-$s_type) ]];then
				port=$(echo $line | cut -d ":" -f2)
				echo "$prefix-$name-$s_type 已配置端口: $port"
			fi
		done
	fi

	# 生成一个随机端口(50000 ~ 60000)
	while [[ $port == "" ]]
	do
		port=$(($RANDOM%10000+50000))
		# 检查端口是否可用
		if [[ $(lsof -i:$port) != "" ]];then
			port=""
		fi
	done
	echo "$prefix-$name-$s_type port= $port"

    # 本机地址
	export HOST_ADDRESS="192.168.0.100"
	# 注册中心地址
	export REGISTER_ADDRESS="http://serverb1.notobject.com:8761/eureka/,http://serverb2.notobject.com:8761/eureka/"
	echo "[build] prepare."

    # 生成 Dockerfile
	echo "FROM docker.io/lwieske/java-8" >> $workdir/Dockerfile
	echo "ENV HOST_ADDRESS $HOST_ADDRESS" >> $workdir/Dockerfile
	echo "ENV REGISTER_ADDRESS $REGISTER_ADDRESS" >> $workdir/Dockerfile
	echo "COPY $file /app.jar" >> $workdir/Dockerfile
	echo "EXPOSE $port" >> $workdir/Dockerfile
	echo "ENTRYPOINT [\"java\",\"-jar\",\"/app.jar\"]" >> $workdir/Dockerfile
	echo "CMD [\"--eureka.instance.ip-address=$HOST_ADDRESS\",\"--eureka.client.service-url.defaultZone=$REGISTER_ADDRESS\",\"--server.port=$port\"]" >> $workdir/Dockerfile

	# 构建镜像
	echo "[build] start."	
	image_name=$(echo "$prefix-$name-$s_type:$major_v.$minor_v.$build_v.$v_type")
	if [[ -n $(echo $(docker build -t $image_name $workdir) | grep "Successfully built") ]]
	    then
		    echo "[build] Successfully."
		else
		    echo "[build] Failed."
	fi

	# 运行容器
	echo "[run] start."
	container_name=$(echo "$prefix-$name-$s_type-$major_v.$minor_v.$build_v.$v_type")
	docker run -d --name $container_name --restart=always -p $port:$port -t $image_name

	# TODO 运行前检查: 有旧版，端口被占用
	# TODO 停止旧版容器
	# TODO 删除旧版镜像
done
