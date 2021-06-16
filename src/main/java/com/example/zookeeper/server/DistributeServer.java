package com.example.zookeeper.server;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @author guoyh
 */
public class DistributeServer {
    private ZooKeeper zkCli;
    String connectString = "127.0.0.1:2181";
    int sessionTimeout = 200000;

    public static void main(String[] args) {
        DistributeServer server = new DistributeServer();

        // 1、连接Zookeeper集群
        server.getConnect();

        // 2、注册创建临时节点
        // 设置服务器名称
        String application = "hadoop101";
        server.regist(application);

        // 3、自己的业务逻辑代码处理
        server.business();
    }

    /**
     * 服务自己的业务处理
     */
    private void business() {
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册服务
     * @param hostname
     */
    private void regist(String hostname) {
        try {
            //创建短暂带序号的子节点
            String path = zkCli.create("/servers/server", hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(hostname + "is online");
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Zookeeper连接
     */
    private void getConnect() {
        try {
            zkCli = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
                //监听回调
                @Override
                public void process(WatchedEvent watchedEvent) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
