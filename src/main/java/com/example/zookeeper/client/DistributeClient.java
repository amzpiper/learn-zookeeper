package com.example.zookeeper.client;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guoyh
 */
public class DistributeClient {
    private ZooKeeper zkCli;
    String connectString = "0.0.0.0:2181";
    int sessionTimeout = 200000;

    public static void main(String[] args) {
        DistributeClient client = new DistributeClient();

        // 1、获取zookeeper连接
        client.getConnect();

        // 2、注册监听
        String application = "hadoop101";
        client.getChildren();

        // 3、业务逻辑处理
        client.business();
    }

    /**
     * 服务自己的业务处理
     */
    private void business() {
        try {
            //保证进程不结束
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册服务
     */
    private void getChildren() {
        try {
            List<String> children = zkCli.getChildren("/servers", true);
            // 存储服务器节点主机名称集合
            List<String> hostnames = new ArrayList<String>();

            System.out.println("------start------");
            for (String child : children) {
                byte[] data = zkCli.getData("/servers/" + child, false, null);
                hostnames.add(new String(data));
            }
            // 将所有在线主机名打印
            System.out.println(hostnames);
            System.out.println("------end------");
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
                    // 监听服务器上下线
                    getChildren();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
