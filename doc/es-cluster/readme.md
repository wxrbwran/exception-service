报错: max virtual memory areas vm.max_map_count [65530] is too low, increase to at least [262144]

解决:

在 /etc/sysctl.conf 追加最大虚拟空间限制 vm.max_map_count=655360 ，如下，记得 sysctl -p 使系统配置生效。
