#!/bin/bash

echo "欢迎使用小王矿工工具"
# 显示选项给用户
echo "请选择一个选项:"
echo "1. qubic CPU模式安装"
echo "2. qubic CPU模式卸载"
echo "3. qubic CPU模式日志查看"


# 读取用户输入
read -p "请输入选项" choice

# 根据用户选择执行相应的操作
case $choice in
  1)
    echo "您选择了qubic CPU模式"
    # 更新你的数据源
	apt update
	#下载项目文件
	 wget -O qli-Service-install.sh https://dl.qubic.li/cloud-init/qli-Service-install.sh

	#设置权限
	chmod u+x qli-Service-install.sh

	# 获取用户输入的参数（第一次）
    read -p "请输入 线程数: " threads

	threads="${threads:-1}"

	echo "使用了默认线程数1"

    # 获取用户输入的参数（第二次）
    read -p "请输入 accessToken或者是payoutId: " access_token

    # 获取用户输入的参数（第三次，可选择不输入）
    read -p "请输入矿工名称: " alias_param

	 alias_param="${alias_param:-sdfdsf}"

	# 打印用户输入的参数
    echo "您输入的参数为: threads=$threads, access_token=$access_token, alias_param=$alias_param"




	# 构建参数字符串
    custom_params="$threads $access_token $alias_param"

    # install qubic.li client as systemd service with user-provided parameters
    ./qli-Service-install.sh $custom_params


	;;
  2)
    echo "您选择了qubic 卸载"

    # stop service
    systemctl stop qli --no-block

    # remove service definition
    rm /etc/systemd/system/qli.service

    # reload systemd
    systemctl daemon-reload

    # remove all related files
    rm -R /q
    rm /var/log/qli.log

    echo "qubic CPU模式已卸载"

    ;;
  3)
    echo "qubic CPU模式日志查看"

    # 查看日志
    tail -f /var/log/qli.log

    ;;
  *)
    echo "无效的选项"
    ;;
esac