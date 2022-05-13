package com.github.lwb2021.myblog.common.util;


import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;

public class SnowflakeIDWorker {
    private final static long START_STMP = 1651488711794L;

    private final static long SEQUENCE_BIT = 10;
    private final static long MACHINE_BIT = 5;
    private final static long DATACENTER_BIT = 5;

    public final static long MAX_DATACENTER_NUM = ~(-1L << DATACENTER_BIT);
    public final static long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);

    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;
    private final long datacenterId;
    private final long machineId;
    private long sequence = 0L;
    private long lastStmp = -1L;

    private static final SnowflakeIDWorker instance = new SnowflakeIDWorker();

    public static SnowflakeIDWorker getInstance() {
        return instance;
    }

    private SnowflakeIDWorker() {
        long datacenterId = getDatacenterId();
        long machineId = getMachineId(datacenterId);
        check(datacenterId, machineId);
        this.datacenterId = datacenterId;
        this.machineId = machineId;
    }


    private SnowflakeIDWorker(long datacenterId, long machineId) {
        check(datacenterId, machineId);
        this.datacenterId = datacenterId;
        this.machineId = machineId;
    }

    private static void check(long datacenterId, long machineId) {
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new RuntimeException(String.format("datacenterId can't be greater than %s or less than 0",
                    MAX_DATACENTER_NUM));
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new RuntimeException(String.format("machineId can't be greater than %s or less than 0",
                    MAX_MACHINE_NUM));
        }
    }

    public synchronized long nextID() {
        long currStmp = getNewstmp();
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards!!!");
        }
        if (currStmp == lastStmp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;

            if (sequence == 0L) {
                currStmp = getNextMill();
            }
        } else {

            sequence = 0L;
        }
        lastStmp = currStmp;
        return (currStmp - START_STMP) << TIMESTMP_LEFT
                | datacenterId << DATACENTER_LEFT
                | machineId << MACHINE_LEFT
                | sequence;
    }

    private long getNextMill() {
        long mill = getNewstmp();
        while (mill <= lastStmp) {
            mill = getNewstmp();
        }
        return mill;
    }


    private long getNewstmp() {
        return System.currentTimeMillis();
    }


    private static long getMachineId(long datacenterId) {
        StringBuilder mpid = new StringBuilder();
        mpid.append(datacenterId);
        String name = ManagementFactory.getRuntimeMXBean().getName();
        if (!name.isEmpty()) {

            mpid.append(name.split("@")[0]);
        }

        return (mpid.toString().hashCode() & 0xffff) % (SnowflakeIDWorker.MAX_MACHINE_NUM + 1);
    }

    private static long getDatacenterId() {
        long id = 0L;
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            if (network == null) {
                id = 1L;
            } else {
                byte[] mac = network.getHardwareAddress();
                id = ((0x000000FF & (long) mac[mac.length - 1])
                        | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
                id = id % (Long.MAX_VALUE + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
}
