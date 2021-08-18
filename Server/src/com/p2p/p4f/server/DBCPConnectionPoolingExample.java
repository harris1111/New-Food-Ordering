package com.p2p.p4f.server;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;


public class DBCPConnectionPoolingExample {

    private static final int NUMBER_OF_USERS = 5;

    public static void main(String[] args) throws SQLException, InterruptedException {
        final CountDownLatch latch = new CountDownLatch(NUMBER_OF_USERS);
        for (int i = 1; i <= NUMBER_OF_USERS; i++) {
            String[] w = {"getBranch", "ngocchinh", "12321"};
            Thread worker = new DBHandler(latch, "" + i, w);
            worker.start();
        }
        latch.await();
        System.out.println("DONE All Tasks");
    }
}