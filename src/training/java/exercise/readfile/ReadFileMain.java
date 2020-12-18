/*
 * ReadFileMain
 *
 * Version 1.0
 *
 * 12/14/2020
 *
 * Copyright thang-tran
 */
package training.java.exercise.readfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class ReadFileMain {
    public static void main(String[] args) {
        // Create array of files
        FileInfo[] fileInfos = new FileInfo[10];
        fileInfos[0] = new FileInfo("/var/admage/log/file1.log", false);
        fileInfos[1] = new FileInfo("/var/admage/log/file2.log", false);
        fileInfos[2] = new FileInfo("/var/admage/log/file3.log", false);
        fileInfos[3] = new FileInfo("/var/admage/log/file4.log", false);
        fileInfos[4] = new FileInfo("/var/admage/log/file5.log", false);
        fileInfos[5] = new FileInfo("/var/admage/log/file6.log", false);
        fileInfos[6] = new FileInfo("/var/admage/log/file7.log", false);
        fileInfos[7] = new FileInfo("/var/admage/log/file8.log", false);
        fileInfos[8] = new FileInfo("/var/admage/log/file9.log", false);
        fileInfos[9] = new FileInfo("/var/admage/log/file10.log", false);

        // Get callable return
        List<Future<List<String>>> list = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Callable<List<String>> callable;
        for (int i = 0; i < fileInfos.length; i++) {
            callable = new ReadFileThread(fileInfos[i]);
            Future<List<String>> future = executorService.submit(callable);
            list.add(future);
        }

        // Add all to lineList
        List<String> lineList = new ArrayList<>();
        for (Future<List<String>> f : list) {
            try {
                lineList.addAll(f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        }
        executorService.shutdown();

        // Split string line and add to LogInfo object
        List<LogInfo> logInfoList = new ArrayList<>();
        for (String item : lineList) {
            String[] stringLine = item.split("\t");
            int adId = Integer.parseInt(stringLine[0]);
            int siteId = Integer.parseInt(stringLine[1]);
            int logType = Integer.parseInt(stringLine[2]);
            int cost = Integer.parseInt(stringLine[3]);
            int wholesale = Integer.parseInt(stringLine[4]);
            LogInfo logInfo = new LogInfo(adId, siteId, logType, cost, wholesale);
            System.out.println("ad_id: " +logInfo.getAdId() + ", " + "site_id: " +logInfo.getSiteId() + ", " + "cost: " +logInfo.getCost() + ", " + "wholesale: " +logInfo.getWholesale());
            logInfoList.add(logInfo);
        }

        // Statistic cost with adId
        Map<Integer, Integer> costAdId =  new HashMap<>();
        for (LogInfo item : logInfoList) {
            Boolean check = costAdId.containsKey(item.getAdId());

            // Check exist adId in map
            if (check) {
                int cost = costAdId.get(item.getAdId()) + item.getCost();
                costAdId.replace(item.getAdId(), cost);
            } else {
                costAdId.put(item.getAdId(), item.getCost());
            }
        }

        // Statistic wholesale with adId
        Map<Integer, Integer> wholesaleAdId =  new HashMap<>();
        for (LogInfo item : logInfoList) {
            Boolean check = wholesaleAdId.containsKey(item.getAdId());

            // Check exist adId in map
            if (check) {
                int wholesale = wholesaleAdId.get(item.getAdId()) + item.getWholesale();
                wholesaleAdId.replace(item.getAdId(), wholesale);
            } else {
                wholesaleAdId.put(item.getAdId(), item.getWholesale());
            }
        }

        // Print
        System.out.println("\nAdId  " + "  Cost  " + "  Wholesale");
        costAdId.forEach((p, n) -> System.out.format("%d       %d$       %d$\n", p, n, wholesaleAdId.get(p)));

        // Statistic cost with siteId
        Map<Integer, Integer> costSiteId =  new HashMap<>();
        for (LogInfo item : logInfoList) {
            Boolean check = costSiteId.containsKey(item.getSiteId());

            // Check exist siteId in map
            if (check) {
                int cost = costSiteId.get(item.getSiteId()) + item.getCost();
                costSiteId.replace(item.getSiteId(), cost);
            } else {
                costSiteId.put(item.getSiteId(), item.getCost());
            }
        }

        // Statistic wholesale with siteId
        Map<Integer, Integer> wholesaleSiteId =  new HashMap<>();
        for (LogInfo item : logInfoList) {
            Boolean check = wholesaleSiteId.containsKey(item.getSiteId());

            // Check exist siteId in map
            if (check) {
                int wholasale = wholesaleSiteId.get(item.getSiteId()) + item.getWholesale();
                wholesaleSiteId.replace(item.getSiteId(), wholasale);
            } else {
                wholesaleSiteId.put(item.getSiteId(), item.getWholesale());
            }
        }

        // Print
        System.out.println("\nSiteId  " + "Cost  " + "  Wholesale");
        costSiteId.forEach((p, n) -> System.out.format("%d       %d$       %d$\n", p, n, wholesaleSiteId.get(p)));
        System.out.println();

        //Two key map
        Map<MapKey, Integer> costAdIdSiteId = new HashMap<>();
        for (LogInfo item : logInfoList) {
            MapKey mapKey = new MapKey(item.getAdId(), item.getSiteId());
            Boolean check = costAdIdSiteId.containsKey(mapKey);
            if (check) {
                int cost = costAdIdSiteId.get(mapKey) + item.getCost();
                costAdIdSiteId.replace(mapKey, cost);
            } else {
                costAdIdSiteId.put(mapKey, item.getCost());
            }
        }

        Map<MapKey, Integer> wholesaleAdIdSiteId = new HashMap<>();
        for (LogInfo item : logInfoList) {
            MapKey mapKey = new MapKey(item.getAdId(), item.getSiteId());
            Boolean check = wholesaleAdIdSiteId.containsKey(mapKey);
            if (check) {
                int wholesale = wholesaleAdIdSiteId.get(mapKey) + item.getWholesale();
                wholesaleAdIdSiteId.replace(mapKey, wholesale);
            } else {
                wholesaleAdIdSiteId.put(mapKey, item.getWholesale());
            }
        }

        // Prinf
        System.out.println("Ad_Id" + "    SiteId" + "     Cost" + "    Wholesale");
        for (MapKey mapKey : costAdIdSiteId.keySet()) {
            System.out.print(mapKey.getAdId() + "        ");
            System.out.print(mapKey.getSiteId() + "          ");
            System.out.print(costAdIdSiteId.get(mapKey) + "$     ");
            System.out.print(wholesaleAdIdSiteId.get(mapKey) + "$");
            System.out.println();
        }
    }
}
