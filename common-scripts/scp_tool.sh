#!/usr/bin/expect -f
#################################################################
# Author: Long Duping                                           #
# Date  : 2018-12-12                                            #
#                                                               #
#  说明：                                                       #
#  JAR 包自动分发工具
#  (该工具依赖 expect 组件: yum install -y expect )
#################################################################
set timeout -1
set host [lindex $argv 0]
set username [lindex $argv 1]
set passwd [lindex $argv 2]
set src_file [lindex $argv 3]
set dest_file [lindex $argv 4]
spawn scp $src_file $username@$host:$dest_file

expect {
    "(yes/no)?" {
        send "yes\n"
        expect "password:"
        send "$passwd\n"
    }
        "password:" {
        send "$passwd\n"
    }
 }
expect {
    "100%" {
	spawn touch /tmp/$src_file.lock
    }
}
expect eof
