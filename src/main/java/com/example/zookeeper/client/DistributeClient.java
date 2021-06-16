package com.example.zookeeper.client;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @author guoyh
 */
public class DistributeClient {
    private ZooKeeper zkCli;
    String connectString = "127.0.0.1:2181";
    int sessionTimeout = 200000;

    public static void main(String[] args) {
        DistributeClient client = new DistributeClient();

        // 1、获取zookeeper连接
        client.getConnect();

        // 2、注册监听

        // 3、业务逻辑处理
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
