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
        for (int i = 0; i < logInfoList.size(); i++) {
            Boolean check = costAdId.containsKey(logInfoList.get(i).getAdId());

            // Check exist adId in map
            if (check) {
                for (Map.Entry<Integer, Integer> entry : costAdId.entrySet()) {
                    if (logInfoList.get(i).getAdId() == entry.getKey()) {
                        int cost = entry.getValue() + logInfoList.get(i).getCost();
                        costAdId.replace(logInfoList.get(i).getAdId(), cost);
                    }
                }
            } else {
                costAdId.put(logInfoList.get(i).getAdId(), logInfoList.get(i).getCost());
            }
        }

        // Statistic wholesale with adId
        Map<Integer, Integer> wholesaleAdId =  new HashMap<>();
        for (int i = 0; i < logInfoList.size(); i++) {
            Boolean check = wholesaleAdId.containsKey(logInfoList.get(i).getAdId());

            // Check exist adId in map
            if (check) {
                for (Map.Entry<Integer, Integer> entry : wholesaleAdId.entrySet()) {
                    if (logInfoList.get(i).getAdId() == entry.getKey()) {
                        int wholesale = entry.getValue() + logInfoList.get(i).getWholesale();
                        wholesaleAdId.replace(logInfoList.get(i).getAdId(), wholesale);
                    }
                }
            } else {
                wholesaleAdId.put(logInfoList.get(i).getAdId(), logInfoList.get(i).getWholesale());
            }
        }

        // Print
        System.out.println("\nAdId  " + "  Cost  " + "  Wholesale");
        costAdId.forEach((p, n) -> System.out.format("%d       %d$       %d$\n", p, n, wholesaleAdId.get(p)));

        // Statistic cost with siteId
        Map<Integer, Integer> costSiteId =  new HashMap<>();
        for (int i = 0; i < logInfoList.size(); i++) {
            Boolean check = costSiteId.containsKey(logInfoList.get(i).getSiteId());

            // Check exist siteId in map
            if (check) {
                for (Map.Entry<Integer, Integer> entry : costSiteId.entrySet()) {
                    if (logInfoList.get(i).getSiteId() == entry.getKey()) {
                        int cost = entry.getValue() + logInfoList.get(i).getCost();
                        costSiteId.replace(logInfoList.get(i).getSiteId(), cost);
                    }
                }
            } else {
                costSiteId.put(logInfoList.get(i).getSiteId(), logInfoList.get(i).getCost());
            }
        }

        // Statistic wholesale with siteId
        Map<Integer, Integer> wholesaleSiteId =  new HashMap<>();
        for (int i = 0; i < logInfoList.size(); i++) {
            Boolean check = wholesaleSiteId.containsKey(logInfoList.get(i).getSiteId());

            // Check exist siteId in map
            if (check) {
                for (Map.Entry<Integer, Integer> entry : wholesaleSiteId.entrySet()) {
                    if (logInfoList.get(i).getSiteId() == entry.getKey()) {
                        int wholasale = entry.getValue() + logInfoList.get(i).getWholesale();
                        wholesaleSiteId.replace(logInfoList.get(i).getSiteId(), wholasale);
                    }
                }
            } else {
                wholesaleSiteId.put(logInfoList.get(i).getSiteId(), logInfoList.get(i).getWholesale());
            }
        }

        // Print
        System.out.println("\nSiteId  " + "Cost  " + "  Wholesale");
        costSiteId.forEach((p, n) -> System.out.format("%d       %d$       %d$\n", p, n, wholesaleSiteId.get(p)));
        System.out.println();

        //Two key map
        Map<MapKey, Integer> costAdIdSiteId = new HashMap<>();
        for (int i = 0; i < logInfoList.size(); i++) {
            MapKey mapKey = new MapKey(logInfoList.get(i).getAdId(), logInfoList.get(i).getSiteId());
            Boolean check = costAdIdSiteId.containsKey(mapKey);
            if (check) {
                for (Map.Entry<MapKey, Integer> entry : costAdIdSiteId.entrySet()) {
                    if ((logInfoList.get(i).getAdId() == entry.getKey().getAdId()) && (logInfoList.get(i).getSiteId() == entry.getKey().getSiteId())) {
                        int cost = entry.getValue() + logInfoList.get(i).getCost();
                        costAdIdSiteId.replace(mapKey, cost);
                    }
                }
            } else {
                costAdIdSiteId.put(mapKey, logInfoList.get(i).getCost());
            }
        }

        Map<MapKey, Integer> wholesaleAdIdSiteId = new HashMap<>();
        for (int i = 0; i < logInfoList.size(); i++) {
            MapKey mapKey = new MapKey(logInfoList.get(i).getAdId(), logInfoList.get(i).getSiteId());
            Boolean check = wholesaleAdIdSiteId.containsKey(mapKey);
            if (check) {
                for (Map.Entry<MapKey, Integer> entry : wholesaleAdIdSiteId.entrySet()) {
                    if ((logInfoList.get(i).getAdId() == entry.getKey().getAdId()) && (logInfoList.get(i).getSiteId() == entry.getKey().getSiteId())) {
                        int wholesale = entry.getValue() + logInfoList.get(i).getWholesale();
                        wholesaleAdIdSiteId.replace(mapKey, wholesale);
                    }
                }
            } else {
                wholesaleAdIdSiteId.put(mapKey, logInfoList.get(i).getWholesale());
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
