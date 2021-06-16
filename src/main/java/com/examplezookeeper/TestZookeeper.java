package com.examplezookeeper;

import org.apache.zookeeper.WatchDeregistration;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import java.io.IOException;

/**
 * @author guoyh
 */
public class TestZookeeper {
    private final String connectString = "39.106.63.189:2181";
    private int sessionTimeout = 2000;
    //ctrl + alt + f = 方法中变量升级为全局变量
    private ZooKeeper zkCli;

    @Test
    public void init() throws IOException {
        zkCli = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
    }
}
